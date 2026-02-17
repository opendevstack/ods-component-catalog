package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.model.CatalogItemRestriction;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CatalogItemRestrictionMapper {

    private CatalogItemRestrictionMapper(){
        // no sonar
    }

    public static CatalogItemRestriction asCatalogItemRestriction(CatalogItemEntityRestrictions catalogItemEntityRestrictions) {
        Set<String> projects = Optional.ofNullable(catalogItemEntityRestrictions)
                .map(CatalogItemEntityRestrictions::getProjects)
                .map(HashSet::new)
                .orElse((new HashSet<>()));

        return CatalogItemRestriction.builder()
                .projects(projects)
                .build();
    }
}
