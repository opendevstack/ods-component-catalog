package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class CatalogItemEntityMetadata {
    private String name;
    private String shortDescription;
    private String description;
    private String contributors;
    private String image;
    private String type;
    private Map<String, Set<String>> tags;
}
