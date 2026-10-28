package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.api.FeedbackSubmissionsApi;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.model.FeedbackSubmissionRequest;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class FeedbackApiClientTest {

    @Test
    void givenJsonNullableResponse_whenRegisterSubmission_thenDeserializeProjectKey() {
        // given
        var objectMapper = new ObjectMapper().registerModule(new JsonNullableModule());
        var restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new MappingJackson2HttpMessageConverter(objectMapper));
        var mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo("http://feedback.example.com/api/v1/feedback/submissions"))
                .andExpect(method(POST))
                .andRespond(withSuccess("{\"projectKey\":\"PROJECT_KEY\"}", MediaType.APPLICATION_JSON));
        var apiClient = new org.opendevstack.component_catalog.client.feedback_api.v0_0_1.ApiClient(restTemplate);
        apiClient.setBasePath("http://feedback.example.com/api/v1/feedback");
        var api = new FeedbackSubmissionsApi(apiClient);
        var request = new FeedbackSubmissionRequest()
                .submitter("submitter@example.com")
                .questions(List.of(Map.of("id", "rating")))
                .answers(Map.of("rating", 5));

        // when
        var response = api.registerFeedbackSubmissionWithHttpInfo(request);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getProjectKey()).isEqualTo("PROJECT_KEY");
        mockServer.verify();
    }
}