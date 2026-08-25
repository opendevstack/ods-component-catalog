package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.ProvisionedComponentsCacheProps;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.cache.ProjectComponentsCacheService;
import org.opendevstack.component_catalog.server.services.exceptions.ComponentAlreadyExistsException;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.exceptions.UnableToDeserializeEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponentRequest;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsService {

    public static final String JSON_FILE_EXTENSION = ".json";
    private static final String DELETE_COMPONENT_COMMIT_MESSAGE = "Delete component and archive provisioning history";
    private static final String DELETE_COMPONENT_PULL_REQUEST_REQUESTER_PLACEHOLDER = "XXXX";
    private static final String DELETE_COMPONENT_PULL_REQUEST_TITLE = "Deletion requested by " + DELETE_COMPONENT_PULL_REQUEST_REQUESTER_PLACEHOLDER;
    private final BitbucketService bitbucketService;
    private final ObjectMapper objectMapper;
    private final ProjectComponentsService projectComponentsService;
    private final ProvisionerActionsConfiguration provisionerActionsConfiguration;
    private final ProjectComponentsCacheService projectComponentsCacheService;

    @Synchronized
    public void updateComponentProvisioningStatus(String projectKey,
                                                  ProjectComponentRequest request) throws JsonProcessingException { //componentUrl can be null
        log.debug("Processing provisioning status for projectKey: {}, status: {}, componentId: {}, catalogItemId: {}, componentUrl: {}",
                projectKey, request.getStatus(), request.getComponentId(), request.getCatalogItemId(), request.getComponentUrl());

        var pathAt = getProjectComponentBitbucketPathAt(projectKey);

        var sourceCommitId = bitbucketService.getLastCommit(pathAt).orElse(null); // If no sourceCommitId, that means is a new file

        var projectComponents = getProjectComponents(getProjectComponentBitbucketPathAt(projectKey));

        validate(projectComponents, request.getComponentId(), request.getStatus());

        var existsComponent = componentExistsInProjectComponents(projectComponents, request.getComponentId());
        ProjectComponents updatedProjectComponents;

        var currentTimestamp = String.valueOf(System.currentTimeMillis());

        request.setUpdatedAt(currentTimestamp);

        if (existsComponent) {
            log.debug("Updating componentKey: {} to projectComponents: {}. Status: {}", request.getComponentId(), projectComponents, request.getStatus());

            var createdAt = projectComponents.getComponents().get(request.getComponentId()).getCreatedAt();
            request.setCreatedAt(createdAt);
            updatedProjectComponents = projectComponentsService.updateExistingComponent(
                    projectComponents, request);
        } else {
            log.debug("Adding new componentKey: {} to projectComponents: {}", request.getComponentId(), projectComponents);

            request.setCreatedAt(currentTimestamp);
            updatedProjectComponents = projectComponentsService.addNewComponent(
                    projectComponents, request);
        }

        // Update file with new status
        saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);
        log.debug("{} component with timestamp {}", (existsComponent ? "Updated" : "Created"), currentTimestamp);
    }

    @Synchronized
    public void updatePartiallyComponentProvisioningStatus(String projectKey,
                                                  ProjectComponentRequest request) throws JsonProcessingException { //componentUrl can be null
        log.debug("Processing provisioning status for projectKey: {}, status: {}, componentId: {}, catalogItemId: {}, componentUrl: {}",
                projectKey, request.getStatus(), request.getComponentId(), request.getCatalogItemId(), request.getComponentUrl());

        var pathAt = getProjectComponentBitbucketPathAt(projectKey);

        var sourceCommitId = bitbucketService.getLastCommit(pathAt).orElse(null); // If no sourceCommitId, that means is a new file

        var projectComponents = getProjectComponents(getProjectComponentBitbucketPathAt(projectKey));

        if (projectComponents == null || projectComponents.getComponents() == null || !projectComponents.getComponents().containsKey(request.getComponentId())) {
            throw new ElementNotFoundException("In a partial update, the projectComponent should exist.");
        }

        var currentTimestamp = System.currentTimeMillis();
        request.setCreatedAt(projectComponents.getComponents().get(request.getComponentId()).getCreatedAt());
        request.setUpdatedAt(String.valueOf(currentTimestamp));

        log.debug("Updating partially componentKey: {} to projectComponents: {}. Status: {}", request.getComponentId(), projectComponents, request.getStatus());

        var updatedProjectComponents = projectComponentsService.updatePartiallyExistingComponent(
                projectComponents, request);

        // Update file with new status
        saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);
        log.debug("Updated component with timestamp {}", currentTimestamp);
    }

    @Synchronized
    public void deleteComponentProvisioningStatus(String projectKey, String componentId, String requester) throws JsonProcessingException {
        log.debug("Deleting provisioning status. ProjectKey: {}, componentId: {}", projectKey, componentId);

        var projectComponentPathAt = getProjectComponentBitbucketPathAt(projectKey);

        var sourceCommitId = bitbucketService.getLastCommit(projectComponentPathAt).orElse(null); // If no sourceCommitId, that means is a new file

        if (sourceCommitId == null) {
            log.debug("No component provisioning status for pathAt: {}", projectComponentPathAt);

            throw new RestEntityNotFoundException("No component provisioning status for pathAt: " + projectComponentPathAt);
        } else {
            var projectComponentHistoryPathAt = getProjectComponentHistoryBitbucketPathAt(projectKey);

            var projectComponentsHistorySourceCommitId = bitbucketService.getLastCommit(projectComponentHistoryPathAt).orElse(null); // If no sourceCommitId, that means is a new file

            var projectComponents = getProjectComponents(projectComponentPathAt);
            var projectComponentsHistory = getProjectComponents(projectComponentHistoryPathAt);

            var componentToBeDeleted = projectComponents.getComponents().get(componentId);

            if (componentToBeDeleted == null) {
                log.debug("Component with id {} not found in project components for projectKey: {}", componentId, projectKey);
                throw new RestEntityNotFoundException("Component with id " + componentId + " not found in project components for projectKey: " + projectKey);
            }

            var componentIdWithEpochTime = componentId + "_" + Instant.now().toEpochMilli();
            var projectComponentRequest = ProjectComponentRequest.builder()
                    .catalogItemId(componentToBeDeleted.getCatalogItemId())
                    .componentId(componentIdWithEpochTime)
                    .status(componentToBeDeleted.getStatus())
                    .componentUrl(componentToBeDeleted.getComponentUrl())
                    .createdAt(componentToBeDeleted.getCreatedAt())
                    .updatedAt(componentToBeDeleted.getUpdatedAt())
                    .workflowJobId(componentToBeDeleted.getWorkflowJobId())
                    .deletionWorkflowJobId(componentToBeDeleted.getDeletionWorkflowJobId())
                    .parameters(componentToBeDeleted.getParameters())
                    .build();

            var updatedProjectComponents = projectComponentsService.deleteComponent(projectComponents, componentId);
            var updatedProjectComponentsHistory = projectComponentsService.addNewComponent(projectComponentsHistory, projectComponentRequest);

            // update the file without the component to be removed
            var updatedProjectComponentRequest = SaveProjectComponentRequest.builder()
                    .pathAt(projectComponentPathAt)
                    .sourceCommitId(sourceCommitId)
                    .projectComponents(updatedProjectComponents)
                    .build();
            var updatedProjectComponentsHistoryRequest = SaveProjectComponentRequest.builder()
                    .pathAt(projectComponentHistoryPathAt)
                    .sourceCommitId(projectComponentsHistorySourceCommitId)
                    .projectComponents(updatedProjectComponentsHistory)
                    .build();

            saveProjectComponents(updatedProjectComponentRequest, updatedProjectComponentsHistoryRequest, requester);
        }
    }

    @Synchronized
    public boolean isCatalogItemAlreadyProvisionedInProject(String projectKey, String catalogItemId) {
        log.debug("Checking if provisioning completed for projectKey: {}, componentId: {}",
                projectKey, catalogItemId);

        var projectComponents = getProjectComponents(getProjectComponentBitbucketPathAt(projectKey));

        return isProvisioned(projectComponents, catalogItemId);
    }

    protected void validate(ProjectComponents projectComponents, String componentId, Status status) {
        if (status == Status.CREATING) {
            validateComponentDoesNotExistsWhenCreating(projectComponents, componentId);
        } else {
            log.debug("No creating status, skipping validation.");
        }
    }

    // We need to prevent there is no update if some other is in the middle of it
    // Pending to discuss ISO levels and how to block in deep
    @Synchronized
    protected void saveProjectComponents(BitbucketPathAt pathAt, String sourceCommitId, ProjectComponents updatedProjectComponents) throws JsonProcessingException {
        try {
            String jsonUpdatedProjectComponents = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updatedProjectComponents);
            bitbucketService.pushFile(pathAt, sourceCommitId, jsonUpdatedProjectComponents);

            projectComponentsCacheService.evict(pathAt.getProjectKeyFromSubPath());
            projectComponentsCacheService.evict("allProjectKeys");
        } catch (HttpClientErrorException httpClientErrorException) {
            log.warn("There were an issue persisting project components: {}", updatedProjectComponents, httpClientErrorException);

            if (httpClientErrorException.getStatusCode() == HttpStatus.CONFLICT &&
                httpClientErrorException.getMessage().contains("com.atlassian.bitbucket.content.FileContentUnmodifiedException")) {
                log.info("Bitbucket rejected update as there were no changes to be pushed. Ignoring exception");
            } else {
                throw  httpClientErrorException;
            }
        }
    }

    // Persist both files atomically through a squash-merge flow, yielding a single commit on target branch.
    // We need to prevent there is no update if some other is in the middle of it
    // Pending to discuss ISO levels and how to block in deep
    @Synchronized
    protected void saveProjectComponents(
            SaveProjectComponentRequest projectComponentRequest,
            SaveProjectComponentRequest updatedProjectComponentsHistoryRequest,
            String requester) throws JsonProcessingException {
        try {
            String jsonUpdatedProjectComponents = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectComponentRequest.getProjectComponents());
            String jsonUpdatedProjectComponentsHistory = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updatedProjectComponentsHistoryRequest.getProjectComponents());

            var customPullRequestTitle = DELETE_COMPONENT_PULL_REQUEST_TITLE.replace(DELETE_COMPONENT_PULL_REQUEST_REQUESTER_PLACEHOLDER, requester);

            bitbucketService.pushFilesAtomically(List.of(
                    new BitbucketService.BitbucketFileUpdate(projectComponentRequest.getPathAt(), projectComponentRequest.getSourceCommitId(), jsonUpdatedProjectComponents),
                    new BitbucketService.BitbucketFileUpdate(updatedProjectComponentsHistoryRequest.getPathAt(), updatedProjectComponentsHistoryRequest.getSourceCommitId(), jsonUpdatedProjectComponentsHistory)
            ), DELETE_COMPONENT_COMMIT_MESSAGE, customPullRequestTitle);

            projectComponentsCacheService.evict(projectComponentRequest.getPathAt().getProjectKeyFromSubPath());
            projectComponentsCacheService.evict("allProjectKeys");
        } catch (HttpClientErrorException httpClientErrorException) {
            log.warn("There were an issue persisting project components: {}", projectComponentRequest.getProjectComponents(), httpClientErrorException);

            if (httpClientErrorException.getStatusCode() == HttpStatus.CONFLICT &&
                    httpClientErrorException.getMessage().contains("com.atlassian.bitbucket.content.FileContentUnmodifiedException")) {
                log.info("Bitbucket rejected update as there were no changes to be pushed. Ignoring exception");
            } else {
                throw  httpClientErrorException;
            }
        }
    }

    private void validateComponentDoesNotExistsWhenCreating(ProjectComponents projectComponents, String componentId) {
        if (componentExistsInProjectComponents(projectComponents, componentId)) {
            throw new ComponentAlreadyExistsException("Component with id '" + componentId + "' already exists in the project components.");
        }
    }

    private boolean componentExistsInProjectComponents(ProjectComponents projectComponents, String componentId) {
        return Optional.ofNullable(projectComponents.getComponents())
                .filter(components -> components.containsKey(componentId))
                .isPresent();
    }

    @SneakyThrows
    protected boolean isProvisioned(ProjectComponents projectComponents, String catalogItemId) {
        if (projectComponents == null || projectComponents.getComponents() == null || catalogItemId == null) {
            return false;
        }
        String target = projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId.trim());
        for (ProjectComponent c : projectComponents.getComponents().values()) {
            String cid = c.getCatalogItemId();
            if (cid != null && !"null".equalsIgnoreCase(cid) && cid.trim().equals(target)) {
                return true;
            }
        }
        return false;
    }

    public List<String> listAllProjectsJsons() {
        var bitbucketProjectsDirectoryPathAt = bitbucketService.pathAtBuilder()
                .projectKey(provisionerActionsConfiguration.getProjectKey())
                .repoSlug(provisionerActionsConfiguration.getRepositorySlug())
                .at(provisionerActionsConfiguration.getBranchName())
                .subPath(provisionerActionsConfiguration.getProjectsPath())
                .build();

        log.debug("Listing all project JSONs from path: {}", bitbucketProjectsDirectoryPathAt);
        var projectJsonFiles = bitbucketService.getFilenamesFromRemoteDirectory(bitbucketProjectsDirectoryPathAt, false);

        log.debug("Project JSON files retrieved: {}", projectJsonFiles);
        return projectJsonFiles;
    }

    // We need to block the method to get the project components from bitbucket, not the methods that work on them (not only I mean)
    @Synchronized
    @Cacheable(cacheNames = ProvisionedComponentsCacheProps.CACHE_NAME, key = "#projectKey")
    public ProjectComponents getProjectComponents(String projectKey) {
        return getProjectComponents(getProjectComponentBitbucketPathAt(projectKey));
    }

    @Synchronized
    public ProjectComponents getProjectComponents(BitbucketPathAt pathAt) {
        log.info("Retrieving project components from project {} via Bitbucket API...", pathAt.getProjectKeyFromSubPath());
        return bitbucketService.getTextFileContents(pathAt)
                .map( content -> {
                    try {
                        return objectMapper.readValue(content.getValue(), ProjectComponents.class);
                    } catch (JsonProcessingException e) {
                        throw new UnableToDeserializeEntityException("Unable to deserialize ProjectComponents.", e);
                    }
                })
                .orElseGet( () -> {
                    log.debug("Project components file not found for pathAt: {}", pathAt);

                    return projectComponentsService.createNewComponent();
                });
    }

    private BitbucketPathAt getProjectComponentBitbucketPathAt(String projectKey) {
        return bitbucketService.pathAtBuilder()
                .projectKey(provisionerActionsConfiguration.getProjectKey())
                .repoSlug(provisionerActionsConfiguration.getRepositorySlug())
                .subPath(provisionerActionsConfiguration.getSubPath().replace(provisionerActionsConfiguration.getSubPathToken(), projectKey))
                .at(provisionerActionsConfiguration.getBranchName())
                .build();
    }

    private BitbucketPathAt getProjectComponentHistoryBitbucketPathAt(String projectKey) {
        return bitbucketService.pathAtBuilder()
                .projectKey(provisionerActionsConfiguration.getProjectKey())
                .repoSlug(provisionerActionsConfiguration.getRepositorySlug())
                .subPath(provisionerActionsConfiguration.getProjectHistorySubPath().replace(provisionerActionsConfiguration.getSubPathToken(), projectKey))
                .at(provisionerActionsConfiguration.getBranchName())
                .build();
    }

    @Cacheable(cacheNames = ProvisionedComponentsCacheProps.CACHE_NAME)
    public List<String> getAllProjectComponentsProjectKeys() {
        var projectComponentFiles = listAllProjectsJsons();

        var projectKeys = projectComponentFiles.stream()
                .filter(filename -> filename.endsWith(JSON_FILE_EXTENSION))
                .map(fileName -> fileName.split(JSON_FILE_EXTENSION)[0])
                .toList();

        log.debug("Project keys found: {}", projectKeys);

        return projectKeys;
    }

    @Builder
    @Getter
    @ToString
    protected static class SaveProjectComponentRequest {
        BitbucketPathAt pathAt;
        String sourceCommitId;
        ProjectComponents projectComponents;
    }
}
