package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.api.FeedbackSubmissionsApi;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmission;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.net.URL;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackApiServiceTest {

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @Test
    void givenFeedbackRequest_whenRegisterSubmission_thenUseAuthenticatedApiAndReturnResponse() throws Exception {
        var accessToken = "access-token";
        var baseUrl = new URL("https://ods.example.com");
        var properties = ApplicationPropertiesConfiguration.OdsApiServerServiceProps.builder()
            .baseRestUrl(baseUrl)
            .build();
        var feedbackApi = mock(FeedbackSubmissionsApi.class);
        var request = new FeedbackSubmissionRequest();
        var submission = mock(FeedbackSubmission.class);
        var expectedResponse = ResponseEntity.ok(submission);

        when(applicationAuthenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(apiClientsBuilder.feedbackSubmissionsApi(accessToken, baseUrl.toString())).thenReturn(feedbackApi);
        when(feedbackApi.registerFeedbackSubmissionWithHttpInfo(request)).thenReturn(expectedResponse);
        var service = new FeedbackApiService(apiClientsBuilder, applicationAuthenticationProvider, properties);

        var response = service.registerFeedbackSubmission(request);

        assertThat(response).isSameAs(expectedResponse);
        verify(applicationAuthenticationProvider).getAccessToken();
        verify(apiClientsBuilder).feedbackSubmissionsApi(accessToken, baseUrl.toString());
        verify(feedbackApi).registerFeedbackSubmissionWithHttpInfo(request);
    }

    @ParameterizedTest
    @MethodSource("upstreamFailures")
    void givenUpstreamFailure_whenRegisterSubmission_thenHideExternalDetails(RestClientException failure)
        throws Exception {
        var baseUrl = new URL("https://ods.example.com");
        var properties = ApplicationPropertiesConfiguration.OdsApiServerServiceProps.builder()
            .baseRestUrl(baseUrl)
            .build();
        var feedbackApi = mock(FeedbackSubmissionsApi.class);
        var request = new FeedbackSubmissionRequest();

        when(applicationAuthenticationProvider.getAccessToken()).thenReturn("access-token");
        when(apiClientsBuilder.feedbackSubmissionsApi("access-token", baseUrl.toString())).thenReturn(feedbackApi);
        when(feedbackApi.registerFeedbackSubmissionWithHttpInfo(request)).thenThrow(failure);
        var service = new FeedbackApiService(apiClientsBuilder, applicationAuthenticationProvider, properties);

        assertThatThrownBy(() -> service.registerFeedbackSubmission(request))
            .isExactlyInstanceOf(RuntimeException.class)
            .hasMessage("Unable to submit feedback")
            .hasCause(failure);
    }

    private static Stream<RestClientException> upstreamFailures() {
        return Stream.of(
            new HttpClientErrorException(HttpStatus.FORBIDDEN, "Internal authorization details"),
            new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server details"),
            new ResourceAccessException("Internal connection details"));
    }
}