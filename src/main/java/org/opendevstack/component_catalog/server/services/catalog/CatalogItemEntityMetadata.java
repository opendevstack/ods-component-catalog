package org.opendevstack.component_catalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntityMetadata {
    private String name;
    private String shortDescription;
    private String description;

    @Deprecated(forRemoval = true) // This is meant to be removed soon. It is here just for prevent error when loading the YAML files. It will not be used anymore.
    private String contributors;

    private String image;
    private Map<String, Set<String>> tags;
}
