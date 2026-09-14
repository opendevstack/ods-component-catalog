package org.opendevstack.component_catalog.server.controllers;

import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.server.api.GithubApi;
import org.opendevstack.component_catalog.server.model.GithubCreatePullRequestRequest;
import org.opendevstack.component_catalog.server.model.GithubCreateRefRequest;
import org.opendevstack.component_catalog.server.model.GithubFileRequest;
import org.opendevstack.component_catalog.server.model.GithubMergePullRequestRequest;
import org.opendevstack.component_catalog.server.model.GithubUpdatePullRequestRequest;
import org.opendevstack.component_catalog.server.services.GithubSpikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;

@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@RequiredArgsConstructor
@Controller
public class GithubApiController implements GithubApi {

    private final GithubSpikeService githubSpikeService;

    @Override
    public ResponseEntity<String> getGithubRepository() {
        return ResponseEntity.ok(githubSpikeService.getRepository());
    }

    @Override
    public ResponseEntity<String> getGithubBranch(String branch) {
        return ResponseEntity.ok(githubSpikeService.getBranch(branch));
    }

    @Override
    public ResponseEntity<String> getGithubContent(String path, String ref) {
        return ResponseEntity.ok(githubSpikeService.getContent(path, ref));
    }

    @Override
    public ResponseEntity<String> listGithubBranches(
            Boolean protected_, Integer perPage, Integer page) {
        return ResponseEntity.ok(githubSpikeService.listBranches(protected_, perPage, page));
    }

    @Override
    public ResponseEntity<String> listGithubCommits(
            String sha,
            String path,
            String author,
            String committer,
            OffsetDateTime since,
            OffsetDateTime until,
            Integer perPage,
            Integer page) {
        return ResponseEntity.ok(githubSpikeService.listCommits(
                sha, path, author, committer, since, until, perPage, page));
    }

    @Override
    public ResponseEntity<String> listGithubPullRequests(
            String state,
            String head,
            String base,
            String sort,
            String direction,
            OffsetDateTime since,
            Integer perPage,
            Integer page) {
        return ResponseEntity.ok(githubSpikeService.listPullRequests(
                state, head, base, sort, direction, since, perPage, page));
    }

    @Override
    public ResponseEntity<String> listGithubTeams(
            String organization, Integer perPage, Integer page) {
        return ResponseEntity.ok(githubSpikeService.listTeams(organization, perPage, page));
    }

    @Override
    public ResponseEntity<String> listGithubTeamMembers(
            String organization,
            String teamSlug,
            String role,
            Integer perPage,
            Integer page) {
        return ResponseEntity.ok(githubSpikeService.listTeamMembers(
                organization, teamSlug, role, perPage, page));
    }

    @Override
    public ResponseEntity<String> listGithubTeamRepositories(
            String organization,
            String teamSlug,
            Integer perPage,
            Integer page) {
        return ResponseEntity.ok(githubSpikeService.listTeamRepositories(
                organization, teamSlug, perPage, page));
    }

    @Override
    public ResponseEntity<String> getGithubCollaboratorPermission(String username) {
        return ResponseEntity.ok(githubSpikeService.getCollaboratorPermission(username));
    }

    @Override
    public ResponseEntity<String> getGithubTree(String treeSha, Boolean recursive) {
        return ResponseEntity.ok(githubSpikeService.getTree(treeSha, recursive));
    }

    @Override
    public ResponseEntity<String> createGithubRef(GithubCreateRefRequest request) {
        return ResponseEntity.ok(githubSpikeService.createRef(request));
    }

    @Override
    public ResponseEntity<String> deleteGithubRef(String ref) {
        return ResponseEntity.ok(githubSpikeService.deleteRef(ref));
    }

    @Override
    public ResponseEntity<String> createOrUpdateGithubFile(String path, GithubFileRequest request) {
        return ResponseEntity.ok(githubSpikeService.createOrUpdateFile(path, request));
    }

    @Override
    public ResponseEntity<String> createGithubPullRequest(GithubCreatePullRequestRequest request) {
        return ResponseEntity.ok(githubSpikeService.createPullRequest(request));
    }

    @Override
    public ResponseEntity<String> updateGithubPullRequest(
            Integer number, GithubUpdatePullRequestRequest request) {
        return ResponseEntity.ok(githubSpikeService.updatePullRequest(number, request));
    }

    @Override
    public ResponseEntity<String> mergeGithubPullRequest(
            Integer number, GithubMergePullRequestRequest request) {
        return ResponseEntity.ok(githubSpikeService.mergePullRequest(number, request));
    }
}
