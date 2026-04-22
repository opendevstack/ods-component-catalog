package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.ProjectComponentParameter;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectComponentParameterMapper {

    public Optional<ProjectComponentParameter> mapToProjectComponentParameter(Parameter param) {
        var pcp = ProjectComponentParameter.builder()
                .name(param.getName())
                .values(param.getValues())
                .build();

        return Optional.of(pcp);
    }

}
