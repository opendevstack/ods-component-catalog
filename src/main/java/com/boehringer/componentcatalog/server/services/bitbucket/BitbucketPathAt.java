package com.boehringer.componentcatalog.server.services.bitbucket;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.join;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Getter
@EqualsAndHashCode
@ToString
public class BitbucketPathAt implements Serializable {

    private static final String PATH_TEMPLATE = "projects/${projectKey}/repos/${repoSlug}/raw/${subPath}?at=${at}";
    private static final String BROWSE_URL_TEMPLATE = "${baseUrl}/${path}";
    private static final String REST_URL_TEMPLATE = "${baseUrl}/api/latest/${path}";
    private static final String BASE_URL = "baseUrl";

    private final String baseRawUrl;
    private final String baseRestUrl;
    private String projectKey;
    private String repoSlug;
    private String subPath;
    private String at;
    private String rawUrl;
    private URI rawUri;
    private String browseUrl;
    private URI browseUri;
    private String restUrl;
    private URI restUri;
    private String pathAt;

    @Builder(toBuilder = true)
    public BitbucketPathAt(String baseRawUrl, String baseRestUrl, //NOSONAR This constructor has many parameters due to it being required by a lombok-generated builder
                           String rawUrl, String restUrl,
                           String pathAt,
                           String projectKey, String repoSlug,
                           String subPath, String at) {
        // Both mandatory
        if (baseRawUrl == null || baseRestUrl == null) {
            throw new IllegalArgumentException("baseRawUrl and baseRestUrl are mandatory");
        }

        this.baseRawUrl = baseRawUrl;
        this.baseRestUrl = baseRestUrl;

        if(rawUrl != null) {
            this.fromFullUrl(rawUrl.trim());
        } else if(restUrl != null) {
            this.fromFullUrl(restUrl.trim());
        } else if(pathAt != null) {
            this.fromPathAt(pathAt.trim());
        } else {
            this.fromPathVars(projectKey, repoSlug, subPath, at);
        }
    }

    public BitbucketPathAt appendSubPath(String subPath) {
        // Update properties
        this.fromComponents(fromUri(this.rawUri)
                .pathSegment(sanitizePathSegment(subPath))
                .build());

        return this;
    }

    public BitbucketPathAt getParent() {
        // Remove the last segment
        var thisComps = fromUri(this.rawUri).build();

        var thisSegments = thisComps.getPathSegments();
        var parentSegments = thisSegments.subList(0, thisSegments.size() - 1);

        var parentComps = UriComponentsBuilder.newInstance()
                .uriComponents(thisComps)
                .replacePath(null)
                .pathSegment(parentSegments.toArray(new String[0]))
                .build();

        return this.toBuilder().build().fromComponents(parentComps);
    }

    public BitbucketPathAt copy() {
        return this.toBuilder().build();
    }

    private void fromFullUrl(String fullUrl) {
        // Example URL to parse:
        // https://my-bitbucket-server.com/projects/MYPROJECT/repos/repo-slug/browse/some-package/SomeFileOrDir?at=refs%2Fheads%2Fmaster
        var builder = fromUriString(fullUrl);
        var urlComps = builder.build();

        var encodedAt = urlComps
                .getQueryParams()
                .get("at")
                .get(0);

        // Workaround: avoid double-encoding issues
        var decodedAt = URLDecoder.decode(encodedAt, StandardCharsets.UTF_8);
        var comps = builder
                .cloneBuilder()
                .replaceQueryParam("at", decodedAt)
                .build();

        this.fromComponents(comps);
    }

    private BitbucketPathAt fromPathVars(String projectKey, String repoSlug, String subPath, String at) {
        this.projectKey = projectKey;
        this.repoSlug = repoSlug;
        this.subPath = subPath;
        this.at = at;

        this.pathAt = urlFromTemplate(PATH_TEMPLATE, "projectKey", this.projectKey, "repoSlug", this.repoSlug,
                "subPath", this.subPath, "at", this.at);

        this.restUrl = urlFromTemplate(REST_URL_TEMPLATE, BASE_URL, this.baseRestUrl, "path", this.pathAt);
        this.rawUrl = urlFromTemplate(BROWSE_URL_TEMPLATE, BASE_URL, this.baseRawUrl, "path", this.pathAt);

        this.restUri = fromUriString(this.restUrl).build().toUri();
        this.rawUri = fromUriString(this.rawUrl).build().toUri();

        this.browseUrl = this.rawUrl.replace("/raw/", "/browse/");
        this.browseUri = fromUriString(this.browseUrl).build().toUri();

        return this;
    }

    private void fromPathAt(String pathAt) {
        var fullUrl = urlFromTemplate(BROWSE_URL_TEMPLATE, BASE_URL, baseRawUrl, "path", pathAt);

        this.fromFullUrl(fullUrl);
    }

    private BitbucketPathAt fromComponents(UriComponents comps) {
        var pathSegments = comps.getPathSegments();

        var startIdx = 0;
        var endIdx = pathSegments.indexOf("projects");
        var baseSegments = join("/", pathSegments.subList(startIdx, endIdx));

        var baseUrl = baseSegments.isEmpty()
                ? "%s://%s".formatted(comps.getScheme(), comps.getHost())
                : "%s://%s/%s".formatted(comps.getScheme(), comps.getHost(), baseSegments);

        // Don't allow the base URL to be different from the base REST or browse URLs, given that currently only one
        // bearer token is supported, and it's used for both REST and browse requests with the configured base URLs
        if(!List.of(baseRawUrl, baseRestUrl).contains(baseUrl)) {
            var errMsg = "Base URL: '%s' doesn't match neither baseRestUrl: '%s', baseBrowseUrl: '%s'"
                    .formatted(baseUrl, baseRestUrl, baseRawUrl);

            throw new IllegalArgumentException(errMsg);
        }

        // from: projects (included), to: browse (excluded)
        startIdx = endIdx;
        endIdx = pathSegments.indexOf("raw");
        var mainPathSegments = pathSegments.subList(startIdx, endIdx);

        // from: browse (excluded), to: path end
        // This will always work for paths for files, given that there is always
        // a file name at the end, but not for the root directory path of a repository
        startIdx = pathSegments.indexOf("raw") + 1;
        endIdx = pathSegments.size();
        List<String> subPathSegments = List.of();

        if(startIdx < endIdx) { // "raw" is not the final segment
            subPathSegments = pathSegments.subList(startIdx, endIdx);
        }

        var mainPathValues = pairsToMap(mainPathSegments);

        var pathProjectKey = mainPathValues.get("projects");
        var pathRepoSlug = mainPathValues.get("repos");

        var pathSubPath = join("/", subPathSegments);

        var atParam = comps
                .getQueryParams()
                .get("at")
                .get(0);

        return this.fromPathVars(pathProjectKey, pathRepoSlug, pathSubPath, atParam);
    }

    private static String urlFromTemplate(String template, String... pairs) {
        var valuesMap = pairsToMap(List.of(pairs));

        return StringSubstitutor.replace(template, valuesMap);
    }

    private static Map<String, String> pairsToMap(List<String> pairs) {
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i < pairs.size() - 1; i += 2) {
            String key = pairs.get(i);
            String value = pairs.get(i + 1);
            result.put(key, value);
        }

        return result;
    }

    private static String sanitizePathSegment(String segment) {
        // Sanitize just in case the path starts with a '/' or './'
        return segment.replaceFirst("^/|^\\./", "");
    }
}
