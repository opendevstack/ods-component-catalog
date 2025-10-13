package com.boehringer.componentcatalog.server.services.catalog;

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
    private String contributors;
    private String image;
    private Map<String, Set<String>> tags;
}
