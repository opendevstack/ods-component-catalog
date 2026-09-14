package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectComponentExtendedInfoMapper {

    private final ProjectComponentParameterMapper projectComponentParameterMapper;
    private final ProjectComponentsInfoMapper projectComponentsInfoMapper;

    public Optional<ProjectComponentExtendedInfo> mapToProjectComponentExtendedInfo(ProjectComponent comp,
                                                                                    String accessToken,
                                                                                    String projectKey,
                                                                                    List<String> userGroups) throws ComponentNotFoundException {
        var projectComponentParameters = Optional.ofNullable(comp.getParameters())
                .orElse(List.of())
                .stream()
                .map(p -> projectComponentParameterMapper.mapToProjectComponentParameter(p)
                        .orElseThrow(() -> new ComponentNotFoundException("Component with ID " + comp.getComponentId() + " not found.")))
                .toList();

        Optional<ProjectComponentInfo> compOpt;
        try {
            compOpt = projectComponentsInfoMapper.mapToProjectComponentInfo(comp, accessToken, projectKey, userGroups);
        } catch (InvalidIdException e) {
            log.error("Unable to map component: {}", comp, e);
            return Optional.empty();
        }

        var projectComponentExtendedInfo = compOpt.map(projectComponentInfo -> ProjectComponentExtendedInfo.builder()
                .componentId(projectComponentInfo.getComponentId())
                .componentUrl(projectComponentInfo.getComponentUrl())
                .status(projectComponentInfo.getStatus())
                .canBeDeleted(projectComponentInfo.getCanBeDeleted())
                .catalogItemId(comp.getCatalogItemId())
                .catalogItemRef(comp.getCatalogItemRef())
                .parameters(projectComponentParameters)
                .workflowJobId(comp.getWorkflowJobId())
                .deletionWorkflowJobId(comp.getDeletionWorkflowJobId())
                .build());

        log.debug("Mapped project component extended info: {}", projectComponentExtendedInfo);

        return projectComponentExtendedInfo;
    }

}
