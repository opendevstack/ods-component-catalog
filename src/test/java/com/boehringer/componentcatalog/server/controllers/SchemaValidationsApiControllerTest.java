package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.services.catalog.UserActionsEntity;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SchemaValidationsApiControllerTest {

    @Test
    void givenAValidYamlFile_whenValidateCatalogSchema_thenReturnsValidResponse() throws IOException {
        // given
        SchemaValidationsApiController controller = new MockSchemaValidationsApiController(Collections.emptyList());
        String className = UserActionsEntity.class.getName();
        String fileContent = "anyContent: here"; // This should be a valid YAML content for the class
        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(file.getBytes()).thenReturn(fileContent.getBytes());


        // when
        var result = controller.validateCatalogSchema(className, file);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getValid()).isTrue();
        assertThat(result.getBody().getErrors()).isEmpty();
    }

    @Test
    void givenAnInvalidYamlFile_whenValidateCatalogSchema_thenReturnsErrors() throws IOException {
        // given
        var validationMessages = List.of(
                new ValidationMessage.Builder().message("Invalid field").build(),
                new ValidationMessage.Builder().message("Another error").build()
        );

        SchemaValidationsApiController controller = new MockSchemaValidationsApiController(validationMessages);
        String className = UserActionsEntity.class.getName();
        String fileContent = "anyContent: here"; // This should be a valid YAML content for the class
        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(file.getBytes()).thenReturn(fileContent.getBytes());


        // when
        var result = controller.validateCatalogSchema(className, file);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getValid()).isFalse();
        assertThat(result.getBody().getErrors()).hasSize(2);
    }

    @Test
    void givenAnInvalidClassName_whenValidateCatalogSchema_thenErrorResponse() throws IOException {
        // given
        SchemaValidationsApiController controller = new MockSchemaValidationsApiController(Collections.emptyList());
        String className = "com.invalid.AnyClass"; // This class does not exist
        String fileContent = "anyContent: here"; // This should be a valid YAML content for the class
        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(file.getBytes()).thenReturn(fileContent.getBytes());


        // when
        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> controller.validateCatalogSchema(className, file));

        // then
        assertThat(badRequestException.getMessage()).isEqualTo("Invalid class name: " + className);
    }

    // This could be simplified if we use mocking frameworks like Mockito,
    // but YamlSchemaValidator is an static utility class, and is not mockable at the moment.
    private static class MockSchemaValidationsApiController extends SchemaValidationsApiController {
        private final List<ValidationMessage> expectedValidationMessages;

        MockSchemaValidationsApiController(List<ValidationMessage> expectedValidationMessages) {
            this.expectedValidationMessages = expectedValidationMessages;

        }

        @Override
        protected List<ValidationMessage> validate(String yamlStr, Class<?> clazz) {
            return expectedValidationMessages;
        }
    }
}
