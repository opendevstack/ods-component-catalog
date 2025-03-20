package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetChangesetsForIssue200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCommentJiraIssue;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestEnhancedEntityLink;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestJiraIssue;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.JiraIntegrationApi")
public class JiraIntegrationApi extends BaseApi {

    public JiraIntegrationApi() {
        super(new ApiClient());
    }

    @Autowired
    public JiraIntegrationApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Create Jira Issue
     * Create a Jira issue and associate it with a comment on a pull request.  This resource can only be used with comments on a pull request. Attempting to call this resource with a different type of comment (for example, a comment on a commit) will result in an error.    The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the comment to call this resource.  The JSON structure for the create issue format is specified by Jira&#39;s REST v2 API.
     * <p><b>200</b> - The created Jira issue key and the associated comment ID
     * <p><b>400</b> - The specified application link ID does not match any linked Jira instance.
     * <p><b>401</b> - Authentication with the Jira instance is required.
     * @param commentId the comment to associate the created Jira issue to (required)
     * @param applicationId id of the Jira server (optional)
     * @param body A String representation of the JSON format Jira create issue request see: &lt;a href&#x3D;\&quot;https://docs.atlassian.com/jira/REST/server/#api/2/issue-createIssue\&quot;&gt;Jira REST API&lt;/a&gt; (optional)
     * @return RestCommentJiraIssue
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestCommentJiraIssue createIssue(String commentId, String applicationId, String body) throws RestClientException {
        return createIssueWithHttpInfo(commentId, applicationId, body).getBody();
    }

    /**
     * Create Jira Issue
     * Create a Jira issue and associate it with a comment on a pull request.  This resource can only be used with comments on a pull request. Attempting to call this resource with a different type of comment (for example, a comment on a commit) will result in an error.    The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the comment to call this resource.  The JSON structure for the create issue format is specified by Jira&#39;s REST v2 API.
     * <p><b>200</b> - The created Jira issue key and the associated comment ID
     * <p><b>400</b> - The specified application link ID does not match any linked Jira instance.
     * <p><b>401</b> - Authentication with the Jira instance is required.
     * @param commentId the comment to associate the created Jira issue to (required)
     * @param applicationId id of the Jira server (optional)
     * @param body A String representation of the JSON format Jira create issue request see: &lt;a href&#x3D;\&quot;https://docs.atlassian.com/jira/REST/server/#api/2/issue-createIssue\&quot;&gt;Jira REST API&lt;/a&gt; (optional)
     * @return ResponseEntity&lt;RestCommentJiraIssue&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestCommentJiraIssue> createIssueWithHttpInfo(String commentId, String applicationId, String body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling createIssue");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("commentId", commentId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "applicationId", applicationId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestCommentJiraIssue> localReturnType = new ParameterizedTypeReference<RestCommentJiraIssue>() {};
        return apiClient.invokeAPI("/jira/latest/comments/{commentId}/issues", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get changesets for issue key
     * Retrieve a page of changesets associated with the given issue key.
     * <p><b>200</b> - A page of detailed changesets
     * @param issueKey The issue key to search by (required)
     * @param maxChanges The maximum number of changes to retrieve for each changeset (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetChangesetsForIssue200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetChangesetsForIssue200Response getChangesetsForIssue(String issueKey, String maxChanges, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getChangesetsForIssueWithHttpInfo(issueKey, maxChanges, start, limit).getBody();
    }

    /**
     * Get changesets for issue key
     * Retrieve a page of changesets associated with the given issue key.
     * <p><b>200</b> - A page of detailed changesets
     * @param issueKey The issue key to search by (required)
     * @param maxChanges The maximum number of changes to retrieve for each changeset (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetChangesetsForIssue200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetChangesetsForIssue200Response> getChangesetsForIssueWithHttpInfo(String issueKey, String maxChanges, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'issueKey' is set
        if (issueKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'issueKey' when calling getChangesetsForIssue");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("issueKey", issueKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxChanges", maxChanges));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetChangesetsForIssue200Response> localReturnType = new ParameterizedTypeReference<GetChangesetsForIssue200Response>() {};
        return apiClient.invokeAPI("/jira/latest/issues/{issueKey}/commits", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get entity link
     * Retrieves the enchanced primary entitylink   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the project having the primary enhanced entitylink.   
     * <p><b>200</b> - The primary enhanced entitylink.
     * @param projectKey The project key (required)
     * @return RestEnhancedEntityLink
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestEnhancedEntityLink getForProject(String projectKey) throws RestClientException {
        return getForProjectWithHttpInfo(projectKey).getBody();
    }

    /**
     * Get entity link
     * Retrieves the enchanced primary entitylink   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the project having the primary enhanced entitylink.   
     * <p><b>200</b> - The primary enhanced entitylink.
     * @param projectKey The project key (required)
     * @return ResponseEntity&lt;RestEnhancedEntityLink&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestEnhancedEntityLink> getForProjectWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getForProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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

        ParameterizedTypeReference<RestEnhancedEntityLink> localReturnType = new ParameterizedTypeReference<RestEnhancedEntityLink>() {};
        return apiClient.invokeAPI("/jira/latest/projects/{projectKey}/primary-enhanced-entitylink", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get issues for a pull request
     * Retrieves Jira issue keys that are associated with the commits in the specified pull request. The number of commits checked for issues is limited to a default of 100.
     * <p><b>200</b> - A list of Jira issues keys for the pull request
     * @param projectKey The project key (required)
     * @param pullRequestId The pull request id (required)
     * @param repositorySlug The repository slug (required)
     * @return List&lt;RestJiraIssue&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestJiraIssue> getIssueKeysForPullRequest(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return getIssueKeysForPullRequestWithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Get issues for a pull request
     * Retrieves Jira issue keys that are associated with the commits in the specified pull request. The number of commits checked for issues is limited to a default of 100.
     * <p><b>200</b> - A list of Jira issues keys for the pull request
     * @param projectKey The project key (required)
     * @param pullRequestId The pull request id (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;List&lt;RestJiraIssue&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestJiraIssue>> getIssueKeysForPullRequestWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getIssueKeysForPullRequest");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getIssueKeysForPullRequest");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getIssueKeysForPullRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json;charset=UTF-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<List<RestJiraIssue>> localReturnType = new ParameterizedTypeReference<List<RestJiraIssue>>() {};
        return apiClient.invokeAPI("/jira/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/issues", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "application/json;charset=UTF-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
