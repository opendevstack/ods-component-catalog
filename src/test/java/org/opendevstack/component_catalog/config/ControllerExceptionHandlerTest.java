package org.opendevstack.component_catalog.config;

import org.junit.jupiter.api.Test;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {

    @Test
    void givenInvalidMethodArgument_whenHandled_thenReturnBadRequestWithBody() {
        // given
        var exception = mock(MethodArgumentNotValidException.class);
        when(exception.getFieldErrors()).thenReturn(List.of(new FieldError(
            "feedbackSubmissionRequest",
            "submitter",
            "testerexample.org",
            false,
            null,
            null,
            "must be a well-formed email address")));

        // when
        var response = new ControllerExceptionHandler().handleMethodArgumentNotValidException(exception);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage())
            .isEqualTo("submitter: must be a well-formed email address");
    }
}