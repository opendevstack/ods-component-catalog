package org.opendevstack.component_catalog.server.services.filters;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.mappers.CatalogItemMother;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemRestriction;
import org.opendevstack.component_catalog.server.services.exceptions.CatalogItemsFilterException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectsFilterTest {

    private final ProjectsFilter projectsFilter = new ProjectsFilter();

    @Test
    void GivenCatalogItemWithoutRestrictions_WhenFilteringByProject_ThenReturnsTrue() {
        // given
        var item = CatalogItemMother.of("item-1");

        // when
        var result = projectsFilter.filter(item, List.of("PRJ-1"));

        // then
        assertThat(result).isTrue();
    }

    @Test
    void GivenCatalogItemWithMatchingProjectRestriction_WhenFilteringByProject_ThenReturnsTrue() {
        // given
        var item = catalogItemWithProjects(Set.of("PRJ-1", "PRJ-2"));

        // when
        var result = projectsFilter.filter(item, List.of("PRJ-1"));

        // then
        assertThat(result).isTrue();
    }

    @Test
    void GivenCatalogItemWithNonMatchingProjectRestriction_WhenFilteringByProject_ThenReturnsFalse() {
        // given
        var item = catalogItemWithProjects(Set.of("PRJ-2", "PRJ-3"));

        // when
        var result = projectsFilter.filter(item, List.of("PRJ-1"));

        // then
        assertThat(result).isFalse();
    }

    @Test
    void GivenCatalogItemWithRestrictionsAndNullProjectKey_WhenFilteringByProject_ThenReturnsFalse() {
        // given
        var item = catalogItemWithProjects(Set.of("PRJ-1"));

        // when
        var result = projectsFilter.filter(item, Collections.singletonList(null));

        // then
        assertThat(result).isFalse();
    }

    @Test
    void GivenNullCatalogItem_WhenFilteringByProject_ThenReturnsTrue() {
        // given
        CatalogItem item = null;

        // when
        var result = projectsFilter.filter(item, List.of("PRJ-1"));

        // then
        assertThat(result).isTrue();
    }

    @Test
    void GivenZeroProjectParams_WhenFilteringByProject_ThenThrowsCatalogItemsFilterException() {
        // given
        var item = CatalogItemMother.of("item-1");

        // when / then
        assertThatThrownBy(() -> projectsFilter.filter(item, List.of()))
                .isInstanceOf(CatalogItemsFilterException.class)
                .hasMessage("Only one project key is allowed");
    }

    @Test
    void GivenMoreThanOneProjectParam_WhenFilteringByProject_ThenThrowsCatalogItemsFilterException() {
        // given
        var item = CatalogItemMother.of("item-1");

        // when / then
        assertThatThrownBy(() -> projectsFilter.filter(item, List.of("PRJ-1", "PRJ-2")))
                .isInstanceOf(CatalogItemsFilterException.class)
                .hasMessage("Only one project key is allowed");
    }

    @Test
    void GivenNullParams_WhenFilteringByProject_ThenThrowsNullPointerException() {
        // given
        var item = CatalogItemMother.of("item-1");

        // when / then
        assertThatThrownBy(() -> projectsFilter.filter(item, null))
                .isInstanceOf(NullPointerException.class);
    }

    private CatalogItem catalogItemWithProjects(Set<String> projects) {
        var item = CatalogItemMother.of("item-1");
        var restrictions = new CatalogItemRestriction();
        restrictions.setProjects(projects);
        item.setRestrictions(restrictions);

        return item;
    }
}

