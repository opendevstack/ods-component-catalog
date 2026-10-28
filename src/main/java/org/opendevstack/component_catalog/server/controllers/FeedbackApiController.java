package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.server.api.FeedbackSubmissionsApi;
import org.opendevstack.component_catalog.server.model.FeedbackSubmission;
import org.opendevstack.component_catalog.server.model.FeedbackSubmissionRequest;
import org.opendevstack.component_catalog.server.services.FeedbackApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@RequiredArgsConstructor
@Validated
public class FeedbackApiController implements FeedbackSubmissionsApi {

    private final FeedbackApiService feedbackApiService;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<FeedbackSubmission> registerFeedbackSubmission(FeedbackSubmissionRequest request) {
        var clientRequest = objectMapper.convertValue(request,
                org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest.class);
        var response = feedbackApiService.registerFeedbackSubmission(clientRequest);
        var submission = objectMapper.convertValue(response.getBody(), FeedbackSubmission.class);

        return ResponseEntity.status(response.getStatusCode()).body(submission);
    }
}
