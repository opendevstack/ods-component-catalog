package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogActivityFacade {
    private final ProjectComponentsFacade projectComponentsFacade;
    private final CatalogServiceAdapter catalogServiceAdapter;

    public List<CatalogActivity> getCatalogActivityById(String id) {
        var catalogEntity = catalogServiceAdapter.getCatalogEntity(id)
                .orElseThrow(() -> new ElementNotFoundException("Catalog entity not found for id: " + id));

        var catalogOwnerGroups = catalogEntity
                .map(CatalogEntity::getMetadata)
                .map(CatalogEntityMetadata::getOwners)
                .orElse(Collections.emptyList());

        if (userGroups.stream().noneMatch(catalogOwnerGroups::contains)) {
            log.debug("User groups {} do not match any catalog owner groups {} for catalog item {}", userGroups, catalogOwnerGroups, catalogRequestParams.getCatalogItemEntityContext().getId());

            return null;
        } else {
            return calculateComponentCount(catalogRequestParams);
        }

        // Implementation would go here
        return null;
    }
}
