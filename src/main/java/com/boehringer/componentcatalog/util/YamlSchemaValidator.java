package com.boehringer.componentcatalog.util;

import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.serialization.JsonNodeReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.networknt.schema.InputFormat.YAML;
import static com.networknt.schema.SpecVersion.VersionFlag.V202012;

@Slf4j
public class YamlSchemaValidator {

    private YamlSchemaValidator() {
        // Utility class, no instantiation needed
    }

    private static boolean initialized;

    private static Map<String, String> schemasMap;

    static {
        try {
            schemasMap = Map.of(
                    "CatalogEntity", loadFileFromClasspath("schemas/schema-CatalogEntity.yaml"),
                    "CatalogItemEntity", loadFileFromClasspath("schemas/schema-CatalogItemEntity.yaml"),
                    "CatalogsCollectionsEntity", loadFileFromClasspath("schemas/schema-CatalogCollectionsEntity.yaml"),
                    "UserActionsEntity", loadFileFromClasspath("schemas/schema-UserActionsEntity.yaml")
            );

            initialized = true;
        } catch (Exception e) {
            initialized = false;
            log.error("Unable to initialize {}: ", YamlSchemaValidator.class.getName(), e);
        }
    }

    public static List<ValidationMessage> validate(String yamlStr, Class<?> clazz) {
        if(!initialized) {
            log.warn("{} is not initialized. No validation will be performed.", YamlSchemaValidator.class.getName());
            return List.of();
        }

        var yamlSchema = schemasMap.get(clazz.getSimpleName());

        if (yamlSchema == null) {
            log.warn("No schema found for class: {}. No validation will be performed.", clazz.getSimpleName());
            return List.of(); // assume no required validation for this entity class
        }

       return validate(yamlStr, yamlSchema);
    }

    public static List<ValidationMessage> validate(String yamlStr, String yamlSchema) {
        if(!initialized) {
            log.warn("{} is not initialized. No validation will be performed.", YamlSchemaValidator.class.getName());
            return List.of();
        }

        var factory = JsonSchemaFactory.getInstance(
                V202012,
                builder ->
                        builder.jsonNodeReader(JsonNodeReader.builder()
                                .locationAware()
                                .build()
                        )
        );

        var config = SchemaValidatorsConfig.builder().build();
        var schema = factory.getSchema(yamlSchema, YAML, config);

        Set<ValidationMessage> messages = new HashSet<>();

        try {
            var validationMessages = schema.validate(
                    yamlStr,
                    YAML,
                    executionContext -> executionContext
                            .getExecutionConfig()
                            .setFormatAssertionsEnabled(true));

            messages.addAll(validationMessages);
        } catch (IllegalArgumentException e) {
            log.warn("Error parsing Yaml: {}", e.getMessage());

            var message = e.getMessage();

            if (e.getCause() != null) {
                message = e.getCause().getMessage();
            }

            messages.add(ValidationMessage.builder()
                    .message("Error while parsing YAML: " + message)
                    .build());
        }

        return messages.stream().toList();
    }

    @SneakyThrows
    private static String loadFileFromClasspath(String fileName) {
        var filePath = Paths.get(ClassLoader.getSystemResource(fileName).toURI());

        return Files.readString(filePath);
    }

}
