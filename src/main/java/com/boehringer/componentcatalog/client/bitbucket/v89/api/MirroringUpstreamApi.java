package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.EnrichedRepository;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAllReposForProject200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ListMirrors200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ListRequests200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAnalyticsSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestApplicationUserWithPermissions;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAuthenticationRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirrorServer;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirrorUpgradeRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirroredRepositoryDescriptor;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirroringRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestProject;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryMirrorEvent;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.MirroringUpstreamApi")
public class MirroringUpstreamApi extends BaseApi {

    public MirroringUpstreamApi() {
        super(new ApiClient());
    }

    @Autowired
    public MirroringUpstreamApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Accept a mirroring request
     * Accepts a mirroring request
     * <p><b>200</b> - The accepted mirror server
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the request to accept (required)
     * @return RestMirrorServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorServer accept(String mirroringRequestId) throws RestClientException {
        return acceptWithHttpInfo(mirroringRequestId).getBody();
    }

    /**
     * Accept a mirroring request
     * Accepts a mirroring request
     * <p><b>200</b> - The accepted mirror server
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the request to accept (required)
     * @return ResponseEntity&lt;RestMirrorServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorServer> acceptWithHttpInfo(String mirroringRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirroringRequestId' is set
        if (mirroringRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirroringRequestId' when calling accept");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirroringRequestId", mirroringRequestId);

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

        ParameterizedTypeReference<RestMirrorServer> localReturnType = new ParameterizedTypeReference<RestMirrorServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/requests/{mirroringRequestId}/accept", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - The analytics settings from upstream
     * @return RestAnalyticsSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAnalyticsSettings analyticsSettings() throws RestClientException {
        return analyticsSettingsWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - The analytics settings from upstream
     * @return ResponseEntity&lt;RestAnalyticsSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAnalyticsSettings> analyticsSettingsWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestAnalyticsSettings> localReturnType = new ParameterizedTypeReference<RestAnalyticsSettings>() {};
        return apiClient.invokeAPI("/mirroring/latest/analyticsSettings", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Authenticates on behalf of a user. Used by mirrors to check the credentials supplied to them by users. If successful a user and their effective permissions are returned as follows -  * For SSH credentials - all the effective user permissions are returned. * For all other credentials - the highest global permission is returned along with highest repository permission if repository ID is also provided in the request.  Currently only username/password, bearer token and SSH credentials are supported.
     * <p><b>200</b> - The user for the supplied credentials and their effective permissions}.
     * <p><b>400</b> - If the supplied credentials are incomplete or not understood.
     * <p><b>401</b> -  The currently authenticated user is not permitted to authenticate on behalf of users or authentication with the supplied user credentials failed for some reason
     * @param restAuthenticationRequest  (optional)
     * @return RestApplicationUserWithPermissions
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestApplicationUserWithPermissions authenticate(RestAuthenticationRequest restAuthenticationRequest) throws RestClientException {
        return authenticateWithHttpInfo(restAuthenticationRequest).getBody();
    }

    /**
     * 
     * Authenticates on behalf of a user. Used by mirrors to check the credentials supplied to them by users. If successful a user and their effective permissions are returned as follows -  * For SSH credentials - all the effective user permissions are returned. * For all other credentials - the highest global permission is returned along with highest repository permission if repository ID is also provided in the request.  Currently only username/password, bearer token and SSH credentials are supported.
     * <p><b>200</b> - The user for the supplied credentials and their effective permissions}.
     * <p><b>400</b> - If the supplied credentials are incomplete or not understood.
     * <p><b>401</b> -  The currently authenticated user is not permitted to authenticate on behalf of users or authentication with the supplied user credentials failed for some reason
     * @param restAuthenticationRequest  (optional)
     * @return ResponseEntity&lt;RestApplicationUserWithPermissions&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestApplicationUserWithPermissions> authenticateWithHttpInfo(RestAuthenticationRequest restAuthenticationRequest) throws RestClientException {
        Object localVarPostBody = restAuthenticationRequest;
        

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

        ParameterizedTypeReference<RestApplicationUserWithPermissions> localReturnType = new ParameterizedTypeReference<RestApplicationUserWithPermissions>() {};
        return apiClient.invokeAPI("/mirroring/latest/authenticate", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a mirroring request
     * Deletes a mirroring request
     * <p><b>204</b> - The request was deleted
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the mirroring request to delete (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteMirroringRequest(String mirroringRequestId) throws RestClientException {
        deleteMirroringRequestWithHttpInfo(mirroringRequestId);
    }

    /**
     * Delete a mirroring request
     * Deletes a mirroring request
     * <p><b>204</b> - The request was deleted
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the mirroring request to delete (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteMirroringRequestWithHttpInfo(String mirroringRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirroringRequestId' is set
        if (mirroringRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirroringRequestId' when calling deleteMirroringRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirroringRequestId", mirroringRequestId);

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
        return apiClient.invokeAPI("/mirroring/latest/requests/{mirroringRequestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove preferred mirror
     * Removes the current user&#39;s preferred mirror
     * <p><b>204</b> - an empty response indicating that the user setting has been updated
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deletePreferredMirrorId() throws RestClientException {
        deletePreferredMirrorIdWithHttpInfo();
    }

    /**
     * Remove preferred mirror
     * Removes the current user&#39;s preferred mirror
     * <p><b>204</b> - an empty response indicating that the user setting has been updated
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deletePreferredMirrorIdWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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
        return apiClient.invokeAPI("/mirroring/latest/account/settings/preferred-mirror", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param mirrorId  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void get1(String mirrorId) throws RestClientException {
        get1WithHttpInfo(mirrorId);
    }

    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param mirrorId  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> get1WithHttpInfo(String mirrorId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling get1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/html"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}/webPanels/config", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get hashes for repositories in project
     * Returns a page of repositories for a given project, enriched with a content hash
     * <p><b>200</b> - A page of repositories with content hashes
     * <p><b>409</b> - Mirroring is not available
     * @param projectId the id of the requested project (required)
     * @param includeDefaultBranch includes defaultBranchId in the response, if &lt;code&gt;true&lt;/code&gt;. Default value is &lt;code&gt;false&lt;/code&gt; (optional, default to false)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetAllReposForProject200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetAllReposForProject200Response getAllReposForProject(String projectId, String includeDefaultBranch, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getAllReposForProjectWithHttpInfo(projectId, includeDefaultBranch, start, limit).getBody();
    }

    /**
     * Get hashes for repositories in project
     * Returns a page of repositories for a given project, enriched with a content hash
     * <p><b>200</b> - A page of repositories with content hashes
     * <p><b>409</b> - Mirroring is not available
     * @param projectId the id of the requested project (required)
     * @param includeDefaultBranch includes defaultBranchId in the response, if &lt;code&gt;true&lt;/code&gt;. Default value is &lt;code&gt;false&lt;/code&gt; (optional, default to false)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetAllReposForProject200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetAllReposForProject200Response> getAllReposForProjectWithHttpInfo(String projectId, String includeDefaultBranch, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectId' when calling getAllReposForProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectId", projectId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDefaultBranch", includeDefaultBranch));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetAllReposForProject200Response> localReturnType = new ParameterizedTypeReference<GetAllReposForProject200Response>() {};
        return apiClient.invokeAPI("/mirroring/latest/projects/{projectId}/repos", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirror auth token
     * Returns an authentication token for the mirror server in question
     * <p><b>200</b> - the mirror auth token
     * <p><b>404</b> - The mirror could not be found.
     * @param mirrorId the mirror ID (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getAuthToken(String mirrorId) throws RestClientException {
        return getAuthTokenWithHttpInfo(mirrorId).getBody();
    }

    /**
     * Get mirror auth token
     * Returns an authentication token for the mirror server in question
     * <p><b>200</b> - the mirror auth token
     * <p><b>404</b> - The mirror could not be found.
     * @param mirrorId the mirror ID (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getAuthTokenWithHttpInfo(String mirrorId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling getAuthToken");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

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
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}/token", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get content hash for a repository
     * Returns a repository enriched with a content hash and default branch
     * <p><b>200</b> - The repository with the specified repoId
     * <p><b>404</b> - Repository not found
     * @param repoId the ID of the requested repository (required)
     * @param includeDefaultBranch  (optional, default to false)
     * @return EnrichedRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public EnrichedRepository getContentHashForRepository(String repoId, Boolean includeDefaultBranch) throws RestClientException {
        return getContentHashForRepositoryWithHttpInfo(repoId, includeDefaultBranch).getBody();
    }

    /**
     * Get content hash for a repository
     * Returns a repository enriched with a content hash and default branch
     * <p><b>200</b> - The repository with the specified repoId
     * <p><b>404</b> - Repository not found
     * @param repoId the ID of the requested repository (required)
     * @param includeDefaultBranch  (optional, default to false)
     * @return ResponseEntity&lt;EnrichedRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<EnrichedRepository> getContentHashForRepositoryWithHttpInfo(String repoId, Boolean includeDefaultBranch) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'repoId' is set
        if (repoId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repoId' when calling getContentHashForRepository");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("repoId", repoId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDefaultBranch", includeDefaultBranch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<EnrichedRepository> localReturnType = new ParameterizedTypeReference<EnrichedRepository>() {};
        return apiClient.invokeAPI("/mirroring/latest/repos/{repoId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get content hashes for repositories
     * Returns a page of repositories enriched with a content hash and default branch
     * <p><b>200</b> - A page of repositories with content hashes and default branch
     * <p><b>409</b> - Mirroring is not available
     * @param includeDefaultBranch includes defaultBranchId for each repository in the response, if &lt;code&gt;true&lt;/code&gt;. Default value is &lt;code&gt;false&lt;/code&gt;. (optional, default to false)
     * @return EnrichedRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public EnrichedRepository getContentHashesForRepositories(String includeDefaultBranch) throws RestClientException {
        return getContentHashesForRepositoriesWithHttpInfo(includeDefaultBranch).getBody();
    }

    /**
     * Get content hashes for repositories
     * Returns a page of repositories enriched with a content hash and default branch
     * <p><b>200</b> - A page of repositories with content hashes and default branch
     * <p><b>409</b> - Mirroring is not available
     * @param includeDefaultBranch includes defaultBranchId for each repository in the response, if &lt;code&gt;true&lt;/code&gt;. Default value is &lt;code&gt;false&lt;/code&gt;. (optional, default to false)
     * @return ResponseEntity&lt;EnrichedRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<EnrichedRepository> getContentHashesForRepositoriesWithHttpInfo(String includeDefaultBranch) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDefaultBranch", includeDefaultBranch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<EnrichedRepository> localReturnType = new ParameterizedTypeReference<EnrichedRepository>() {};
        return apiClient.invokeAPI("/mirroring/latest/repos", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirror by ID
     * Returns the mirror specified by a mirror ID
     * <p><b>200</b> - the mirror
     * <p><b>404</b> - The mirror could not be found.
     * @param mirrorId the mirror ID (required)
     * @return RestMirrorServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorServer getMirror(String mirrorId) throws RestClientException {
        return getMirrorWithHttpInfo(mirrorId).getBody();
    }

    /**
     * Get mirror by ID
     * Returns the mirror specified by a mirror ID
     * <p><b>200</b> - the mirror
     * <p><b>404</b> - The mirror could not be found.
     * @param mirrorId the mirror ID (required)
     * @return ResponseEntity&lt;RestMirrorServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorServer> getMirrorWithHttpInfo(String mirrorId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling getMirror");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

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

        ParameterizedTypeReference<RestMirrorServer> localReturnType = new ParameterizedTypeReference<RestMirrorServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a mirroring request
     * Retrieves a mirroring request
     * <p><b>200</b> - The mirroring request
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the mirroring request to retrieve (required)
     * @return RestMirroringRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirroringRequest getMirroringRequest(String mirroringRequestId) throws RestClientException {
        return getMirroringRequestWithHttpInfo(mirroringRequestId).getBody();
    }

    /**
     * Get a mirroring request
     * Retrieves a mirroring request
     * <p><b>200</b> - The mirroring request
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the mirroring request to retrieve (required)
     * @return ResponseEntity&lt;RestMirroringRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirroringRequest> getMirroringRequestWithHttpInfo(String mirroringRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirroringRequestId' is set
        if (mirroringRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirroringRequestId' when calling getMirroringRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirroringRequestId", mirroringRequestId);

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

        ParameterizedTypeReference<RestMirroringRequest> localReturnType = new ParameterizedTypeReference<RestMirroringRequest>() {};
        return apiClient.invokeAPI("/mirroring/latest/requests/{mirroringRequestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get preferred mirror
     * Retrieves the current user&#39;s preferred mirror server
     * <p><b>200</b> - the preferred mirror server
     * <p><b>404</b> - The user&#39;s preferred mirror server could not be found.
     * @return RestMirrorServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorServer getPreferredMirrorId() throws RestClientException {
        return getPreferredMirrorIdWithHttpInfo().getBody();
    }

    /**
     * Get preferred mirror
     * Retrieves the current user&#39;s preferred mirror server
     * <p><b>200</b> - the preferred mirror server
     * <p><b>404</b> - The user&#39;s preferred mirror server could not be found.
     * @return ResponseEntity&lt;RestMirrorServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorServer> getPreferredMirrorIdWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestMirrorServer> localReturnType = new ParameterizedTypeReference<RestMirrorServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/account/settings/preferred-mirror", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get project
     * Returns the requested project using its primary key ID.&lt;br&gt; Since 6.7
     * <p><b>200</b> - The project with the specified ID
     * <p><b>404</b> - Project not found
     * @param projectId the ID of the requested project (required)
     * @return RestProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestProject getProjectById(String projectId) throws RestClientException {
        return getProjectByIdWithHttpInfo(projectId).getBody();
    }

    /**
     * Get project
     * Returns the requested project using its primary key ID.&lt;br&gt; Since 6.7
     * <p><b>200</b> - The project with the specified ID
     * <p><b>404</b> - Project not found
     * @param projectId the ID of the requested project (required)
     * @return ResponseEntity&lt;RestProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestProject> getProjectByIdWithHttpInfo(String projectId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectId' when calling getProjectById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectId", projectId);

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
        return apiClient.invokeAPI("/mirroring/latest/projects/{projectId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirrors for repository
     * Returns a page of mirrors for a repository. This resource will return &lt;strong&gt;all mirrors&lt;/strong&gt; along with authorized links to the mirror&#39;s repository REST resource. To determine if a repository is available on the mirror, the returned URL needs to be called.
     * <p><b>200</b> - The mirrored repository descriptor
     * <p><b>409</b> - Mirroring is not available
     * @param repoId the ID of the requested repository (required)
     * @return RestMirroredRepositoryDescriptor
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirroredRepositoryDescriptor getRepositoryMirrors(String repoId) throws RestClientException {
        return getRepositoryMirrorsWithHttpInfo(repoId).getBody();
    }

    /**
     * Get mirrors for repository
     * Returns a page of mirrors for a repository. This resource will return &lt;strong&gt;all mirrors&lt;/strong&gt; along with authorized links to the mirror&#39;s repository REST resource. To determine if a repository is available on the mirror, the returned URL needs to be called.
     * <p><b>200</b> - The mirrored repository descriptor
     * <p><b>409</b> - Mirroring is not available
     * @param repoId the ID of the requested repository (required)
     * @return ResponseEntity&lt;RestMirroredRepositoryDescriptor&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirroredRepositoryDescriptor> getRepositoryMirrorsWithHttpInfo(String repoId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'repoId' is set
        if (repoId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repoId' when calling getRepositoryMirrors");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("repoId", repoId);

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

        ParameterizedTypeReference<RestMirroredRepositoryDescriptor> localReturnType = new ParameterizedTypeReference<RestMirroredRepositoryDescriptor>() {};
        return apiClient.invokeAPI("/mirroring/latest/repos/{repoId}/mirrors", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all mirrors
     * Returns a list of mirrors
     * <p><b>200</b> - a page of mirrors
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ListMirrors200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListMirrors200Response listMirrors(BigDecimal start, BigDecimal limit) throws RestClientException {
        return listMirrorsWithHttpInfo(start, limit).getBody();
    }

    /**
     * Get all mirrors
     * Returns a list of mirrors
     * <p><b>200</b> - a page of mirrors
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;ListMirrors200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListMirrors200Response> listMirrorsWithHttpInfo(BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ListMirrors200Response> localReturnType = new ParameterizedTypeReference<ListMirrors200Response>() {};
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirroring requests
     * Retrieves a mirroring request
     * <p><b>200</b> - A page of mirroring requests
     * @param state (optional) the request state to filter on (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ListRequests200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListRequests200Response listRequests(String state, BigDecimal start, BigDecimal limit) throws RestClientException {
        return listRequestsWithHttpInfo(state, start, limit).getBody();
    }

    /**
     * Get mirroring requests
     * Retrieves a mirroring request
     * <p><b>200</b> - A page of mirroring requests
     * @param state (optional) the request state to filter on (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;ListRequests200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListRequests200Response> listRequestsWithHttpInfo(String state, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

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

        ParameterizedTypeReference<ListRequests200Response> localReturnType = new ParameterizedTypeReference<ListRequests200Response>() {};
        return apiClient.invokeAPI("/mirroring/latest/requests", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Publish RepositoryMirrorEvent
     * Publishes a RepositoryMirrorEvent on the event queue.
     * <p><b>204</b> - The event was successfully placed on the queue
     * <p><b>404</b> - The specified repository does not exist.
     * @param mirrorId the server id of the mirror that raised this event (required)
     * @param restRepositoryMirrorEvent  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void publishEvent(String mirrorId, RestRepositoryMirrorEvent restRepositoryMirrorEvent) throws RestClientException {
        publishEventWithHttpInfo(mirrorId, restRepositoryMirrorEvent);
    }

    /**
     * Publish RepositoryMirrorEvent
     * Publishes a RepositoryMirrorEvent on the event queue.
     * <p><b>204</b> - The event was successfully placed on the queue
     * <p><b>404</b> - The specified repository does not exist.
     * @param mirrorId the server id of the mirror that raised this event (required)
     * @param restRepositoryMirrorEvent  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> publishEventWithHttpInfo(String mirrorId, RestRepositoryMirrorEvent restRepositoryMirrorEvent) throws RestClientException {
        Object localVarPostBody = restRepositoryMirrorEvent;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling publishEvent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

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
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}/events", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a mirroring request
     * Creates a new mirroring request
     * <p><b>200</b> - The created mirroring request
     * <p><b>409</b> - The request was invalid or missing
     * @param restMirroringRequest  (optional)
     * @return RestMirroringRequest
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirroringRequest register(RestMirroringRequest restMirroringRequest) throws RestClientException {
        return registerWithHttpInfo(restMirroringRequest).getBody();
    }

    /**
     * Create a mirroring request
     * Creates a new mirroring request
     * <p><b>200</b> - The created mirroring request
     * <p><b>409</b> - The request was invalid or missing
     * @param restMirroringRequest  (optional)
     * @return ResponseEntity&lt;RestMirroringRequest&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirroringRequest> registerWithHttpInfo(RestMirroringRequest restMirroringRequest) throws RestClientException {
        Object localVarPostBody = restMirroringRequest;
        

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

        ParameterizedTypeReference<RestMirroringRequest> localReturnType = new ParameterizedTypeReference<RestMirroringRequest>() {};
        return apiClient.invokeAPI("/mirroring/latest/requests", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Reject a mirroring request
     * Rejects a mirroring request
     * <p><b>200</b> - The rejected mirror server
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the request to reject (required)
     * @return RestMirrorServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorServer reject(String mirroringRequestId) throws RestClientException {
        return rejectWithHttpInfo(mirroringRequestId).getBody();
    }

    /**
     * Reject a mirroring request
     * Rejects a mirroring request
     * <p><b>200</b> - The rejected mirror server
     * <p><b>409</b> - The request could not be found
     * @param mirroringRequestId the ID of the request to reject (required)
     * @return ResponseEntity&lt;RestMirrorServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorServer> rejectWithHttpInfo(String mirroringRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirroringRequestId' is set
        if (mirroringRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirroringRequestId' when calling reject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirroringRequestId", mirroringRequestId);

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

        ParameterizedTypeReference<RestMirrorServer> localReturnType = new ParameterizedTypeReference<RestMirrorServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/requests/{mirroringRequestId}/reject", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete mirror by ID
     * Removes a mirror, disabling all access and notifications for the mirror server in question
     * <p><b>204</b> - an empty response indicating that the mirror has been removed
     * @param mirrorId the ID of the mirror to remove (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void remove(String mirrorId) throws RestClientException {
        removeWithHttpInfo(mirrorId);
    }

    /**
     * Delete mirror by ID
     * Removes a mirror, disabling all access and notifications for the mirror server in question
     * <p><b>204</b> - an empty response indicating that the mirror has been removed
     * @param mirrorId the ID of the mirror to remove (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> removeWithHttpInfo(String mirrorId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling remove");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

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
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set preferred mirror
     * Sets the mirror specified by a mirror ID as the current user&#39;s preferred mirror
     * <p><b>204</b> - an empty response indicating that the user setting has been updated
     * <p><b>404</b> - The mirror could not be found.
     * @param body the mirror ID (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPreferredMirrorId(String body) throws RestClientException {
        setPreferredMirrorIdWithHttpInfo(body);
    }

    /**
     * Set preferred mirror
     * Sets the mirror specified by a mirror ID as the current user&#39;s preferred mirror
     * <p><b>204</b> - an empty response indicating that the user setting has been updated
     * <p><b>404</b> - The mirror could not be found.
     * @param body the mirror ID (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPreferredMirrorIdWithHttpInfo(String body) throws RestClientException {
        Object localVarPostBody = body;
        

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
        return apiClient.invokeAPI("/mirroring/latest/account/settings/preferred-mirror", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Upgrade add-on for a mirror
     * Upgrades the add-on for the mirror server in question This endpoint can only be called by the mirror instance or system administrators&lt;br&gt;Since 5.8
     * <p><b>200</b> - the mirror
     * @param mirrorId the ID of the mirror to upgrade (required)
     * @param restMirrorUpgradeRequest  (optional)
     * @return RestMirrorServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorServer upgrade(String mirrorId, RestMirrorUpgradeRequest restMirrorUpgradeRequest) throws RestClientException {
        return upgradeWithHttpInfo(mirrorId, restMirrorUpgradeRequest).getBody();
    }

    /**
     * Upgrade add-on for a mirror
     * Upgrades the add-on for the mirror server in question This endpoint can only be called by the mirror instance or system administrators&lt;br&gt;Since 5.8
     * <p><b>200</b> - the mirror
     * @param mirrorId the ID of the mirror to upgrade (required)
     * @param restMirrorUpgradeRequest  (optional)
     * @return ResponseEntity&lt;RestMirrorServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorServer> upgradeWithHttpInfo(String mirrorId, RestMirrorUpgradeRequest restMirrorUpgradeRequest) throws RestClientException {
        Object localVarPostBody = restMirrorUpgradeRequest;
        
        // verify the required parameter 'mirrorId' is set
        if (mirrorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mirrorId' when calling upgrade");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mirrorId", mirrorId);

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

        ParameterizedTypeReference<RestMirrorServer> localReturnType = new ParameterizedTypeReference<RestMirrorServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/mirrorServers/{mirrorId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
