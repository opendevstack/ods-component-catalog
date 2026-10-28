package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmission;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest;
import org.opendevstack.component_catalog.config.ControllerExceptionHandler;
import org.opendevstack.component_catalog.server.services.FeedbackApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackApiControllerTest {

    @Mock
    private FeedbackApiService feedbackApiService;

    private FeedbackApiController feedbackApiController;

    @BeforeEach
    void setUp() {
        feedbackApiController = new FeedbackApiController(feedbackApiService, new ObjectMapper());
    }

    @Test
    void givenFeedbackRequest_whenRegisterSubmission_thenForwardRequest() {
        // given
        var request = new org.opendevstack.component_catalog.server.model.FeedbackSubmissionRequest(
                "submitter@example.com", List.of(Map.of("id", "rating")), Map.of("rating", 5));
        when(feedbackApiService.registerFeedbackSubmission(any(FeedbackSubmissionRequest.class)))
            .thenReturn(ResponseEntity.<FeedbackSubmission>status(HttpStatus.CREATED).build());

        // when
        var response = feedbackApiController.registerFeedbackSubmission(request);

        // then
        var requestCaptor = ArgumentCaptor.forClass(FeedbackSubmissionRequest.class);
        verify(feedbackApiService).registerFeedbackSubmission(requestCaptor.capture());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(requestCaptor.getValue().getSubmitter()).isEqualTo("submitter@example.com");
        assertThat(requestCaptor.getValue().getAnswers()).containsEntry("rating", 5);
    }

    @Test
    void givenUpstreamError_whenHandled_thenPreserveStatusAndBody() {
        // given
        var body = "{\"message\":\"upstream error\"}".getBytes(StandardCharsets.UTF_8);
        var upstreamHeaders = new HttpHeaders();
        upstreamHeaders.setContentType(MediaType.APPLICATION_JSON);
        var exception = HttpClientErrorException.create(
                HttpStatus.BAD_REQUEST, "Bad Request", upstreamHeaders, body, StandardCharsets.UTF_8);

        // when
        var response = new ControllerExceptionHandler().handleRestClientResponseException(exception);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsExactly(body);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }
}