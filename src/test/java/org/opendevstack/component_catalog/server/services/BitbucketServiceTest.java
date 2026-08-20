package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.client.bitbucket.v89.api.PermissionManagementApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.api.ProjectApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.api.PullRequestsApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.api.RepositoryApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.model.*;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BitbucketServiceTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ApplicationPropertiesConfiguration.BitbucketServiceProps bitbucketServiceProps;
    @Mock
    private RepositoryApi repositoryApi;
    @Mock
    private PermissionManagementApi permissionApi;
    @Mock
    private ProjectApi projectApi;
    @Mock
    private PullRequestsApi pullRequestsApi;

    @InjectMocks
    private BitbucketService service;

    @Test
    void testGetCachedTextFileContents_success() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        byte[] contentBytes = "Hello World".getBytes();
        ResponseEntity<Resource> response = ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(new ByteArrayResource(contentBytes));

        when(repositoryApi.streamRawWithHttpInfo(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(response);

        // when
        Optional<Pair<MediaType, String>> result = service.getCachedTextFileContents(pathAt);

        // then
        assertThat(result).isPresent();
        assertThat(result.orElseThrow().getRight()).isEqualTo("Hello World");
    }

    @Test
    void testGetLastCommit_success() {
        // given
        RestCommit commit = new RestCommit();
        GetCommits200Response responseBody = new GetCommits200Response();
        responseBody.setValues(List.of(commit));

        ResponseEntity<GetCommits200Response> response = ResponseEntity.ok(responseBody);
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(response);

        // when
        Optional<RestCommit> result = service.getLastCommit("PROJ", "repo", "main");

        // then
        assertThat(result).isPresent();
        assertThat(result.orElseThrow()).isEqualTo(commit);
    }

    @Test
    void testSearchRepoUserPermissions_success() throws Exception {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", Map.of("name", "testuser", "active", true));
        userMap.put("permission", "REPO_READ");

        List<Map<String, Object>> values = List.of(userMap);
        Map<String, Object> responseMap = Map.of("values", values);

        when(permissionApi.searchPermissions1(any(), any(), any(), any(), any()))
                .thenReturn("");
        when(objectMapper.readValue(anyString(), any(TypeReference.class)))
                .thenReturn(responseMap);
        when(objectMapper.convertValue(any(), eq(RestPermittedUser.class)))
                .thenReturn(new RestPermittedUser()
                        .user(new RestPullRequestParticipantUser().name("testuser").active(true))
                        .permission(RestPermittedUser.PermissionEnum.REPO_READ));
        when(permissionApi.findGroupsForUser(any(), any(), any(), any()))
                .thenReturn(new FindUsersInGroup200Response());

        // when
        Set<RestPermittedUser.PermissionEnum> result = service.searchRepoUserPermissions(pathAt, "testuser");

        // then
        assertThat(result).contains(RestPermittedUser.PermissionEnum.REPO_READ);
    }

    // getLastCommit
    @Test
    void givenABitbucketPathAt_whenGetLastCommit_thenCommitIdIsReturned() {
        // given
        var commitId = "commit-id-1234";

        var pathAt = BitbucketPathAtMother.of();

        RestCommit commit = new RestCommit();
        commit.setId(commitId);

        GetCommits200Response getCommits200Response = new GetCommits200Response();
        getCommits200Response.setValues(List.of(commit));

        when(repositoryApi.getCommitsWithHttpInfo(
                pathAt.getProjectKey(),
                pathAt.getRepoSlug(),
                null, // avatarScheme
                pathAt.getSubPath(), // this filters commits by file path
                null, null, pathAt.getAt(), null, null, null, null,
                BigDecimal.valueOf(0), BigDecimal.valueOf(1)
        )).thenReturn(ResponseEntity.ok(getCommits200Response));

        // when
        var lastCommit = service.getLastCommit(pathAt);

        // then
        assertThat(lastCommit).isPresent()
                .contains(commitId);
    }

    @Test
    void givenABitbucketPathAt_whenGetLastCommit_andFileDoesNotExist_thenEmptyIsReturned() {
        // given
        var pathAt = BitbucketPathAtMother.of();

        GetCommits200Response getCommits200Response = new GetCommits200Response();

        when(repositoryApi.getCommitsWithHttpInfo(
                pathAt.getProjectKey(),
                pathAt.getRepoSlug(),
                null, // avatarScheme
                pathAt.getSubPath(), // this filters commits by file path
                null, null, pathAt.getAt(), null, null, null, null,
                BigDecimal.valueOf(0), BigDecimal.valueOf(1)
        )).thenReturn(ResponseEntity.ok(getCommits200Response));

        // when
        var lastCommit = service.getLastCommit(pathAt);

        // then
        assertThat(lastCommit).isNotPresent();
    }

    @Test
    void givenABitbucketPathAt_whenPushFile_thenBitbucketAPIisCalled() {
        // given
        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "commit-id-1234";
        var content = "file content";

        // when
        service.pushFile(pathAt, sourceCommitId, content);

        // then
        verify(repositoryApi).editFile(pathAt.getSubPath(), pathAt.getProjectKey(), pathAt.getRepoSlug(),
                "master", content, "Automated update from BitbucketService", null, sourceCommitId);
    }

    @Test
    void givenAValidPathAt_whenDoesContributingFileExists_andApiReturns2xx_thenReturnTrue() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();
        ResponseEntity<Void> response = ResponseEntity.ok().build();
        when(projectApi.streamContributingWithHttpInfo(pathAt.getProjectKey(), pathAt.getRepoSlug(), pathAt.getAt(),
                null, null, null, null))
                .thenReturn(response);

        // when
        boolean exists = service.doesContributingFileExists(pathAt);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void givenAValidPathAt_whenDoesContributingFileExists_andApiThrowsException_thenReturnFalse() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();
        when(projectApi.streamContributingWithHttpInfo(pathAt.getProjectKey(), pathAt.getRepoSlug(), pathAt.getAt(),
                null, null, null, null))
                .thenThrow(new RuntimeException("error"));

        // when
        boolean exists = service.doesContributingFileExists(pathAt);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void givenSinglePageResponse_whenGetFilenames_thenAllReturned() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        StreamFiles200Response response = new StreamFiles200Response();
        response.setValues(List.of("file1.json", "file2.json"));
        response.setIsLastPage(true);

        when(repositoryApi.streamFiles1(
                pathAt.getSubPath(),
                pathAt.getProjectKey(),
                pathAt.getRepoSlug(),
                pathAt.getAt(),
                BigDecimal.ZERO,
                null
        )).thenReturn(response);

        // when
        var result = service.getFilenamesFromRemoteDirectory(pathAt);

        // then
        assertThat(result).containsExactly("file1.json", "file2.json");
    }

    @Test
    void givenMultiplePages_whenGetFilenames_thenAllPagesAreAggregated() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        StreamFiles200Response page1 = new StreamFiles200Response();
        page1.setValues(List.of("file1.json", "file2.json"));
        page1.setIsLastPage(false);
        page1.setNextPageStart(2);

        StreamFiles200Response page2 = new StreamFiles200Response();
        page2.setValues(List.of("file3.json"));
        page2.setIsLastPage(true);

        when(repositoryApi.streamFiles1(
                pathAt.getSubPath(), pathAt.getProjectKey(), pathAt.getRepoSlug(),
                pathAt.getAt(), BigDecimal.ZERO, null
        )).thenReturn(page1);

        when(repositoryApi.streamFiles1(
                pathAt.getSubPath(), pathAt.getProjectKey(), pathAt.getRepoSlug(),
                pathAt.getAt(), new BigDecimal(2), null
        )).thenReturn(page2);

        // when
        var result = service.getFilenamesFromRemoteDirectory(pathAt);

        // then
        assertThat(result).containsExactly("file1.json", "file2.json", "file3.json");
    }

    @Test
    void givenNextPageStartNull_whenNotLastPage_thenStopsLoop() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        StreamFiles200Response response = new StreamFiles200Response();
        response.setValues(List.of("file1.json"));
        response.setIsLastPage(false);
        response.setNextPageStart(null);

        when(repositoryApi.streamFiles1(
                any(), any(), any(), any(), any(), any()
        )).thenReturn(response);

        // when
        var result = service.getFilenamesFromRemoteDirectory(pathAt);

        // then
        assertThat(result).containsExactly("file1.json");
    }

    @Test
    void givenMultipleFiles_whenPushFilesInSeries_thenAllFilesUseCustomCommitMessage() {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of().copy().appendSubPath("another-file.json");

        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "commit-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "commit-2", "content-2")
        );

        // when
        service.pushFilesInSeries(fileUpdates, "custom commit message");

        // then
        verify(repositoryApi).editFile(firstPathAt.getSubPath(), firstPathAt.getProjectKey(), firstPathAt.getRepoSlug(),
                "master", "content-1", "custom commit message", null, "commit-1");
        verify(repositoryApi).editFile(
                secondPathAt.getSubPath(),
                secondPathAt.getProjectKey(),
                secondPathAt.getRepoSlug(),
                "master",
                "content-2",
                "custom commit message",
                null,
                "commit-2"
        );
    }

    @Test
    void givenMultipleFiles_whenPushFilesAtomically_thenSquashMergeCreatesSingleTargetCommit() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");

        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        var settings = new RestRepositoryPullRequestSettings()
                .mergeConfig(new RestPullRequestSettingsMergeConfig().strategies(List.of(
                        new RestPullRequestMergeStrategy(null, true, null, null).id("squash")
                )));
        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenReturn(settings);

        var createdPr = new RestPullRequest().id(123L).version(7);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("123"),
                eq(firstPathAt.getRepoSlug()),
                eq("7"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenReturn(new RestPullRequest());

        // when
        service.pushFilesAtomically(fileUpdates, "atomic commit message");

        // then
        verify(repositoryApi).createBranch(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestCreateBranchRequest.class)
        );
        ArgumentCaptor<RestPullRequest> pullRequestCaptor = ArgumentCaptor.forClass(RestPullRequest.class);
        verify(pullRequestsApi).createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                pullRequestCaptor.capture()
        );
        assertThat(pullRequestCaptor.getValue().getParticipants()).isNull();
        assertThat(pullRequestCaptor.getValue().getReviewers()).isNull();

        ArgumentCaptor<RestPullRequestMergeRequest> mergeRequestCaptor =
                ArgumentCaptor.forClass(RestPullRequestMergeRequest.class);
        verify(pullRequestsApi).merge(
                eq(firstPathAt.getProjectKey()),
                eq("123"),
                eq(firstPathAt.getRepoSlug()),
                eq("7"),
                mergeRequestCaptor.capture()
        );
        assertThat(mergeRequestCaptor.getValue().getStrategyId()).isEqualTo("squash");
        assertThat(mergeRequestCaptor.getValue().getMessage()).isEqualTo("atomic commit message");

        verify(repositoryApi, times(2)).editFile(
                anyString(),
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                anyString(),
                anyString(),
                eq("atomic commit message"),
                isNull(),
                anyString()
        );

        verify(repositoryApi).deleteBranch(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                argThat(request -> request != null
                        && request.getName() != null
                        && request.getName().startsWith("refs/heads/cc-atomic-"))
        );
    }

    @Test
    void givenMergeFailure_whenPushFilesAtomically_thenDeclinePrBeforeDeletingTempBranch() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");

        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        var settings = new RestRepositoryPullRequestSettings()
                .mergeConfig(new RestPullRequestSettingsMergeConfig().strategies(List.of(
                        new RestPullRequestMergeStrategy(null, true, null, null).id("squash")
                )));
        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenReturn(settings);

        var createdPr = new RestPullRequest().id(123L).version(7);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("123"),
                eq(firstPathAt.getRepoSlug()),
                eq("7"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));

        // when
        assertThatCode(() -> service.pushFilesAtomically(fileUpdates, "atomic commit message"))
                .doesNotThrowAnyException();

        // then
        verify(pullRequestsApi).decline(
                eq(firstPathAt.getProjectKey()),
                eq("123"),
                eq(firstPathAt.getRepoSlug()),
                eq("7"),
                any(RestPullRequestDeclineRequest.class)
        );
        verify(repositoryApi).deleteBranch(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                argThat(request -> request != null
                        && request.getName() != null
                        && request.getName().startsWith("refs/heads/cc-atomic-"))
        );
    }

    @Test
    void givenBrokenRepoSettingsPayload_whenPushFilesAtomically_thenFallbackToGlobalMergeConfig() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");

        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenThrow(new RestClientException("parse error"));

        var globalMergeConfig = new RestPullRequestMergeConfig()
                .strategies(List.of(new RestPullRequestMergeStrategy(null, true, null, null).id("squash")));
        when(pullRequestsApi.getMergeConfig("git")).thenReturn(globalMergeConfig);

        var createdPr = new RestPullRequest().id(456L).version(9);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("456"),
                eq(firstPathAt.getRepoSlug()),
                eq("9"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenReturn(new RestPullRequest());

        // when
        service.pushFilesAtomically(fileUpdates, "atomic commit message");

        // then
        verify(repositoryApi).getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug());
        verify(pullRequestsApi).getMergeConfig("git");

        ArgumentCaptor<RestPullRequestMergeRequest> mergeRequestCaptor =
                ArgumentCaptor.forClass(RestPullRequestMergeRequest.class);
        verify(pullRequestsApi).merge(
                eq(firstPathAt.getProjectKey()),
                eq("456"),
                eq(firstPathAt.getRepoSlug()),
                eq("9"),
                mergeRequestCaptor.capture()
        );
        assertThat(mergeRequestCaptor.getValue().getStrategyId()).isEqualTo("squash");
    }

    @Test
    void givenRepoSquashStrategyNotEnabled_whenPushFilesAtomically_thenUsesRelaxedRepoStrategy() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");
        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        var repoSettings = new RestRepositoryPullRequestSettings()
                .mergeConfig(new RestPullRequestSettingsMergeConfig().strategies(List.of(
                        new RestPullRequestMergeStrategy(null, false, null, null).id("squash-repo-relaxed")
                )));
        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenReturn(repoSettings);

        var globalMergeConfig = new RestPullRequestMergeConfig()
                .strategies(List.of(new RestPullRequestMergeStrategy(null, true, null, null).id("merge-commit")));
        when(pullRequestsApi.getMergeConfig("git")).thenReturn(globalMergeConfig);

        var createdPr = new RestPullRequest().id(457L).version(10);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("457"),
                eq(firstPathAt.getRepoSlug()),
                eq("10"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenReturn(new RestPullRequest());

        // when
        service.pushFilesAtomically(fileUpdates, "atomic commit message");

        // then
        ArgumentCaptor<RestPullRequestMergeRequest> mergeRequestCaptor =
                ArgumentCaptor.forClass(RestPullRequestMergeRequest.class);
        verify(pullRequestsApi).merge(
                eq(firstPathAt.getProjectKey()),
                eq("457"),
                eq(firstPathAt.getRepoSlug()),
                eq("10"),
                mergeRequestCaptor.capture()
        );
        assertThat(mergeRequestCaptor.getValue().getStrategyId()).isEqualTo("squash-repo-relaxed");
    }

    @Test
    void givenGlobalSquashStrategyNotEnabled_whenPushFilesAtomically_thenUsesRelaxedGlobalStrategy() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");
        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        var repoSettings = new RestRepositoryPullRequestSettings()
                .mergeConfig(new RestPullRequestSettingsMergeConfig().strategies(List.of(
                        new RestPullRequestMergeStrategy(null, true, null, null).id("merge-commit")
                )));
        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenReturn(repoSettings);

        var globalMergeConfig = new RestPullRequestMergeConfig()
                .strategies(List.of(
                        new RestPullRequestMergeStrategy(null, false, null, null).id("squash-global-relaxed")
                ));
        when(pullRequestsApi.getMergeConfig("git")).thenReturn(globalMergeConfig);

        var createdPr = new RestPullRequest().id(458L).version(11);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("458"),
                eq(firstPathAt.getRepoSlug()),
                eq("11"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenReturn(new RestPullRequest());

        // when
        service.pushFilesAtomically(fileUpdates, "atomic commit message");

        // then
        ArgumentCaptor<RestPullRequestMergeRequest> mergeRequestCaptor =
                ArgumentCaptor.forClass(RestPullRequestMergeRequest.class);
        verify(pullRequestsApi).merge(
                eq(firstPathAt.getProjectKey()),
                eq("458"),
                eq(firstPathAt.getRepoSlug()),
                eq("11"),
                mergeRequestCaptor.capture()
        );
        assertThat(mergeRequestCaptor.getValue().getStrategyId()).isEqualTo("squash-global-relaxed");
    }


    @Test
    void givenNoSquashInRepoOrGlobal_whenPushFilesAtomically_thenHardcodedFallbackIsUsed() throws Exception {
        // given
        var firstPathAt = BitbucketPathAtMother.of();
        var secondPathAt = BitbucketPathAtMother.of("AnotherFileOrDir");
        var fileUpdates = List.of(
                new BitbucketService.BitbucketFileUpdate(firstPathAt, "ignored-1", "content-1"),
                new BitbucketService.BitbucketFileUpdate(secondPathAt, "ignored-2", "content-2")
        );

        when(bitbucketServiceProps.getBaseRawUrl()).thenReturn(new URL("https://my-bitbucket-server.com"));
        when(bitbucketServiceProps.getBaseRestUrl())
                .thenReturn(new URL("https://my-bitbucket-server.com/rest/api/latest"));

        var commit = new RestCommit().id("temp-commit");
        var commitsResponse = new GetCommits200Response().values(List.of(commit));
        when(repositoryApi.getCommitsWithHttpInfo(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
        ))
                .thenReturn(ResponseEntity.ok(commitsResponse));

        when(repositoryApi.getPullRequestSettings1(firstPathAt.getProjectKey(), firstPathAt.getRepoSlug()))
                .thenReturn(new RestRepositoryPullRequestSettings());
        when(pullRequestsApi.getMergeConfig("git")).thenReturn(new RestPullRequestMergeConfig());

        var createdPr = new RestPullRequest().id(460L).version(13);
        when(pullRequestsApi.createPullRequest(
                eq(firstPathAt.getProjectKey()),
                eq(firstPathAt.getRepoSlug()),
                any(RestPullRequest.class)
        ))
                .thenReturn(createdPr);
        when(pullRequestsApi.merge(
                eq(firstPathAt.getProjectKey()),
                eq("460"),
                eq(firstPathAt.getRepoSlug()),
                eq("13"),
                any(RestPullRequestMergeRequest.class)
        ))
                .thenReturn(new RestPullRequest());

        // when
        service.pushFilesAtomically(fileUpdates, "atomic commit message");

        // then
        ArgumentCaptor<RestPullRequestMergeRequest> mergeRequestCaptor =
                ArgumentCaptor.forClass(RestPullRequestMergeRequest.class);
        verify(pullRequestsApi).merge(
                eq(firstPathAt.getProjectKey()),
                eq("460"),
                eq(firstPathAt.getRepoSlug()),
                eq("13"),
                mergeRequestCaptor.capture()
        );
        assertThat(mergeRequestCaptor.getValue().getStrategyId())
                .isEqualTo(BitbucketService.FALLBACK_SQUASH_STRATEGY_ID);
    }

}
