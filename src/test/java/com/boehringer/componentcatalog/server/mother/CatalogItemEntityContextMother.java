package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityContext;

import java.time.OffsetDateTime;
import java.util.List;

public class CatalogItemEntityContextMother {

    public static CatalogItemEntityContext of(){
        return of("12345");
    }

    public static CatalogItemEntityContext of(String id){
        var descriptionPath = BitbucketPathAtMother.of("description.md");
        var imagePath = BitbucketPathAtMother.of("logo.png");
        var catalogItemEntity = CatalogItemEntityMother.of();
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
