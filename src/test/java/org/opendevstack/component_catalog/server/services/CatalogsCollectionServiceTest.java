package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogsCollectionServiceTest {

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @InjectMocks
    private CatalogsCollectionService catalogsCollectionService;

    @Test
    public void givenACatalogOfCatalogs_whenGetCatalogCollections_thenACatalogCollectionEntityReturned() throws InvalidIdException {
        // given
        var catalogOfCatalogsId = "catalogOfCatalogsId";

        ReflectionTestUtils.setField(catalogsCollectionService, "catalogOfCatalogsId", catalogOfCatalogsId);

        var catalogIdPathAt = mock(BitbucketPathAt.class);
        var repoCatalog = CatalogsCollectionsEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogOfCatalogsId)).thenReturn(catalogIdPathAt);
        when(catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogsCollectionsEntity.class)).thenReturn(Optional.of(repoCatalog));

        // when
        var catalogsCollection = catalogsCollectionService.getCatalogsCollection();

        // then
        assertThat(catalogsCollection).isPresent();
        assertThat(catalogsCollection.get()).isEqualTo(repoCatalog);
    }

}
