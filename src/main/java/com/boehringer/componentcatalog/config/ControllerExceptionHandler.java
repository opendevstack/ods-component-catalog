package com.boehringer.componentcatalog.config;

import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.controllers.exceptions.InvalidRestEntityException;
import com.boehringer.componentcatalog.server.controllers.exceptions.RestEntityNotFoundException;
import com.boehringer.componentcatalog.server.model.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleAllExceptions(Exception ex) {
        return defaultErrResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<RestErrorMessage> handleRequestParamsExceptions(Exception ex) {
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorMessage> handleBadRequestException(BadRequestException ex) {
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestEntityNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleEntityNotFoundException(RestEntityNotFoundException ex) {
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRestEntityException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidEntityException(InvalidRestEntityException ex) {
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
