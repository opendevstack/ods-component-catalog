package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import static org.assertj.core.api.Assertions.assertThat;

class ProvisioningStatusMapperTest {

    private final ProvisioningStatusMapper mapper = new ProvisioningStatusMapper();

    @Test
    void GivenStatusValues_whenAsStatusEnum_thenMappingIsCorrect() {
        // given / when
        ProvisioningStatus created = mapper.asProvisioningStatus(Status.CREATED);
        ProvisioningStatus unknown = mapper.asProvisioningStatus(Status.UNKNOWN);

        // then
        assertThat(created).isEqualTo(ProvisioningStatus.CREATED);
        assertThat(unknown).isEqualTo(ProvisioningStatus.UNKNOWN);
    }
}
