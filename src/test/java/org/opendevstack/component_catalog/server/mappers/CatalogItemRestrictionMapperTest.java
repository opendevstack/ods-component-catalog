package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.model.CatalogItemRestriction;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogItemRestrictionMapperTest {

    @Test
    void giveNullInput_whenMapping_thenReturnRestrictionWithEmptyProjects() {
        // given
        CatalogItemEntityRestrictions input = null;

        // when
        CatalogItemRestriction result =
                CatalogItemRestrictionMapper.asCatalogItemRestriction(input);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProjects())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void giveInputWithNullProjects_whenMapping_thenReturnRestrictionWithEmptyProjects() {
        // given
        CatalogItemEntityRestrictions input = CatalogItemEntityRestrictions.builder()
                .projects(null)
                .build();

        // when
        CatalogItemRestriction result = CatalogItemRestrictionMapper.asCatalogItemRestriction(input);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProjects())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void giveInputWithProjects_whenMapping_thenReturnRestrictionWithCopiedProjects() {
        // given
        List<String> projects = Arrays.asList("A", "B");
        CatalogItemEntityRestrictions input = CatalogItemEntityRestrictions.builder()
                .projects(projects)
                .build();

        // when
        CatalogItemRestriction result =
                CatalogItemRestrictionMapper.asCatalogItemRestriction(input);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProjects())
                .containsExactlyInAnyOrder("A", "B")
                .isInstanceOf(Set.class);

        // verify defensive copy (mapper uses new HashSet<>(r.getProjects()))
        assertThat(result.getProjects()).isNotSameAs(new HashSet<>(projects));
    }

    @Test
    void giveRestrictions_whenMapping_thenNeverReturnNullProjects() {
        // given
        CatalogItemEntityRestrictions input = CatalogItemEntityRestrictions.builder()
                .projects(Collections.emptyList())
                .build();

        // when
        CatalogItemRestriction result =
                CatalogItemRestrictionMapper.asCatalogItemRestriction(input);

        // then
        assertThat(result.getProjects())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void giveMappedRestriction_whenModifyingOriginalProjects_thenMappedProjectsRemainUnchanged() {
        // given
        List<String> projects = Arrays.asList("A", "B");
        CatalogItemEntityRestrictions input = CatalogItemEntityRestrictions.builder()
                .projects(projects)
                .build();

        // when
        CatalogItemRestriction result =
                CatalogItemRestrictionMapper.asCatalogItemRestriction(input);

        // mutate original list
        projects.set(0, "X");

        // then
        assertThat(result.getProjects())
                .containsExactlyInAnyOrder("A", "B"); // unchanged
    }
}