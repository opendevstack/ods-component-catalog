package org.opendevstack.component_catalog.server.services.provisioner;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProjectComponentUpdateRequest {
    private String componentId;
    private String catalogItemId;
    private Status status;
    private String componentUrl;
    private String workflowJobId;
    private String createdAt;
    private String updatedAt;
    private List<Parameter> parameters;
}
