package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTechnologiesMapperTest {

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @InjectMocks
    private ProjectTechnologiesMapper projectTechnologiesMapper;

    @Test
    void givenProjectComponents_whenMap_thenFetchCatalogItemsAndReturnUniqueTechnologies()
            throws Exception {
        // given
        var catalogItemId = IdEncoderDecoder.idEncode(
                "projects/CATALOG/repos/java/raw/catalog-item.yaml?at=refs/heads/main");
        var otherCatalogItemId = IdEncoderDecoder.idEncode(
                "projects/CATALOG/repos/java/raw/catalog-item.yaml?at=refs/heads/release");
        var components = new LinkedHashMap<String, ProjectComponent>();
        components.put("C1", component(catalogItemId));
        components.put("C2", component(otherCatalogItemId));

        var catalogItem = new CatalogItem().slug("java").title("Java");
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class))).thenReturn(catalogItem);

        // when
        var result = projectTechnologiesMapper.asProjectTechnologies(ProjectComponents.builder()
                .components(components)
                .build());

        // then
        assertThat(result).containsExactly(ProjectTechnology.builder().slug("java").name("Java").build());

        var requestCaptor = ArgumentCaptor.forClass(CatalogRequestParams.class);
        verify(catalogItemsApiFacade, times(2)).fetchCatalogItem(requestCaptor.capture());
        assertThat(requestCaptor.getAllValues())
                .extracting(CatalogRequestParams::getCatalogItemId)
                .containsExactlyInAnyOrder(catalogItemId, otherCatalogItemId);
        assertThat(requestCaptor.getAllValues()).allSatisfy(params ->
                assertThat(params.isIgnoreVisibilityRestrictions()).isTrue());
    }

    @Test
    void givenCatalogItemIdParameter_whenMap_thenUseParameterValue() throws Exception {
        // given
        var catalogItemId = IdEncoderDecoder.idEncode("projects/CATALOG/repos/java/raw/catalog-item.yaml"
                + "?at=refs/heads/parameter");
        var component = component(IdEncoderDecoder.idEncode(
                        "projects/CATALOG/repos/java/raw/catalog-item.yaml?at=refs/heads/main"))
                .toBuilder()
                .parameters(List.of(Parameter.builder()
                        .name("catalog_item_id")
                        .values(List.of(catalogItemId))
                        .build()))
                .build();
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class)))
                .thenReturn(new CatalogItem().slug("java").title("Java"));

        // when
        projectTechnologiesMapper.asProjectTechnologies(projectComponentsWith(component));

        // then
        var requestCaptor = ArgumentCaptor.forClass(CatalogRequestParams.class);
        verify(catalogItemsApiFacade).fetchCatalogItem(requestCaptor.capture());
        assertThat(requestCaptor.getValue().getCatalogItemId()).isEqualTo(catalogItemId);
    }

    @Test
    void givenCatalogItemCannotBeFetched_whenMap_thenOmitTechnology() throws Exception {
        // given
        var catalogItemId = IdEncoderDecoder.idEncode(
                "projects/CATALOG/repos/java/raw/catalog-item.yaml?at=refs/heads/main");
        var projectComponents = projectComponentsWith(component(catalogItemId));
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class)))
                .thenThrow(new InvalidCatalogItemEntityException("invalid catalog item"));

        // when
        var result = projectTechnologiesMapper.asProjectTechnologies(projectComponents);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenCatalogItemIsNull_whenMap_thenOmitTechnology() throws Exception {
        // given
        var catalogItemId = IdEncoderDecoder.idEncode(
                "projects/CATALOG/repos/java/raw/catalog-item.yaml?at=refs/heads/main");
        var projectComponents = projectComponentsWith(component(catalogItemId));
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class))).thenReturn(null);

        // when
        var result = projectTechnologiesMapper.asProjectTechnologies(projectComponents);

        // then
        assertThat(result).isEmpty();
    }

    private ProjectComponents projectComponentsWith(ProjectComponent component) {
        return ProjectComponents.builder().components(Map.of("C1", component)).build();
    }

    private ProjectComponent component(String catalogItemId) {
        return ProjectComponent.builder()
                .catalogItemId(IdEncoderDecoder.idEncode("projects/CATALOG/repos/java/raw/catalog-item.yaml"))
                .catalogItemRef(IdEncoderDecoder.idEncode("refs/heads/main"))
                .parameters(List.of(Parameter.builder()
                        .name("catalog_item_id")
                        .values(List.of(catalogItemId))
                        .build()))
                .build();
    }
}
