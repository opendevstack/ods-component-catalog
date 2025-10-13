package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetPage200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetReports200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildStats;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildStatus;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildStatusSetRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBulkAddInsightAnnotationRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDeployment;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDeploymentSetRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestInsightAnnotationsResponse;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestInsightReport;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRequiredBuildCondition;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRequiredBuildConditionSetRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSetInsightReportRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSingleAddInsightAnnotationRequest;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.BuildsAndDeploymentsApi")
public class BuildsAndDeploymentsApi extends BaseApi {

    public BuildsAndDeploymentsApi() {
        super(new ApiClient());
    }

    @Autowired
    public BuildsAndDeploymentsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Store a build status
     * Store a build status.   The authenticated user must have **REPO_READ** permission for the repository that this build status is for. The request can also be made with anonymous 2-legged OAuth.
     * <p><b>204</b> - The build status was posted
     * <p><b>400</b> - The build status was not added as the request was invalid. This could be because of a number of things:   - an invalid commit hash was provided - build key was blank or longer than 255 characters - invalid branch was provided - invalid state was provided - build status url was blank or longer than 450 characters  The specifics will be included in the error message.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to push a build status to this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBuildStatusSetRequest The contents of the build status request are: These fields are **required**:   - **key**: The string referring to this branch plan/job - **state**: The build status state, one of: \&quot;SUCCESSFUL\&quot;, \&quot;FAILED\&quot;, \&quot;INPROGRESS\&quot;, \&quot;CANCELLED\&quot;, \&quot;UNKNOWN\&quot; - **url**: URL referring to the build result page in the CI tool.   These fields are optional:   - **buildNumber** (optional): A unique identifier for this particular run of a plan&lt; - **dateAdded** (optional): milliseconds since epoch. If not provided current date is used. - **description** (optional): Describes the build result - **duration** (optional): Duration of a completed build in milliseconds. - **name** (optional): A short string that describes the build plan - **parent** (optional): The identifier for the plan or job that ran the branch plan that produced this build status. - **ref** (optional): The fully qualified git reference e.g. refs/heads/master. - **testResults** (optional): A summary of the passed, failed and skipped tests.  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void add(String projectKey, String commitId, String repositorySlug, RestBuildStatusSetRequest restBuildStatusSetRequest) throws RestClientException {
        addWithHttpInfo(projectKey, commitId, repositorySlug, restBuildStatusSetRequest);
    }

    /**
     * Store a build status
     * Store a build status.   The authenticated user must have **REPO_READ** permission for the repository that this build status is for. The request can also be made with anonymous 2-legged OAuth.
     * <p><b>204</b> - The build status was posted
     * <p><b>400</b> - The build status was not added as the request was invalid. This could be because of a number of things:   - an invalid commit hash was provided - build key was blank or longer than 255 characters - invalid branch was provided - invalid state was provided - build status url was blank or longer than 450 characters  The specifics will be included in the error message.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to push a build status to this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBuildStatusSetRequest The contents of the build status request are: These fields are **required**:   - **key**: The string referring to this branch plan/job - **state**: The build status state, one of: \&quot;SUCCESSFUL\&quot;, \&quot;FAILED\&quot;, \&quot;INPROGRESS\&quot;, \&quot;CANCELLED\&quot;, \&quot;UNKNOWN\&quot; - **url**: URL referring to the build result page in the CI tool.   These fields are optional:   - **buildNumber** (optional): A unique identifier for this particular run of a plan&lt; - **dateAdded** (optional): milliseconds since epoch. If not provided current date is used. - **description** (optional): Describes the build result - **duration** (optional): Duration of a completed build in milliseconds. - **name** (optional): A short string that describes the build plan - **parent** (optional): The identifier for the plan or job that ran the branch plan that produced this build status. - **ref** (optional): The fully qualified git reference e.g. refs/heads/master. - **testResults** (optional): A summary of the passed, failed and skipped tests.  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addWithHttpInfo(String projectKey, String commitId, String repositorySlug, RestBuildStatusSetRequest restBuildStatusSetRequest) throws RestClientException {
        Object localVarPostBody = restBuildStatusSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling add");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling add");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling add");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/builds", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add Code Insights annotations
     * Add annotations to the given report. The request should be a JSON object mapping the string \&quot;annotations\&quot; to an array of maps containing the annotation data, as described below. See also the example request.  A few things to note:- Annotations are an extension of a report, so a report must first exist in order to post annotations.   Annotations are posted separately from the report, and can be posted in bulk using this endpoint. - Only the annotations that are on lines changed in the unified diff will be displayed. This means it is  likely not all annotations posted will be displayed on the pull request  It also means that if the user is viewing a side-by-side diff,  commit diff or iterative review diff they will not be able to view the annotations. - A report cannot have more than 1000 annotations by default, however this property is congurable at an  instance level. If the request would result in more than the maximum number of annotations being stored  then the entire request is rejected and no new annotations are stored.  - There is no de-duplication of annotations on Bitbucket so be sure that reruns of builds will first  delete the report and annotations before creating them.  # Annotation parameters  |Parameter|Description|Required?|Restrictions|Type| |--- |--- |--- |--- |--- | |path|The path of the file on which this annotation should be placed. This is the path of the filerelative to the git repository. If no path is provided, then it will appear in the overview modalon all pull requests where the tip of the branch is the given commit, regardless of which files weremodified.|No||String| |line|The line number that the annotation should belong to. If no line number is provided, then it willdefault to 0 and in a pull request it will appear at the top of the file specified by the path field.|No|Non-negative integer|Integer| |message|The message to display to users|Yes|The maximum length accepted is 2000 characters, however the user interface may truncate this valuefor display purposes. We recommend that the message is short and succinct, with further detailsavailable to the user if needed on the page linked to by the the annotation link.|String| |severity|The severity of the annotation|Yes|One of: LOW, MEDIUM, HIGH|String| |link|An http or https URL representing the location of the annotation in the external tool|No||String| |type|The type of annotation posted|No|One of: VULNERABILITY, CODE_SMELL, BUG|String| |externalId|If the caller requires a link to get or modify this annotation, then an ID must be provided. It isnot used or required by Bitbucket, but only by the annotation creator for updating or deleting thisspecific annotation.|No|A string value shorter than 450 characters|String|
     * <p><b>204</b> - An empty response indicating that the request succeeded.
     * <p><b>401</b> - The currently authenticated user is not the author of the report, or the author no longer has sufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @param restBulkAddInsightAnnotationRequest The annotations to add. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void addAnnotations(String projectKey, String commitId, String repositorySlug, String key, RestBulkAddInsightAnnotationRequest restBulkAddInsightAnnotationRequest) throws RestClientException {
        addAnnotationsWithHttpInfo(projectKey, commitId, repositorySlug, key, restBulkAddInsightAnnotationRequest);
    }

    /**
     * Add Code Insights annotations
     * Add annotations to the given report. The request should be a JSON object mapping the string \&quot;annotations\&quot; to an array of maps containing the annotation data, as described below. See also the example request.  A few things to note:- Annotations are an extension of a report, so a report must first exist in order to post annotations.   Annotations are posted separately from the report, and can be posted in bulk using this endpoint. - Only the annotations that are on lines changed in the unified diff will be displayed. This means it is  likely not all annotations posted will be displayed on the pull request  It also means that if the user is viewing a side-by-side diff,  commit diff or iterative review diff they will not be able to view the annotations. - A report cannot have more than 1000 annotations by default, however this property is congurable at an  instance level. If the request would result in more than the maximum number of annotations being stored  then the entire request is rejected and no new annotations are stored.  - There is no de-duplication of annotations on Bitbucket so be sure that reruns of builds will first  delete the report and annotations before creating them.  # Annotation parameters  |Parameter|Description|Required?|Restrictions|Type| |--- |--- |--- |--- |--- | |path|The path of the file on which this annotation should be placed. This is the path of the filerelative to the git repository. If no path is provided, then it will appear in the overview modalon all pull requests where the tip of the branch is the given commit, regardless of which files weremodified.|No||String| |line|The line number that the annotation should belong to. If no line number is provided, then it willdefault to 0 and in a pull request it will appear at the top of the file specified by the path field.|No|Non-negative integer|Integer| |message|The message to display to users|Yes|The maximum length accepted is 2000 characters, however the user interface may truncate this valuefor display purposes. We recommend that the message is short and succinct, with further detailsavailable to the user if needed on the page linked to by the the annotation link.|String| |severity|The severity of the annotation|Yes|One of: LOW, MEDIUM, HIGH|String| |link|An http or https URL representing the location of the annotation in the external tool|No||String| |type|The type of annotation posted|No|One of: VULNERABILITY, CODE_SMELL, BUG|String| |externalId|If the caller requires a link to get or modify this annotation, then an ID must be provided. It isnot used or required by Bitbucket, but only by the annotation creator for updating or deleting thisspecific annotation.|No|A string value shorter than 450 characters|String|
     * <p><b>204</b> - An empty response indicating that the request succeeded.
     * <p><b>401</b> - The currently authenticated user is not the author of the report, or the author no longer has sufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @param restBulkAddInsightAnnotationRequest The annotations to add. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addAnnotationsWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key, RestBulkAddInsightAnnotationRequest restBulkAddInsightAnnotationRequest) throws RestClientException {
        Object localVarPostBody = restBulkAddInsightAnnotationRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling addAnnotations");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling addAnnotations");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling addAnnotations");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling addAnnotations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}/annotations", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a required builds merge check
     * Create a required build merge check for the given repository.  The authenticated user must have **REPO_ADMIN** permission for the target repository to register a required build merge check.  The contents of the required build merge check request are:  These fields are **required**:  - **buildParentKeys**: A non-empty list of build parent keys that require green builds for this merge check to pass - **refMatcher.id**: The value to match refs against in the target branch - **refMatcher.type.id**: The type of ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   These fields are optional:  - **exemptRefMatcher.id** The value to exempt refs in the source branch from this check - **exemptRefMatcher.type.id**: The type of exempt ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   
     * <p><b>200</b> - A response containing the newly created required build merge check.
     * <p><b>400</b> - The request was malformed. This could be caused because:  - The build parent key list is empty - Either of the provided ref matchers is of an unrecognized type - Either of the provided ref matchers could not be created with the provided type and id   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param repositorySlug The repository being used (required)
     * @param restRequiredBuildConditionSetRequest The request specifying the required build parent keys, ref matcher and exemption matcher (optional)
     * @return RestRequiredBuildCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRequiredBuildCondition create(String projectKey, String repositorySlug, RestRequiredBuildConditionSetRequest restRequiredBuildConditionSetRequest) throws RestClientException {
        return createWithHttpInfo(projectKey, repositorySlug, restRequiredBuildConditionSetRequest).getBody();
    }

    /**
     * Create a required builds merge check
     * Create a required build merge check for the given repository.  The authenticated user must have **REPO_ADMIN** permission for the target repository to register a required build merge check.  The contents of the required build merge check request are:  These fields are **required**:  - **buildParentKeys**: A non-empty list of build parent keys that require green builds for this merge check to pass - **refMatcher.id**: The value to match refs against in the target branch - **refMatcher.type.id**: The type of ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   These fields are optional:  - **exemptRefMatcher.id** The value to exempt refs in the source branch from this check - **exemptRefMatcher.type.id**: The type of exempt ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   
     * <p><b>200</b> - A response containing the newly created required build merge check.
     * <p><b>400</b> - The request was malformed. This could be caused because:  - The build parent key list is empty - Either of the provided ref matchers is of an unrecognized type - Either of the provided ref matchers could not be created with the provided type and id   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param repositorySlug The repository being used (required)
     * @param restRequiredBuildConditionSetRequest The request specifying the required build parent keys, ref matcher and exemption matcher (optional)
     * @return ResponseEntity&lt;RestRequiredBuildCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRequiredBuildCondition> createWithHttpInfo(String projectKey, String repositorySlug, RestRequiredBuildConditionSetRequest restRequiredBuildConditionSetRequest) throws RestClientException {
        Object localVarPostBody = restRequiredBuildConditionSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling create");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling create");
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

        ParameterizedTypeReference<RestRequiredBuildCondition> localReturnType = new ParameterizedTypeReference<RestRequiredBuildCondition>() {};
        return apiClient.invokeAPI("/required-builds/latest/projects/{projectKey}/repos/{repositorySlug}/condition", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create or update a deployment
     * Create or update a deployment.    The authenticated user must have REPO_READ permission for the repository.
     * <p><b>200</b> - The deployment was created
     * <p><b>400</b> - the deployment was not created because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the path (required)
     * @param repositorySlug The repository slug (required)
     * @param restDeploymentSetRequest the details of the deployment to create, as detailed by the request body (optional)
     * @return RestDeployment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDeployment createOrUpdateDeployment(String projectKey, String commitId, String repositorySlug, RestDeploymentSetRequest restDeploymentSetRequest) throws RestClientException {
        return createOrUpdateDeploymentWithHttpInfo(projectKey, commitId, repositorySlug, restDeploymentSetRequest).getBody();
    }

    /**
     * Create or update a deployment
     * Create or update a deployment.    The authenticated user must have REPO_READ permission for the repository.
     * <p><b>200</b> - The deployment was created
     * <p><b>400</b> - the deployment was not created because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the path (required)
     * @param repositorySlug The repository slug (required)
     * @param restDeploymentSetRequest the details of the deployment to create, as detailed by the request body (optional)
     * @return ResponseEntity&lt;RestDeployment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDeployment> createOrUpdateDeploymentWithHttpInfo(String projectKey, String commitId, String repositorySlug, RestDeploymentSetRequest restDeploymentSetRequest) throws RestClientException {
        Object localVarPostBody = restDeploymentSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createOrUpdateDeployment");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling createOrUpdateDeployment");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createOrUpdateDeployment");
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

        ParameterizedTypeReference<RestDeployment> localReturnType = new ParameterizedTypeReference<RestDeployment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/deployments", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a Code Insights report
     * Delete a report for the given commit. Also deletes any annotations associated with this report.
     * <p><b>204</b> - The report and associated annotations were successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete insight reports or was not the author (&lt;code&gt;REPO_READ&lt;/code&gt; for author otherwise &lt;code&gt;REPO_ADMIN&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        deleteWithHttpInfo(projectKey, commitId, repositorySlug, key);
    }

    /**
     * Delete a Code Insights report
     * Delete a report for the given commit. Also deletes any annotations associated with this report.
     * <p><b>204</b> - The report and associated annotations were successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete insight reports or was not the author (&lt;code&gt;REPO_READ&lt;/code&gt; for author otherwise &lt;code&gt;REPO_ADMIN&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling delete");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling delete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a specific build status
     * Delete a specific build status.   The authenticated user must have **REPO_ADMIN** permission for the provided repository.
     * <p><b>204</b> - The build status associated with the provided commit and key has been deleted
     * <p><b>400</b> - The request has failed validation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key the key of the build status (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete2(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        delete2WithHttpInfo(projectKey, commitId, repositorySlug, key);
    }

    /**
     * Delete a specific build status
     * Delete a specific build status.   The authenticated user must have **REPO_ADMIN** permission for the provided repository.
     * <p><b>204</b> - The build status associated with the provided commit and key has been deleted
     * <p><b>400</b> - The request has failed validation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key the key of the build status (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete2WithHttpInfo(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete2");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling delete2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete2");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling delete2");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/builds", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a deployment
     * Delete the deployment matching the specified Repository, key, environmentKey and deploymentSequenceNumber.   The user must have REPO_ADMIN.
     * <p><b>204</b> - the request has been processed
     * <p><b>400</b> - the deployment was not deleted because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a deployment
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the path (required)
     * @param repositorySlug The repository slug (required)
     * @param deploymentSequenceNumber the sequence number of the deployment, as detailed by the query parameter (optional)
     * @param key the key of the deployment, as detailed by the query parameter (optional)
     * @param environmentKey the key of the environment, as detailed by the query parameter (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete3(String projectKey, String commitId, String repositorySlug, String deploymentSequenceNumber, String key, String environmentKey) throws RestClientException {
        delete3WithHttpInfo(projectKey, commitId, repositorySlug, deploymentSequenceNumber, key, environmentKey);
    }

    /**
     * Delete a deployment
     * Delete the deployment matching the specified Repository, key, environmentKey and deploymentSequenceNumber.   The user must have REPO_ADMIN.
     * <p><b>204</b> - the request has been processed
     * <p><b>400</b> - the deployment was not deleted because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a deployment
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the path (required)
     * @param repositorySlug The repository slug (required)
     * @param deploymentSequenceNumber the sequence number of the deployment, as detailed by the query parameter (optional)
     * @param key the key of the deployment, as detailed by the query parameter (optional)
     * @param environmentKey the key of the environment, as detailed by the query parameter (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete3WithHttpInfo(String projectKey, String commitId, String repositorySlug, String deploymentSequenceNumber, String key, String environmentKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling delete3");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling delete3");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling delete3");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentSequenceNumber", deploymentSequenceNumber));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "environmentKey", environmentKey));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/deployments", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete Code Insights annotations
     * Delete annotations for a given report that match the given external IDs, or all annotations if no external IDs are provided.
     * <p><b>204</b> - The annotations were successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete insight reports or was not the author (&lt;code&gt;REPO_READ&lt;/code&gt; for author otherwise &lt;code&gt;REPO_ADMIN&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @param externalId The external IDs for the annotations that are to be deleted. Can be specified more than once to delete by more than one external ID, or can be unspecified to delete all annotations. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAnnotations(String projectKey, String commitId, String repositorySlug, String key, String externalId) throws RestClientException {
        deleteAnnotationsWithHttpInfo(projectKey, commitId, repositorySlug, key, externalId);
    }

    /**
     * Delete Code Insights annotations
     * Delete annotations for a given report that match the given external IDs, or all annotations if no external IDs are provided.
     * <p><b>204</b> - The annotations were successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete insight reports or was not the author (&lt;code&gt;REPO_READ&lt;/code&gt; for author otherwise &lt;code&gt;REPO_ADMIN&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs. (required)
     * @param externalId The external IDs for the annotations that are to be deleted. Can be specified more than once to delete by more than one external ID, or can be unspecified to delete all annotations. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAnnotationsWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key, String externalId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAnnotations");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling deleteAnnotations");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteAnnotations");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling deleteAnnotations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalId", externalId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}/annotations", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a required builds merge check
     * Deletes a required build existing merge check, given it&#39;s ID.  The authenticated user must have **REPO_ADMIN** permission for the target repository to delete a required build merge check.
     * <p><b>204</b> - An empty response indicating the merge check was successfully deleted, or was never present.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param id  (required)
     * @param repositorySlug The repository being used (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRequiredBuildsMergeCheck(String projectKey, Long id, String repositorySlug) throws RestClientException {
        deleteRequiredBuildsMergeCheckWithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete a required builds merge check
     * Deletes a required build existing merge check, given it&#39;s ID.  The authenticated user must have **REPO_ADMIN** permission for the target repository to delete a required build merge check.
     * <p><b>204</b> - An empty response indicating the merge check was successfully deleted, or was never present.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param id  (required)
     * @param repositorySlug The repository being used (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRequiredBuildsMergeCheckWithHttpInfo(String projectKey, Long id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRequiredBuildsMergeCheck");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRequiredBuildsMergeCheck");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteRequiredBuildsMergeCheck");
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
        return apiClient.invokeAPI("/required-builds/latest/projects/{projectKey}/repos/{repositorySlug}/condition/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a Code Insights report
     * Retrieve the specified report.
     * <p><b>200</b> - The specified report.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ needed&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The report key. (required)
     * @return RestInsightReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestInsightReport get(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        return getWithHttpInfo(projectKey, commitId, repositorySlug, key).getBody();
    }

    /**
     * Get a Code Insights report
     * Retrieve the specified report.
     * <p><b>200</b> - The specified report.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ needed&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The report key. (required)
     * @return ResponseEntity&lt;RestInsightReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestInsightReport> getWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling get");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling get");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling get");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling get");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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

        ParameterizedTypeReference<RestInsightReport> localReturnType = new ParameterizedTypeReference<RestInsightReport>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a specific build status
     * Get a specific build status.   The authenticated user must have **REPO_READ** permission for the provided repository.The request can also be made with anonymous 2-legged OAuth.&lt;br&gt;Since 7.14
     * <p><b>200</b> - The build status associated with the provided commit and key
     * <p><b>400</b> - The request has failed validation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions this repository
     * <p><b>404</b> - The specified repository, commit or build status does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key the key of the build status (required)
     * @return RestBuildStatus
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBuildStatus get2(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        return get2WithHttpInfo(projectKey, commitId, repositorySlug, key).getBody();
    }

    /**
     * Get a specific build status
     * Get a specific build status.   The authenticated user must have **REPO_READ** permission for the provided repository.The request can also be made with anonymous 2-legged OAuth.&lt;br&gt;Since 7.14
     * <p><b>200</b> - The build status associated with the provided commit and key
     * <p><b>400</b> - The request has failed validation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions this repository
     * <p><b>404</b> - The specified repository, commit or build status does not exist
     * @param projectKey The project key. (required)
     * @param commitId The commit. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key the key of the build status (required)
     * @return ResponseEntity&lt;RestBuildStatus&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBuildStatus> get2WithHttpInfo(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling get2");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling get2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling get2");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling get2");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestBuildStatus> localReturnType = new ParameterizedTypeReference<RestBuildStatus>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/builds", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a deployment
     * Get the deployment matching the specified Repository, key, environmentKey and deploymentSequenceNumber.   The user must have REPO_READ.
     * <p><b>200</b> - The deployment
     * <p><b>400</b> - could not get the deployment because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The specified repository or deployment does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the query parameter (required)
     * @param repositorySlug The repository slug (required)
     * @param deploymentSequenceNumber the sequence number of the deployment, as detailed by the query param (optional)
     * @param key the key of the deployment, as detailed by the query parameter (optional)
     * @param environmentKey the key of the environment, as detailed by the query parameter (optional)
     * @return RestDeployment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDeployment get3(String projectKey, String commitId, String repositorySlug, String deploymentSequenceNumber, String key, String environmentKey) throws RestClientException {
        return get3WithHttpInfo(projectKey, commitId, repositorySlug, deploymentSequenceNumber, key, environmentKey).getBody();
    }

    /**
     * Get a deployment
     * Get the deployment matching the specified Repository, key, environmentKey and deploymentSequenceNumber.   The user must have REPO_READ.
     * <p><b>200</b> - The deployment
     * <p><b>400</b> - could not get the deployment because the request was invalid
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the repository
     * <p><b>404</b> - The specified repository or deployment does not exist
     * @param projectKey The project key (required)
     * @param commitId the commitId that was deployed as indicated by the query parameter (required)
     * @param repositorySlug The repository slug (required)
     * @param deploymentSequenceNumber the sequence number of the deployment, as detailed by the query param (optional)
     * @param key the key of the deployment, as detailed by the query parameter (optional)
     * @param environmentKey the key of the environment, as detailed by the query parameter (optional)
     * @return ResponseEntity&lt;RestDeployment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDeployment> get3WithHttpInfo(String projectKey, String commitId, String repositorySlug, String deploymentSequenceNumber, String key, String environmentKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling get3");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling get3");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling get3");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentSequenceNumber", deploymentSequenceNumber));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "environmentKey", environmentKey));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestDeployment> localReturnType = new ParameterizedTypeReference<RestDeployment>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/deployments", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Code Insights annotations for a report
     * Retrieve the specified report&#39;s annotations.
     * <p><b>200</b> - The specified annotations.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ needed&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The report key. (required)
     * @return RestInsightAnnotationsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestInsightAnnotationsResponse getAnnotations(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        return getAnnotationsWithHttpInfo(projectKey, commitId, repositorySlug, key).getBody();
    }

    /**
     * Get Code Insights annotations for a report
     * Retrieve the specified report&#39;s annotations.
     * <p><b>200</b> - The specified annotations.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ needed&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The report key. (required)
     * @return ResponseEntity&lt;RestInsightAnnotationsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestInsightAnnotationsResponse> getAnnotationsWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAnnotations");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getAnnotations");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAnnotations");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getAnnotations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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

        ParameterizedTypeReference<RestInsightAnnotationsResponse> localReturnType = new ParameterizedTypeReference<RestInsightAnnotationsResponse>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}/annotations", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Code Insights annotations for a commit
     * Get annotations for the given commit ID, filtered by any query parameters given.
     * <p><b>200</b> - The requested annotations.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;) to get insight annotations.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param severity Return only annotations that have one of the given severities. Can be specified more than once to filter by more than one severity. Valid severities are &lt;code&gt;LOW&lt;/code&gt;, &lt;code&gt;MEDIUM&lt;/code&gt; and &lt;code&gt;HIGH&lt;/code&gt;. (optional)
     * @param path Return only annotations that appear on one of the provided paths. Can be specified more than once to filter by more than one path. (optional)
     * @param externalId Return only annotations that have one of the provided external IDs. Can be specified more than once to filter by more than one external ID. (optional)
     * @param type Return only annotations that have one of the given types. Can be specified more than once to filter by multiple types. Valid types are &lt;code&gt;BUG&lt;/code&gt;, &lt;code&gt;CODE_SMELL&lt;/code&gt;, and &lt;code&gt;VULNERABILITY&lt;/code&gt;. (optional)
     * @param key Return only annotations that belong to one of the provided report keys. Can be specified more than once to filter by more than one report (optional)
     * @return RestInsightAnnotationsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestInsightAnnotationsResponse getAnnotations1(String projectKey, String commitId, String repositorySlug, String severity, String path, String externalId, String type, String key) throws RestClientException {
        return getAnnotations1WithHttpInfo(projectKey, commitId, repositorySlug, severity, path, externalId, type, key).getBody();
    }

    /**
     * Get Code Insights annotations for a commit
     * Get annotations for the given commit ID, filtered by any query parameters given.
     * <p><b>200</b> - The requested annotations.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;) to get insight annotations.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param severity Return only annotations that have one of the given severities. Can be specified more than once to filter by more than one severity. Valid severities are &lt;code&gt;LOW&lt;/code&gt;, &lt;code&gt;MEDIUM&lt;/code&gt; and &lt;code&gt;HIGH&lt;/code&gt;. (optional)
     * @param path Return only annotations that appear on one of the provided paths. Can be specified more than once to filter by more than one path. (optional)
     * @param externalId Return only annotations that have one of the provided external IDs. Can be specified more than once to filter by more than one external ID. (optional)
     * @param type Return only annotations that have one of the given types. Can be specified more than once to filter by multiple types. Valid types are &lt;code&gt;BUG&lt;/code&gt;, &lt;code&gt;CODE_SMELL&lt;/code&gt;, and &lt;code&gt;VULNERABILITY&lt;/code&gt;. (optional)
     * @param key Return only annotations that belong to one of the provided report keys. Can be specified more than once to filter by more than one report (optional)
     * @return ResponseEntity&lt;RestInsightAnnotationsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestInsightAnnotationsResponse> getAnnotations1WithHttpInfo(String projectKey, String commitId, String repositorySlug, String severity, String path, String externalId, String type, String key) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAnnotations1");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getAnnotations1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAnnotations1");
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

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "severity", severity));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "path", path));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalId", externalId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestInsightAnnotationsResponse> localReturnType = new ParameterizedTypeReference<RestInsightAnnotationsResponse>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/annotations", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Get required builds merge checks
     * Returns a page of required build merge checks that have been configured for this repository.  The authenticated user must have **REPO_READ** permission for the target repository to request a page of required build merge checks.
     * <p><b>200</b> - The required build merge checks associated with the provided repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to request a page of required build merge checks in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param repositorySlug The repository being used (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetPage200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetPage200Response getPage(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getPageWithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get required builds merge checks
     * Returns a page of required build merge checks that have been configured for this repository.  The authenticated user must have **REPO_READ** permission for the target repository to request a page of required build merge checks.
     * <p><b>200</b> - The required build merge checks associated with the provided repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to request a page of required build merge checks in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param repositorySlug The repository being used (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetPage200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetPage200Response> getPageWithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPage");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getPage");
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

        ParameterizedTypeReference<GetPage200Response> localReturnType = new ParameterizedTypeReference<GetPage200Response>() {};
        return apiClient.invokeAPI("/required-builds/latest/projects/{projectKey}/repos/{repositorySlug}/conditions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all Code Insights reports for a commit
     * Retrieve all reports for the given commit.
     * <p><b>200</b> - A page of reports
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetReports200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetReports200Response getReports(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getReportsWithHttpInfo(projectKey, commitId, repositorySlug, start, limit).getBody();
    }

    /**
     * Get all Code Insights reports for a commit
     * Retrieve all reports for the given commit.
     * <p><b>200</b> - A page of reports
     * <p><b>401</b> - The currently authenticated user has insufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;) to get insight reports.
     * <p><b>404</b> - The specified project, repository or commit does not exist.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetReports200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetReports200Response> getReportsWithHttpInfo(String projectKey, String commitId, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getReports");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling getReports");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getReports");
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

        ParameterizedTypeReference<GetReports200Response> localReturnType = new ParameterizedTypeReference<GetReports200Response>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a Code Insights report
     * Create a new insight report, or replace the existing one if a report already exists for the given repository, commit, and report key. A request to replace an existing report will be rejected if the authenticated user was not the creator of the specified report.  The report key should be a unique string chosen by the reporter and should be unique enough not to potentially clash with report keys from other reporters. We recommend using reverse DNS namespacing or a similar standard to ensure that collision is avoided.&lt;h1&gt;Report parameters&lt;/h1&gt;&lt;table summary&#x3D;\&quot;Report parameters\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Parameter&lt;/th&gt;        &lt;th&gt;Description&lt;/th&gt;        &lt;th&gt;Required?&lt;/th&gt;        &lt;th&gt;Restrictions&lt;/th&gt;        &lt;th&gt;Type&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;title&lt;/td&gt;        &lt;td&gt;A short string representing the name of the report&lt;/td&gt;        &lt;td&gt;Yes&lt;/td&gt;        &lt;td&gt;Max length: 450 characters (but we recommend that it is shorter so that the display is nicer)&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;details&lt;/td&gt;        &lt;td&gt;             A string to describe the purpose of the report. This string may contain             escaped newlines and if it does it will display the content accordingly.        &lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Max length: 2000 characters&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;result&lt;/td&gt;        &lt;td&gt;Indicates whether the report is in a passed or failed state&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;One of: PASS, FAIL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;data&lt;/td&gt;        &lt;td&gt;An array of data fields (described below) to display information on the report&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Maximum 6 data fields&lt;/td&gt;        &lt;td&gt;Array&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;reporter&lt;/td&gt;        &lt;td&gt;A string to describe the tool or company who created the report&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Max length: 450 characters&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;link&lt;/td&gt;        &lt;td&gt;A URL linking to the results of the report in an external tool.&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Must be a valid http or https URL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;logoUrl&lt;/td&gt;        &lt;td&gt;A URL to the report logo. If none is provided, the default insights logo will be used.&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Must be a valid http or https URL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;&lt;h1&gt;Data parameters&lt;/h1&gt;The data field on the report is an array with at most 6 data fields (JSON maps) containing information that is to be displayed on the report (see the request example).&lt;table summary&#x3D;\&quot;Data parameters\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Parameter&lt;/th&gt;        &lt;th&gt;Description&lt;/th&gt;        &lt;th&gt;Type&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;title&lt;/td&gt;        &lt;td&gt;A string describing what this data field represents&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;type&lt;/td&gt;        &lt;td&gt;             The type of data contained in the value field. If not provided,             then the value will be detected as a boolean, number or string.             One of: BOOLEAN, DATE, DURATION, LINK, NUMBER, PERCENTAGE, TEXT        &lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;value&lt;/td&gt;        &lt;td&gt;            A value based on the type provided. Either a raw value             (string, number or boolean) or a map. See below.        &lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;&lt;table summary&#x3D;\&quot;Types\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Type Field&lt;/th&gt;        &lt;th&gt;Value Field Type&lt;/th&gt;        &lt;th&gt;Value Field Display&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;None/Omitted&lt;/td&gt;        &lt;td&gt;Number, String or Boolean (not an array or object)&lt;/td&gt;        &lt;td&gt;Plain text&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;BOOLEAN&lt;/td&gt;        &lt;td&gt;Boolean&lt;/td&gt;        &lt;td&gt;The value will be read as a JSON boolean and displayed as &#39;Yes&#39; or &#39;No&#39;.&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;DATE&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number in the form of a Unix timestamp              (milliseconds) and will be displayed as a relative date if the date is less             than one week ago, otherwise it will be displayed as an absolute date.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;DURATION&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number in milliseconds and             will be displayed in a human readable duration format.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;LINK&lt;/td&gt;        &lt;td&gt;Object: {\&quot;linktext\&quot;: \&quot;Link text here\&quot;, \&quot;href\&quot;: \&quot;https://link.to.annotation/in/external/tool\&quot;}&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON object containing the fields \&quot;linktext\&quot;             and \&quot;href\&quot; and will be displayed as a clickable link on the report.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;NUMBER&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number and large numbers will             be displayed in a human readable format (e.g. 14.3k).        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;PERCENTAGE&lt;/td&gt;        &lt;td&gt;Number (between 0 and 100)&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number between 0 and 100              and will be displayed with a percentage sign.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;TEXT&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;        &lt;td&gt;The value will be read as a JSON string and will be displayed as-is&lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;
     * <p><b>200</b> - The created report.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):  - The request does not contain a report title. - The data field contains unsupported objects. - The request does not contain a report key/ - The provided commit hash is invalid, according to  the validation rules mentioned for the commitId above. 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an insight report or authentication failed.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key A unique string representing the report as chosen by the reporter. This should be unique enough to not clash with other report&#39;s keys. To do this, we recommend namespacing the key using reverse DNS (required)
     * @param restSetInsightReportRequest The request object containing the details of the report to create (see example). (optional)
     * @return RestInsightReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * Tutorial adding Code Insights to your CI system
     * @see <a href="https://developer.atlassian.com/server/bitbucket/tutorials-and-examples/code-insights-tutorial/">Create a Code Insights report Documentation</a>
     */
    public RestInsightReport set(String projectKey, String commitId, String repositorySlug, String key, RestSetInsightReportRequest restSetInsightReportRequest) throws RestClientException {
        return setWithHttpInfo(projectKey, commitId, repositorySlug, key, restSetInsightReportRequest).getBody();
    }

    /**
     * Create a Code Insights report
     * Create a new insight report, or replace the existing one if a report already exists for the given repository, commit, and report key. A request to replace an existing report will be rejected if the authenticated user was not the creator of the specified report.  The report key should be a unique string chosen by the reporter and should be unique enough not to potentially clash with report keys from other reporters. We recommend using reverse DNS namespacing or a similar standard to ensure that collision is avoided.&lt;h1&gt;Report parameters&lt;/h1&gt;&lt;table summary&#x3D;\&quot;Report parameters\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Parameter&lt;/th&gt;        &lt;th&gt;Description&lt;/th&gt;        &lt;th&gt;Required?&lt;/th&gt;        &lt;th&gt;Restrictions&lt;/th&gt;        &lt;th&gt;Type&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;title&lt;/td&gt;        &lt;td&gt;A short string representing the name of the report&lt;/td&gt;        &lt;td&gt;Yes&lt;/td&gt;        &lt;td&gt;Max length: 450 characters (but we recommend that it is shorter so that the display is nicer)&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;details&lt;/td&gt;        &lt;td&gt;             A string to describe the purpose of the report. This string may contain             escaped newlines and if it does it will display the content accordingly.        &lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Max length: 2000 characters&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;result&lt;/td&gt;        &lt;td&gt;Indicates whether the report is in a passed or failed state&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;One of: PASS, FAIL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;data&lt;/td&gt;        &lt;td&gt;An array of data fields (described below) to display information on the report&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Maximum 6 data fields&lt;/td&gt;        &lt;td&gt;Array&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;reporter&lt;/td&gt;        &lt;td&gt;A string to describe the tool or company who created the report&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Max length: 450 characters&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;link&lt;/td&gt;        &lt;td&gt;A URL linking to the results of the report in an external tool.&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Must be a valid http or https URL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;logoUrl&lt;/td&gt;        &lt;td&gt;A URL to the report logo. If none is provided, the default insights logo will be used.&lt;/td&gt;        &lt;td&gt;No&lt;/td&gt;        &lt;td&gt;Must be a valid http or https URL&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;&lt;h1&gt;Data parameters&lt;/h1&gt;The data field on the report is an array with at most 6 data fields (JSON maps) containing information that is to be displayed on the report (see the request example).&lt;table summary&#x3D;\&quot;Data parameters\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Parameter&lt;/th&gt;        &lt;th&gt;Description&lt;/th&gt;        &lt;th&gt;Type&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;title&lt;/td&gt;        &lt;td&gt;A string describing what this data field represents&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;type&lt;/td&gt;        &lt;td&gt;             The type of data contained in the value field. If not provided,             then the value will be detected as a boolean, number or string.             One of: BOOLEAN, DATE, DURATION, LINK, NUMBER, PERCENTAGE, TEXT        &lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;value&lt;/td&gt;        &lt;td&gt;            A value based on the type provided. Either a raw value             (string, number or boolean) or a map. See below.        &lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;&lt;table summary&#x3D;\&quot;Types\&quot;&gt;    &lt;tr&gt;        &lt;th&gt;Type Field&lt;/th&gt;        &lt;th&gt;Value Field Type&lt;/th&gt;        &lt;th&gt;Value Field Display&lt;/th&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;None/Omitted&lt;/td&gt;        &lt;td&gt;Number, String or Boolean (not an array or object)&lt;/td&gt;        &lt;td&gt;Plain text&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;BOOLEAN&lt;/td&gt;        &lt;td&gt;Boolean&lt;/td&gt;        &lt;td&gt;The value will be read as a JSON boolean and displayed as &#39;Yes&#39; or &#39;No&#39;.&lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;DATE&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number in the form of a Unix timestamp              (milliseconds) and will be displayed as a relative date if the date is less             than one week ago, otherwise it will be displayed as an absolute date.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;DURATION&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number in milliseconds and             will be displayed in a human readable duration format.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;LINK&lt;/td&gt;        &lt;td&gt;Object: {\&quot;linktext\&quot;: \&quot;Link text here\&quot;, \&quot;href\&quot;: \&quot;https://link.to.annotation/in/external/tool\&quot;}&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON object containing the fields \&quot;linktext\&quot;             and \&quot;href\&quot; and will be displayed as a clickable link on the report.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;NUMBER&lt;/td&gt;        &lt;td&gt;Number&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number and large numbers will             be displayed in a human readable format (e.g. 14.3k).        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;PERCENTAGE&lt;/td&gt;        &lt;td&gt;Number (between 0 and 100)&lt;/td&gt;        &lt;td&gt;             The value will be read as a JSON number between 0 and 100              and will be displayed with a percentage sign.        &lt;/td&gt;    &lt;/tr&gt;    &lt;tr&gt;        &lt;td&gt;TEXT&lt;/td&gt;        &lt;td&gt;String&lt;/td&gt;        &lt;td&gt;The value will be read as a JSON string and will be displayed as-is&lt;/td&gt;    &lt;/tr&gt;&lt;/table&gt;
     * <p><b>200</b> - The created report.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):  - The request does not contain a report title. - The data field contains unsupported objects. - The request does not contain a report key/ - The provided commit hash is invalid, according to  the validation rules mentioned for the commitId above. 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an insight report or authentication failed.
     * @param projectKey The project key. (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key A unique string representing the report as chosen by the reporter. This should be unique enough to not clash with other report&#39;s keys. To do this, we recommend namespacing the key using reverse DNS (required)
     * @param restSetInsightReportRequest The request object containing the details of the report to create (see example). (optional)
     * @return ResponseEntity&lt;RestInsightReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * Tutorial adding Code Insights to your CI system
     * @see <a href="https://developer.atlassian.com/server/bitbucket/tutorials-and-examples/code-insights-tutorial/">Create a Code Insights report Documentation</a>
     */
    public ResponseEntity<RestInsightReport> setWithHttpInfo(String projectKey, String commitId, String repositorySlug, String key, RestSetInsightReportRequest restSetInsightReportRequest) throws RestClientException {
        Object localVarPostBody = restSetInsightReportRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling set");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling set");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling set");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling set");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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

        ParameterizedTypeReference<RestInsightReport> localReturnType = new ParameterizedTypeReference<RestInsightReport>() {};
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create or replace a Code Insights annotation
     * Create an annotation with the given external ID, or replace it if it already exists. A request to replace an existing annotation will be rejected if the authenticated user was not the creator of the specified report.
     * <p><b>204</b> - No content, indicating that the request succeeded.
     * <p><b>401</b> - The currently authenticated user is not the author of the report, or the author no longer has sufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit, report or annotation does not exist.
     * @param projectKey The project key. (required)
     * @param externalId The external ID of the annotation that is to be updated or created (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs (required)
     * @param restSingleAddInsightAnnotationRequest The new annotation that is to replace the existing one. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setAnnotation(String projectKey, String externalId, String commitId, String repositorySlug, String key, RestSingleAddInsightAnnotationRequest restSingleAddInsightAnnotationRequest) throws RestClientException {
        setAnnotationWithHttpInfo(projectKey, externalId, commitId, repositorySlug, key, restSingleAddInsightAnnotationRequest);
    }

    /**
     * Create or replace a Code Insights annotation
     * Create an annotation with the given external ID, or replace it if it already exists. A request to replace an existing annotation will be rejected if the authenticated user was not the creator of the specified report.
     * <p><b>204</b> - No content, indicating that the request succeeded.
     * <p><b>401</b> - The currently authenticated user is not the author of the report, or the author no longer has sufficient permissions (&lt;code&gt;REPO_READ&lt;/code&gt;).
     * <p><b>404</b> - The specified project, repository, commit, report or annotation does not exist.
     * @param projectKey The project key. (required)
     * @param externalId The external ID of the annotation that is to be updated or created (required)
     * @param commitId The commit ID on which to record the annotation. This must be a full 40 character commit hash. (required)
     * @param repositorySlug The repository slug. (required)
     * @param key The key of the report to which this annotation belongs (required)
     * @param restSingleAddInsightAnnotationRequest The new annotation that is to replace the existing one. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setAnnotationWithHttpInfo(String projectKey, String externalId, String commitId, String repositorySlug, String key, RestSingleAddInsightAnnotationRequest restSingleAddInsightAnnotationRequest) throws RestClientException {
        Object localVarPostBody = restSingleAddInsightAnnotationRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setAnnotation");
        }
        
        // verify the required parameter 'externalId' is set
        if (externalId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'externalId' when calling setAnnotation");
        }
        
        // verify the required parameter 'commitId' is set
        if (commitId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'commitId' when calling setAnnotation");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setAnnotation");
        }
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling setAnnotation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("externalId", externalId);
        uriVariables.put("commitId", commitId);
        uriVariables.put("repositorySlug", repositorySlug);
        uriVariables.put("key", key);

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
        return apiClient.invokeAPI("/insights/latest/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}/reports/{key}/annotations/{externalId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update a required builds merge check
     * Update the required builds merge check for the given ID.  The authenticated user must have **REPO_ADMIN** permission for the target repository to update a required build merge check.  The contents of the required build merge check request are:  These fields are **required**:  - **buildParentKeys**: A non-empty list of build parent keys that require green builds for this merge check to pass - **refMatcher.id**: The value to match refs against in the target branch - **refMatcher.type.id**: The type of ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   These fields are optional:  - **exemptRefMatcher.id** The value to exempt refs in the source branch from this check - **exemptRefMatcher.type.id**: The type of exempt ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   
     * <p><b>200</b> - The details needed to update a required build merge check.
     * <p><b>400</b> - The request was malformed. This could be caused because:  - The build parent key list is empty - Either of the provided ref matchers is of an unrecognized type - Either of the provided ref matchers could not be created with the provided type and id   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param id  (required)
     * @param repositorySlug The repository being used (required)
     * @param restRequiredBuildConditionSetRequest The request specifying the required build parent keys, ref matcher and exemption matcher (optional)
     * @return RestRequiredBuildCondition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRequiredBuildCondition updateRequiredBuildsMergeCheck(String projectKey, Long id, String repositorySlug, RestRequiredBuildConditionSetRequest restRequiredBuildConditionSetRequest) throws RestClientException {
        return updateRequiredBuildsMergeCheckWithHttpInfo(projectKey, id, repositorySlug, restRequiredBuildConditionSetRequest).getBody();
    }

    /**
     * Update a required builds merge check
     * Update the required builds merge check for the given ID.  The authenticated user must have **REPO_ADMIN** permission for the target repository to update a required build merge check.  The contents of the required build merge check request are:  These fields are **required**:  - **buildParentKeys**: A non-empty list of build parent keys that require green builds for this merge check to pass - **refMatcher.id**: The value to match refs against in the target branch - **refMatcher.type.id**: The type of ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   These fields are optional:  - **exemptRefMatcher.id** The value to exempt refs in the source branch from this check - **exemptRefMatcher.type.id**: The type of exempt ref matcher, one of: \&quot;ANY_REF\&quot;, \&quot;BRANCH\&quot;, \&quot;PATTERN\&quot;, \&quot;MODEL_CATEGORY\&quot; or \&quot;MODEL_BRANCH\&quot;   
     * <p><b>200</b> - The details needed to update a required build merge check.
     * <p><b>400</b> - The request was malformed. This could be caused because:  - The build parent key list is empty - Either of the provided ref matchers is of an unrecognized type - Either of the provided ref matchers could not be created with the provided type and id   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a required build merge check in this repository.
     * @param projectKey The project that the repository belongs to (required)
     * @param id  (required)
     * @param repositorySlug The repository being used (required)
     * @param restRequiredBuildConditionSetRequest The request specifying the required build parent keys, ref matcher and exemption matcher (optional)
     * @return ResponseEntity&lt;RestRequiredBuildCondition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRequiredBuildCondition> updateRequiredBuildsMergeCheckWithHttpInfo(String projectKey, Long id, String repositorySlug, RestRequiredBuildConditionSetRequest restRequiredBuildConditionSetRequest) throws RestClientException {
        Object localVarPostBody = restRequiredBuildConditionSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateRequiredBuildsMergeCheck");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRequiredBuildsMergeCheck");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateRequiredBuildsMergeCheck");
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

        ParameterizedTypeReference<RestRequiredBuildCondition> localReturnType = new ParameterizedTypeReference<RestRequiredBuildCondition>() {};
        return apiClient.invokeAPI("/required-builds/latest/projects/{projectKey}/repos/{repositorySlug}/condition/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
