package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogActivityMapperTest {

    private final CatalogActivityMapper mapper = new CatalogActivityMapper();

    @Test
    void GivenProjectComponentWithValidCreatedAt_whenMapToCatalogActivity_ThenCreatedAtIsParsed() {
        // given
        ProjectComponent pc = ProjectComponent.builder()
                .componentId("comp-1")
                .createdAt("1707043200000")
                .status(Status.CREATED)
                .build();

        // when
        CatalogActivity result = mapper.asCatalogActivity("PRJ", "proj/repo", pc);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getComponentId()).isEqualTo("comp-1");
        assertThat(result.getProjectKey()).isEqualTo("PRJ");
        assertThat(result.getCatalogItemSlug()).isEqualTo("proj/repo");
        assertThat(result.getCreatedAt()).isEqualByComparingTo(new BigDecimal("1707043200000"));
        assertThat(result.getStatus()).isEqualTo(CatalogActivity.StatusEnum.CREATED);
    }

    @Test
    void GivenProjectComponentWithInvalidCreatedAt_whenMapToCatalogActivity_ThenCreatedAtIsNull() {
        // given
        ProjectComponent pc = ProjectComponent.builder()
                .componentId("comp-2")
                .createdAt("not-a-number")
                .status(Status.CREATED)
                .build();

        // when
        CatalogActivity result = mapper.asCatalogActivity("PRJ", "proj/repo", pc);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCreatedAt()).isNull();
    }

    @Test
    void GivenProjectComponentWithNullCreatedAt_whenMapToCatalogActivity_ThenCreatedAtIsNull() {
        // given
        ProjectComponent pc = ProjectComponent.builder()
                .componentId("comp-3")
                .createdAt(null)
                .status(Status.CREATED)
                .build();

        // when
        CatalogActivity result = mapper.asCatalogActivity("PRJ", "proj/repo", pc);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCreatedAt()).isNull();
    }

    @Test
    void GivenStatusValues_whenAsStatusEnum_thenMappingIsCorrect() {
        // given / when
        CatalogActivity.StatusEnum created = mapper.asStatusEnum(Status.CREATED);
        CatalogActivity.StatusEnum unknown = mapper.asStatusEnum(Status.UNKNOWN);

        // then
        assertThat(created).isEqualTo(CatalogActivity.StatusEnum.CREATED);
        assertThat(unknown).isEqualTo(CatalogActivity.StatusEnum.UNKNOWN);
    }
}

