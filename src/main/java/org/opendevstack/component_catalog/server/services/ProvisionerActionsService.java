package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.ComponentAlreadyExistsException;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.exceptions.UnableToDeserializeEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsService {

    private final BitbucketService bitbucketService;
    private final ObjectMapper objectMapper;
    private final ProjectComponentsService projectComponentsService;
    private final ProvisionerActionsConfiguration provisionerActionsConfiguration;

    @Synchronized
    public void updateComponentProvisioningStatus(String projectKey,
                                                  Status status,
                                                  String componentId,
                                                  String catalogItemId,
                                                  String componentUrl,
                                                  List<Pair<String, List<String>>> parameters) throws JsonProcessingException { //componentUrl can be null
        log.debug("Processing provisioning status for projectKey: {}, status: {}, componentId: {}, catalogItemId: {}, componentUrl: {}",
                projectKey, status, componentId, catalogItemId, componentUrl);

        var pathAt = getBitbucketPathAt(projectKey);
        List<Parameter> projectComponentParameters = map(parameters);

        var sourceCommitId = bitbucketService.getLastCommit(pathAt).orElse(null); // If no sourceCommitId, that means is a new file

        var projectComponents = getProjectComponents(projectKey);

        validate(projectComponents, componentId, status);

        var existsComponent = componentExistsInProjectComponents(projectComponents, componentId);
        ProjectComponents updatedProjectComponents;

        if (existsComponent) {
            log.trace("Updating componentKey: {} to projectComponents: {}. Status: {}", componentId, projectComponents, status);
            updatedProjectComponents = projectComponentsService.updateExistingComponent(
                    projectComponents, componentId, catalogItemId, status, componentUrl, projectComponentParameters);
        } else {
            log.trace("Adding new componentKey: {} to projectComponents: {}", componentId, projectComponents);
            updatedProjectComponents = projectComponentsService.addNewComponent(
                    projectComponents, componentId, catalogItemId, status, componentUrl, projectComponentParameters);
        }

        // Update file with new status
        saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);
    }

    @Synchronized
    public void updatePartiallyComponentProvisioningStatus(String projectKey,
                                                  Status status,
                                                  String componentId,
                                                  String catalogItemId,
                                                  String componentUrl,
                                                  String workflowJobId,
                                                  List<Pair<String, List<String>>> parameters) throws JsonProcessingException { //componentUrl can be null
        log.debug("Processing provisioning status for projectKey: {}, status: {}, componentId: {}, catalogItemId: {}, componentUrl: {}",
                projectKey, status, componentId, catalogItemId, componentUrl);

        var pathAt = getBitbucketPathAt(projectKey);
        List<Parameter> projectComponentParameters = map(parameters);

        var sourceCommitId = bitbucketService.getLastCommit(pathAt).orElse(null); // If no sourceCommitId, that means is a new file

        var projectComponents = getProjectComponents(projectKey);

        if (projectComponents == null || projectComponents.getComponents() == null) {
            throw new ElementNotFoundException("In a partial update, the projectComponent should exist.");
        }

        log.trace("Updating partially componentKey: {} to projectComponents: {}. Status: {}", componentId, projectComponents, status);
        var updatedProjectComponents = projectComponentsService.updatePartiallyExistingComponent(
                projectComponents, componentId, catalogItemId, status, componentUrl, workflowJobId, projectComponentParameters);

        // Update file with new status
        saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);
    }

    @Synchronized
    public void deleteComponentProvisioningStatus(String projectKey, String componentId) throws JsonProcessingException {
        log.debug("Deleting provisioning status. ProjectKey: {}, componentId: {}", projectKey, componentId);

        var pathAt = getBitbucketPathAt(projectKey);

        var sourceCommitId = bitbucketService.getLastCommit(pathAt).orElse(null); // If no sourceCommitId, that means is a new file

        if (sourceCommitId == null) {
            log.debug("No component provisioning status for pathAt: {}", pathAt);

            throw new RestEntityNotFoundException("No component provisioning status for pathAt: " + pathAt);
        } else {
            var projectComponents = getProjectComponents(pathAt);

            var updatedProjectComponents = projectComponentsService.deleteComponent(projectComponents, componentId);

            // update the file without the component to be removed
            saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);
        }
    }

    @Synchronized
    public boolean isCatalogItemAlreadyProvisionedInProject(String projectKey, String catalogItemId) {
        log.debug("Checking if provisioning completed for projectKey: {}, componentId: {}",
                projectKey, catalogItemId);

        var projectComponents = getProjectComponents(projectKey);

        return isProvisioned(projectComponents, catalogItemId);
    }

    protected void validate(ProjectComponents projectComponents, String componentId, Status status) {
        if (status == Status.CREATING) {
            validateComponentDoesNotExistsWhenCreating(projectComponents, componentId);
        } else {
            log.debug("No creating status, skipping validation.");
        }
    }

    // We need to prevent there is no update if some other is on the middle of it
    // Pending to discuss ISO levels and how to block in deep
    @Synchronized
    protected void saveProjectComponents(BitbucketPathAt pathAt, String sourceCommitId, ProjectComponents updatedProjectComponents) throws JsonProcessingException {
        try {
            String jsonUpdatedProjectComponents = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updatedProjectComponents);
            bitbucketService.pushFile(pathAt, sourceCommitId, jsonUpdatedProjectComponents);
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

    private static @NonNull List<Parameter> map(List<Pair<String, List<String>>> parameters) {
        return parameters.stream()
                .map(pair -> Parameter.builder().name(pair.getLeft()).values(pair.getRight()).build())
                .toList();
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

    // We need to block the method to get the project components from bitbucket, not the methods that work on them (not only I mean)
    @Synchronized
    public ProjectComponents getProjectComponents(String projectKey) {
        return getProjectComponents(getBitbucketPathAt(projectKey));
    }

    @Synchronized
    public ProjectComponents getProjectComponents(BitbucketPathAt pathAt) {
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

    private BitbucketPathAt getBitbucketPathAt(String projectKey) {
        return bitbucketService.pathAtBuilder()
                .projectKey(provisionerActionsConfiguration.getProjectKey())
                .repoSlug(provisionerActionsConfiguration.getRepositorySlug())
                .subPath(provisionerActionsConfiguration.getSubPath().replace(provisionerActionsConfiguration.getSubPathToken(), projectKey))
                .at(provisionerActionsConfiguration.getBranchName())
                .build();
    }

}
