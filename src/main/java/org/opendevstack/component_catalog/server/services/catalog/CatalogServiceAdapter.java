package org.opendevstack.component_catalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_catalog.server.services.BitbucketService;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketInvalidEntityException;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.util.YamlSchemaValidator;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.util.Optional;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idDecode;
import static org.opendevstack.component_catalog.util.EitherUtils.maybeValue;
import static org.opendevstack.component_catalog.util.EitherUtils.maybeValueFrom;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogServiceAdapter {

    private final BitbucketService bitbucketService;

    public BitbucketPathAt bitbucketPathAtFromId(String id) throws InvalidIdException {
        var builder = bitbucketService.pathAtBuilder();

        return Optional.of(idDecode(id))
                .flatMap(maybeValueFrom(builder::pathAt))
                .flatMap(maybeValueFrom(BitbucketPathAt.BitbucketPathAtBuilder::build))
                .orElseThrow(() -> new InvalidIdException(id));
    }

    public Optional<CatalogEntity> getCatalogEntity(BitbucketPathAt catalogPathAt) throws InvalidCatalogEntityException {
        try {
            return getYamlEntity(catalogPathAt, CatalogEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog contents from Bitbucket for catalog: '{}'", catalogPathAt.getRawUrl(), e);
            throw new InvalidCatalogEntityException(catalogPathAt.getPathAt());
        }
    }

    public Optional<UserActionsEntity> getUserActionsEntity(BitbucketPathAt userActionsPathAt) throws InvalidCatalogEntityException {
        try {
            return getYamlEntity(userActionsPathAt, UserActionsEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing user actions contents from Bitbucket for user actions: '{}'", userActionsPathAt.getRawUrl(), e);
            throw new InvalidCatalogEntityException(userActionsPathAt.getPathAt());
        }
    }

    public Optional<CatalogsCollectionsEntity> getCatalogsCollectionEntity(BitbucketPathAt catalogsCollectionPathAt) throws InvalidCatalogEntityException {
        try {
            return getYamlEntity(catalogsCollectionPathAt, CatalogsCollectionsEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog collections contents from Bitbucket for catalog: '{}'", catalogsCollectionPathAt.getRawUrl(), e);
            throw new InvalidCatalogEntityException(catalogsCollectionPathAt.getPathAt());
        }
    }

    public Optional<CatalogItemEntity> getCatalogItemEntity(BitbucketPathAt catalogItemPathAt) throws InvalidCatalogItemEntityException {
        try {
            return getYamlEntity(catalogItemPathAt, CatalogItemEntity.class);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog item contents from Bitbucket for catalog item: '{}'", catalogItemPathAt.getRawUrl(), e);
            throw new InvalidCatalogItemEntityException(catalogItemPathAt.getPathAt());
        }
    }

    public <T> Optional<T> getYamlEntity(BitbucketPathAt catalogPathAt, Class<T> entityType) throws InvalidEntityException {
        // Either get a valid entity from a yaml file on a repo, or we throw an exception
        var maybeText = textContentsAtPath(catalogPathAt);

        if (maybeText.isEmpty()) {
            return Optional.empty();
        }

        return maybeText
                .map(yamlStr -> maybeValue(this::fromYaml, yamlStr, entityType))
                .filter(Optional::isPresent)
                .orElseThrow(() -> new InvalidEntityException("Invalid yaml file for entity, pathAt: " + catalogPathAt.getPathAt()));
    }

    public Optional<String> textContentsAtPath(BitbucketPathAt pathAt) throws InvalidEntityException {
        // Several options here:
        // 1. Not found: return Optional.empty()
        // 2. Found, but invalid file, e.g. an empty file: throw InvalidEntityException
        // 3. Unable to read file: let it throw BitbucketIOException under the hood, and consider this a server error
        try {
            return bitbucketService.getCachedTextFileContents(pathAt)
                    .map(Pair::getRight);
        } catch (BitbucketInvalidEntityException e) {
            throw new InvalidEntityException("Invalid text file, path: %s".formatted(pathAt.getPathAt()), e);
        }
    }

    public boolean contributingFileExists(String id) {
        BitbucketPathAt pathAt;
        try {
            pathAt = bitbucketPathAtFromId(id);
        } catch (InvalidIdException e) {
            return false;
        }
        return bitbucketService.doesContributingFileExists(pathAt);
    }

    private <T> T fromYaml(String yamlStr, Class<T> clazz) {
        var validationMessages = YamlSchemaValidator.validate(yamlStr, clazz);
        validationMessages.forEach(msg -> log.warn("YAML schema validation issue: {}", msg.getMessage()));

        try {
            var parser = new Yaml();
            parser.setBeanAccess(BeanAccess.FIELD);

            return parser.loadAs(yamlStr, clazz);
        } catch (Exception e) {
            log.error("Error while parsing YAML string: {}", yamlStr, e);
            throw e;
        }
    }
}
