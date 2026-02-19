package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.client.bitbucket.v89.model.RestCommit;
import org.opendevstack.component_catalog.client.bitbucket.v89.model.RestPermittedUser.PermissionEnum;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.codeowners.CodeOwnersContainer;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.util.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;
import static org.opendevstack.component_catalog.util.EitherUtils.either;
import static org.opendevstack.component_catalog.util.EitherUtils.maybeValueFrom;
import static org.opendevstack.component_catalog.util.EitherUtils.uncheckedFrom;
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

    public List<CatalogItemEntityContext> getCatalogItemsEntities(String catalogId)
            throws InvalidIdException, InvalidCatalogEntityException {
        // In order to return a valid list of RepoCatalogItemContexts, we need a valid RepoCatalog, that's
        // an existing file, a valid yaml file and also a valid RepoCatalog object
        var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogId);
        var catalogEntity = catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class);

        if(catalogEntity.isEmpty()) {
            throw new InvalidCatalogEntityException(catalogId);
        }

        return this.allCatalogItemsEntitiesCtxs(catalogEntity.get());
    }

    public Optional<CatalogItemEntityContext> getCatalogItemEntity(String id)
            throws InvalidIdException, InvalidCatalogItemEntityException {
        return this.buildCatalogItemEntityCtx(catalogServiceAdapter.bitbucketPathAtFromId(id));
    }

    public Set<CatalogEntityPermissionEnum> catalogPrincipalPermissions(String catalogId, String principalName) throws InvalidIdException {
        var catalogPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogId);

        // We will assume that the user has no permissions if either the principalName is null
        // or we can't retrieve the permissions from Bitbucket
        return Optional.ofNullable(principalName)
                .flatMap(username -> maybeValueFrom(this.bitbucketService::searchRepoUserPermissions).apply(catalogPathAt, username))
                .map(permissions -> permissions.stream()
                        .map(PermissionEnum::getValue)
                        .map(CatalogEntityPermissionEnum::fromValue)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    protected Optional<List<String>> buildCodeowners(CatalogItemEntityMetadata metadata, BitbucketPathAt pathAtParent) {
        return ofNullable(metadata.getContributors())
                .map(pathAtParent.copy()::appendSubPath)
                .flatMap(maybeValueFrom(uncheckedFrom(catalogServiceAdapter::textContentsAtPath)))
                .flatMap(txt -> txt)
                .map(CodeownersCommentStripper::strip)
                .flatMap(maybeValueFrom(this::parseContributors));
    }

    private Optional<CatalogItemEntity> getCatalogItemEntity(BitbucketPathAt catalogItemPathAt)
            throws InvalidCatalogItemEntityException {
        try {
            return catalogServiceAdapter.getYamlEntity(catalogItemPathAt, CatalogItemEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog item contents from Bitbucket for catalog item: '{}'", catalogItemPathAt.getRawUrl(), e);
            throw new InvalidCatalogItemEntityException(catalogItemPathAt.getPathAt());
        }
    }

    private List<CatalogItemEntityContext> allCatalogItemsEntitiesCtxs(CatalogEntity catalogEntity) {
        var catalogEntityTargets = catalogEntity.getMetadata().getSpec().getTargets();

        // For this particular case, we will ignore any errored CatalogItems
        return Stream.of(catalogEntityTargets)
                .map(target -> either(this::buildCatalogItemEntityCtx, target))
                .filter(Either::isOk)
                .map(Either::getValue)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<CatalogItemEntityContext> buildCatalogItemEntityCtx(CatalogEntityTarget catalogEntityTarget)
            throws InvalidCatalogEntityException, InvalidCatalogItemEntityException {
        // Examples:
        // https://my-bitbucket.com/projects/MYPROJECT/repos/catalogitem-repo/raw/CatalogItem.yaml?at=refs%2Fheads%2Fmaster
        // https://my-bitbucket.com/projects/MYPROJECT/repos/catalogitem-repo/raw/first-package/PackageCatalogItem.yaml?at=refs%2Fheads%2Fmaster
        var catalogEntityItemUrl = catalogEntityTarget.getUrl();

        try {
            var catalogItemPathAt = bitbucketService.pathAtBuilder()
                    .rawUrl(catalogEntityItemUrl)
                    .build();
            return buildCatalogItemEntityCtx(catalogItemPathAt);
        } catch (Exception e) {
            var errMsg = "Error while parsing catalog item URL: %s".formatted(catalogEntityItemUrl);
            log.error(errMsg, e);
            throw new InvalidCatalogItemEntityException(errMsg);
        }
    }

    private Optional<CatalogItemEntityContext> buildCatalogItemEntityCtx(BitbucketPathAt catalogItemPathAt)
            throws InvalidCatalogItemEntityException {

        var catalogItemId = idEncode(catalogItemPathAt.getPathAt());
        var maybeCatalogItemEntity = getCatalogItemEntity(catalogItemPathAt);

        if(maybeCatalogItemEntity.isEmpty()) {
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
        var catalogItemEntity = maybeCatalogItemEntity.get();
        var metadata = catalogItemEntity.getMetadata();

        var pathAtParent = catalogItemPathAt.getParent();

        // Get the code owners, if possible - any error will be considered as "no code owners"
        var codeOwners = buildCodeowners(metadata, pathAtParent);

        var descriptionPathAt = ofNullable(metadata.getDescription())
                .map(pathAtParent.copy()::appendSubPath);

        var imagePathAt = ofNullable(metadata.getImage())
                .map(pathAtParent.copy()::appendSubPath);

        // Set mandatory fields
        var catalogItemCtxBuilder = CatalogItemEntityContext.builder()
                .id(catalogItemId)
                .catalogItemEntity(catalogItemEntity)
                .repoCatalogItemPathAt(catalogItemPathAt)
                .lastCommitDateUTC(lastCommitDate);

        // Set optional fields
        codeOwners.ifPresent(catalogItemCtxBuilder::contributors);
        descriptionPathAt.ifPresent(catalogItemCtxBuilder::descriptionPath);
        imagePathAt.ifPresent(catalogItemCtxBuilder::imagePath);

        return Optional.of(catalogItemCtxBuilder.build());
    }

    private List<String> parseContributors(String contributorsStr) {
        try {
            if (contributorsStr == null) {
                return List.of();
            }

            CodeOwnersContainer codeOwners = new CodeOwnersContainer(contributorsStr);

            return codeOwners.getCodeOwners();

        } catch (RuntimeException e) {
            log.error("Error while parsing contributors from codeowners file: {}", contributorsStr, e);
            throw e;
        }
    }

}
