package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExampleFiles;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExampleSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.FindByCommit200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetBranches200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetChanges1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetComments200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetCommits200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetConfigurations200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetContent1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRefChangeActivity200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepositoriesRecentlyAccessed200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepositoryHooks1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRestrictions1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetTags200Response;
import org.springframework.core.io.Resource;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAttachmentMetadata;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAutoDeclineSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAutoDeclineSettingsRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBranch;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBranchCreateRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBranchDeleteRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestComment;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCommit;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCreateBranchRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestCreateTagRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDetailedInvocation;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestGitTagCreateRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestHookScriptConfig;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestHookScriptTriggers;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestLabel;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRefRestriction;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRefSyncRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRefSyncStatus;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRejectedRef;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepository;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryContents;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryHook;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryPullRequestSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRestrictionRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestTag;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUserReaction;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestWebhook;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestWebhookCredentials;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.StreamDiff200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.StreamFiles200Response;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.RepositoryApi")
public class RepositoryApi extends BaseApi {

    public RepositoryApi() {
        super(new ApiClient());
    }

    @Autowired
    public RepositoryApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Add repository label
     * Applies a label to the repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>200</b> - The applied label.
     * <p><b>400</b> - A validation error prevented the label from being created or applied. Possible validation errors include: The name of the label contains uppercase characters, the name is smaller than 3 characters or longer than 50 characters, the label contains other characters than a-z 0-9 and - or the label is already applied to the given repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to apply a label.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restLabel The label to apply (optional)
     * @return RestLabel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestLabel addLabel(String projectKey, String repositorySlug, RestLabel restLabel) throws RestClientException {
        return addLabelWithHttpInfo(projectKey, repositorySlug, restLabel).getBody();
    }

    /**
     * Add repository label
     * Applies a label to the repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>200</b> - The applied label.
     * <p><b>400</b> - A validation error prevented the label from being created or applied. Possible validation errors include: The name of the label contains uppercase characters, the name is smaller than 3 characters or longer than 50 characters, the label contains other characters than a-z 0-9 and - or the label is already applied to the given repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to apply a label.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restLabel The label to apply (optional)
     * @return ResponseEntity&lt;RestLabel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestLabel> addLabelWithHttpInfo(String projectKey, String repositorySlug, RestLabel restLabel) throws RestClientException {
        Object localVarPostBody = restLabel;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling addLabel");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling addLabel");
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

        ParameterizedTypeReference<RestLabel> localReturnType = new ParameterizedTypeReference<RestLabel>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/labels", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create branch
     * Creates a branch using the information provided in the RestCreateBranchRequest request   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The created branch.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to write to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restCreateBranchRequest The request to create a branch containing a &lt;strong&gt;name&lt;/strong&gt;, &lt;strong&gt;startPoint&lt;/strong&gt;, and optionally a &lt;strong&gt;message&lt;/strong&gt; (optional)
     * @return RestBranch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBranch createBranch(String projectKey, String repositorySlug, RestCreateBranchRequest restCreateBranchRequest) throws RestClientException {
        return createBranchWithHttpInfo(projectKey, repositorySlug, restCreateBranchRequest).getBody();
    }

    /**
     * Create branch
     * Creates a branch using the information provided in the RestCreateBranchRequest request   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The created branch.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to write to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restCreateBranchRequest The request to create a branch containing a &lt;strong&gt;name&lt;/strong&gt;, &lt;strong&gt;startPoint&lt;/strong&gt;, and optionally a &lt;strong&gt;message&lt;/strong&gt; (optional)
     * @return ResponseEntity&lt;RestBranch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBranch> createBranchWithHttpInfo(String projectKey, String repositorySlug, RestCreateBranchRequest restCreateBranchRequest) throws RestClientException {
        Object localVarPostBody = restCreateBranchRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createBranch");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createBranch");
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

        ParameterizedTypeReference<RestBranch> localReturnType = new ParameterizedTypeReference<RestBranch>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/branches", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create branch
     *  Creates a branch in the specified repository.   The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource. If branch permissions are set up in the repository, the authenticated user must also have access to the branch name that is to be created.
     * <p><b>201</b> - JSON representation of the newly created branch
     * <p><b>400</b> - The branch was not created because the request was invalid, e.g. the provided ref name already existed in the repository, or was not a valid ref name in the repository
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a branch. This could be due to insufficient repository permissions, or lack of branch permission for the provided ref name
     * <p><b>409</b> - The branch name overlapped with an existing branch
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranchCreateRequest  (required)
     * @return RestBranch
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBranch createBranchByBranchUtils(String projectKey, String repositorySlug, RestBranchCreateRequest restBranchCreateRequest) throws RestClientException {
        return createBranchByBranchUtilsWithHttpInfo(projectKey, repositorySlug, restBranchCreateRequest).getBody();
    }

    /**
     * Create branch
     *  Creates a branch in the specified repository.   The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource. If branch permissions are set up in the repository, the authenticated user must also have access to the branch name that is to be created.
     * <p><b>201</b> - JSON representation of the newly created branch
     * <p><b>400</b> - The branch was not created because the request was invalid, e.g. the provided ref name already existed in the repository, or was not a valid ref name in the repository
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a branch. This could be due to insufficient repository permissions, or lack of branch permission for the provided ref name
     * <p><b>409</b> - The branch name overlapped with an existing branch
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranchCreateRequest  (required)
     * @return ResponseEntity&lt;RestBranch&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBranch> createBranchByBranchUtilsWithHttpInfo(String projectKey, String repositorySlug, RestBranchCreateRequest restBranchCreateRequest) throws RestClientException {
        Object localVarPostBody = restBranchCreateRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createBranchByBranchUtils");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createBranchByBranchUtils");
        }
        
        // verify the required parameter 'restBranchCreateRequest' is set
        if (restBranchCreateRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restBranchCreateRequest' when calling createBranchByBranchUtils");
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

        ParameterizedTypeReference<RestBranch> localReturnType = new ParameterizedTypeReference<RestBranch>() {};
        return apiClient.invokeAPI("/branch-utils/latest/projects/{projectKey}/repos/{repositorySlug}/branches", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add a new commit comment
     * Add a new comment.  Comments can be added in a few places by setting different attributes:  General commit comment:  &#x60;&#x60;&#x60;{       \&quot;text\&quot;: \&quot;An insightful general comment on a commit.\&quot; }  &lt;/pre&gt; Reply to a comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;A measured reply.\&quot;,       \&quot;parent\&quot;: {           \&quot;id\&quot;: 1       } } &lt;/pre&gt; General file comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;An insightful general comment on a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       } } &lt;/pre&gt; File line comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;A pithy comment on a particular line within a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;line\&quot;: 1,           \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,           \&quot;fileType\&quot;: \&quot;FROM\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       } } &#x60;&#x60;&#x60;  Note: general file comments are an experimental feature and may change in the near future!  For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on.  For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:- &#39;ADDED&#39; - for an added line;&lt;/li&gt;- &#39;REMOVED&#39; - for a removed line; or&lt;/li&gt;- &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff.&lt;/li&gt;&#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:- &#39;FROM&#39; - the source file of the diff&lt;/li&gt;- &#39;TO&#39; - the destination file of the diff&lt;/li&gt;If the current user is not a participant the user is added as one and updated to watch the commit.  The authenticated user must have REPO_READ permission for the repository that the commit is in to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the commit, create a comment or watch the commit.
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or parent comment. The missing entity will be specified in the error details.
     * <p><b>409</b> - Adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param since For a merge commit, a parent can be provided to specify which diff the comments should be on. For a commit range, a sinceId can be provided to specify where the comments should be anchored from. (optional)
     * @param restComment the comment (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment createComment(String projectKey, String commitId, String repositorySlug, String since, RestComment restComment) throws RestClientException {
        return createCommentWithHttpInfo(projectKey, commitId, repositorySlug, since, restComment).getBody();
    }

    /**
     * Add a new commit comment
     * Add a new comment.  Comments can be added in a few places by setting different attributes:  General commit comment:  &#x60;&#x60;&#x60;{       \&quot;text\&quot;: \&quot;An insightful general comment on a commit.\&quot; }  &lt;/pre&gt; Reply to a comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;A measured reply.\&quot;,       \&quot;parent\&quot;: {           \&quot;id\&quot;: 1       } } &lt;/pre&gt; General file comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;An insightful general comment on a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       } } &lt;/pre&gt; File line comment: &lt;pre&gt;{       \&quot;text\&quot;: \&quot;A pithy comment on a particular line within a file.\&quot;,       \&quot;anchor\&quot;: {           \&quot;diffType\&quot;: \&quot;COMMIT\&quot;,           \&quot;line\&quot;: 1,           \&quot;lineType\&quot;: \&quot;CONTEXT\&quot;,           \&quot;fileType\&quot;: \&quot;FROM\&quot;,           \&quot;fromHash\&quot;: \&quot;6df3858eeb9a53a911cd17e66a9174d44ffb02cd\&quot;,           \&quot;path\&quot;: \&quot;path/to/file\&quot;,           \&quot;srcPath\&quot;: \&quot;path/to/file\&quot;,           \&quot;toHash\&quot;: \&quot;04c7c5c931b9418ca7b66f51fe934d0bd9b2ba4b\&quot;       } } &#x60;&#x60;&#x60;  Note: general file comments are an experimental feature and may change in the near future!  For file and line comments, &#39;path&#39; refers to the path of the file to which the comment should be applied and &#39;srcPath&#39; refers to the path the that file used to have (only required for copies and moves). Also, fromHash and toHash refer to the sinceId / untilId (respectively) used to produce the diff on which the comment was added. Finally diffType refers to the type of diff the comment was added on.  For line comments, &#39;line&#39; refers to the line in the diff that the comment should apply to. &#39;lineType&#39; refers to the type of diff hunk, which can be:- &#39;ADDED&#39; - for an added line;&lt;/li&gt;- &#39;REMOVED&#39; - for a removed line; or&lt;/li&gt;- &#39;CONTEXT&#39; - for a line that was unmodified but is in the vicinity of the diff.&lt;/li&gt;&#39;fileType&#39; refers to the file of the diff to which the anchor should be attached - which is of relevance when displaying the diff in a side-by-side way. Currently the supported values are:- &#39;FROM&#39; - the source file of the diff&lt;/li&gt;- &#39;TO&#39; - the destination file of the diff&lt;/li&gt;If the current user is not a participant the user is added as one and updated to watch the commit.  The authenticated user must have REPO_READ permission for the repository that the commit is in to call this resource.
     * <p><b>201</b> - The newly created comment.
     * <p><b>400</b> - The comment was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the commit, create a comment or watch the commit.
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or parent comment. The missing entity will be specified in the error details.
     * <p><b>409</b> - Adding, deleting, or editing comments isn&#39;t supported on archived repositories.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param since For a merge commit, a parent can be provided to specify which diff the comments should be on. For a commit range, a sinceId can be provided to specify where the comments should be anchored from. (optional)
     * @param restComment the comment (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> createCommentWithHttpInfo(String projectKey, String commitId, String repositorySlug, String since, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createComment");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling createComment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createComment");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create tag
     * Creates a tag in the specified repository.  The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource.  &#39;LIGHTWEIGHT&#39; and &#39;ANNOTATED&#39; are the two type of tags that can be created. The &#39;startPoint&#39; can either be a ref or a &#39;commit&#39;.
     * <p><b>201</b> - A JSON representation of the newly created tag.
     * <p><b>400</b> - The tag was not created because the request was invalid, e.g. the provided ref name already existed in the repository, or was not a valid ref name in the repository, or the start point is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a tag. This could be due to insufficient repository permissions.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restGitTagCreateRequest The create git tag request. (optional)
     * @return RestTag
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestTag createGitTag(String projectKey, String repositorySlug, RestGitTagCreateRequest restGitTagCreateRequest) throws RestClientException {
        return createGitTagWithHttpInfo(projectKey, repositorySlug, restGitTagCreateRequest).getBody();
    }

    /**
     * Create tag
     * Creates a tag in the specified repository.  The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource.  &#39;LIGHTWEIGHT&#39; and &#39;ANNOTATED&#39; are the two type of tags that can be created. The &#39;startPoint&#39; can either be a ref or a &#39;commit&#39;.
     * <p><b>201</b> - A JSON representation of the newly created tag.
     * <p><b>400</b> - The tag was not created because the request was invalid, e.g. the provided ref name already existed in the repository, or was not a valid ref name in the repository, or the start point is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a tag. This could be due to insufficient repository permissions.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restGitTagCreateRequest The create git tag request. (optional)
     * @return ResponseEntity&lt;RestTag&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestTag> createGitTagWithHttpInfo(String projectKey, String repositorySlug, RestGitTagCreateRequest restGitTagCreateRequest) throws RestClientException {
        Object localVarPostBody = restGitTagCreateRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createGitTag");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createGitTag");
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

        ParameterizedTypeReference<RestTag> localReturnType = new ParameterizedTypeReference<RestTag>() {};
        return apiClient.invokeAPI("/git/latest/projects/{projectKey}/repos/{repositorySlug}/tags", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create multiple ref restrictions
     * Allows creating multiple restrictions at once.
     * <p><b>200</b> - Response contains the ref restriction that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRestrictionRequest The request containing a list of the details of the restrictions to create. (optional)
     * @return RestRefRestriction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefRestriction createRestrictions1(String projectKey, String repositorySlug, List<RestRestrictionRequest> restRestrictionRequest) throws RestClientException {
        return createRestrictions1WithHttpInfo(projectKey, repositorySlug, restRestrictionRequest).getBody();
    }

    /**
     * Create multiple ref restrictions
     * Allows creating multiple restrictions at once.
     * <p><b>200</b> - Response contains the ref restriction that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRestrictionRequest The request containing a list of the details of the restrictions to create. (optional)
     * @return ResponseEntity&lt;RestRefRestriction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefRestriction> createRestrictions1WithHttpInfo(String projectKey, String repositorySlug, List<RestRestrictionRequest> restRestrictionRequest) throws RestClientException {
        Object localVarPostBody = restRestrictionRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createRestrictions1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createRestrictions1");
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
            "application/vnd.atl.bitbucket.bulk+json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestRefRestriction> localReturnType = new ParameterizedTypeReference<RestRefRestriction>() {};
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/repos/{repositorySlug}/restrictions", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create tag
     * Creates a tag using the information provided in the RestCreateTagRequest request   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The created tag.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to write to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restCreateTagRequest The request to create a tag containing a &lt;strong&gt;name&lt;/strong&gt;, &lt;strong&gt;startPoint&lt;/strong&gt;, and optionally a &lt;strong&gt;message&lt;/strong&gt; (optional)
     * @return RestTag
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestTag createTag(String projectKey, String repositorySlug, RestCreateTagRequest restCreateTagRequest) throws RestClientException {
        return createTagWithHttpInfo(projectKey, repositorySlug, restCreateTagRequest).getBody();
    }

    /**
     * Create tag
     * Creates a tag using the information provided in the RestCreateTagRequest request   The authenticated user must have &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The created tag.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to write to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restCreateTagRequest The request to create a tag containing a &lt;strong&gt;name&lt;/strong&gt;, &lt;strong&gt;startPoint&lt;/strong&gt;, and optionally a &lt;strong&gt;message&lt;/strong&gt; (optional)
     * @return ResponseEntity&lt;RestTag&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestTag> createTagWithHttpInfo(String projectKey, String repositorySlug, RestCreateTagRequest restCreateTagRequest) throws RestClientException {
        Object localVarPostBody = restCreateTagRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createTag");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createTag");
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

        ParameterizedTypeReference<RestTag> localReturnType = new ParameterizedTypeReference<RestTag>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/tags", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create webhook
     * Create a webhook for the repository specified via the URL.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A created webhook.
     * <p><b>400</b> - The webhook parameters were invalid or not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create webhooks in the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restWebhook The webhook to be created for this repository. (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook createWebhook1(String projectKey, String repositorySlug, RestWebhook restWebhook) throws RestClientException {
        return createWebhook1WithHttpInfo(projectKey, repositorySlug, restWebhook).getBody();
    }

    /**
     * Create webhook
     * Create a webhook for the repository specified via the URL.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A created webhook.
     * <p><b>400</b> - The webhook parameters were invalid or not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create webhooks in the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restWebhook The webhook to be created for this repository. (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> createWebhook1WithHttpInfo(String projectKey, String repositorySlug, RestWebhook restWebhook) throws RestClientException {
        Object localVarPostBody = restWebhook;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createWebhook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createWebhook1");
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

        ParameterizedTypeReference<RestWebhook> localReturnType = new ParameterizedTypeReference<RestWebhook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete auto decline settings
     * Delete auto decline settings for the supplied repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for this repository to call the resource.
     * <p><b>204</b> - The auto decline settings have been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete1(String projectKey, String repositorySlug) throws RestClientException {
        delete1WithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Delete auto decline settings
     * Delete auto decline settings for the supplied repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for this repository to call the resource.
     * <p><b>204</b> - The auto decline settings have been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete1WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete1");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/auto-decline", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete an attachment
     * Delete an attachment.  The user must be authenticated and have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the attachment
     * <p><b>404</b> - The attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAttachment(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        deleteAttachmentWithHttpInfo(projectKey, attachmentId, repositorySlug);
    }

    /**
     * Delete an attachment
     * Delete an attachment.  The user must be authenticated and have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the attachment
     * <p><b>404</b> - The attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAttachmentWithHttpInfo(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAttachment");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling deleteAttachment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteAttachment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("attachmentId", attachmentId);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/attachments/{attachmentId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete attachment metadata
     * Delete attachment metadata.  The user must be authenticated and have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete theattachment metadata
     * <p><b>404</b> - The attachment or the attachment metadata does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAttachmentMetadata(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        deleteAttachmentMetadataWithHttpInfo(projectKey, attachmentId, repositorySlug);
    }

    /**
     * Delete attachment metadata
     * Delete attachment metadata.  The user must be authenticated and have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete theattachment metadata
     * <p><b>404</b> - The attachment or the attachment metadata does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAttachmentMetadataWithHttpInfo(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAttachmentMetadata");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling deleteAttachmentMetadata");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteAttachmentMetadata");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("attachmentId", attachmentId);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/attachments/{attachmentId}/metadata", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete branch
     *  Deletes a branch in the specified repository.    If the branch does not exist, this operation will not raise an error. In other words after calling this resource  and receiving a 204 response the branch provided in the request is guaranteed to not exist in the specified  repository any more, regardless of its existence beforehand.    The optional &#39;endPoint&#39; parameter of the request may contain a commit ID that the provided ref name is  expected to point to. Should the ref point to a different commit ID, a 400 response will be returned with  appropriate error details.    The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource. If  branch permissions are set up in the repository, the authenticated user must also have access to the branch name  that is to be deleted.
     * <p><b>204</b> - An empty response indicating that the branch no longer exists in the repository
     * <p><b>400</b> - The branch was not deleted because the request was invalid, e.g. no ref name to delete was provided, or the provided ref name points to the default branch in the repository that cannot be deleted
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a branch. This could be due to insufficient repository permissions, or lack of branch permission for the provided ref name.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranchDeleteRequest Branch delete request (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteBranch(String projectKey, String repositorySlug, RestBranchDeleteRequest restBranchDeleteRequest) throws RestClientException {
        deleteBranchWithHttpInfo(projectKey, repositorySlug, restBranchDeleteRequest);
    }

    /**
     * Delete branch
     *  Deletes a branch in the specified repository.    If the branch does not exist, this operation will not raise an error. In other words after calling this resource  and receiving a 204 response the branch provided in the request is guaranteed to not exist in the specified  repository any more, regardless of its existence beforehand.    The optional &#39;endPoint&#39; parameter of the request may contain a commit ID that the provided ref name is  expected to point to. Should the ref point to a different commit ID, a 400 response will be returned with  appropriate error details.    The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource. If  branch permissions are set up in the repository, the authenticated user must also have access to the branch name  that is to be deleted.
     * <p><b>204</b> - An empty response indicating that the branch no longer exists in the repository
     * <p><b>400</b> - The branch was not deleted because the request was invalid, e.g. no ref name to delete was provided, or the provided ref name points to the default branch in the repository that cannot be deleted
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a branch. This could be due to insufficient repository permissions, or lack of branch permission for the provided ref name.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranchDeleteRequest Branch delete request (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteBranchWithHttpInfo(String projectKey, String repositorySlug, RestBranchDeleteRequest restBranchDeleteRequest) throws RestClientException {
        Object localVarPostBody = restBranchDeleteRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteBranch");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteBranch");
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
        return apiClient.invokeAPI("/branch-utils/latest/projects/{projectKey}/repos/{repositorySlug}/branches", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a commit comment
     * Delete a commit comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users. Comments which have replies &lt;i&gt;may not be deleted&lt;/i&gt;, regardless of the user&#39;s granted permissions.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>204</b> - The operation was successful
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or commit. The missing entity will be specified in the error details.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the comment&#39;s current version or the repository is archived.
     * @param projectKey The project key (required)
     * @param commentId the comment (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteComment(String projectKey, String commentId, String commitId, String repositorySlug, String version) throws RestClientException {
        deleteCommentWithHttpInfo(projectKey, commentId, commitId, repositorySlug, version);
    }

    /**
     * Delete a commit comment
     * Delete a commit comment. Anyone can delete their own comment. Only users with &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; and above may delete comments created by other users. Comments which have replies &lt;i&gt;may not be deleted&lt;/i&gt;, regardless of the user&#39;s granted permissions.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>204</b> - The operation was successful
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the comment.
     * <p><b>404</b> - Unable to find the supplied project, repository or commit. The missing entity will be specified in the error details.
     * <p><b>409</b> - The comment has replies, the version supplied does not match the comment&#39;s current version or the repository is archived.
     * @param projectKey The project key (required)
     * @param commentId the comment (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param version The expected version of the comment. This must match the server&#39;s version of the comment or the delete will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the delete. Look for the &#39;version&#39; attribute in the returned JSON structure. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteCommentWithHttpInfo(String projectKey, String commentId, String commitId, String repositorySlug, String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteComment");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling deleteComment");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling deleteComment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteComment");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Delete repository hook configuration for the supplied &lt;strong&gt;hookKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The hook configuration matching the supplied &lt;strong&gt;hookKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; was deleted
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRepositoryHook(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        deleteRepositoryHookWithHttpInfo(projectKey, hookKey, repositorySlug);
    }

    /**
     * 
     * Delete repository hook configuration for the supplied &lt;strong&gt;hookKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The hook configuration matching the supplied &lt;strong&gt;hookKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; was deleted
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRepositoryHookWithHttpInfo(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRepositoryHook");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling deleteRepositoryHook");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteRepositoryHook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a ref restriction
     * Deletes a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>204</b> - An empty response indicating that the operation was successful
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete restrictions on the provided project
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRestriction1(String projectKey, String id, String repositorySlug) throws RestClientException {
        deleteRestriction1WithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete a ref restriction
     * Deletes a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>204</b> - An empty response indicating that the operation was successful
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete restrictions on the provided project
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRestriction1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRestriction1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRestriction1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteRestriction1");
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
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/repos/{repositorySlug}/restrictions/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete tag
     * Deletes a tag in the specified repository.  The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - An empty response indicating that the tag no longer exists in the repository.
     * <p><b>400</b> - The tag was not deleted because repository is either empty, or is not a git repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a tag. This could be due to insufficient repository permissions.
     * <p><b>404</b> - If the tag doesn&#39;t exist in the repository.
     * @param projectKey The project key. (required)
     * @param name The name of the tag to be deleted. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTag(String projectKey, String name, String repositorySlug) throws RestClientException {
        deleteTagWithHttpInfo(projectKey, name, repositorySlug);
    }

    /**
     * Delete tag
     * Deletes a tag in the specified repository.  The authenticated user must have an effective &lt;strong&gt;REPO_WRITE&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - An empty response indicating that the tag no longer exists in the repository.
     * <p><b>400</b> - The tag was not deleted because repository is either empty, or is not a git repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a tag. This could be due to insufficient repository permissions.
     * <p><b>404</b> - If the tag doesn&#39;t exist in the repository.
     * @param projectKey The project key. (required)
     * @param name The name of the tag to be deleted. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTagWithHttpInfo(String projectKey, String name, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteTag");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling deleteTag");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteTag");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("name", name);
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
        return apiClient.invokeAPI("/git/latest/projects/{projectKey}/repos/{repositorySlug}/tags/{name}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete webhook
     * Delete a webhook for the repository specified via the URL.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The webhook for the repository has been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist, or webhook does not exist in this repository.
     * @param projectKey The project key. (required)
     * @param webhookId The ID of the webhook to be deleted. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteWebhook1(String projectKey, String webhookId, String repositorySlug) throws RestClientException {
        deleteWebhook1WithHttpInfo(projectKey, webhookId, repositorySlug);
    }

    /**
     * Delete webhook
     * Delete a webhook for the repository specified via the URL.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The webhook for the repository has been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist, or webhook does not exist in this repository.
     * @param projectKey The project key. (required)
     * @param webhookId The ID of the webhook to be deleted. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteWebhook1WithHttpInfo(String projectKey, String webhookId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteWebhook1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling deleteWebhook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteWebhook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Disable a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to disable the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook disableHook1(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        return disableHook1WithHttpInfo(projectKey, hookKey, repositorySlug).getBody();
    }

    /**
     * 
     * Disable a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to disable the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> disableHook1WithHttpInfo(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling disableHook1");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling disableHook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling disableHook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
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

        ParameterizedTypeReference<RestRepositoryHook> localReturnType = new ParameterizedTypeReference<RestRepositoryHook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/enabled", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit file
     * Update the content of path, on the given repository and branch.   This resource accepts PUT multipart form data, containing the file in a form-field named content.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to update &#39;README.md&#39; would be:  &#x60;&#x60;&#x60;curl -X PUT -u username:password -F content&#x3D;@README.md  -F &#39;message&#x3D;Updated using file-edit REST API&#39; -F branch&#x3D;master -F  sourceCommitId&#x3D;5636641a50b  http://example.com/rest/api/latest/projects/PROJECT_1/repos/repo_1/browse/README.md &#x60;&#x60;&#x60;  - branch:  the branch on which the path should be modified or created - content: the full content of the file at path  - message: the message associated with this change, to be used as the commit message. Or null if the default message should be used. - sourceCommitId: the commit ID of the file before it was edited, used to identify if content has changed. Or null if this is a new file   The file can be updated or created on a new branch. In this case, the sourceBranch parameter should be provided to identify the starting point for the new branch and the branch parameter identifies the branch to create the new commit on.
     * <p><b>200</b> - The newly created commit.
     * <p><b>400</b> - There are validation errors, e.g. The branch or content parameters were not supplied.
     * <p><b>401</b> - The currently authenticated user does not have write permission for the given repository.
     * <p><b>403</b> - The request was authenticated using a project or repository access token, which does not have a valid user associated with it
     * <p><b>404</b> - The repository does not exist.
     * <p><b>409</b> - The file already exists when trying to create a file, or the given content does not modify the file, or the file has changed since the given sourceCommitId, or the repository is archived.
     * @param path The path of the file that is to be modified or created (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param branch The branch on which the &lt;code&gt;path&lt;/code&gt; should be modified or created. (optional)
     * @param content The full content of the file at &lt;code&gt;path&lt;/code&gt;. (optional)
     * @param message The message associated with this change, to be used as the commit message. Or null if the default message should be used. (optional)
     * @param sourceBranch The starting point for &lt;code&gt;branch&lt;/code&gt;. If provided and different from &lt;code&gt;branch&lt;/code&gt;, &lt;code&gt;branch&lt;/code&gt; will be created as a new branch, branching off from &lt;code&gt;sourceBranch&lt;/code&gt;. (optional)
     * @param sourceCommitId The commit ID of the file before it was edited, used to identify if content has changed. Or null if this is a new file (optional)
     * @return RestCommit
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestCommit editFile(String path, String projectKey, String repositorySlug, String branch, String content, String message, String sourceBranch, String sourceCommitId) throws RestClientException {
        return editFileWithHttpInfo(path, projectKey, repositorySlug, branch, content, message, sourceBranch, sourceCommitId).getBody();
    }

    /**
     * Edit file
     * Update the content of path, on the given repository and branch.   This resource accepts PUT multipart form data, containing the file in a form-field named content.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to update &#39;README.md&#39; would be:  &#x60;&#x60;&#x60;curl -X PUT -u username:password -F content&#x3D;@README.md  -F &#39;message&#x3D;Updated using file-edit REST API&#39; -F branch&#x3D;master -F  sourceCommitId&#x3D;5636641a50b  http://example.com/rest/api/latest/projects/PROJECT_1/repos/repo_1/browse/README.md &#x60;&#x60;&#x60;  - branch:  the branch on which the path should be modified or created - content: the full content of the file at path  - message: the message associated with this change, to be used as the commit message. Or null if the default message should be used. - sourceCommitId: the commit ID of the file before it was edited, used to identify if content has changed. Or null if this is a new file   The file can be updated or created on a new branch. In this case, the sourceBranch parameter should be provided to identify the starting point for the new branch and the branch parameter identifies the branch to create the new commit on.
     * <p><b>200</b> - The newly created commit.
     * <p><b>400</b> - There are validation errors, e.g. The branch or content parameters were not supplied.
     * <p><b>401</b> - The currently authenticated user does not have write permission for the given repository.
     * <p><b>403</b> - The request was authenticated using a project or repository access token, which does not have a valid user associated with it
     * <p><b>404</b> - The repository does not exist.
     * <p><b>409</b> - The file already exists when trying to create a file, or the given content does not modify the file, or the file has changed since the given sourceCommitId, or the repository is archived.
     * @param path The path of the file that is to be modified or created (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param branch The branch on which the &lt;code&gt;path&lt;/code&gt; should be modified or created. (optional)
     * @param content The full content of the file at &lt;code&gt;path&lt;/code&gt;. (optional)
     * @param message The message associated with this change, to be used as the commit message. Or null if the default message should be used. (optional)
     * @param sourceBranch The starting point for &lt;code&gt;branch&lt;/code&gt;. If provided and different from &lt;code&gt;branch&lt;/code&gt;, &lt;code&gt;branch&lt;/code&gt; will be created as a new branch, branching off from &lt;code&gt;sourceBranch&lt;/code&gt;. (optional)
     * @param sourceCommitId The commit ID of the file before it was edited, used to identify if content has changed. Or null if this is a new file (optional)
     * @return ResponseEntity&lt;RestCommit&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestCommit> editFileWithHttpInfo(String path, String projectKey, String repositorySlug, String branch, String content, String message, String sourceBranch, String sourceCommitId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling editFile");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling editFile");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling editFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (branch != null)
            localVarFormParams.add("branch", branch);
        if (content != null)
            localVarFormParams.add("content", content);
        if (message != null)
            localVarFormParams.add("message", message);
        if (sourceBranch != null)
            localVarFormParams.add("sourceBranch", sourceBranch);
        if (sourceCommitId != null)
            localVarFormParams.add("sourceCommitId", sourceCommitId);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestCommit> localReturnType = new ParameterizedTypeReference<RestCommit>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/browse/{path}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Enable a repository hook for this repository and optionally apply new configuration.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.   A JSON document may be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contentLength The content length. (optional)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook enableHook1(String projectKey, String hookKey, String repositorySlug, String contentLength) throws RestClientException {
        return enableHook1WithHttpInfo(projectKey, hookKey, repositorySlug, contentLength).getBody();
    }

    /**
     * 
     * Enable a repository hook for this repository and optionally apply new configuration.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.   A JSON document may be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contentLength The content length. (optional)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> enableHook1WithHttpInfo(String projectKey, String hookKey, String repositorySlug, String contentLength) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling enableHook1");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling enableHook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling enableHook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (contentLength != null)
        localVarHeaderParams.add("Content-Length", apiClient.parameterToString(contentLength));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestRepositoryHook> localReturnType = new ParameterizedTypeReference<RestRepositoryHook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/enabled", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get branches with ref change activities for repository
     * Retrieve a page of branches with ref change activities for a specific repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of branches with ref change activities.
     * <p><b>401</b> - The user is currently not authenticated or the user does not have REPO_ADMIN permission.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filterText (optional) Partial match for a ref ID to filter minimal refs for (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindByCommit200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindByCommit200Response findBranches(String projectKey, String repositorySlug, String filterText, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findBranchesWithHttpInfo(projectKey, repositorySlug, filterText, start, limit).getBody();
    }

    /**
     * Get branches with ref change activities for repository
     * Retrieve a page of branches with ref change activities for a specific repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of branches with ref change activities.
     * <p><b>401</b> - The user is currently not authenticated or the user does not have REPO_ADMIN permission.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filterText (optional) Partial match for a ref ID to filter minimal refs for (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindByCommit200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindByCommit200Response> findBranchesWithHttpInfo(String projectKey, String repositorySlug, String filterText, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling findBranches");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling findBranches");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<FindByCommit200Response> localReturnType = new ParameterizedTypeReference<FindByCommit200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/ref-change-activities/branches", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get branch
     * Gets the branch information associated with a single commit from a given repository.
     * <p><b>200</b> - A page of branch refs associated with the commit
     * <p><b>500</b> - The request has timed out processing the branch request
     * @param projectKey The project key. (required)
     * @param commitId  (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindByCommit200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindByCommit200Response findByCommit(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findByCommitWithHttpInfo(projectKey, commitId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get branch
     * Gets the branch information associated with a single commit from a given repository.
     * <p><b>200</b> - A page of branch refs associated with the commit
     * <p><b>500</b> - The request has timed out processing the branch request
     * @param projectKey The project key. (required)
     * @param commitId  (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindByCommit200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindByCommit200Response> findByCommitWithHttpInfo(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling findByCommit");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling findByCommit");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling findByCommit");
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

        ParameterizedTypeReference<FindByCommit200Response> localReturnType = new ParameterizedTypeReference<FindByCommit200Response>() {};
        return apiClient.invokeAPI("/branch-utils/latest/projects/{projectKey}/repos/{repositorySlug}/branches/info/{commitId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find webhooks
     * Find webhooks in this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; IDs to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void findWebhooks1(String projectKey, String repositorySlug, String event, Boolean statistics) throws RestClientException {
        findWebhooks1WithHttpInfo(projectKey, repositorySlug, event, statistics);
    }

    /**
     * Find webhooks
     * Find webhooks in this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; IDs to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> findWebhooks1WithHttpInfo(String projectKey, String repositorySlug, String event, Boolean statistics) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling findWebhooks1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling findWebhooks1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "event", event));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "statistics", statistics));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository labels
     * Get all labels applied to the given repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository.
     * <p><b>200</b> - The applied label.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the labels.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestLabel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestLabel getAllLabelsForRepository(String projectKey, String repositorySlug) throws RestClientException {
        return getAllLabelsForRepositoryWithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get repository labels
     * Get all labels applied to the given repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository.
     * <p><b>200</b> - The applied label.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the labels.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestLabel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestLabel> getAllLabelsForRepositoryWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAllLabelsForRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAllLabelsForRepository");
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

        ParameterizedTypeReference<RestLabel> localReturnType = new ParameterizedTypeReference<RestLabel>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/labels", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream archive of repository
     * Streams an archive of the repository&#39;s contents at the requested commit. If no &#x60;at&#x3D;&#x60; commit is requested, an archive of the default branch is streamed.  The &lt;code&gt;filename&#x3D;&lt;/code&gt; query parameter may be used to specify the exact filename to include in the \&quot;Content-Disposition\&quot; header. If an explicit filename is not provided, one will be automatically generated based on what is being archived. Its format depends on the at&#x3D; value:   - No &lt;code&gt;at&#x3D;&lt;/code&gt; commit:     &amp;lt;slug&amp;gt;-&amp;lt;default-branch-name&amp;gt;@&amp;lt;commit&amp;gt;.&amp;lt;format&amp;gt;;     e.g. example-master@43c2f8a0fe8.zip - &lt;code&gt;at&#x3D;&lt;/code&gt;sha: &amp;lt;slug&amp;gt;-&amp;lt;at&amp;gt;.&amp;lt;format&amp;gt;; e.g.     example-09bcbb00100cfbb5310fb6834a1d5ce6cac253e9.tar.gz - &lt;code&gt;at&#x3D;&lt;/code&gt;branchOrTag: &amp;lt;slug&amp;gt;-&amp;lt;branchOrTag&amp;gt;@&amp;lt;commit&amp;gt;.&amp;lt;format&amp;gt;;     e.g. example-feature@bbb225f16e1.tar       - If the branch or tag is qualified (e.g. refs/heads/master, the short name         (master) will be included in the filename     - If the branch or tag&#39;s &lt;i&gt;short name&lt;/i&gt; includes slashes (e.g. release/4.6),         they will be converted to hyphens in the filename (release-4.5)     Archives may be requested in the following formats by adding the &lt;code&gt;format&#x3D;&lt;/code&gt; query parameter:   - zip: A zip file using standard compression (Default) - tar: An uncompressed tarball - tar.gz or tgz: A GZip-compressed tarball   The contents of the archive may be filtered by using the &lt;code&gt;path&#x3D;&lt;/code&gt; query parameter to specify paths to include. &lt;code&gt;path&#x3D;&lt;/code&gt; may be specified multiple times to include multiple paths.   The &lt;code&gt;prefix&#x3D;&lt;/code&gt; query parameter may be used to define a directory (or multiple directories) where the archive&#39;s contents should be placed. If the prefix does not end with /, one will be added automatically. The prefix is &lt;i&gt;always&lt;/i&gt; treated as a directory; it is not possible to use it to prepend characters to the entries in the archive.   Archives of public repositories may be streamed by any authenticated or anonymous user. Streaming archives for non-public repositories requires an &lt;i&gt;authenticated user&lt;/i&gt; with at least &lt;b&gt;REPO_READ&lt;/b&gt; permission.
     * <p><b>200</b> - An archive or the requested commit, in zip, tar or gzipped-tar format.
     * <p><b>400</b> - The requested format is not supported.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the at commit.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param path Paths to include in the streamed archive; may be repeated to include multiple paths (optional)
     * @param filename A filename to include the \&quot;Content-Disposition\&quot; header (optional)
     * @param at The commit to stream an archive of; if not supplied, an archive of the default branch is streamed (optional)
     * @param prefix A prefix to apply to all entries in the streamed archive; if the supplied prefix does not end with a trailing /, one will be added automatically (optional)
     * @param format The format to stream the archive in; must be one of: zip, tar, tar.gz or tgz (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getArchive(String projectKey, String repositorySlug, String path, String filename, String at, String prefix, String format) throws RestClientException {
        getArchiveWithHttpInfo(projectKey, repositorySlug, path, filename, at, prefix, format);
    }

    /**
     * Stream archive of repository
     * Streams an archive of the repository&#39;s contents at the requested commit. If no &#x60;at&#x3D;&#x60; commit is requested, an archive of the default branch is streamed.  The &lt;code&gt;filename&#x3D;&lt;/code&gt; query parameter may be used to specify the exact filename to include in the \&quot;Content-Disposition\&quot; header. If an explicit filename is not provided, one will be automatically generated based on what is being archived. Its format depends on the at&#x3D; value:   - No &lt;code&gt;at&#x3D;&lt;/code&gt; commit:     &amp;lt;slug&amp;gt;-&amp;lt;default-branch-name&amp;gt;@&amp;lt;commit&amp;gt;.&amp;lt;format&amp;gt;;     e.g. example-master@43c2f8a0fe8.zip - &lt;code&gt;at&#x3D;&lt;/code&gt;sha: &amp;lt;slug&amp;gt;-&amp;lt;at&amp;gt;.&amp;lt;format&amp;gt;; e.g.     example-09bcbb00100cfbb5310fb6834a1d5ce6cac253e9.tar.gz - &lt;code&gt;at&#x3D;&lt;/code&gt;branchOrTag: &amp;lt;slug&amp;gt;-&amp;lt;branchOrTag&amp;gt;@&amp;lt;commit&amp;gt;.&amp;lt;format&amp;gt;;     e.g. example-feature@bbb225f16e1.tar       - If the branch or tag is qualified (e.g. refs/heads/master, the short name         (master) will be included in the filename     - If the branch or tag&#39;s &lt;i&gt;short name&lt;/i&gt; includes slashes (e.g. release/4.6),         they will be converted to hyphens in the filename (release-4.5)     Archives may be requested in the following formats by adding the &lt;code&gt;format&#x3D;&lt;/code&gt; query parameter:   - zip: A zip file using standard compression (Default) - tar: An uncompressed tarball - tar.gz or tgz: A GZip-compressed tarball   The contents of the archive may be filtered by using the &lt;code&gt;path&#x3D;&lt;/code&gt; query parameter to specify paths to include. &lt;code&gt;path&#x3D;&lt;/code&gt; may be specified multiple times to include multiple paths.   The &lt;code&gt;prefix&#x3D;&lt;/code&gt; query parameter may be used to define a directory (or multiple directories) where the archive&#39;s contents should be placed. If the prefix does not end with /, one will be added automatically. The prefix is &lt;i&gt;always&lt;/i&gt; treated as a directory; it is not possible to use it to prepend characters to the entries in the archive.   Archives of public repositories may be streamed by any authenticated or anonymous user. Streaming archives for non-public repositories requires an &lt;i&gt;authenticated user&lt;/i&gt; with at least &lt;b&gt;REPO_READ&lt;/b&gt; permission.
     * <p><b>200</b> - An archive or the requested commit, in zip, tar or gzipped-tar format.
     * <p><b>400</b> - The requested format is not supported.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the at commit.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param path Paths to include in the streamed archive; may be repeated to include multiple paths (optional)
     * @param filename A filename to include the \&quot;Content-Disposition\&quot; header (optional)
     * @param at The commit to stream an archive of; if not supplied, an archive of the default branch is streamed (optional)
     * @param prefix A prefix to apply to all entries in the streamed archive; if the supplied prefix does not end with a trailing /, one will be added automatically (optional)
     * @param format The format to stream the archive in; must be one of: zip, tar, tar.gz or tgz (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getArchiveWithHttpInfo(String projectKey, String repositorySlug, String path, String filename, String at, String prefix, String format) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getArchive");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getArchive");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filename", filename));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "prefix", prefix));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/archive", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get an attachment
     * Retrieve the attachment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment.
     * <p><b>200</b> - the attachment
     * <p><b>401</b> - the user is currently not authenticated
     * <p><b>404</b> - The attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @param userAgent  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getAttachment(String projectKey, String attachmentId, String repositorySlug, String userAgent) throws RestClientException {
        getAttachmentWithHttpInfo(projectKey, attachmentId, repositorySlug, userAgent);
    }

    /**
     * Get an attachment
     * Retrieve the attachment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment.
     * <p><b>200</b> - the attachment
     * <p><b>401</b> - the user is currently not authenticated
     * <p><b>404</b> - The attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @param userAgent  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getAttachmentWithHttpInfo(String projectKey, String attachmentId, String repositorySlug, String userAgent) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAttachment");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling getAttachment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAttachment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("attachmentId", attachmentId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (userAgent != null)
        localVarHeaderParams.add("User-Agent", apiClient.parameterToString(userAgent));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/attachments/{attachmentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get attachment metadata
     * Retrieve the attachment metadata.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment that has the attachment metadata.
     * <p><b>200</b> - The attachment metadata
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the attachment metadata
     * <p><b>404</b> - The attachment or the attachment metadata does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @return RestAttachmentMetadata
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAttachmentMetadata getAttachmentMetadata(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        return getAttachmentMetadataWithHttpInfo(projectKey, attachmentId, repositorySlug).getBody();
    }

    /**
     * Get attachment metadata
     * Retrieve the attachment metadata.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment that has the attachment metadata.
     * <p><b>200</b> - The attachment metadata
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the attachment metadata
     * <p><b>404</b> - The attachment or the attachment metadata does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestAttachmentMetadata&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAttachmentMetadata> getAttachmentMetadataWithHttpInfo(String projectKey, String attachmentId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAttachmentMetadata");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling getAttachmentMetadata");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAttachmentMetadata");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("attachmentId", attachmentId);
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

        ParameterizedTypeReference<RestAttachmentMetadata> localReturnType = new ParameterizedTypeReference<RestAttachmentMetadata>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/attachments/{attachmentId}/metadata", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get auto decline settings
     * Retrieves the auto decline settings for the supplied repository. Project settings will be returned if no explicit settings have been set for the repository. In the case that there are no project settings, the default settings will be returned.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for this repository to call the resource.
     * <p><b>200</b> - The auto decline settings
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return RestAutoDeclineSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAutoDeclineSettings getAutoDeclineSettings(String projectKey, String repositorySlug) throws RestClientException {
        return getAutoDeclineSettingsWithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get auto decline settings
     * Retrieves the auto decline settings for the supplied repository. Project settings will be returned if no explicit settings have been set for the repository. In the case that there are no project settings, the default settings will be returned.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for this repository to call the resource.
     * <p><b>200</b> - The auto decline settings
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestAutoDeclineSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAutoDeclineSettings> getAutoDeclineSettingsWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAutoDeclineSettings");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAutoDeclineSettings");
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

        ParameterizedTypeReference<RestAutoDeclineSettings> localReturnType = new ParameterizedTypeReference<RestAutoDeclineSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/auto-decline", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find branches
     * Retrieve the branches matching the supplied &lt;strong&gt;filterText&lt;/strong&gt; param.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The branches matching the supplied &lt;strong&gt;filterText&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param boostMatches Controls whether exact and prefix matches will be boosted to the top (optional)
     * @param orderBy Ordering of refs either ALPHABETICAL (by name) or MODIFICATION (last updated) (optional)
     * @param details Whether to retrieve plugin-provided metadata about each branch (optional)
     * @param filterText The text to match on (optional)
     * @param base Base branch or tag to compare each branch to (for the metadata providers that uses that information (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetBranches200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetBranches200Response getBranches(String projectKey, String repositorySlug, Boolean boostMatches, String orderBy, Boolean details, String filterText, String base, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getBranchesWithHttpInfo(projectKey, repositorySlug, boostMatches, orderBy, details, filterText, base, start, limit).getBody();
    }

    /**
     * Find branches
     * Retrieve the branches matching the supplied &lt;strong&gt;filterText&lt;/strong&gt; param.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The branches matching the supplied &lt;strong&gt;filterText&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param boostMatches Controls whether exact and prefix matches will be boosted to the top (optional)
     * @param orderBy Ordering of refs either ALPHABETICAL (by name) or MODIFICATION (last updated) (optional)
     * @param details Whether to retrieve plugin-provided metadata about each branch (optional)
     * @param filterText The text to match on (optional)
     * @param base Base branch or tag to compare each branch to (for the metadata providers that uses that information (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetBranches200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetBranches200Response> getBranchesWithHttpInfo(String projectKey, String repositorySlug, Boolean boostMatches, String orderBy, Boolean details, String filterText, String base, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getBranches");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getBranches");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "boostMatches", boostMatches));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "orderBy", orderBy));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "details", details));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "base", base));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetBranches200Response> localReturnType = new ParameterizedTypeReference<GetBranches200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/branches", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get changes in commit
     * Retrieve a page of changes made in a specified commit.    &lt;strong&gt;Note:&lt;/strong&gt; The implementation will apply a hard cap (&lt;code&gt;page.max.changes&lt;/code&gt;) and it is not possible to request subsequent content when that cap is exceeded.    The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of changes
     * <p><b>400</b> - The until parameter was not supplied
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository or the since or until parameters supplied does not exist.
     * @param projectKey The project key (required)
     * @param commitId The commit to retrieve changes for (required)
     * @param repositorySlug The repository slug (required)
     * @param withComments &lt;code&gt;true&lt;/code&gt; to apply comment counts in the changes (the default); otherwise, &lt;code&gt;false&lt;/code&gt; to stream changes without comment counts (optional)
     * @param since The commit to which &lt;code&gt;until&lt;/code&gt; should be compared to produce a page of changes. If not specified the commit&#39;s first parent is assumed (if one exists) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetChanges1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetChanges1200Response getChanges(String projectKey, String commitId, String repositorySlug, String withComments, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getChangesWithHttpInfo(projectKey, commitId, repositorySlug, withComments, since, start, limit).getBody();
    }

    /**
     * Get changes in commit
     * Retrieve a page of changes made in a specified commit.    &lt;strong&gt;Note:&lt;/strong&gt; The implementation will apply a hard cap (&lt;code&gt;page.max.changes&lt;/code&gt;) and it is not possible to request subsequent content when that cap is exceeded.    The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of changes
     * <p><b>400</b> - The until parameter was not supplied
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository or the since or until parameters supplied does not exist.
     * @param projectKey The project key (required)
     * @param commitId The commit to retrieve changes for (required)
     * @param repositorySlug The repository slug (required)
     * @param withComments &lt;code&gt;true&lt;/code&gt; to apply comment counts in the changes (the default); otherwise, &lt;code&gt;false&lt;/code&gt; to stream changes without comment counts (optional)
     * @param since The commit to which &lt;code&gt;until&lt;/code&gt; should be compared to produce a page of changes. If not specified the commit&#39;s first parent is assumed (if one exists) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetChanges1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetChanges1200Response> getChangesWithHttpInfo(String projectKey, String commitId, String repositorySlug, String withComments, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getChanges");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getChanges");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getChanges");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withComments", withComments));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetChanges1200Response> localReturnType = new ParameterizedTypeReference<GetChanges1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/changes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get changes made in commit
     * Retrieve a page of changes made in a specified commit.   &lt;strong&gt;Note:&lt;/strong&gt; The implementation will apply a hard cap ({@code page.max.changes}) and it is not possible to request subsequent content when that cap is exceeded.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of changes
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository or the since or until parameters supplied does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param until The commit to retrieve changes for (optional)
     * @param since The commit to which &lt;code&gt;until&lt;/code&gt; should be compared to produce a page of changes. If not specified the commit&#39;s first parent is assumed (if one exists) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetChanges1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetChanges1200Response getChanges1(String projectKey, String repositorySlug, String until, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getChanges1WithHttpInfo(projectKey, repositorySlug, until, since, start, limit).getBody();
    }

    /**
     * Get changes made in commit
     * Retrieve a page of changes made in a specified commit.   &lt;strong&gt;Note:&lt;/strong&gt; The implementation will apply a hard cap ({@code page.max.changes}) and it is not possible to request subsequent content when that cap is exceeded.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of changes
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository or the since or until parameters supplied does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param until The commit to retrieve changes for (optional)
     * @param since The commit to which &lt;code&gt;until&lt;/code&gt; should be compared to produce a page of changes. If not specified the commit&#39;s first parent is assumed (if one exists) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetChanges1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetChanges1200Response> getChanges1WithHttpInfo(String projectKey, String repositorySlug, String until, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getChanges1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getChanges1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "until", until));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetChanges1200Response> localReturnType = new ParameterizedTypeReference<GetChanges1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/changes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a commit comment
     * Retrieves a commit discussion comment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or comment. The missing entity will be specified in the error details.
     * @param projectKey The project key (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment getComment(String projectKey, String commentId, String commitId, String repositorySlug) throws RestClientException {
        return getCommentWithHttpInfo(projectKey, commentId, commitId, repositorySlug).getBody();
    }

    /**
     * Get a commit comment
     * Retrieves a commit discussion comment.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - The requested comment.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or comment. The missing entity will be specified in the error details.
     * @param projectKey The project key (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> getCommentWithHttpInfo(String projectKey, String commentId, String commitId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComment");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling getComment");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getComment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComment");
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

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search for commit comments
     * Retrieves the commit discussion comments that match the specified search criteria.  It is possible to retrieve commit discussion comments that are anchored to a range of commits by providing the sinceId that the comments anchored from.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - A page of comments that match the search criteria
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment
     * <p><b>404</b> - Unable to find the supplied project, repository, or commit. The missing entity will be specified in the error details.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param path The path to the file on which comments were made (optional)
     * @param since For a merge commit, a parent can be provided to specify which diff the comments are on. For a commit range, a sinceId can be provided to specify where the comments are anchored from. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetComments200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetComments200Response getComments(String projectKey, String commitId, String repositorySlug, String path, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getCommentsWithHttpInfo(projectKey, commitId, repositorySlug, path, since, start, limit).getBody();
    }

    /**
     * Search for commit comments
     * Retrieves the commit discussion comments that match the specified search criteria.  It is possible to retrieve commit discussion comments that are anchored to a range of commits by providing the sinceId that the comments anchored from.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - A page of comments that match the search criteria
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the comment
     * <p><b>404</b> - Unable to find the supplied project, repository, or commit. The missing entity will be specified in the error details.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param path The path to the file on which comments were made (optional)
     * @param since For a merge commit, a parent can be provided to specify which diff the comments are on. For a commit range, a sinceId can be provided to specify where the comments are anchored from. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetComments200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetComments200Response> getCommentsWithHttpInfo(String projectKey, String commitId, String repositorySlug, String path, String since, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getComments");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getComments");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getComments");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get commit by ID
     * Retrieve a single commit &lt;i&gt;identified by its ID&lt;/i&gt;. In general, that ID is a SHA1. &lt;u&gt;From 2.11, ref names like \&quot;refs/heads/master\&quot; are no longer accepted by this resource.&lt;/u&gt;  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A commit
     * <p><b>400</b> - The supplied commit ID was invalid
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key (required)
     * @param commitId The commit ID to retrieve (required)
     * @param repositorySlug The repository slug (required)
     * @param path An optional path to filter the commit by. If supplied the details returned &lt;i&gt;may not&lt;/i&gt; be for the specified commit. Instead, starting from the specified commit, they will be the details for the first commit affecting the specified path. (optional)
     * @return RestCommit
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestCommit getCommit(String projectKey, String commitId, String repositorySlug, String path) throws RestClientException {
        return getCommitWithHttpInfo(projectKey, commitId, repositorySlug, path).getBody();
    }

    /**
     * Get commit by ID
     * Retrieve a single commit &lt;i&gt;identified by its ID&lt;/i&gt;. In general, that ID is a SHA1. &lt;u&gt;From 2.11, ref names like \&quot;refs/heads/master\&quot; are no longer accepted by this resource.&lt;/u&gt;  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A commit
     * <p><b>400</b> - The supplied commit ID was invalid
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key (required)
     * @param commitId The commit ID to retrieve (required)
     * @param repositorySlug The repository slug (required)
     * @param path An optional path to filter the commit by. If supplied the details returned &lt;i&gt;may not&lt;/i&gt; be for the specified commit. Instead, starting from the specified commit, they will be the details for the first commit affecting the specified path. (optional)
     * @return ResponseEntity&lt;RestCommit&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestCommit> getCommitWithHttpInfo(String projectKey, String commitId, String repositorySlug, String path) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getCommit");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getCommit");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getCommit");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestCommit> localReturnType = new ParameterizedTypeReference<RestCommit>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get commits
     * Retrieve a page of commits from a given starting commit or \&quot;between\&quot; two commits. If no explicit commit is specified, the tip of the repository&#39;s default branch is assumed. commits may be identified by branch or tag name or by ID. A path may be supplied to restrict the returned commits to only those which affect that path.   The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of commits
     * <p><b>400</b> - One of the supplied commit IDs or refs was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param avatarScheme The desired scheme for the avatar URL. If the parameter is not present URLs will use the same scheme as this request (optional)
     * @param path An optional path to filter commits by (optional)
     * @param withCounts Optionally include the total number of commits and total number of unique authors (optional)
     * @param followRenames If &lt;code&gt;true&lt;/code&gt;, the commit history of the specified file will be followed past renames. Only valid for a path to a single file. (optional)
     * @param until The commit ID (SHA1) or ref (inclusively) to retrieve commits before (optional)
     * @param avatarSize If present the service adds avatar URLs for commit authors. Should be an integer specifying the desired size in pixels. If the parameter is not present, avatar URLs will not be set (optional)
     * @param since The commit ID or ref (exclusively) to retrieve commits after (optional)
     * @param merges If present, controls how merge commits should be filtered. Can be either &lt;code&gt;exclude&lt;/code&gt;, to exclude merge commits, &lt;code&gt;include&lt;/code&gt;, to include both merge commits and non-merge commits or &lt;code&gt;only&lt;/code&gt;, to only return merge commits. (optional)
     * @param ignoreMissing &lt;code&gt;true&lt;/code&gt; to ignore missing commits, &lt;code&gt;false&lt;/code&gt; otherwise (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetCommits200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetCommits200Response getCommits(String projectKey, String repositorySlug, String avatarScheme, String path, String withCounts, String followRenames, String until, String avatarSize, String since, String merges, String ignoreMissing, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getCommitsWithHttpInfo(projectKey, repositorySlug, avatarScheme, path, withCounts, followRenames, until, avatarSize, since, merges, ignoreMissing, start, limit).getBody();
    }

    /**
     * Get commits
     * Retrieve a page of commits from a given starting commit or \&quot;between\&quot; two commits. If no explicit commit is specified, the tip of the repository&#39;s default branch is assumed. commits may be identified by branch or tag name or by ID. A path may be supplied to restrict the returned commits to only those which affect that path.   The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of commits
     * <p><b>400</b> - One of the supplied commit IDs or refs was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param avatarScheme The desired scheme for the avatar URL. If the parameter is not present URLs will use the same scheme as this request (optional)
     * @param path An optional path to filter commits by (optional)
     * @param withCounts Optionally include the total number of commits and total number of unique authors (optional)
     * @param followRenames If &lt;code&gt;true&lt;/code&gt;, the commit history of the specified file will be followed past renames. Only valid for a path to a single file. (optional)
     * @param until The commit ID (SHA1) or ref (inclusively) to retrieve commits before (optional)
     * @param avatarSize If present the service adds avatar URLs for commit authors. Should be an integer specifying the desired size in pixels. If the parameter is not present, avatar URLs will not be set (optional)
     * @param since The commit ID or ref (exclusively) to retrieve commits after (optional)
     * @param merges If present, controls how merge commits should be filtered. Can be either &lt;code&gt;exclude&lt;/code&gt;, to exclude merge commits, &lt;code&gt;include&lt;/code&gt;, to include both merge commits and non-merge commits or &lt;code&gt;only&lt;/code&gt;, to only return merge commits. (optional)
     * @param ignoreMissing &lt;code&gt;true&lt;/code&gt; to ignore missing commits, &lt;code&gt;false&lt;/code&gt; otherwise (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetCommits200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetCommits200Response> getCommitsWithHttpInfo(String projectKey, String repositorySlug, String avatarScheme, String path, String withCounts, String followRenames, String until, String avatarSize, String since, String merges, String ignoreMissing, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getCommits");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getCommits");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "avatarScheme", avatarScheme));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCounts", withCounts));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "followRenames", followRenames));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "until", until));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "avatarSize", avatarSize));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "merges", merges));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "ignoreMissing", ignoreMissing));
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get hook scripts
     * Return a page of hook scripts configured for the specified repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>200</b> - A page of hook scripts.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetConfigurations200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetConfigurations200Response getConfigurations1(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getConfigurations1WithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get hook scripts
     * Return a page of hook scripts configured for the specified repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>200</b> - A page of hook scripts.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetConfigurations200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetConfigurations200Response> getConfigurations1WithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getConfigurations1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getConfigurations1");
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

        ParameterizedTypeReference<GetConfigurations200Response> localReturnType = new ParameterizedTypeReference<GetConfigurations200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/hook-scripts", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get file content at revision
     * Retrieve a page of content for a file path at a specified revision.   Responses from this endpoint vary widely depending on the query parameters. The example JSON is for a request that does not use size, type, blame or noContent.   1. size will return a response like {\&quot;size\&quot;:10000} 2. type will return a response like {\&quot;type\&quot;:\&quot;FILE\&quot;}, where possible values are    \&quot;DIRECTORY\&quot;, \&quot;FILE\&quot; and \&quot;SUBMODULE\&quot; 3. blame &lt;i&gt;without&lt;/i&gt; noContent will include blame for the lines of    content returned on the page 4. blame &lt;i&gt;with&lt;/i&gt; noContent will omit file contents and only return    blame for the requested lines 5. noContent without blame is ignored and does nothing   The various parameters are \&quot;processed\&quot; in the above order. That means ?size&#x3D;true&amp;amp;type&#x3D;truewill return a size response, not a type one; the type parameter will be ignored.   The blame and noContent query parameters are handled differently from size and type. For blame and noContent, the &lt;i&gt;presence&lt;/i&gt; of the parameter implies \&quot;true\&quot; if no value is specified; size and and type both require an explicit&#x3D;true or they&#39;re treated as \&quot;false\&quot;.   - ?blame is the same as ?blame&#x3D;true - ?blame&amp;amp;noContent is the same as ?blame&#x3D;true&amp;amp;noContent&#x3D;true - ?size is the same as ?size&#x3D;false - ?type is the same as ?type&#x3D;false   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of contents from a file.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param noContent If blame&amp;amp;noContent only the blame is retrieved instead of the contents (optional)
     * @param at The commit ID or ref to retrieve the content for (optional)
     * @param size If true only the size will be returned for the file path instead of the contents (optional)
     * @param blame If present and not equal to &#39;false&#39;, the blame will be returned for the file as well (optional)
     * @param type If true only the type will be returned for the file path instead of the contents (optional)
     * @return RestRepositoryContents
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryContents getContent(String projectKey, String repositorySlug, String noContent, String at, String size, String blame, String type) throws RestClientException {
        return getContentWithHttpInfo(projectKey, repositorySlug, noContent, at, size, blame, type).getBody();
    }

    /**
     * Get file content at revision
     * Retrieve a page of content for a file path at a specified revision.   Responses from this endpoint vary widely depending on the query parameters. The example JSON is for a request that does not use size, type, blame or noContent.   1. size will return a response like {\&quot;size\&quot;:10000} 2. type will return a response like {\&quot;type\&quot;:\&quot;FILE\&quot;}, where possible values are    \&quot;DIRECTORY\&quot;, \&quot;FILE\&quot; and \&quot;SUBMODULE\&quot; 3. blame &lt;i&gt;without&lt;/i&gt; noContent will include blame for the lines of    content returned on the page 4. blame &lt;i&gt;with&lt;/i&gt; noContent will omit file contents and only return    blame for the requested lines 5. noContent without blame is ignored and does nothing   The various parameters are \&quot;processed\&quot; in the above order. That means ?size&#x3D;true&amp;amp;type&#x3D;truewill return a size response, not a type one; the type parameter will be ignored.   The blame and noContent query parameters are handled differently from size and type. For blame and noContent, the &lt;i&gt;presence&lt;/i&gt; of the parameter implies \&quot;true\&quot; if no value is specified; size and and type both require an explicit&#x3D;true or they&#39;re treated as \&quot;false\&quot;.   - ?blame is the same as ?blame&#x3D;true - ?blame&amp;amp;noContent is the same as ?blame&#x3D;true&amp;amp;noContent&#x3D;true - ?size is the same as ?size&#x3D;false - ?type is the same as ?type&#x3D;false   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of contents from a file.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param noContent If blame&amp;amp;noContent only the blame is retrieved instead of the contents (optional)
     * @param at The commit ID or ref to retrieve the content for (optional)
     * @param size If true only the size will be returned for the file path instead of the contents (optional)
     * @param blame If present and not equal to &#39;false&#39;, the blame will be returned for the file as well (optional)
     * @param type If true only the type will be returned for the file path instead of the contents (optional)
     * @return ResponseEntity&lt;RestRepositoryContents&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryContents> getContentWithHttpInfo(String projectKey, String repositorySlug, String noContent, String at, String size, String blame, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getContent");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "noContent", noContent));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "blame", blame));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestRepositoryContents> localReturnType = new ParameterizedTypeReference<RestRepositoryContents>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/browse", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get file content
     * Retrieve a page of content for a file path at a specified revision.   Responses from this endpoint vary widely depending on the query parameters. The example JSON is for a request that does not use size, type, blame or noContent.   1. size will return a response like {\&quot;size\&quot;:10000} 2. type will return a response like {\&quot;type\&quot;:\&quot;FILE\&quot;}, where possible values are    \&quot;DIRECTORY\&quot;, \&quot;FILE\&quot; and \&quot;SUBMODULE\&quot; 3. blame &lt;i&gt;without&lt;/i&gt; noContent will include blame for the lines of    content returned on the page 4. blame &lt;i&gt;with&lt;/i&gt; noContent will omit file contents and only return    blame for the requested lines 5. noContent without blame is ignored and does nothing   The various parameters are \&quot;processed\&quot; in the above order. That means ?size&#x3D;true&amp;amp;type&#x3D;truewill return a size response, not a type one; the type parameter will be ignored.   The blame and noContent query parameters are handled differently from size and type. For blame and noContent, the &lt;i&gt;presence&lt;/i&gt; of the parameter implies \&quot;true\&quot; if no value is specified; size and and type both require an explicit&#x3D;true or they&#39;re treated as \&quot;false\&quot;.   - ?blame is the same as ?blame&#x3D;true - ?blame&amp;amp;noContent is the same as ?blame&#x3D;true&amp;amp;noContent&#x3D;true - ?size is the same as ?size&#x3D;false - ?type is the same as ?type&#x3D;false   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of contents from a file.
     * <p><b>400</b> - The path or until parameters were not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The file path to retrieve content from (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param noContent If blame&amp;amp;noContent only the blame is retrieved instead of the contents (optional)
     * @param at The commit ID or ref to retrieve the content for (optional)
     * @param size If true only the size will be returned for the file path instead of the contents (optional)
     * @param blame If present and not equal to &#39;false&#39;, the blame will be returned for the file as well (optional)
     * @param type If true only the type will be returned for the file path instead of the contents (optional)
     * @return GetContent1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetContent1200Response getContent1(String path, String projectKey, String repositorySlug, String noContent, String at, String size, String blame, String type) throws RestClientException {
        return getContent1WithHttpInfo(path, projectKey, repositorySlug, noContent, at, size, blame, type).getBody();
    }

    /**
     * Get file content
     * Retrieve a page of content for a file path at a specified revision.   Responses from this endpoint vary widely depending on the query parameters. The example JSON is for a request that does not use size, type, blame or noContent.   1. size will return a response like {\&quot;size\&quot;:10000} 2. type will return a response like {\&quot;type\&quot;:\&quot;FILE\&quot;}, where possible values are    \&quot;DIRECTORY\&quot;, \&quot;FILE\&quot; and \&quot;SUBMODULE\&quot; 3. blame &lt;i&gt;without&lt;/i&gt; noContent will include blame for the lines of    content returned on the page 4. blame &lt;i&gt;with&lt;/i&gt; noContent will omit file contents and only return    blame for the requested lines 5. noContent without blame is ignored and does nothing   The various parameters are \&quot;processed\&quot; in the above order. That means ?size&#x3D;true&amp;amp;type&#x3D;truewill return a size response, not a type one; the type parameter will be ignored.   The blame and noContent query parameters are handled differently from size and type. For blame and noContent, the &lt;i&gt;presence&lt;/i&gt; of the parameter implies \&quot;true\&quot; if no value is specified; size and and type both require an explicit&#x3D;true or they&#39;re treated as \&quot;false\&quot;.   - ?blame is the same as ?blame&#x3D;true - ?blame&amp;amp;noContent is the same as ?blame&#x3D;true&amp;amp;noContent&#x3D;true - ?size is the same as ?size&#x3D;false - ?type is the same as ?type&#x3D;false   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of contents from a file.
     * <p><b>400</b> - The path or until parameters were not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The file path to retrieve content from (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param noContent If blame&amp;amp;noContent only the blame is retrieved instead of the contents (optional)
     * @param at The commit ID or ref to retrieve the content for (optional)
     * @param size If true only the size will be returned for the file path instead of the contents (optional)
     * @param blame If present and not equal to &#39;false&#39;, the blame will be returned for the file as well (optional)
     * @param type If true only the type will be returned for the file path instead of the contents (optional)
     * @return ResponseEntity&lt;GetContent1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetContent1200Response> getContent1WithHttpInfo(String path, String projectKey, String repositorySlug, String noContent, String at, String size, String blame, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling getContent1");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getContent1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getContent1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "noContent", noContent));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "blame", blame));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetContent1200Response> localReturnType = new ParameterizedTypeReference<GetContent1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/browse/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Get last webhook invocation details
     * Get the latest invocations for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>204</b> - No webhook invocations exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook invocations in the repository.
     * <p><b>404</b> - The specified repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. (optional)
     * @param outcome The outcome to filter for. Can be SUCCESS, FAILURE, ERROR. None specified means that the all will be considered (optional)
     * @return RestDetailedInvocation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedInvocation getLatestInvocation1(String projectKey, String webhookId, String repositorySlug, String event, String outcome) throws RestClientException {
        return getLatestInvocation1WithHttpInfo(projectKey, webhookId, repositorySlug, event, outcome).getBody();
    }

    /**
     * Get last webhook invocation details
     * Get the latest invocations for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>204</b> - No webhook invocations exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook invocations in the repository.
     * <p><b>404</b> - The specified repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. (optional)
     * @param outcome The outcome to filter for. Can be SUCCESS, FAILURE, ERROR. None specified means that the all will be considered (optional)
     * @return ResponseEntity&lt;RestDetailedInvocation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedInvocation> getLatestInvocation1WithHttpInfo(String projectKey, String webhookId, String repositorySlug, String event, String outcome) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getLatestInvocation1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getLatestInvocation1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getLatestInvocation1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "event", event));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "outcome", outcome));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestDetailedInvocation> localReturnType = new ParameterizedTypeReference<RestDetailedInvocation>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}/latest", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get pull request settings
     * Retrieve the pull request settings for the context repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.   This resource will call all RestFragments that are registered with the key &lt;strong&gt;bitbucket.repository.settings.pullRequests&lt;/strong&gt;. If any fragment fails validations by returning a non-empty Map of errors, then no fragments will execute.   The property keys for the settings that are bundled with the application are   - mergeConfig - the merge strategy configuration for pull requests - requiredApprovers - (Deprecated, please use com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook instead) the number of approvals required on a pull request for it to be mergeable, or 0 if the merge check is disabled - com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook - the merge check configuration for required approvers - requiredAllApprovers - whether or not all approvers must approve a pull request for it to be mergeable - requiredAllTasksComplete - whether or not all tasks on a pull request need to be completed for it to be mergeable - requiredSuccessfulBuilds - (Deprecated, please use com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck instead) the number of successful builds on a pull request for it to be mergeable, or 0 if the merge check is disabled - com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck - the merge check configuration for required builds   
     * <p><b>200</b> - The repository pull request settings for the context repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRepositoryPullRequestSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPullRequestSettings getPullRequestSettings1(String projectKey, String repositorySlug) throws RestClientException {
        return getPullRequestSettings1WithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get pull request settings
     * Retrieve the pull request settings for the context repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.   This resource will call all RestFragments that are registered with the key &lt;strong&gt;bitbucket.repository.settings.pullRequests&lt;/strong&gt;. If any fragment fails validations by returning a non-empty Map of errors, then no fragments will execute.   The property keys for the settings that are bundled with the application are   - mergeConfig - the merge strategy configuration for pull requests - requiredApprovers - (Deprecated, please use com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook instead) the number of approvals required on a pull request for it to be mergeable, or 0 if the merge check is disabled - com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook - the merge check configuration for required approvers - requiredAllApprovers - whether or not all approvers must approve a pull request for it to be mergeable - requiredAllTasksComplete - whether or not all tasks on a pull request need to be completed for it to be mergeable - requiredSuccessfulBuilds - (Deprecated, please use com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck instead) the number of successful builds on a pull request for it to be mergeable, or 0 if the merge check is disabled - com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck - the merge check configuration for required builds   
     * <p><b>200</b> - The repository pull request settings for the context repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRepositoryPullRequestSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPullRequestSettings> getPullRequestSettings1WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestSettings1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPullRequestSettings1");
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

        ParameterizedTypeReference<RestRepositoryPullRequestSettings> localReturnType = new ParameterizedTypeReference<RestRepositoryPullRequestSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/pull-requests", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get ref change activity
     * Retrieve a page of repository ref change activity.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of ref change activity.
     * <p><b>401</b> - The user is currently not authenticated or the user does not have REPO_ADMIN permission.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param ref (optional) exact match for a ref ID to filter ref change activity for (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRefChangeActivity200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRefChangeActivity200Response getRefChangeActivity(String projectKey, String repositorySlug, String ref, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRefChangeActivityWithHttpInfo(projectKey, repositorySlug, ref, start, limit).getBody();
    }

    /**
     * Get ref change activity
     * Retrieve a page of repository ref change activity.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of ref change activity.
     * <p><b>401</b> - The user is currently not authenticated or the user does not have REPO_ADMIN permission.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param ref (optional) exact match for a ref ID to filter ref change activity for (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRefChangeActivity200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRefChangeActivity200Response> getRefChangeActivityWithHttpInfo(String projectKey, String repositorySlug, String ref, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRefChangeActivity");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRefChangeActivity");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "ref", ref));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetRefChangeActivity200Response> localReturnType = new ParameterizedTypeReference<GetRefChangeActivity200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/ref-change-activities", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search for repositories
     * Retrieve a page of repositories based on query parameters that control the search. See the documentation of the parameters for more details.   This resource is anonymously accessible.   &lt;b&gt;Note on permissions.&lt;/b&gt; In absence of the &lt;code&gt;permission&lt;/code&gt; query parameter the implicit &#39;read&#39; permission is assumed. Please note that this permission is lower than the &lt;tt&gt;REPO_READ&lt;/tt&gt; permission rather than being equal to it. The implicit &#39;read&#39; permission for a given repository is assigned to any user that has any of the higher permissions, such as &lt;tt&gt;REPO_READ&lt;/tt&gt;, as well as to anonymous users if the repository is marked as public. The important implication of the above is that an anonymous request to this resource with a permission level &lt;tt&gt;REPO_READ&lt;/tt&gt; is guaranteed to receive an empty list of repositories as a result. For anonymous requests it is therefore recommended to not specify the &lt;tt&gt;permission&lt;/tt&gt; parameter at all.
     * <p><b>200</b> - A page of repositories.
     * <p><b>400</b> - The &lt;code&gt;visibility&lt;/code&gt; parameter contains an invalid value.
     * @param archived (optional) if specified, this will limit the resulting repository list to ones whose are &lt;tt&gt;ACTIVE&lt;/tt&gt;, &lt;tt&gt;ARCHIVED&lt;/tt&gt; or &lt;tt&gt;ALL&lt;/tt&gt; for both. The match performed is case-insensitive. This filter defaults to &lt;tt&gt;ACTIVE&lt;/tt&gt; when not set. &lt;em&gt;Available since 8.0&lt;/em&gt; (optional)
     * @param projectname (optional) if specified, this will limit the resulting repository list to ones whose project&#39;s name matches this parameter&#39;s value. The match performed is case-insensitive and any leading and/or trailing whitespace characters on the &lt;code&gt;projectname&lt;/code&gt; parameter will be stripped. (optional)
     * @param projectkey (optional) if specified, this will limit the resulting repository list to ones whose project&#39;s key matches this parameter&#39;s value. The match performed is case-insensitive and any leading  and/or trailing whitespace characters on the &lt;code&gt;projectKey&lt;/code&gt; parameter will be stripped. &lt;em&gt;Available since 8.0&lt;/em&gt; (optional)
     * @param visibility (optional) if specified, this will limit the resulting repository list based on the repositories visibility. Valid values are &lt;em&gt;public&lt;/em&gt; or &lt;em&gt;private&lt;/em&gt;. (optional)
     * @param name (optional) if specified, this will limit the resulting repository list to ones whose name matches this parameter&#39;s value. The match performed is case-insensitive and any leading and/or trailing whitespace characters on the &lt;code&gt;name&lt;/code&gt; parameter will be stripped. (optional)
     * @param permission (optional) if specified, it must be a valid repository permission level name and will limit the resulting repository list to ones that the requesting user has the specified permission level to. If not specified, the default implicit &#39;read&#39; permission level will be assumed. The currently supported explicit permission values are &lt;tt&gt;REPO_READ&lt;/tt&gt;, &lt;tt&gt;REPO_WRITE&lt;/tt&gt; and &lt;tt&gt;REPO_ADMIN&lt;/tt&gt;. (optional)
     * @param state (optional) if specified, it must be a valid repository state name and will limit the resulting repository list to ones that are in the specified state. The currently supported explicit state values are &lt;tt&gt;AVAILABLE&lt;/tt&gt;, &lt;tt&gt;INITIALISING&lt;/tt&gt;, &lt;tt&gt;INITIALISATION_FAILED&lt;/tt&gt; and &lt;tt&gt;OFFLINE&lt;/tt&gt;.&lt;br&gt; &lt;em&gt;Available since 5.13&lt;/em&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response getRepositories1(String archived, String projectname, String projectkey, String visibility, String name, String permission, String state, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepositories1WithHttpInfo(archived, projectname, projectkey, visibility, name, permission, state, start, limit).getBody();
    }

    /**
     * Search for repositories
     * Retrieve a page of repositories based on query parameters that control the search. See the documentation of the parameters for more details.   This resource is anonymously accessible.   &lt;b&gt;Note on permissions.&lt;/b&gt; In absence of the &lt;code&gt;permission&lt;/code&gt; query parameter the implicit &#39;read&#39; permission is assumed. Please note that this permission is lower than the &lt;tt&gt;REPO_READ&lt;/tt&gt; permission rather than being equal to it. The implicit &#39;read&#39; permission for a given repository is assigned to any user that has any of the higher permissions, such as &lt;tt&gt;REPO_READ&lt;/tt&gt;, as well as to anonymous users if the repository is marked as public. The important implication of the above is that an anonymous request to this resource with a permission level &lt;tt&gt;REPO_READ&lt;/tt&gt; is guaranteed to receive an empty list of repositories as a result. For anonymous requests it is therefore recommended to not specify the &lt;tt&gt;permission&lt;/tt&gt; parameter at all.
     * <p><b>200</b> - A page of repositories.
     * <p><b>400</b> - The &lt;code&gt;visibility&lt;/code&gt; parameter contains an invalid value.
     * @param archived (optional) if specified, this will limit the resulting repository list to ones whose are &lt;tt&gt;ACTIVE&lt;/tt&gt;, &lt;tt&gt;ARCHIVED&lt;/tt&gt; or &lt;tt&gt;ALL&lt;/tt&gt; for both. The match performed is case-insensitive. This filter defaults to &lt;tt&gt;ACTIVE&lt;/tt&gt; when not set. &lt;em&gt;Available since 8.0&lt;/em&gt; (optional)
     * @param projectname (optional) if specified, this will limit the resulting repository list to ones whose project&#39;s name matches this parameter&#39;s value. The match performed is case-insensitive and any leading and/or trailing whitespace characters on the &lt;code&gt;projectname&lt;/code&gt; parameter will be stripped. (optional)
     * @param projectkey (optional) if specified, this will limit the resulting repository list to ones whose project&#39;s key matches this parameter&#39;s value. The match performed is case-insensitive and any leading  and/or trailing whitespace characters on the &lt;code&gt;projectKey&lt;/code&gt; parameter will be stripped. &lt;em&gt;Available since 8.0&lt;/em&gt; (optional)
     * @param visibility (optional) if specified, this will limit the resulting repository list based on the repositories visibility. Valid values are &lt;em&gt;public&lt;/em&gt; or &lt;em&gt;private&lt;/em&gt;. (optional)
     * @param name (optional) if specified, this will limit the resulting repository list to ones whose name matches this parameter&#39;s value. The match performed is case-insensitive and any leading and/or trailing whitespace characters on the &lt;code&gt;name&lt;/code&gt; parameter will be stripped. (optional)
     * @param permission (optional) if specified, it must be a valid repository permission level name and will limit the resulting repository list to ones that the requesting user has the specified permission level to. If not specified, the default implicit &#39;read&#39; permission level will be assumed. The currently supported explicit permission values are &lt;tt&gt;REPO_READ&lt;/tt&gt;, &lt;tt&gt;REPO_WRITE&lt;/tt&gt; and &lt;tt&gt;REPO_ADMIN&lt;/tt&gt;. (optional)
     * @param state (optional) if specified, it must be a valid repository state name and will limit the resulting repository list to ones that are in the specified state. The currently supported explicit state values are &lt;tt&gt;AVAILABLE&lt;/tt&gt;, &lt;tt&gt;INITIALISING&lt;/tt&gt;, &lt;tt&gt;INITIALISATION_FAILED&lt;/tt&gt; and &lt;tt&gt;OFFLINE&lt;/tt&gt;.&lt;br&gt; &lt;em&gt;Available since 5.13&lt;/em&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> getRepositories1WithHttpInfo(String archived, String projectname, String projectkey, String visibility, String name, String permission, String state, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "archived", archived));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "projectname", projectname));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "projectkey", projectkey));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "visibility", visibility));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/repos", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get recently accessed repositories
     * Retrieve a page of recently accessed repositories for the currently authenticated user.   Repositories are ordered from most recently to least recently accessed. &lt;p&gt;Only authenticated users may call this resource.
     * <p><b>200</b> - A page of recently accessed repositories.
     * <p><b>400</b> - The permission level is unknown or not related to repository.
     * <p><b>401</b> - The request is unauthenticated.
     * @param permission (optional) If specified, it must be a valid repository permission level name and will limit the resulting repository list to ones that the requesting user has the specified permission level to. If not specified, the default &lt;code&gt;REPO_READ&lt;/code&gt; permission level will be assumed. (optional, default to REPO_READ)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response getRepositoriesRecentlyAccessed(String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepositoriesRecentlyAccessedWithHttpInfo(permission, start, limit).getBody();
    }

    /**
     * Get recently accessed repositories
     * Retrieve a page of recently accessed repositories for the currently authenticated user.   Repositories are ordered from most recently to least recently accessed. &lt;p&gt;Only authenticated users may call this resource.
     * <p><b>200</b> - A page of recently accessed repositories.
     * <p><b>400</b> - The permission level is unknown or not related to repository.
     * <p><b>401</b> - The request is unauthenticated.
     * @param permission (optional) If specified, it must be a valid repository permission level name and will limit the resulting repository list to ones that the requesting user has the specified permission level to. If not specified, the default &lt;code&gt;REPO_READ&lt;/code&gt; permission level will be assumed. (optional, default to REPO_READ)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> getRepositoriesRecentlyAccessedWithHttpInfo(String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/profile/recent/repos", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieve a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook.
     * <p><b>404</b> - The specified repository hook does not exist for the given repository, or the repository does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook getRepositoryHook1(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        return getRepositoryHook1WithHttpInfo(projectKey, hookKey, repositorySlug).getBody();
    }

    /**
     * 
     * Retrieve a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook.
     * <p><b>404</b> - The specified repository hook does not exist for the given repository, or the repository does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> getRepositoryHook1WithHttpInfo(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositoryHook1");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling getRepositoryHook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRepositoryHook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
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

        ParameterizedTypeReference<RestRepositoryHook> localReturnType = new ParameterizedTypeReference<RestRepositoryHook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieve a page of repository hooks for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of repository hooks with their associated enabled state.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hooks.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param type The optional type to filter by. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoryHooks1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoryHooks1200Response getRepositoryHooks1(String projectKey, String repositorySlug, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepositoryHooks1WithHttpInfo(projectKey, repositorySlug, type, start, limit).getBody();
    }

    /**
     * 
     * Retrieve a page of repository hooks for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of repository hooks with their associated enabled state.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hooks.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param type The optional type to filter by. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoryHooks1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoryHooks1200Response> getRepositoryHooks1WithHttpInfo(String projectKey, String repositorySlug, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositoryHooks1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRepositoryHooks1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetRepositoryHooks1200Response> localReturnType = new ParameterizedTypeReference<GetRepositoryHooks1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a ref restriction
     * Returns a restriction as specified by a restriction id.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing the restriction.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRefRestriction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefRestriction getRestriction1(String projectKey, String id, String repositorySlug) throws RestClientException {
        return getRestriction1WithHttpInfo(projectKey, id, repositorySlug).getBody();
    }

    /**
     * Get a ref restriction
     * Returns a restriction as specified by a restriction id.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing the restriction.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRefRestriction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefRestriction> getRestriction1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRestriction1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRestriction1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRestriction1");
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

        ParameterizedTypeReference<RestRefRestriction> localReturnType = new ParameterizedTypeReference<RestRefRestriction>() {};
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/repos/{repositorySlug}/restrictions/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search for ref restrictions
     * Search for restrictions using the supplied parameters.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing a page of restrictions.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param matcherType Matcher type to filter on (optional)
     * @param matcherId Matcher id to filter on. Requires the matcherType parameter to be specified also. (optional)
     * @param type Types of restrictions to filter on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRestrictions1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRestrictions1200Response getRestrictions1(String projectKey, String repositorySlug, String matcherType, String matcherId, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRestrictions1WithHttpInfo(projectKey, repositorySlug, matcherType, matcherId, type, start, limit).getBody();
    }

    /**
     * Search for ref restrictions
     * Search for restrictions using the supplied parameters.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing a page of restrictions.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param matcherType Matcher type to filter on (optional)
     * @param matcherId Matcher id to filter on. Requires the matcherType parameter to be specified also. (optional)
     * @param type Types of restrictions to filter on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRestrictions1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRestrictions1200Response> getRestrictions1WithHttpInfo(String projectKey, String repositorySlug, String matcherType, String matcherId, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRestrictions1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRestrictions1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "matcherType", matcherType));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "matcherId", matcherId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetRestrictions1200Response> localReturnType = new ParameterizedTypeReference<GetRestrictions1200Response>() {};
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/repos/{repositorySlug}/restrictions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieve the settings for a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook settings.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ExampleSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleSettings getSettings1(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        return getSettings1WithHttpInfo(projectKey, hookKey, repositorySlug).getBody();
    }

    /**
     * 
     * Retrieve the settings for a repository hook for this repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook settings.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;ExampleSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleSettings> getSettings1WithHttpInfo(String projectKey, String hookKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getSettings1");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling getSettings1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getSettings1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
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

        ParameterizedTypeReference<ExampleSettings> localReturnType = new ParameterizedTypeReference<ExampleSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/settings", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook statistics
     * Get the statistics for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics in the repository.
     * <p><b>404</b> - The specified repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. May be empty, in which case all events are considered (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStatistics1(String projectKey, String webhookId, String repositorySlug, String event) throws RestClientException {
        return getStatistics1WithHttpInfo(projectKey, webhookId, repositorySlug, event).getBody();
    }

    /**
     * Get webhook statistics
     * Get the statistics for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics in the repository.
     * <p><b>404</b> - The specified repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. May be empty, in which case all events are considered (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStatistics1WithHttpInfo(String projectKey, String webhookId, String repositorySlug, String event) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getStatistics1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getStatistics1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getStatistics1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "event", event));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}/statistics", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook statistics summary
     * Get the statistics summary for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics summary in the repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStatisticsSummary1(String projectKey, String webhookId, String repositorySlug) throws RestClientException {
        return getStatisticsSummary1WithHttpInfo(projectKey, webhookId, repositorySlug).getBody();
    }

    /**
     * Get webhook statistics summary
     * Get the statistics summary for a specific webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics summary in the repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStatisticsSummary1WithHttpInfo(String projectKey, String webhookId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getStatisticsSummary1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getStatisticsSummary1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getStatisticsSummary1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
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

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}/statistics/summary", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get synchronization status
     * Retrieves the synchronization status for the specified repository. In addition to listing refs which cannot be synchronized, if any, the status also provides the timestamp for the most recent synchronization and indicates whether synchronization is available and enabled. If \&quot;?at\&quot; is specified in the URL, the synchronization status for the specified ref is returned, rather than the complete repository status.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the repository, or it must be public if the request is anonymous. Additionally, after synchronization is enabled for a repository, meaning synchronization was available at that time, permission changes and other actions can cause it to become unavailable. Even when synchronization is enabled, if it is no longer available for the repository it will not be performed.
     * <p><b>200</b> - Synchronization status for the specified repository, or specific ref within that repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository, or the repository is not public if the request is anonymous.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at Retrieves the synchronization status for the specified ref within the repository, rather than for the entire repository (optional)
     * @return RestRefSyncStatus
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefSyncStatus getStatus(String projectKey, String repositorySlug, String at) throws RestClientException {
        return getStatusWithHttpInfo(projectKey, repositorySlug, at).getBody();
    }

    /**
     * Get synchronization status
     * Retrieves the synchronization status for the specified repository. In addition to listing refs which cannot be synchronized, if any, the status also provides the timestamp for the most recent synchronization and indicates whether synchronization is available and enabled. If \&quot;?at\&quot; is specified in the URL, the synchronization status for the specified ref is returned, rather than the complete repository status.  The authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; permission for the repository, or it must be public if the request is anonymous. Additionally, after synchronization is enabled for a repository, meaning synchronization was available at that time, permission changes and other actions can cause it to become unavailable. Even when synchronization is enabled, if it is no longer available for the repository it will not be performed.
     * <p><b>200</b> - Synchronization status for the specified repository, or specific ref within that repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository, or the repository is not public if the request is anonymous.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at Retrieves the synchronization status for the specified ref within the repository, rather than for the entire repository (optional)
     * @return ResponseEntity&lt;RestRefSyncStatus&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefSyncStatus> getStatusWithHttpInfo(String projectKey, String repositorySlug, String at) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getStatus");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestRefSyncStatus> localReturnType = new ParameterizedTypeReference<RestRefSyncStatus>() {};
        return apiClient.invokeAPI("/sync/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get tag
     * Retrieve a tag in the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The tag which matches the supplied &lt;strong&gt;name&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified tag does not exist.
     * @param projectKey The project key. (required)
     * @param name The name of the tag to be retrieved. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestTag
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestTag getTag(String projectKey, String name, String repositorySlug) throws RestClientException {
        return getTagWithHttpInfo(projectKey, name, repositorySlug).getBody();
    }

    /**
     * Get tag
     * Retrieve a tag in the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The tag which matches the supplied &lt;strong&gt;name&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified tag does not exist.
     * @param projectKey The project key. (required)
     * @param name The name of the tag to be retrieved. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestTag&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestTag> getTagWithHttpInfo(String projectKey, String name, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getTag");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling getTag");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getTag");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("name", name);
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

        ParameterizedTypeReference<RestTag> localReturnType = new ParameterizedTypeReference<RestTag>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/tags/{name}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find tag
     * Retrieve the tags matching the supplied &lt;strong&gt;filterText&lt;/strong&gt; param.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The tags matching the supplied &lt;strong&gt;filterText&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param orderBy Ordering of refs either ALPHABETICAL (by name) or MODIFICATION (last updated) (optional)
     * @param filterText The text to match on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetTags200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetTags200Response getTags(String projectKey, String repositorySlug, String orderBy, String filterText, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getTagsWithHttpInfo(projectKey, repositorySlug, orderBy, filterText, start, limit).getBody();
    }

    /**
     * Find tag
     * Retrieve the tags matching the supplied &lt;strong&gt;filterText&lt;/strong&gt; param.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The tags matching the supplied &lt;strong&gt;filterText&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param orderBy Ordering of refs either ALPHABETICAL (by name) or MODIFICATION (last updated) (optional)
     * @param filterText The text to match on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetTags200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetTags200Response> getTagsWithHttpInfo(String projectKey, String repositorySlug, String orderBy, String filterText, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getTags");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getTags");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "orderBy", orderBy));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetTags200Response> localReturnType = new ParameterizedTypeReference<GetTags200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/tags", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook
     * Get a webhook by ID.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get a webhook in the repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for the webhook (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook getWebhook1(String projectKey, String webhookId, String repositorySlug, String statistics) throws RestClientException {
        return getWebhook1WithHttpInfo(projectKey, webhookId, repositorySlug, statistics).getBody();
    }

    /**
     * Get webhook
     * Get a webhook by ID.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get a webhook in the repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for the webhook (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> getWebhook1WithHttpInfo(String projectKey, String webhookId, String repositorySlug, String statistics) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getWebhook1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getWebhook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getWebhook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "statistics", statistics));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestWebhook> localReturnType = new ParameterizedTypeReference<RestWebhook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * React to a comment
     * Add an emoticon reaction to a comment
     * <p><b>200</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id (required)
     * @param commitId The commit id (required)
     * @param emoticon The emoticon to add (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestUserReaction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUserReaction react(String projectKey, String commentId, String commitId, String emoticon, String repositorySlug) throws RestClientException {
        return reactWithHttpInfo(projectKey, commentId, commitId, emoticon, repositorySlug).getBody();
    }

    /**
     * React to a comment
     * Add an emoticon reaction to a comment
     * <p><b>200</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id (required)
     * @param commitId The commit id (required)
     * @param emoticon The emoticon to add (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestUserReaction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUserReaction> reactWithHttpInfo(String projectKey, String commentId, String commitId, String emoticon, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling react");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling react");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling react");
        }
        
        // verify the required parameter 'emoticon' is set
        if (emoticon == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'emoticon' when calling react");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling react");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("commitId", commitId);
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
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}/reactions/{emoticon}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove a hook script
     * Removes the hook script from the set of hook scripts configured to run in the repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>204</b> - The hook script was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The repository slug or hook script ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void removeConfiguration1(String projectKey, String scriptId, String repositorySlug) throws RestClientException {
        removeConfiguration1WithHttpInfo(projectKey, scriptId, repositorySlug);
    }

    /**
     * Remove a hook script
     * Removes the hook script from the set of hook scripts configured to run in the repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>204</b> - The hook script was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The repository slug or hook script ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> removeConfiguration1WithHttpInfo(String projectKey, String scriptId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling removeConfiguration1");
        }
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling removeConfiguration1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling removeConfiguration1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("scriptId", scriptId);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/hook-scripts/{scriptId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove repository label
     * Remove label that is applied to the given repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - An empty response indicating that the label is no longer associated to the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove the label.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param labelName The label to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void removeLabel(String projectKey, String labelName, String repositorySlug) throws RestClientException {
        removeLabelWithHttpInfo(projectKey, labelName, repositorySlug);
    }

    /**
     * Remove repository label
     * Remove label that is applied to the given repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository.
     * <p><b>204</b> - An empty response indicating that the label is no longer associated to the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove the label.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param labelName The label to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> removeLabelWithHttpInfo(String projectKey, String labelName, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling removeLabel");
        }
        
        // verify the required parameter 'labelName' is set
        if (labelName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'labelName' when calling removeLabel");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling removeLabel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("labelName", labelName);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/labels/{labelName}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Save attachment metadata
     * Save attachment metadata.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment that has the attachment metadata.
     * <p><b>200</b> - The attachment metadata
     * <p><b>400</b> - The supplied content is not valid JSON
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to save theattachment metadata
     * <p><b>404</b> - The repository or the attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @param body The attachment metadata can be any valid JSON content (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void saveAttachmentMetadata(String projectKey, String attachmentId, String repositorySlug, String body) throws RestClientException {
        saveAttachmentMetadataWithHttpInfo(projectKey, attachmentId, repositorySlug, body);
    }

    /**
     * Save attachment metadata
     * Save attachment metadata.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository that is associated to the attachment that has the attachment metadata.
     * <p><b>200</b> - The attachment metadata
     * <p><b>400</b> - The supplied content is not valid JSON
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to save theattachment metadata
     * <p><b>404</b> - The repository or the attachment does not exist
     * @param projectKey The project key (required)
     * @param attachmentId the attachment ID (required)
     * @param repositorySlug The repository slug (required)
     * @param body The attachment metadata can be any valid JSON content (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> saveAttachmentMetadataWithHttpInfo(String projectKey, String attachmentId, String repositorySlug, String body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling saveAttachmentMetadata");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling saveAttachmentMetadata");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling saveAttachmentMetadata");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("attachmentId", attachmentId);
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/attachments/{attachmentId}/metadata", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search webhooks
     * Search webhooks in this repository and parent project. This endpoint returns a superset of the results returned by the /webhooks endpoint because it allows filtering by project scope too, not just repository webhooks.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param scopeType Scopes to filter by. This parameter can be specified once e.g. \&quot;scopeType&#x3D;repository\&quot;, or twice e.g. \&quot;scopeType&#x3D;repository&amp;scopeType&#x3D;project\&quot;, to filter by more than one scope level.  (optional)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; ids to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void searchWebhooks(String projectKey, String repositorySlug, String scopeType, String event, Boolean statistics) throws RestClientException {
        searchWebhooksWithHttpInfo(projectKey, repositorySlug, scopeType, event, statistics);
    }

    /**
     * Search webhooks
     * Search webhooks in this repository and parent project. This endpoint returns a superset of the results returned by the /webhooks endpoint because it allows filtering by project scope too, not just repository webhooks.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param scopeType Scopes to filter by. This parameter can be specified once e.g. \&quot;scopeType&#x3D;repository\&quot;, or twice e.g. \&quot;scopeType&#x3D;repository&amp;scopeType&#x3D;project\&quot;, to filter by more than one scope level.  (optional)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; ids to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> searchWebhooksWithHttpInfo(String projectKey, String repositorySlug, String scopeType, String event, Boolean statistics) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling searchWebhooks");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling searchWebhooks");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "scopeType", scopeType));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "event", event));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "statistics", statistics));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/search", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create auto decline settings
     * Creates or updates the auto decline settings for the supplied repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for this repository to call the resource
     * <p><b>200</b> - The auto decline settings
     * <p><b>400</b> - inactivityWeeks was not one of 1, 2, 4, 8, or, 12, or the enabled parameter was not included in the request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create or update the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param restAutoDeclineSettingsRequest The settings to create or update (optional)
     * @return RestAutoDeclineSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAutoDeclineSettings set1(String projectKey, String repositorySlug, RestAutoDeclineSettingsRequest restAutoDeclineSettingsRequest) throws RestClientException {
        return set1WithHttpInfo(projectKey, repositorySlug, restAutoDeclineSettingsRequest).getBody();
    }

    /**
     * Create auto decline settings
     * Creates or updates the auto decline settings for the supplied repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for this repository to call the resource
     * <p><b>200</b> - The auto decline settings
     * <p><b>400</b> - inactivityWeeks was not one of 1, 2, 4, 8, or, 12, or the enabled parameter was not included in the request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create or update the auto decline settings.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param restAutoDeclineSettingsRequest The settings to create or update (optional)
     * @return ResponseEntity&lt;RestAutoDeclineSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAutoDeclineSettings> set1WithHttpInfo(String projectKey, String repositorySlug, RestAutoDeclineSettingsRequest restAutoDeclineSettingsRequest) throws RestClientException {
        Object localVarPostBody = restAutoDeclineSettingsRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling set1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling set1");
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

        ParameterizedTypeReference<RestAutoDeclineSettings> localReturnType = new ParameterizedTypeReference<RestAutoDeclineSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/auto-decline", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create/update a hook script
     * Creates/updates the hook script configuration for the provided hook script and repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>400</b> - The hook script was not created/updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The repository slug supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param repositorySlug The repository slug. (required)
     * @param restHookScriptTriggers The hook triggers for which the hook script should be run (optional)
     * @return RestHookScriptConfig
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestHookScriptConfig setConfiguration1(String projectKey, String scriptId, String repositorySlug, RestHookScriptTriggers restHookScriptTriggers) throws RestClientException {
        return setConfiguration1WithHttpInfo(projectKey, scriptId, repositorySlug, restHookScriptTriggers).getBody();
    }

    /**
     * Create/update a hook script
     * Creates/updates the hook script configuration for the provided hook script and repository.   This endpoint requires **REPO_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>400</b> - The hook script was not created/updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified repository.
     * <p><b>404</b> - The repository slug supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param repositorySlug The repository slug. (required)
     * @param restHookScriptTriggers The hook triggers for which the hook script should be run (optional)
     * @return ResponseEntity&lt;RestHookScriptConfig&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestHookScriptConfig> setConfiguration1WithHttpInfo(String projectKey, String scriptId, String repositorySlug, RestHookScriptTriggers restHookScriptTriggers) throws RestClientException {
        Object localVarPostBody = restHookScriptTriggers;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setConfiguration1");
        }
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling setConfiguration1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setConfiguration1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("scriptId", scriptId);
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

        ParameterizedTypeReference<RestHookScriptConfig> localReturnType = new ParameterizedTypeReference<RestHookScriptConfig>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/hook-scripts/{scriptId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Disable synchronization
     * Enables or disables synchronization for the specified repository. When synchronization is enabled, branches within the repository are immediately synchronized and the status is updated with the outcome. That initial synchronization is performed before the REST request returns, allowing it to return the updated status.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository. Anonymous users cannot manage synchronization, even on public repositories. Additionally, synchronization must be available for the specified repository. Synchronization is only available if:  - The repository is a fork, since its origin is used as upstream - The owning user still has access to the fork&#39;s origin,  if the repository is a &lt;i&gt;personalfork&lt;/i&gt;
     * <p><b>200</b> - The updated synchronization status for the repository, after enabling synchronization. 204 NO CONTENT is returned instead after disabling synchronization.
     * <p><b>204</b> - Synchronization has successfully been disabled. 200 OK, with updated status information, is returned instead after enabling synchronization.
     * <p><b>400</b> - The JSON payload for the request did not define the \&quot;enabled\&quot; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to manage synchronization in the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRefSyncStatus  (optional)
     * @return RestRefSyncStatus
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefSyncStatus setEnabled(String projectKey, String repositorySlug, RestRefSyncStatus restRefSyncStatus) throws RestClientException {
        return setEnabledWithHttpInfo(projectKey, repositorySlug, restRefSyncStatus).getBody();
    }

    /**
     * Disable synchronization
     * Enables or disables synchronization for the specified repository. When synchronization is enabled, branches within the repository are immediately synchronized and the status is updated with the outcome. That initial synchronization is performed before the REST request returns, allowing it to return the updated status.  The authenticated user must have &lt;b&gt;REPO_ADMIN&lt;/b&gt; permission for the specified repository. Anonymous users cannot manage synchronization, even on public repositories. Additionally, synchronization must be available for the specified repository. Synchronization is only available if:  - The repository is a fork, since its origin is used as upstream - The owning user still has access to the fork&#39;s origin,  if the repository is a &lt;i&gt;personalfork&lt;/i&gt;
     * <p><b>200</b> - The updated synchronization status for the repository, after enabling synchronization. 204 NO CONTENT is returned instead after disabling synchronization.
     * <p><b>204</b> - Synchronization has successfully been disabled. 200 OK, with updated status information, is returned instead after enabling synchronization.
     * <p><b>400</b> - The JSON payload for the request did not define the \&quot;enabled\&quot; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to manage synchronization in the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRefSyncStatus  (optional)
     * @return ResponseEntity&lt;RestRefSyncStatus&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefSyncStatus> setEnabledWithHttpInfo(String projectKey, String repositorySlug, RestRefSyncStatus restRefSyncStatus) throws RestClientException {
        Object localVarPostBody = restRefSyncStatus;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setEnabled");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setEnabled");
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

        ParameterizedTypeReference<RestRefSyncStatus> localReturnType = new ParameterizedTypeReference<RestRefSyncStatus>() {};
        return apiClient.invokeAPI("/sync/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Modify the settings for a repository hook for this repository.   The service will reject any settings which are too large, the current limit is 32KB once serialized.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.   A JSON document can be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to modify the hook settings.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param exampleSettings The raw settings. (optional)
     * @return ExampleSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleSettings setSettings1(String projectKey, String hookKey, String repositorySlug, ExampleSettings exampleSettings) throws RestClientException {
        return setSettings1WithHttpInfo(projectKey, hookKey, repositorySlug, exampleSettings).getBody();
    }

    /**
     * 
     * Modify the settings for a repository hook for this repository.   The service will reject any settings which are too large, the current limit is 32KB once serialized.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.   A JSON document can be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to modify the hook settings.
     * <p><b>404</b> - The specified repository or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param exampleSettings The raw settings. (optional)
     * @return ResponseEntity&lt;ExampleSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleSettings> setSettings1WithHttpInfo(String projectKey, String hookKey, String repositorySlug, ExampleSettings exampleSettings) throws RestClientException {
        Object localVarPostBody = exampleSettings;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setSettings1");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling setSettings1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setSettings1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);
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

        ParameterizedTypeReference<ExampleSettings> localReturnType = new ParameterizedTypeReference<ExampleSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/settings", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream files
     * Streams files from the repository&#39;s root with the last commit to modify each file. Commit modifications are traversed starting from the &lt;code&gt;at&lt;/code&gt; commit or, if not specified, from the tip of the default branch.  Unless the repository is public, the authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; access to call this resource.
     * <p><b>200</b> - A map of files to the last commit that modified them, and the latest commit to the repository (by nature, any commit to a repository modifies its root).
     * <p><b>400</b> - No &lt;code&gt;at&lt;/code&gt; commit was specified. When streaming modifications, an explicit starting commit must be supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the &lt;code&gt;at&lt;/code&gt; commit.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param at The commit to use as the starting point when listing files and calculating modifications (optional)
     * @return ExampleFiles
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleFiles stream(String projectKey, String repositorySlug, String at) throws RestClientException {
        return streamWithHttpInfo(projectKey, repositorySlug, at).getBody();
    }

    /**
     * Stream files
     * Streams files from the repository&#39;s root with the last commit to modify each file. Commit modifications are traversed starting from the &lt;code&gt;at&lt;/code&gt; commit or, if not specified, from the tip of the default branch.  Unless the repository is public, the authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; access to call this resource.
     * <p><b>200</b> - A map of files to the last commit that modified them, and the latest commit to the repository (by nature, any commit to a repository modifies its root).
     * <p><b>400</b> - No &lt;code&gt;at&lt;/code&gt; commit was specified. When streaming modifications, an explicit starting commit must be supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the &lt;code&gt;at&lt;/code&gt; commit.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param at The commit to use as the starting point when listing files and calculating modifications (optional)
     * @return ResponseEntity&lt;ExampleFiles&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleFiles> streamWithHttpInfo(String projectKey, String repositorySlug, String at) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling stream");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling stream");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<ExampleFiles> localReturnType = new ParameterizedTypeReference<ExampleFiles>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/last-modified", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream files with last modified commit in path
     * Streams files in the requested &lt;code&gt;path&lt;/code&gt; with the last commit to modify each file. Commit modifications are traversed starting from the &lt;code&gt;at&lt;/code&gt; commit or, if not specified, from the tip of the default branch.  Unless the repository is public, the authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; access to call this resource.
     * <p><b>200</b> - A map of files to the last commit that modified them, and the latest commit to update the requested path.
     * <p><b>400</b> - No &lt;code&gt;at&lt;/code&gt; commit was specified. When streaming modifications, an explicit starting commit must be supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the &lt;code&gt;at&lt;/code&gt; commit, or the &lt;code&gt;at&lt;/code&gt; commit does not contain the requested path.
     * @param path The path within the repository whose files should be streamed (required)
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param at The commit to use as the starting point when listing files and calculating modifications (optional)
     * @return ExampleFiles
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleFiles stream1(String path, String projectKey, String repositorySlug, String at) throws RestClientException {
        return stream1WithHttpInfo(path, projectKey, repositorySlug, at).getBody();
    }

    /**
     * Stream files with last modified commit in path
     * Streams files in the requested &lt;code&gt;path&lt;/code&gt; with the last commit to modify each file. Commit modifications are traversed starting from the &lt;code&gt;at&lt;/code&gt; commit or, if not specified, from the tip of the default branch.  Unless the repository is public, the authenticated user must have &lt;b&gt;REPO_READ&lt;/b&gt; access to call this resource.
     * <p><b>200</b> - A map of files to the last commit that modified them, and the latest commit to update the requested path.
     * <p><b>400</b> - No &lt;code&gt;at&lt;/code&gt; commit was specified. When streaming modifications, an explicit starting commit must be supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist or does not contain the &lt;code&gt;at&lt;/code&gt; commit, or the &lt;code&gt;at&lt;/code&gt; commit does not contain the requested path.
     * @param path The path within the repository whose files should be streamed (required)
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param at The commit to use as the starting point when listing files and calculating modifications (optional)
     * @return ResponseEntity&lt;ExampleFiles&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleFiles> stream1WithHttpInfo(String path, String projectKey, String repositorySlug, String at) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling stream1");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling stream1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling stream1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<ExampleFiles> localReturnType = new ParameterizedTypeReference<ExampleFiles>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/last-modified/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Compare commits
     * Gets the file changes available in the &lt;code&gt; from&lt;/code&gt; commit but not in the &lt;code&gt; to&lt;/code&gt; commit.   If either the &lt;code&gt; from&lt;/code&gt; or &lt;code&gt; to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - A page of changes.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetChanges1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetChanges1200Response streamChanges(String projectKey, String repositorySlug, String fromRepo, String from, String to, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamChangesWithHttpInfo(projectKey, repositorySlug, fromRepo, from, to, start, limit).getBody();
    }

    /**
     * Compare commits
     * Gets the file changes available in the &lt;code&gt; from&lt;/code&gt; commit but not in the &lt;code&gt; to&lt;/code&gt; commit.   If either the &lt;code&gt; from&lt;/code&gt; or &lt;code&gt; to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - A page of changes.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetChanges1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetChanges1200Response> streamChangesWithHttpInfo(String projectKey, String repositorySlug, String fromRepo, String from, String to, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamChanges");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamChanges");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromRepo", fromRepo));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "to", to));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetChanges1200Response> localReturnType = new ParameterizedTypeReference<GetChanges1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/compare/changes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get accessible commits
     * Gets the commits accessible from the &lt;code&gt;from&lt;/code&gt; commit but not in the &lt;code&gt;to&lt;/code&gt; commit.  If either the &lt;code&gt;from&lt;/code&gt; or &lt;code&gt;to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - A page of commits.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetCommits200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetCommits200Response streamCommits(String projectKey, String repositorySlug, String fromRepo, String from, String to, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamCommitsWithHttpInfo(projectKey, repositorySlug, fromRepo, from, to, start, limit).getBody();
    }

    /**
     * Get accessible commits
     * Gets the commits accessible from the &lt;code&gt;from&lt;/code&gt; commit but not in the &lt;code&gt;to&lt;/code&gt; commit.  If either the &lt;code&gt;from&lt;/code&gt; or &lt;code&gt;to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - A page of commits.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetCommits200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetCommits200Response> streamCommitsWithHttpInfo(String projectKey, String repositorySlug, String fromRepo, String from, String to, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamCommits");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamCommits");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromRepo", fromRepo));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "to", to));
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/compare/commits", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get diff between commits
     * Gets a diff of the changes available in the &lt;code&gt;from&lt;/code&gt; commit but not in the &lt;code&gt; to&lt;/code&gt; commit.  If either the &lt;code&gt; from&lt;/code&gt; or &lt;code&gt; to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - The diff of the changes.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param path the path to the file to diff (optional) (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines an optional number of context lines to include around each added or removed lines in the diff (optional)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param srcPath source path (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param whitespace an optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return StreamDiff200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StreamDiff200Response streamDiff(String path, String projectKey, String repositorySlug, String contextLines, String fromRepo, String srcPath, String from, String to, String whitespace, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamDiffWithHttpInfo(path, projectKey, repositorySlug, contextLines, fromRepo, srcPath, from, to, whitespace, start, limit).getBody();
    }

    /**
     * Get diff between commits
     * Gets a diff of the changes available in the &lt;code&gt;from&lt;/code&gt; commit but not in the &lt;code&gt; to&lt;/code&gt; commit.  If either the &lt;code&gt; from&lt;/code&gt; or &lt;code&gt; to&lt;/code&gt; commit are not specified, they will be replaced by the default branch of their containing repository.
     * <p><b>200</b> - The diff of the changes.
     * <p><b>404</b> - The source repository,target repository, or commit does not exist.
     * @param path the path to the file to diff (optional) (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines an optional number of context lines to include around each added or removed lines in the diff (optional)
     * @param fromRepo an optional parameter specifying the source repository containing the source commit if that commit is not present in the current repository; the repository can be specified by either its ID &lt;em&gt;fromRepo&#x3D;42&lt;/em&gt; or by its project key plus its repo slug separated by a slash: &lt;em&gt;fromRepo&#x3D;projectKey/repoSlug&lt;/em&gt; (optional)
     * @param srcPath source path (optional)
     * @param from the source commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param to the target commit (can be a partial/full commit ID or qualified/unqualified ref name) (optional)
     * @param whitespace an optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;StreamDiff200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StreamDiff200Response> streamDiffWithHttpInfo(String path, String projectKey, String repositorySlug, String contextLines, String fromRepo, String srcPath, String from, String to, String whitespace, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamDiff");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamDiff");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamDiff");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "fromRepo", fromRepo));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "to", to));
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

        ParameterizedTypeReference<StreamDiff200Response> localReturnType = new ParameterizedTypeReference<StreamDiff200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/compare/diff{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get files in directory
     * Retrieve a page of files from particular directory of a repository. The search is done recursively, so all files from any sub-directory of the specified directory will be returned.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of files.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The path requested does not exist at the supplied commit.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at The commit ID or ref (e.g. a branch or tag) to list the files at. If not specified the default branch will be used instead. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return StreamFiles200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StreamFiles200Response streamFiles(String projectKey, String repositorySlug, String at, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamFilesWithHttpInfo(projectKey, repositorySlug, at, start, limit).getBody();
    }

    /**
     * Get files in directory
     * Retrieve a page of files from particular directory of a repository. The search is done recursively, so all files from any sub-directory of the specified directory will be returned.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of files.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The path requested does not exist at the supplied commit.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at The commit ID or ref (e.g. a branch or tag) to list the files at. If not specified the default branch will be used instead. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;StreamFiles200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StreamFiles200Response> streamFilesWithHttpInfo(String projectKey, String repositorySlug, String at, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamFiles");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamFiles");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<StreamFiles200Response> localReturnType = new ParameterizedTypeReference<StreamFiles200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/files", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get files in directory
     * Retrieve a page of files from particular directory of a repository. The search is done recursively, so all files from any sub-directory of the specified directory will be returned.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of files.
     * <p><b>400</b> - The path requested is not a directory at the supplied commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The path requested does not exist at the supplied commit.
     * @param path The directory to list files for. (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at The commit ID or ref (e.g. a branch or tag) to list the files at. If not specified the default branch will be used instead. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return StreamFiles200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StreamFiles200Response streamFiles1(String path, String projectKey, String repositorySlug, String at, BigDecimal start, BigDecimal limit) throws RestClientException {
        return streamFiles1WithHttpInfo(path, projectKey, repositorySlug, at, start, limit).getBody();
    }

    /**
     * Get files in directory
     * Retrieve a page of files from particular directory of a repository. The search is done recursively, so all files from any sub-directory of the specified directory will be returned.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A page of files.
     * <p><b>400</b> - The path requested is not a directory at the supplied commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The path requested does not exist at the supplied commit.
     * @param path The directory to list files for. (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at The commit ID or ref (e.g. a branch or tag) to list the files at. If not specified the default branch will be used instead. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;StreamFiles200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StreamFiles200Response> streamFiles1WithHttpInfo(String path, String projectKey, String repositorySlug, String at, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamFiles1");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamFiles1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamFiles1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<StreamFiles200Response> localReturnType = new ParameterizedTypeReference<StreamFiles200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/files/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get patch content at revision
     * Retrieve the patch content for a repository at a specified revision.   Cache headers are added to the response (only if full commit hashes are used, not in the case of short hashes).   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The patch contents from a repository.
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param until The target revision from which to generate the patch (required) (optional)
     * @param allAncestors indicates whether or not to generate a patch which includes all the ancestors of the &#39;until&#39; revision. If true, the value provided by &#39;since&#39; is ignored. (optional)
     * @param since The base revision from which to generate the patch. This is only applicable when &#39;allAncestors&#39; is false. If omitted the patch will represent one single commit, the &#39;until&#39;. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamPatch(String projectKey, String repositorySlug, String until, String allAncestors, String since) throws RestClientException {
        streamPatchWithHttpInfo(projectKey, repositorySlug, until, allAncestors, since);
    }

    /**
     * Get patch content at revision
     * Retrieve the patch content for a repository at a specified revision.   Cache headers are added to the response (only if full commit hashes are used, not in the case of short hashes).   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The patch contents from a repository.
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param until The target revision from which to generate the patch (required) (optional)
     * @param allAncestors indicates whether or not to generate a patch which includes all the ancestors of the &#39;until&#39; revision. If true, the value provided by &#39;since&#39; is ignored. (optional)
     * @param since The base revision from which to generate the patch. This is only applicable when &#39;allAncestors&#39; is false. If omitted the patch will represent one single commit, the &#39;until&#39;. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamPatchWithHttpInfo(String projectKey, String repositorySlug, String until, String allAncestors, String since) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamPatch");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamPatch");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "until", until));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "allAncestors", allAncestors));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/patch", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get raw content of a file at revision
     * Retrieve the raw content for a file path at a specified revision.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The raw contents from a file.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The file path to retrieve content from (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the raw content at, or the default branch if not specified (optional)
     * @param markup If present or \&quot;true\&quot;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than \&quot;true\&quot;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the markup.render.html.escape property, which is true by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the markup.render.headerids property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the markup.render.hardwrap property, which is true by default, will be used (optional)
     * @return Resource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Resource streamRaw(String path, String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        return streamRawWithHttpInfo(path, projectKey, repositorySlug, at, markup, htmlEscape, includeHeadingId, hardwrap).getBody();
    }

    /**
     * Get raw content of a file at revision
     * Retrieve the raw content for a file path at a specified revision.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The raw contents from a file.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The file path to retrieve content from (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the raw content at, or the default branch if not specified (optional)
     * @param markup If present or \&quot;true\&quot;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than \&quot;true\&quot;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the markup.render.html.escape property, which is true by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the markup.render.headerids property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the markup.render.hardwrap property, which is true by default, will be used (optional)
     * @return ResponseEntity&lt;Resource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Resource> streamRawWithHttpInfo(String path, String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamRaw");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRaw");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRaw");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "at", at));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "markup", markup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "htmlEscape", htmlEscape));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeHeadingId", includeHeadingId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "hardwrap", hardwrap));
        

        final String[] localVarAccepts = { 
            "*/*", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Resource> localReturnType = new ParameterizedTypeReference<Resource>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/raw/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param contextLines  (optional, default to -1)
     * @param srcPath  (optional)
     * @param autoSrcPath  (optional, default to false)
     * @param whitespace  (optional)
     * @param since  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff(String projectKey, String commitId, String repositorySlug, Integer contextLines, String srcPath, Boolean autoSrcPath, String whitespace, String since) throws RestClientException {
        streamRawDiffWithHttpInfo(projectKey, commitId, repositorySlug, contextLines, srcPath, autoSrcPath, whitespace, since);
    }

    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param contextLines  (optional, default to -1)
     * @param srcPath  (optional)
     * @param autoSrcPath  (optional, default to false)
     * @param whitespace  (optional)
     * @param since  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiffWithHttpInfo(String projectKey, String commitId, String repositorySlug, Integer contextLines, String srcPath, Boolean autoSrcPath, String whitespace, String since) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling streamRawDiff");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "autoSrcPath", autoSrcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

        final String[] localVarAccepts = { 
            "text/plain; qs=0.1"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/diff", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stream diff between revisions
     * Stream the diff between two provided revisions.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The repository does not exist
     * @param path  (required)
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param autoSrcPath &lt;code&gt;true&lt;/code&gt; to automatically try to find the source path when it&#39;s not provided, &lt;code&gt;false&lt;/code&gt; otherwise. Requires the &lt;code&gt;path&lt;/code&gt; to be provided. (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff1(String path, String projectKey, String commitId, String repositorySlug, String contextLines, String srcPath, String autoSrcPath, String whitespace, String since) throws RestClientException {
        streamRawDiff1WithHttpInfo(path, projectKey, commitId, repositorySlug, contextLines, srcPath, autoSrcPath, whitespace, since);
    }

    /**
     * Stream diff between revisions
     * Stream the diff between two provided revisions.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The repository does not exist
     * @param path  (required)
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param autoSrcPath &lt;code&gt;true&lt;/code&gt; to automatically try to find the source path when it&#39;s not provided, &lt;code&gt;false&lt;/code&gt; otherwise. Requires the &lt;code&gt;path&lt;/code&gt; to be provided. (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff1WithHttpInfo(String path, String projectKey, String commitId, String repositorySlug, String contextLines, String srcPath, String autoSrcPath, String whitespace, String since) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamRawDiff1");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff1");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling streamRawDiff1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "autoSrcPath", autoSrcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/diff/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get raw diff for path
     * Stream the raw diff between two provided revisions.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param until The target revision to diff to (required) (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff2(String projectKey, String repositorySlug, String contextLines, String srcPath, String until, String whitespace, String since) throws RestClientException {
        streamRawDiff2WithHttpInfo(projectKey, repositorySlug, contextLines, srcPath, until, whitespace, since);
    }

    /**
     * Get raw diff for path
     * Stream the raw diff between two provided revisions.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions.
     * <p><b>400</b> - The path parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param until The target revision to diff to (required) (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff2WithHttpInfo(String projectKey, String repositorySlug, String contextLines, String srcPath, String until, String whitespace, String since) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "until", until));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/diff", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get raw diff for path
     * Stream the raw diff between two provided revisions.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions.
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The path to the file which should be diffed (required) (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param until The target revision to diff to (required) (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamRawDiff3(String path, String projectKey, String repositorySlug, String contextLines, String srcPath, String until, String whitespace, String since) throws RestClientException {
        streamRawDiff3WithHttpInfo(path, projectKey, repositorySlug, contextLines, srcPath, until, whitespace, since);
    }

    /**
     * Get raw diff for path
     * Stream the raw diff between two provided revisions.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A raw diff between two revisions.
     * <p><b>400</b> - The until parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The repository does not exist.
     * @param path The path to the file which should be diffed (required) (required)
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param contextLines The number of context lines to include around added/removed lines in the diff (optional)
     * @param srcPath The source path for the file, if it was copied, moved or renamed (optional)
     * @param until The target revision to diff to (required) (optional)
     * @param whitespace Optional whitespace flag which can be set to &lt;code&gt;ignore-all&lt;/code&gt; (optional)
     * @param since The base revision to diff from. If omitted the parent revision of the until revision is used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamRawDiff3WithHttpInfo(String path, String projectKey, String repositorySlug, String contextLines, String srcPath, String until, String whitespace, String since) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'path' when calling streamRawDiff3");
        }
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamRawDiff3");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamRawDiff3");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("path", path);
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "contextLines", contextLines));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "srcPath", srcPath));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "until", until));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "whitespace", whitespace));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "since", since));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/diff/{path}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Manual synchronization
     * Allows developers to apply a manual operation to bring a ref back in sync with upstream when it becomes out of sync due to conflicting changes. The following actions are supported:  - &lt;tt&gt;MERGE&lt;/tt&gt;: Merges in commits from the upstream ref. After applying this action, the   synchronized ref will be &lt;tt&gt;AHEAD&lt;/tt&gt; (as it still includes commits that do not exist   upstream.    - This action is only supported for &lt;tt&gt;DIVERGED&lt;/tt&gt; refs    - If a \&quot;commitMessage\&quot; is provided in the context, it will be used on the merge commit.      Otherwise a default message is used. - &lt;tt&gt;DISCARD&lt;/tt&gt;: &lt;i&gt;Throws away&lt;/i&gt; local changes in favour of those made upstream. This is a   &lt;i&gt;destructive&lt;/i&gt; operation where commits in the local repository are lost.    - No context entries are supported for this action    - If the upstream ref has been deleted, the local ref is deleted as well    - Otherwise, the local ref is updated to reference the same commit as upstream, even if      the update is not fast-forward (similar to a forced push)   The authenticated user must have &lt;b&gt;REPO_WRITE&lt;/b&gt; permission for the specified repository. Anonymous users cannot synchronize refs, even on public repositories. Additionally, synchronization must be &lt;i&gt;enabled&lt;/i&gt; and &lt;i&gt;available&lt;/i&gt; for the specified repository.
     * <p><b>200</b> - The requested action was successfully performed, and has updated the ref&#39;s state, but the ref if is still not in sync with upstream. For example, after applying the &lt;tt&gt;MERGE&lt;/tt&gt; action, the ref will still be &lt;tt&gt;AHEAD&lt;/tt&gt; of upstream. If the action brings the ref in sync with upstream, 204 NO CONTENT is returned instead.
     * <p><b>204</b> - The requested action was successfully performed and the ref is now in sync with upstream. If the action updates the ref but does not bring it in sync with upstream, 200 OK is returned instead.
     * <p><b>400</b> - The requested synchronization action was not understood.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update refs within the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - Synchronization is not available or enabled for the specified repository, or the ref is already in sync with upstream.
     * <p><b>501</b> - The requested synchronization action was understood by the server, but the mechanism to apply it has not been implemented.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRefSyncRequest  (optional)
     * @return RestRejectedRef
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRejectedRef synchronize(String projectKey, String repositorySlug, RestRefSyncRequest restRefSyncRequest) throws RestClientException {
        return synchronizeWithHttpInfo(projectKey, repositorySlug, restRefSyncRequest).getBody();
    }

    /**
     * Manual synchronization
     * Allows developers to apply a manual operation to bring a ref back in sync with upstream when it becomes out of sync due to conflicting changes. The following actions are supported:  - &lt;tt&gt;MERGE&lt;/tt&gt;: Merges in commits from the upstream ref. After applying this action, the   synchronized ref will be &lt;tt&gt;AHEAD&lt;/tt&gt; (as it still includes commits that do not exist   upstream.    - This action is only supported for &lt;tt&gt;DIVERGED&lt;/tt&gt; refs    - If a \&quot;commitMessage\&quot; is provided in the context, it will be used on the merge commit.      Otherwise a default message is used. - &lt;tt&gt;DISCARD&lt;/tt&gt;: &lt;i&gt;Throws away&lt;/i&gt; local changes in favour of those made upstream. This is a   &lt;i&gt;destructive&lt;/i&gt; operation where commits in the local repository are lost.    - No context entries are supported for this action    - If the upstream ref has been deleted, the local ref is deleted as well    - Otherwise, the local ref is updated to reference the same commit as upstream, even if      the update is not fast-forward (similar to a forced push)   The authenticated user must have &lt;b&gt;REPO_WRITE&lt;/b&gt; permission for the specified repository. Anonymous users cannot synchronize refs, even on public repositories. Additionally, synchronization must be &lt;i&gt;enabled&lt;/i&gt; and &lt;i&gt;available&lt;/i&gt; for the specified repository.
     * <p><b>200</b> - The requested action was successfully performed, and has updated the ref&#39;s state, but the ref if is still not in sync with upstream. For example, after applying the &lt;tt&gt;MERGE&lt;/tt&gt; action, the ref will still be &lt;tt&gt;AHEAD&lt;/tt&gt; of upstream. If the action brings the ref in sync with upstream, 204 NO CONTENT is returned instead.
     * <p><b>204</b> - The requested action was successfully performed and the ref is now in sync with upstream. If the action updates the ref but does not bring it in sync with upstream, 200 OK is returned instead.
     * <p><b>400</b> - The requested synchronization action was not understood.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update refs within the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - Synchronization is not available or enabled for the specified repository, or the ref is already in sync with upstream.
     * <p><b>501</b> - The requested synchronization action was understood by the server, but the mechanism to apply it has not been implemented.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRefSyncRequest  (optional)
     * @return ResponseEntity&lt;RestRejectedRef&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRejectedRef> synchronizeWithHttpInfo(String projectKey, String repositorySlug, RestRefSyncRequest restRefSyncRequest) throws RestClientException {
        Object localVarPostBody = restRefSyncRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling synchronize");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling synchronize");
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

        ParameterizedTypeReference<RestRejectedRef> localReturnType = new ParameterizedTypeReference<RestRejectedRef>() {};
        return apiClient.invokeAPI("/sync/latest/projects/{projectKey}/repos/{repositorySlug}/synchronize", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Test webhook
     * Test connectivity to a specific endpoint.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to test a connection.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param webhookId  (optional)
     * @param sslVerificationRequired Whether SSL verification is required for the specified webhook URL. Default value is  &lt;code&gt;true&lt;/code&gt;. (optional)
     * @param url The url in which to connect to (optional)
     * @param restWebhookCredentials Basic authentication credentials, if required. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object testWebhook1(String projectKey, String repositorySlug, Integer webhookId, String sslVerificationRequired, String url, RestWebhookCredentials restWebhookCredentials) throws RestClientException {
        return testWebhook1WithHttpInfo(projectKey, repositorySlug, webhookId, sslVerificationRequired, url, restWebhookCredentials).getBody();
    }

    /**
     * Test webhook
     * Test connectivity to a specific endpoint.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to test a connection.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param webhookId  (optional)
     * @param sslVerificationRequired Whether SSL verification is required for the specified webhook URL. Default value is  &lt;code&gt;true&lt;/code&gt;. (optional)
     * @param url The url in which to connect to (optional)
     * @param restWebhookCredentials Basic authentication credentials, if required. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> testWebhook1WithHttpInfo(String projectKey, String repositorySlug, Integer webhookId, String sslVerificationRequired, String url, RestWebhookCredentials restWebhookCredentials) throws RestClientException {
        Object localVarPostBody = restWebhookCredentials;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling testWebhook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling testWebhook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "webhookId", webhookId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sslVerificationRequired", sslVerificationRequired));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));
        

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/test", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove a reaction from comment
     * Remove an emoticon reaction from a comment
     * <p><b>204</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id (required)
     * @param commitId The commit id (required)
     * @param emoticon The emoticon to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unReact(String projectKey, String commentId, String commitId, String emoticon, String repositorySlug) throws RestClientException {
        unReactWithHttpInfo(projectKey, commentId, commitId, emoticon, repositorySlug);
    }

    /**
     * Remove a reaction from comment
     * Remove an emoticon reaction from a comment
     * <p><b>204</b> - The added reaction
     * @param projectKey The project key. (required)
     * @param commentId The comment id (required)
     * @param commitId The commit id (required)
     * @param emoticon The emoticon to remove (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unReactWithHttpInfo(String projectKey, String commentId, String commitId, String emoticon, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unReact");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling unReact");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling unReact");
        }
        
        // verify the required parameter 'emoticon' is set
        if (emoticon == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'emoticon' when calling unReact");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unReact");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commentId", commentId);
        uriVariables.put("commitId", commitId);
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
        return apiClient.invokeAPI("/comment-likes/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}/reactions/{emoticon}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stop watching commit
     * Remove the authenticated user as a watcher for the specified commit.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the commit to call this resource.
     * <p><b>204</b> - The user is no longer watching the commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unwatch(String projectKey, String commitId, String repositorySlug) throws RestClientException {
        unwatchWithHttpInfo(projectKey, commitId, repositorySlug);
    }

    /**
     * Stop watching commit
     * Remove the authenticated user as a watcher for the specified commit.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the commit to call this resource.
     * <p><b>204</b> - The user is no longer watching the commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request.
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unwatchWithHttpInfo(String projectKey, String commitId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unwatch");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling unwatch");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unwatch");
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

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/watch", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stop watching repository
     * Remove the authenticated user as a watcher for the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The user is no longer watching the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unwatch2(String projectKey, String repositorySlug) throws RestClientException {
        unwatch2WithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Stop watching repository
     * Remove the authenticated user as a watcher for the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The user is no longer watching the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unwatch2WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling unwatch2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling unwatch2");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/watch", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update a commit comment
     * Update a comment, with the following restrictions:  - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment or repository admins and above may update the other   fields of a comment   &lt;strong&gt;Note:&lt;/strong&gt; the supplied supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the commit, update the comment or watch the commit.
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or comment. The missing entity will be specified in the error details.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param restComment The comment to update (optional)
     * @return RestComment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestComment updateComment(String projectKey, String commentId, String commitId, String repositorySlug, RestComment restComment) throws RestClientException {
        return updateCommentWithHttpInfo(projectKey, commentId, commitId, repositorySlug, restComment).getBody();
    }

    /**
     * Update a commit comment
     * Update a comment, with the following restrictions:  - only the author of the comment may update the &lt;i&gt;text&lt;/i&gt; of the comment - only the author of the comment or repository admins and above may update the other   fields of a comment   &lt;strong&gt;Note:&lt;/strong&gt; the supplied supplied JSON object must contain a &lt;code&gt;version&lt;/code&gt; that must match the server&#39;s version of the comment or the update will fail. To determine the current version of the comment, the comment should be fetched from the server prior to the update. Look for the &#39;version&#39; attribute in the returned JSON structure.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository that the commit is in to call this resource.
     * <p><b>200</b> - The newly updated comment.
     * <p><b>400</b> - The comment was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the commit, update the comment or watch the commit.
     * <p><b>404</b> - Unable to find the supplied project, repository, commit or comment. The missing entity will be specified in the error details.
     * <p><b>409</b> - The comment version supplied does not match the current version or the repository is archived.
     * @param projectKey The project key (required)
     * @param commentId The ID of the comment to retrieve (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @param restComment The comment to update (optional)
     * @return ResponseEntity&lt;RestComment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestComment> updateCommentWithHttpInfo(String projectKey, String commentId, String commitId, String repositorySlug, RestComment restComment) throws RestClientException {
        Object localVarPostBody = restComment;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateComment");
        }
        
        // verify the required parameter 'commentId' is set
        if (commentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commentId' when calling updateComment");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling updateComment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateComment");
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestComment> localReturnType = new ParameterizedTypeReference<RestComment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/comments/{commentId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update pull request settings
     * Update the pull request settings for the context repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the context repository to call this resource.   This resource will call all RestFragments that are registered with the key &lt;strong&gt;bitbucket.repository.settings.pullRequests&lt;/strong&gt;. If any fragment fails validations by returning a non-empty Map of errors, then no fragments will execute.   Only the settings that should be updated need to be included in the request.   The property keys for the settings that are bundled with the application are   - mergeConfig - the merge strategy configuration for pull requests - requiredApprovers - (Deprecated, please use com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook instead) the number of approvals required on a pull request for it to be mergeable, or 0 to disable the merge check - com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook - a json map containing the keys &#39;enabled&#39; (a boolean to enable or disable this merge check) and &#39;count&#39; (an integer to set the number of required approvals) - requiredAllApprovers - whether or not all approvers must approve a pull request for it to be mergeable - requiredAllTasksComplete - whether or not all tasks on a pull request need to be completed for it to be mergeable - requiredSuccessfulBuilds - (Deprecated, please use com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck instead) the number of successful builds on a pull request for it to be mergeable, or 0 to disable the merge check - com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck - a json map containing the keys &#39;enabled&#39; (a boolean to enable or disable this merge check) and &#39;count&#39; (an integer to set the number of required builds)   &lt;strong&gt;Merge strategy configuration deletion:&lt;/strong&gt;  An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:    &#x60;&#x60;&#x60;{      \&quot;mergeConfig\&quot;: {      }  }  &#x60;&#x60;&#x60;  Upon completion of this request, the effective configuration will be:   - The configuration set for this repository&#39;s SCM type as set at the project level, if present, otherwise - the configuration set for this repository&#39;s SCM type as set at the instance level, if present, otherwise - the default configuration for this repository&#39;s SCM type   
     * <p><b>200</b> - The repository pull request settings for the context repository.
     * <p><b>400</b> - The repository pull request settings were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepositoryPullRequestSettings The updated settings. (optional)
     * @return RestRepositoryPullRequestSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPullRequestSettings updatePullRequestSettings1(String projectKey, String repositorySlug, RestRepositoryPullRequestSettings restRepositoryPullRequestSettings) throws RestClientException {
        return updatePullRequestSettings1WithHttpInfo(projectKey, repositorySlug, restRepositoryPullRequestSettings).getBody();
    }

    /**
     * Update pull request settings
     * Update the pull request settings for the context repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the context repository to call this resource.   This resource will call all RestFragments that are registered with the key &lt;strong&gt;bitbucket.repository.settings.pullRequests&lt;/strong&gt;. If any fragment fails validations by returning a non-empty Map of errors, then no fragments will execute.   Only the settings that should be updated need to be included in the request.   The property keys for the settings that are bundled with the application are   - mergeConfig - the merge strategy configuration for pull requests - requiredApprovers - (Deprecated, please use com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook instead) the number of approvals required on a pull request for it to be mergeable, or 0 to disable the merge check - com.atlassian.bitbucket.server.bundled-hooks.requiredApproversMergeHook - a json map containing the keys &#39;enabled&#39; (a boolean to enable or disable this merge check) and &#39;count&#39; (an integer to set the number of required approvals) - requiredAllApprovers - whether or not all approvers must approve a pull request for it to be mergeable - requiredAllTasksComplete - whether or not all tasks on a pull request need to be completed for it to be mergeable - requiredSuccessfulBuilds - (Deprecated, please use com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck instead) the number of successful builds on a pull request for it to be mergeable, or 0 to disable the merge check - com.atlassian.bitbucket.server.bitbucket-build.requiredBuildsMergeCheck - a json map containing the keys &#39;enabled&#39; (a boolean to enable or disable this merge check) and &#39;count&#39; (an integer to set the number of required builds)   &lt;strong&gt;Merge strategy configuration deletion:&lt;/strong&gt;  An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:    &#x60;&#x60;&#x60;{      \&quot;mergeConfig\&quot;: {      }  }  &#x60;&#x60;&#x60;  Upon completion of this request, the effective configuration will be:   - The configuration set for this repository&#39;s SCM type as set at the project level, if present, otherwise - the configuration set for this repository&#39;s SCM type as set at the instance level, if present, otherwise - the default configuration for this repository&#39;s SCM type   
     * <p><b>200</b> - The repository pull request settings for the context repository.
     * <p><b>400</b> - The repository pull request settings were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepositoryPullRequestSettings The updated settings. (optional)
     * @return ResponseEntity&lt;RestRepositoryPullRequestSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPullRequestSettings> updatePullRequestSettings1WithHttpInfo(String projectKey, String repositorySlug, RestRepositoryPullRequestSettings restRepositoryPullRequestSettings) throws RestClientException {
        Object localVarPostBody = restRepositoryPullRequestSettings;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePullRequestSettings1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updatePullRequestSettings1");
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

        ParameterizedTypeReference<RestRepositoryPullRequestSettings> localReturnType = new ParameterizedTypeReference<RestRepositoryPullRequestSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/settings/pull-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Update an existing webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a webhook in this repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId Id of the existing webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param restWebhook The representation of the updated values for the webhook (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook updateWebhook1(String projectKey, String webhookId, String repositorySlug, RestWebhook restWebhook) throws RestClientException {
        return updateWebhook1WithHttpInfo(projectKey, webhookId, repositorySlug, restWebhook).getBody();
    }

    /**
     * 
     * Update an existing webhook.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a webhook in this repository.
     * <p><b>404</b> - The repository does not exist, or the webhook does not exist in the repository.
     * @param projectKey The project key. (required)
     * @param webhookId Id of the existing webhook (required)
     * @param repositorySlug The repository slug. (required)
     * @param restWebhook The representation of the updated values for the webhook (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> updateWebhook1WithHttpInfo(String projectKey, String webhookId, String repositorySlug, RestWebhook restWebhook) throws RestClientException {
        Object localVarPostBody = restWebhook;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateWebhook1");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling updateWebhook1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateWebhook1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);
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

        ParameterizedTypeReference<RestWebhook> localReturnType = new ParameterizedTypeReference<RestWebhook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/webhooks/{webhookId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Watch commit
     * Add the authenticated user as a watcher for the specified commit.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the commit to call this resource.
     * <p><b>204</b> - The user is now watching the commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void watch(String projectKey, String commitId, String repositorySlug) throws RestClientException {
        watchWithHttpInfo(projectKey, commitId, repositorySlug);
    }

    /**
     * Watch commit
     * Add the authenticated user as a watcher for the specified commit.  The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository containing the commit to call this resource.
     * <p><b>204</b> - The user is now watching the commit.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the pull request
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key (required)
     * @param commitId The &lt;i&gt;full ID&lt;/i&gt; of the commit within the repository (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> watchWithHttpInfo(String projectKey, String commitId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling watch");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling watch");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling watch");
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

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/watch", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Watch repository
     * Add the authenticated user as a watcher for the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The user is now watching the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The repository to watch. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void watch2(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        watch2WithHttpInfo(projectKey, repositorySlug, restRepository);
    }

    /**
     * Watch repository
     * Add the authenticated user as a watcher for the specified repository.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the repository to call this resource.
     * <p><b>204</b> - The user is now watching the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The repository to watch. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> watch2WithHttpInfo(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        Object localVarPostBody = restRepository;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling watch2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling watch2");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/watch", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
