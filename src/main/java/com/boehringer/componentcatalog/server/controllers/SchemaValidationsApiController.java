package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.api.SchemaValidationsApi;
import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.mappers.EntitiesMapper;
import com.boehringer.componentcatalog.server.model.ValidationMessage;
import com.boehringer.componentcatalog.server.model.ValidationResult;
import com.boehringer.componentcatalog.util.YamlSchemaValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class SchemaValidationsApiController implements SchemaValidationsApi {

    @Override
    public ResponseEntity<ValidationResult> validateCatalogSchema(String className, MultipartFile file) {
        try {
            var fileContent = new String(file.getBytes());
            var clazz = getClassByName(className);

            List<com.networknt.schema.ValidationMessage> validationMessages = validate(fileContent, clazz);

            if (validationMessages.isEmpty()) {
                return ResponseEntity.ok(new ValidationResult().valid(true));
            } else {
                List<ValidationMessage> mappedValidationMessages = validationMessages.stream()
                        .map(EntitiesMapper::asValidationMessage)
                        .toList();

                ValidationResult validationResult = ValidationResult.builder()
                        .valid(false)
                        .errors(mappedValidationMessages)
                        .build();

                return ResponseEntity.ok(validationResult);

            }
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    protected List<com.networknt.schema.ValidationMessage> validate(String yamlStr, Class<?> clazz) {
        return YamlSchemaValidator.validate(yamlStr, clazz);
    }

    private Class<?> getClassByName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", className, e);
            throw new BadRequestException("Invalid class name: " + className);
        }
    }
}
