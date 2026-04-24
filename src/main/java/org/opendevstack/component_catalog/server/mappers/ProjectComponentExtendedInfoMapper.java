package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectComponentExtendedInfoMapper {

    private final ProjectComponentParameterMapper projectComponentParameterMapper;

    public Optional<ProjectComponentExtendedInfo> mapToProjectComponentExtendedInfo(ProjectComponent comp) throws ComponentNotFoundException {
        var pcps = Optional.ofNullable(comp.getParameters())
                .orElse(List.of())
                .stream()
                .map(p -> projectComponentParameterMapper.mapToProjectComponentParameter(p)
                        .orElseThrow(() -> new ComponentNotFoundException("Component with ID " + comp.getComponentId() + " not found.")))
                .toList();

        var pcei = ProjectComponentExtendedInfo.builder()
                .componentId(comp.getComponentId())
                .componentUrl(comp.getComponentUrl())
                .status(comp.getStatus().toString())
                .catalogItemId(comp.getCatalogItemId())
                .catalogItemRef(comp.getCatalogItemRef())
                .parameters(pcps)
                .build();

        return Optional.of(pcei);
    }

}
