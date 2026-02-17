package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CatalogsCollectionService {

    private final CatalogServiceAdapter catalogServiceAdapter;

    @Value("${catalog-collection.id}")
    private String catalogOfCatalogsId;

    public CatalogsCollectionService(CatalogServiceAdapter catalogServiceAdapter) {
        this.catalogServiceAdapter = catalogServiceAdapter;
    }

    public Optional<CatalogsCollectionsEntity> getCatalogsCollection() throws InvalidIdException {
        var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogOfCatalogsId);
        return catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogsCollectionsEntity.class);
    }

}
