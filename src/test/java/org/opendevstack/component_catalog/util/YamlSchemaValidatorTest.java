package org.opendevstack.component_catalog.util;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class YamlSchemaValidatorTest {

    private static final String VALIDATION_ENTITIES_PREFIX = "validation/entities/";

    @Test
    void givenACatalogItemEntity_whenValidatingYaml_thenNoValidationErrors() {
        // given
        var validCatalogItemEntityYaml = loadFileFromClasspath("valid-catalog-item-entity.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(validCatalogItemEntityYaml, CatalogItemEntity.class);

        // then
        assertThat(validationMessageList).isEmpty();
    }

    @Test
    void givenACatalogsCollectionsEntity_whenValidatingYaml_thenNoValidationErrors() {
        // given
        var validCatalogsCollectionsEntityYaml = loadFileFromClasspath("valid-catalogs-collections-entity.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(validCatalogsCollectionsEntityYaml, CatalogsCollectionsEntity.class);

        // then
        assertThat(validationMessageList).isEmpty();
    }

    @Test
    void givenACatalogEntity_whenValidatingYaml_thenNoValidationErrors() {
        // given
        var validCatalogEntityYaml = loadFileFromClasspath("valid-catalog-entity.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(validCatalogEntityYaml, CatalogEntity.class);

        // then
        assertThat(validationMessageList).isEmpty();
    }

    @Test
    void givenAUserActionsEntity_whenValidatingYaml_thenNoValidationErrors() {
        // given
        var validUserActionsEntityYaml = loadFileFromClasspath("valid-user-actions-entity.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(validUserActionsEntityYaml, UserActionsEntity.class);

        // then
        assertThat(validationMessageList).isEmpty();
    }

    @Test
    void givenAnInvalidYaml_whenValidatingYaml_thenReturnValidationErrors() {
        // given
        var validCatalogItemEntityYaml = loadFileFromClasspath("invalid-yaml.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(validCatalogItemEntityYaml, CatalogItemEntity.class);

        // then
        assertThat(validationMessageList).hasSize(1);
        assertThat(validationMessageList.get(0).getMessage()).startsWith("Error while parsing YAML");
    }

    @Test
    void givenAnInvalidCatalogItemEntity_whenValidatingYaml_thenReturnValidationErrors() {
        // given
        var invalidCatalogItemEntityYaml = loadFileFromClasspath("invalid-catalog-item-entity.yml");

        // when
        List<ValidationMessage> validationMessageList = YamlSchemaValidator.validate(invalidCatalogItemEntityYaml, CatalogItemEntity.class);

        // then
        assertThat(validationMessageList).hasSize(2);

        assertThat(validationMessageList).anySatisfy( msg -> assertThat(msg.getMessage()).startsWith("/metadata: required property 'tags' not found"));
        assertThat(validationMessageList).anySatisfy( msg -> assertThat(msg.getMessage()).startsWith("/spec/userActions/0: required property 'triggerMessage' not found"));
    }

    private static String loadFileFromClasspath(String fileName) {
        try {
            var filePath = Paths.get(ClassLoader.getSystemResource(VALIDATION_ENTITIES_PREFIX + fileName).toURI());

            return Files.readString(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file from classpath: " + fileName, e);
        }
    }
}
