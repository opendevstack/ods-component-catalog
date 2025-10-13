package com.boehringer.componentcatalog.server.services.catalog;

import com.boehringer.componentcatalog.server.services.BitbucketService;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketInvalidEntityException;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidEntityException;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import com.boehringer.componentcatalog.util.YamlSchemaValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.util.Optional;

import static com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder.idDecode;
import static com.boehringer.componentcatalog.util.EitherUtils.maybeValue;
import static com.boehringer.componentcatalog.util.EitherUtils.maybeValueFrom;

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

    public <T> Optional<T> getCatalogEntity(BitbucketPathAt catalogPathAt, Class<T> clazz) throws InvalidCatalogEntityException {
        try {
            return getYamlEntity(catalogPathAt, clazz);
        } catch (InvalidEntityException e) {
            log.error("Error while parsing catalog contents from Bitbucket for catalog: '{}'", catalogPathAt.getRawUrl(), e);
            throw new InvalidCatalogEntityException(catalogPathAt.getPathAt());
        }
    }

    public <T> Optional<T> getYamlEntity(BitbucketPathAt catalogPathAt, Class<T> entityType) throws InvalidEntityException {
        // Either get a valid entity from a yaml file on a repo, or we throw an exception
        var maybeText = textContentsAtPath(catalogPathAt);

        if(maybeText.isEmpty()) {
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
            return bitbucketService.getTextFileContents(pathAt)
                    .map(Pair::getRight);
        } catch (BitbucketInvalidEntityException e) {
            throw new InvalidEntityException("Invalid text file, path: %s".formatted(pathAt.getPathAt()), e);
        }
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
