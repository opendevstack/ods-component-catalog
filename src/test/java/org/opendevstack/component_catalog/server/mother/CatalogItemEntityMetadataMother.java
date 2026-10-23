package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityMetadata;

import java.util.Map;
import java.util.Set;

public class CatalogItemEntityMetadataMother {

    public static CatalogItemEntityMetadata of(String name,
                                               String shortDescription,
                                               Map<String, Set<String>> tags) {
        return CatalogItemEntityMetadata.builder()
                .name(name)
                .shortDescription(shortDescription)
                .description("README.md")
                .image("./logo.png")
                .tags(tags)
                .build();
    }

    public static CatalogItemEntityMetadata of(String name,
                                               String shortDescription,
                                               String description,
                                               String image,
                                               Map<String, Set<String>> tags) {
        return CatalogItemEntityMetadata.builder()
                .name(name)
                .shortDescription(shortDescription)
                .description(description)
                .image(image)
                .tags(tags)
                .build();
    }

    public static CatalogItemEntityMetadata of() {
        return of(
                "Appshell in Angular",
                "Quickstart template to boost the development of web applications on the EDP.",
                Map.of(
                        "Type", Set.of("appshell"),
                        "Technology", Set.of("angular"),
                        "GXP readiness", Set.of("non-gxp")
                ));
    }
}
