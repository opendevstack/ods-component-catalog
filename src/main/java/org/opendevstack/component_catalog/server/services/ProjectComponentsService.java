package org.opendevstack.component_catalog.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidCatalogItemIdStructureException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ProjectComponentsService {

    public ProjectComponents createNewComponent() {
        return new ProjectComponents();
    }

    @SneakyThrows
    public ProjectComponents addNewComponent(ProjectComponents projectComponents,
                                             String componentId,
                                             String catalogItemId,
                                             Status status,
                                             String componentUrl) {
        var catalogItemIdWithoutBranch = getRepoPathFromCatalogItemId(catalogItemId);
        var branchReference = getBranchRefFromCatalogItemId(catalogItemId);

        var updatedComponents = Optional.ofNullable(projectComponents.getComponents())
                .map(HashMap::new)
                .orElse(new HashMap<>());

        updatedComponents.put(componentId, ProjectComponent.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemIdWithoutBranch)
                .status(status)
                .catalogItemRef(branchReference)
                .componentUrl(componentUrl)
                .build());

        var updatedProjectComponents = ProjectComponents.builder()
                .components(updatedComponents)
                .build();

        log.trace("Updated project components: {}", updatedProjectComponents);

        return updatedProjectComponents;
    }

    @SneakyThrows
    public ProjectComponents updateExistingComponent(ProjectComponents projectComponents,
                                                     String componentId,
                                                     String catalogItemId,
                                                     Status status,
                                                     String componentUrl) {

        Map<String, ProjectComponent> components = projectComponents.getComponents();

        if (!components.containsKey(componentId)) {
            throw new InvalidComponentStateException("Component with id " + componentId + " does not exist");
        }

        var existing = components.get(componentId);
        var catalogItemIdWithoutBranch = getRepoPathFromCatalogItemId(catalogItemId);
        var branchReference = getBranchRefFromCatalogItemId(catalogItemId);

        if (!existing.getCatalogItemId().equals(catalogItemIdWithoutBranch)) {
            return projectComponents;
        }

        ProjectComponent updated = ProjectComponent.builder()
                .componentId(existing.getComponentId())
                .catalogItemId(existing.getCatalogItemId())
                .status(status)
                .catalogItemRef(branchReference)
                .componentUrl(StringUtils.isBlank(componentUrl) ? existing.getComponentUrl() : componentUrl)
                .build();

        Map<String, ProjectComponent> updatedMap = new HashMap<>(components);
        updatedMap.put(componentId, updated);

        return ProjectComponents.builder()
                .components(updatedMap)
                .build();
    }

    @SneakyThrows
    public ProjectComponents updatePartiallyExistingComponent(ProjectComponents projectComponents,
                                                              String componentId,
                                                              String catalogItemId,
                                                              Status status,
                                                              String componentUrl) {
        var projectComponent = projectComponents.getComponents().get(componentId);

        if (projectComponent != null) {
            Map<String, ProjectComponent> componentsMap = new HashMap<>();

            projectComponents.getComponents().forEach((key, value) -> {
                var branchReference = StringUtils.isBlank(catalogItemId) ? value.getCatalogItemRef() : getBranchRefFromCatalogItemId(catalogItemId);
                var nonEmptyComponentUrl = StringUtils.isBlank(componentUrl) ? value.getComponentUrl() : componentUrl;

                if (key.equals(componentId)) {
                    ProjectComponent pc = ProjectComponent.builder()
                            .componentId(value.getComponentId())
                            .catalogItemId(value.getCatalogItemId())
                            .status(status)
                            .catalogItemRef(branchReference)
                            .componentUrl(nonEmptyComponentUrl)
                            .build();
                    componentsMap.put(key, pc);
                } else {
                    componentsMap.put(key, value);
                }
            });

            return ProjectComponents.builder()
                    .components(componentsMap)
                    .build();
        } else {
            throw new InvalidComponentStateException("Component with id " + componentId + " does not exist");
        }
    }

    public ProjectComponents deleteComponent(ProjectComponents projectComponents, String componentId) {
        var updatedComponents = new HashMap<>(projectComponents.getComponents());

        updatedComponents.remove(componentId);

        return ProjectComponents.builder()
                .components(updatedComponents)
                .build();
    }

    protected String getRepoPathFromCatalogItemId(String catalogItemId) throws InvalidEntityException {
        return Optional.ofNullable(catalogItemId)
                .map(ProjectComponentsService::decodeId)
                .map(String::new)
                .map(id -> {
                    log.debug("Getting repo path from catalogItemId: {}", id);

                    var idWithoutBranch = id;

                    var indexOfRef = id.indexOf("?at=");
                    if (indexOfRef > 0) {
                        idWithoutBranch = id.substring(0, indexOfRef);
                    }

                    log.debug("Id without branch: {}", idWithoutBranch);
                    return idWithoutBranch;
                })
                .map(ProjectComponentsService::encodeId)
                .map(String::new)
                .orElseThrow(() -> new InvalidEntityException("Invalid Base64 encoded catalogItemId: " + catalogItemId));
    }

    private String getBranchRefFromCatalogItemId(String catalogItemId) throws InvalidEntityException {
        return Optional.ofNullable(catalogItemId)
                .map(ProjectComponentsService::decodeId)
                .map(String::new)
                .map(id -> {
                    log.debug("Getting branch from catalogItemId: {}", id);

                    var branchReference = id;

                    var indexOfRef = id.indexOf("?at=");
                    if (indexOfRef > 0) {
                        branchReference = id.substring(indexOfRef);
                    } else {
                        throw new InvalidCatalogItemIdStructureException("Invalid Base64 encoded catalogItemId: " + catalogItemId);
                    }

                    log.debug("Branch reference: {}", branchReference);
                    return branchReference;
                })
                .map(ProjectComponentsService::encodeId)
                .map(String::new)
                .orElseThrow(() -> new InvalidEntityException("Invalid Base64 encoded catalogItemId: " + catalogItemId));
    }

    private static byte[] encodeId(String id) {
        return Base64.getUrlEncoder().encode(id.getBytes());
    }

    private static byte[] decodeId(String id) {
        return Base64.getUrlDecoder().decode(id);
    }
}
