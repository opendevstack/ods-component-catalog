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
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;

import java.util.LinkedHashMap;
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
        var catalogItemPath = "projects/CATALOG/repos/java/raw/catalog-item.yaml";
        var components = new LinkedHashMap<String, ProjectComponent>();
        components.put("C1", component(catalogItemPath, "refs/heads/main"));
        components.put("C2", component(catalogItemPath, "?at=refs/heads/main"));

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
                .containsOnly(IdEncoderDecoder.idEncode(catalogItemPath + "?at=refs/heads/main"));
        assertThat(requestCaptor.getAllValues()).allSatisfy(params ->
                assertThat(params.isIgnoreVisibilityRestrictions()).isTrue());
    }

    @Test
    void givenCatalogItemCannotBeFetched_whenMap_thenReturnEmptyTechnology() throws Exception {
        // given
        var projectComponents = projectComponentsWith(component("projects/CATALOG/repos/java/raw/catalog-item.yaml",
                "refs/heads/main"));
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class)))
                .thenThrow(new InvalidCatalogItemEntityException("invalid catalog item"));

        // when
        var result = projectTechnologiesMapper.asProjectTechnologies(projectComponents);

        // then
        assertThat(result).containsExactly(ProjectTechnology.builder().build());
    }

    @Test
    void givenCatalogItemIsNull_whenMap_thenReturnEmptyTechnology() throws Exception {
        // given
        var projectComponents = projectComponentsWith(component("projects/CATALOG/repos/java/raw/catalog-item.yaml",
                "refs/heads/main"));
        when(catalogItemsApiFacade.fetchCatalogItem(any(CatalogRequestParams.class))).thenReturn(null);

        // when
        var result = projectTechnologiesMapper.asProjectTechnologies(projectComponents);

        // then
        assertThat(result).containsExactly(ProjectTechnology.builder().build());
    }

    private ProjectComponents projectComponentsWith(ProjectComponent component) {
        return ProjectComponents.builder().components(Map.of("C1", component)).build();
    }

    private ProjectComponent component(String catalogItemPath, String catalogItemRef) {
        return ProjectComponent.builder()
                .catalogItemId(IdEncoderDecoder.idEncode(catalogItemPath))
                .catalogItemRef(IdEncoderDecoder.idEncode(catalogItemRef))
                .build();
    }
}
