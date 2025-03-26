package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCommit;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermittedUser.PermissionEnum;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketInvalidEntityException;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt.BitbucketPathAtBuilder;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidEntityException;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import com.boehringer.componentcatalog.server.services.catalog.InvalidCatalogEntityException;
import com.boehringer.componentcatalog.server.services.catalog.InvalidCatalogItemEntityException;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityContext;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityPermissionEnum;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityTarget;
import com.boehringer.componentcatalog.util.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.codeowners.CodeOwners;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.boehringer.componentcatalog.util.EitherUtils.*;
import static com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder.idDecode;
import static com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder.idEncode;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogEntitiesService {

    private final BitbucketService bitbucketService;

    public Optional<CatalogEntity> getCatalogEntity(String id) throws InvalidIdException, InvalidCatalogEntityException {
        var catalogIdPathAt = this.bitbucketPathAtFromId(id);

        try {
            return this.getCatalogEntity(catalogIdPathAt);
        } catch (InvalidCatalogEntityException e) {
            throw new InvalidIdException(id, e);
        }
    }

    public List<CatalogItemEntityContext> getCatalogItemsEntities(String catalogId) throws InvalidIdException, InvalidCatalogEntityException {
        // In order to return a valid list of RepoCatalogItemContexts, we need a valid RepoCatalog, that's
        // an existing file, a valid yaml file and also a valid RepoCatalog object
        var catalogIdPathAt = this.bitbucketPathAtFromId(catalogId);
        var repoCatalog = getCatalogEntity(catalogIdPathAt);

        if(repoCatalog.isEmpty()) {
            throw new InvalidCatalogEntityException(catalogId);
        }

        return this.allCatalogItemsEntitiesCtxs(repoCatalog.get());
    }

    public Optional<CatalogItemEntityContext> getCatalogItemEntity(String id) throws InvalidIdException, InvalidCatalogItemEntityException {
        return this.buildCatalogItemEntityCtx(bitbucketPathAtFromId(id));
    }

    private BitbucketPathAt bitbucketPathAtFromId(String id) throws InvalidIdException {
        var builder = bitbucketService.pathAtBuilder();

        return Optional.of(idDecode(id))
                .flatMap(maybeValueFrom(builder::pathAt))
                .flatMap(maybeValueFrom(BitbucketPathAtBuilder::build))
                .orElseThrow(() -> new InvalidIdException(id));
    }

    private Optional<CatalogEntity> getCatalogEntity(BitbucketPathAt catalogPathAt) throws InvalidCatalogEntityException {
        try {
            return this.getYamlEntity(catalogPathAt, CatalogEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog contents from Bitbucket for catalog: '{}'", catalogPathAt.getRawUrl(), e);
            throw new InvalidCatalogEntityException(catalogPathAt.getPathAt());
        }
    }

    private Optional<CatalogItemEntity> getCatalogItemEntity(BitbucketPathAt catalogItemPathAt) throws InvalidCatalogItemEntityException {
        try {
            return this.getYamlEntity(catalogItemPathAt, CatalogItemEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog item contents from Bitbucket for catalog item: '{}'", catalogItemPathAt.getRawUrl(), e);
            throw new InvalidCatalogItemEntityException(catalogItemPathAt.getPathAt());
        }
    }

    private <T> Optional<T> getYamlEntity(BitbucketPathAt catalogPathAt, Class<T> entityType) throws InvalidEntityException {
        // Either get a valid entity from a yaml file on a repo, or we throw an exception
        var maybeText = this.textContentsAtPath(catalogPathAt);

        if(maybeText.isEmpty()) {
            return Optional.empty();
        }

        return maybeText
                .map(yamlStr -> maybeValue(CatalogEntitiesService::fromYaml, yamlStr, entityType))
                .filter(Optional::isPresent)
                .orElseThrow(() -> new InvalidEntityException("Invalid yaml file for entity, pathAt: " + catalogPathAt.getPathAt()));
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
                .flatMap(maybeValueFrom(uncheckedFrom(this::textContentsAtPath)))
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
        var catalogPathAt = this.bitbucketPathAtFromId(catalogId);

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

    private Optional<String> textContentsAtPath(BitbucketPathAt pathAt) throws InvalidEntityException {
        // Several options here:
        // 1. Not found: return Optional.empty()
        // 2. Found, but invalid file, e.g. an empty file: throw InvalidEntityException
        // 3. Unable to read file: let it throw BitbucketIOException under the hood, and consider this a server error
        try {
            return this.bitbucketService.getTextFileContents(pathAt)
                    .map(Pair::getRight);
        } catch (BitbucketInvalidEntityException e) {
            throw new InvalidEntityException("Invalid text file, path: %s".formatted(pathAt.getPathAt()), e);
        }
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

    private static <T> T fromYaml(String yamlStr, Class<T> clazz) {
        return new Yaml().loadAs(yamlStr, clazz);
    }

}
