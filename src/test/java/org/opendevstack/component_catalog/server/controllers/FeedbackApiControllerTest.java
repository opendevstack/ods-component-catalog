package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmission;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest;
import org.opendevstack.component_catalog.config.ControllerExceptionHandler;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.services.ApiClientsBuilder;
import org.opendevstack.component_catalog.server.services.ApplicationAuthenticationProvider;
import org.opendevstack.component_catalog.server.services.FeedbackApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientResponseException;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @ParameterizedTest
    @ValueSource(ints = {400, 401, 403, 404, 429, 500, 503})
    void givenUpstreamError_whenRegisterSubmission_thenHideOnlyAuthenticationErrors(int upstreamStatus)
        throws Exception {
        var body = "{\"message\":\"internal authorization details\"}".getBytes(StandardCharsets.UTF_8);
        var upstreamHeaders = new HttpHeaders();
        upstreamHeaders.setContentType(MediaType.APPLICATION_JSON);
        upstreamHeaders.set("X-Internal-Details", "private-upstream-info");
        var exception = new RestClientResponseException(
            "Internal authorization details", upstreamStatus, "Upstream error", upstreamHeaders, body,
            StandardCharsets.UTF_8);
        var apiClientsBuilder = mock(ApiClientsBuilder.class);
        var authenticationProvider = mock(ApplicationAuthenticationProvider.class);
        var feedbackApi = mock(
            org.opendevstack.component_catalog.client.feedback_api.v0_0_1.api.FeedbackSubmissionsApi.class);
        var baseUrl = new URL("https://ods.example.com");
        var properties = ApplicationPropertiesConfiguration.OdsApiServerServiceProps.builder()
            .baseRestUrl(baseUrl)
            .build();

        when(authenticationProvider.getAccessToken()).thenReturn("access-token");
        when(apiClientsBuilder.feedbackSubmissionsApi("access-token", baseUrl.toString())).thenReturn(feedbackApi);
        when(feedbackApi.registerFeedbackSubmissionWithHttpInfo(any(FeedbackSubmissionRequest.class)))
            .thenThrow(exception);
        var service = new FeedbackApiService(apiClientsBuilder, authenticationProvider, properties);
        var controller = new FeedbackApiController(service, new ObjectMapper());
        var mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new ControllerExceptionHandler())
            .build();

        var result = mockMvc.perform(post("/v1/feedback/submissions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"submitter":"submitter@example.com","questions":[{"id":"rating"}],"answers":{"rating":5}}
                    """));
        if (upstreamStatus == 401 || upstreamStatus == 403) {
            result.andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(content().json("{\"message\":\"Unable to submit feedback\"}"))
                .andExpect(header().doesNotExist("X-Internal-Details"));
        } else {
            result.andExpect(status().is(upstreamStatus))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().bytes(body))
                .andExpect(header().string("X-Internal-Details", "private-upstream-info"));
        }
    }
}