package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.api.FeedbackSubmissionsApi;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmission;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

@Service
@AllArgsConstructor
public class FeedbackApiService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @Qualifier("odsApiServerServiceConfig")
    private final ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServiceProps;

    public ResponseEntity<FeedbackSubmission> registerFeedbackSubmission(FeedbackSubmissionRequest request) {
        try {
            FeedbackSubmissionsApi feedbackSubmissionsApi = apiClientsBuilder.feedbackSubmissionsApi(
                applicationAuthenticationProvider.getAccessToken(), odsApiServiceProps.getBaseRestUrl().toString());

            return feedbackSubmissionsApi.registerFeedbackSubmissionWithHttpInfo(request);
        } catch (RestClientResponseException exception) {
            // This 401/403 is from our internal account, not the user's token. Handle it here to avoid a misleading token error or not-found redirect in the UI.
            if (exception.getStatusCode().equals(HttpStatus.UNAUTHORIZED)
                || exception.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                throw new RuntimeException("Unable to submit feedback", exception);
            }
            throw exception;
        }
    }
}