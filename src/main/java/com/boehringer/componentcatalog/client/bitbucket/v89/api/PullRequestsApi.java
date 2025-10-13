package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetActivities200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetComments200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetCommits200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLikers200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetPullRequests1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetReviewerGroups1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ListParticipants200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestApplicationUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestApplySuggestionRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestChange;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestComment;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDefaultReviewersRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestErrors;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestAssignParticipantRoleRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestAssignStatusRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestCondition;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestDeclineRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestDeleteRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestFinishReviewRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestMergeConfig;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestMergeRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestMergeability;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestParticipant;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestRebaseRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestRebaseResult;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestRebaseability;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestReopenRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestReviewerGroup;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUserReaction;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UpdatePullRequestCondition1Request;

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

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.PullRequestsApi")
public class PullRequestsApi extends BaseApi {

    public PullRequestsApi() {
        super(new ApiClient());
    }

    @Autowired
    public PullRequestsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Apply pull request suggestion
     * Apply a suggestion contained within a comment.
     * <p><b>204</b> - An empty response indicating the suggestion has been applied.
     * <p><b>400</b> - The suggestion was not applied due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to apply the suggestion.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - There was an error applying the suggestion to the source branch. It must be applied manually.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restApplySuggestionRequest A request containing other parameters required to apply a suggestion - The given versions/hashes must match the server&#39;s version/hashes or the suggestion application will fail (in order to avoid applying the suggestion to the wrong place (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void applySuggestion(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestApplySuggestionRequest restApplySuggestionRequest) throws RestClientException {
        applySuggestionWithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, restApplySuggestionRequest);
    }

    /**
     * Apply pull request suggestion
     * Apply a suggestion contained within a comment.
     * <p><b>204</b> - An empty response indicating the suggestion has been applied.
     * <p><b>400</b> - The suggestion was not applied due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to apply the suggestion.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - There was an error applying the suggestion to the source branch. It must be applied manually.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restApplySuggestionRequest A request containing other parameters required to apply a suggestion - The given versions/hashes must match the server&#39;s version/hashes or the suggestion application will fail (in order to avoid applying the suggestion to the wrong place (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> applySuggestionWithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestApplySuggestionRequest restApplySuggestionRequest) throws RestClientException {
        Object localVarPostBody = restApplySuggestionRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling applySuggestion");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling applySuggestion");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling applySuggestion");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling applySuggestion");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/apply-suggestion", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Approve pull request
     * Approve a pull request as the current user. Implicitly adds the user as a participant if they are not already.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead
     * <p><b>200</b> - Details of the new participant.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestPullRequestParticipant
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public RestPullRequestParticipant approve(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return approveWithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Approve pull request
     * Approve a pull request as the current user. Implicitly adds the user as a participant if they are not already.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead
     * <p><b>200</b> - Details of the new participant.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestPullRequestParticipant&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<RestPullRequestParticipant> approveWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling approve");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling approve");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling approve");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestParticipant> localReturnType = new ParameterizedTypeReference<RestPullRequestParticipant>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/approve", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Assign pull request participant role
     * Assigns a participant to an explicit role in pull request. Currently only the REVIEWER role may be assigned.   If the user is not yet a participant in the pull request, they are made one and assigned the supplied role.   If the user is already a participant in the pull request, their previous role is replaced with the supplied role unless they are already assigned the AUTHOR role which cannot be changed and will result in a Bad Request (400) response code.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the participants in this pull request.
     * <p><b>400</b> - The request does not have the username and role, or is attempting an invalid assignment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Adding reviewers isn&#39;t supported on archived repositories
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestAssignParticipantRoleRequest The participant to be added to the pull request, includes the user and their role (required)
     * @return RestPullRequestParticipant
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestParticipant assignParticipantRole(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestAssignParticipantRoleRequest restPullRequestAssignParticipantRoleRequest) throws RestClientException {
        return assignParticipantRoleWithHttpInfo(projectKey, pullRequestId, repositorySlug, restPullRequestAssignParticipantRoleRequest).getBody();
    }

    /**
     * Assign pull request participant role
     * Assigns a participant to an explicit role in pull request. Currently only the REVIEWER role may be assigned.   If the user is not yet a participant in the pull request, they are made one and assigned the supplied role.   If the user is already a participant in the pull request, their previous role is replaced with the supplied role unless they are already assigned the AUTHOR role which cannot be changed and will result in a Bad Request (400) response code.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the participants in this pull request.
     * <p><b>400</b> - The request does not have the username and role, or is attempting an invalid assignment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Adding reviewers isn&#39;t supported on archived repositories
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestAssignParticipantRoleRequest The participant to be added to the pull request, includes the user and their role (required)
     * @return ResponseEntity&lt;RestPullRequestParticipant&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestParticipant> assignParticipantRoleWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestAssignParticipantRoleRequest restPullRequestAssignParticipantRoleRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestAssignParticipantRoleRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling assignParticipantRole");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling assignParticipantRole");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling assignParticipantRole");
        }
        
        // verify the required parameter 'restPullRequestAssignParticipantRoleRequest' is set
        if (restPullRequestAssignParticipantRoleRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restPullRequestAssignParticipantRoleRequest' when calling assignParticipantRole");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestParticipant> localReturnType = new ParameterizedTypeReference<RestPullRequestParticipant>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Test if pull request can be merged
     * Test whether a pull request can be merged.   A pull request may not be merged if:   - there are conflicts that need to be manually resolved before merging; and/or - one or more merge checks have vetoed the merge.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The mergeability status of the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The specified pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestPullRequestMergeability
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestMergeability canMerge(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return canMergeWithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Test if pull request can be merged
     * Test whether a pull request can be merged.   A pull request may not be merged if:   - there are conflicts that need to be manually resolved before merging; and/or - one or more merge checks have vetoed the merge.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The mergeability status of the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The specified pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestPullRequestMergeability&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestMergeability> canMergeWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling canMerge");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling canMerge");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling canMerge");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestMergeability> localReturnType = new ParameterizedTypeReference<RestPullRequestMergeability>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Check PR rebase precondition
     * Checks preconditions to determine whether the pull request can be rebased.  Some of the preconditions are:  - The pull request is between Git repositories - The pull request is currently open - The pull request&#39;s {@link PullRequest#getFromRef \&quot;from\&quot; ref} is a &lt;i&gt;branch&lt;/i&gt;    - In other words, the qualified ID for the \&quot;from\&quot; ref must start with &lt;code&gt;refs/heads/&lt;/code&gt;    - Tags, and other non-standard refs, cannot be rebased - The current user has an e-mail address    - Pull requests cannot be rebased anonymously    - &#x60;git rebase&#x60; records the current user as the committer for the rebased commits, which        requires a name and e-mail address - The current user has &lt;i&gt;write&lt;/i&gt; access to the {@link PullRequest#getFromRef \&quot;from\&quot; ref}&#39;s repository    - Note that in order to &lt;i&gt;view&lt;/i&gt; a pull request a user is only required to have &lt;i&gt;read&lt;/i&gt;      access to the {@link PullRequest#getToRef toRef}&#39;s repository, so just because a user can &lt;i&gt;see&lt;/i&gt;      a pull request does not mean they can request a rebase   This list is not exhaustive, and the exact set of preconditions applied can be extended by third-party add-ons.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The rebaseability status of the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestPullRequestRebaseability
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestRebaseability canRebase(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return canRebaseWithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Check PR rebase precondition
     * Checks preconditions to determine whether the pull request can be rebased.  Some of the preconditions are:  - The pull request is between Git repositories - The pull request is currently open - The pull request&#39;s {@link PullRequest#getFromRef \&quot;from\&quot; ref} is a &lt;i&gt;branch&lt;/i&gt;    - In other words, the qualified ID for the \&quot;from\&quot; ref must start with &lt;code&gt;refs/heads/&lt;/code&gt;    - Tags, and other non-standard refs, cannot be rebased - The current user has an e-mail address    - Pull requests cannot be rebased anonymously    - &#x60;git rebase&#x60; records the current user as the committer for the rebased commits, which        requires a name and e-mail address - The current user has &lt;i&gt;write&lt;/i&gt; access to the {@link PullRequest#getFromRef \&quot;from\&quot; ref}&#39;s repository    - Note that in order to &lt;i&gt;view&lt;/i&gt; a pull request a user is only required to have &lt;i&gt;read&lt;/i&gt;      access to the {@link PullRequest#getToRef toRef}&#39;s repository, so just because a user can &lt;i&gt;see&lt;/i&gt;      a pull request does not mean they can request a rebase   This list is not exhaustive, and the exact set of preconditions applied can be extended by third-party add-ons.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The rebaseability status of the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestPullRequestRebaseability&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestRebaseability> canRebaseWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling canRebase");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling canRebase");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling canRebase");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestRebaseability> localReturnType = new ParameterizedTypeReference<RestPullRequestRebaseability>() {};
        return apiClient.invokeAPI("/git/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/rebase", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create reviewer group
     * Create a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>201</b> - The newly created reviewer group.
     * <p><b>400</b> - The request is missing a reviewer group name.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * <p><b>409</b> - The new created name already exists.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restReviewerGroup The request containing the details of the reviewer group. (optional)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup create4(String projectKey, String repositorySlug, RestReviewerGroup restReviewerGroup) throws RestClientException {
        return create4WithHttpInfo(projectKey, repositorySlug, restReviewerGroup).getBody();
    }

    /**
     * Create reviewer group
     * Create a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>201</b> - The newly created reviewer group.
     * <p><b>400</b> - The request is missing a reviewer group name.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * <p><b>409</b> - The new created name already exists.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restReviewerGroup The request containing the details of the reviewer group. (optional)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> create4WithHttpInfo(String projectKey, String repositorySlug, RestReviewerGroup restReviewerGroup) throws RestClientException {
        Object localVarPostBody = restReviewerGroup;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling create4");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling create4");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add new blocker comment
     * Add a new blocker comment.   Comments can be added in a few places by setting different attributes:   General pull request blocker comment:  &#x60;&#x60;&#x60;  {       \&quot;text\&quot;: \&quot;A task on a pull request.\&quot;  } &#x60;&#x60;&#x60;  Blocker reply to a comment:   &#x60;&#x60;&#x60;  {      \&quot;text\&quot;: \&quot;This reply is a task.\&quot;,       \&quot;parent\&quot;: {           \&quot;id\&quot;: 1       }  }  &#x60;&#x60;&#x60;  General blocker file comment:  &#x60;&#x60;&#x60;  {      \&quot;text\&quot;: \&quot;A blocker comment on a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;RANGE\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       }   }  &#x60;&#x60;&#x60;  Blocker file line comment:   &#x60;&#x60;&#x60;  {       \&quot;text\&quot;: \&quot;A task on a particular line within a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;line\&quot;: 1,           \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,           \&quot;fileType\&quot;: \&quot;FROM\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       }   }  &#x60;&#x60;&#x60;  For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on. For backwards compatibility purposes if no diffType is provided and no fromHash/toHash pair is provided the diffType will be resolved to &#39;EFFECTIVE&#39;. In any other cases the diffType is REQUIRED.   For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:   - &#39;ADDED&#39; - for an added line; - &#39;REMOVED&#39; - for a removed line; or - &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff.    &#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:   - &#39;FROM&#39; - the source file of the diff  - &#39;TO&#39; - the destination file of the diff   If the current user is not a participant the user is added as a watcher of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, create a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - The new created name already exists or adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add. (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment createComment1(String projectKey, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        return createComment1WithHttpInfo(projectKey, pullRequestId, repositorySlug, restComment).getBody();
    }

    /**
     * Add new blocker comment
     * Add a new blocker comment.   Comments can be added in a few places by setting different attributes:   General pull request blocker comment:  &#x60;&#x60;&#x60;  {       \&quot;text\&quot;: \&quot;A task on a pull request.\&quot;  } &#x60;&#x60;&#x60;  Blocker reply to a comment:   &#x60;&#x60;&#x60;  {      \&quot;text\&quot;: \&quot;This reply is a task.\&quot;,       \&quot;parent\&quot;: {           \&quot;id\&quot;: 1       }  }  &#x60;&#x60;&#x60;  General blocker file comment:  &#x60;&#x60;&#x60;  {      \&quot;text\&quot;: \&quot;A blocker comment on a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;RANGE\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       }   }  &#x60;&#x60;&#x60;  Blocker file line comment:   &#x60;&#x60;&#x60;  {       \&quot;text\&quot;: \&quot;A task on a particular line within a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;line\&quot;: 1,           \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,           \&quot;fileType\&quot;: \&quot;FROM\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       }   }  &#x60;&#x60;&#x60;  For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on. For backwards compatibility purposes if no diffType is provided and no fromHash/toHash pair is provided the diffType will be resolved to &#39;EFFECTIVE&#39;. In any other cases the diffType is REQUIRED.   For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:   - &#39;ADDED&#39; - for an added line; - &#39;REMOVED&#39; - for a removed line; or - &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff.    &#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:   - &#39;FROM&#39; - the source file of the diff  - &#39;TO&#39; - the destination file of the diff   If the current user is not a participant the user is added as a watcher of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, create a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - The new created name already exists or adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add. (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> createComment1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createComment1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling createComment1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createComment1");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add pull request comment
     * Add a new comment.   Comments can be added in a few places by setting different attributes: &lt;/p&gt;General pull request comment:   &lt;pre&gt; {     \&quot;text\&quot;: \&quot;An insightful general comment on a pull request.\&quot;   }   &lt;/pre&gt; Reply to a comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A measured reply.\&quot;,     \&quot;parent\&quot;: {        \&quot;id\&quot;: 1      }   }   &lt;/pre&gt; General file comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;An insightful general comment on a file.\&quot;,     \&quot;anchor\&quot;: {        \&quot;diffType\&quot;: \&quot;RANGE\&quot;,        \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,        \&quot;path\&quot;: \&quot;path/to/file\&quot;,        \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,        \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;     }   }   &lt;/pre&gt; File line comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A pithy comment on a particular line within a file.\&quot;,     \&quot;anchor\&quot;: {        \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,        \&quot;line\&quot;: 1,        \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,        \&quot;fileType\&quot;: \&quot;FROM\&quot;,        \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,        \&quot;path\&quot;: \&quot;path/to/file\&quot;,        \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,        \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;      }   }   &lt;/pre&gt;    Add a new task.   Tasks are just comments with the attribute &#39;severity&#39; set to &#39;BLOCKER&#39;:   General pull request task:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A task on a pull request.\&quot;,     \&quot;severity\&quot;: \&quot;BLOCKER\&quot;   }   &lt;/pre&gt;    Add a pending comment.    Pending comments are just comments with the attribute &#39;state&#39; set to &#39;PENDING&#39;:   Pending comment: &lt;pre&gt; {     \&quot;text\&quot;: \&quot;This is a pending comment\&quot;,     \&quot;state\&quot;: \&quot;PENDING\&quot;   }   &lt;/pre&gt;   For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on. For backwards compatibility purposes if no diffType is provided and no fromHash/toHash pair is provided the diffType will be resolved to &#39;EFFECTIVE&#39;. In any other cases the diffType is REQUIRED.   For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:   - &#39;ADDED&#39; - for an added line; - &#39;REMOVED&#39; - for a removed line; or - &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff. &lt;/ul&gt;&#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:   - &#39;FROM&#39; - the source file of the diff - &#39;TO&#39; - the destination file of the diff &lt;/ul&gt;If the current user is not a participant the user is added as a watcher of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, create a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - Adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment createComment2(String projectKey, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        return createComment2WithHttpInfo(projectKey, pullRequestId, repositorySlug, restComment).getBody();
    }

    /**
     * Add pull request comment
     * Add a new comment.   Comments can be added in a few places by setting different attributes: &lt;/p&gt;General pull request comment:   &lt;pre&gt; {     \&quot;text\&quot;: \&quot;An insightful general comment on a pull request.\&quot;   }   &lt;/pre&gt; Reply to a comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A measured reply.\&quot;,     \&quot;parent\&quot;: {        \&quot;id\&quot;: 1      }   }   &lt;/pre&gt; General file comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;An insightful general comment on a file.\&quot;,     \&quot;anchor\&quot;: {        \&quot;diffType\&quot;: \&quot;RANGE\&quot;,        \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,        \&quot;path\&quot;: \&quot;path/to/file\&quot;,        \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,        \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;     }   }   &lt;/pre&gt; File line comment:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A pithy comment on a particular line within a file.\&quot;,     \&quot;anchor\&quot;: {        \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,        \&quot;line\&quot;: 1,        \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,        \&quot;fileType\&quot;: \&quot;FROM\&quot;,        \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,        \&quot;path\&quot;: \&quot;path/to/file\&quot;,        \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,        \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;      }   }   &lt;/pre&gt;    Add a new task.   Tasks are just comments with the attribute &#39;severity&#39; set to &#39;BLOCKER&#39;:   General pull request task:  &lt;pre&gt; {     \&quot;text\&quot;: \&quot;A task on a pull request.\&quot;,     \&quot;severity\&quot;: \&quot;BLOCKER\&quot;   }   &lt;/pre&gt;    Add a pending comment.    Pending comments are just comments with the attribute &#39;state&#39; set to &#39;PENDING&#39;:   Pending comment: &lt;pre&gt; {     \&quot;text\&quot;: \&quot;This is a pending comment\&quot;,     \&quot;state\&quot;: \&quot;PENDING\&quot;   }   &lt;/pre&gt;   For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on. For backwards compatibility purposes if no diffType is provided and no fromHash/toHash pair is provided the diffType will be resolved to &#39;EFFECTIVE&#39;. In any other cases the diffType is REQUIRED.   For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:   - &#39;ADDED&#39; - for an added line; - &#39;REMOVED&#39; - for a removed line; or - &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff. &lt;/ul&gt;&#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:   - &#39;FROM&#39; - the source file of the diff - &#39;TO&#39; - the destination file of the diff &lt;/ul&gt;If the current user is not a participant the user is added as a watcher of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, create a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or parent comment.
     * <p><b>409</b> - Adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> createComment2WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createComment2");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling createComment2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createComment2");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create pull request
     * Create a new pull request from a source branch or tag to a target branch. The source and target may be in the same repository, or different ones. (Note that different repositories must belong to the same &lt;code&gt;Repository#getHierarchyId()&lt;/code&gt; hierarchy.)   The &lt;code&gt;fromRef&lt;/code&gt; may be a branch or a tag. The &lt;code&gt;toRef&lt;/code&gt; is required to be a branch. Tags are not allowed as targets because tags are intended to be immutable and should not be changed after they are created.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the &lt;code&gt;fromRef&lt;/code&gt; and &lt;code&gt;toRef&lt;/code&gt; repositories to call this resource.
     * <p><b>201</b> - The newly created pull request.
     * <p><b>400</b> - The pull request entity supplied in the request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a pull request between the two specified repositories.
     * <p><b>404</b> - One of the specified repositories or branches does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - There was a problem resolving one or more reviewers. - The specified branches were the same. - The &lt;em&gt;to&lt;/em&gt; branch is already up-to-date with all the commits on the     &lt;em&gt;from&lt;/em&gt; branch. - A pull request between the two branches already exists. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequest The pull request data (optional)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest createPullRequest(String projectKey, String repositorySlug, RestPullRequest restPullRequest) throws RestClientException {
        return createPullRequestWithHttpInfo(projectKey, repositorySlug, restPullRequest).getBody();
    }

    /**
     * Create pull request
     * Create a new pull request from a source branch or tag to a target branch. The source and target may be in the same repository, or different ones. (Note that different repositories must belong to the same &lt;code&gt;Repository#getHierarchyId()&lt;/code&gt; hierarchy.)   The &lt;code&gt;fromRef&lt;/code&gt; may be a branch or a tag. The &lt;code&gt;toRef&lt;/code&gt; is required to be a branch. Tags are not allowed as targets because tags are intended to be immutable and should not be changed after they are created.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the &lt;code&gt;fromRef&lt;/code&gt; and &lt;code&gt;toRef&lt;/code&gt; repositories to call this resource.
     * <p><b>201</b> - The newly created pull request.
     * <p><b>400</b> - The pull request entity supplied in the request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a pull request between the two specified repositories.
     * <p><b>404</b> - One of the specified repositories or branches does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - There was a problem resolving one or more reviewers. - The specified branches were the same. - The &lt;em&gt;to&lt;/em&gt; branch is already up-to-date with all the commits on the     &lt;em&gt;from&lt;/em&gt; branch. - A pull request between the two branches already exists. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequest The pull request data (optional)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> createPullRequestWithHttpInfo(String projectKey, String repositorySlug, RestPullRequest restPullRequest) throws RestClientException {
        Object localVarPostBody = restPullRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createPullRequest");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createPullRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create default reviewer
     * Create a default reviewer pull request condition for the given project.
     * <p><b>200</b> - The default reviewer pull request condition that was created.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param restDefaultReviewersRequest The details needed to create a default reviewer pull request condition. (optional)
     * @return RestPullRequestCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestCondition createPullRequestCondition(String projectKey, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        return createPullRequestConditionWithHttpInfo(projectKey, restDefaultReviewersRequest).getBody();
    }

    /**
     * Create default reviewer
     * Create a default reviewer pull request condition for the given project.
     * <p><b>200</b> - The default reviewer pull request condition that was created.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param restDefaultReviewersRequest The details needed to create a default reviewer pull request condition. (optional)
     * @return ResponseEntity&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestCondition> createPullRequestConditionWithHttpInfo(String projectKey, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        Object localVarPostBody = restDefaultReviewersRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createPullRequestCondition");
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestCondition> localReturnType = new ParameterizedTypeReference<RestPullRequestCondition>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/condition", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create default reviewers condition
     * Create a default reviewer pull request condition for the given repository.
     * <p><b>200</b> - The default reviewer pull request condition that was created.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restDefaultReviewersRequest The details needed to create a default reviewer pull request condition. (optional)
     * @return RestPullRequestCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestCondition createPullRequestCondition1(String projectKey, String repositorySlug, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        return createPullRequestCondition1WithHttpInfo(projectKey, repositorySlug, restDefaultReviewersRequest).getBody();
    }

    /**
     * Create default reviewers condition
     * Create a default reviewer pull request condition for the given repository.
     * <p><b>200</b> - The default reviewer pull request condition that was created.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restDefaultReviewersRequest The details needed to create a default reviewer pull request condition. (optional)
     * @return ResponseEntity&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestCondition> createPullRequestCondition1WithHttpInfo(String projectKey, String repositorySlug, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        Object localVarPostBody = restDefaultReviewersRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createPullRequestCondition1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createPullRequestCondition1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestCondition> localReturnType = new ParameterizedTypeReference<RestPullRequestCondition>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/repos/{repositorySlug}/condition", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create reviewer group
     * Create a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>201</b> - The newly created reviewer group.
     * <p><b>400</b> - The request is missing a reviewer group name.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * <p><b>409</b> - The new created name already exists.
     * @param projectKey The project key. (required)
     * @param restReviewerGroup The reviewer group to be create (optional)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup createReviewerGroup(String projectKey, RestReviewerGroup restReviewerGroup) throws RestClientException {
        return createReviewerGroupWithHttpInfo(projectKey, restReviewerGroup).getBody();
    }

    /**
     * Create reviewer group
     * Create a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>201</b> - The newly created reviewer group.
     * <p><b>400</b> - The request is missing a reviewer group name.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * <p><b>409</b> - The new created name already exists.
     * @param projectKey The project key. (required)
     * @param restReviewerGroup The reviewer group to be create (optional)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> createReviewerGroupWithHttpInfo(String projectKey, RestReviewerGroup restReviewerGroup) throws RestClientException {
        Object localVarPostBody = restReviewerGroup;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createReviewerGroup");
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/reviewer-groups", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Decline pull request
     * Decline a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The pull request was declined.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not OPEN or has been updated since the version specified by the request.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pullrequest ID provided by the path (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestDeclineRequest The body holder (optional)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest decline(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestDeclineRequest restPullRequestDeclineRequest) throws RestClientException {
        return declineWithHttpInfo(projectKey, pullRequestId, repositorySlug, version, restPullRequestDeclineRequest).getBody();
    }

    /**
     * Decline pull request
     * Decline a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The pull request was declined.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not OPEN or has been updated since the version specified by the request.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pullrequest ID provided by the path (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestDeclineRequest The body holder (optional)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> declineWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestDeclineRequest restPullRequestDeclineRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestDeclineRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling decline");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling decline");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling decline");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/decline", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete pull request
     * Deletes a pull request.   To call this resource, users must be authenticated and have permission to view the pull request. Additionally, they must:   - be the pull request author, if the system is configured to allow authors to delete their own   pull requests (this is the default) OR  - have repository administrator permission for the repository the pull request is targeting   A body containing the version of the pull request must be provided with this request.   &#x60;{ \&quot;version\&quot;: 1 }&#x60;
     * <p><b>204</b> - The pull request was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Deleting pull requests isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestDeleteRequest A body containing the version of the pull request (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete6(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestDeleteRequest restPullRequestDeleteRequest) throws RestClientException {
        delete6WithHttpInfo(projectKey, pullRequestId, repositorySlug, restPullRequestDeleteRequest);
    }

    /**
     * Delete pull request
     * Deletes a pull request.   To call this resource, users must be authenticated and have permission to view the pull request. Additionally, they must:   - be the pull request author, if the system is configured to allow authors to delete their own   pull requests (this is the default) OR  - have repository administrator permission for the repository the pull request is targeting   A body containing the version of the pull request must be provided with this request.   &#x60;{ \&quot;version\&quot;: 1 }&#x60;
     * <p><b>204</b> - The pull request was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Deleting pull requests isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestDeleteRequest A body containing the version of the pull request (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete6WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestDeleteRequest restPullRequestDeleteRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestDeleteRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete6");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling delete6");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete6");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete reviewer group
     * Deletes a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the reviewer group in this project.
     * <p><b>404</b> - Unable to find the supplied reviewer group ID.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be deleted (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete7(String projectKey, String id) throws RestClientException {
        delete7WithHttpInfo(projectKey, id);
    }

    /**
     * Delete reviewer group
     * Deletes a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the reviewer group in this project.
     * <p><b>404</b> - Unable to find the supplied reviewer group ID.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be deleted (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete7WithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete7");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling delete7");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/reviewer-groups/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete reviewer group
     * Deletes a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The operation was successful
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the reviewer group in this repository.
     * <p><b>404</b> - Unable to find the supplied reviewer group ID.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be deleted (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete8(String projectKey, String id, String repositorySlug) throws RestClientException {
        delete8WithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete reviewer group
     * Deletes a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The operation was successful
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the reviewer group in this repository.
     * <p><b>404</b> - Unable to find the supplied reviewer group ID.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be deleted (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete8WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete8");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling delete8");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete8");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete pull request comment
     * Delete a pull request comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or pull request.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteComment1(String projectKey, String commentId, String pullRequestId, String repositorySlug, String version) throws RestClientException {
        deleteComment1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, version);
    }

    /**
     * Delete pull request comment
     * Delete a pull request comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or pull request.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteComment1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteComment1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling deleteComment1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling deleteComment1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteComment1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments/{commentId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a pull request comment
     * Delete a pull request comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or pull request.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteComment2(String projectKey, String commentId, String pullRequestId, String repositorySlug, String version) throws RestClientException {
        deleteComment2WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, version);
    }

    /**
     * Delete a pull request comment
     * Delete a pull request comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or pull request.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteComment2WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteComment2");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling deleteComment2");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling deleteComment2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteComment2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove default reviewer
     * Delete the default reviewer pull request condition associated with the given ID.
     * <p><b>204</b> - An empty response indicating that the pull request condition was deleted.
     * <p><b>404</b> - An empty response indicating a pull request condition with the given ID could not be found.
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deletePullRequestCondition(String projectKey, String id) throws RestClientException {
        deletePullRequestConditionWithHttpInfo(projectKey, id);
    }

    /**
     * Remove default reviewer
     * Delete the default reviewer pull request condition associated with the given ID.
     * <p><b>204</b> - An empty response indicating that the pull request condition was deleted.
     * <p><b>404</b> - An empty response indicating a pull request condition with the given ID could not be found.
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deletePullRequestConditionWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deletePullRequestCondition");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deletePullRequestCondition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/condition/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a default reviewer condition
     * Delete the default reviewer pull request condition associated with the given ID.
     * <p><b>204</b> - An empty response indicating that the pull request condition was deleted
     * <p><b>404</b> - An empty response indicating a pull request condition with the given ID could not be found.
     * @param projectKey The project key. (required)
     * @param id  (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deletePullRequestCondition1(String projectKey, Integer id, String repositorySlug) throws RestClientException {
        deletePullRequestCondition1WithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete a default reviewer condition
     * Delete the default reviewer pull request condition associated with the given ID.
     * <p><b>204</b> - An empty response indicating that the pull request condition was deleted
     * <p><b>404</b> - An empty response indicating a pull request condition with the given ID could not be found.
     * @param projectKey The project key. (required)
     * @param id  (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deletePullRequestCondition1WithHttpInfo(String projectKey, Integer id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deletePullRequestCondition1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deletePullRequestCondition1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deletePullRequestCondition1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/repos/{repositorySlug}/condition/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Discard pull request review
     * Discard a pull request review for the authenticated user.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The pull request review has been discarded.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to discard the the pull request review
     * <p><b>404</b> - The specified pull request or repository does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void discardReview(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        discardReviewWithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Discard pull request review
     * Discard a pull request review for the authenticated user.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The pull request review has been discarded.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to discard the the pull request review
     * <p><b>404</b> - The specified pull request or repository does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> discardReviewWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling discardReview");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling discardReview");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling discardReview");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/review", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Complete pull request review
     * Complete a review on a pull request.
     * <p><b>200</b> - Getting back the number of published comments and completing the review on a pull request.
     * <p><b>400</b> - The request is invalid when there is no request body provided, or the participant status in the request is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - There is no pull request review for the user to finish.
     * <p><b>409</b> - The pull request has been updated since the last reviewed commit specified by the request, or reviews cannot be made on pull requests in archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. Note: This parameter is deprecated. Use last reviewed commit in request body instead (optional)
     * @param restPullRequestFinishReviewRequest The REST request which contains comment text, last reviewed commit and participant status. If last reviewed commit is provided, it will be used to update the participant status. The operation will fail if the latest commit of the pull request does not match the provided last reviewed commit. If last reviewed commit is not provided, the latest commit of the pull request will be used for the update by default. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void finishReview(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestFinishReviewRequest restPullRequestFinishReviewRequest) throws RestClientException {
        finishReviewWithHttpInfo(projectKey, pullRequestId, repositorySlug, version, restPullRequestFinishReviewRequest);
    }

    /**
     * Complete pull request review
     * Complete a review on a pull request.
     * <p><b>200</b> - Getting back the number of published comments and completing the review on a pull request.
     * <p><b>400</b> - The request is invalid when there is no request body provided, or the participant status in the request is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - There is no pull request review for the user to finish.
     * <p><b>409</b> - The pull request has been updated since the last reviewed commit specified by the request, or reviews cannot be made on pull requests in archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. Note: This parameter is deprecated. Use last reviewed commit in request body instead (optional)
     * @param restPullRequestFinishReviewRequest The REST request which contains comment text, last reviewed commit and participant status. If last reviewed commit is provided, it will be used to update the participant status. The operation will fail if the latest commit of the pull request does not match the provided last reviewed commit. If last reviewed commit is not provided, the latest commit of the pull request will be used for the update by default. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> finishReviewWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestFinishReviewRequest restPullRequestFinishReviewRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestFinishReviewRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling finishReview");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling finishReview");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling finishReview");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/review", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request
     * Retrieve a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest get5(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return get5WithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Get pull request
     * Retrieve a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> get5WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling get5");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling get5");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling get5");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request activity
     * Retrieve a page of activity associated with a pull request.   Activity items include comments, approvals, rescopes (i.e. adding and removing of commits), merges and more.   Different types of activity items may be introduced in newer versions of Stash or by user installed plugins, so clients should be flexible enough to handle unexpected entity shapes in the returned page.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of activity relating to the specified pull request.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromType (required if &lt;strong&gt;fromId&lt;/strong&gt; is present) the type of the activity item specified by &lt;strong&gt;fromId&lt;/strong&gt; (either &lt;strong&gt;COMMENT&lt;/strong&gt; or &lt;strong&gt;ACTIVITY&lt;/strong&gt;) (optional)
     * @param fromId (optional) the ID of the activity item to use as the first item in the returned page (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetActivities200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetActivities200Response getActivities(String projectKey, String pullRequestId, String repositorySlug, String fromType, String fromId, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getActivitiesWithHttpInfo(projectKey, pullRequestId, repositorySlug, fromType, fromId, start, limit).getBody();
    }

    /**
     * Get pull request activity
     * Retrieve a page of activity associated with a pull request.   Activity items include comments, approvals, rescopes (i.e. adding and removing of commits), merges and more.   Different types of activity items may be introduced in newer versions of Stash or by user installed plugins, so clients should be flexible enough to handle unexpected entity shapes in the returned page.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of activity relating to the specified pull request.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromType (required if &lt;strong&gt;fromId&lt;/strong&gt; is present) the type of the activity item specified by &lt;strong&gt;fromId&lt;/strong&gt; (either &lt;strong&gt;COMMENT&lt;/strong&gt; or &lt;strong&gt;ACTIVITY&lt;/strong&gt;) (optional)
     * @param fromId (optional) the ID of the activity item to use as the first item in the returned page (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetActivities200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetActivities200Response> getActivitiesWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String fromType, String fromId, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getActivities");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getActivities");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getActivities");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromType", fromType));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromId", fromId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetActivities200Response> localReturnType = new ParameterizedTypeReference<GetActivities200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/activities", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request comment
     * Retrieves a pull request comment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment getComment1(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        return getComment1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Get pull request comment
     * Retrieves a pull request comment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> getComment1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComment1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling getComment1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getComment1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComment1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments/{commentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a pull request comment
     * Retrieves a pull request comment.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment getComment2(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        return getComment2WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Get a pull request comment
     * Retrieves a pull request comment.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> getComment2WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComment2");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling getComment2");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getComment2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComment2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search pull request comments
     * Gets comments matching the given set of field values for the specified pull request. (Note this does &lt;b&gt;not&lt;/b&gt; perform any kind of searching for comments by their text).   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param count If true only the count of the comments by state will be returned (and not the body of the comments). (optional)
     * @param state  (optional)
     * @param states (optional). If supplied, only comments with a state in the given list will be returned. The state can be OPEN or RESOLVED. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetComments200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetComments200Response getComments1(String projectKey, String pullRequestId, String repositorySlug, String count, List<String> state, String states, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getComments1WithHttpInfo(projectKey, pullRequestId, repositorySlug, count, state, states, start, limit).getBody();
    }

    /**
     * Search pull request comments
     * Gets comments matching the given set of field values for the specified pull request. (Note this does &lt;b&gt;not&lt;/b&gt; perform any kind of searching for comments by their text).   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param count If true only the count of the comments by state will be returned (and not the body of the comments). (optional)
     * @param state  (optional)
     * @param states (optional). If supplied, only comments with a state in the given list will be returned. The state can be OPEN or RESOLVED. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetComments200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetComments200Response> getComments1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String count, List<String> state, String states, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComments1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getComments1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComments1");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "count", count));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "states", states));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetComments200Response> localReturnType = new ParameterizedTypeReference<GetComments200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request comments for path
     * Gets comments for the specified pull request and path.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param path The path to stream comments for a given path (required)
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromHash The from commit hash to stream comments for a RANGE or COMMIT arbitrary change scope (optional)
     * @param anchorState ACTIVE to stream the active comments; ORPHANED to stream the orphaned comments; ALL to stream both the active and the orphaned comments; (optional)
     * @param diffType  (optional)
     * @param toHash The to commit hash to stream comments for a RANGE or COMMIT arbitrary change scope (optional)
     * @param state  (optional)
     * @param diffTypes EFFECTIVE to stream the comments related to the effective diff of the pull request; RANGE to stream comments related to a commit range between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;); COMMIT to stream comments related to a commit between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;) (optional)
     * @param states (optional). If supplied, only comments with a state in the given list will be returned. The state can be OPEN or RESOLVED. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetComments200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetComments200Response getComments2(String path, String projectKey, String pullRequestId, String repositorySlug, String fromHash, String anchorState, List<String> diffType, String toHash, List<String> state, String diffTypes, String states, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getComments2WithHttpInfo(path, projectKey, pullRequestId, repositorySlug, fromHash, anchorState, diffType, toHash, state, diffTypes, states, start, limit).getBody();
    }

    /**
     * Get pull request comments for path
     * Gets comments for the specified pull request and path.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param path The path to stream comments for a given path (required)
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromHash The from commit hash to stream comments for a RANGE or COMMIT arbitrary change scope (optional)
     * @param anchorState ACTIVE to stream the active comments; ORPHANED to stream the orphaned comments; ALL to stream both the active and the orphaned comments; (optional)
     * @param diffType  (optional)
     * @param toHash The to commit hash to stream comments for a RANGE or COMMIT arbitrary change scope (optional)
     * @param state  (optional)
     * @param diffTypes EFFECTIVE to stream the comments related to the effective diff of the pull request; RANGE to stream comments related to a commit range between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;); COMMIT to stream comments related to a commit between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;) (optional)
     * @param states (optional). If supplied, only comments with a state in the given list will be returned. The state can be OPEN or RESOLVED. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetComments200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetComments200Response> getComments2WithHttpInfo(String path, String projectKey, String pullRequestId, String repositorySlug, String fromHash, String anchorState, List<String> diffType, String toHash, List<String> state, String diffTypes, String states, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling getComments2");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComments2");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getComments2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComments2");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromHash", fromHash));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "anchorState", anchorState));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "diffType", diffType));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "toHash", toHash));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "diffTypes", diffTypes));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "states", states));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetComments200Response> localReturnType = new ParameterizedTypeReference<GetComments200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get merge strategies
     * Retrieve the merge strategies available for this instance.   The user must be authenticated to call this resource.
     * <p><b>200</b> - The merge configuration of this instance.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist
     * @param scmId the id of the scm to get strategies for (required)
     * @return RestPullRequestMergeConfig
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestMergeConfig getMergeConfig(String scmId) throws RestClientException {
        return getMergeConfigWithHttpInfo(scmId).getBody();
    }

    /**
     * Get merge strategies
     * Retrieve the merge strategies available for this instance.   The user must be authenticated to call this resource.
     * <p><b>200</b> - The merge configuration of this instance.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist
     * @param scmId the id of the scm to get strategies for (required)
     * @return ResponseEntity&lt;RestPullRequestMergeConfig&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestMergeConfig> getMergeConfigWithHttpInfo(String scmId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'scmId' is set
        if (scmId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scmId' when calling getMergeConfig");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("scmId", scmId);

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

        ParameterizedTypeReference<RestPullRequestMergeConfig> localReturnType = new ParameterizedTypeReference<RestPullRequestMergeConfig>() {};
        return apiClient.invokeAPI("/api/latest/admin/pull-requests/{scmId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request commits
     * Retrieve commits for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of commits from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId ID of the pullrequest, part of the path (required)
     * @param repositorySlug The repository slug. (required)
     * @param avatarScheme The desired scheme for the avatar URL. If the parameter is not present URLs will use the same scheme as this request (optional)
     * @param withCounts If set to true, the service will add \&quot;authorCount\&quot; and \&quot;totalCount\&quot; at the end of the page. \&quot;authorCount\&quot; is the number of different authors and \&quot;totalCount\&quot; is the total number of commits. (optional)
     * @param avatarSize If present the service adds avatar URLs for commit authors. Should be an integer specifying the desired size in pixels. If the parameter is not present, avatar URLs will not be setCOMMIT to stream comments related to a commit between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetCommits200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetCommits200Response getPullRequestCommits(String projectKey, String pullRequestId, String repositorySlug, String avatarScheme, String withCounts, String avatarSize, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getPullRequestCommitsWithHttpInfo(projectKey, pullRequestId, repositorySlug, avatarScheme, withCounts, avatarSize, start, limit).getBody();
    }

    /**
     * Get pull request commits
     * Retrieve commits for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of commits from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId ID of the pullrequest, part of the path (required)
     * @param repositorySlug The repository slug. (required)
     * @param avatarScheme The desired scheme for the avatar URL. If the parameter is not present URLs will use the same scheme as this request (optional)
     * @param withCounts If set to true, the service will add \&quot;authorCount\&quot; and \&quot;totalCount\&quot; at the end of the page. \&quot;authorCount\&quot; is the number of different authors and \&quot;totalCount\&quot; is the total number of commits. (optional)
     * @param avatarSize If present the service adds avatar URLs for commit authors. Should be an integer specifying the desired size in pixels. If the parameter is not present, avatar URLs will not be setCOMMIT to stream comments related to a commit between two arbitrary commits (requires &#39;fromHash&#39; and &#39;toHash&#39;) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetCommits200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetCommits200Response> getPullRequestCommitsWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String avatarScheme, String withCounts, String avatarSize, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestCommits");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getPullRequestCommits");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequestCommits");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "avatarScheme", avatarScheme));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCounts", withCounts));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "avatarSize", avatarSize));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetCommits200Response> localReturnType = new ParameterizedTypeReference<GetCommits200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/commits", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get default reviewers
     * Return a page of default reviewer pull request conditions that have been configured for this project.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given project.
     * @param projectKey The project key. (required)
     * @return List&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestPullRequestCondition> getPullRequestConditions(String projectKey) throws RestClientException {
        return getPullRequestConditionsWithHttpInfo(projectKey).getBody();
    }

    /**
     * Get default reviewers
     * Return a page of default reviewer pull request conditions that have been configured for this project.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given project.
     * @param projectKey The project key. (required)
     * @return ResponseEntity&lt;List&lt;RestPullRequestCondition&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestPullRequestCondition>> getPullRequestConditionsWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestConditions");
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

        ParameterizedTypeReference<List<RestPullRequestCondition>> localReturnType = new ParameterizedTypeReference<List<RestPullRequestCondition>>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/conditions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get configured default reviewers
     * Return a page of default reviewer pull request conditions that have been configured for this repository.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return List&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestPullRequestCondition> getPullRequestConditions1(String projectKey, String repositorySlug) throws RestClientException {
        return getPullRequestConditions1WithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get configured default reviewers
     * Return a page of default reviewer pull request conditions that have been configured for this repository.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;List&lt;RestPullRequestCondition&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestPullRequestCondition>> getPullRequestConditions1WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestConditions1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequestConditions1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<List<RestPullRequestCondition>> localReturnType = new ParameterizedTypeReference<List<RestPullRequestCondition>>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/repos/{repositorySlug}/conditions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository pull requests containing commit
     * Retrieve a page of pull requests in the current repository that contain the given commit.  The user must be authenticated and have access to the specified repository to call this resource.
     * <p><b>200</b> - Return a page of pull requests in the current repository containing the given commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commit ID (required)
     * @param repositorySlug The repository slug (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetPullRequests1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetPullRequests1200Response getPullRequests(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getPullRequestsWithHttpInfo(projectKey, commitId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get repository pull requests containing commit
     * Retrieve a page of pull requests in the current repository that contain the given commit.  The user must be authenticated and have access to the specified repository to call this resource.
     * <p><b>200</b> - Return a page of pull requests in the current repository containing the given commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commit ID (required)
     * @param repositorySlug The repository slug (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetPullRequests1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetPullRequests1200Response> getPullRequestsWithHttpInfo(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequests");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getPullRequests");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequests");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/pull-requests", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull requests for repository
     * Retrieve a page of pull requests to or from the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.  Optionally clients can specify PR participant filters. Each filter has a mandatory username.N parameter, and the optional role.N and approved.N parameters.   - username.N - the \&quot;root\&quot; of a single participant filter, where \&quot;N\&quot; is a natural number   starting from 1. This allows clients to specify multiple participant filters, by providing consecutive   filters as username.1, username.2 etc. Note that the filters numbering has to start   with 1 and be continuous for all filters to be processed. The total allowed number of participant   filters is 10 and all filters exceeding that limit will be dropped. - role.N(optional) the role associated with username.N.   This must be one of AUTHOR, REVIEWER, or PARTICIPANT - approved.N (optional) the approved status associated with username.N.   That is whether username.N has approved the PR. Either true, or false 
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param withAttributes (optional) defaults to true, whether to return additional pull request attributes (optional)
     * @param at (optional) a &lt;i&gt;fully-qualified&lt;/i&gt; branch ID to find pull requests to or from, such as refs/heads/master (optional)
     * @param withProperties (optional) defaults to true, whether to return additional pull request properties (optional)
     * @param filterText (optional) If specified, only pull requests where the title or description contains the supplied string will be returned. (optional)
     * @param state (optional, defaults to &lt;strong&gt;OPEN&lt;/strong&gt;). Supply &lt;strong&gt;ALL&lt;/strong&gt; to return pull request in any state. If a state is supplied only pull requests in the specified state will be returned. Either &lt;strong&gt;OPEN&lt;/strong&gt;, &lt;strong&gt;DECLINED&lt;/strong&gt; or &lt;strong&gt;MERGED&lt;/strong&gt;. (optional)
     * @param order (optional, defaults to &lt;strong&gt;NEWEST&lt;/strong&gt;) the order to return pull requests in, either &lt;strong&gt;OLDEST&lt;/strong&gt; (as in: \&quot;oldest first\&quot;) or &lt;strong&gt;NEWEST&lt;/strong&gt;. (optional)
     * @param direction (optional, defaults to &lt;strong&gt;INCOMING&lt;/strong&gt;) the direction relative to the specified repository. Either &lt;strong&gt;INCOMING&lt;/strong&gt; or &lt;strong&gt;OUTGOING&lt;/strong&gt;. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetPullRequests1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetPullRequests1200Response getPullRequestsForRepository(String projectKey, String repositorySlug, String withAttributes, String at, String withProperties, String filterText, String state, String order, String direction, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getPullRequestsForRepositoryWithHttpInfo(projectKey, repositorySlug, withAttributes, at, withProperties, filterText, state, order, direction, start, limit).getBody();
    }

    /**
     * Get pull requests for repository
     * Retrieve a page of pull requests to or from the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.  Optionally clients can specify PR participant filters. Each filter has a mandatory username.N parameter, and the optional role.N and approved.N parameters.   - username.N - the \&quot;root\&quot; of a single participant filter, where \&quot;N\&quot; is a natural number   starting from 1. This allows clients to specify multiple participant filters, by providing consecutive   filters as username.1, username.2 etc. Note that the filters numbering has to start   with 1 and be continuous for all filters to be processed. The total allowed number of participant   filters is 10 and all filters exceeding that limit will be dropped. - role.N(optional) the role associated with username.N.   This must be one of AUTHOR, REVIEWER, or PARTICIPANT - approved.N (optional) the approved status associated with username.N.   That is whether username.N has approved the PR. Either true, or false 
     * <p><b>200</b> - A page of pull requests that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param withAttributes (optional) defaults to true, whether to return additional pull request attributes (optional)
     * @param at (optional) a &lt;i&gt;fully-qualified&lt;/i&gt; branch ID to find pull requests to or from, such as refs/heads/master (optional)
     * @param withProperties (optional) defaults to true, whether to return additional pull request properties (optional)
     * @param filterText (optional) If specified, only pull requests where the title or description contains the supplied string will be returned. (optional)
     * @param state (optional, defaults to &lt;strong&gt;OPEN&lt;/strong&gt;). Supply &lt;strong&gt;ALL&lt;/strong&gt; to return pull request in any state. If a state is supplied only pull requests in the specified state will be returned. Either &lt;strong&gt;OPEN&lt;/strong&gt;, &lt;strong&gt;DECLINED&lt;/strong&gt; or &lt;strong&gt;MERGED&lt;/strong&gt;. (optional)
     * @param order (optional, defaults to &lt;strong&gt;NEWEST&lt;/strong&gt;) the order to return pull requests in, either &lt;strong&gt;OLDEST&lt;/strong&gt; (as in: \&quot;oldest first\&quot;) or &lt;strong&gt;NEWEST&lt;/strong&gt;. (optional)
     * @param direction (optional, defaults to &lt;strong&gt;INCOMING&lt;/strong&gt;) the direction relative to the specified repository. Either &lt;strong&gt;INCOMING&lt;/strong&gt; or &lt;strong&gt;OUTGOING&lt;/strong&gt;. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetPullRequests1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetPullRequests1200Response> getPullRequestsForRepositoryWithHttpInfo(String projectKey, String repositorySlug, String withAttributes, String at, String withProperties, String filterText, String state, String order, String direction, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestsForRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequestsForRepository");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withAttributes", withAttributes));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withProperties", withProperties));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "direction", direction));
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request comment thread
     * Get the &lt;code&gt;CommentThread&lt;/code&gt; threads which have &lt;code&gt;Comment&lt;/code&gt; comments that have a &lt;code&gt;CommentState#PENDING&lt;/code&gt; pending state and are part of the pull request review for the authenticated user.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetComments200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetComments200Response getReview(String projectKey, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getReviewWithHttpInfo(projectKey, pullRequestId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get pull request comment thread
     * Get the &lt;code&gt;CommentThread&lt;/code&gt; threads which have &lt;code&gt;Comment&lt;/code&gt; comments that have a &lt;code&gt;CommentState#PENDING&lt;/code&gt; pending state and are part of the pull request review for the authenticated user.
     * <p><b>200</b> - A page of Comments from the supplied pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetComments200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetComments200Response> getReviewWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReview");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getReview");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getReview");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetComments200Response> localReturnType = new ParameterizedTypeReference<GetComments200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/review", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get reviewer group
     * Retrieve a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup getReviewerGroup(String projectKey, String id) throws RestClientException {
        return getReviewerGroupWithHttpInfo(projectKey, id).getBody();
    }

    /**
     * Get reviewer group
     * Retrieve a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> getReviewerGroupWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReviewerGroup");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getReviewerGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);

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

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/reviewer-groups/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get reviewer group
     * Retrieve a reviewer group.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup getReviewerGroup1(String projectKey, String id, String repositorySlug) throws RestClientException {
        return getReviewerGroup1WithHttpInfo(projectKey, id, repositorySlug).getBody();
    }

    /**
     * Get reviewer group
     * Retrieve a reviewer group.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> getReviewerGroup1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReviewerGroup1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getReviewerGroup1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getReviewerGroup1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all reviewer groups
     * Retrieve a page of reviewer groups of a given scope.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of reviewer group(s) of the provided scope.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetReviewerGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetReviewerGroups1200Response getReviewerGroups(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getReviewerGroupsWithHttpInfo(projectKey, start, limit).getBody();
    }

    /**
     * Get all reviewer groups
     * Retrieve a page of reviewer groups of a given scope.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of reviewer group(s) of the provided scope.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetReviewerGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetReviewerGroups1200Response> getReviewerGroupsWithHttpInfo(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReviewerGroups");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetReviewerGroups1200Response> localReturnType = new ParameterizedTypeReference<GetReviewerGroups1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/reviewer-groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all reviewer groups
     * Retrieve a page of reviewer groups of a given scope.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A &#x60;page&#x60; of reviewer group(s) of the provided scope and its inherited scope.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetReviewerGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetReviewerGroups1200Response getReviewerGroups1(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getReviewerGroups1WithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get all reviewer groups
     * Retrieve a page of reviewer groups of a given scope.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A &#x60;page&#x60; of reviewer group(s) of the provided scope and its inherited scope.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetReviewerGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetReviewerGroups1200Response> getReviewerGroups1WithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReviewerGroups1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getReviewerGroups1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetReviewerGroups1200Response> localReturnType = new ParameterizedTypeReference<GetReviewerGroups1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get required reviewers for PR creation
     * Return a set of users who are required reviewers for pull requests created from the given source repository and ref to the given target ref in this repository.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given repository.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param targetRepoId The ID of the repository in which the target ref exists (optional)
     * @param sourceRepoId The ID of the repository in which the source ref exists (optional)
     * @param sourceRefId The ID of the source ref (optional)
     * @param targetRefId The ID of the target ref (optional)
     * @return List&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestPullRequestCondition> getReviewers(String projectKey, String repositorySlug, String targetRepoId, String sourceRepoId, String sourceRefId, String targetRefId) throws RestClientException {
        return getReviewersWithHttpInfo(projectKey, repositorySlug, targetRepoId, sourceRepoId, sourceRefId, targetRefId).getBody();
    }

    /**
     * Get required reviewers for PR creation
     * Return a set of users who are required reviewers for pull requests created from the given source repository and ref to the given target ref in this repository.
     * <p><b>200</b> - The default reviewer pull request conditions associated with the given repository.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param targetRepoId The ID of the repository in which the target ref exists (optional)
     * @param sourceRepoId The ID of the repository in which the source ref exists (optional)
     * @param sourceRefId The ID of the source ref (optional)
     * @param targetRefId The ID of the target ref (optional)
     * @return ResponseEntity&lt;List&lt;RestPullRequestCondition&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestPullRequestCondition>> getReviewersWithHttpInfo(String projectKey, String repositorySlug, String targetRepoId, String sourceRepoId, String sourceRefId, String targetRefId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReviewers");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getReviewers");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "targetRepoId", targetRepoId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sourceRepoId", sourceRepoId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sourceRefId", sourceRefId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "targetRefId", targetRefId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<List<RestPullRequestCondition>> localReturnType = new ParameterizedTypeReference<List<RestPullRequestCondition>>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/repos/{repositorySlug}/reviewers", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get reviewer group users
     * Retrieve a list of the users of a reviewer group.  This does not return all the users of the group, only the users who are licensed and have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The list of users of a reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The ID supplied does not exist.d
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @param repositorySlug The repository slug. (required)
     * @return List&lt;RestApplicationUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestApplicationUser> getUsers(String projectKey, String id, String repositorySlug) throws RestClientException {
        return getUsersWithHttpInfo(projectKey, id, repositorySlug).getBody();
    }

    /**
     * Get reviewer group users
     * Retrieve a list of the users of a reviewer group.  This does not return all the users of the group, only the users who are licensed and have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The list of users of a reviewer group.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The ID supplied does not exist.d
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be retrieved (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;List&lt;RestApplicationUser&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestApplicationUser>> getUsersWithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getUsers");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getUsers");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getUsers");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
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

        ParameterizedTypeReference<List<RestApplicationUser>> localReturnType = new ParameterizedTypeReference<List<RestApplicationUser>>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups/{id}/users", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request participants
     * Retrieves a page of the participants for a given pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the participants in this pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ListParticipants200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListParticipants200Response listParticipants(String projectKey, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return listParticipantsWithHttpInfo(projectKey, pullRequestId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get pull request participants
     * Retrieves a page of the participants for a given pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the participants in this pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;ListParticipants200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListParticipants200Response> listParticipantsWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling listParticipants");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling listParticipants");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling listParticipants");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<ListParticipants200Response> localReturnType = new ParameterizedTypeReference<ListParticipants200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Merge pull request
     * Merge the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to merge the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The pull request has conflicts. - A merge check vetoed the merge. - The specified version is out of date. - The specified pull request is not open. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestMergeRequest The body holder (optional)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest merge(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestMergeRequest restPullRequestMergeRequest) throws RestClientException {
        return mergeWithHttpInfo(projectKey, pullRequestId, repositorySlug, version, restPullRequestMergeRequest).getBody();
    }

    /**
     * Merge pull request
     * Merge the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to merge the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The pull request has conflicts. - A merge check vetoed the merge. - The specified version is out of date. - The specified pull request is not open. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestMergeRequest The body holder (optional)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> mergeWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestMergeRequest restPullRequestMergeRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestMergeRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling merge");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling merge");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling merge");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * React to a PR comment
     * Add an emoticon reaction to a pull request comment
     * <p><b>200</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param emoticon The emoticon to add (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestUserReaction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUserReaction react1(String projectKey, String commentId, String pullRequestId, String emoticon, String repositorySlug) throws RestClientException {
        return react1WithHttpInfo(projectKey, commentId, pullRequestId, emoticon, repositorySlug).getBody();
    }

    /**
     * React to a PR comment
     * Add an emoticon reaction to a pull request comment
     * <p><b>200</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param emoticon The emoticon to add (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestUserReaction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUserReaction> react1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String emoticon, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling react1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling react1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling react1");
        }
        
        // verify the required parameter 'emoticon' is set
        if (emoticon == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'emoticon' when calling react1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling react1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("emoticon", emoticon);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<RestUserReaction> localReturnType = new ParameterizedTypeReference<RestUserReaction>() {};
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/reactions/{emoticon}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Rebase pull request
     * Rebases the specified pull request, rewriting the incoming commits to start from the tip commit of the pull request&#39;s target branch. &lt;i&gt;This operation alters the pull request&#39;s source branch and cannot be undone.&lt;/i&gt;  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets &lt;i&gt;and&lt;/i&gt; &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the pull request&#39;s source repository to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request and/or to update its source branch.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Any of the following error cases occurred (check the error message for more details):  - The rebase encountered conflicts. - The rebase discarded all of the incoming commits and would have left the pull request empty - A &lt;tt&gt;PreRepositoryHook&lt;/tt&gt; vetoed the rebase. - The specified version is out of date. - The specified pull request is not open. - The target repository is archived.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestRebaseRequest The pull request rebase request. (optional)
     * @return RestPullRequestRebaseResult
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestRebaseResult rebase(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestRebaseRequest restPullRequestRebaseRequest) throws RestClientException {
        return rebaseWithHttpInfo(projectKey, pullRequestId, repositorySlug, restPullRequestRebaseRequest).getBody();
    }

    /**
     * Rebase pull request
     * Rebases the specified pull request, rewriting the incoming commits to start from the tip commit of the pull request&#39;s target branch. &lt;i&gt;This operation alters the pull request&#39;s source branch and cannot be undone.&lt;/i&gt;  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets &lt;i&gt;and&lt;/i&gt; &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the pull request&#39;s source repository to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request and/or to update its source branch.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Any of the following error cases occurred (check the error message for more details):  - The rebase encountered conflicts. - The rebase discarded all of the incoming commits and would have left the pull request empty - A &lt;tt&gt;PreRepositoryHook&lt;/tt&gt; vetoed the rebase. - The specified version is out of date. - The specified pull request is not open. - The target repository is archived.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestRebaseRequest The pull request rebase request. (optional)
     * @return ResponseEntity&lt;RestPullRequestRebaseResult&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestRebaseResult> rebaseWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestPullRequestRebaseRequest restPullRequestRebaseRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestRebaseRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling rebase");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling rebase");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling rebase");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestRebaseResult> localReturnType = new ParameterizedTypeReference<RestPullRequestRebaseResult>() {};
        return apiClient.invokeAPI("/git/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/rebase", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Re-open pull request
     * Re-open a declined pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to reopen the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The pull request is not in a declined state. - The specified version is out of date. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestReopenRequest The body holder (optional)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest reopen(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestReopenRequest restPullRequestReopenRequest) throws RestClientException {
        return reopenWithHttpInfo(projectKey, pullRequestId, repositorySlug, version, restPullRequestReopenRequest).getBody();
    }

    /**
     * Re-open pull request
     * Re-open a declined pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The merged pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to reopen the specified pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The pull request is not in a declined state. - The specified version is out of date. - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @param restPullRequestReopenRequest The body holder (optional)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> reopenWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String version, RestPullRequestReopenRequest restPullRequestReopenRequest) throws RestClientException {
        Object localVarPostBody = restPullRequestReopenRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling reopen");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling reopen");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling reopen");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/reopen", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search pull request participants
     * Retrieve a page of participant users for all the pull requests to or from the specified repository.   Optionally clients can specify following filters.
     * <p><b>200</b> - A page of users that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter (optional) Return only users, whose username, name or email address &lt;i&gt;contain&lt;/i&gt; the filter value (optional)
     * @param role (optional) The role associated with the pull request participant. This must be one of AUTHOR, REVIEWER, or PARTICIPANT (optional)
     * @param direction (optional), Defaults to &lt;strong&gt;INCOMING&lt;/strong&gt;) the direction relative to the specified repository. Either &lt;strong&gt;INCOMING&lt;/strong&gt; or &lt;strong&gt;OUTGOING&lt;/strong&gt;. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLikers200Response search(String projectKey, String repositorySlug, String filter, String role, String direction, BigDecimal start, BigDecimal limit) throws RestClientException {
        return searchWithHttpInfo(projectKey, repositorySlug, filter, role, direction, start, limit).getBody();
    }

    /**
     * Search pull request participants
     * Retrieve a page of participant users for all the pull requests to or from the specified repository.   Optionally clients can specify following filters.
     * <p><b>200</b> - A page of users that match the search criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter (optional) Return only users, whose username, name or email address &lt;i&gt;contain&lt;/i&gt; the filter value (optional)
     * @param role (optional) The role associated with the pull request participant. This must be one of AUTHOR, REVIEWER, or PARTICIPANT (optional)
     * @param direction (optional), Defaults to &lt;strong&gt;INCOMING&lt;/strong&gt;) the direction relative to the specified repository. Either &lt;strong&gt;INCOMING&lt;/strong&gt; or &lt;strong&gt;OUTGOING&lt;/strong&gt;. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLikers200Response> searchWithHttpInfo(String projectKey, String repositorySlug, String filter, String role, String direction, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling search");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling search");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "role", role));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "direction", direction));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetLikers200Response> localReturnType = new ParameterizedTypeReference<GetLikers200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/participants", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update merge strategies
     * Update the pull request merge strategies for the context repository.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.   Only the strategies provided will be enabled, only one may be set to default   An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:&#x60;&#x60;&#x60;{      \&quot;mergeConfig\&quot;: {        }  }  &#x60;&#x60;&#x60;  Upon completion of this request, the effective configuration will be the default configuration.
     * <p><b>200</b> - The repository pull request merge strategies for the context repository.
     * <p><b>400</b> - The repository pull request merge strategies were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to administrate thespecified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - Setting or deleting merge configurations isn&#39;t supported on archived repositories.
     * @param scmId the id of the scm to get strategies for (required)
     * @param restPullRequestSettings the settings (optional)
     * @return RestPullRequestMergeConfig
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestMergeConfig setMergeConfig(String scmId, RestPullRequestSettings restPullRequestSettings) throws RestClientException {
        return setMergeConfigWithHttpInfo(scmId, restPullRequestSettings).getBody();
    }

    /**
     * Update merge strategies
     * Update the pull request merge strategies for the context repository.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.   Only the strategies provided will be enabled, only one may be set to default   An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:&#x60;&#x60;&#x60;{      \&quot;mergeConfig\&quot;: {        }  }  &#x60;&#x60;&#x60;  Upon completion of this request, the effective configuration will be the default configuration.
     * <p><b>200</b> - The repository pull request merge strategies for the context repository.
     * <p><b>400</b> - The repository pull request merge strategies were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to administrate thespecified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - Setting or deleting merge configurations isn&#39;t supported on archived repositories.
     * @param scmId the id of the scm to get strategies for (required)
     * @param restPullRequestSettings the settings (optional)
     * @return ResponseEntity&lt;RestPullRequestMergeConfig&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestMergeConfig> setMergeConfigWithHttpInfo(String scmId, RestPullRequestSettings restPullRequestSettings) throws RestClientException {
        Object localVarPostBody = restPullRequestSettings;
        
        // verify the required parameter 'scmId' is set
        if (scmId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scmId' when calling setMergeConfig");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("scmId", scmId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestMergeConfig> localReturnType = new ParameterizedTypeReference<RestPullRequestMergeConfig>() {};
        return apiClient.invokeAPI("/api/latest/admin/pull-requests/{scmId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets pull request changes
     * Gets changes for the specified PullRequest.  If the changeScope query parameter is set to &#39;UNREVIEWED&#39;, the application will attempt to stream unreviewed changes based on the lastReviewedCommit of the current user, which are the changes between the lastReviewedCommit and the latest commit of the source branch. The current user is considered to &lt;i&gt;not&lt;/i&gt; have any unreviewed changes for the pull request when the lastReviewedCommit is either null (everything is unreviewed, so all changes are streamed), equal to the latest commit of the source branch (everything is reviewed), or no longer on the source branch (the source branch has been rebased). In these cases, the application will fall back to streaming all changes (the default), which is the effective diff for the pull request. The type of changes streamed can be determined by the changeScope parameter included in the properties map of the response.   Note: This resource is currently &lt;i&gt;not paged&lt;/i&gt;. The server will return at most one page. The server will truncate the number of changes to either the request&#39;s page limit or an internal maximum, whichever is smaller. The start parameter of the page request is also ignored.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of unreviewed Changes for the current user from the supplied pull request, including the unreviewedCommits in the properties map.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param sinceId The since commit hash to stream changes for a RANGE arbitrary change scope (optional)
     * @param changeScope UNREVIEWED to stream the unreviewed changes for the current user (if they exist); RANGE to stream changes between two arbitrary commits (requires &#39;sinceId&#39; and &#39;untilId&#39;); otherwise ALL to stream all changes (the default) (optional)
     * @param untilId The until commit hash to stream changes for a RANGE arbitrary change scope (optional)
     * @param withComments true to apply comment counts in the changes (the default); otherwise, false to stream changes without comment counts (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return RestChange
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestChange streamChanges1(String projectKey, String pullRequestId, String repositorySlug, String sinceId, String changeScope, String untilId, String withComments, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamChanges1WithHttpInfo(projectKey, pullRequestId, repositorySlug, sinceId, changeScope, untilId, withComments, start, limit).getBody();
    }

    /**
     * Gets pull request changes
     * Gets changes for the specified PullRequest.  If the changeScope query parameter is set to &#39;UNREVIEWED&#39;, the application will attempt to stream unreviewed changes based on the lastReviewedCommit of the current user, which are the changes between the lastReviewedCommit and the latest commit of the source branch. The current user is considered to &lt;i&gt;not&lt;/i&gt; have any unreviewed changes for the pull request when the lastReviewedCommit is either null (everything is unreviewed, so all changes are streamed), equal to the latest commit of the source branch (everything is reviewed), or no longer on the source branch (the source branch has been rebased). In these cases, the application will fall back to streaming all changes (the default), which is the effective diff for the pull request. The type of changes streamed can be determined by the changeScope parameter included in the properties map of the response.   Note: This resource is currently &lt;i&gt;not paged&lt;/i&gt;. The server will return at most one page. The server will truncate the number of changes to either the request&#39;s page limit or an internal maximum, whichever is smaller. The start parameter of the page request is also ignored.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A page of unreviewed Changes for the current user from the supplied pull request, including the unreviewedCommits in the properties map.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository or pull request.
     * <p><b>404</b> - The repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param sinceId The since commit hash to stream changes for a RANGE arbitrary change scope (optional)
     * @param changeScope UNREVIEWED to stream the unreviewed changes for the current user (if they exist); RANGE to stream changes between two arbitrary commits (requires &#39;sinceId&#39; and &#39;untilId&#39;); otherwise ALL to stream all changes (the default) (optional)
     * @param untilId The until commit hash to stream changes for a RANGE arbitrary change scope (optional)
     * @param withComments true to apply comment counts in the changes (the default); otherwise, false to stream changes without comment counts (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;RestChange&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestChange> streamChanges1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String sinceId, String changeScope, String untilId, String withComments, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamChanges1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling streamChanges1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamChanges1");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sinceId", sinceId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "changeScope", changeScope));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "untilId", untilId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withComments", withComments));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestChange> localReturnType = new ParameterizedTypeReference<RestChange>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/changes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream pull request as patch
     * Streams a patch representing a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A patch representing the specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to access the pull request.
     * <p><b>404</b> - The pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamPatch1(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        streamPatch1WithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Stream pull request as patch
     * Streams a patch representing a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A patch representing the specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to access the pull request.
     * <p><b>404</b> - The pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamPatch1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamPatch1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling streamPatch1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamPatch1");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}.patch", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream the raw diff for a pull request
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - If the pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param sinceId The since commit hash to stream a diff between two arbitrary hashes (optional)
     * @param srcPath The previous path to the file, if the file has been copied, moved or renamed (optional)
     * @param untilId The until commit hash to stream a diff between two arbitrary hashes (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff4(String projectKey, String pullRequestId, String repositorySlug, String contextLines, String sinceId, String srcPath, String untilId, String whitespace) throws RestClientException {
        streamRawDiff4WithHttpInfo(projectKey, pullRequestId, repositorySlug, contextLines, sinceId, srcPath, untilId, whitespace);
    }

    /**
     * Stream the raw diff for a pull request
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - If the pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param sinceId The since commit hash to stream a diff between two arbitrary hashes (optional)
     * @param srcPath The previous path to the file, if the file has been copied, moved or renamed (optional)
     * @param untilId The until commit hash to stream a diff between two arbitrary hashes (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff4WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String contextLines, String sinceId, String srcPath, String untilId, String whitespace) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff4");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling streamRawDiff4");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff4");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sinceId", sinceId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "untilId", untilId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/diff", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream raw diff for a pull request
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>400</b> - If the request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - If the pull request does not exist.
     * @param path The path to the file which should be diffed (optional) (required)
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param sinceId The since commit hash to stream a diff between two arbitrary hashes (optional)
     * @param srcPath The previous path to the file, if the file has been copied, moved or renamed (optional)
     * @param untilId The until commit hash to stream a diff between two arbitrary hashes (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff5(String path, String projectKey, String pullRequestId, String repositorySlug, String contextLines, String sinceId, String srcPath, String untilId, String whitespace, BigDecimal start, BigDecimal limit) throws RestClientException {
        streamRawDiff5WithHttpInfo(path, projectKey, pullRequestId, repositorySlug, contextLines, sinceId, srcPath, untilId, whitespace, start, limit);
    }

    /**
     * Stream raw diff for a pull request
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>400</b> - If the request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - If the pull request does not exist.
     * @param path The path to the file which should be diffed (optional) (required)
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param sinceId The since commit hash to stream a diff between two arbitrary hashes (optional)
     * @param srcPath The previous path to the file, if the file has been copied, moved or renamed (optional)
     * @param untilId The until commit hash to stream a diff between two arbitrary hashes (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff5WithHttpInfo(String path, String projectKey, String pullRequestId, String repositorySlug, String contextLines, String sinceId, String srcPath, String untilId, String whitespace, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamRawDiff5");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff5");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling streamRawDiff5");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff5");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sinceId", sinceId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "untilId", untilId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/diff/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream raw pull request diff
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>400</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param whitespace optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff6(String projectKey, String pullRequestId, String repositorySlug, String contextLines, String whitespace) throws RestClientException {
        streamRawDiff6WithHttpInfo(projectKey, pullRequestId, repositorySlug, contextLines, whitespace);
    }

    /**
     * Stream raw pull request diff
     * Streams the raw diff for a pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - A raw diff for the specified pull request.
     * <p><b>400</b> - The currently authenticated user has insufficient permissions to view the specified pull request.
     * <p><b>404</b> - The pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param whitespace optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff6WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String contextLines, String whitespace) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff6");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling streamRawDiff6");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff6");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        

        final String[] localVarAccepts = { 
            "text/plain", "text/html"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}.diff", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove a reaction from a PR comment
     * Remove an emoticon reaction from a pull request comment
     * <p><b>204</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param emoticon The emoticon to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unReact1(String projectKey, String commentId, String pullRequestId, String emoticon, String repositorySlug) throws RestClientException {
        unReact1WithHttpInfo(projectKey, commentId, pullRequestId, emoticon, repositorySlug);
    }

    /**
     * Remove a reaction from a PR comment
     * Remove an emoticon reaction from a pull request comment
     * <p><b>204</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param emoticon The emoticon to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unReact1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String emoticon, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unReact1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling unReact1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling unReact1");
        }
        
        // verify the required parameter 'emoticon' is set
        if (emoticon == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'emoticon' when calling unReact1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unReact1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("emoticon", emoticon);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/reactions/{emoticon}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Unassign pull request participant
     * Unassigns a participant from the REVIEWER role they may have been given in a pull request.   If the participant has no explicit role this method has no effect.   Afterwards, the user will still remain a participant in the pull request but their role will be reduced to PARTICIPANT. This is because once made a participant of a pull request, a user will forever remain a participant. Only their role may be altered.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The update completed.
     * <p><b>400</b> - The request does not have the username.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Removing reviewers isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param userSlug The slug for the user being unassigned (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unassignParticipantRole(String projectKey, String userSlug, String pullRequestId, String repositorySlug) throws RestClientException {
        unassignParticipantRoleWithHttpInfo(projectKey, userSlug, pullRequestId, repositorySlug);
    }

    /**
     * Unassign pull request participant
     * Unassigns a participant from the REVIEWER role they may have been given in a pull request.   If the participant has no explicit role this method has no effect.   Afterwards, the user will still remain a participant in the pull request but their role will be reduced to PARTICIPANT. This is because once made a participant of a pull request, a user will forever remain a participant. Only their role may be altered.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The update completed.
     * <p><b>400</b> - The request does not have the username.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Removing reviewers isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param userSlug The slug for the user being unassigned (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unassignParticipantRoleWithHttpInfo(String projectKey, String userSlug, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unassignParticipantRole");
        }
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling unassignParticipantRole");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling unassignParticipantRole");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unassignParticipantRole");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("userSlug", userSlug);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Unassign pull request participant
     * Unassigns a participant from the REVIEWER role they may have been given in a pull request.   If the participant has no explicit role this method has no effect.   Afterwards, the user will still remain a participant in the pull request but their role will be reduced to PARTICIPANT. This is because once made a participant of a pull request, a user will forever remain a participant. Only their role may be altered.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead.
     * <p><b>204</b> - The update completed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Removing reviewers isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param username  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void unassignParticipantRole1(String projectKey, String pullRequestId, String repositorySlug, String username) throws RestClientException {
        unassignParticipantRole1WithHttpInfo(projectKey, pullRequestId, repositorySlug, username);
    }

    /**
     * Unassign pull request participant
     * Unassigns a participant from the REVIEWER role they may have been given in a pull request.   If the participant has no explicit role this method has no effect.   Afterwards, the user will still remain a participant in the pull request but their role will be reduced to PARTICIPANT. This is because once made a participant of a pull request, a user will forever remain a participant. Only their role may be altered.   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead.
     * <p><b>204</b> - The update completed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - Removing reviewers isn&#39;t supported on archived repositories.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param username  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> unassignParticipantRole1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, String username) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unassignParticipantRole1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling unassignParticipantRole1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unassignParticipantRole1");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "username", username));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stop watching pull request
     * Remove the authenticated user as a watcher for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The user is no longer watching the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unwatch1(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        unwatch1WithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Stop watching pull request
     * Remove the authenticated user as a watcher for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The user is no longer watching the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unwatch1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unwatch1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling unwatch1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unwatch1");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/watch", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update pull request metadata
     * Update the title, description, reviewers or destination branch of an existing pull request.   **Note:** the &lt;em&gt;reviewers&lt;/em&gt; list may be updated using this resource. However the &lt;em&gt;author&lt;/em&gt; and &lt;em&gt;participants&lt;/em&gt; list may not.   The authenticated user must either:   - be the author of the pull request and have the &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets; or - have the &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets   to call this resource.
     * <p><b>200</b> - The updated pull request.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):   - The request tried to modify the &lt;em&gt;author&lt;/em&gt; or &lt;em&gt;participants&lt;/em&gt;. - The pull request&#39;s version attribute was not specified. - A reviewer&#39;s username was not specified. - The toRef ID value was incorrectly left blank 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the specified pull request.
     * <p><b>404</b> - One of the specified repositories or branches does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The specified version is out of date. - One of the reviewers could not be added to the pull request. - If updating the destination branch:    - There is already an open pull request with an identical to branch    - The from and new to branch &lt;i&gt;are&lt;/i&gt; the same    - The new destination branch up-to-date is up-to-date with all of                 changes from the from branch, resulting in a pull request with                 nothing to merge             - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequest The updated pull request (optional)
     * @return RestPullRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequest update3(String projectKey, String pullRequestId, String repositorySlug, RestPullRequest restPullRequest) throws RestClientException {
        return update3WithHttpInfo(projectKey, pullRequestId, repositorySlug, restPullRequest).getBody();
    }

    /**
     * Update pull request metadata
     * Update the title, description, reviewers or destination branch of an existing pull request.   **Note:** the &lt;em&gt;reviewers&lt;/em&gt; list may be updated using this resource. However the &lt;em&gt;author&lt;/em&gt; and &lt;em&gt;participants&lt;/em&gt; list may not.   The authenticated user must either:   - be the author of the pull request and have the &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets; or - have the &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the repository that this pull request targets   to call this resource.
     * <p><b>200</b> - The updated pull request.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):   - The request tried to modify the &lt;em&gt;author&lt;/em&gt; or &lt;em&gt;participants&lt;/em&gt;. - The pull request&#39;s version attribute was not specified. - A reviewer&#39;s username was not specified. - The toRef ID value was incorrectly left blank 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the specified pull request.
     * <p><b>404</b> - One of the specified repositories or branches does not exist.
     * <p><b>409</b> - One of the following error cases occurred (check the error message for more details):   - The specified version is out of date. - One of the reviewers could not be added to the pull request. - If updating the destination branch:    - There is already an open pull request with an identical to branch    - The from and new to branch &lt;i&gt;are&lt;/i&gt; the same    - The new destination branch up-to-date is up-to-date with all of                 changes from the from branch, resulting in a pull request with                 nothing to merge             - The &lt;em&gt;to&lt;/em&gt; repository is archived. 
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequest The updated pull request (optional)
     * @return ResponseEntity&lt;RestPullRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequest> update3WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug, RestPullRequest restPullRequest) throws RestClientException {
        Object localVarPostBody = restPullRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling update3");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling update3");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling update3");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequest> localReturnType = new ParameterizedTypeReference<RestPullRequest>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update reviewer group attributes
     * Update the attributes of a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of changes.
     * <p><b>400</b> - The updated attribute does not meet the requirements. E.g. the name exceeds 50 characters, setting name to blank.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * <p><b>409</b> - The new updated name already exists.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be updated (required)
     * @param restReviewerGroup The request containing the attributes of the reviewer group to be updated. Only the attributes to be updated need to be present in this object. (optional)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup update4(String projectKey, String id, RestReviewerGroup restReviewerGroup) throws RestClientException {
        return update4WithHttpInfo(projectKey, id, restReviewerGroup).getBody();
    }

    /**
     * Update reviewer group attributes
     * Update the attributes of a reviewer group.  The authenticated user must have &lt;b&gt;PROJECT_READ&lt;/b&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of changes.
     * <p><b>400</b> - The updated attribute does not meet the requirements. E.g. the name exceeds 50 characters, setting name to blank.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The project scope supplied does not exist.
     * <p><b>409</b> - The new updated name already exists.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be updated (required)
     * @param restReviewerGroup The request containing the attributes of the reviewer group to be updated. Only the attributes to be updated need to be present in this object. (optional)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> update4WithHttpInfo(String projectKey, String id, RestReviewerGroup restReviewerGroup) throws RestClientException {
        Object localVarPostBody = restReviewerGroup;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling update4");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling update4");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/reviewer-groups/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update reviewer group attributes
     * Update the attributes of a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The updated reviewer group.
     * <p><b>400</b> - The updated attribute does not meet the requirements. E.g. the name exceeds 50 characters, setting name to blank.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * <p><b>409</b> - The new updated name already exists.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be updated (required)
     * @param repositorySlug The repository slug. (required)
     * @param restReviewerGroup The request containing the attributes of the reviewer group to be updated. Only the attributes to be updated need to be present in this object. (optional)
     * @return RestReviewerGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestReviewerGroup update5(String projectKey, String id, String repositorySlug, RestReviewerGroup restReviewerGroup) throws RestClientException {
        return update5WithHttpInfo(projectKey, id, repositorySlug, restReviewerGroup).getBody();
    }

    /**
     * Update reviewer group attributes
     * Update the attributes of a reviewer group.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The updated reviewer group.
     * <p><b>400</b> - The updated attribute does not meet the requirements. E.g. the name exceeds 50 characters, setting name to blank.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository scope supplied does not exist.
     * <p><b>409</b> - The new updated name already exists.
     * @param projectKey The project key. (required)
     * @param id The ID of the reviewer group to be updated (required)
     * @param repositorySlug The repository slug. (required)
     * @param restReviewerGroup The request containing the attributes of the reviewer group to be updated. Only the attributes to be updated need to be present in this object. (optional)
     * @return ResponseEntity&lt;RestReviewerGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestReviewerGroup> update5WithHttpInfo(String projectKey, String id, String repositorySlug, RestReviewerGroup restReviewerGroup) throws RestClientException {
        Object localVarPostBody = restReviewerGroup;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling update5");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling update5");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling update5");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestReviewerGroup> localReturnType = new ParameterizedTypeReference<RestReviewerGroup>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/reviewer-groups/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update pull request comment
     * Update a comment, with the following restrictions:   - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment, the author of the pull request or repository admins and above may update   the other fields of a comment   Convert a comment to a task or vice versa.   Comments can be converted to tasks by setting the &#39;severity&#39; attribute to &#39;BLOCKER&#39;:  &#x60;&#x60;&#x60;  {  \&quot;severity\&quot;: \&quot;BLOCKER\&quot;  }  &#x60;&#x60;&#x60;  Tasks can be converted to comments by setting the &#39;severity&#39; attribute to &#39;NORMAL&#39;: &#x60;&#x60;&#x60;  {  \&quot;severity\&quot;: \&quot;NORMAL\&quot;  }  &#x60;&#x60;&#x60;  Resolve a blocker comment.   Blocker comments can be resolved by setting the &#39;state&#39; attribute to &#39;RESOLVED&#39;: &#x60;&#x60;&#x60;  {  \&quot;state\&quot;: \&quot;RESOLVED\&quot;  }  &#x60;&#x60;&#x60;  &lt;strong&gt;Note:&lt;/strong&gt; the supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add. (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment updateComment1(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        return updateComment1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, restComment).getBody();
    }

    /**
     * Update pull request comment
     * Update a comment, with the following restrictions:   - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment, the author of the pull request or repository admins and above may update   the other fields of a comment   Convert a comment to a task or vice versa.   Comments can be converted to tasks by setting the &#39;severity&#39; attribute to &#39;BLOCKER&#39;:  &#x60;&#x60;&#x60;  {  \&quot;severity\&quot;: \&quot;BLOCKER\&quot;  }  &#x60;&#x60;&#x60;  Tasks can be converted to comments by setting the &#39;severity&#39; attribute to &#39;NORMAL&#39;: &#x60;&#x60;&#x60;  {  \&quot;severity\&quot;: \&quot;NORMAL\&quot;  }  &#x60;&#x60;&#x60;  Resolve a blocker comment.   Blocker comments can be resolved by setting the &#39;state&#39; attribute to &#39;RESOLVED&#39;: &#x60;&#x60;&#x60;  {  \&quot;state\&quot;: \&quot;RESOLVED\&quot;  }  &#x60;&#x60;&#x60;  &lt;strong&gt;Note:&lt;/strong&gt; the supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The comment to add. (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> updateComment1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateComment1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling updateComment1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling updateComment1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateComment1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments/{commentId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update pull request comment
     * Update a comment, with the following restrictions:   - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment, the author of the pull request or repository admins and above may update the other fields of a comment &lt;/ul&gt;   Convert a comment to a task or vice versa.   Comments can be converted to tasks by setting the &#39;severity&#39; attribute to &#39;BLOCKER&#39;:   &lt;pre&gt; {   \&quot;severity\&quot;: \&quot;BLOCKER\&quot;   }   &lt;/pre&gt;  Tasks can be converted to comments by setting the &#39;severity&#39; attribute to &#39;NORMAL&#39;:  &lt;pre&gt; {   \&quot;severity\&quot;: \&quot;NORMAL\&quot;   }   &lt;/pre&gt;  Resolve a task.   Tasks can be resolved by setting the &#39;state&#39; attribute to &#39;RESOLVED&#39;:  &lt;pre&gt; {   \&quot;state\&quot;: \&quot;RESOLVED\&quot;   }   &lt;/pre&gt;  &lt;strong&gt;Note:&lt;/strong&gt; the supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The updated comment (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment updateComment2(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        return updateComment2WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, restComment).getBody();
    }

    /**
     * Update pull request comment
     * Update a comment, with the following restrictions:   - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment, the author of the pull request or repository admins and above may update the other fields of a comment &lt;/ul&gt;   Convert a comment to a task or vice versa.   Comments can be converted to tasks by setting the &#39;severity&#39; attribute to &#39;BLOCKER&#39;:   &lt;pre&gt; {   \&quot;severity\&quot;: \&quot;BLOCKER\&quot;   }   &lt;/pre&gt;  Tasks can be converted to comments by setting the &#39;severity&#39; attribute to &#39;NORMAL&#39;:  &lt;pre&gt; {   \&quot;severity\&quot;: \&quot;NORMAL\&quot;   }   &lt;/pre&gt;  Resolve a task.   Tasks can be resolved by setting the &#39;state&#39; attribute to &#39;RESOLVED&#39;:  &lt;pre&gt; {   \&quot;state\&quot;: \&quot;RESOLVED\&quot;   }   &lt;/pre&gt;  &lt;strong&gt;Note:&lt;/strong&gt; the supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request, update a comment or watch the pull request.
     * <p><b>404</b> - Unable to find the supplied project, repository, pull request or comment.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key. (required)
     * @param commentId The ID of the comment to retrieve. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restComment The updated comment (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> updateComment2WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateComment2");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling updateComment2");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling updateComment2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateComment2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update the default reviewer
     * Update the default reviewer pull request condition for the given ID.
     * <p><b>200</b> - The updated default reviewer pull request condition.
     * <p><b>400</b> - The request was malformed
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition. (required)
     * @param restDefaultReviewersRequest The new details for the default reviewer pull request condition. (optional)
     * @return RestPullRequestCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestCondition updatePullRequestCondition(String projectKey, String id, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        return updatePullRequestConditionWithHttpInfo(projectKey, id, restDefaultReviewersRequest).getBody();
    }

    /**
     * Update the default reviewer
     * Update the default reviewer pull request condition for the given ID.
     * <p><b>200</b> - The updated default reviewer pull request condition.
     * <p><b>400</b> - The request was malformed
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition. (required)
     * @param restDefaultReviewersRequest The new details for the default reviewer pull request condition. (optional)
     * @return ResponseEntity&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestCondition> updatePullRequestConditionWithHttpInfo(String projectKey, String id, RestDefaultReviewersRequest restDefaultReviewersRequest) throws RestClientException {
        Object localVarPostBody = restDefaultReviewersRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePullRequestCondition");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updatePullRequestCondition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestCondition> localReturnType = new ParameterizedTypeReference<RestPullRequestCondition>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/condition/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update a default reviewer condition
     * Update the default reviewer pull request condition for the given ID.
     * <p><b>200</b> - The updated default reviewer pull request condition.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition (required)
     * @param repositorySlug The repository slug. (required)
     * @param updatePullRequestCondition1Request  (optional)
     * @return RestPullRequestCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestCondition updatePullRequestCondition1(String projectKey, String id, String repositorySlug, UpdatePullRequestCondition1Request updatePullRequestCondition1Request) throws RestClientException {
        return updatePullRequestCondition1WithHttpInfo(projectKey, id, repositorySlug, updatePullRequestCondition1Request).getBody();
    }

    /**
     * Update a default reviewer condition
     * Update the default reviewer pull request condition for the given ID.
     * <p><b>200</b> - The updated default reviewer pull request condition.
     * <p><b>400</b> - The request was malformed.
     * @param projectKey The project key. (required)
     * @param id The ID of the pull request condition (required)
     * @param repositorySlug The repository slug. (required)
     * @param updatePullRequestCondition1Request  (optional)
     * @return ResponseEntity&lt;RestPullRequestCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestCondition> updatePullRequestCondition1WithHttpInfo(String projectKey, String id, String repositorySlug, UpdatePullRequestCondition1Request updatePullRequestCondition1Request) throws RestClientException {
        Object localVarPostBody = updatePullRequestCondition1Request;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePullRequestCondition1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updatePullRequestCondition1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updatePullRequestCondition1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("id", id);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestCondition> localReturnType = new ParameterizedTypeReference<RestPullRequestCondition>() {};
        return apiClient.invokeAPI("/default-reviewers/latest/projects/{projectKey}/repos/{repositorySlug}/condition/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Change pull request status
     * Change the current user&#39;s status for a pull request. Implicitly adds the user as a participant if they are not already. If the current user is the author, this method will fail.   The possible values for {@code status} are &lt;strong&gt;UNAPPROVED&lt;/strong&gt;, &lt;strong&gt;NEEDS_WORK&lt;/strong&gt;, or &lt;strong&gt;APPROVED&lt;/strong&gt;.   If the new {@code status} is &lt;strong&gt;NEEDS_WORK&lt;/strong&gt; or &lt;strong&gt;APPROVED&lt;/strong&gt; then the {@code lastReviewedCommit} for the participant will be updated to the latest commit of the source branch of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the new participant.
     * <p><b>400</b> - The specified status was invalid or the currently authenticated user is the author of the PR and cannot have its status updated.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not open, or has been updated since the last reviewed commit specified by the request.
     * @param projectKey The project key. (required)
     * @param userSlug The slug for the user changing their status (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestAssignStatusRequest The participant representing the status to set, includes the status of the participant and last reviewed commit. If last reviewed commit is provided, it will be used to update the participant status. The operation will fail if the latest commit of the pull request does not match the provided last reviewed commit. If last reviewed commit is not provided, the latest commit of the pull request will be used for the update by default. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. Note: This parameter is deprecated. Use last reviewed commit in request body instead (optional)
     * @return RestPullRequestParticipant
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestParticipant updateStatus(String projectKey, String userSlug, String pullRequestId, String repositorySlug, RestPullRequestAssignStatusRequest restPullRequestAssignStatusRequest, String version) throws RestClientException {
        return updateStatusWithHttpInfo(projectKey, userSlug, pullRequestId, repositorySlug, restPullRequestAssignStatusRequest, version).getBody();
    }

    /**
     * Change pull request status
     * Change the current user&#39;s status for a pull request. Implicitly adds the user as a participant if they are not already. If the current user is the author, this method will fail.   The possible values for {@code status} are &lt;strong&gt;UNAPPROVED&lt;/strong&gt;, &lt;strong&gt;NEEDS_WORK&lt;/strong&gt;, or &lt;strong&gt;APPROVED&lt;/strong&gt;.   If the new {@code status} is &lt;strong&gt;NEEDS_WORK&lt;/strong&gt; or &lt;strong&gt;APPROVED&lt;/strong&gt; then the {@code lastReviewedCommit} for the participant will be updated to the latest commit of the source branch of the pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>200</b> - Details of the new participant.
     * <p><b>400</b> - The specified status was invalid or the currently authenticated user is the author of the PR and cannot have its status updated.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * <p><b>409</b> - The pull request is not open, or has been updated since the last reviewed commit specified by the request.
     * @param projectKey The project key. (required)
     * @param userSlug The slug for the user changing their status (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @param restPullRequestAssignStatusRequest The participant representing the status to set, includes the status of the participant and last reviewed commit. If last reviewed commit is provided, it will be used to update the participant status. The operation will fail if the latest commit of the pull request does not match the provided last reviewed commit. If last reviewed commit is not provided, the latest commit of the pull request will be used for the update by default. (required)
     * @param version The current version of the pull request. If the server&#39;s version isn&#39;t the same as the specified version the operation will fail. To determine the current version of the pull request it should be fetched from the server prior to this operation. Look for the &#39;version&#39; attribute in the returned JSON structure. Note: This parameter is deprecated. Use last reviewed commit in request body instead (optional)
     * @return ResponseEntity&lt;RestPullRequestParticipant&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestParticipant> updateStatusWithHttpInfo(String projectKey, String userSlug, String pullRequestId, String repositorySlug, RestPullRequestAssignStatusRequest restPullRequestAssignStatusRequest, String version) throws RestClientException {
        Object localVarPostBody = restPullRequestAssignStatusRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateStatus");
        }
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling updateStatus");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling updateStatus");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateStatus");
        }
        
        // verify the required parameter 'restPullRequestAssignStatusRequest' is set
        if (restPullRequestAssignStatusRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restPullRequestAssignStatusRequest' when calling updateStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("userSlug", userSlug);
        uriVariables.put("pullRequestId", pullRequestId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestParticipant> localReturnType = new ParameterizedTypeReference<RestPullRequestParticipant>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Watch pull request
     * Add the authenticated user as a watcher for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The user is now watching the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void watch1(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        watch1WithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Watch pull request
     * Add the authenticated user as a watcher for the specified pull request.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.
     * <p><b>204</b> - The user is now watching the pull request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> watch1WithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling watch1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling watch1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling watch1");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/watch", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Unapprove pull request
     * Remove approval from a pull request as the current user. This does not remove the user as a participant.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead
     * <p><b>200</b> - Details of the updated participant.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist or the current user is not a participant on the pull request.
     * <p><b>409</b> - The pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestPullRequestParticipant
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public RestPullRequestParticipant withdrawApproval(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        return withdrawApprovalWithHttpInfo(projectKey, pullRequestId, repositorySlug).getBody();
    }

    /**
     * Unapprove pull request
     * Remove approval from a pull request as the current user. This does not remove the user as a participant.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that this pull request targets to call this resource.   &lt;strong&gt;Deprecated since 4.2&lt;/strong&gt;. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/participants/{userSlug} instead
     * <p><b>200</b> - Details of the updated participant.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified repository or pull request does not exist or the current user is not a participant on the pull request.
     * <p><b>409</b> - The pull request is not open.
     * @param projectKey The project key. (required)
     * @param pullRequestId The ID of the pull request within the repository (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestPullRequestParticipant&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<RestPullRequestParticipant> withdrawApprovalWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling withdrawApproval");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling withdrawApproval");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling withdrawApproval");
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
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestPullRequestParticipant> localReturnType = new ParameterizedTypeReference<RestPullRequestParticipant>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/approve", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
