package org.opendevstack.component_catalog.config;

import org.opendevstack.component_catalog.server.controllers.exceptions.BadConfigurationException;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.model.RestErrorMessage;
import org.opendevstack.component_catalog.server.services.exceptions.ComponentAlreadyExistsException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidCatalogItemIdStructureException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.UnableToDeserializeEntityException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    public static final String SENDING_PREDEFINED_HTTP_STATUS = "Expected exception reached controller level. Sending predefined HttpStatus.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleAllExceptions(Exception ex) {
        log.error("Unhandled exception", ex);
        
        return defaultErrResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<RestErrorMessage> handleRequestParamsExceptions(Exception ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorMessage> handleBadRequestException(BadRequestException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestEntityNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleEntityNotFoundException(RestEntityNotFoundException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidComponentStateException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidComponentStateException(InvalidComponentStateException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRestEntityException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidEntityException(InvalidRestEntityException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnableToDeserializeEntityException.class)
    public ResponseEntity<RestErrorMessage> handleUnableToDeserializeEntityException(UnableToDeserializeEntityException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ComponentAlreadyExistsException.class)
    public ResponseEntity<RestErrorMessage> handleComponentAlreadyExistsException(ComponentAlreadyExistsException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadConfigurationException.class)
    public ResponseEntity<RestErrorMessage> handleBadConfigurationException(BadConfigurationException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(InvalidCatalogItemIdStructureException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidCatalogItemIdStructureException(InvalidCatalogItemIdStructureException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);
        
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentNotValidException(MissingServletRequestParameterException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);

        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<RestErrorMessage> handleJsonProcessingException(JsonProcessingException ex) {
        log.trace(SENDING_PREDEFINED_HTTP_STATUS, ex);

        return defaultErrResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static ResponseEntity<RestErrorMessage> defaultErrResponse(Exception ex, HttpStatus errStatus) {
        // Explicitly setting MediaType.APPLICATION_JSON contentType is required, 
        // due to clients sending miscellaneous Accept headers on the request, 
        // but error messages are always in JSON format
        return ResponseEntity
                .status(errStatus)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(defaultErrMsg(ex));
    }

    private static RestErrorMessage defaultErrMsg(Exception ex) {
        return new RestErrorMessage(ex.getMessage());
    }
}
