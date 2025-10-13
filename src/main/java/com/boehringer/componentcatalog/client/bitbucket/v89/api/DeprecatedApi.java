package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetBuildStatus200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLikers200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GroupPickerContext;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBranch;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildStats;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildStatus;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestParticipant;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserPickerContext;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.DeprecatedApi")
public class DeprecatedApi extends BaseApi {

    public DeprecatedApi() {
        super(new ApiClient());
    }

    @Autowired
    public DeprecatedApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Create build status for commit
     * Associates a build status with a commit.The &lt;code&gt;state&lt;/code&gt;, the &lt;code&gt;key&lt;/code&gt; and the &lt;code&gt;url&lt;/code&gt; fields are mandatory. The &lt;code&gt;name&lt;/code&gt; and&lt;code&gt;description&lt;/code&gt; fields are optional.All fields (mandatory or optional) are limited to 255 characters, except for the &lt;code&gt;url&lt;/code&gt;,which is limited to 450 characters.Supported values for the &lt;code&gt;state&lt;/code&gt; are &lt;code&gt;SUCCESSFUL&lt;/code&gt;, &lt;code&gt;FAILED&lt;/code&gt;and &lt;code&gt;INPROGRESS&lt;/code&gt;.The authenticated user must have &lt;strong&gt;LICENSED&lt;/strong&gt; permission or higher to call this resource.  &lt;strong&gt;Deprecated in 7.14, please use the repository based builds resource instead.&lt;/strong&gt;
     * <p><b>204</b> - An empty response if the build status was successfully stored
     * <p><b>400</b> - An error message if the &lt;code&gt;commitId&lt;/code&gt; is not a full 40-characters SHA1, if the build status has a missing mandatory field or if the fields are too large
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;strong&gt;LICENSED&lt;/strong&gt; permission
     * @param commitId full SHA1 of the commit (required)
     * @param restBuildStatus build status to associate with the commit (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addBuildStatus(String commitId, RestBuildStatus restBuildStatus) throws RestClientException {
        addBuildStatusWithHttpInfo(commitId, restBuildStatus);
    }

    /**
     * Create build status for commit
     * Associates a build status with a commit.The &lt;code&gt;state&lt;/code&gt;, the &lt;code&gt;key&lt;/code&gt; and the &lt;code&gt;url&lt;/code&gt; fields are mandatory. The &lt;code&gt;name&lt;/code&gt; and&lt;code&gt;description&lt;/code&gt; fields are optional.All fields (mandatory or optional) are limited to 255 characters, except for the &lt;code&gt;url&lt;/code&gt;,which is limited to 450 characters.Supported values for the &lt;code&gt;state&lt;/code&gt; are &lt;code&gt;SUCCESSFUL&lt;/code&gt;, &lt;code&gt;FAILED&lt;/code&gt;and &lt;code&gt;INPROGRESS&lt;/code&gt;.The authenticated user must have &lt;strong&gt;LICENSED&lt;/strong&gt; permission or higher to call this resource.  &lt;strong&gt;Deprecated in 7.14, please use the repository based builds resource instead.&lt;/strong&gt;
     * <p><b>204</b> - An empty response if the build status was successfully stored
     * <p><b>400</b> - An error message if the &lt;code&gt;commitId&lt;/code&gt; is not a full 40-characters SHA1, if the build status has a missing mandatory field or if the fields are too large
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;strong&gt;LICENSED&lt;/strong&gt; permission
     * @param commitId full SHA1 of the commit (required)
     * @param restBuildStatus build status to associate with the commit (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addBuildStatusWithHttpInfo(String commitId, RestBuildStatus restBuildStatus) throws RestClientException {
        Object localVarPostBody = restBuildStatus;
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling addBuildStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("commitId", commitId);

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
        return apiClient.invokeAPI("/build-status/latest/commits/{commitId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group. This is very similar to &lt;code&gt;groups/add-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addGroupToUser(GroupPickerContext groupPickerContext) throws RestClientException {
        addGroupToUserWithHttpInfo(groupPickerContext);
    }

    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group. This is very similar to &lt;code&gt;groups/add-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addGroupToUserWithHttpInfo(GroupPickerContext groupPickerContext) throws RestClientException {
        Object localVarPostBody = groupPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/add-group", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addUserToGroup(UserPickerContext userPickerContext) throws RestClientException {
        addUserToGroupWithHttpInfo(userPickerContext);
    }

    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addUserToGroupWithHttpInfo(UserPickerContext userPickerContext) throws RestClientException {
        Object localVarPostBody = userPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/groups/add-user", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Get pull request task count
     * Retrieve the total number of OPEN and RESOLVED tasks associated with a pull request.    &lt;strong&gt;Deprecated since 7.2&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments?count&#x3D;true instead.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void countPullRequestTasks(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        countPullRequestTasksWithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Get pull request task count
     * Retrieve the total number of OPEN and RESOLVED tasks associated with a pull request.    &lt;strong&gt;Deprecated since 7.2&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments?count&#x3D;true instead.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> countPullRequestTasksWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling countPullRequestTasks");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling countPullRequestTasks");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling countPullRequestTasks");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/tasks/count", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create task
     * Create a new task.   &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with severity BLOCKER. Call &lt;code&gt;POST /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments&lt;/code&gt; instead,passing the attribute &#39;severity&#39; set to &#39;BLOCKER&#39;.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void createTask() throws RestClientException {
        createTaskWithHttpInfo();
    }

    /**
     * Create task
     * Create a new task.   &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with severity BLOCKER. Call &lt;code&gt;POST /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments&lt;/code&gt; instead,passing the attribute &#39;severity&#39; set to &#39;BLOCKER&#39;.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> createTaskWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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
        return apiClient.invokeAPI("/api/latest/tasks", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete task
     * Delete a task.  &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Call &lt;code&gt;DELETE /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}&lt;/code&gt;instead. @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call DELETE /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} instead.  Note that only the task&#39;s creator, the context&#39;s author or an admin of the context&#39;s repository can delete a task. (For a pull request task, those are the task&#39;s creator, the pull request&#39;s author or an admin on the repository containing the pull request). Additionally a task cannot be deleted if it has already been resolved.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task to delete (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void deleteTask(String taskId) throws RestClientException {
        deleteTaskWithHttpInfo(taskId);
    }

    /**
     * Delete task
     * Delete a task.  &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Call &lt;code&gt;DELETE /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}&lt;/code&gt;instead. @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call DELETE /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} instead.  Note that only the task&#39;s creator, the context&#39;s author or an admin of the context&#39;s repository can delete a task. (For a pull request task, those are the task&#39;s creator, the pull request&#39;s author or an admin on the repository containing the pull request). Additionally a task cannot be deleted if it has already been resolved.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task to delete (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> deleteTaskWithHttpInfo(String taskId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'taskId' when calling deleteTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("taskId", taskId);

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
        return apiClient.invokeAPI("/api/latest/tasks/{taskId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get build statuses for commit
     * Gets build statuses associated with a commit.  &lt;strong&gt;Deprecated in 7.14, please use the repository based builds resource instead.&lt;/strong&gt;
     * <p><b>200</b> - A Page of build statuses associated with the commit &lt;br /&gt; (limited to the most recent 100 build statuses associated with the commit)
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;b&gt;LICENSED&lt;/b&gt; permission.
     * @param commitId Full SHA1 of the commit (ex: &lt;code&gt;e00cf62997a027bbf785614a93e2e55bb331d268&lt;/code&gt;) (required)
     * @param orderBy How the results should be ordered. Options are NEWEST, OLDEST, STATUS (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetBuildStatus200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public GetBuildStatus200Response getBuildStatus(String commitId, String orderBy, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getBuildStatusWithHttpInfo(commitId, orderBy, start, limit).getBody();
    }

    /**
     * Get build statuses for commit
     * Gets build statuses associated with a commit.  &lt;strong&gt;Deprecated in 7.14, please use the repository based builds resource instead.&lt;/strong&gt;
     * <p><b>200</b> - A Page of build statuses associated with the commit &lt;br /&gt; (limited to the most recent 100 build statuses associated with the commit)
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;b&gt;LICENSED&lt;/b&gt; permission.
     * @param commitId Full SHA1 of the commit (ex: &lt;code&gt;e00cf62997a027bbf785614a93e2e55bb331d268&lt;/code&gt;) (required)
     * @param orderBy How the results should be ordered. Options are NEWEST, OLDEST, STATUS (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetBuildStatus200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<GetBuildStatus200Response> getBuildStatusWithHttpInfo(String commitId, String orderBy, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getBuildStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("commitId", commitId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "orderBy", orderBy));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetBuildStatus200Response> localReturnType = new ParameterizedTypeReference<GetBuildStatus200Response>() {};
        return apiClient.invokeAPI("/build-status/latest/commits/{commitId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get build status statistics for commit
     * Gets statistics regarding the builds associated with a commit
     * <p><b>200</b> - The number of successful/failed/in-progress/cancelled/unknown builds for the commit
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;b&gt;LICENSED&lt;/b&gt; permission.
     * @param commitId full SHA1 of the commit (required)
     * @param includeUnique include a unique build result if there is either only one failed build, only one in-progress build or only one successful build (optional)
     * @return RestBuildStats
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBuildStats getBuildStatusStats(String commitId, Boolean includeUnique) throws RestClientException {
        return getBuildStatusStatsWithHttpInfo(commitId, includeUnique).getBody();
    }

    /**
     * Get build status statistics for commit
     * Gets statistics regarding the builds associated with a commit
     * <p><b>200</b> - The number of successful/failed/in-progress/cancelled/unknown builds for the commit
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;b&gt;LICENSED&lt;/b&gt; permission.
     * @param commitId full SHA1 of the commit (required)
     * @param includeUnique include a unique build result if there is either only one failed build, only one in-progress build or only one successful build (optional)
     * @return ResponseEntity&lt;RestBuildStats&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBuildStats> getBuildStatusStatsWithHttpInfo(String commitId, Boolean includeUnique) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getBuildStatusStats");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("commitId", commitId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeUnique", includeUnique));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestBuildStats> localReturnType = new ParameterizedTypeReference<RestBuildStats>() {};
        return apiClient.invokeAPI("/build-status/latest/commits/stats/{commitId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get default branch
     * Retrieves the repository&#39;s default branch, if it has been created. If the repository is empty, 204 No Content will be returned. For non-empty repositories, if the configured default branch has not yet been created a 404 Not Found will be returned.   This URL is deprecated. Callers should use &lt;code&gt;GET /projects/{key}/repos/{slug}/default-branch&lt;/code&gt; instead, which allows retrieving the &lt;i&gt;configured&lt;/i&gt; default branch even if the ref has not been created yet.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The configured default branch for the repository.
     * <p><b>204</b> - The repository is empty, and has no default branch.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist, or its configured default branch does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestBranch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public RestBranch getDefaultBranch1(String projectKey, String repositorySlug) throws RestClientException {
        return getDefaultBranch1WithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get default branch
     * Retrieves the repository&#39;s default branch, if it has been created. If the repository is empty, 204 No Content will be returned. For non-empty repositories, if the configured default branch has not yet been created a 404 Not Found will be returned.   This URL is deprecated. Callers should use &lt;code&gt;GET /projects/{key}/repos/{slug}/default-branch&lt;/code&gt; instead, which allows retrieving the &lt;i&gt;configured&lt;/i&gt; default branch even if the ref has not been created yet.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The configured default branch for the repository.
     * <p><b>204</b> - The repository is empty, and has no default branch.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist, or its configured default branch does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestBranch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<RestBranch> getDefaultBranch1WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getDefaultBranch1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getDefaultBranch1");
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

        ParameterizedTypeReference<RestBranch> localReturnType = new ParameterizedTypeReference<RestBranch>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/branches/default", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get comment likes
     * Get a page of users who liked a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the **REPO_READ** (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt;
     * <p><b>200</b> - Page of users who liked the specified comment
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;) to query the comment likes
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public GetLikers200Response getLikers(String projectKey, Long commentId, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getLikersWithHttpInfo(projectKey, commentId, commitId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get comment likes
     * Get a page of users who liked a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the **REPO_READ** (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt;
     * <p><b>200</b> - Page of users who liked the specified comment
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;) to query the comment likes
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<GetLikers200Response> getLikersWithHttpInfo(String projectKey, Long commentId, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getLikers");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling getLikers");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getLikers");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getLikers");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
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

        ParameterizedTypeReference<GetLikers200Response> localReturnType = new ParameterizedTypeReference<GetLikers200Response>() {};
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}/likes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request comment likes
     * Get a page of users who liked a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.   The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.**
     * <p><b>200</b> - Page of users who liked the specified comment
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;) to query the comment likes
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public GetLikers200Response getLikers1(String projectKey, String commentId, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getLikers1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get pull request comment likes
     * Get a page of users who liked a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.   The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.**
     * <p><b>200</b> - Page of users who liked the specified comment
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;) to query the comment likes
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<GetLikers200Response> getLikers1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getLikers1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling getLikers1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getLikers1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getLikers1");
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
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/likes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get build status statistics for multiple commits
     * Produces a list of the build statistics for multiple commits. Commits &lt;em&gt;without any builds associated with them&lt;/em&gt; will not be returned.&lt;br&gt; For example if the commit &lt;code&gt;e00cf62997a027bbf785614a93e2e55bb331d268&lt;/code&gt; does not have any build statuses associated with it, it will not be present in the response.
     * <p><b>200</b> - The number of successful/failed/in-progress/cancelled/unknown builds for each commit (with the caveat that the commits &lt;em&gt;without any builds associated with them&lt;/em&gt; will not be present in the response)
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;strong&gt;LICENSED&lt;/strong&gt; permission
     * @param requestBody full SHA1 of each commit (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getMultipleBuildStatusStats(List<String> requestBody) throws RestClientException {
        return getMultipleBuildStatusStatsWithHttpInfo(requestBody).getBody();
    }

    /**
     * Get build status statistics for multiple commits
     * Produces a list of the build statistics for multiple commits. Commits &lt;em&gt;without any builds associated with them&lt;/em&gt; will not be returned.&lt;br&gt; For example if the commit &lt;code&gt;e00cf62997a027bbf785614a93e2e55bb331d268&lt;/code&gt; does not have any build statuses associated with it, it will not be present in the response.
     * <p><b>200</b> - The number of successful/failed/in-progress/cancelled/unknown builds for each commit (with the caveat that the commits &lt;em&gt;without any builds associated with them&lt;/em&gt; will not be present in the response)
     * <p><b>401</b> - The user is not authenticated or does not have the &lt;strong&gt;LICENSED&lt;/strong&gt; permission
     * @param requestBody full SHA1 of each commit (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getMultipleBuildStatusStatsWithHttpInfo(List<String> requestBody) throws RestClientException {
        Object localVarPostBody = requestBody;
        

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

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/build-status/latest/commits/stats", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request tasks
     * Retrieve the tasks associated with a pull request.   **Removed in 8.0**. Tasks are now managed using Comments with BLOCKER severity. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments instead   **Deprecated since 7.2, changed to 404 in 8.0, remove in 9.0.** Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments instead
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void getPullRequestTasks(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        getPullRequestTasksWithHttpInfo(projectKey, pullRequestId, repositorySlug);
    }

    /**
     * Get pull request tasks
     * Retrieve the tasks associated with a pull request.   **Removed in 8.0**. Tasks are now managed using Comments with BLOCKER severity. Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments instead   **Deprecated since 7.2, changed to 404 in 8.0, remove in 9.0.** Use /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/blocker-comments instead
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param projectKey The project key. (required)
     * @param pullRequestId The pull request ID. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> getPullRequestTasksWithHttpInfo(String projectKey, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestTasks");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling getPullRequestTasks");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequestTasks");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/tasks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get task
     * Retrieve an existing task.  &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Call &lt;code&gt;GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt;instead. @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} instead.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void getTask(String taskId) throws RestClientException {
        getTaskWithHttpInfo(taskId);
    }

    /**
     * Get task
     * Retrieve an existing task.  &lt;strong&gt;Removed in 8.0&lt;/strong&gt;. Tasks are now managed using Comments with BLOCKER severity. Call &lt;code&gt;GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt;instead. @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} instead.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> getTaskWithHttpInfo(String taskId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'taskId' when calling getTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("taskId", taskId);

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
        return apiClient.invokeAPI("/api/latest/tasks/{taskId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Like a commit comment
     * Like a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt; Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void like(String projectKey, Long commentId, String commitId, String repositorySlug) throws RestClientException {
        likeWithHttpInfo(projectKey, commentId, commitId, repositorySlug);
    }

    /**
     * Like a commit comment
     * Like a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt; Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> likeWithHttpInfo(String projectKey, Long commentId, String commitId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling like");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling like");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling like");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling like");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("commitId", commitId);
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
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}/likes", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Like a pull request comment
     * Like a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;. The like will be recorded against the requesting user.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.** Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>401</b> - he currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;)
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void like1(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        like1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug);
    }

    /**
     * Like a pull request comment
     * Like a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;. The like will be recorded against the requesting user.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.** Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>401</b> - he currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;)
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> like1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling like1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling like1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling like1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling like1");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/likes", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * On disable of mirror addon
     * This REST endpoint is retained for backwards compatibility only. It is a no-op. Starting from 4.6.0, mirrors no longer specify a disabled lifecycle callback in their addon descriptor. Prior to 4.6.0, this was the callback method that was called when the mirroring atlassian-connect add-on has been disabled in the upstream server identified by &lt;code&gt; upstreamId&lt;/code&gt;.
     * <p><b>204</b> - An empty response indicating that callback has been processed
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void onAddonDisabled(String upstreamId) throws RestClientException {
        onAddonDisabledWithHttpInfo(upstreamId);
    }

    /**
     * On disable of mirror addon
     * This REST endpoint is retained for backwards compatibility only. It is a no-op. Starting from 4.6.0, mirrors no longer specify a disabled lifecycle callback in their addon descriptor. Prior to 4.6.0, this was the callback method that was called when the mirroring atlassian-connect add-on has been disabled in the upstream server identified by &lt;code&gt; upstreamId&lt;/code&gt;.
     * <p><b>204</b> - An empty response indicating that callback has been processed
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> onAddonDisabledWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling onAddonDisabled");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/addon/disabled", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * On enabled of mirror addon
     * This REST endpoint is retained for backwards compatibility only. It is a no-op. Starting from 4.6.0, mirrors no longer specify an enabled lifecycle callback in their addon descriptor. Prior to 4.6.0, this was the callback method that was called when the mirroring atlassian-connect add-on has been enabled in the upstream server identified by &lt;code&gt;upstreamId&lt;/code&gt;.
     * <p><b>204</b> - An empty response indicating that callback has been processed
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void onAddonEnabled(String upstreamId) throws RestClientException {
        onAddonEnabledWithHttpInfo(upstreamId);
    }

    /**
     * On enabled of mirror addon
     * This REST endpoint is retained for backwards compatibility only. It is a no-op. Starting from 4.6.0, mirrors no longer specify an enabled lifecycle callback in their addon descriptor. Prior to 4.6.0, this was the callback method that was called when the mirroring atlassian-connect add-on has been enabled in the upstream server identified by &lt;code&gt;upstreamId&lt;/code&gt;.
     * <p><b>204</b> - An empty response indicating that callback has been processed
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> onAddonEnabledWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling onAddonEnabled");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/addon/enabled", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove user from group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/remove-groups instead.  Remove a user from a group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void removeUserFromGroup(UserPickerContext userPickerContext) throws RestClientException {
        removeUserFromGroupWithHttpInfo(userPickerContext);
    }

    /**
     * Remove user from group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/remove-groups instead.  Remove a user from a group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> removeUserFromGroupWithHttpInfo(UserPickerContext userPickerContext) throws RestClientException {
        Object localVarPostBody = userPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/groups/remove-user", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update default branch
     * Update the default branch of a repository.   This URL is deprecated. Callers should use &lt;code&gt;PUT /projects/{key}/repos/{slug}/default-branch&lt;/code&gt; instead.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranch The branch to set as default (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void setDefaultBranch1(String projectKey, String repositorySlug, RestBranch restBranch) throws RestClientException {
        setDefaultBranch1WithHttpInfo(projectKey, repositorySlug, restBranch);
    }

    /**
     * Update default branch
     * Update the default branch of a repository.   This URL is deprecated. Callers should use &lt;code&gt;PUT /projects/{key}/repos/{slug}/default-branch&lt;/code&gt; instead.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The operation was successful.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranch The branch to set as default (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> setDefaultBranch1WithHttpInfo(String projectKey, String repositorySlug, RestBranch restBranch) throws RestClientException {
        Object localVarPostBody = restBranch;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setDefaultBranch1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setDefaultBranch1");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/branches/default", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get upstream settings
     * This method is no longer supported
     * <p><b>410</b> - this operation is no longer supported
     * @param upstreamRepoId  (required)
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @param level the level of synchronization to perform (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void synchronizeRepositoryWithUpstream(String upstreamRepoId, String upstreamId, String level) throws RestClientException {
        synchronizeRepositoryWithUpstreamWithHttpInfo(upstreamRepoId, upstreamId, level);
    }

    /**
     * Get upstream settings
     * This method is no longer supported
     * <p><b>410</b> - this operation is no longer supported
     * @param upstreamRepoId  (required)
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @param level the level of synchronization to perform (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> synchronizeRepositoryWithUpstreamWithHttpInfo(String upstreamRepoId, String upstreamId, String level) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamRepoId' is set
        if (upstreamRepoId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamRepoId' when calling synchronizeRepositoryWithUpstream");
        }
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling synchronizeRepositoryWithUpstream");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamRepoId", upstreamRepoId);
        uriVariables.put("upstreamId", upstreamId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "level", level));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/repos/{upstreamRepoId}/synchronization", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Change upstream settings
     * This method is no longer supported
     * <p><b>410</b> - this operation is no longer supported
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @param level  (optional, default to DEFAULT)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void synchronizeWithUpstream(String upstreamId, String level) throws RestClientException {
        synchronizeWithUpstreamWithHttpInfo(upstreamId, level);
    }

    /**
     * Change upstream settings
     * This method is no longer supported
     * <p><b>410</b> - this operation is no longer supported
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @param level  (optional, default to DEFAULT)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> synchronizeWithUpstreamWithHttpInfo(String upstreamId, String level) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling synchronizeWithUpstream");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "level", level));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/synchronization", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Unlike a commit comment
     * Unlike a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt; Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void unlike(String projectKey, Long commentId, String commitId, String repositorySlug) throws RestClientException {
        unlikeWithHttpInfo(projectKey, commentId, commitId, repositorySlug);
    }

    /**
     * Unlike a commit comment
     * Unlike a commit comment in the specified repository, identified by &lt;code&gt;commitId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  &lt;strong&gt;Deprecated in 8.0 to be removed in 9.0.&lt;/strong&gt; Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission
     * <p><b>404</b> - The specified repository, commit or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId  (required)
     * @param commitId The commit id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> unlikeWithHttpInfo(String projectKey, Long commentId, String commitId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unlike");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling unlike");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling unlike");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unlike");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("commitId", commitId);
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
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}/likes", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Unlike a pull request comment
     * Unlike a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.** Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;)
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void unlike1(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        unlike1WithHttpInfo(projectKey, commentId, pullRequestId, repositorySlug);
    }

    /**
     * Unlike a pull request comment
     * Unlike a pull request comment in the specified repository, identified by &lt;code&gt;pullRequestId&lt;/code&gt; and &lt;code&gt;commentId&lt;/code&gt;.  The authenticated user must have the &lt;strong&gt;REPO_READ&lt;/strong&gt; (or higher) permission for the specified repository to access this resource.  **Deprecated in 8.0 to be removed in 9.0.** Likes have been replaced with reactions. For backwards compatibility, the &lt;pre&gt;thumbsup&lt;/pre&gt; reaction is treated as a like.
     * <p><b>204</b> - No content response indicating that the request succeeded
     * <p><b>400</b> - The currently authenticated user is the comment author
     * <p><b>401</b> - The currently authenticated user does not have sufficient permission (&lt;code&gt;REPO_READ&lt;/code&gt;)
     * <p><b>404</b> - The specified repository, pull request or comment does not exist
     * @param projectKey The project key. (required)
     * @param commentId The comment id. (required)
     * @param pullRequestId The pull request id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> unlike1WithHttpInfo(String projectKey, String commentId, String pullRequestId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unlike1");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling unlike1");
        }
        
        // verify the required parameter 'pullRequestId' is set
        if (pullRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pullRequestId' when calling unlike1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unlike1");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId}/likes", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update task
     * Update an existing task.     &lt;strong&gt;Removed in 8.0&lt;/strong&gt;.  Tasks are now managed using Comments with BLOCKER severity.  Call &lt;code&gt;PUT /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt; instead.  To resolve a task, pass the attribute &#39;state&#39; set to &#39;RESOLVED&#39;.  @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call &lt;code&gt;PUT  /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt; instead.    As of Stash 3.3, only the state and text of a task can be updated.    Updating the state of a task is allowed for any user having &lt;em&gt;READ&lt;/em&gt; access to the repository.  However only the task&#39;s creator, the context&#39;s author or an admin of the context&#39;s repository can update the task&#39;s text. (For a pull request task, those are the task&#39;s creator, the pull request&#39;s author or an admin on the repository containing the pull request). Additionally the task&#39;s text cannot be updated if it has been resolved.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task to update (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void updateTask(String taskId) throws RestClientException {
        updateTaskWithHttpInfo(taskId);
    }

    /**
     * Update task
     * Update an existing task.     &lt;strong&gt;Removed in 8.0&lt;/strong&gt;.  Tasks are now managed using Comments with BLOCKER severity.  Call &lt;code&gt;PUT /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt; instead.  To resolve a task, pass the attribute &#39;state&#39; set to &#39;RESOLVED&#39;.  @deprecated since 7.2, changed to 404 in 8.0, remove in 9.0. Call &lt;code&gt;PUT  /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments/{commentId} &lt;/code&gt; instead.    As of Stash 3.3, only the state and text of a task can be updated.    Updating the state of a task is allowed for any user having &lt;em&gt;READ&lt;/em&gt; access to the repository.  However only the task&#39;s creator, the context&#39;s author or an admin of the context&#39;s repository can update the task&#39;s text. (For a pull request task, those are the task&#39;s creator, the pull request&#39;s author or an admin on the repository containing the pull request). Additionally the task&#39;s text cannot be updated if it has been resolved.
     * <p><b>404</b> - This endpoint has been removed as tasks are now managed using Comments with severity BLOCKER.
     * @param taskId the id identifying the task to update (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> updateTaskWithHttpInfo(String taskId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'taskId' when calling updateTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("taskId", taskId);

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
        return apiClient.invokeAPI("/api/latest/tasks/{taskId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
