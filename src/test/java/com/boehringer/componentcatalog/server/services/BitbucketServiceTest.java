package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.client.bitbucket.v89.api.PermissionManagementApi;
import com.boehringer.componentcatalog.client.bitbucket.v89.api.RepositoryApi;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.*;
import com.boehringer.componentcatalog.config.ApplicationPropertiesConfiguration.BitbucketServiceProps;
import com.boehringer.componentcatalog.server.mother.BitbucketPathAtMother;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BitbucketServiceTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private RepositoryApi repositoryApi;
    @Mock
    private PermissionManagementApi permissionApi;

    @InjectMocks private BitbucketService service;

    @Test
    void testGetTextFileContents_success() {
        // given
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        byte[] contentBytes = "Hello World".getBytes();
        ResponseEntity<Resource> response = ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(new ByteArrayResource(contentBytes));

        when(repositoryApi.streamRawWithHttpInfo(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(response);

        // when
        Optional<Pair<MediaType, String>> result = service.getTextFileContents(pathAt);

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
}
