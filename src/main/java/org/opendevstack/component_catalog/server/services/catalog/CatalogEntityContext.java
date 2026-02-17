package org.opendevstack.component_catalog.server.services.catalog;

import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Getter
@ToString
public class CatalogEntityContext {
    private CatalogEntity catalogEntity;

    private String communityPagePath;

    @Builder
    public CatalogEntityContext(CatalogEntity catalogEntity, Optional<BitbucketPathAt> communityPagePathAt) {
        this.catalogEntity = catalogEntity;
        this.communityPagePath = communityPagePathAt.map(BitbucketPathAt::getPathAt).orElse(null);
    }

    public String getName() {
        return catalogEntity.getMetadata().getName();
    }

    public String getDescription() {
        return catalogEntity.getMetadata().getDescription();
    }

    public List<CatalogEntityLink> getLinks() {
        return List.of(catalogEntity.getMetadata().getSpec().getLinks());
    }

    public List<String> getTags() {
        return catalogEntity.getMetadata().getSpec().getTags();
    }

    public List<CatalogEntityTarget> getTargets() {
        return List.of(catalogEntity.getMetadata().getSpec().getTargets());
    }
}
