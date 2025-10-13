package com.boehringer.componentcatalog.server.services.catalog;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@ToString
public class CatalogItemEntityContext {

    private final String id;
    private final BitbucketPathAt repoCatalogItemPathAt;
    public final CatalogItemEntity catalogItemEntity;
    private final String descriptionPath;
    private final String imagePath;
    private final String path;
    private final String itemSrc;
    private final OffsetDateTime lastCommitDateUTC;
    private final List<String> contributors;

    @Builder
    public CatalogItemEntityContext(String id,
                                    BitbucketPathAt repoCatalogItemPathAt,
                                    CatalogItemEntity catalogItemEntity,
                                    OffsetDateTime lastCommitDateUTC,
                                    List<String> contributors,
                                    BitbucketPathAt descriptionPath,
                                    BitbucketPathAt imagePath) {
        this.id = id;
        this.repoCatalogItemPathAt = repoCatalogItemPathAt;
        this.path = repoCatalogItemPathAt.getPathAt();
        this.itemSrc = repoCatalogItemPathAt.getParent().getBrowseUrl();
        this.catalogItemEntity = catalogItemEntity;
        this.lastCommitDateUTC = lastCommitDateUTC;
        this.descriptionPath = descriptionPath != null ? descriptionPath.getPathAt() : null;
        this.imagePath = imagePath != null ? imagePath.getPathAt() : null;

        this.contributors = Optional.ofNullable(contributors).orElse(List.of());
    }

    public String getName() {
        return this.catalogItemEntity.getMetadata().getName();
    }

    public String getDescription() {
        return this.catalogItemEntity.getMetadata().getDescription();
    }

    public String getShortDescription() {
        return this.catalogItemEntity.getMetadata().getShortDescription();
    }

    public Map<String, Set<String>> getTags() {
        return this.catalogItemEntity.getMetadata().getTags();
    }

}
