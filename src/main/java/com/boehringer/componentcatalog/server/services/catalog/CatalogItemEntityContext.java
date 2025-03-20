package com.boehringer.componentcatalog.server.services.catalog;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Getter
public class CatalogItemEntityContext {

    private final String catalogItemId;
    private final BitbucketPathAt repoCatalogItemPathAt;
    private final CatalogItemEntity catalogItemEntity;
    private final String descriptionPath;
    private final String imagePath;
    private final String repoItemPath;
    private final String repoItemSrc;
    private final OffsetDateTime repoLastCommitDateUTC;
    private final List<String> contributors;

    @Builder
    public CatalogItemEntityContext(String catalogItemId,
                                    BitbucketPathAt repoCatalogItemPathAt,
                                    CatalogItemEntity catalogItemEntity,
                                    OffsetDateTime repoLastCommitDateUTC,
                                    List<String> contributors,
                                    BitbucketPathAt descriptionPath,
                                    BitbucketPathAt imagePath) {
        this.catalogItemId = catalogItemId;
        this.repoCatalogItemPathAt = repoCatalogItemPathAt;
        this.repoItemPath = repoCatalogItemPathAt.getPathAt();
        this.repoItemSrc = repoCatalogItemPathAt.getParent().getBrowseUrl();
        this.catalogItemEntity = catalogItemEntity;
        this.repoLastCommitDateUTC = repoLastCommitDateUTC;
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

    public Map<String, Set<String>> getRepoItemTags() {
        return this.catalogItemEntity.getMetadata().getTags();
    }
}
