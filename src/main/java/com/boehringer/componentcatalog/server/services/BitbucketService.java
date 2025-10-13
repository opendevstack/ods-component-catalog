package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.api.PermissionManagementApi;
import com.boehringer.componentcatalog.client.bitbucket.v89.api.RepositoryApi;
import com.boehringer.componentcatalog.client.bitbucket.v89.auth.HttpBearerAuth;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetCommits200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCommit;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDetailedUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermittedUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermittedUser.PermissionEnum;
import com.boehringer.componentcatalog.config.ApplicationPropertiesConfiguration.BitbucketServiceCacheProps;
import com.boehringer.componentcatalog.config.ApplicationPropertiesConfiguration.BitbucketServiceProps;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketIOException;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketInvalidEntityException;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.util.FileFormatUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.boehringer.componentcatalog.util.EitherUtils.*;

@Service
@CacheConfig(cacheNames={BitbucketServiceCacheProps.CACHE_NAME})
@AllArgsConstructor
@Slf4j
public class BitbucketService {

    private final BitbucketServiceProps bitbucketServiceProps;
    private final ObjectMapper objectMapper;
    private final ApiClient apiClient;
    private final RepositoryApi repositoryApi;
    private final PermissionManagementApi permissionApi;

    @PostConstruct
    public void configureApiClient() {
        // Broken, sometimes the debugging interceptor consumes response's bytes!
        // this.apiClient.setDebugging(true)
        var auth = (HttpBearerAuth) this.apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(this.bitbucketServiceProps.getBearerToken());
        this.apiClient.setBasePath(this.bitbucketServiceProps.getBaseRestUrl().toString());
    }

    public BitbucketPathAt.BitbucketPathAtBuilder pathAtBuilder() {
        return BitbucketPathAt.builder()
                .baseRawUrl(this.bitbucketServiceProps.getBaseRawUrl().toString())
                .baseRestUrl(this.bitbucketServiceProps.getBaseRestUrl().toString());
    }

    @Cacheable
    public Optional<Pair<MediaType, String>> getTextFileContents(BitbucketPathAt pathAt)
            throws BitbucketInvalidEntityException {
        return this.getFileContents(pathAt.getProjectKey(), pathAt.getRepoSlug(), pathAt.getSubPath(),
                pathAt.getAt(), String::new);
    }

    @Cacheable
    public Optional<Pair<MediaType, byte[]>> getBinaryFileContents(BitbucketPathAt pathAt)
            throws BitbucketInvalidEntityException {
        return this.getFileContents(pathAt.getProjectKey(), pathAt.getRepoSlug(), pathAt.getSubPath(),
                pathAt.getAt(), bytes -> bytes);
    }

    private <T> Optional<Pair<MediaType, T>> getFileContents(String projectKey, String repoSlug, String subPath, String at,
                                                             Function<byte[], T> transformer)
                throws BitbucketInvalidEntityException {
        log.debug("Fetching file content for path: {}", subPath);

        try {
            var response = this.repositoryApi.streamRawWithHttpInfo(subPath, projectKey, repoSlug, at,
                    null, "false", null, null);

            var maybeContents = either(this::extractResponseContents, response);

            // If for whatever reason we can't process the response, we will consider that the file has some issue,
            // even though that might not be the case: maybe the problem could be due to e.g. a networking issue
            // while retrieving the file bytes
            if(maybeContents.isFailed()) {
                var errMsg = "Unable to process file response from Bitbucket, repo: %s, path: %s"
                        .formatted(repoSlug, subPath);
                log.error(errMsg);
                throw new BitbucketInvalidEntityException(errMsg, maybeContents.getError());
            }

            var contentsType = maybeContents.getValue().getLeft();
            var contentsBytes = maybeContents.getValue().getRight();

            log.debug("File content for path: {}, contentsType: {}, bytes size: {}",
                    subPath, contentsType, contentsBytes.length);

            T contents = transformer.apply(contentsBytes);

            return Optional.of(Pair.of(contentsType, contents));
        } catch (HttpClientErrorException.NotFound e) {
            log.debug("File not found on Bitbucket, repo: {}, path: {}", repoSlug, subPath);
            return Optional.empty();
        } catch (HttpClientErrorException.UnprocessableEntity e) {
            var errMsg = "Invalid entity on Bitbucket, repo: %s, path: %s".formatted(repoSlug, subPath);
            log.error(errMsg);
            throw new BitbucketInvalidEntityException(errMsg, e);
        } catch (HttpStatusCodeException e) {
            var errMsg = "Unable to get file from Bitbucket: %s, path: %s".formatted(repoSlug, subPath);
            log.error(errMsg, e);
            throw new BitbucketIOException(errMsg, e);
        }
    }

    @SneakyThrows
    private Pair<MediaType, byte[]> extractResponseContents(ResponseEntity<Resource> response) {
        var filename = response.getHeaders().getContentDisposition().getFilename();
        var respContentType = response.getHeaders().getContentType();
        var respBytes = Optional.ofNullable(response.getBody())
                .flatMap(maybeValueFrom(uncheckedFrom(Resource::getContentAsByteArray))) // IO errors: consider empty body
                .orElse(new byte[0]);

        if(respBytes.length == 0) {
            throw new UnsupportedOperationException("Unable to process empty file, response content type: %s".formatted(respContentType));
        }

        // Get content type based on the content type and/or file content, just in case the content type is not correct
        var contentType = FileFormatUtils.detectMediaType(filename, respBytes);

        return Pair.of(contentType, respBytes);
    }

    @Cacheable
    public Optional<RestCommit> getLastCommit(String projectKey, String repoSlug, String at) throws BitbucketIOException {
        try {
            var response = this.repositoryApi.getCommitsWithHttpInfo(
                    projectKey, repoSlug, null, null, null, null, at,
                    null, null, null, null, null, BigDecimal.ONE);

            return Optional.ofNullable(response.getBody())
                    .map(GetCommits200Response::getValues)
                    .orElse(List.of())
                    .stream()
                    .findFirst();
        } catch (Exception e) {
            var errMsg = "Unable to get last commit from Bitbucket, project key: %s, repo: %s, at: %s".formatted(projectKey, repoSlug, at);
            log.error(errMsg, e);
            throw new BitbucketIOException(errMsg, e);
        }
    }

    @Cacheable
    public Set<PermissionEnum> searchRepoUserPermissions(BitbucketPathAt repoPathAt, String username) {
        try {
            // This is a workaround in order to get the returned JSON string.
            //  According to the (broken!) API definition, this method is supposed to return "any",
            //  which is not useful to us, in order to tell apart users and groups, error responses, etc.
            var res = this.permissionApi.searchPermissions1(repoPathAt.getProjectKey(), repoPathAt.getRepoSlug(),
                    null, null, null);

            var resObj =  objectMapper.readValue(res, new TypeReference<Map<String, Object>>() {});
            var values =  resObj.getOrDefault("values", List.of());

            if(!List.class.isAssignableFrom(values.getClass())) {
                log.error("Failed to parse permissions response, assuming user has no permissions, error: {}", values);
                return Set.of();
            }

            var permitted = (List<Map<String, Object>>) values;

            var permittedUsers = permitted.stream()
                    .filter(m -> m.containsKey("user"))
                    .map(m -> this.objectMapper.convertValue(m, RestPermittedUser.class))
                    .filter(u -> u.getUser().getActive())
                    .collect(Collectors.toSet());

            var permittedGroups = permitted.stream()
                    .filter(m -> m.containsKey("group"))
                    .map(m -> Pair.of((String) m.get("group"), (String) m.get("permission")))
                    .collect(Collectors.toSet());

            var userGroupsNames = this.getUserGroupsNames(username);

            var userPermissions = permittedUsers.stream()
                    .filter(u -> Objects.equals(username, u.getUser().getName()))
                    .map(RestPermittedUser::getPermission)
                    .collect(Collectors.toSet());

            var userGroupsPermissions = permittedGroups.stream()
                    .filter(g -> userGroupsNames.stream().anyMatch(ug -> ug.equalsIgnoreCase(g.getLeft())))
                    .map(Pair::getRight)
                    .map(PermissionEnum::fromValue)
                    .collect(Collectors.toSet());

            // Return without duplicates
            return Stream.concat(userPermissions.stream(), userGroupsPermissions.stream())
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            var errMsg = "Unable to get permissions info from Bitbucket, username: %s, query permissions for: %s"
                    .formatted(username, repoPathAt.getPathAt());
            log.error(errMsg, e);
            throw new BitbucketIOException(errMsg, e);
        }
    }

    @SuppressWarnings("java:S125")
    private Set<String> getUserGroupsNames(String username) {
        /*
         Workaround, the API definition is broken, the following method returns RestDetailedUsers JSON
         instead of RestDetailedGroups JSON.
         The actual JSON response is something like:
         {
          "size": 2,
          "limit": 25,
          "isLastPage": true,
          "values": [
            {
              "name": "some group",
              "deletable": true
            },
            {
              "name": "Another-Different-Group",
              "deletable": true
            }
          ],
          "start": 0
        }
        */
        var values = this.permissionApi.findGroupsForUser(username, null,null, BigDecimal.valueOf(1000))
                .getValues();

        return values.stream()
                .map(RestDetailedUser::getName)
                .collect(Collectors.toSet());
    }

}
