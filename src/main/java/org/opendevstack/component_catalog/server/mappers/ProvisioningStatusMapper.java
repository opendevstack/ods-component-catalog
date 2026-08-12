package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProvisioningStatusMapper {

    public ProvisioningStatus asProvisioningStatus(Status status) {
        return ProvisioningStatus.valueOf(status.name());
    }
}
