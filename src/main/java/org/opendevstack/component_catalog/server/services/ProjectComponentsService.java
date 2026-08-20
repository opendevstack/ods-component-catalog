package org.opendevstack.component_catalog.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.*;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectComponentsService {

    public static final String REFS_HEADS_MASTER = "refs/heads/master";

    public ProjectComponents createNewComponent() {
        return new ProjectComponents();
    }

    @SneakyThrows
    public ProjectComponents addNewComponent(ProjectComponents projectComponents,
                                             ProjectComponentRequest request) {
        var catalogItemIdWithoutBranch = getRepoPathFromCatalogItemId(request.getCatalogItemId());
        var branchReference = getBranchRefFromCatalogItemId(request.getCatalogItemId());

        var updatedComponents = Optional.ofNullable(projectComponents.getComponents())
                .map(HashMap::new)
                .orElse(new HashMap<>());

        updatedComponents.put(request.getComponentId(), ProjectComponent.builder()
                .componentId(request.getComponentId())
                .catalogItemId(catalogItemIdWithoutBranch)
                .status(request.getStatus())
                .catalogItemRef(branchReference)
                .componentUrl(request.getComponentUrl())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .parameters(request.getParameters())
                .build());

        var updatedProjectComponents = ProjectComponents.builder()
                .components(updatedComponents)
                .build();

        log.trace("Updated project components: {}", updatedProjectComponents);

        return updatedProjectComponents;
    }

    @SneakyThrows
    public ProjectComponents updateExistingComponent(ProjectComponents projectComponents,
                                                     ProjectComponentRequest request) {

        Map<String, ProjectComponent> components = projectComponents.getComponents();

        if (!components.containsKey(request.getComponentId())) {
            throw new InvalidComponentStateException("Component with id " + request.getComponentId() + " does not exist");
        }

        var existing = components.get(request.getComponentId());
        var catalogItemIdWithoutBranch = getRepoPathFromCatalogItemId(request.getCatalogItemId());
        var branchReference = getBranchRefFromCatalogItemId(request.getCatalogItemId());

        if (!existing.getCatalogItemId().equals(catalogItemIdWithoutBranch)) {
            return projectComponents;
        }

        ProjectComponent updated = ProjectComponent.builder()
                .componentId(existing.getComponentId())
                .catalogItemId(existing.getCatalogItemId())
                .status(request.getStatus())
                .catalogItemRef(branchReference)
                .componentUrl(StringUtils.isBlank(request.getComponentUrl()) ? existing.getComponentUrl() : request.getComponentUrl())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .parameters(request.getParameters())
                .build();

        Map<String, ProjectComponent> updatedMap = new HashMap<>(components);
        updatedMap.put(request.getComponentId(), updated);

        return ProjectComponents.builder()
                .components(updatedMap)
                .build();
    }

    @SneakyThrows
    public ProjectComponents updatePartiallyExistingComponent(ProjectComponents projectComponents,
                                                              ProjectComponentRequest request) {

        validateComponentExists(projectComponents, request.getComponentId());

        Map<String, ProjectComponent> updatedMap = projectComponents.getComponents()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> updateComponentIfMatch(entry, request)
                ));

        return ProjectComponents.builder()
                .components(updatedMap)
                .build();
    }

    private void validateComponentExists(ProjectComponents projectComponents, String componentId) {
        if (!projectComponents.getComponents().containsKey(componentId)) {
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

    public String getRepoPathFromCatalogItemId(String catalogItemId) throws InvalidEntityException {
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

    private ProjectComponent updateComponentIfMatch(Map.Entry<String, ProjectComponent> entry,
                                                    ProjectComponentRequest request) {

        if (!entry.getKey().equals(request.getComponentId())) {
            return entry.getValue(); // leave unchanged
        }

        return ProjectComponent.builder()
                .componentId(entry.getValue().getComponentId())
                .catalogItemId(entry.getValue().getCatalogItemId())
                .status(request.getStatus())
                .catalogItemRef(resolveCatalogItemRef(entry.getValue(), request.getCatalogItemId()))
                .componentUrl(resolveComponentUrl(entry.getValue(), request.getComponentUrl()))
                .workflowJobId(resolveWorkflowJobId(entry.getValue(), request.getWorkflowJobId()))
                .deletionWorkflowJobId(resolveDeletionWorkflowJobId(entry.getValue(), request.getDeletionWorkflowJobId()))
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .parameters(resolveParameters(entry.getValue(), request.getParameters()))
                .build();
    }

    private String resolveCatalogItemRef(ProjectComponent value, String catalogItemId) {
        return StringUtils.isBlank(catalogItemId)
                ? value.getCatalogItemRef()
                : getBranchRefFromCatalogItemId(catalogItemId);
    }

    private String resolveComponentUrl(ProjectComponent value, String newUrl) {
        return StringUtils.isBlank(newUrl)
                ? value.getComponentUrl()
                : newUrl;
    }

    private List<Parameter> resolveParameters(ProjectComponent value, List<Parameter> params) {
        return (params == null || params.isEmpty())
                ? value.getParameters()
                : params;
    }

    private String resolveWorkflowJobId(ProjectComponent value, String newJobId) {
        return StringUtils.isBlank(newJobId)
                ? value.getWorkflowJobId()
                : newJobId;
    }

    private String resolveDeletionWorkflowJobId(ProjectComponent value, String newJobId) {
        return StringUtils.isBlank(newJobId)
                ? value.getDeletionWorkflowJobId()
                : newJobId;
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
                        log.debug("No branch reference found in catalogItemId: {}, returning master", id);

                        return REFS_HEADS_MASTER;
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
