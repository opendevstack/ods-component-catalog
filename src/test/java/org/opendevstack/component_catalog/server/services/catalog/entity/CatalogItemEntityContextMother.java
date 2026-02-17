package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictionsMother;

import java.time.OffsetDateTime;
import java.util.List;

public class CatalogItemEntityContextMother {

    public static CatalogItemEntityContext of(){
        return of("12345", CatalogItemEntityRestrictionsMother.of());
    }

    public static CatalogItemEntityContext of(CatalogItemEntityRestrictions restrictions){
        return of("12345", restrictions);
    }

    public static CatalogItemEntityContext of(String id, CatalogItemEntityRestrictions restrictions){
        var descriptionPath = BitbucketPathAtMother.of("description.md");
        var imagePath = BitbucketPathAtMother.of("logo.png");
        var catalogItemEntity = CatalogItemEntityMother.of(restrictions);
        var lastCommitDateUTC = OffsetDateTime.now();
        var contributors = List.of("contributor1", "contributor2");

        return of(
                id,
                catalogItemEntity,
                lastCommitDateUTC,
                contributors,
                descriptionPath,
                imagePath
        );

    }

    public static CatalogItemEntityContext of(String id,
                                              CatalogItemEntity catalogItemEntity,
                                              OffsetDateTime lastCommitDateUTC,
                                              List<String> contributors,
                                              BitbucketPathAt descriptionPath,
                                              BitbucketPathAt imagePath) {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of();

        return CatalogItemEntityContext.builder()
                .id(id)
                .repoCatalogItemPathAt(repoCatalogItemPathAt)
                .catalogItemEntity(catalogItemEntity)
                .lastCommitDateUTC(lastCommitDateUTC)
                .contributors(contributors)
                .descriptionPath(descriptionPath)
                .imagePath(imagePath)
                .build();
    }
}
