package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExampleSettings;
import java.io.File;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetConfigurations200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetGroups1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetGroupsWithAnyPermission200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLikers200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetProjects200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepositoriesRecentlyAccessed200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepositoryHooks1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRestrictions1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetUsersWithAnyPermission1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAutoDeclineSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAutoDeclineSettingsRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBranch;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDetailedInvocation;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestHookScriptConfig;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestHookScriptTriggers;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMinimalRef;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermitted;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestProject;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPullRequestSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRefRestriction;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepository;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryHook;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRestrictionRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestWebhook;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestWebhookCredentials;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.ProjectApi")
public class ProjectApi extends BaseApi {

    public ProjectApi() {
        super(new ApiClient());
    }

    @Autowired
    public ProjectApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Create a new project
     * Create a new project.   To include a custom avatar for the project, the project definition should contain an additional attribute with the key &lt;code&gt;avatar&lt;/code&gt; and the value a data URI containing Base64-encoded image data. The URI should be in the following format: &lt;pre&gt;    data:(content type, e.g. image/png);base64,(data) &lt;/pre&gt;If the data is not Base64-encoded, or if a character set is defined in the URI, or the URI is otherwise invalid, &lt;em&gt;project creation will fail&lt;/em&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_CREATE&lt;/strong&gt; permission to call this resource.
     * <p><b>201</b> - The newly created project.
     * <p><b>400</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a project.
     * <p><b>409</b> - The project key or name is already in use.
     * @param restProject The project. (optional)
     * @return RestProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestProject createProject(RestProject restProject) throws RestClientException {
        return createProjectWithHttpInfo(restProject).getBody();
    }

    /**
     * Create a new project
     * Create a new project.   To include a custom avatar for the project, the project definition should contain an additional attribute with the key &lt;code&gt;avatar&lt;/code&gt; and the value a data URI containing Base64-encoded image data. The URI should be in the following format: &lt;pre&gt;    data:(content type, e.g. image/png);base64,(data) &lt;/pre&gt;If the data is not Base64-encoded, or if a character set is defined in the URI, or the URI is otherwise invalid, &lt;em&gt;project creation will fail&lt;/em&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_CREATE&lt;/strong&gt; permission to call this resource.
     * <p><b>201</b> - The newly created project.
     * <p><b>400</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a project.
     * <p><b>409</b> - The project key or name is already in use.
     * @param restProject The project. (optional)
     * @return ResponseEntity&lt;RestProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestProject> createProjectWithHttpInfo(RestProject restProject) throws RestClientException {
        Object localVarPostBody = restProject;
        

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

        ParameterizedTypeReference<RestProject> localReturnType = new ParameterizedTypeReference<RestProject>() {};
        return apiClient.invokeAPI("/api/latest/projects", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create repository
     * Create a new repository. Requires an existing project in which this repository will be created. The only parameters which will be used are name and scmId.   The authenticated user must have &lt;strong&gt;REPO_CREATE&lt;/strong&gt; permission or higher, for the context project to call this resource.
     * <p><b>201</b> - The newly created repository.
     * <p><b>400</b> - The repository was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a repository.
     * <p><b>409</b> - A repository with same name already exists.
     * @param projectKey The project key. (required)
     * @param restRepository The repository (optional)
     * @return RestRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepository createRepository(String projectKey, RestRepository restRepository) throws RestClientException {
        return createRepositoryWithHttpInfo(projectKey, restRepository).getBody();
    }

    /**
     * Create repository
     * Create a new repository. Requires an existing project in which this repository will be created. The only parameters which will be used are name and scmId.   The authenticated user must have &lt;strong&gt;REPO_CREATE&lt;/strong&gt; permission or higher, for the context project to call this resource.
     * <p><b>201</b> - The newly created repository.
     * <p><b>400</b> - The repository was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a repository.
     * <p><b>409</b> - A repository with same name already exists.
     * @param projectKey The project key. (required)
     * @param restRepository The repository (optional)
     * @return ResponseEntity&lt;RestRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepository> createRepositoryWithHttpInfo(String projectKey, RestRepository restRepository) throws RestClientException {
        Object localVarPostBody = restRepository;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createRepository");
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

        ParameterizedTypeReference<RestRepository> localReturnType = new ParameterizedTypeReference<RestRepository>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create multiple ref restrictions
     * Allows creating multiple restrictions at once.
     * <p><b>200</b> - Response contains the ref restriction that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param projectKey The project key. (required)
     * @param restRestrictionRequest The request containing a list of the details of the restrictions to create. (optional)
     * @return RestRefRestriction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefRestriction createRestrictions(String projectKey, List<RestRestrictionRequest> restRestrictionRequest) throws RestClientException {
        return createRestrictionsWithHttpInfo(projectKey, restRestrictionRequest).getBody();
    }

    /**
     * Create multiple ref restrictions
     * Allows creating multiple restrictions at once.
     * <p><b>200</b> - Response contains the ref restriction that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param projectKey The project key. (required)
     * @param restRestrictionRequest The request containing a list of the details of the restrictions to create. (optional)
     * @return ResponseEntity&lt;RestRefRestriction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefRestriction> createRestrictionsWithHttpInfo(String projectKey, List<RestRestrictionRequest> restRestrictionRequest) throws RestClientException {
        Object localVarPostBody = restRestrictionRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createRestrictions");
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
            "application/vnd.atl.bitbucket.bulk+json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestRefRestriction> localReturnType = new ParameterizedTypeReference<RestRefRestriction>() {};
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/restrictions", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create webhook
     * Create a webhook for the project specified via the URL.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A created webhook.
     * <p><b>400</b> - The webhook parameters were invalid or not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create webhooks in the project.
     * <p><b>404</b> - The project does not exist.
     * @param projectKey The project key. (required)
     * @param restWebhook The webhook to be created for this project. (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook createWebhook(String projectKey, RestWebhook restWebhook) throws RestClientException {
        return createWebhookWithHttpInfo(projectKey, restWebhook).getBody();
    }

    /**
     * Create webhook
     * Create a webhook for the project specified via the URL.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A created webhook.
     * <p><b>400</b> - The webhook parameters were invalid or not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create webhooks in the project.
     * <p><b>404</b> - The project does not exist.
     * @param projectKey The project key. (required)
     * @param restWebhook The webhook to be created for this project. (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> createWebhookWithHttpInfo(String projectKey, RestWebhook restWebhook) throws RestClientException {
        Object localVarPostBody = restWebhook;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createWebhook");
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

        ParameterizedTypeReference<RestWebhook> localReturnType = new ParameterizedTypeReference<RestWebhook>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete auto decline settings
     * Delete auto decline settings for the supplied project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for this project to call the resource.
     * <p><b>204</b> - The auto decline settings have been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAutoDeclineSettings(String projectKey) throws RestClientException {
        deleteAutoDeclineSettingsWithHttpInfo(projectKey);
    }

    /**
     * Delete auto decline settings
     * Delete auto decline settings for the supplied project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for this project to call the resource.
     * <p><b>204</b> - The auto decline settings have been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAutoDeclineSettingsWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAutoDeclineSettings");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/auto-decline", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete project
     * Delete the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> - The project can not be deleted as it contains repositories.
     * @param projectKey The project key. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProject(String projectKey) throws RestClientException {
        deleteProjectWithHttpInfo(projectKey);
    }

    /**
     * Delete project
     * Delete the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> - The project can not be deleted as it contains repositories.
     * @param projectKey The project key. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProjectWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteProject");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete repository
     * Schedule the repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; to be deleted.   The authenticated user must have sufficient permissions specified by the repository delete policy to call this resource. The default permission required is &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission.
     * <p><b>202</b> - The repository has been scheduled for deletion.
     * <p><b>204</b> - No repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; was found.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRepository(String projectKey, String repositorySlug) throws RestClientException {
        deleteRepositoryWithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Delete repository
     * Schedule the repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; to be deleted.   The authenticated user must have sufficient permissions specified by the repository delete policy to call this resource. The default permission required is &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission.
     * <p><b>202</b> - The repository has been scheduled for deletion.
     * <p><b>204</b> - No repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt; was found.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRepositoryWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteRepository");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a ref restriction
     * Deletes a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>204</b> - An empty response indicating that the operation was successful
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete restrictions on the provided project
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRestriction(String projectKey, String id) throws RestClientException {
        deleteRestrictionWithHttpInfo(projectKey, id);
    }

    /**
     * Delete a ref restriction
     * Deletes a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>204</b> - An empty response indicating that the operation was successful
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete restrictions on the provided project
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRestrictionWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRestriction");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRestriction");
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
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/restrictions/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete webhook
     * Delete a webhook for the project specified via the URL.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The webhook for the project has been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete webhooks in the project.
     * <p><b>404</b> - The specified project does not exist, or webhook does not exist in this project.
     * @param projectKey The project key. (required)
     * @param webhookId The ID of the webhook to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteWebhook(String projectKey, String webhookId) throws RestClientException {
        deleteWebhookWithHttpInfo(projectKey, webhookId);
    }

    /**
     * Delete webhook
     * Delete a webhook for the project specified via the URL.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>204</b> - The webhook for the project has been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete webhooks in the project.
     * <p><b>404</b> - The specified project does not exist, or webhook does not exist in this project.
     * @param projectKey The project key. (required)
     * @param webhookId The ID of the webhook to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteWebhookWithHttpInfo(String projectKey, String webhookId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteWebhook");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling deleteWebhook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Disable repository hook
     * Disable a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to disable the hook.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook disableHook(String projectKey, String hookKey) throws RestClientException {
        return disableHookWithHttpInfo(projectKey, hookKey).getBody();
    }

    /**
     * Disable repository hook
     * Disable a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to disable the hook.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> disableHookWithHttpInfo(String projectKey, String hookKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling disableHook");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling disableHook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks/{hookKey}/enabled", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Enable repository hook
     * Enable a repository hook for this project and optionally apply new configuration.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.   A JSON document may be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param contentLength The content length. (optional)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook enableHook(String projectKey, String hookKey, Long contentLength) throws RestClientException {
        return enableHookWithHttpInfo(projectKey, hookKey, contentLength).getBody();
    }

    /**
     * Enable repository hook
     * Enable a repository hook for this project and optionally apply new configuration.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.   A JSON document may be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @param contentLength The content length. (optional)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> enableHookWithHttpInfo(String projectKey, String hookKey, Long contentLength) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling enableHook");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling enableHook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks/{hookKey}/enabled", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find webhooks
     * Find webhooks in this project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; IDs to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void findWebhooks(String projectKey, String event, Boolean statistics) throws RestClientException {
        findWebhooksWithHttpInfo(projectKey, event, statistics);
    }

    /**
     * Find webhooks
     * Find webhooks in this project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of webhooks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to find webhooks in the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param event List of &lt;code&gt;com.atlassian.webhooks.WebhookEvent&lt;/code&gt; IDs to filter for (optional)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for all found webhooks (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> findWebhooksWithHttpInfo(String projectKey, String event, Boolean statistics) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling findWebhooks");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Fork repository
     * Create a new repository forked from an existing repository.   The JSON body for this &lt;code&gt;POST&lt;/code&gt; is not required to contain &lt;i&gt;any&lt;/i&gt; properties. Even the name may be omitted. The following properties will be used, if provided:   - &lt;code&gt;\&quot;name\&quot;:\&quot;Fork name\&quot;&lt;/code&gt; - Specifies the forked repository&#39;s name    - Defaults to the name of the origin repository if not specified - &lt;code&gt;\&quot;defaultBranch\&quot;:\&quot;main\&quot;&lt;/code&gt; - Specifies the forked repository&#39;s default branch   - Defaults to the origin repository&#39;s default branch if not specified - &lt;code&gt;\&quot;project\&quot;:{\&quot;key\&quot;:\&quot;TARGET_KEY\&quot;}&lt;/code&gt; - Specifies the forked repository&#39;s target project by key   - Defaults to the current user&#39;s personal project if not specified   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository and &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; on the target project to call this resource. Note that users &lt;i&gt;always&lt;/i&gt; have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission on their personal projects.
     * <p><b>201</b> - The newly created fork.
     * <p><b>400</b> - A validation error prevented the fork from being created. Possible validation errors include: The name or slug for the fork collides with another repository in the target project; an SCM type was specified in the JSON body; a project was specified in the JSON body without a \&quot;key\&quot; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a fork.
     * <p><b>404</b> - The specified repository does not exist, or, if a target project was specified, the target project does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The rest fork. (optional)
     * @return RestRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepository forkRepository(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        return forkRepositoryWithHttpInfo(projectKey, repositorySlug, restRepository).getBody();
    }

    /**
     * Fork repository
     * Create a new repository forked from an existing repository.   The JSON body for this &lt;code&gt;POST&lt;/code&gt; is not required to contain &lt;i&gt;any&lt;/i&gt; properties. Even the name may be omitted. The following properties will be used, if provided:   - &lt;code&gt;\&quot;name\&quot;:\&quot;Fork name\&quot;&lt;/code&gt; - Specifies the forked repository&#39;s name    - Defaults to the name of the origin repository if not specified - &lt;code&gt;\&quot;defaultBranch\&quot;:\&quot;main\&quot;&lt;/code&gt; - Specifies the forked repository&#39;s default branch   - Defaults to the origin repository&#39;s default branch if not specified - &lt;code&gt;\&quot;project\&quot;:{\&quot;key\&quot;:\&quot;TARGET_KEY\&quot;}&lt;/code&gt; - Specifies the forked repository&#39;s target project by key   - Defaults to the current user&#39;s personal project if not specified   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository and &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; on the target project to call this resource. Note that users &lt;i&gt;always&lt;/i&gt; have &lt;b&gt;PROJECT_ADMIN&lt;/b&gt; permission on their personal projects.
     * <p><b>201</b> - The newly created fork.
     * <p><b>400</b> - A validation error prevented the fork from being created. Possible validation errors include: The name or slug for the fork collides with another repository in the target project; an SCM type was specified in the JSON body; a project was specified in the JSON body without a \&quot;key\&quot; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a fork.
     * <p><b>404</b> - The specified repository does not exist, or, if a target project was specified, the target project does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The rest fork. (optional)
     * @return ResponseEntity&lt;RestRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepository> forkRepositoryWithHttpInfo(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        Object localVarPostBody = restRepository;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling forkRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling forkRepository");
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

        ParameterizedTypeReference<RestRepository> localReturnType = new ParameterizedTypeReference<RestRepository>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get project avatar
     * Retrieve the avatar for the project matching the supplied &lt;strong&gt;moduleKey&lt;/strong&gt;.
     * <p><b>200</b> - The avatar of the project matching the supplied &lt;strong&gt;moduleKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param hookKey The complete module key of the hook module. (required)
     * @param version (optional) Version used for HTTP caching only - any non-blank version will result in a large max-age Cache-Control header. Note that this does not affect the Last-Modified header. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getAvatar(String hookKey, String version) throws RestClientException {
        getAvatarWithHttpInfo(hookKey, version);
    }

    /**
     * Get project avatar
     * Retrieve the avatar for the project matching the supplied &lt;strong&gt;moduleKey&lt;/strong&gt;.
     * <p><b>200</b> - The avatar of the project matching the supplied &lt;strong&gt;moduleKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param hookKey The complete module key of the hook module. (required)
     * @param version (optional) Version used for HTTP caching only - any non-blank version will result in a large max-age Cache-Control header. Note that this does not affect the Last-Modified header. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getAvatarWithHttpInfo(String hookKey, String version) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling getAvatar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/hooks/{hookKey}/avatar", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get configured hook scripts
     * Return a page of hook scripts configured for the specified project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>200</b> - A page of hook scripts.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetConfigurations200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetConfigurations200Response getConfigurations(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getConfigurationsWithHttpInfo(projectKey, start, limit).getBody();
    }

    /**
     * Get configured hook scripts
     * Return a page of hook scripts configured for the specified project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>200</b> - A page of hook scripts.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetConfigurations200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetConfigurations200Response> getConfigurationsWithHttpInfo(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getConfigurations");
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

        ParameterizedTypeReference<GetConfigurations200Response> localReturnType = new ParameterizedTypeReference<GetConfigurations200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/hook-scripts", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository default branch
     * Retrieves the repository&#39;s &lt;i&gt;configured&lt;/i&gt; default branch.   Every repository has a &lt;i&gt;configured&lt;/i&gt; default branch, but that branch may not actually &lt;i&gt;exist&lt;/i&gt; in the repository. For example, a newly-created repository will have a configured default branch even though no branches have been pushed yet.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The configured default branch for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist, or its configured default branch does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestMinimalRef
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMinimalRef getDefaultBranch2(String projectKey, String repositorySlug) throws RestClientException {
        return getDefaultBranch2WithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get repository default branch
     * Retrieves the repository&#39;s &lt;i&gt;configured&lt;/i&gt; default branch.   Every repository has a &lt;i&gt;configured&lt;/i&gt; default branch, but that branch may not actually &lt;i&gt;exist&lt;/i&gt; in the repository. For example, a newly-created repository will have a configured default branch even though no branches have been pushed yet.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The configured default branch for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist, or its configured default branch does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestMinimalRef&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMinimalRef> getDefaultBranch2WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getDefaultBranch2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getDefaultBranch2");
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

        ParameterizedTypeReference<RestMinimalRef> localReturnType = new ParameterizedTypeReference<RestMinimalRef>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/default-branch", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository forks
     * Retrieve repositories which have been forked from this one. Unlike #getRelatedRepositories(Repository, PageRequest) related repositories, this only looks at a given repository&#39;s direct forks. If those forks have themselves been the origin of more forks, such \&quot;grandchildren\&quot; repositories will not be retrieved.   Only repositories to which the authenticated user has &lt;b&gt;REPO_READ&lt;/b&gt; permission will be included, even if other repositories have been forked from this one.
     * <p><b>200</b> - A page of repositories related to the request repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response getForkedRepositories(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getForkedRepositoriesWithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get repository forks
     * Retrieve repositories which have been forked from this one. Unlike #getRelatedRepositories(Repository, PageRequest) related repositories, this only looks at a given repository&#39;s direct forks. If those forks have themselves been the origin of more forks, such \&quot;grandchildren\&quot; repositories will not be retrieved.   Only repositories to which the authenticated user has &lt;b&gt;REPO_READ&lt;/b&gt; permission will be included, even if other repositories have been forked from this one.
     * <p><b>200</b> - A page of repositories related to the request repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> getForkedRepositoriesWithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getForkedRepositories");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getForkedRepositories");
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

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/forks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups with permission to project
     * Retrieve a page of groups that have been granted at least one permission for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of groups and their highest permissions for the specified project.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroupsWithAnyPermission200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroupsWithAnyPermission200Response getGroupsWithAnyPermission1(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithAnyPermission1WithHttpInfo(projectKey, filter, start, limit).getBody();
    }

    /**
     * Get groups with permission to project
     * Retrieve a page of groups that have been granted at least one permission for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of groups and their highest permissions for the specified project.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroupsWithAnyPermission200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroupsWithAnyPermission200Response> getGroupsWithAnyPermission1WithHttpInfo(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getGroupsWithAnyPermission1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetGroupsWithAnyPermission200Response> localReturnType = new ParameterizedTypeReference<GetGroupsWithAnyPermission200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups without project permission
     * Retrieve a page of groups that have no granted permissions for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher
     * <p><b>202</b> - A page of groups that have not been granted any permissions for the specifiedproject.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups1200Response getGroupsWithoutAnyPermission1(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithoutAnyPermission1WithHttpInfo(projectKey, filter, start, limit).getBody();
    }

    /**
     * Get groups without project permission
     * Retrieve a page of groups that have no granted permissions for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher
     * <p><b>202</b> - A page of groups that have not been granted any permissions for the specifiedproject.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups1200Response> getGroupsWithoutAnyPermission1WithHttpInfo(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getGroupsWithoutAnyPermission1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetGroups1200Response> localReturnType = new ParameterizedTypeReference<GetGroups1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/groups/none", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get last webhook invocation details
     * Get the latest invocations for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook invocations in the project.
     * <p><b>404</b> - The specified project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. (optional)
     * @param outcome The outcome to filter for. Can be SUCCESS, FAILURE, ERROR. None specified means that the all will be considered (optional)
     * @return RestDetailedInvocation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedInvocation getLatestInvocation(String projectKey, String webhookId, String event, String outcome) throws RestClientException {
        return getLatestInvocationWithHttpInfo(projectKey, webhookId, event, outcome).getBody();
    }

    /**
     * Get last webhook invocation details
     * Get the latest invocations for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook invocations in the project.
     * <p><b>404</b> - The specified project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. (optional)
     * @param outcome The outcome to filter for. Can be SUCCESS, FAILURE, ERROR. None specified means that the all will be considered (optional)
     * @return ResponseEntity&lt;RestDetailedInvocation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedInvocation> getLatestInvocationWithHttpInfo(String projectKey, String webhookId, String event, String outcome) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getLatestInvocation");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getLatestInvocation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}/latest", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a project
     * Retrieve the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @return RestProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestProject getProject(String projectKey) throws RestClientException {
        return getProjectWithHttpInfo(projectKey).getBody();
    }

    /**
     * Get a project
     * Retrieve the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @return ResponseEntity&lt;RestProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestProject> getProjectWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getProject");
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

        ParameterizedTypeReference<RestProject> localReturnType = new ParameterizedTypeReference<RestProject>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get auto decline settings
     * Retrieves the auto decline settings for the supplied project. Default settings are returned if no explicit settings have been set for the project.
     * <p><b>200</b> - The auto decline settings
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @return RestAutoDeclineSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAutoDeclineSettings getProjectAutoDeclineSettings(String projectKey) throws RestClientException {
        return getProjectAutoDeclineSettingsWithHttpInfo(projectKey).getBody();
    }

    /**
     * Get auto decline settings
     * Retrieves the auto decline settings for the supplied project. Default settings are returned if no explicit settings have been set for the project.
     * <p><b>200</b> - The auto decline settings
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @return ResponseEntity&lt;RestAutoDeclineSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAutoDeclineSettings> getProjectAutoDeclineSettingsWithHttpInfo(String projectKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getProjectAutoDeclineSettings");
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

        ParameterizedTypeReference<RestAutoDeclineSettings> localReturnType = new ParameterizedTypeReference<RestAutoDeclineSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/auto-decline", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get avatar for project
     * Retrieve the avatar for the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The avatar of the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param s The desired size of the image. The server will return an image as close as possible to the specified size. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getProjectAvatar(String projectKey, String s) throws RestClientException {
        getProjectAvatarWithHttpInfo(projectKey, s);
    }

    /**
     * Get avatar for project
     * Retrieve the avatar for the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The avatar of the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param s The desired size of the image. The server will return an image as close as possible to the specified size. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getProjectAvatarWithHttpInfo(String projectKey, String s) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getProjectAvatar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "s", s));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/avatar.png", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get projects
     * Retrieve a page of projects.   Only projects for which the authenticated user has the &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission will be returned.
     * <p><b>200</b> - A page of projects.
     * <p><b>400</b> - The permission level is unknown or not related to projects.
     * @param name Name to filter by. (optional)
     * @param permission Permission to filter by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetProjects200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetProjects200Response getProjects(String name, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getProjectsWithHttpInfo(name, permission, start, limit).getBody();
    }

    /**
     * Get projects
     * Retrieve a page of projects.   Only projects for which the authenticated user has the &lt;strong&gt;PROJECT_VIEW&lt;/strong&gt; permission will be returned.
     * <p><b>200</b> - A page of projects.
     * <p><b>400</b> - The permission level is unknown or not related to projects.
     * @param name Name to filter by. (optional)
     * @param permission Permission to filter by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetProjects200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetProjects200Response> getProjectsWithHttpInfo(String name, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
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

        ParameterizedTypeReference<GetProjects200Response> localReturnType = new ParameterizedTypeReference<GetProjects200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get merge strategy
     * Retrieve the merge strategy configuration for this project and SCM.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The merge configuration of the request project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param scmId The SCM to get strategies for. (required)
     * @return RestPullRequestSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestSettings getPullRequestSettings(String projectKey, String scmId) throws RestClientException {
        return getPullRequestSettingsWithHttpInfo(projectKey, scmId).getBody();
    }

    /**
     * Get merge strategy
     * Retrieve the merge strategy configuration for this project and SCM.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the context repository to call this resource.
     * <p><b>200</b> - The merge configuration of the request project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param scmId The SCM to get strategies for. (required)
     * @return ResponseEntity&lt;RestPullRequestSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestSettings> getPullRequestSettingsWithHttpInfo(String projectKey, String scmId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getPullRequestSettings");
        }
        
        // verify the required parameter 'scmId' is set
        if (scmId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scmId' when calling getPullRequestSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
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

        ParameterizedTypeReference<RestPullRequestSettings> localReturnType = new ParameterizedTypeReference<RestPullRequestSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/pull-requests/{scmId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get related repository
     * Retrieve repositories which are related to this one. Related repositories are from the same Repository#getHierarchyId() hierarchy as this repository.   Only repositories to which the authenticated user has &lt;b&gt;REPO_READ&lt;/b&gt; permission will be included, even if more repositories are part of this repository&#39;s hierarchy.
     * <p><b>200</b> - A page of repositories related to the request repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response getRelatedRepositories(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRelatedRepositoriesWithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get related repository
     * Retrieve repositories which are related to this one. Related repositories are from the same Repository#getHierarchyId() hierarchy as this repository.   Only repositories to which the authenticated user has &lt;b&gt;REPO_READ&lt;/b&gt; permission will be included, even if more repositories are part of this repository&#39;s hierarchy.
     * <p><b>200</b> - A page of repositories related to the request repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the request repository.
     * <p><b>404</b> - The request repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> getRelatedRepositoriesWithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRelatedRepositories");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRelatedRepositories");
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

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/related", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repositories for project
     * Retrieve repositories from the project corresponding to the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The repositories matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response getRepositories(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepositoriesWithHttpInfo(projectKey, start, limit).getBody();
    }

    /**
     * Get repositories for project
     * Retrieve repositories from the project corresponding to the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The repositories matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> getRepositoriesWithHttpInfo(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositories");
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

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository
     * Retrieve the repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository which matches the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepository getRepository(String projectKey, String repositorySlug) throws RestClientException {
        return getRepositoryWithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get repository
     * Retrieve the repository matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The repository which matches the supplied &lt;strong&gt;projectKey&lt;/strong&gt; and &lt;strong&gt;repositorySlug&lt;/strong&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to see the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepository> getRepositoryWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRepository");
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

        ParameterizedTypeReference<RestRepository> localReturnType = new ParameterizedTypeReference<RestRepository>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a repository hook
     * Retrieve a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - Returns the repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified repository hook does not exist for the given project, or the project does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return RestRepositoryHook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryHook getRepositoryHook(String projectKey, String hookKey) throws RestClientException {
        return getRepositoryHookWithHttpInfo(projectKey, hookKey).getBody();
    }

    /**
     * Get a repository hook
     * Retrieve a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - Returns the repository hooks with their associated enabled state for the supplied hookKey.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to enable the hook.
     * <p><b>404</b> - The specified repository hook does not exist for the given project, or the project does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return ResponseEntity&lt;RestRepositoryHook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryHook> getRepositoryHookWithHttpInfo(String projectKey, String hookKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositoryHook");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling getRepositoryHook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks/{hookKey}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository hooks
     * Retrieve a page of repository hooks for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of repository hooks with their associated enabled state.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hooks.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param type The optional type to filter by. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoryHooks1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoryHooks1200Response getRepositoryHooks(String projectKey, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepositoryHooksWithHttpInfo(projectKey, type, start, limit).getBody();
    }

    /**
     * Get repository hooks
     * Retrieve a page of repository hooks for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A page of repository hooks with their associated enabled state.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hooks.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param type The optional type to filter by. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoryHooks1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoryHooks1200Response> getRepositoryHooksWithHttpInfo(String projectKey, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositoryHooks");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a ref restriction
     * Returns a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing the restriction.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @return RestRefRestriction
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefRestriction getRestriction(String projectKey, String id) throws RestClientException {
        return getRestrictionWithHttpInfo(projectKey, id).getBody();
    }

    /**
     * Get a ref restriction
     * Returns a restriction as specified by a restriction id.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing the restriction.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param id The restriction id. (required)
     * @return ResponseEntity&lt;RestRefRestriction&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefRestriction> getRestrictionWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRestriction");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRestriction");
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

        ParameterizedTypeReference<RestRefRestriction> localReturnType = new ParameterizedTypeReference<RestRefRestriction>() {};
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/restrictions/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search for ref restrictions
     * Search for restrictions using the supplied parameters.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing a page of restrictions.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param matcherType Matcher type to filter on (optional)
     * @param matcherId Matcher id to filter on. Requires the matcherType parameter to be specified also. (optional)
     * @param type Types of restrictions to filter on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRestrictions1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRestrictions1200Response getRestrictions(String projectKey, String matcherType, String matcherId, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRestrictionsWithHttpInfo(projectKey, matcherType, matcherId, type, start, limit).getBody();
    }

    /**
     * Search for ref restrictions
     * Search for restrictions using the supplied parameters.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission or higher to call this resource. Only authenticated users may call this resource.
     * <p><b>200</b> - A response containing a page of restrictions.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user is not permitted to get restrictions on the provided project
     * <p><b>404</b> - No restriction exists for the provided ID.
     * @param projectKey The project key. (required)
     * @param matcherType Matcher type to filter on (optional)
     * @param matcherId Matcher id to filter on. Requires the matcherType parameter to be specified also. (optional)
     * @param type Types of restrictions to filter on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRestrictions1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRestrictions1200Response> getRestrictionsWithHttpInfo(String projectKey, String matcherType, String matcherId, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRestrictions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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
        return apiClient.invokeAPI("/branch-permissions/latest/projects/{projectKey}/restrictions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository hook settings
     * Retrieve the settings for a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook settings.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return ExampleSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleSettings getSettings(String projectKey, String hookKey) throws RestClientException {
        return getSettingsWithHttpInfo(projectKey, hookKey).getBody();
    }

    /**
     * Get repository hook settings
     * Retrieve the settings for a repository hook for this project.   The authenticated user must have &lt;strong&gt;PROJECT_READ&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the hook settings.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The hook key. (required)
     * @return ResponseEntity&lt;ExampleSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleSettings> getSettingsWithHttpInfo(String projectKey, String hookKey) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getSettings");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling getSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks/{hookKey}/settings", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook statistics
     * Get the statistics for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics in the project.
     * <p><b>404</b> - The specified project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. May be empty, in which case all events are considered (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStatistics(String projectKey, String webhookId, String event) throws RestClientException {
        return getStatisticsWithHttpInfo(projectKey, webhookId, event).getBody();
    }

    /**
     * Get webhook statistics
     * Get the statistics for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics in the project.
     * <p><b>404</b> - The specified project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param event The string ID of a specific event to retrieve the last invocation for. May be empty, in which case all events are considered (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStatisticsWithHttpInfo(String projectKey, String webhookId, String event) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getStatistics");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getStatistics");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}/statistics", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook statistics summary
     * Get the statistics summary for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>204</b> - No webhook invocations exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics summary in the project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStatisticsSummary(String projectKey, String webhookId) throws RestClientException {
        return getStatisticsSummaryWithHttpInfo(projectKey, webhookId).getBody();
    }

    /**
     * Get webhook statistics summary
     * Get the statistics summary for a specific webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook invocation dataset.
     * <p><b>204</b> - No webhook invocations exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get webhook statistics summary in the project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStatisticsSummaryWithHttpInfo(String projectKey, String webhookId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getStatisticsSummary");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getStatisticsSummary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}/statistics/summary", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users with permission to project
     * Retrieve a page of users that have been granted at least one permission for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of users and their highest permissions for the specified project.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetUsersWithAnyPermission1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetUsersWithAnyPermission1200Response getUsersWithAnyPermission1(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithAnyPermission1WithHttpInfo(projectKey, filter, start, limit).getBody();
    }

    /**
     * Get users with permission to project
     * Retrieve a page of users that have been granted at least one permission for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of users and their highest permissions for the specified project.
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetUsersWithAnyPermission1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetUsersWithAnyPermission1200Response> getUsersWithAnyPermission1WithHttpInfo(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getUsersWithAnyPermission1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetUsersWithAnyPermission1200Response> localReturnType = new ParameterizedTypeReference<GetUsersWithAnyPermission1200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/users", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users without project permission
     * Retrieve a page of &lt;i&gt;licensed&lt;/i&gt; users that have no granted permissions for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any permissions for the specified project
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLikers200Response getUsersWithoutPermission(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithoutPermissionWithHttpInfo(projectKey, filter, start, limit).getBody();
    }

    /**
     * Get users without project permission
     * Retrieve a page of &lt;i&gt;licensed&lt;/i&gt; users that have no granted permissions for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any permissions for the specified project
     * <p><b>401</b> - The currently authenticated user is not a project administrator for thespecified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLikers200Response> getUsersWithoutPermissionWithHttpInfo(String projectKey, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getUsersWithoutPermission");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/users/none", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get webhook
     * Get a webhook by ID.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get a webhook in the project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for the webhook (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook getWebhook(String projectKey, String webhookId, String statistics) throws RestClientException {
        return getWebhookWithHttpInfo(projectKey, webhookId, statistics).getBody();
    }

    /**
     * Get webhook
     * Get a webhook by ID.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to get a webhook in the project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId ID of the webhook (required)
     * @param statistics &lt;code&gt;true&lt;/code&gt; if statistics should be provided for the webhook (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> getWebhookWithHttpInfo(String projectKey, String webhookId, String statistics) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getWebhook");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling getWebhook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Check default project permission
     * Check whether the specified permission is the default permission (granted to all users) for a project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A simple entity indicating whether the specified permission is the defaultpermission for this project.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param permission The permission to grant. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (required)
     * @return RestPermitted
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPermitted hasAllUserPermission(String projectKey, String permission) throws RestClientException {
        return hasAllUserPermissionWithHttpInfo(projectKey, permission).getBody();
    }

    /**
     * Check default project permission
     * Check whether the specified permission is the default permission (granted to all users) for a project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>200</b> - A simple entity indicating whether the specified permission is the defaultpermission for this project.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param permission The permission to grant. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (required)
     * @return ResponseEntity&lt;RestPermitted&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPermitted> hasAllUserPermissionWithHttpInfo(String projectKey, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling hasAllUserPermission");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling hasAllUserPermission");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("permission", permission);

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

        ParameterizedTypeReference<RestPermitted> localReturnType = new ParameterizedTypeReference<RestPermitted>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/{permission}/all", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Grant project permission
     * Grant or revoke a project permission to all users, i.e. set the default permission.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>204</b> - The requested permission was successfully granted or revoked.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param permission The permission to grant. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (required)
     * @param allow &lt;em&gt;true&lt;/em&gt; to grant the specified permission to all users, or &lt;em&gt;false&lt;/em&gt; to revoke it (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void modifyAllUserPermission(String projectKey, String permission, String allow) throws RestClientException {
        modifyAllUserPermissionWithHttpInfo(projectKey, permission, allow);
    }

    /**
     * Grant project permission
     * Grant or revoke a project permission to all users, i.e. set the default permission.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>204</b> - The requested permission was successfully granted or revoked.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specified project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param permission The permission to grant. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (required)
     * @param allow &lt;em&gt;true&lt;/em&gt; to grant the specified permission to all users, or &lt;em&gt;false&lt;/em&gt; to revoke it (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> modifyAllUserPermissionWithHttpInfo(String projectKey, String permission, String allow) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling modifyAllUserPermission");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling modifyAllUserPermission");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("permission", permission);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "allow", allow));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/{permission}/all", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove a hook script
     * Removes the hook script from the set of hook scripts configured to run in all repositories under the project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>204</b> - The hook script was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The project key or hook script ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void removeConfiguration(String projectKey, String scriptId) throws RestClientException {
        removeConfigurationWithHttpInfo(projectKey, scriptId);
    }

    /**
     * Remove a hook script
     * Removes the hook script from the set of hook scripts configured to run in all repositories under the project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>204</b> - The hook script was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The project key or hook script ID supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> removeConfigurationWithHttpInfo(String projectKey, String scriptId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling removeConfiguration");
        }
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling removeConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("scriptId", scriptId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/hook-scripts/{scriptId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retry repository creation
     * If a create or fork operation fails, calling this method will clean up the broken repository and try again. The repository must be in an INITIALISATION_FAILED state.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The newly created repository.
     * <p><b>400</b> - The repository was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepository retryCreateRepository(String projectKey, String repositorySlug) throws RestClientException {
        return retryCreateRepositoryWithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Retry repository creation
     * If a create or fork operation fails, calling this method will clean up the broken repository and try again. The repository must be in an INITIALISATION_FAILED state.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The newly created repository.
     * <p><b>400</b> - The repository was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create a repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepository> retryCreateRepositoryWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling retryCreateRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling retryCreateRepository");
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

        ParameterizedTypeReference<RestRepository> localReturnType = new ParameterizedTypeReference<RestRepository>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/recreate", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke project permissions
     * Revoke all permissions for the specified project for the given groups and users.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permission if their own permission would be revoked as a result, nor may they revoke their own permission unless they have a global permission that already implies that permission.
     * <p><b>204</b> - All project permissions were revoked from the users and groups for the specified project.
     * <p><b>400</b> - No permissions were revoked because the request was invalid. No users or groups were provided.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist, or one or more of the users or groups provided does not exist.
     * <p><b>409</b> - The action was disallowed as it would revoke the currently authenticated user&#39;s permission on the project.
     * @param projectKey The project key (required)
     * @param user The names of the users (optional)
     * @param group The names of the groups (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissions(String projectKey, String user, String group) throws RestClientException {
        revokePermissionsWithHttpInfo(projectKey, user, group);
    }

    /**
     * Revoke project permissions
     * Revoke all permissions for the specified project for the given groups and users.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permission if their own permission would be revoked as a result, nor may they revoke their own permission unless they have a global permission that already implies that permission.
     * <p><b>204</b> - All project permissions were revoked from the users and groups for the specified project.
     * <p><b>400</b> - No permissions were revoked because the request was invalid. No users or groups were provided.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist, or one or more of the users or groups provided does not exist.
     * <p><b>409</b> - The action was disallowed as it would revoke the currently authenticated user&#39;s permission on the project.
     * @param projectKey The project key (required)
     * @param user The names of the users (optional)
     * @param group The names of the groups (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsWithHttpInfo(String projectKey, String user, String group) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "user", user));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke group project permission
     *  Revoke all permissions for the specified project for a group.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permissions if it will reduce their own permission level.
     * <p><b>204</b> - All project permissions were revoked from the group for the specified project.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> -  The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * @param projectKey The project key (required)
     * @param name The name of the group (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForGroup1(String projectKey, String name) throws RestClientException {
        revokePermissionsForGroup1WithHttpInfo(projectKey, name);
    }

    /**
     * Revoke group project permission
     *  Revoke all permissions for the specified project for a group.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permissions if it will reduce their own permission level.
     * <p><b>204</b> - All project permissions were revoked from the group for the specified project.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> -  The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * @param projectKey The project key (required)
     * @param name The name of the group (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForGroup1WithHttpInfo(String projectKey, String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissionsForGroup1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/groups", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke user project permission
     * Revoke all permissions for the specified project for a user.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke their own project permissions if they do not have a higher global permission.
     * <p><b>204</b> - All project permissions were revoked from the user for the specified project.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> -  The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * @param projectKey The project key (required)
     * @param name The name of the user (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForUser1(String projectKey, String name) throws RestClientException {
        revokePermissionsForUser1WithHttpInfo(projectKey, name);
    }

    /**
     * Revoke user project permission
     * Revoke all permissions for the specified project for a user.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.  In addition, a user may not revoke their own project permissions if they do not have a higher global permission.
     * <p><b>204</b> - All project permissions were revoked from the user for the specified project.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>404</b> - The specified project does not exist.
     * <p><b>409</b> -  The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * @param projectKey The project key (required)
     * @param name The name of the user (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForUser1WithHttpInfo(String projectKey, String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissionsForUser1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/users", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search project permissions
     * Search direct and implied permissions of principals (users and groups). This endpoint returns a superset of the results returned by the /users and /groups endpoints because it allows filtering by global permissions too.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>0</b> - default response
     * @param projectKey The project key (required)
     * @param permission Permissions to filter by. See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. This parameter can be specified multiple times to filter by more than one permission, and can contain global and project permissions.   (optional)
     * @param filterText Name of the user or group to filter the name of (optional)
     * @param type Type of entity (user or group)Valid entity types are:  - USER- GROUP (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void searchPermissions(String projectKey, String permission, String filterText, String type) throws RestClientException {
        searchPermissionsWithHttpInfo(projectKey, permission, filterText, type);
    }

    /**
     * Search project permissions
     * Search direct and implied permissions of principals (users and groups). This endpoint returns a superset of the results returned by the /users and /groups endpoints because it allows filtering by global permissions too.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource.
     * <p><b>0</b> - default response
     * @param projectKey The project key (required)
     * @param permission Permissions to filter by. See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. This parameter can be specified multiple times to filter by more than one permission, and can contain global and project permissions.   (optional)
     * @param filterText Name of the user or group to filter the name of (optional)
     * @param type Type of entity (user or group)Valid entity types are:  - USER- GROUP (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> searchPermissionsWithHttpInfo(String projectKey, String permission, String filterText, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling searchPermissions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json;charset=UTF-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/search", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create/update a hook script
     * Creates/updates the hook script configuration for the provided hook script and project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>400</b> - The hook script was not created/updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The project key supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param restHookScriptTriggers The hook triggers for which the hook script should be run (optional)
     * @return RestHookScriptConfig
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestHookScriptConfig setConfiguration(String projectKey, String scriptId, RestHookScriptTriggers restHookScriptTriggers) throws RestClientException {
        return setConfigurationWithHttpInfo(projectKey, scriptId, restHookScriptTriggers).getBody();
    }

    /**
     * Create/update a hook script
     * Creates/updates the hook script configuration for the provided hook script and project.   This endpoint requires **PROJECT_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>400</b> - The hook script was not created/updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the specified project.
     * <p><b>404</b> - The project key supplied does not exist.
     * @param projectKey The project key. (required)
     * @param scriptId The ID of the hook script (required)
     * @param restHookScriptTriggers The hook triggers for which the hook script should be run (optional)
     * @return ResponseEntity&lt;RestHookScriptConfig&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestHookScriptConfig> setConfigurationWithHttpInfo(String projectKey, String scriptId, RestHookScriptTriggers restHookScriptTriggers) throws RestClientException {
        Object localVarPostBody = restHookScriptTriggers;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setConfiguration");
        }
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling setConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("scriptId", scriptId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/hook-scripts/{scriptId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create/Update auto decline settings
     * Creates or updates the auto decline settings for the supplied project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for this project to call the resource.
     * <p><b>200</b> - The auto decline settings
     * <p><b>400</b> - inactivityWeeks was not one of 1, 2, 4, 8, or, 12, or the enabled parameter was not included in the request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create or update the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param restAutoDeclineSettingsRequest The settings to create or update (optional)
     * @return RestAutoDeclineSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAutoDeclineSettings setCreateUpdateAutoDeclineSettings(String projectKey, RestAutoDeclineSettingsRequest restAutoDeclineSettingsRequest) throws RestClientException {
        return setCreateUpdateAutoDeclineSettingsWithHttpInfo(projectKey, restAutoDeclineSettingsRequest).getBody();
    }

    /**
     * Create/Update auto decline settings
     * Creates or updates the auto decline settings for the supplied project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for this project to call the resource.
     * <p><b>200</b> - The auto decline settings
     * <p><b>400</b> - inactivityWeeks was not one of 1, 2, 4, 8, or, 12, or the enabled parameter was not included in the request.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to create or update the auto decline settings.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param restAutoDeclineSettingsRequest The settings to create or update (optional)
     * @return ResponseEntity&lt;RestAutoDeclineSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAutoDeclineSettings> setCreateUpdateAutoDeclineSettingsWithHttpInfo(String projectKey, RestAutoDeclineSettingsRequest restAutoDeclineSettingsRequest) throws RestClientException {
        Object localVarPostBody = restAutoDeclineSettingsRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setCreateUpdateAutoDeclineSettings");
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

        ParameterizedTypeReference<RestAutoDeclineSettings> localReturnType = new ParameterizedTypeReference<RestAutoDeclineSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/auto-decline", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update default branch for repository
     * Update the default branch of a repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The default branch was updated.
     * <p><b>401</b> - The authenticated user does not have permission to modify the default branch.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranch The branch to set as default (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setDefaultBranch2(String projectKey, String repositorySlug, RestBranch restBranch) throws RestClientException {
        setDefaultBranch2WithHttpInfo(projectKey, repositorySlug, restBranch);
    }

    /**
     * Update default branch for repository
     * Update the default branch of a repository.   The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>204</b> - The default branch was updated.
     * <p><b>401</b> - The authenticated user does not have permission to modify the default branch.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restBranch The branch to set as default (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setDefaultBranch2WithHttpInfo(String projectKey, String repositorySlug, RestBranch restBranch) throws RestClientException {
        Object localVarPostBody = restBranch;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setDefaultBranch2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setDefaultBranch2");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/default-branch", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update group project permission
     * Promote or demote a group&#39;s permission level for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource. In addition, a user may not demote a group&#39;s permission level if theirown permission level would be reduced as a result.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param name The names of the groups (optional)
     * @param permission The permission to grant.See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForGroups1(String projectKey, String name, String permission) throws RestClientException {
        setPermissionForGroups1WithHttpInfo(projectKey, name, permission);
    }

    /**
     * Update group project permission
     * Promote or demote a group&#39;s permission level for the specified project.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource. In addition, a user may not demote a group&#39;s permission level if theirown permission level would be reduced as a result.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param name The names of the groups (optional)
     * @param permission The permission to grant.See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForGroups1WithHttpInfo(String projectKey, String name, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setPermissionForGroups1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/groups", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user project permission
     * Promote or demote a user&#39;s permission level for the specified project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource. In addition, a user may not reduce their own permission level unless they have a global permission that already implies that permission.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param name The names of the users (optional)
     * @param permission The permission to grant.See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForUsers1(String projectKey, String name, String permission) throws RestClientException {
        setPermissionForUsers1WithHttpInfo(projectKey, name, permission);
    }

    /**
     * Update user project permission
     * Promote or demote a user&#39;s permission level for the specified project.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project or a higher global permission to call this resource. In addition, a user may not reduce their own permission level unless they have a global permission that already implies that permission.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specifiedspecified project.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;spermission level.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param name The names of the users (optional)
     * @param permission The permission to grant.See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+project+permissions)for a detailed explanation of what each permission entails. Available project permissions are:  - PROJECT_READ - PROJECT_WRITE - PROJECT_ADMIN    (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForUsers1WithHttpInfo(String projectKey, String name, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setPermissionForUsers1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/permissions/users", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update repository hook settings
     * Modify the settings for a repository hook for this project.   The service will reject any settings which are too large, the current limit is 32KB once serialized.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.   A JSON document can be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to modify the hook settings.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The complete module key of the hook module. (required)
     * @param exampleSettings The raw settings. (optional)
     * @return ExampleSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleSettings setSettings(String projectKey, String hookKey, ExampleSettings exampleSettings) throws RestClientException {
        return setSettingsWithHttpInfo(projectKey, hookKey, exampleSettings).getBody();
    }

    /**
     * Update repository hook settings
     * Modify the settings for a repository hook for this project.   The service will reject any settings which are too large, the current limit is 32KB once serialized.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.   A JSON document can be provided to use as the settings for the hook. These structure and validity of the document is decided by the plugin providing the hook.
     * <p><b>200</b> - The settings for the hook.
     * <p><b>400</b> - The settings specified are invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to modify the hook settings.
     * <p><b>404</b> - The specified project or hook does not exist.
     * @param projectKey The project key. (required)
     * @param hookKey The complete module key of the hook module. (required)
     * @param exampleSettings The raw settings. (optional)
     * @return ResponseEntity&lt;ExampleSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleSettings> setSettingsWithHttpInfo(String projectKey, String hookKey, ExampleSettings exampleSettings) throws RestClientException {
        Object localVarPostBody = exampleSettings;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setSettings");
        }
        
        // verify the required parameter 'hookKey' is set
        if (hookKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'hookKey' when calling setSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("hookKey", hookKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/hooks/{hookKey}/settings", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository contributing guidelines
     * Retrieves the contributing guidelines for the repository, if they&#39;ve been defined.   This checks the repository for a CONTRIBUTING file, optionally with an md or txt extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;code&gt;?markup&lt;/code&gt; to the URL will stream an HTML-rendered version instead.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The contributing guidelines for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamContributing(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        streamContributingWithHttpInfo(projectKey, repositorySlug, at, markup, htmlEscape, includeHeadingId, hardwrap);
    }

    /**
     * Get repository contributing guidelines
     * Retrieves the contributing guidelines for the repository, if they&#39;ve been defined.   This checks the repository for a CONTRIBUTING file, optionally with an md or txt extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;code&gt;?markup&lt;/code&gt; to the URL will stream an HTML-rendered version instead.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The contributing guidelines for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamContributingWithHttpInfo(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamContributing");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamContributing");
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
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "markup", markup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "htmlEscape", htmlEscape));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeHeadingId", includeHeadingId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "hardwrap", hardwrap));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/contributing", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository license
     * Retrieves the license for the repository, if it&#39;s been defined.   This checks the repository for a &lt;pre&gt;LICENSE&lt;/pre&gt; file, optionally with an &lt;pre&gt;md&lt;/pre&gt; or &lt;pre&gt;txt&lt;/pre&gt;extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;pre&gt;?markup&lt;/pre&gt; to the URL will stream an HTML-rendered version instead.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The license for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamLicense(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        streamLicenseWithHttpInfo(projectKey, repositorySlug, at, markup, htmlEscape, includeHeadingId, hardwrap);
    }

    /**
     * Get repository license
     * Retrieves the license for the repository, if it&#39;s been defined.   This checks the repository for a &lt;pre&gt;LICENSE&lt;/pre&gt; file, optionally with an &lt;pre&gt;md&lt;/pre&gt; or &lt;pre&gt;txt&lt;/pre&gt;extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;pre&gt;?markup&lt;/pre&gt; to the URL will stream an HTML-rendered version instead.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The license for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamLicenseWithHttpInfo(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamLicense");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamLicense");
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
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "markup", markup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "htmlEscape", htmlEscape));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeHeadingId", includeHeadingId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "hardwrap", hardwrap));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/license", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository readme
     * Retrieves the README for the repository, if it&#39;s been defined.   This checks the repository for a &lt;pre&gt;README&lt;/pre&gt; file, optionally with an &lt;pre&gt;md&lt;/pre&gt; or &lt;pre&gt;txt&lt;/pre&gt;extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;pre&gt;?markup&lt;/pre&gt; to the URL will stream an HTML-rendered version instead. Note that, when streaming HTML, relative URLs in the README will not work if applied relative to this URL.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The README for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void streamReadme(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        streamReadmeWithHttpInfo(projectKey, repositorySlug, at, markup, htmlEscape, includeHeadingId, hardwrap);
    }

    /**
     * Get repository readme
     * Retrieves the README for the repository, if it&#39;s been defined.   This checks the repository for a &lt;pre&gt;README&lt;/pre&gt; file, optionally with an &lt;pre&gt;md&lt;/pre&gt; or &lt;pre&gt;txt&lt;/pre&gt;extension, and, if found, streams it. By default, the &lt;i&gt;raw content&lt;/i&gt; of the file is streamed. Appending &lt;pre&gt;?markup&lt;/pre&gt; to the URL will stream an HTML-rendered version instead. Note that, when streaming HTML, relative URLs in the README will not work if applied relative to this URL.   The authenticated user must have &lt;strong&gt;REPO_READ&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>200</b> - The README for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to read the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param at A specific commit or ref to retrieve the guidelines at, or the default branch if not specified (optional)
     * @param markup If present or &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, triggers the raw content to be markup-rendered and returned as HTML; otherwise, if not specified, or any value other than &lt;code&gt;\&quot;true\&quot;&lt;/code&gt;, the content is streamed without markup (optional)
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. If not specified, the value of the &lt;code&gt;markup.render.html.escape&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @param includeHeadingId (Optional) true if headings should contain an ID based on the heading content. If not specified, the value of the &lt;code&gt;markup.render.headerids&lt;/code&gt; property, which is false by default, will be used (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. If not specified, the value of the &lt;code&gt;markup.render.hardwrap&lt;/code&gt; property, which is &lt;code&gt;true&lt;/code&gt; by default, will be used (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> streamReadmeWithHttpInfo(String projectKey, String repositorySlug, String at, String markup, String htmlEscape, String includeHeadingId, String hardwrap) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling streamReadme");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling streamReadme");
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
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "markup", markup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "htmlEscape", htmlEscape));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeHeadingId", includeHeadingId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "hardwrap", hardwrap));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/readme", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Test webhook
     * Test connectivity to a specific endpoint.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to test a connection.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param webhookId  (optional)
     * @param sslVerificationRequired  (optional, default to true)
     * @param url The url in which to connect to (optional)
     * @param restWebhookCredentials Basic authentication credentials, if required. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object testWebhook(String projectKey, Integer webhookId, Boolean sslVerificationRequired, String url, RestWebhookCredentials restWebhookCredentials) throws RestClientException {
        return testWebhookWithHttpInfo(projectKey, webhookId, sslVerificationRequired, url, restWebhookCredentials).getBody();
    }

    /**
     * Test webhook
     * Test connectivity to a specific endpoint.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to test a connection.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param webhookId  (optional)
     * @param sslVerificationRequired  (optional, default to true)
     * @param url The url in which to connect to (optional)
     * @param restWebhookCredentials Basic authentication credentials, if required. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> testWebhookWithHttpInfo(String projectKey, Integer webhookId, Boolean sslVerificationRequired, String url, RestWebhookCredentials restWebhookCredentials) throws RestClientException {
        Object localVarPostBody = restWebhookCredentials;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling testWebhook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/test", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update project
     * Update the project matching the &lt;strong&gt;projectKey&lt;/strong&gt; supplied in the resource path.   To include a custom avatar for the updated project, the project definition should contain an additional attribute with the key &lt;code&gt;avatar&lt;/code&gt; and the value a data URI containing Base64-encoded image data. The URI should be in the following format:  &#x60;&#x60;&#x60;    data:(content type, e.g. image/png);base64,(data)&#x60;&#x60;&#x60;  If the data is not Base64-encoded, or if a character set is defined in the URI, or the URI is otherwise invalid, &lt;em&gt;project creation will fail&lt;/em&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The updated project. The project&#39;s key &lt;strong&gt;was not&lt;/strong&gt; updated.
     * <p><b>201</b> - The updated project. The project&#39;s key &lt;strong&gt;was&lt;/strong&gt; updated.
     * <p><b>400</b> - The project was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param restProject Project parameters to update. (optional)
     * @return RestProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestProject updateProject(String projectKey, RestProject restProject) throws RestClientException {
        return updateProjectWithHttpInfo(projectKey, restProject).getBody();
    }

    /**
     * Update project
     * Update the project matching the &lt;strong&gt;projectKey&lt;/strong&gt; supplied in the resource path.   To include a custom avatar for the updated project, the project definition should contain an additional attribute with the key &lt;code&gt;avatar&lt;/code&gt; and the value a data URI containing Base64-encoded image data. The URI should be in the following format:  &#x60;&#x60;&#x60;    data:(content type, e.g. image/png);base64,(data)&#x60;&#x60;&#x60;  If the data is not Base64-encoded, or if a character set is defined in the URI, or the URI is otherwise invalid, &lt;em&gt;project creation will fail&lt;/em&gt;.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - The updated project. The project&#39;s key &lt;strong&gt;was not&lt;/strong&gt; updated.
     * <p><b>201</b> - The updated project. The project&#39;s key &lt;strong&gt;was&lt;/strong&gt; updated.
     * <p><b>400</b> - The project was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param restProject Project parameters to update. (optional)
     * @return ResponseEntity&lt;RestProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestProject> updateProjectWithHttpInfo(String projectKey, RestProject restProject) throws RestClientException {
        Object localVarPostBody = restProject;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateProject");
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

        ParameterizedTypeReference<RestProject> localReturnType = new ParameterizedTypeReference<RestProject>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update merge strategy
     * Update the pull request merge strategy configuration for this project and SCM.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the context repository to call this resource.   Only the strategies provided will be enabled, the default must be set and included in the set of strategies.   An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:  &lt;pre&gt;{      \&quot;mergeConfig\&quot;: {}  }  &lt;/pre&gt;  Upon completion of this request, the effective configuration will be the configuration explicitly set for the SCM, or if no such explicit configuration is set then the default configuration will be used.
     * <p><b>200</b> - The merge configuration of the request project.
     * <p><b>400</b> - The repository pull request merge strategies were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to administrate the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param scmId The SCM to get strategies for. (required)
     * @param restPullRequestSettings The settings. (optional)
     * @return RestPullRequestSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestPullRequestSettings updatePullRequestSettings(String projectKey, String scmId, RestPullRequestSettings restPullRequestSettings) throws RestClientException {
        return updatePullRequestSettingsWithHttpInfo(projectKey, scmId, restPullRequestSettings).getBody();
    }

    /**
     * Update merge strategy
     * Update the pull request merge strategy configuration for this project and SCM.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the context repository to call this resource.   Only the strategies provided will be enabled, the default must be set and included in the set of strategies.   An explicitly set pull request merge strategy configuration can be deleted by POSTing a document with an empty \&quot;mergeConfig\&quot; attribute. i.e:  &lt;pre&gt;{      \&quot;mergeConfig\&quot;: {}  }  &lt;/pre&gt;  Upon completion of this request, the effective configuration will be the configuration explicitly set for the SCM, or if no such explicit configuration is set then the default configuration will be used.
     * <p><b>200</b> - The merge configuration of the request project.
     * <p><b>400</b> - The repository pull request merge strategies were not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to administrate the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param scmId The SCM to get strategies for. (required)
     * @param restPullRequestSettings The settings. (optional)
     * @return ResponseEntity&lt;RestPullRequestSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestPullRequestSettings> updatePullRequestSettingsWithHttpInfo(String projectKey, String scmId, RestPullRequestSettings restPullRequestSettings) throws RestClientException {
        Object localVarPostBody = restPullRequestSettings;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePullRequestSettings");
        }
        
        // verify the required parameter 'scmId' is set
        if (scmId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scmId' when calling updatePullRequestSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
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

        ParameterizedTypeReference<RestPullRequestSettings> localReturnType = new ParameterizedTypeReference<RestPullRequestSettings>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/settings/pull-requests/{scmId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update repository
     * Update the repository matching the &lt;strong&gt;repositorySlug&lt;/strong&gt; supplied in the resource path.   The repository&#39;s slug is derived from its name. If the name changes the slug may also change.   This resource can be used to change the repository&#39;s default branch by specifying a new default branch in the request. For example: &lt;code&gt;\&quot;defaultBranch\&quot;:\&quot;main\&quot;&lt;/code&gt;  This resource can be used to move the repository to a different project by specifying a new project in the request. For example: &lt;code&gt;\&quot;project\&quot;:{\&quot;key\&quot;:\&quot;NEW_KEY\&quot;}&lt;/code&gt;  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>201</b> - The updated repository.
     * <p><b>400</b> - The repository was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a repository.
     * <p><b>403</b> - Cannot archive repository because it has open pull requests.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - A repository with the same name as the target already exists, or the repository is archived.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The updated repository. (optional)
     * @return RestRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepository updateRepository(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        return updateRepositoryWithHttpInfo(projectKey, repositorySlug, restRepository).getBody();
    }

    /**
     * Update repository
     * Update the repository matching the &lt;strong&gt;repositorySlug&lt;/strong&gt; supplied in the resource path.   The repository&#39;s slug is derived from its name. If the name changes the slug may also change.   This resource can be used to change the repository&#39;s default branch by specifying a new default branch in the request. For example: &lt;code&gt;\&quot;defaultBranch\&quot;:\&quot;main\&quot;&lt;/code&gt;  This resource can be used to move the repository to a different project by specifying a new project in the request. For example: &lt;code&gt;\&quot;project\&quot;:{\&quot;key\&quot;:\&quot;NEW_KEY\&quot;}&lt;/code&gt;  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository to call this resource.
     * <p><b>201</b> - The updated repository.
     * <p><b>400</b> - The repository was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a repository.
     * <p><b>403</b> - Cannot archive repository because it has open pull requests.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - A repository with the same name as the target already exists, or the repository is archived.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restRepository The updated repository. (optional)
     * @return ResponseEntity&lt;RestRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepository> updateRepositoryWithHttpInfo(String projectKey, String repositorySlug, RestRepository restRepository) throws RestClientException {
        Object localVarPostBody = restRepository;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updateRepository");
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

        ParameterizedTypeReference<RestRepository> localReturnType = new ParameterizedTypeReference<RestRepository>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Update an existing webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a webhook in this project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId Id of the existing webhook (required)
     * @param restWebhook The representation of the updated values for the webhook (optional)
     * @return RestWebhook
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestWebhook updateWebhook(String projectKey, String webhookId, RestWebhook restWebhook) throws RestClientException {
        return updateWebhookWithHttpInfo(projectKey, webhookId, restWebhook).getBody();
    }

    /**
     * 
     * Update an existing webhook.   The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>200</b> - A webhook.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update a webhook in this project.
     * <p><b>404</b> - The project does not exist, or the webhook does not exist in the project.
     * @param projectKey The project key. (required)
     * @param webhookId Id of the existing webhook (required)
     * @param restWebhook The representation of the updated values for the webhook (optional)
     * @return ResponseEntity&lt;RestWebhook&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestWebhook> updateWebhookWithHttpInfo(String projectKey, String webhookId, RestWebhook restWebhook) throws RestClientException {
        Object localVarPostBody = restWebhook;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updateWebhook");
        }
        
        // verify the required parameter 'webhookId' is set
        if (webhookId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'webhookId' when calling updateWebhook");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("webhookId", webhookId);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/webhooks/{webhookId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update project avatar
     * Update the avatar for the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   This resource accepts POST multipart form data, containing a single image in a form-field named &#39;avatar&#39;.   There are configurable server limits on both the dimensions (1024x1024 pixels by default) and uploaded file size (1MB by default). Several different image formats are supported, but &lt;strong&gt;PNG&lt;/strong&gt; and &lt;strong&gt;JPEG&lt;/strong&gt; are preferred due to the file size limit.   This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to upload an image name &#39;avatar.png&#39; would be: &#x60;&#x60;&#x60;curl -X POST -u username:password -H \&quot;X-Atlassian-Token: no-check\&quot; http://example.com/rest/api/1.0/projects/STASH/avatar.png -F avatar&#x3D;@avatar.png &#x60;&#x60;&#x60;  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>201</b> - The avatar was uploaded successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param avatar The avatar file to upload. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void uploadAvatar(String projectKey, File avatar) throws RestClientException {
        uploadAvatarWithHttpInfo(projectKey, avatar);
    }

    /**
     * Update project avatar
     * Update the avatar for the project matching the supplied &lt;strong&gt;projectKey&lt;/strong&gt;.   This resource accepts POST multipart form data, containing a single image in a form-field named &#39;avatar&#39;.   There are configurable server limits on both the dimensions (1024x1024 pixels by default) and uploaded file size (1MB by default). Several different image formats are supported, but &lt;strong&gt;PNG&lt;/strong&gt; and &lt;strong&gt;JPEG&lt;/strong&gt; are preferred due to the file size limit.   This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to upload an image name &#39;avatar.png&#39; would be: &#x60;&#x60;&#x60;curl -X POST -u username:password -H \&quot;X-Atlassian-Token: no-check\&quot; http://example.com/rest/api/1.0/projects/STASH/avatar.png -F avatar&#x3D;@avatar.png &#x60;&#x60;&#x60;  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified project to call this resource.
     * <p><b>201</b> - The avatar was uploaded successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param avatar The avatar file to upload. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> uploadAvatarWithHttpInfo(String projectKey, File avatar) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling uploadAvatar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (avatar != null)
            localVarFormParams.add("avatar", new FileSystemResource(avatar));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/avatar.png", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
