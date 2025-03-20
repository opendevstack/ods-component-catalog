package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetPullRequestSuggestions200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetPullRequests1200Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-02-28T09:35:07.114611322+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.DashboardApi")
public class DashboardApi extends BaseApi {

    public DashboardApi() {
        super(new ApiClient());
    }

    @Autowired
    public DashboardApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Get pull request suggestions
     * Retrieves a page of suggestions for pull requests that the currently authenticated user may wish to raise. Such suggestions are based on ref changes occurring and so contain the ref change that prompted the suggestion plus the time the change event occurred. Changes will be returned in descending order based on the time the change that prompted the suggestion occurred.   Note that although the response is a page object, the interface does not support paging, however a limit can be applied to the size of the returned page.
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The current user is not authenticated
     * @param changesSince restrict pull request suggestions to be based on events that occurred since some timein the past. This is expressed in seconds since \&quot;now\&quot;. So to return suggestionsbased only on activity within the past 48 hours, pass a value of 172800. (optional)
     * @param limit restricts the result set to return at most this many suggestions. (optional)
     * @return GetPullRequestSuggestions200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetPullRequestSuggestions200Response getPullRequestSuggestions(String changesSince, String limit) throws RestClientException {
        return getPullRequestSuggestionsWithHttpInfo(changesSince, limit).getBody();
    }

    /**
     * Get pull request suggestions
     * Retrieves a page of suggestions for pull requests that the currently authenticated user may wish to raise. Such suggestions are based on ref changes occurring and so contain the ref change that prompted the suggestion plus the time the change event occurred. Changes will be returned in descending order based on the time the change that prompted the suggestion occurred.   Note that although the response is a page object, the interface does not support paging, however a limit can be applied to the size of the returned page.
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The current user is not authenticated
     * @param changesSince restrict pull request suggestions to be based on events that occurred since some timein the past. This is expressed in seconds since \&quot;now\&quot;. So to return suggestionsbased only on activity within the past 48 hours, pass a value of 172800. (optional)
     * @param limit restricts the result set to return at most this many suggestions. (optional)
     * @return ResponseEntity&lt;GetPullRequestSuggestions200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetPullRequestSuggestions200Response> getPullRequestSuggestionsWithHttpInfo(String changesSince, String limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "changesSince", changesSince));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetPullRequestSuggestions200Response> localReturnType = new ParameterizedTypeReference<GetPullRequestSuggestions200Response>() {};
        return apiClient.invokeAPI("/api/latest/dashboard/pull-request-suggestions", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull requests for current user
     * Retrieve a page of pull requests where the current authenticated user is involved as either a reviewer, author or a participant. The request may be filtered by pull request state, role or participant status.
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The current user is not authenticated
     * @param closedSince (optional, defaults to returning pull requests regardless of closed since date). Permits returning only pull requests with a closed timestamp set more recently that (now - closedSince). Units are in seconds. So for example if closed since 86400 is set only pull requests closed in the previous 24 hours will be returned. (optional)
     * @param role (optional, defaults to returning pull requests for any role). If a role is supplied only pull requests where the authenticated user is a participant in the given role will be returned. Either &lt;strong&gt;REVIEWER&lt;/strong&gt;, &lt;strong&gt;AUTHOR&lt;/strong&gt; or &lt;strong&gt;PARTICIPANT&lt;/strong&gt;. (optional)
     * @param participantStatus (optional, defaults to returning pull requests with any participant status). A comma separated list of participant status. That is, one or more of &lt;strong&gt;UNAPPROVED&lt;/strong&gt;, &lt;strong&gt;NEEDS_WORK&lt;/strong&gt;, or &lt;strong&gt;APPROVED&lt;/strong&gt;. (optional)
     * @param state (optional, defaults to returning pull requests in any state). If a state is supplied only pull requests in the specified state will be returned. Either &lt;strong&gt;OPEN&lt;/strong&gt;, &lt;strong&gt;DECLINED&lt;/strong&gt; or &lt;strong&gt;MERGED&lt;/strong&gt;. Omit this parameter to return pull request in any state. (optional)
     * @param order (optional, defaults to &lt;strong&gt;NEWEST&lt;/strong&gt;) the order to return pull requests in, either &lt;strong&gt;OLDEST&lt;/strong&gt; (as in: \&quot;oldest first\&quot;), &lt;strong&gt;NEWEST&lt;/strong&gt;, &lt;strong&gt;PARTICIPANT_STATUS&lt;/strong&gt;, or &lt;strong&gt;CLOSED_DATE&lt;/strong&gt;. Where &lt;strong&gt;CLOSED_DATE&lt;/strong&gt; is specified and the result set includes pull requests that are not in the closed state, these pull requests will appear first in the result set, followed by most recently closed pull requests. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetPullRequests1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetPullRequests1200Response getPullRequests1(String closedSince, String role, String participantStatus, String state, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getPullRequests1WithHttpInfo(closedSince, role, participantStatus, state, order, start, limit).getBody();
    }

    /**
     * Get pull requests for current user
     * Retrieve a page of pull requests where the current authenticated user is involved as either a reviewer, author or a participant. The request may be filtered by pull request state, role or participant status.
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The current user is not authenticated
     * @param closedSince (optional, defaults to returning pull requests regardless of closed since date). Permits returning only pull requests with a closed timestamp set more recently that (now - closedSince). Units are in seconds. So for example if closed since 86400 is set only pull requests closed in the previous 24 hours will be returned. (optional)
     * @param role (optional, defaults to returning pull requests for any role). If a role is supplied only pull requests where the authenticated user is a participant in the given role will be returned. Either &lt;strong&gt;REVIEWER&lt;/strong&gt;, &lt;strong&gt;AUTHOR&lt;/strong&gt; or &lt;strong&gt;PARTICIPANT&lt;/strong&gt;. (optional)
     * @param participantStatus (optional, defaults to returning pull requests with any participant status). A comma separated list of participant status. That is, one or more of &lt;strong&gt;UNAPPROVED&lt;/strong&gt;, &lt;strong&gt;NEEDS_WORK&lt;/strong&gt;, or &lt;strong&gt;APPROVED&lt;/strong&gt;. (optional)
     * @param state (optional, defaults to returning pull requests in any state). If a state is supplied only pull requests in the specified state will be returned. Either &lt;strong&gt;OPEN&lt;/strong&gt;, &lt;strong&gt;DECLINED&lt;/strong&gt; or &lt;strong&gt;MERGED&lt;/strong&gt;. Omit this parameter to return pull request in any state. (optional)
     * @param order (optional, defaults to &lt;strong&gt;NEWEST&lt;/strong&gt;) the order to return pull requests in, either &lt;strong&gt;OLDEST&lt;/strong&gt; (as in: \&quot;oldest first\&quot;), &lt;strong&gt;NEWEST&lt;/strong&gt;, &lt;strong&gt;PARTICIPANT_STATUS&lt;/strong&gt;, or &lt;strong&gt;CLOSED_DATE&lt;/strong&gt;. Where &lt;strong&gt;CLOSED_DATE&lt;/strong&gt; is specified and the result set includes pull requests that are not in the closed state, these pull requests will appear first in the result set, followed by most recently closed pull requests. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetPullRequests1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetPullRequests1200Response> getPullRequests1WithHttpInfo(String closedSince, String role, String participantStatus, String state, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "closedSince", closedSince));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "role", role));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "participantStatus", participantStatus));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetPullRequests1200Response> localReturnType = new ParameterizedTypeReference<GetPullRequests1200Response>() {};
        return apiClient.invokeAPI("/api/latest/dashboard/pull-requests", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
