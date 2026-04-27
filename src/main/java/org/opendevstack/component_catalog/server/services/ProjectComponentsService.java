package org.opendevstack.component_catalog.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
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
                                             String componentId,
                                             String catalogItemId,
                                             Status status,
                                             String componentUrl,
                                             List<Parameter> parameters) {
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
                .parameters(parameters)
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
                                                     String componentUrl,
                                                     List<Parameter> parameters) {

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
                .parameters(parameters)
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
                                                              String componentUrl,
                                                              String workflowJobId,
                                                              List<Parameter> parameters) {

        validateComponentExists(projectComponents, componentId);

        Map<String, ProjectComponent> updatedMap = projectComponents.getComponents()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> updateComponentIfMatch(
                                entry,
                                componentId,
                                catalogItemId,
                                status,
                                componentUrl,
                                workflowJobId,
                                parameters)
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

    private ProjectComponent updateComponentIfMatch(Map.Entry<String, ProjectComponent> entry,
                                                    String componentId,
                                                    String catalogItemId,
                                                    Status status,
                                                    String componentUrl,
                                                    String workflowJobId,
                                                    List<Parameter> parameters) {

        if (!entry.getKey().equals(componentId)) {
            return entry.getValue(); // leave unchanged
        }

        return ProjectComponent.builder()
                .componentId(entry.getValue().getComponentId())
                .catalogItemId(entry.getValue().getCatalogItemId())
                .status(status)
                .catalogItemRef(resolveCatalogItemRef(entry.getValue(), catalogItemId))
                .componentUrl(resolveComponentUrl(entry.getValue(), componentUrl))
                .workflowJobId(workflowJobId)
                .parameters(resolveParameters(entry.getValue(), parameters))
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
