package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.client.bitbucket.v89.api.PermissionManagementApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.api.ProjectApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.api.RepositoryApi;
import org.opendevstack.component_catalog.client.bitbucket.v89.model.*;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BitbucketServiceTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private RepositoryApi repositoryApi;
    @Mock
    private PermissionManagementApi permissionApi;
    @Mock
    private ProjectApi projectApi;

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
        assertTrue(result.isPresent());
        assertEquals("Hello World", result.get().getRight());
    }

    @Test
    void testGetLastCommit_success() {
        // given
        RestCommit commit = new RestCommit();
        GetCommits200Response responseBody = new GetCommits200Response();
        responseBody.setValues(List.of(commit));

        ResponseEntity<GetCommits200Response> response = ResponseEntity.ok(responseBody);
        when(repositoryApi.getCommitsWithHttpInfo(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(response);

        // when
        Optional<RestCommit> result = service.getLastCommit("PROJ", "repo", "main");

        // then
        assertTrue(result.isPresent());
        assertEquals(commit, result.get());
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
                .thenReturn(new RestPermittedUser().user(new RestPullRequestParticipantUser().name("testuser").active(true)).permission(RestPermittedUser.PermissionEnum.REPO_READ));
        when(permissionApi.findGroupsForUser(any(), any(), any(), any()))
                .thenReturn(new FindUsersInGroup200Response());

        // when
        Set<RestPermittedUser.PermissionEnum> result = service.searchRepoUserPermissions(pathAt, "testuser");

        // then
        assertTrue(result.contains(RestPermittedUser.PermissionEnum.REPO_READ));
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
        assertTrue(exists);
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
        assertFalse(exists);
    }
}
