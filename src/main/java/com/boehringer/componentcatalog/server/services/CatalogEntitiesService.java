package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCommit;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermittedUser.PermissionEnum;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.catalog.*;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidEntityException;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import com.boehringer.componentcatalog.util.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.codeowners.CodeOwners;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder.idEncode;
import static com.boehringer.componentcatalog.util.EitherUtils.*;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogEntitiesService {

    private final BitbucketService bitbucketService;

    private final CatalogServiceAdapter catalogServiceAdapter;

    public Optional<CatalogEntity> getCatalogEntity(String id) throws InvalidIdException, InvalidCatalogEntityException {
        var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(id);

        try {
            return catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class);
        } catch (InvalidCatalogEntityException e) {
            throw new InvalidIdException(id, e);
        }
    }

    public Optional<CatalogEntityContext> getCatalogEntityContext(String id) throws InvalidIdException, InvalidCatalogEntityException {
        var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(id);

        var catalogEntity = catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class);

        return catalogEntity.map(entity -> {
            var communityPagePathAt = ofNullable(entity.getMetadata().getCommunityPage())
                    .map(catalogIdPathAt.getParent().copy()::appendSubPath);

            return CatalogEntityContext.builder()
                    .catalogEntity(entity)
                    .communityPagePathAt(communityPagePathAt)
                    .build();
        });
    }

    public List<CatalogItemEntityContext> getCatalogItemsEntities(String catalogId) throws InvalidIdException, InvalidCatalogEntityException {
        // In order to return a valid list of RepoCatalogItemContexts, we need a valid RepoCatalog, that's
        // an existing file, a valid yaml file and also a valid RepoCatalog object
        var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogId);
        var repoCatalog = catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class);

        if(repoCatalog.isEmpty()) {
            throw new InvalidCatalogEntityException(catalogId);
        }

        return this.allCatalogItemsEntitiesCtxs(repoCatalog.get());
    }

    public Optional<CatalogItemEntityContext> getCatalogItemEntity(String id) throws InvalidIdException, InvalidCatalogItemEntityException {
        return this.buildCatalogItemEntityCtx(catalogServiceAdapter.bitbucketPathAtFromId(id));
    }

    private Optional<CatalogItemEntity> getCatalogItemEntity(BitbucketPathAt catalogItemPathAt) throws InvalidCatalogItemEntityException {
        try {
            return catalogServiceAdapter.getYamlEntity(catalogItemPathAt, CatalogItemEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog item contents from Bitbucket for catalog item: '{}'", catalogItemPathAt.getRawUrl(), e);
            throw new InvalidCatalogItemEntityException(catalogItemPathAt.getPathAt());
        }
    }

    private List<CatalogItemEntityContext> allCatalogItemsEntitiesCtxs(CatalogEntity catalog) {
        var repoTargets = catalog.getMetadata().getSpec().getTargets();

        // For this particular case, we will ignore any errored CatalogItems
        return List.of(repoTargets).parallelStream() // TODO review this, it doesn't yield much performance improvements //NOSONAR
                .map(target -> either(this::buildCatalogItemEntityCtx, target))
                .filter(Either::isOk)
                .map(Either::getValue)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<CatalogItemEntityContext> buildCatalogItemEntityCtx(CatalogEntityTarget repoTarget)
            throws InvalidCatalogEntityException, InvalidCatalogItemEntityException {
        // Examples:
        // https://my-bitbucket.boehringer.com/projects/MYPROJECT/repos/catalogitem-repo/raw/CatalogItem.yaml?at=refs%2Fheads%2Fmaster
        // https://my-bitbucket.boehringer.com/projects/MYPROJECT/repos/catalogitem-repo/raw/first-package/PackageCatalogItem.yaml?at=refs%2Fheads%2Fmaster
        var catalogItemUrl = repoTarget.getUrl();

        try {
            var catalogItemPathAt = bitbucketService.pathAtBuilder()
                    .rawUrl(catalogItemUrl)
                    .build();
            return buildCatalogItemEntityCtx(catalogItemPathAt);
        } catch (Exception e) {
            var errMsg = "Error while parsing catalog item URL: %s".formatted(catalogItemUrl);
            log.error(errMsg, e);
            throw new InvalidCatalogItemEntityException(errMsg);
        }
    }

    private Optional<CatalogItemEntityContext> buildCatalogItemEntityCtx(BitbucketPathAt catalogItemPathAt)
            throws InvalidCatalogItemEntityException {

        var catalogItemId = idEncode(catalogItemPathAt.getPathAt());
        var maybeCatalogItem = getCatalogItemEntity(catalogItemPathAt);

        if(maybeCatalogItem.isEmpty()) {
            return Optional.empty();
        }

        var rootLastCommit = this.bitbucketService.getLastCommit(catalogItemPathAt.getProjectKey(),
                catalogItemPathAt.getRepoSlug(),
                catalogItemPathAt.getAt());

        var lastTimestamp = rootLastCommit.map(RestCommit::getCommitterTimestamp)
                .or(() -> rootLastCommit.map(RestCommit::getAuthorTimestamp));

        // This should be the last commit date in the repo, unless it was not possible to retrieve it
        var lastCommitDate = lastTimestamp
                .map(Instant::ofEpochMilli)
                .map(inst -> OffsetDateTime.ofInstant(inst, ZoneId.of("UTC")))
                .orElse(null);

        // Get UTC string representation of the last commit date
        var catalogItem = maybeCatalogItem.get();
        var metadata = catalogItem.getMetadata();

        var pathAtParent = catalogItemPathAt.getParent();

        // Get the code owners, if possible - any error will be considered as "no code owners"
        var codeOwners = ofNullable(metadata.getContributors())
                .map(pathAtParent.copy()::appendSubPath)
                .flatMap(maybeValueFrom(uncheckedFrom(catalogServiceAdapter::textContentsAtPath)))
                .flatMap(txt -> txt)
                .flatMap(maybeValueFrom(this::parseContributors));

        var descriptionPathAt = ofNullable(metadata.getDescription())
                .map(pathAtParent.copy()::appendSubPath);

        var imagePathAt = ofNullable(metadata.getImage())
                .map(pathAtParent.copy()::appendSubPath);

        // Set mandatory fields
        var catalogItemCtxBuilder = CatalogItemEntityContext.builder()
                .catalogItemId(catalogItemId)
                .catalogItemEntity(catalogItem)
                .repoCatalogItemPathAt(catalogItemPathAt)
                .repoLastCommitDateUTC(lastCommitDate);

        // Set optional fields
        codeOwners.ifPresent(catalogItemCtxBuilder::contributors);
        descriptionPathAt.ifPresent(catalogItemCtxBuilder::descriptionPath);
        imagePathAt.ifPresent(catalogItemCtxBuilder::imagePath);

        return Optional.of(catalogItemCtxBuilder.build());
    }

    public Set<CatalogEntityPermissionEnum> catalogPrincipalPermissions(String catalogId, String principalName) throws InvalidIdException {
        var catalogPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogId);

        // We will assume that the user has no permissions if either the principalName is null
        // or we can't retrieve the permissions from Bitbucket
        return ofNullable(principalName)
                .flatMap(username -> maybeValueFrom(this.bitbucketService::searchRepoUserPermissions).apply(catalogPathAt, username))
                .map(permissions -> StreamEx.of(permissions)
                        .map(PermissionEnum::getValue)
                        .map(CatalogEntityPermissionEnum::fromValue)
                        .toSet())
                .orElse(Set.of());
    }

    private List<String> parseContributors(String contributorsStr) {
        // TODO unit test this method, just in case the codeowners file contains errors
        try {
            return StreamEx.ofNullable(contributorsStr)
                    .map(CodeOwners::new)
                    .flatCollection(CodeOwners::getAllDefinedSections)
                    .flatCollection(CodeOwners.Section::getApprovalRules)
                    .flatCollection(CodeOwners.ApprovalRule::getApprovers)
                    .distinct()
                    .toList();
        } catch (RuntimeException e) {
            log.error("Error while parsing contributors from codeowners file: {}", contributorsStr, e);
            throw e;
        }
    }

}
