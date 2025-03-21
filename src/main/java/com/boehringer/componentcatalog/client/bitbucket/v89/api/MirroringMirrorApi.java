package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepoSyncStatus200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ListUpstreamServers200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestClusterNode;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestErrors;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirrorRepositorySynchronizationStatus;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMirroredRepository;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRefSyncQueue;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryLockOwner;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSyncProgress;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUpstreamServer;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUpstreamSettings;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.MirroringMirrorApi")
public class MirroringMirrorApi extends BaseApi {

    public MirroringMirrorApi() {
        super(new ApiClient());
    }

    @Autowired
    public MirroringMirrorApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Get farm nodes
     * Retrieves the list of farm nodes in this cluster
     * <p><b>200</b> - The list of farm nodes
     * <p><b>404</b> - The upstream server could not be found
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return List&lt;RestClusterNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestClusterNode> getFarmNodes(String upstreamId) throws RestClientException {
        return getFarmNodesWithHttpInfo(upstreamId).getBody();
    }

    /**
     * Get farm nodes
     * Retrieves the list of farm nodes in this cluster
     * <p><b>200</b> - The list of farm nodes
     * <p><b>404</b> - The upstream server could not be found
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return ResponseEntity&lt;List&lt;RestClusterNode&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestClusterNode>> getFarmNodesWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getFarmNodes");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<List<RestClusterNode>> localReturnType = new ParameterizedTypeReference<List<RestClusterNode>>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/farmNodes", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirror mode
     * Gets the current mirror mode for the specified upstream
     * <p><b>200</b> - the current mirror mode
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMirrorMode(String upstreamId) throws RestClientException {
        getMirrorModeWithHttpInfo(upstreamId);
    }

    /**
     * Get mirror mode
     * Gets the current mirror mode for the specified upstream
     * <p><b>200</b> - the current mirror mode
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMirrorModeWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getMirrorMode");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/mode", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get upstream settings
     * Retrieves upstream settings
     * <p><b>200</b> - the mirror settings
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @return RestUpstreamSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUpstreamSettings getMirrorSettings(String upstreamId) throws RestClientException {
        return getMirrorSettingsWithHttpInfo(upstreamId).getBody();
    }

    /**
     * Get upstream settings
     * Retrieves upstream settings
     * <p><b>200</b> - the mirror settings
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @return ResponseEntity&lt;RestUpstreamSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUpstreamSettings> getMirrorSettingsWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getMirrorSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<RestUpstreamSettings> localReturnType = new ParameterizedTypeReference<RestUpstreamSettings>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mirrored project IDs
     * Returns the IDs of the projects that the mirror is configured to mirror
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMirroredProjects(String upstreamId) throws RestClientException {
        getMirroredProjectsWithHttpInfo(upstreamId);
    }

    /**
     * Get mirrored project IDs
     * Returns the IDs of the projects that the mirror is configured to mirror
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMirroredProjectsWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getMirroredProjects");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/projects", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get clone URLs
     * Retrieves all available clone urls for the specified repository.
     * <p><b>200</b> - The mirrored repository&#39;s information.
     * <p><b>404</b> - The upstream server or the repository could not be found.
     * @param upstreamRepoId the repository ID (required)
     * @param upstreamId the upstream server ID (required)
     * @return RestMirroredRepository
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirroredRepository getMirroredRepository(String upstreamRepoId, String upstreamId) throws RestClientException {
        return getMirroredRepositoryWithHttpInfo(upstreamRepoId, upstreamId).getBody();
    }

    /**
     * Get clone URLs
     * Retrieves all available clone urls for the specified repository.
     * <p><b>200</b> - The mirrored repository&#39;s information.
     * <p><b>404</b> - The upstream server or the repository could not be found.
     * @param upstreamRepoId the repository ID (required)
     * @param upstreamId the upstream server ID (required)
     * @return ResponseEntity&lt;RestMirroredRepository&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirroredRepository> getMirroredRepositoryWithHttpInfo(String upstreamRepoId, String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamRepoId' is set
        if (upstreamRepoId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamRepoId' when calling getMirroredRepository");
        }
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getMirroredRepository");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamRepoId", upstreamRepoId);
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<RestMirroredRepository> localReturnType = new ParameterizedTypeReference<RestMirroredRepository>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/repos/{upstreamRepoId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get out-of-sync repositories
     * Retrieves a list of repository IDs which have not synced on the mirror node for at least the threshold time limit after the content was changed in the corresponding upstream repositories. The threshold time limit is defined by a configuration property &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.tolerance&lt;/code&gt;. The detection of out of sync repositories is dependent on the timing of a scheduled job which is controlled by a configuration property &lt;code&gt;plugin.mirroring.synchronization.interval&lt;/code&gt; which means in worst case it can take upto &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.tolerance&lt;/code&gt; + &lt;code&gt;plugin.mirroring.synchronization.interval&lt;/code&gt; time to detect an out-of-sync repository.&lt;p&gt;To use this API, a configuration property &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.enabled&lt;/code&gt; has to be set to &lt;code&gt;true&lt;/code&gt; as this feature is disabled by default.
     * <p><b>200</b> - The upstream IDs of the repositories that are out of sync on the mirror node
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>409</b> - The feature is not enabled i.e. &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.enabled&#x3D;false&lt;/code&gt;
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getOutOfSyncRepositories() throws RestClientException {
        return getOutOfSyncRepositoriesWithHttpInfo().getBody();
    }

    /**
     * Get out-of-sync repositories
     * Retrieves a list of repository IDs which have not synced on the mirror node for at least the threshold time limit after the content was changed in the corresponding upstream repositories. The threshold time limit is defined by a configuration property &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.tolerance&lt;/code&gt;. The detection of out of sync repositories is dependent on the timing of a scheduled job which is controlled by a configuration property &lt;code&gt;plugin.mirroring.synchronization.interval&lt;/code&gt; which means in worst case it can take upto &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.tolerance&lt;/code&gt; + &lt;code&gt;plugin.mirroring.synchronization.interval&lt;/code&gt; time to detect an out-of-sync repository.&lt;p&gt;To use this API, a configuration property &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.enabled&lt;/code&gt; has to be set to &lt;code&gt;true&lt;/code&gt; as this feature is disabled by default.
     * <p><b>200</b> - The upstream IDs of the repositories that are out of sync on the mirror node
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>409</b> - The feature is not enabled i.e. &lt;code&gt;plugin.mirroring.repository.diagnostics.sync.enabled&#x3D;false&lt;/code&gt;
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getOutOfSyncRepositoriesWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/out-of-sync-repos/content", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieves a list of up to &lt;code&gt;plugin.mirroring.farm.max.ref.change.queue.dump.size&lt;/code&gt; items currently in the ref changes queue. The ref changes queue is an internal component of every mirror farm, and is shared between all nodes. When the contents of an upstream repository changes, an item is added to this queue so that the mirror farm nodes know to synchronize. The mirror farm constantly polls and removes items from this queue for processing, so it is empty most of the time.
     * <p><b>200</b> - The contents of the ref changes queue
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return RestRefSyncQueue
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRefSyncQueue getRefChangesQueue() throws RestClientException {
        return getRefChangesQueueWithHttpInfo().getBody();
    }

    /**
     * 
     * Retrieves a list of up to &lt;code&gt;plugin.mirroring.farm.max.ref.change.queue.dump.size&lt;/code&gt; items currently in the ref changes queue. The ref changes queue is an internal component of every mirror farm, and is shared between all nodes. When the contents of an upstream repository changes, an item is added to this queue so that the mirror farm nodes know to synchronize. The mirror farm constantly polls and removes items from this queue for processing, so it is empty most of the time.
     * <p><b>200</b> - The contents of the ref changes queue
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return ResponseEntity&lt;RestRefSyncQueue&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRefSyncQueue> getRefChangesQueueWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<RestRefSyncQueue> localReturnType = new ParameterizedTypeReference<RestRefSyncQueue>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/refChangesQueue", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieves the total number of items currently in the ref changes queue
     * <p><b>200</b> - The total number of items currently in the ref changes queue
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getRefChangesQueueCount() throws RestClientException {
        getRefChangesQueueCountWithHttpInfo();
    }

    /**
     * 
     * Retrieves the total number of items currently in the ref changes queue
     * <p><b>200</b> - The total number of items currently in the ref changes queue
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getRefChangesQueueCountWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/refChangesQueue/count", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * Retrieves a page of sync statuses of the repositories on this mirror node
     * <p><b>200</b> - The sync status of the repositories on this node
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepoSyncStatus200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepoSyncStatus200Response getRepoSyncStatus(BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRepoSyncStatusWithHttpInfo(start, limit).getBody();
    }

    /**
     * 
     * Retrieves a page of sync statuses of the repositories on this mirror node
     * <p><b>200</b> - The sync status of the repositories on this node
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepoSyncStatus200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepoSyncStatus200Response> getRepoSyncStatusWithHttpInfo(BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetRepoSyncStatus200Response> localReturnType = new ParameterizedTypeReference<GetRepoSyncStatus200Response>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/repoSyncStatus", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets information about the mirrored repository
     * Retrieves information about an external repository mirrored by the mirror server. Particularly the local ID &amp; external ID of the repository
     * <p><b>200</b> - The sync status of the repository on this node
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return RestMirrorRepositorySynchronizationStatus
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMirrorRepositorySynchronizationStatus getRepoSyncStatus1(String projectKey, String repositorySlug) throws RestClientException {
        return getRepoSyncStatus1WithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Gets information about the mirrored repository
     * Retrieves information about an external repository mirrored by the mirror server. Particularly the local ID &amp; external ID of the repository
     * <p><b>200</b> - The sync status of the repository on this node
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestMirrorRepositorySynchronizationStatus&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMirrorRepositorySynchronizationStatus> getRepoSyncStatus1WithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepoSyncStatus1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRepoSyncStatus1");
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

        ParameterizedTypeReference<RestMirrorRepositorySynchronizationStatus> localReturnType = new ParameterizedTypeReference<RestMirrorRepositorySynchronizationStatus>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/projects/{projectKey}/repos/{repositorySlug}/repoSyncStatus", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get the repository lock owner for the syncing process
     * Retrieves the information about the process owning the sync lock for this repository. The process owning the lock could be running on any of the nodes in the mirror farm
     * <p><b>200</b> - The information about the repository lock owner for the syncing process, if the lock is currently being held, otherwise an empty response
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return RestRepositoryLockOwner
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryLockOwner getRepositoryLockOwner(String projectKey, String repositorySlug) throws RestClientException {
        return getRepositoryLockOwnerWithHttpInfo(projectKey, repositorySlug).getBody();
    }

    /**
     * Get the repository lock owner for the syncing process
     * Retrieves the information about the process owning the sync lock for this repository. The process owning the lock could be running on any of the nodes in the mirror farm
     * <p><b>200</b> - The information about the repository lock owner for the syncing process, if the lock is currently being held, otherwise an empty response
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestRepositoryLockOwner&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryLockOwner> getRepositoryLockOwnerWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRepositoryLockOwner");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRepositoryLockOwner");
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

        ParameterizedTypeReference<RestRepositoryLockOwner> localReturnType = new ParameterizedTypeReference<RestRepositoryLockOwner>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/projects/{projectKey}/repos/{repositorySlug}/repo-lock-owner", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all the repository lock owners for the syncing process
     * Retrieves the information about all the processes from the all the nodes in the mirror farm owning sync lock for any repository
     * <p><b>200</b> - A list of all the repository lock owners for the syncing process
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @return List&lt;RestRepositoryLockOwner&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RestRepositoryLockOwner> getRepositoryLockOwners() throws RestClientException {
        return getRepositoryLockOwnersWithHttpInfo().getBody();
    }

    /**
     * Get all the repository lock owners for the syncing process
     * Retrieves the information about all the processes from the all the nodes in the mirror farm owning sync lock for any repository
     * <p><b>200</b> - A list of all the repository lock owners for the syncing process
     * <p><b>401</b> - When the user doesn&#39;t have ADMIN permission
     * @return ResponseEntity&lt;List&lt;RestRepositoryLockOwner&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RestRepositoryLockOwner>> getRepositoryLockOwnersWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<List<RestRepositoryLockOwner>> localReturnType = new ParameterizedTypeReference<List<RestRepositoryLockOwner>>() {};
        return apiClient.invokeAPI("/mirroring/latest/supportInfo/repo-lock-owners", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get synchronization progress state
     *  Retrieves synchronization progress state for the specified upstream server.If there&#39;s no progress to report, this resource will return &lt;pre&gt;&lt;code&gt; {\&quot;discovering\&quot;:false,\&quot;syncedRepos\&quot;:0,\&quot;totalRepos\&quot;:0}&lt;/code&gt;&lt;/pre&gt; If there are repositories in the process of synchronizing, but the precise number hasn&#39;t been discovered yet, this resource will return: &lt;pre&gt;&lt;code&gt; {\&quot;discovering\&quot;:true,\&quot;syncedRepos\&quot;:3,\&quot;totalRepos\&quot;:100}&lt;/code&gt;&lt;/pre&gt; If there is progress to report and the total number of repositories is known, this resource will return: &lt;pre&gt; &lt;code&gt; {\&quot;discovering\&quot;:false,\&quot;syncedRepos\&quot;:242,\&quot;totalRepos\&quot;:1071}&lt;/code&gt; &lt;/pre&gt;
     * <p><b>200</b> - the synchronization progress state
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return RestSyncProgress
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSyncProgress getSynchronizationProgress(String upstreamId) throws RestClientException {
        return getSynchronizationProgressWithHttpInfo(upstreamId).getBody();
    }

    /**
     * Get synchronization progress state
     *  Retrieves synchronization progress state for the specified upstream server.If there&#39;s no progress to report, this resource will return &lt;pre&gt;&lt;code&gt; {\&quot;discovering\&quot;:false,\&quot;syncedRepos\&quot;:0,\&quot;totalRepos\&quot;:0}&lt;/code&gt;&lt;/pre&gt; If there are repositories in the process of synchronizing, but the precise number hasn&#39;t been discovered yet, this resource will return: &lt;pre&gt;&lt;code&gt; {\&quot;discovering\&quot;:true,\&quot;syncedRepos\&quot;:3,\&quot;totalRepos\&quot;:100}&lt;/code&gt;&lt;/pre&gt; If there is progress to report and the total number of repositories is known, this resource will return: &lt;pre&gt; &lt;code&gt; {\&quot;discovering\&quot;:false,\&quot;syncedRepos\&quot;:242,\&quot;totalRepos\&quot;:1071}&lt;/code&gt; &lt;/pre&gt;
     * <p><b>200</b> - the synchronization progress state
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId the upstream server ID to retrieve settings for (required)
     * @return ResponseEntity&lt;RestSyncProgress&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSyncProgress> getSynchronizationProgressWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getSynchronizationProgress");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<RestSyncProgress> localReturnType = new ParameterizedTypeReference<RestSyncProgress>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/progress", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get upstream server by ID
     * Retrieves upstream server details by ID.
     * <p><b>200</b> - The upstream server.
     * <p><b>404</b> - The upstream server could not be found
     * @param upstreamId The upstream server ID to retrieve settings for. (required)
     * @return RestUpstreamServer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUpstreamServer getUpstreamServer(String upstreamId) throws RestClientException {
        return getUpstreamServerWithHttpInfo(upstreamId).getBody();
    }

    /**
     * Get upstream server by ID
     * Retrieves upstream server details by ID.
     * <p><b>200</b> - The upstream server.
     * <p><b>404</b> - The upstream server could not be found
     * @param upstreamId The upstream server ID to retrieve settings for. (required)
     * @return ResponseEntity&lt;RestUpstreamServer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUpstreamServer> getUpstreamServerWithHttpInfo(String upstreamId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling getUpstreamServer");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<RestUpstreamServer> localReturnType = new ParameterizedTypeReference<RestUpstreamServer>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get upstream servers
     * Retrieves a page of upstream servers
     * <p><b>200</b> - A page of upstream servers
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ListUpstreamServers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListUpstreamServers200Response listUpstreamServers(BigDecimal start, BigDecimal limit) throws RestClientException {
        return listUpstreamServersWithHttpInfo(start, limit).getBody();
    }

    /**
     * Get upstream servers
     * Retrieves a page of upstream servers
     * <p><b>200</b> - A page of upstream servers
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;ListUpstreamServers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListUpstreamServers200Response> listUpstreamServersWithHttpInfo(BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<ListUpstreamServers200Response> localReturnType = new ParameterizedTypeReference<ListUpstreamServers200Response>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * Update mirror mode
     * Sets the mirror mode for the specified upstream
     * <p><b>200</b> - the mode to set
     * <p><b>400</b> - The provided mode is invalid
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * @param upstreamId  (required)
     * @param body  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setMirrorMode(String upstreamId, String body) throws RestClientException {
        setMirrorModeWithHttpInfo(upstreamId, body);
    }

    /**
     * Update mirror mode
     * Sets the mirror mode for the specified upstream
     * <p><b>200</b> - the mode to set
     * <p><b>400</b> - The provided mode is invalid
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * @param upstreamId  (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setMirrorModeWithHttpInfo(String upstreamId, String body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling setMirrorMode");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/mode", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update upstream settings
     * Sets the settings for the specified upstream
     * <p><b>200</b> - the updated mirror settings
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param restUpstreamSettings the mirror settings to update to (optional)
     * @return RestUpstreamSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUpstreamSettings setMirrorSettings(String upstreamId, RestUpstreamSettings restUpstreamSettings) throws RestClientException {
        return setMirrorSettingsWithHttpInfo(upstreamId, restUpstreamSettings).getBody();
    }

    /**
     * Update upstream settings
     * Sets the settings for the specified upstream
     * <p><b>200</b> - the updated mirror settings
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param restUpstreamSettings the mirror settings to update to (optional)
     * @return ResponseEntity&lt;RestUpstreamSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUpstreamSettings> setMirrorSettingsWithHttpInfo(String upstreamId, RestUpstreamSettings restUpstreamSettings) throws RestClientException {
        Object localVarPostBody = restUpstreamSettings;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling setMirrorSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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

        ParameterizedTypeReference<RestUpstreamSettings> localReturnType = new ParameterizedTypeReference<RestUpstreamSettings>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add project to be mirrored
     * Configures the mirror to mirror the provided project
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param projectId  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void startMirroringProject(String upstreamId, String projectId) throws RestClientException {
        startMirroringProjectWithHttpInfo(upstreamId, projectId);
    }

    /**
     * Add project to be mirrored
     * Configures the mirror to mirror the provided project
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param projectId  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> startMirroringProjectWithHttpInfo(String upstreamId, String projectId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling startMirroringProject");
        }
        
        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectId' when calling startMirroringProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/projects/{projectId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add multiple projects to be mirrored
     * Configures the mirror to mirror the provided projects
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param requestBody  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void startMirroringProjects(String upstreamId, List<String> requestBody) throws RestClientException {
        startMirroringProjectsWithHttpInfo(upstreamId, requestBody);
    }

    /**
     * Add multiple projects to be mirrored
     * Configures the mirror to mirror the provided projects
     * <p><b>200</b> - the currently mirrored project IDs
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param requestBody  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> startMirroringProjectsWithHttpInfo(String upstreamId, List<String> requestBody) throws RestClientException {
        Object localVarPostBody = requestBody;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling startMirroringProjects");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/projects", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stop mirroring project
     * Configures the mirror to no longer mirror the provided project
     * <p><b>204</b> - the request has been processed
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param projectId the project ID to stop mirroring (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void stopMirroringProject(String upstreamId, String projectId) throws RestClientException {
        stopMirroringProjectWithHttpInfo(upstreamId, projectId);
    }

    /**
     * Stop mirroring project
     * Configures the mirror to no longer mirror the provided project
     * <p><b>204</b> - the request has been processed
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param projectId the project ID to stop mirroring (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> stopMirroringProjectWithHttpInfo(String upstreamId, String projectId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling stopMirroringProject");
        }
        
        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectId' when calling stopMirroringProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/projects/{projectId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Stop mirroring projects
     * Configures the mirror to no longer mirror the provided projects
     * <p><b>204</b> - the request has been processed
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param requestBody  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void stopMirroringProjects(String upstreamId, List<String> requestBody) throws RestClientException {
        stopMirroringProjectsWithHttpInfo(upstreamId, requestBody);
    }

    /**
     * Stop mirroring projects
     * Configures the mirror to no longer mirror the provided projects
     * <p><b>204</b> - the request has been processed
     * <p><b>401</b> - When the user is not a service user for the currently registered upstream or doesn&#39;t have ADMIN permission
     * <p><b>404</b> - The upstream server could not be found.
     * @param upstreamId  (required)
     * @param requestBody  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> stopMirroringProjectsWithHttpInfo(String upstreamId, List<String> requestBody) throws RestClientException {
        Object localVarPostBody = requestBody;
        
        // verify the required parameter 'upstreamId' is set
        if (upstreamId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'upstreamId' when calling stopMirroringProjects");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("upstreamId", upstreamId);

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
        return apiClient.invokeAPI("/mirroring/latest/upstreamServers/{upstreamId}/settings/projects", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
