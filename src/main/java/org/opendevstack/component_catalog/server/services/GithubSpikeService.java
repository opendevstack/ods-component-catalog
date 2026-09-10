package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.client.github.v114.ApiClient;
import org.opendevstack.component_catalog.client.github.v114.api.GitApi;
import org.opendevstack.component_catalog.client.github.v114.api.PullsApi;
import org.opendevstack.component_catalog.client.github.v114.api.ReposApi;
import org.opendevstack.component_catalog.client.github.v114.auth.HttpBearerAuth;
import org.opendevstack.component_catalog.client.github.v114.model.GitCreateRefRequest;
import org.opendevstack.component_catalog.client.github.v114.model.PullsCreateRequest;
import org.opendevstack.component_catalog.client.github.v114.model.PullsMergeRequest;
import org.opendevstack.component_catalog.client.github.v114.model.PullsUpdateRequest;
import org.opendevstack.component_catalog.client.github.v114.model.ReposCreateOrUpdateFileContentsRequest;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.GithubSpikeServiceProps;
import org.opendevstack.component_catalog.server.model.GithubCreatePullRequestRequest;
import org.opendevstack.component_catalog.server.model.GithubCreateRefRequest;
import org.opendevstack.component_catalog.server.model.GithubFileRequest;
import org.opendevstack.component_catalog.server.model.GithubMergePullRequestRequest;
import org.opendevstack.component_catalog.server.model.GithubUpdatePullRequestRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GithubSpikeService {

    private static final String GITHUB_BASE_REST_URL = "https://api.github.com";

    @Qualifier("githubSpikeServiceConfig")
    private final GithubSpikeServiceProps properties;
    private final ObjectMapper objectMapper;

    public String getRepository() {
        return serialize(reposApi().reposGet(owner(), repository()));
    }

    public String getBranch(String branch) {
        return serialize(reposApi().reposGetBranch(owner(), repository(), branch));
    }

    public String getContent(String path, String ref) {
        return serialize(reposApi().reposGetContent(owner(), repository(), path, ref));
    }

    public String listBranches(Boolean protectedBranches, Integer perPage, Integer page) {
        return serialize(reposApi().reposListBranches(owner(), repository(), protectedBranches, perPage, page));
    }

    public String listCommits(
            String sha,
            String path,
            String author,
            String committer,
            OffsetDateTime since,
            OffsetDateTime until,
            Integer perPage,
            Integer page) {
        return serialize(reposApi().reposListCommits(
                owner(), repository(), sha, path, author, committer, since, until, perPage, page));
    }

    public String listPullRequests(
            String state,
            String head,
            String base,
            String sort,
            String direction,
            OffsetDateTime since,
            Integer perPage,
            Integer page) {
        return serialize(pullsApi().pullsList(
                owner(), repository(), state, head, base, sort, direction, since, perPage, page));
    }

    public String getCollaboratorPermission(String username) {
        return serialize(reposApi().reposGetCollaboratorPermissionLevel(owner(), repository(), username));
    }

    public String getTree(String treeSha, Boolean recursive) {
        return serialize(gitApi().gitGetTree(owner(), repository(), treeSha, recursive == null ? null : recursive.toString()));
    }

    public String createRef(GithubCreateRefRequest request) {
        var githubRequest = new GitCreateRefRequest()
                .ref(request.getRef())
                .sha(request.getSha());
        return serialize(gitApi().gitCreateRef(owner(), repository(), githubRequest));
    }

    public String deleteRef(String ref) {
        var githubRef = normalizeRefForDelete(ref);
        gitApi().gitDeleteRef(owner(), repository(), githubRef);
        return serialize(objectMapper.createObjectNode().put("deleted", true).put("ref", githubRef));
    }

    public String createOrUpdateFile(String path, GithubFileRequest request) {
        var githubRequest = new ReposCreateOrUpdateFileContentsRequest()
                .message(request.getMessage())
                .content(request.getContent())
                .branch(request.getBranch());
        Optional.ofNullable(request.getSha()).ifPresent(githubRequest::sha);
        return serialize(reposApi().reposCreateOrUpdateFileContents(owner(), repository(), path, githubRequest));
    }

    public String createPullRequest(GithubCreatePullRequestRequest request) {
        var githubRequest = new PullsCreateRequest()
                .title(request.getTitle())
                .head(request.getHead())
                .base(request.getBase());
        Optional.ofNullable(request.getBody()).ifPresent(githubRequest::body);
        Optional.ofNullable(request.getDraft()).ifPresent(githubRequest::draft);
        Optional.ofNullable(request.getMaintainerCanModify()).ifPresent(githubRequest::maintainerCanModify);
        return serialize(pullsApi().pullsCreate(owner(), repository(), githubRequest));
    }

    public String updatePullRequest(Integer number, GithubUpdatePullRequestRequest request) {
        var githubRequest = new PullsUpdateRequest();
        Optional.ofNullable(request.getTitle()).ifPresent(githubRequest::title);
        Optional.ofNullable(request.getBody()).ifPresent(githubRequest::body);
        Optional.ofNullable(request.getState())
                .map(value -> PullsUpdateRequest.StateEnum.fromValue(value.getValue()))
                .ifPresent(githubRequest::state);
        Optional.ofNullable(request.getBase()).ifPresent(githubRequest::base);
        Optional.ofNullable(request.getMaintainerCanModify()).ifPresent(githubRequest::maintainerCanModify);
        return serialize(pullsApi().pullsUpdate(owner(), repository(), number, githubRequest));
    }

    public String mergePullRequest(Integer number, GithubMergePullRequestRequest request) {
        var githubRequest = new PullsMergeRequest();
        Optional.ofNullable(request.getCommitTitle()).ifPresent(githubRequest::commitTitle);
        Optional.ofNullable(request.getCommitMessage()).ifPresent(githubRequest::commitMessage);
        Optional.ofNullable(request.getMergeMethod())
                .map(value -> PullsMergeRequest.MergeMethodEnum.fromValue(value.getValue()))
                .ifPresent(githubRequest::mergeMethod);
        return serialize(pullsApi().pullsMerge(owner(), repository(), number, githubRequest));
    }

    private ReposApi reposApi() {
        return new ReposApi(apiClient());
    }

    private GitApi gitApi() {
        return new GitApi(apiClient());
    }

    private PullsApi pullsApi() {
        return new PullsApi(apiClient());
    }

    private ApiClient apiClient() {
        var apiClient = new ApiClient(githubRestTemplate()).setBasePath(GITHUB_BASE_REST_URL);
        var auth = (HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(properties.getBearerToken());
        return apiClient;
    }

    private RestTemplate githubRestTemplate() {
        var restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().stream()
                .filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .map(MappingJackson2HttpMessageConverter.class::cast)
                .forEach(converter -> converter.setObjectMapper(objectMapper));
        return restTemplate;
    }

    private String owner() {
        return properties.getOwner();
    }

    private String repository() {
        return properties.getRepository();
    }

    private String normalizeRefForDelete(String ref) {
        if (ref == null || ref.isBlank()) {
            throw new IllegalArgumentException("GitHub ref must not be blank");
        }

        var normalizedRef = ref.startsWith("refs/") ? ref.substring("refs/".length()) : ref;
        return normalizedRef.contains("/") ? normalizedRef : "heads/" + normalizedRef;
    }

    private String serialize(Object result) {
        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Could not serialize GitHub response", exception);
        }
    }
}
