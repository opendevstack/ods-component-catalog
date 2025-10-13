package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExamplePreviewMigration;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExamplePutMultipartFormData;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.ExampleSettingsMap;
import java.io.File;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAllMeshMigrationSummaries200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetExportJobMessages200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetHistory200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLabelables200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLabels200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRateLimitSettingsForUser200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAnnouncementBanner;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestApplicationProperties;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestApplicationUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBitbucketLicense;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBulkUserRateLimitSettingsUpdateRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestClusterInformation;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestErrors;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestExportRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestHookScript;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestImportRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestJob;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestLabel;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestLogLevel;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMailConfiguration;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMeshConnectivityReport;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMeshMigrationRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMeshMigrationSummary;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMeshNode;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestNamedLink;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRateLimitSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositoryPolicy;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestScopesExample;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSshKeySettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUserRateLimitSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestUserRateLimitSettingsUpdateRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.SearchMeshMigrationRepos200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.SetBannerRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.SetDefaultBranchRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.SetMailConfigRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.StartMeshMigrationRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserPasswordUpdate;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserUpdate;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.SystemMaintenanceApi")
public class SystemMaintenanceApi extends BaseApi {

    public SystemMaintenanceApi() {
        super(new ApiClient());
    }

    @Autowired
    public SystemMaintenanceApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Cancel export job
     * Requests the cancellation of an export job.  The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.  There might be a small delay between accepting the request and actually cancelling the job. In most cases, the delay will be close to instantaneously. In the unlikely case of communication issues across a cluster, it can however take a few seconds to cancel a job.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>204</b> - The job has successfully been marked for cancellation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to cancel this job.
     * <p><b>404</b> - The specified job does not exist.
     * <p><b>409</b> - The job was in a state that does not allow cancellation, e.g. it has already finished.
     * @param jobId the ID of the job to cancel (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void cancelExportJob(String jobId) throws RestClientException {
        cancelExportJobWithHttpInfo(jobId);
    }

    /**
     * Cancel export job
     * Requests the cancellation of an export job.  The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.  There might be a small delay between accepting the request and actually cancelling the job. In most cases, the delay will be close to instantaneously. In the unlikely case of communication issues across a cluster, it can however take a few seconds to cancel a job.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>204</b> - The job has successfully been marked for cancellation
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to cancel this job.
     * <p><b>404</b> - The specified job does not exist.
     * <p><b>409</b> - The job was in a state that does not allow cancellation, e.g. it has already finished.
     * @param jobId the ID of the job to cancel (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> cancelExportJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling cancelExportJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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
        return apiClient.invokeAPI("/api/latest/migration/exports/{jobId}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancel import job
     * Requests the cancellation of an import job.  The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.  Note that import jobs are not canceled as instantaneously as export jobs. Rather, once the request has been accepted, there are a number of checkpoints at which the job will actually apply it and stop. This is to keep the system in a reasonably consistent state:  - After the current fork hierarchy has been imported and verified. - Before the next repository is imported. - Before the next pull request is imported.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>204</b> - The job has successfully been marked for cancellation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to cancel this job.
     * <p><b>404</b> - The specified job does not exist.
     * <p><b>409</b> - The job was in a state that does not allow cancellation, e.g. it has already finished.
     * @param jobId the ID of the job to cancel (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void cancelImportJob(String jobId) throws RestClientException {
        cancelImportJobWithHttpInfo(jobId);
    }

    /**
     * Cancel import job
     * Requests the cancellation of an import job.  The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.  Note that import jobs are not canceled as instantaneously as export jobs. Rather, once the request has been accepted, there are a number of checkpoints at which the job will actually apply it and stop. This is to keep the system in a reasonably consistent state:  - After the current fork hierarchy has been imported and verified. - Before the next repository is imported. - Before the next pull request is imported.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>204</b> - The job has successfully been marked for cancellation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to cancel this job.
     * <p><b>404</b> - The specified job does not exist.
     * <p><b>409</b> - The job was in a state that does not allow cancellation, e.g. it has already finished.
     * @param jobId the ID of the job to cancel (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> cancelImportJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling cancelImportJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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
        return apiClient.invokeAPI("/api/latest/migration/imports/{jobId}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancel Mesh migration job
     * Requests the cancellation of a migration job.   The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.   There might be a small delay between accepting the request and actually cancelling the job. In most cases, the delay will be close to instantaneously. In the unlikely case of communication issues across a cluster, it can however take a few seconds to cancel a job.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>204</b> - The migration job was successfully marked for cancellation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * <p><b>409</b> - The migration job has already been canceled or finished.
     * @param jobId The ID of the job to cancel (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void cancelMeshMigrationJob(String jobId) throws RestClientException {
        cancelMeshMigrationJobWithHttpInfo(jobId);
    }

    /**
     * Cancel Mesh migration job
     * Requests the cancellation of a migration job.   The request to cancel a job will be processed successfully if the job is actually still running. If it has already finished (successfully or with errors) or if it has already been canceled before, then an error will be returned.   There might be a small delay between accepting the request and actually cancelling the job. In most cases, the delay will be close to instantaneously. In the unlikely case of communication issues across a cluster, it can however take a few seconds to cancel a job.  A client should always actively query the job status to confirm that a job has been successfully canceled.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>204</b> - The migration job was successfully marked for cancellation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * <p><b>409</b> - The migration job has already been canceled or finished.
     * @param jobId The ID of the job to cancel (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> cancelMeshMigrationJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling cancelMeshMigrationJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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
        return apiClient.invokeAPI("/api/latest/migration/mesh/{jobId}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clear default branch
     * Clears the global default branch, which is used when creating new repositories if an explicit default branch is not specified, if one has been configured.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The default branch has been cleared.
     * <p><b>401</b> - The current user does not have sufficient permissions to clear the global default branch.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void clearDefaultBranch() throws RestClientException {
        clearDefaultBranchWithHttpInfo();
    }

    /**
     * Clear default branch
     * Clears the global default branch, which is used when creating new repositories if an explicit default branch is not specified, if one has been configured.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The default branch has been cleared.
     * <p><b>401</b> - The current user does not have sufficient permissions to clear the global default branch.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> clearDefaultBranchWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/default-branch", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update mail configuration
     * Clears the server email address.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - he server email address was successfully cleared.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions toclear the server email address.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void clearSenderAddress() throws RestClientException {
        clearSenderAddressWithHttpInfo();
    }

    /**
     * Update mail configuration
     * Clears the server email address.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - he server email address was successfully cleared.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions toclear the server email address.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> clearSenderAddressWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/mail-server/sender-address", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Generate Mesh connectivity report
     * Generates a connectivity report between the Bitbucket node(s) and the Mesh node(s).  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The connectivity report between the Bitbucket node(s) and Mesh node(s).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return RestMeshConnectivityReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshConnectivityReport connectivity() throws RestClientException {
        return connectivityWithHttpInfo().getBody();
    }

    /**
     * Generate Mesh connectivity report
     * Generates a connectivity report between the Bitbucket node(s) and the Mesh node(s).  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The connectivity report between the Bitbucket node(s) and Mesh node(s).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return ResponseEntity&lt;RestMeshConnectivityReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshConnectivityReport> connectivityWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestMeshConnectivityReport> localReturnType = new ParameterizedTypeReference<RestMeshConnectivityReport>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/diagnostics/connectivity", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a new hook script
     * Create a new hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The newly created hook script.
     * <p><b>400</b> - The hook script was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * @param content The hook script contents. (optional)
     * @param description A description of the hook script (useful when querying registered hook scripts). (optional)
     * @param name The name of the hook script (useful when querying registered hook scripts). (optional)
     * @param type The type of hook script; supported values are \\\&quot;PRE\\\&quot; for pre-receive hooks and \\\&quot;POST\\\&quot; for post-receive hooks. (optional)
     * @return RestHookScript
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestHookScript createNewHookScript(String content, String description, String name, String type) throws RestClientException {
        return createNewHookScriptWithHttpInfo(content, description, name, type).getBody();
    }

    /**
     * Create a new hook script
     * Create a new hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The newly created hook script.
     * <p><b>400</b> - The hook script was not created due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * @param content The hook script contents. (optional)
     * @param description A description of the hook script (useful when querying registered hook scripts). (optional)
     * @param name The name of the hook script (useful when querying registered hook scripts). (optional)
     * @param type The type of hook script; supported values are \\\&quot;PRE\\\&quot; for pre-receive hooks and \\\&quot;POST\\\&quot; for post-receive hooks. (optional)
     * @return ResponseEntity&lt;RestHookScript&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestHookScript> createNewHookScriptWithHttpInfo(String content, String description, String name, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (content != null)
            localVarFormParams.add("content", content);
        if (description != null)
            localVarFormParams.add("description", description);
        if (name != null)
            localVarFormParams.add("name", name);
        if (type != null)
            localVarFormParams.add("type", type);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestHookScript> localReturnType = new ParameterizedTypeReference<RestHookScript>() {};
        return apiClient.invokeAPI("/api/latest/hook-scripts", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a hook script.
     * Deletes a registered hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>204</b> - The hook script was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - Unable to find the supplied hook script ID.
     * @param scriptId The ID of the hook script to delete (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete4(String scriptId) throws RestClientException {
        delete4WithHttpInfo(scriptId);
    }

    /**
     * Delete a hook script.
     * Deletes a registered hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>204</b> - The hook script was deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - Unable to find the supplied hook script ID.
     * @param scriptId The ID of the hook script to delete (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete4WithHttpInfo(String scriptId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling delete4");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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
        return apiClient.invokeAPI("/api/latest/hook-scripts/{scriptId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param id  (required)
     * @param force  (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete5(Long id, Boolean force) throws RestClientException {
        delete5WithHttpInfo(id, force);
    }

    /**
     * 
     * 
     * <p><b>0</b> - default response
     * @param id  (required)
     * @param force  (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete5WithHttpInfo(Long id, Boolean force) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling delete5");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "force", force));
        

        final String[] localVarAccepts = { 
            "application/json;charset=UTF-8"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/nodes/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete user specific rate limit settings
     * Deletes the user-specific rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - An empty response indicating that the user settings have been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * <p><b>404</b> - The specified user does not exist, or has no settings.
     * @param userSlug The user slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete9(String userSlug) throws RestClientException {
        delete9WithHttpInfo(userSlug);
    }

    /**
     * Delete user specific rate limit settings
     * Deletes the user-specific rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - An empty response indicating that the user settings have been deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * <p><b>404</b> - The specified user does not exist, or has no settings.
     * @param userSlug The user slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delete9WithHttpInfo(String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling delete9");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings/users/{userSlug}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete user avatar
     * Delete the avatar associated to a user.   Users are always allowed to delete their own avatar. To delete someone else&#39;s avatar the authenticated user must have global &lt;strong&gt;ADMIN&lt;/strong&gt; permission, or global &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to update a &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; user&#39;s avatar.
     * <p><b>200</b> - The new avatar URL if the local avatar was successfully deleted or did not exist
     * <p><b>401</b> - The authenticated user has insufficient permissions to delete the specified avatar.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @return RestNamedLink
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestNamedLink deleteAvatar(String userSlug) throws RestClientException {
        return deleteAvatarWithHttpInfo(userSlug).getBody();
    }

    /**
     * Delete user avatar
     * Delete the avatar associated to a user.   Users are always allowed to delete their own avatar. To delete someone else&#39;s avatar the authenticated user must have global &lt;strong&gt;ADMIN&lt;/strong&gt; permission, or global &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to update a &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; user&#39;s avatar.
     * <p><b>200</b> - The new avatar URL if the local avatar was successfully deleted or did not exist
     * <p><b>401</b> - The authenticated user has insufficient permissions to delete the specified avatar.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @return ResponseEntity&lt;RestNamedLink&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestNamedLink> deleteAvatarWithHttpInfo(String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling deleteAvatar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<RestNamedLink> localReturnType = new ParameterizedTypeReference<RestNamedLink>() {};
        return apiClient.invokeAPI("/api/latest/users/{userSlug}/avatar.png", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete announcement banner
     * Deletes a banner, if one is present in the database.
     * <p><b>204</b> - The query executed successfully, whether a banner was deleted or not
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteBanner() throws RestClientException {
        deleteBannerWithHttpInfo();
    }

    /**
     * Delete announcement banner
     * Deletes a banner, if one is present in the database.
     * <p><b>204</b> - The query executed successfully, whether a banner was deleted or not
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteBannerWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/banner", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete mail configuration
     * Deletes the current mail configuration.  The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The mail configuration was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the mail server configuration.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteMailConfig() throws RestClientException {
        deleteMailConfigWithHttpInfo();
    }

    /**
     * Delete mail configuration
     * Deletes the current mail configuration.  The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The mail configuration was successfully deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the mail server configuration.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteMailConfigWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/mail-server", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Dismiss retention config notification
     * Dismisses the retention config review notification displayed by the audit plugin for the user that&#39;s currently logged in.
     * <p><b>200</b> - A blank response
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to dismiss the notification.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void dismissRetentionConfigReviewNotification() throws RestClientException {
        dismissRetentionConfigReviewNotificationWithHttpInfo();
    }

    /**
     * Dismiss retention config notification
     * Dismisses the retention config review notification displayed by the audit plugin for the user that&#39;s currently logged in.
     * <p><b>200</b> - A blank response
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to dismiss the notification.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> dismissRetentionConfigReviewNotificationWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/audit/latest/notification-settings/retention-config-review", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get license details
     * Retrieves details about the current license, as well as the current status of the system with regards to the installed license. The status includes the current number of users applied toward the license limit, as well as any status messages about the license (warnings about expiry or user counts exceeding license limits).   The authenticated user must have &lt;b&gt;ADMIN&lt;/b&gt; permission. Unauthenticated users, and non-administrators, are not permitted to access license details.
     * <p><b>200</b> - The currently-installed license.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the license, or the request is anonymous.
     * <p><b>404</b> - No license has been installed.
     * @return RestBitbucketLicense
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBitbucketLicense get4() throws RestClientException {
        return get4WithHttpInfo().getBody();
    }

    /**
     * Get license details
     * Retrieves details about the current license, as well as the current status of the system with regards to the installed license. The status includes the current number of users applied toward the license limit, as well as any status messages about the license (warnings about expiry or user counts exceeding license limits).   The authenticated user must have &lt;b&gt;ADMIN&lt;/b&gt; permission. Unauthenticated users, and non-administrators, are not permitted to access license details.
     * <p><b>200</b> - The currently-installed license.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the license, or the request is anonymous.
     * <p><b>404</b> - No license has been installed.
     * @return ResponseEntity&lt;RestBitbucketLicense&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBitbucketLicense> get4WithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestBitbucketLicense> localReturnType = new ParameterizedTypeReference<RestBitbucketLicense>() {};
        return apiClient.invokeAPI("/api/latest/admin/license", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get user specific rate limit settings
     * Retrieves the user-specific rate limit settings for the given user.  To call this resource, the user must be authenticated and either have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or be the same user as the one whose settings are requested. A user with &lt;strong&gt;ADMIN&lt;/strong&gt; permission cannot get the settings of a user with &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission.
     * <p><b>200</b> - A response containing the user-specific rate limit settings for the given user.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * <p><b>404</b> - The specified user does not exist, or has no settings.
     * @param userSlug The user slug. (required)
     * @return RestUserRateLimitSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUserRateLimitSettings get6(String userSlug) throws RestClientException {
        return get6WithHttpInfo(userSlug).getBody();
    }

    /**
     * Get user specific rate limit settings
     * Retrieves the user-specific rate limit settings for the given user.  To call this resource, the user must be authenticated and either have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or be the same user as the one whose settings are requested. A user with &lt;strong&gt;ADMIN&lt;/strong&gt; permission cannot get the settings of a user with &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission.
     * <p><b>200</b> - A response containing the user-specific rate limit settings for the given user.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * <p><b>404</b> - The specified user does not exist, or has no settings.
     * @param userSlug The user slug. (required)
     * @return ResponseEntity&lt;RestUserRateLimitSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUserRateLimitSettings> get6WithHttpInfo(String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling get6");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<RestUserRateLimitSettings> localReturnType = new ParameterizedTypeReference<RestUserRateLimitSettings>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings/users/{userSlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get summary for Mesh migration job
     * Gets the summary, including the queue status and progress, of the currently active Mesh migration job.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the currently active migration job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - No active migration job found.
     * @return RestMeshMigrationSummary
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshMigrationSummary getActiveMeshMigrationSummary() throws RestClientException {
        return getActiveMeshMigrationSummaryWithHttpInfo().getBody();
    }

    /**
     * Get summary for Mesh migration job
     * Gets the summary, including the queue status and progress, of the currently active Mesh migration job.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the currently active migration job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - No active migration job found.
     * @return ResponseEntity&lt;RestMeshMigrationSummary&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshMigrationSummary> getActiveMeshMigrationSummaryWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestMeshMigrationSummary> localReturnType = new ParameterizedTypeReference<RestMeshMigrationSummary>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/summary", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all Mesh migration job summaries
     * Retrieve a page of Mesh migration job summaries. Jobs are ordered by when they were started, newest first.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the migration job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetAllMeshMigrationSummaries200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetAllMeshMigrationSummaries200Response getAllMeshMigrationSummaries(BigDecimal start, BigDecimal limit) throws RestClientException {
        return getAllMeshMigrationSummariesWithHttpInfo(start, limit).getBody();
    }

    /**
     * Get all Mesh migration job summaries
     * Retrieve a page of Mesh migration job summaries. Jobs are ordered by when they were started, newest first.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the migration job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetAllMeshMigrationSummaries200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetAllMeshMigrationSummaries200Response> getAllMeshMigrationSummariesWithHttpInfo(BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetAllMeshMigrationSummaries200Response> localReturnType = new ParameterizedTypeReference<GetAllMeshMigrationSummaries200Response>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/summaries", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all registered Mesh nodes
     * Get all the registered Mesh nodes.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The list of registered Mesh nodes.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return RestMeshNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshNode getAllRegisteredMeshNodes() throws RestClientException {
        return getAllRegisteredMeshNodesWithHttpInfo().getBody();
    }

    /**
     * Get all registered Mesh nodes
     * Get all the registered Mesh nodes.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The list of registered Mesh nodes.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return ResponseEntity&lt;RestMeshNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshNode> getAllRegisteredMeshNodesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestMeshNode> localReturnType = new ParameterizedTypeReference<RestMeshNode>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/nodes", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get application properties
     * Retrieve version information and other application properties.  No authentication is required to call this resource.
     * <p><b>200</b> - The application properties
     * @return RestApplicationProperties
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestApplicationProperties getApplicationProperties() throws RestClientException {
        return getApplicationPropertiesWithHttpInfo().getBody();
    }

    /**
     * Get application properties
     * Retrieve version information and other application properties.  No authentication is required to call this resource.
     * <p><b>200</b> - The application properties
     * @return ResponseEntity&lt;RestApplicationProperties&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestApplicationProperties> getApplicationPropertiesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestApplicationProperties> localReturnType = new ParameterizedTypeReference<RestApplicationProperties>() {};
        return apiClient.invokeAPI("/api/latest/application-properties", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get announcement banner
     * Gets the announcement banner, if one exists and is available to the user
     * <p><b>200</b> - The requested banner
     * <p><b>204</b> - There is no banner to display
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @return RestAnnouncementBanner
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAnnouncementBanner getBanner() throws RestClientException {
        return getBannerWithHttpInfo().getBody();
    }

    /**
     * Get announcement banner
     * Gets the announcement banner, if one exists and is available to the user
     * <p><b>200</b> - The requested banner
     * <p><b>204</b> - There is no banner to display
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @return ResponseEntity&lt;RestAnnouncementBanner&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAnnouncementBanner> getBannerWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestAnnouncementBanner> localReturnType = new ParameterizedTypeReference<RestAnnouncementBanner>() {};
        return apiClient.invokeAPI("/api/latest/admin/banner", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get the control plane PEM
     * Obtain the control plane PEM.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The control plane PEM.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getControlPlanePublicKey() throws RestClientException {
        getControlPlanePublicKeyWithHttpInfo();
    }

    /**
     * Get the control plane PEM
     * Obtain the control plane PEM.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The control plane PEM.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getControlPlanePublicKeyWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/config/control-plane.pem", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get the default branch
     * Retrieves the configured global default branch, which is used when creating new repositories if an explicit default branch is not specified.  The user must be authenticated to call this resource.
     * <p><b>200</b> - The configured global default branch.
     * <p><b>404</b> - No global default branch has been configured.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getDefaultBranch() throws RestClientException {
        getDefaultBranchWithHttpInfo();
    }

    /**
     * Get the default branch
     * Retrieves the configured global default branch, which is used when creating new repositories if an explicit default branch is not specified.  The user must be authenticated to call this resource.
     * <p><b>200</b> - The configured global default branch.
     * <p><b>404</b> - No global default branch has been configured.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getDefaultBranchWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/default-branch", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get export job details
     * Gets the details, including the current status and progress, of the export job identified by the given ID.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The job, including status and progress information.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId the ID of the job (required)
     * @return RestJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestJob getExportJob(String jobId) throws RestClientException {
        return getExportJobWithHttpInfo(jobId).getBody();
    }

    /**
     * Get export job details
     * Gets the details, including the current status and progress, of the export job identified by the given ID.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The job, including status and progress information.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId the ID of the job (required)
     * @return ResponseEntity&lt;RestJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestJob> getExportJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getExportJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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

        ParameterizedTypeReference<RestJob> localReturnType = new ParameterizedTypeReference<RestJob>() {};
        return apiClient.invokeAPI("/api/latest/migration/exports/{jobId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get job messages
     * Gets the messages generated by the job.  Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.  - INFO - WARN - ERROR  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The messages generated by this job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetExportJobMessages200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetExportJobMessages200Response getExportJobMessages(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getExportJobMessagesWithHttpInfo(jobId, severity, subject, start, limit).getBody();
    }

    /**
     * Get job messages
     * Gets the messages generated by the job.  Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.  - INFO - WARN - ERROR  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The messages generated by this job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetExportJobMessages200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetExportJobMessages200Response> getExportJobMessagesWithHttpInfo(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getExportJobMessages");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "severity", severity));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "subject", subject));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetExportJobMessages200Response> localReturnType = new ParameterizedTypeReference<GetExportJobMessages200Response>() {};
        return apiClient.invokeAPI("/api/latest/migration/exports/{jobId}/messages", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get global SSH key settings
     * Gets the global settings that enforce the maximum expiry of SSH keys and restrictions on SSH key types.
     * <p><b>200</b> - The global ssh key settings configuration.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the ssh keys global settings configuration.
     * @return RestSshKeySettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshKeySettings getGlobalSettings() throws RestClientException {
        return getGlobalSettingsWithHttpInfo().getBody();
    }

    /**
     * Get global SSH key settings
     * Gets the global settings that enforce the maximum expiry of SSH keys and restrictions on SSH key types.
     * <p><b>200</b> - The global ssh key settings configuration.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the ssh keys global settings configuration.
     * @return ResponseEntity&lt;RestSshKeySettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshKeySettings> getGlobalSettingsWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestSshKeySettings> localReturnType = new ParameterizedTypeReference<RestSshKeySettings>() {};
        return apiClient.invokeAPI("/admin", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get rate limit history
     * Retrieves the recent rate limit history for the instance.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing a page of aggregated counters for users who have been recently rate limited.
     * <p><b>400</b> - The sort query parameter is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit history.
     * @param order An optional sort category to arrange the results in descending order (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetHistory200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetHistory200Response getHistory(String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getHistoryWithHttpInfo(order, start, limit).getBody();
    }

    /**
     * Get rate limit history
     * Retrieves the recent rate limit history for the instance.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing a page of aggregated counters for users who have been recently rate limited.
     * <p><b>400</b> - The sort query parameter is invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit history.
     * @param order An optional sort category to arrange the results in descending order (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetHistory200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetHistory200Response> getHistoryWithHttpInfo(String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

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

        ParameterizedTypeReference<GetHistory200Response> localReturnType = new ParameterizedTypeReference<GetHistory200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/history", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a hook script
     * Retrieves a hook script by ID.
     * <p><b>200</b> - The hook script.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * @param scriptId The ID of the hook script to retrieve (required)
     * @return RestHookScript
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestHookScript getHookScript(String scriptId) throws RestClientException {
        return getHookScriptWithHttpInfo(scriptId).getBody();
    }

    /**
     * Get a hook script
     * Retrieves a hook script by ID.
     * <p><b>200</b> - The hook script.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * @param scriptId The ID of the hook script to retrieve (required)
     * @return ResponseEntity&lt;RestHookScript&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestHookScript> getHookScriptWithHttpInfo(String scriptId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling getHookScript");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RestHookScript> localReturnType = new ParameterizedTypeReference<RestHookScript>() {};
        return apiClient.invokeAPI("/api/latest/hook-scripts/{scriptId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get import job status
     * Gets the details, including the current status and progress, of the import job identified by the given ID.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The job, including status and progress information.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @return RestJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestJob getImportJob(String jobId) throws RestClientException {
        return getImportJobWithHttpInfo(jobId).getBody();
    }

    /**
     * Get import job status
     * Gets the details, including the current status and progress, of the import job identified by the given ID.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The job, including status and progress information.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @return ResponseEntity&lt;RestJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestJob> getImportJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getImportJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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

        ParameterizedTypeReference<RestJob> localReturnType = new ParameterizedTypeReference<RestJob>() {};
        return apiClient.invokeAPI("/api/latest/migration/imports/{jobId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get import job messages
     * Gets the messages generated by the job.  Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.  - INFO - WARN - ERROR  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The messages generated by this job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetExportJobMessages200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetExportJobMessages200Response getImportJobMessages(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getImportJobMessagesWithHttpInfo(jobId, severity, subject, start, limit).getBody();
    }

    /**
     * Get import job messages
     * Gets the messages generated by the job.  Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.  - INFO - WARN - ERROR  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The messages generated by this job.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve information about this job.
     * <p><b>404</b> - The specified job does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetExportJobMessages200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetExportJobMessages200Response> getImportJobMessagesWithHttpInfo(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getImportJobMessages");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "severity", severity));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "subject", subject));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetExportJobMessages200Response> localReturnType = new ParameterizedTypeReference<GetExportJobMessages200Response>() {};
        return apiClient.invokeAPI("/api/latest/migration/imports/{jobId}/messages", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get cluster node information
     * Gets information about the nodes that currently make up the stash cluster.  The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing information about the cluster
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the cluster information.
     * @return RestClusterInformation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestClusterInformation getInformation() throws RestClientException {
        return getInformationWithHttpInfo().getBody();
    }

    /**
     * Get cluster node information
     * Gets information about the nodes that currently make up the stash cluster.  The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing information about the cluster
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the cluster information.
     * @return ResponseEntity&lt;RestClusterInformation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestClusterInformation> getInformationWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestClusterInformation> localReturnType = new ParameterizedTypeReference<RestClusterInformation>() {};
        return apiClient.invokeAPI("/api/latest/admin/cluster", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get label
     * Returns a label.  The user needs to be authenticated to use this resource.
     * <p><b>200</b> - The label.
     * <p><b>401</b> - The user is currently not authenticated.
     * <p><b>404</b> - The specified label does not exist.
     * @param labelName the label name (required)
     * @return RestLabel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestLabel getLabel(String labelName) throws RestClientException {
        return getLabelWithHttpInfo(labelName).getBody();
    }

    /**
     * Get label
     * Returns a label.  The user needs to be authenticated to use this resource.
     * <p><b>200</b> - The label.
     * <p><b>401</b> - The user is currently not authenticated.
     * <p><b>404</b> - The specified label does not exist.
     * @param labelName the label name (required)
     * @return ResponseEntity&lt;RestLabel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestLabel> getLabelWithHttpInfo(String labelName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'labelName' is set
        if (labelName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'labelName' when calling getLabel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("labelName", labelName);

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
        return apiClient.invokeAPI("/api/latest/labels/{labelName}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get labelables for label
     * Returns a page of labelables for a given label.  Only labelables that the authenticated user has view access to will be returned.
     * <p><b>200</b> - The page of labelables.
     * <p><b>400</b> - The type of labelable is incorrect, correct values are REPOSITORY.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the labelables
     * <p><b>404</b> - The specified label does not exist.
     * @param labelName The page of labelables. (required)
     * @param type  the type of labelables to be returned. Supported values: REPOSITORY (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLabelables200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLabelables200Response getLabelables(String labelName, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getLabelablesWithHttpInfo(labelName, type, start, limit).getBody();
    }

    /**
     * Get labelables for label
     * Returns a page of labelables for a given label.  Only labelables that the authenticated user has view access to will be returned.
     * <p><b>200</b> - The page of labelables.
     * <p><b>400</b> - The type of labelable is incorrect, correct values are REPOSITORY.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the labelables
     * <p><b>404</b> - The specified label does not exist.
     * @param labelName The page of labelables. (required)
     * @param type  the type of labelables to be returned. Supported values: REPOSITORY (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLabelables200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLabelables200Response> getLabelablesWithHttpInfo(String labelName, String type, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'labelName' is set
        if (labelName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'labelName' when calling getLabelables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("labelName", labelName);

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

        ParameterizedTypeReference<GetLabelables200Response> localReturnType = new ParameterizedTypeReference<GetLabelables200Response>() {};
        return apiClient.invokeAPI("/api/latest/labels/{labelName}/labeled", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all labels
     * Returns a paged response of all the labels in the system.  The user needs to be authenticated to use this resource.
     * <p><b>200</b> - Page of returned labels.
     * <p><b>401</b> - The user is currently not authenticated.
     * @param prefix (optional) prefix to filter the labels on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLabels200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLabels200Response getLabels(String prefix, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getLabelsWithHttpInfo(prefix, start, limit).getBody();
    }

    /**
     * Get all labels
     * Returns a paged response of all the labels in the system.  The user needs to be authenticated to use this resource.
     * <p><b>200</b> - Page of returned labels.
     * <p><b>401</b> - The user is currently not authenticated.
     * @param prefix (optional) prefix to filter the labels on. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLabels200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLabels200Response> getLabelsWithHttpInfo(String prefix, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "prefix", prefix));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetLabels200Response> localReturnType = new ParameterizedTypeReference<GetLabels200Response>() {};
        return apiClient.invokeAPI("/api/latest/labels", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get current log level
     * Retrieve the current log level for a given logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The log level of the logger.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the log level.
     * @param loggerName The name of the logger. (required)
     * @return RestLogLevel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestLogLevel getLevel(String loggerName) throws RestClientException {
        return getLevelWithHttpInfo(loggerName).getBody();
    }

    /**
     * Get current log level
     * Retrieve the current log level for a given logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The log level of the logger.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the log level.
     * @param loggerName The name of the logger. (required)
     * @return ResponseEntity&lt;RestLogLevel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestLogLevel> getLevelWithHttpInfo(String loggerName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'loggerName' is set
        if (loggerName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'loggerName' when calling getLevel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("loggerName", loggerName);

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

        ParameterizedTypeReference<RestLogLevel> localReturnType = new ParameterizedTypeReference<RestLogLevel>() {};
        return apiClient.invokeAPI("/api/latest/logs/logger/{loggerName}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get mail configuration
     * Retrieves the current mail configuration.   The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The mail configuration
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the mail configuration.
     * <p><b>404</b> - The mail server hasn&#39;t been configured
     * @return RestMailConfiguration
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMailConfiguration getMailConfig() throws RestClientException {
        return getMailConfigWithHttpInfo().getBody();
    }

    /**
     * Get mail configuration
     * Retrieves the current mail configuration.   The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The mail configuration
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the mail configuration.
     * <p><b>404</b> - The mail server hasn&#39;t been configured
     * @return ResponseEntity&lt;RestMailConfiguration&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMailConfiguration> getMailConfigWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestMailConfiguration> localReturnType = new ParameterizedTypeReference<RestMailConfiguration>() {};
        return apiClient.invokeAPI("/api/latest/admin/mail-server", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Mesh migration job details
     * Gets the details, including the current status and progress, of the job identified by the given ID.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The details of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMeshMigrationJob(String jobId) throws RestClientException {
        getMeshMigrationJobWithHttpInfo(jobId);
    }

    /**
     * Get Mesh migration job details
     * Gets the details, including the current status and progress, of the job identified by the given ID.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The details of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMeshMigrationJobWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getMeshMigrationJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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
        return apiClient.invokeAPI("/api/latest/migration/mesh/{jobId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Mesh migration job messages
     * Gets the messages generated by the job.   Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.        - INFO      - WARN      - ERROR   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The details of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetExportJobMessages200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetExportJobMessages200Response getMeshMigrationJobMessages(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getMeshMigrationJobMessagesWithHttpInfo(jobId, severity, subject, start, limit).getBody();
    }

    /**
     * Get Mesh migration job messages
     * Gets the messages generated by the job.   Without any filter, all messages will be returned, but the response can optionally be filtered for the following severities. The severity parameter can be repeated to include multiple severities in one response.        - INFO      - WARN      - ERROR   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The details of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @param severity The severity to include in the results (optional)
     * @param subject The subject (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetExportJobMessages200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetExportJobMessages200Response> getMeshMigrationJobMessagesWithHttpInfo(String jobId, String severity, String subject, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getMeshMigrationJobMessages");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "severity", severity));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "subject", subject));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetExportJobMessages200Response> localReturnType = new ParameterizedTypeReference<GetExportJobMessages200Response>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/{jobId}/messages", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Mesh migration job summary
     * Gets the summary, including the queue status and progress, of a Mesh migration job.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @return RestMeshMigrationSummary
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshMigrationSummary getMeshMigrationJobSummary(String jobId) throws RestClientException {
        return getMeshMigrationJobSummaryWithHttpInfo(jobId).getBody();
    }

    /**
     * Get Mesh migration job summary
     * Gets the summary, including the queue status and progress, of a Mesh migration job.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The summary of the migration job.
     * <p><b>400</b> - The job ID parameter was not supplied.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The specified job ID does not exist.
     * @param jobId The ID of the job (required)
     * @return ResponseEntity&lt;RestMeshMigrationSummary&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshMigrationSummary> getMeshMigrationJobSummaryWithHttpInfo(String jobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jobId' when calling getMeshMigrationJobSummary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("jobId", jobId);

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

        ParameterizedTypeReference<RestMeshMigrationSummary> localReturnType = new ParameterizedTypeReference<RestMeshMigrationSummary>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/{jobId}/summary", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get Mesh node
     * Get the registered Mesh node that matches the supplied ID.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The Mesh node that matches the ID.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The Mesh node does not exist.
     * @param id The ID of the Mesh node. (required)
     * @return RestMeshNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshNode getMeshNode(String id) throws RestClientException {
        return getMeshNodeWithHttpInfo(id).getBody();
    }

    /**
     * Get Mesh node
     * Get the registered Mesh node that matches the supplied ID.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The Mesh node that matches the ID.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The Mesh node does not exist.
     * @param id The ID of the Mesh node. (required)
     * @return ResponseEntity&lt;RestMeshNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshNode> getMeshNodeWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getMeshNode");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RestMeshNode> localReturnType = new ParameterizedTypeReference<RestMeshNode>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/nodes/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get rate limit settings for user
     * Retrieves the user-specific rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing all the user-specific rate limit settings filtered by the optional filter.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * @param filter Optional filter (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRateLimitSettingsForUser200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRateLimitSettingsForUser200Response getRateLimitSettingsForUser(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getRateLimitSettingsForUserWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get rate limit settings for user
     * Retrieves the user-specific rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing all the user-specific rate limit settings filtered by the optional filter.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * @param filter Optional filter (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRateLimitSettingsForUser200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRateLimitSettingsForUser200Response> getRateLimitSettingsForUserWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<GetRateLimitSettingsForUser200Response> localReturnType = new ParameterizedTypeReference<GetRateLimitSettingsForUser200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings/users", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository archive policy
     * Retrieves the repository archive policy for the instance.  The user must be authenticated to access this resource.
     * <p><b>200</b> - A response containing the repository archive policy for the instance
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the repository archive policy
     * @return RestRepositoryPolicy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPolicy getRepositoryArchivePolicy() throws RestClientException {
        return getRepositoryArchivePolicyWithHttpInfo().getBody();
    }

    /**
     * Get repository archive policy
     * Retrieves the repository archive policy for the instance.  The user must be authenticated to access this resource.
     * <p><b>200</b> - A response containing the repository archive policy for the instance
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the repository archive policy
     * @return ResponseEntity&lt;RestRepositoryPolicy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPolicy> getRepositoryArchivePolicyWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestRepositoryPolicy> localReturnType = new ParameterizedTypeReference<RestRepositoryPolicy>() {};
        return apiClient.invokeAPI("/policies/latest/admin/repos/archive", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository delete policy
     * Retrieves the repository delete policy for the instance.  The user must be authenticated to access this resource.
     * <p><b>200</b> - A response containing the repository delete policy for the instance
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the repository delete policy
     * @return RestRepositoryPolicy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPolicy getRepositoryDeletePolicy() throws RestClientException {
        return getRepositoryDeletePolicyWithHttpInfo().getBody();
    }

    /**
     * Get repository delete policy
     * Retrieves the repository delete policy for the instance.  The user must be authenticated to access this resource.
     * <p><b>200</b> - A response containing the repository delete policy for the instance
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the repository delete policy
     * @return ResponseEntity&lt;RestRepositoryPolicy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPolicy> getRepositoryDeletePolicyWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestRepositoryPolicy> localReturnType = new ParameterizedTypeReference<RestRepositoryPolicy>() {};
        return apiClient.invokeAPI("/policies/latest/admin/repos/delete", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get root log level
     *  Retrieve the current log level for the root logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The log level of the logger.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the log level.
     * @return RestLogLevel
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestLogLevel getRootLevel() throws RestClientException {
        return getRootLevelWithHttpInfo().getBody();
    }

    /**
     * Get root log level
     *  Retrieve the current log level for the root logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The log level of the logger.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the log level.
     * @return ResponseEntity&lt;RestLogLevel&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestLogLevel> getRootLevelWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestLogLevel> localReturnType = new ParameterizedTypeReference<RestLogLevel>() {};
        return apiClient.invokeAPI("/api/latest/logs/rootLogger", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get server mail address
     * Retrieves the server email address
     * <p><b>200</b> - The server email address
     * <p><b>401</b> - he currently authenticated user has insufficient permissions to retrieve the server email address.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getSenderAddress() throws RestClientException {
        getSenderAddressWithHttpInfo();
    }

    /**
     * Get server mail address
     * Retrieves the server email address
     * <p><b>200</b> - The server email address
     * <p><b>401</b> - he currently authenticated user has insufficient permissions to retrieve the server email address.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getSenderAddressWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/mail-server/sender-address", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get rate limit settings
     * Retrieves the rate limit settings for the instance.  The user must be authenticated to call this resource.
     * <p><b>200</b> - A response containing the rate limit plugin settings for the instance.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * @return RestRateLimitSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRateLimitSettings getSettings2() throws RestClientException {
        return getSettings2WithHttpInfo().getBody();
    }

    /**
     * Get rate limit settings
     * Retrieves the rate limit settings for the instance.  The user must be authenticated to call this resource.
     * <p><b>200</b> - A response containing the rate limit plugin settings for the instance.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve rate limit settings.
     * @return ResponseEntity&lt;RestRateLimitSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRateLimitSettings> getSettings2WithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestRateLimitSettings> localReturnType = new ParameterizedTypeReference<RestRateLimitSettings>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get support zip for node
     * Get the support zip for the Mesh node that matches the specified ID.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The support zip for the Mesh node that matches the ID.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The Mesh node does not exist.
     * @param id The ID of the Mesh node. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getSupportZip(String id) throws RestClientException {
        getSupportZipWithHttpInfo(id);
    }

    /**
     * Get support zip for node
     * Get the support zip for the Mesh node that matches the specified ID.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The support zip for the Mesh node that matches the ID.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - The Mesh node does not exist.
     * @param id The ID of the Mesh node. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getSupportZipWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getSupportZip");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/support-zips/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get support zips for all Mesh nodes
     * Get the support zips for all the Mesh nodes.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The support zips for all the Mesh nodes.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getSupportZips() throws RestClientException {
        getSupportZipsWithHttpInfo();
    }

    /**
     * Get support zips for all Mesh nodes
     * Get the support zips for all the Mesh nodes.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The support zips for all the Mesh nodes.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getSupportZipsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/support-zips", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get supported SSH key algorithms and lengths
     * Retrieves a list of all supported SSH key algorithms and lengths.
     * <p><b>200</b> - A list of supported SSH key algorithms and lengths.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve this list.
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getSupportedKeyTypes() throws RestClientException {
        getSupportedKeyTypesWithHttpInfo();
    }

    /**
     * Get supported SSH key algorithms and lengths
     * Retrieves a list of all supported SSH key algorithms and lengths.
     * <p><b>200</b> - A list of supported SSH key algorithms and lengths.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve this list.
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getSupportedKeyTypesWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/admin/supported-key-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get user
     * Retrieve the user matching the supplied &lt;strong&gt;userSlug&lt;/strong&gt;.
     * <p><b>200</b> - The user matching the supplied &lt;strong&gt;userSlug&lt;/strong&gt;. Note, this may &lt;i&gt;not&lt;/i&gt; be the user&#39;s username, always use the &lt;strong&gt;user.slug&lt;/strong&gt; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the user.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @return RestApplicationUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestApplicationUser getUser(String userSlug) throws RestClientException {
        return getUserWithHttpInfo(userSlug).getBody();
    }

    /**
     * Get user
     * Retrieve the user matching the supplied &lt;strong&gt;userSlug&lt;/strong&gt;.
     * <p><b>200</b> - The user matching the supplied &lt;strong&gt;userSlug&lt;/strong&gt;. Note, this may &lt;i&gt;not&lt;/i&gt; be the user&#39;s username, always use the &lt;strong&gt;user.slug&lt;/strong&gt; property.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to view the user.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @return ResponseEntity&lt;RestApplicationUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestApplicationUser> getUserWithHttpInfo(String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling getUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<RestApplicationUser> localReturnType = new ParameterizedTypeReference<RestApplicationUser>() {};
        return apiClient.invokeAPI("/api/latest/users/{userSlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get user settings
     * Retrieve a map of user setting key values for a specific user identified by the user slug.
     * <p><b>200</b> - The user settings for the specified user slug.
     * <p><b>401</b> - The currently authenticated user does not have permission to view the settings of this user.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param userSlug The user slug. (required)
     * @return ExampleSettingsMap
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExampleSettingsMap getUserSettings(String userSlug) throws RestClientException {
        return getUserSettingsWithHttpInfo(userSlug).getBody();
    }

    /**
     * Get user settings
     * Retrieve a map of user setting key values for a specific user identified by the user slug.
     * <p><b>200</b> - The user settings for the specified user slug.
     * <p><b>401</b> - The currently authenticated user does not have permission to view the settings of this user.
     * <p><b>404</b> - The specified project, repository, commit, or report does not exist.
     * @param userSlug The user slug. (required)
     * @return ResponseEntity&lt;ExampleSettingsMap&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExampleSettingsMap> getUserSettingsWithHttpInfo(String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling getUserSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<ExampleSettingsMap> localReturnType = new ParameterizedTypeReference<ExampleSettingsMap>() {};
        return apiClient.invokeAPI("/api/latest/users/{userSlug}/settings", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all users
     * Retrieve a page of users, optionally run through provided filters.   Only authenticated users may call this resource. ### Permission Filters   The following three sub-sections list parameters supported for permission filters (where &lt;code&gt;[root]&lt;/code&gt; is the root permission filter name, e.g. &lt;code&gt;permission&lt;/code&gt;, &lt;code&gt;permission.1&lt;/code&gt; etc.) depending on the permission resource. The system determines which filter to apply (Global, Project or Repository permission) based on the &#x60;[root]&#x60; permission value. E.g. &lt;code&gt;ADMIN&lt;/code&gt; is a global permission, &lt;code&gt;PROJECT_ADMIN&lt;/code&gt; is a project permission and &lt;code&gt;REPO_ADMIN&lt;/code&gt; is a repository permission. Note that the parameters for a given resource will be looked up in the order as they are listed below, that is e.g. for a project resource, if both &lt;code&gt;projectId&lt;/code&gt; and &lt;code&gt;projectKey&lt;/code&gt; are provided, the system will use &lt;code&gt;projectId&lt;/code&gt; for the lookup. &lt;h4&gt;Global permissions&lt;/h4&gt;   The permission value under &lt;code&gt;[root]&lt;/code&gt; is the only required and recognized parameter, as global permissions do not apply to a specific resource.   Example valid filter: &lt;code&gt;permission&#x3D;ADMIN&lt;/code&gt;. &lt;h4&gt;Project permissions&lt;/h4&gt;   - &lt;code&gt;[root]&lt;/code&gt;- specifies the project permission - &lt;code&gt;[root].projectId&lt;/code&gt; - specifies the project ID to lookup the project by - &lt;code&gt;[root].projectKey&lt;/code&gt; - specifies the project key to lookup the project by   Example valid filter: &lt;code&gt;permission.1&#x3D;PROJECT_ADMIN&amp;amp;permission.1.projectKey&#x3D;TEST_PROJECT&lt;/code&gt;. #### Repository permissions   - &lt;code&gt;[root]&lt;/code&gt;- specifies the repository permission - &lt;code&gt;[root].projectId&lt;/code&gt; - specifies the repository ID to lookup the repository by - &lt;code&gt;[root].projectKey&lt;/code&gt; and &lt;code&gt;[root].repositorySlug&lt;/code&gt;- specifies the project key and     repository slug to lookup the repository by; both values &lt;i&gt;need to&lt;/i&gt; be provided for this look up to be     triggered   Example valid filter: &lt;code&gt;permission.2&#x3D;REPO_ADMIN&amp;amp;permission.2.projectKey&#x3D;TEST_PROJECT&amp;amp;permission.2.repositorySlug&#x3D;test_repo&lt;/code&gt;.
     * <p><b>200</b> - A page of users.
     * <p><b>400</b> - The search request was invalid, which may happen for multiple reasons, among others:   - permission filter for project/repository permission with no parameters specifying the project or     repository to apply the filter to - invalid permission name - permission filter for a project/repository permission pointing to a non-existent project or repository   The exact reason for the error and - in most cases - the request parameter name that had invalid value - will be provided in the error message.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param filter Return only users, whose username, name or email address &lt;i&gt;contain&lt;/i&gt; the &lt;code&gt; filter&lt;/code&gt; value (optional)
     * @param permissionN The \&quot;root\&quot; of a single permission filter, similar to the &lt;code&gt;permission&lt;/code&gt; parameter, where \&quot;N\&quot; is a natural number starting from 1. This allows clients to specify multiple permission filters, by providing consecutive filters as &lt;code&gt;permission.1&lt;/code&gt;, &lt;code&gt;permission.2&lt;/code&gt; etc. Note that the filters numbering has to start with 1 and be continuous for all filters to be processed. The total allowed number of permission filters is 50 and all filters exceeding that limit will be dropped. See the section \&quot;Permission Filters\&quot; above for more details on how the permission filters are processed. (optional)
     * @param permission The \&quot;root\&quot; of a permission filter, whose value must be a valid global, project, or repository permission. Additional filter parameters referring to this filter that specify the resource (project or repository) to apply the filter to must be prefixed with &lt;code&gt;permission.&lt;/code&gt;. See the section \&quot;Permission Filters\&quot; above for more details. (optional)
     * @param group return only users who are members of the given group (optional)
     * @return RestApplicationUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestApplicationUser getUsers2(String filter, String permissionN, String permission, String group) throws RestClientException {
        return getUsers2WithHttpInfo(filter, permissionN, permission, group).getBody();
    }

    /**
     * Get all users
     * Retrieve a page of users, optionally run through provided filters.   Only authenticated users may call this resource. ### Permission Filters   The following three sub-sections list parameters supported for permission filters (where &lt;code&gt;[root]&lt;/code&gt; is the root permission filter name, e.g. &lt;code&gt;permission&lt;/code&gt;, &lt;code&gt;permission.1&lt;/code&gt; etc.) depending on the permission resource. The system determines which filter to apply (Global, Project or Repository permission) based on the &#x60;[root]&#x60; permission value. E.g. &lt;code&gt;ADMIN&lt;/code&gt; is a global permission, &lt;code&gt;PROJECT_ADMIN&lt;/code&gt; is a project permission and &lt;code&gt;REPO_ADMIN&lt;/code&gt; is a repository permission. Note that the parameters for a given resource will be looked up in the order as they are listed below, that is e.g. for a project resource, if both &lt;code&gt;projectId&lt;/code&gt; and &lt;code&gt;projectKey&lt;/code&gt; are provided, the system will use &lt;code&gt;projectId&lt;/code&gt; for the lookup. &lt;h4&gt;Global permissions&lt;/h4&gt;   The permission value under &lt;code&gt;[root]&lt;/code&gt; is the only required and recognized parameter, as global permissions do not apply to a specific resource.   Example valid filter: &lt;code&gt;permission&#x3D;ADMIN&lt;/code&gt;. &lt;h4&gt;Project permissions&lt;/h4&gt;   - &lt;code&gt;[root]&lt;/code&gt;- specifies the project permission - &lt;code&gt;[root].projectId&lt;/code&gt; - specifies the project ID to lookup the project by - &lt;code&gt;[root].projectKey&lt;/code&gt; - specifies the project key to lookup the project by   Example valid filter: &lt;code&gt;permission.1&#x3D;PROJECT_ADMIN&amp;amp;permission.1.projectKey&#x3D;TEST_PROJECT&lt;/code&gt;. #### Repository permissions   - &lt;code&gt;[root]&lt;/code&gt;- specifies the repository permission - &lt;code&gt;[root].projectId&lt;/code&gt; - specifies the repository ID to lookup the repository by - &lt;code&gt;[root].projectKey&lt;/code&gt; and &lt;code&gt;[root].repositorySlug&lt;/code&gt;- specifies the project key and     repository slug to lookup the repository by; both values &lt;i&gt;need to&lt;/i&gt; be provided for this look up to be     triggered   Example valid filter: &lt;code&gt;permission.2&#x3D;REPO_ADMIN&amp;amp;permission.2.projectKey&#x3D;TEST_PROJECT&amp;amp;permission.2.repositorySlug&#x3D;test_repo&lt;/code&gt;.
     * <p><b>200</b> - A page of users.
     * <p><b>400</b> - The search request was invalid, which may happen for multiple reasons, among others:   - permission filter for project/repository permission with no parameters specifying the project or     repository to apply the filter to - invalid permission name - permission filter for a project/repository permission pointing to a non-existent project or repository   The exact reason for the error and - in most cases - the request parameter name that had invalid value - will be provided in the error message.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param filter Return only users, whose username, name or email address &lt;i&gt;contain&lt;/i&gt; the &lt;code&gt; filter&lt;/code&gt; value (optional)
     * @param permissionN The \&quot;root\&quot; of a single permission filter, similar to the &lt;code&gt;permission&lt;/code&gt; parameter, where \&quot;N\&quot; is a natural number starting from 1. This allows clients to specify multiple permission filters, by providing consecutive filters as &lt;code&gt;permission.1&lt;/code&gt;, &lt;code&gt;permission.2&lt;/code&gt; etc. Note that the filters numbering has to start with 1 and be continuous for all filters to be processed. The total allowed number of permission filters is 50 and all filters exceeding that limit will be dropped. See the section \&quot;Permission Filters\&quot; above for more details on how the permission filters are processed. (optional)
     * @param permission The \&quot;root\&quot; of a permission filter, whose value must be a valid global, project, or repository permission. Additional filter parameters referring to this filter that specify the resource (project or repository) to apply the filter to must be prefixed with &lt;code&gt;permission.&lt;/code&gt;. See the section \&quot;Permission Filters\&quot; above for more details. (optional)
     * @param group return only users who are members of the given group (optional)
     * @return ResponseEntity&lt;RestApplicationUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestApplicationUser> getUsers2WithHttpInfo(String filter, String permissionN, String permission, String group) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission.N", permissionN));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestApplicationUser> localReturnType = new ParameterizedTypeReference<RestApplicationUser>() {};
        return apiClient.invokeAPI("/api/latest/users", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Preview export
     * Enumerates the projects and repositories that would be exported for a given export request.  All affected repositories will be enumerated explicitly, and while projects are listed as individual items in responses from this endpoint, their presence does not imply that all their repositories are included.  While this endpoint can be used to verify that all selectors in the request apply as intended, it should be noted that a subsequent, actual export might contain a different set of repositories, as they might have been added or deleted in the meantime.  Note that the overall response from this endpoint can become very large when a lot of repositories end up in the selection. This is why the server is streaming the response while it is being generated (as opposed to creating it in memory and then sending it all at once) and it can be consumed in a streaming way, too.  Also, due to the potential size of the response, projects and repositories are listed with fewer details than in other REST responses.  For a more detailed description of selectors, see the endpoint documentation for starting an export.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The effectively selected projects and repositories.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to generate a preview.
     * @param restExportRequest the export request (optional)
     * @return RestScopesExample
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestScopesExample previewExport(RestExportRequest restExportRequest) throws RestClientException {
        return previewExportWithHttpInfo(restExportRequest).getBody();
    }

    /**
     * Preview export
     * Enumerates the projects and repositories that would be exported for a given export request.  All affected repositories will be enumerated explicitly, and while projects are listed as individual items in responses from this endpoint, their presence does not imply that all their repositories are included.  While this endpoint can be used to verify that all selectors in the request apply as intended, it should be noted that a subsequent, actual export might contain a different set of repositories, as they might have been added or deleted in the meantime.  Note that the overall response from this endpoint can become very large when a lot of repositories end up in the selection. This is why the server is streaming the response while it is being generated (as opposed to creating it in memory and then sending it all at once) and it can be consumed in a streaming way, too.  Also, due to the potential size of the response, projects and repositories are listed with fewer details than in other REST responses.  For a more detailed description of selectors, see the endpoint documentation for starting an export.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - The effectively selected projects and repositories.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to generate a preview.
     * @param restExportRequest the export request (optional)
     * @return ResponseEntity&lt;RestScopesExample&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestScopesExample> previewExportWithHttpInfo(RestExportRequest restExportRequest) throws RestClientException {
        Object localVarPostBody = restExportRequest;
        

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

        ParameterizedTypeReference<RestScopesExample> localReturnType = new ParameterizedTypeReference<RestScopesExample>() {};
        return apiClient.invokeAPI("/api/latest/migration/exports/preview", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Preview Mesh migration
     * Enumerates the projects and repositories that would be migrated for a given request.  All affected repositories will be enumerated explicitly, and while projects are listed as individual items in responses from this endpoint, their presence does not imply that all their repositories are included.  While this endpoint can be used to verify that all selectors in the request apply as intended, it should be noted that a subsequent, actual export might contain a different set of repositories, as they might have been added or deleted in the meantime.  Note that the overall response from this endpoint can become very large when a lot of repositories end up in the selection. This is why the server is streaming the response while it is being generated (as opposed to creating it in memory and then sending it all at once) and it can be consumed in a streaming way, too.  Also, due to the potential size of the response, projects and repositories are listed with fewer details than in other REST responses.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - Enumeration of projects and repositories that would be migrated for a given request.
     * <p><b>400</b> - The request was invalid or missing.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param restMeshMigrationRequest The export request (optional)
     * @return ExamplePreviewMigration
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExamplePreviewMigration previewMeshMigration(RestMeshMigrationRequest restMeshMigrationRequest) throws RestClientException {
        return previewMeshMigrationWithHttpInfo(restMeshMigrationRequest).getBody();
    }

    /**
     * Preview Mesh migration
     * Enumerates the projects and repositories that would be migrated for a given request.  All affected repositories will be enumerated explicitly, and while projects are listed as individual items in responses from this endpoint, their presence does not imply that all their repositories are included.  While this endpoint can be used to verify that all selectors in the request apply as intended, it should be noted that a subsequent, actual export might contain a different set of repositories, as they might have been added or deleted in the meantime.  Note that the overall response from this endpoint can become very large when a lot of repositories end up in the selection. This is why the server is streaming the response while it is being generated (as opposed to creating it in memory and then sending it all at once) and it can be consumed in a streaming way, too.  Also, due to the potential size of the response, projects and repositories are listed with fewer details than in other REST responses.  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - Enumeration of projects and repositories that would be migrated for a given request.
     * <p><b>400</b> - The request was invalid or missing.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param restMeshMigrationRequest The export request (optional)
     * @return ResponseEntity&lt;ExamplePreviewMigration&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExamplePreviewMigration> previewMeshMigrationWithHttpInfo(RestMeshMigrationRequest restMeshMigrationRequest) throws RestClientException {
        Object localVarPostBody = restMeshMigrationRequest;
        

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

        ParameterizedTypeReference<ExamplePreviewMigration> localReturnType = new ParameterizedTypeReference<ExamplePreviewMigration>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/preview", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get hook script content
     * Retrieves the hook script content.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The hook script content.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * @param scriptId The ID of the hook script (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void read(String scriptId) throws RestClientException {
        readWithHttpInfo(scriptId);
    }

    /**
     * Get hook script content
     * Retrieves the hook script content.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The hook script content.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * @param scriptId The ID of the hook script (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> readWithHttpInfo(String scriptId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling read");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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
        return apiClient.invokeAPI("/api/latest/hook-scripts/{scriptId}/content", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Register new Mesh node
     * Register a new Mesh node.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The newly registered Mesh node.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param restMeshNode The request specifying the new Mesh node. (optional)
     * @return RestMeshNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshNode registerNewMeshNode(RestMeshNode restMeshNode) throws RestClientException {
        return registerNewMeshNodeWithHttpInfo(restMeshNode).getBody();
    }

    /**
     * Register new Mesh node
     * Register a new Mesh node.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The newly registered Mesh node.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param restMeshNode The request specifying the new Mesh node. (optional)
     * @return ResponseEntity&lt;RestMeshNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshNode> registerNewMeshNodeWithHttpInfo(RestMeshNode restMeshNode) throws RestClientException {
        Object localVarPostBody = restMeshNode;
        

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

        ParameterizedTypeReference<RestMeshNode> localReturnType = new ParameterizedTypeReference<RestMeshNode>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/nodes", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find repositories by Mesh migration state
     * Searches for repositories in the system matching the specified criteria and enriches their MeshMigrationQueueState migration state if a migration is currently in progress.   The currently active migration can optionally be specified by passing a migrationId, if known. If this isn&#39;t passed, an attempt is made to locate the active migration and its ID is used.   If a migration is currently active, only repositories that are a part of the migration are filtered and returned. Otherwise, all repositories in the systems are filtered and returned.   Filtering by state is ignored when no migration is currently in progress. In such a case, results are not enriched with their MeshMigrationQueueState migration state.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - A page of repositories matching the specified criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - No migration job with the given ID exists.
     * @param migrationId (optional) The currently active migration job. If not passed, this is looked up internally. (optional)
     * @param projectKey (optional) The project key. Can be specified more than once to filter by more than one project. (optional)
     * @param name (optional) The repository name (optional)
     * @param state (optional) If a migration is active, the MeshMigrationQueueState state to filter results by. Can be specified more than once to filter by more than one state. (optional)
     * @param remote (optional) Whether the repository has been fully migrated to Mesh. If not present, all repositories are considered regardless of where they&#39;re located. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return SearchMeshMigrationRepos200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchMeshMigrationRepos200Response searchMeshMigrationRepos(String migrationId, String projectKey, String name, String state, String remote, BigDecimal start, BigDecimal limit) throws RestClientException {
        return searchMeshMigrationReposWithHttpInfo(migrationId, projectKey, name, state, remote, start, limit).getBody();
    }

    /**
     * Find repositories by Mesh migration state
     * Searches for repositories in the system matching the specified criteria and enriches their MeshMigrationQueueState migration state if a migration is currently in progress.   The currently active migration can optionally be specified by passing a migrationId, if known. If this isn&#39;t passed, an attempt is made to locate the active migration and its ID is used.   If a migration is currently active, only repositories that are a part of the migration are filtered and returned. Otherwise, all repositories in the systems are filtered and returned.   Filtering by state is ignored when no migration is currently in progress. In such a case, results are not enriched with their MeshMigrationQueueState migration state.   The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - A page of repositories matching the specified criteria.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>404</b> - No migration job with the given ID exists.
     * @param migrationId (optional) The currently active migration job. If not passed, this is looked up internally. (optional)
     * @param projectKey (optional) The project key. Can be specified more than once to filter by more than one project. (optional)
     * @param name (optional) The repository name (optional)
     * @param state (optional) If a migration is active, the MeshMigrationQueueState state to filter results by. Can be specified more than once to filter by more than one state. (optional)
     * @param remote (optional) Whether the repository has been fully migrated to Mesh. If not present, all repositories are considered regardless of where they&#39;re located. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;SearchMeshMigrationRepos200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchMeshMigrationRepos200Response> searchMeshMigrationReposWithHttpInfo(String migrationId, String projectKey, String name, String state, String remote, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "migrationId", migrationId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "projectKey", projectKey));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "state", state));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "remote", remote));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<SearchMeshMigrationRepos200Response> localReturnType = new ParameterizedTypeReference<SearchMeshMigrationRepos200Response>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh/repos", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set rate limit settings for user
     * Sets the given rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated user settings
     * <p><b>400</b> - One of the following valid state error cases occurred (check the error message for more details):  - The request is empty - Whitelisted is false or not provided, and no settings are provided either - Whitelisted is false or not provided, settings are provided,   but do not contain both capacity and fillRate - Whitelisted is false or not provided, settings are provided,   but capacity and fillRate are not positive integers - Whitelisted is true, and settings are provided (only one must be provided)   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set user settings.
     * @param userSlug The user slug. (required)
     * @param restUserRateLimitSettingsUpdateRequest  (optional)
     * @return RestUserRateLimitSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUserRateLimitSettings set2(String userSlug, RestUserRateLimitSettingsUpdateRequest restUserRateLimitSettingsUpdateRequest) throws RestClientException {
        return set2WithHttpInfo(userSlug, restUserRateLimitSettingsUpdateRequest).getBody();
    }

    /**
     * Set rate limit settings for user
     * Sets the given rate limit settings for the given user.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated user settings
     * <p><b>400</b> - One of the following valid state error cases occurred (check the error message for more details):  - The request is empty - Whitelisted is false or not provided, and no settings are provided either - Whitelisted is false or not provided, settings are provided,   but do not contain both capacity and fillRate - Whitelisted is false or not provided, settings are provided,   but capacity and fillRate are not positive integers - Whitelisted is true, and settings are provided (only one must be provided)   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set user settings.
     * @param userSlug The user slug. (required)
     * @param restUserRateLimitSettingsUpdateRequest  (optional)
     * @return ResponseEntity&lt;RestUserRateLimitSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUserRateLimitSettings> set2WithHttpInfo(String userSlug, RestUserRateLimitSettingsUpdateRequest restUserRateLimitSettingsUpdateRequest) throws RestClientException {
        Object localVarPostBody = restUserRateLimitSettingsUpdateRequest;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling set2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<RestUserRateLimitSettings> localReturnType = new ParameterizedTypeReference<RestUserRateLimitSettings>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings/users/{userSlug}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set rate limit settings for users
     * Sets the given rate limit settings for the given users.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated user settings.
     * <p><b>400</b> - One of the following valid state error cases occurred (check the error message for more details):  - The request is empty - No users are provided in the request - One or more of the users are invalid - Whitelisted is false or not provided, and no settings are provided either - Whitelisted is false or not provided, settings are provided,   but do not contain both capacity and fillRate - Whitelisted is false or not provided, settings are provided,   but capacity and fillRate are not positive integers - Whitelisted is true, and settings are provided (only one must be provided) 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set user settings.
     * @param restBulkUserRateLimitSettingsUpdateRequest  (optional)
     * @return RestUserRateLimitSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestUserRateLimitSettings set3(RestBulkUserRateLimitSettingsUpdateRequest restBulkUserRateLimitSettingsUpdateRequest) throws RestClientException {
        return set3WithHttpInfo(restBulkUserRateLimitSettingsUpdateRequest).getBody();
    }

    /**
     * Set rate limit settings for users
     * Sets the given rate limit settings for the given users.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated user settings.
     * <p><b>400</b> - One of the following valid state error cases occurred (check the error message for more details):  - The request is empty - No users are provided in the request - One or more of the users are invalid - Whitelisted is false or not provided, and no settings are provided either - Whitelisted is false or not provided, settings are provided,   but do not contain both capacity and fillRate - Whitelisted is false or not provided, settings are provided,   but capacity and fillRate are not positive integers - Whitelisted is true, and settings are provided (only one must be provided) 
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set user settings.
     * @param restBulkUserRateLimitSettingsUpdateRequest  (optional)
     * @return ResponseEntity&lt;RestUserRateLimitSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestUserRateLimitSettings> set3WithHttpInfo(RestBulkUserRateLimitSettingsUpdateRequest restBulkUserRateLimitSettingsUpdateRequest) throws RestClientException {
        Object localVarPostBody = restBulkUserRateLimitSettingsUpdateRequest;
        

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

        ParameterizedTypeReference<RestUserRateLimitSettings> localReturnType = new ParameterizedTypeReference<RestUserRateLimitSettings>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings/users", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update/Set announcement banner
     * Sets the announcement banner with the provided JSON. Only users authenticated as Admins may call this resource
     * <p><b>204</b> - The banner was set successfully
     * <p><b>400</b> - There was malformed or incorrect data in the provided JSON
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @param setBannerRequest  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setBanner(SetBannerRequest setBannerRequest) throws RestClientException {
        setBannerWithHttpInfo(setBannerRequest);
    }

    /**
     * Update/Set announcement banner
     * Sets the announcement banner with the provided JSON. Only users authenticated as Admins may call this resource
     * <p><b>204</b> - The banner was set successfully
     * <p><b>400</b> - There was malformed or incorrect data in the provided JSON
     * <p><b>401</b> - The user does not have permission to access the banner service through this endpoint
     * @param setBannerRequest  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setBannerWithHttpInfo(SetBannerRequest setBannerRequest) throws RestClientException {
        Object localVarPostBody = setBannerRequest;
        

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
        return apiClient.invokeAPI("/api/latest/admin/banner", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update/Set default branch
     * Configures the global default branch, which is used when creating new repositories if an explicit default branch is not specified.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The default branch has been set.
     * <p><b>401</b> - The current user does not have sufficient permissions to configure the global default branch.
     * @param setDefaultBranchRequest  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setDefaultBranch(SetDefaultBranchRequest setDefaultBranchRequest) throws RestClientException {
        setDefaultBranchWithHttpInfo(setDefaultBranchRequest);
    }

    /**
     * Update/Set default branch
     * Configures the global default branch, which is used when creating new repositories if an explicit default branch is not specified.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The default branch has been set.
     * <p><b>401</b> - The current user does not have sufficient permissions to configure the global default branch.
     * @param setDefaultBranchRequest  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setDefaultBranchWithHttpInfo(SetDefaultBranchRequest setDefaultBranchRequest) throws RestClientException {
        Object localVarPostBody = setDefaultBranchRequest;
        

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
        return apiClient.invokeAPI("/api/latest/admin/default-branch", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set log level
     * Set the current log level for a given logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>204</b> - The log level was successfully changed.
     * <p><b>400</b> - The log level was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the log level.
     * @param levelName The level to set the logger to. Either TRACE, DEBUG, INFO, WARN or ERROR (required)
     * @param loggerName The name of the logger. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setLevel(String levelName, String loggerName) throws RestClientException {
        setLevelWithHttpInfo(levelName, loggerName);
    }

    /**
     * Set log level
     * Set the current log level for a given logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>204</b> - The log level was successfully changed.
     * <p><b>400</b> - The log level was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the log level.
     * @param levelName The level to set the logger to. Either TRACE, DEBUG, INFO, WARN or ERROR (required)
     * @param loggerName The name of the logger. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setLevelWithHttpInfo(String levelName, String loggerName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'levelName' is set
        if (levelName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'levelName' when calling setLevel");
        }
        
        // verify the required parameter 'loggerName' is set
        if (loggerName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'loggerName' when calling setLevel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("levelName", levelName);
        uriVariables.put("loggerName", loggerName);

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
        return apiClient.invokeAPI("/api/latest/logs/logger/{loggerName}/{levelName}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update mail configuration
     * Updates the mail configuration.   The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The updated mail configuration.
     * <p><b>400</b> - The mail configuration was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update themail configuration.
     * @param setMailConfigRequest  (optional)
     * @return RestMailConfiguration
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMailConfiguration setMailConfig(SetMailConfigRequest setMailConfigRequest) throws RestClientException {
        return setMailConfigWithHttpInfo(setMailConfigRequest).getBody();
    }

    /**
     * Update mail configuration
     * Updates the mail configuration.   The authenticated user must have the &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The updated mail configuration.
     * <p><b>400</b> - The mail configuration was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update themail configuration.
     * @param setMailConfigRequest  (optional)
     * @return ResponseEntity&lt;RestMailConfiguration&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMailConfiguration> setMailConfigWithHttpInfo(SetMailConfigRequest setMailConfigRequest) throws RestClientException {
        Object localVarPostBody = setMailConfigRequest;
        

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

        ParameterizedTypeReference<RestMailConfiguration> localReturnType = new ParameterizedTypeReference<RestMailConfiguration>() {};
        return apiClient.invokeAPI("/api/latest/admin/mail-server", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update repository archive policy
     * Sets the repository archive policy for the instance.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission.
     * <p><b>200</b> - A response containing the repository archive policy for the instance
     * <p><b>400</b> - The permission was invalid or does not exist
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the repository archive policy
     * @param restRepositoryPolicy The request containing the details of the policy. (optional)
     * @return RestRepositoryPolicy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPolicy setRepositoryArchivePolicy(RestRepositoryPolicy restRepositoryPolicy) throws RestClientException {
        return setRepositoryArchivePolicyWithHttpInfo(restRepositoryPolicy).getBody();
    }

    /**
     * Update repository archive policy
     * Sets the repository archive policy for the instance.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission.
     * <p><b>200</b> - A response containing the repository archive policy for the instance
     * <p><b>400</b> - The permission was invalid or does not exist
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the repository archive policy
     * @param restRepositoryPolicy The request containing the details of the policy. (optional)
     * @return ResponseEntity&lt;RestRepositoryPolicy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPolicy> setRepositoryArchivePolicyWithHttpInfo(RestRepositoryPolicy restRepositoryPolicy) throws RestClientException {
        Object localVarPostBody = restRepositoryPolicy;
        

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

        ParameterizedTypeReference<RestRepositoryPolicy> localReturnType = new ParameterizedTypeReference<RestRepositoryPolicy>() {};
        return apiClient.invokeAPI("/policies/latest/admin/repos/archive", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update the repository delete policy
     * Sets the repository delete policy for the instance.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission.
     * <p><b>200</b> - A response containing the repository delete policy for the instance
     * <p><b>400</b> - The permission was invalid or does not exist
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the repository delete policy
     * @param restRepositoryPolicy The request containing the details of the policy. (optional)
     * @return RestRepositoryPolicy
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRepositoryPolicy setRepositoryDeletePolicy(RestRepositoryPolicy restRepositoryPolicy) throws RestClientException {
        return setRepositoryDeletePolicyWithHttpInfo(restRepositoryPolicy).getBody();
    }

    /**
     * Update the repository delete policy
     * Sets the repository delete policy for the instance.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission.
     * <p><b>200</b> - A response containing the repository delete policy for the instance
     * <p><b>400</b> - The permission was invalid or does not exist
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the repository delete policy
     * @param restRepositoryPolicy The request containing the details of the policy. (optional)
     * @return ResponseEntity&lt;RestRepositoryPolicy&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRepositoryPolicy> setRepositoryDeletePolicyWithHttpInfo(RestRepositoryPolicy restRepositoryPolicy) throws RestClientException {
        Object localVarPostBody = restRepositoryPolicy;
        

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

        ParameterizedTypeReference<RestRepositoryPolicy> localReturnType = new ParameterizedTypeReference<RestRepositoryPolicy>() {};
        return apiClient.invokeAPI("/policies/latest/admin/repos/delete", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set root log level
     * Set the current log level for the root logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>204</b> - The log level was successfully changed.
     * <p><b>400</b> - The log level was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the log level.
     * @param levelName the level to set the logger to. Either TRACE, DEBUG, INFO, WARN or ERROR (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setRootLevel(String levelName) throws RestClientException {
        setRootLevelWithHttpInfo(levelName);
    }

    /**
     * Set root log level
     * Set the current log level for the root logger.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>204</b> - The log level was successfully changed.
     * <p><b>400</b> - The log level was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set the log level.
     * @param levelName the level to set the logger to. Either TRACE, DEBUG, INFO, WARN or ERROR (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setRootLevelWithHttpInfo(String levelName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'levelName' is set
        if (levelName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'levelName' when calling setRootLevel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("levelName", levelName);

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
        return apiClient.invokeAPI("/api/latest/logs/rootLogger/{levelName}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update server mail address
     * Updates the server email address   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The from address used in notification emails
     * <p><b>400</b> - The server email address was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the server email address.
     * @param body  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setSenderAddress(String body) throws RestClientException {
        setSenderAddressWithHttpInfo(body);
    }

    /**
     * Update server mail address
     * Updates the server email address   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The from address used in notification emails
     * <p><b>400</b> - The server email address was not updated due to a validation error.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the server email address.
     * @param body  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setSenderAddressWithHttpInfo(String body) throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/admin/mail-server/sender-address", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set rate limit
     * Sets the rate limit settings for the instance.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated rate limit plugin settings for the instance.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):  - The request is empty - The enabled field of the request is not a boolean - The defaultSettings field of the request does not contain both capacity and fillRate - The capacity and fillRate are not positive integers   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set rate limit settings.
     * @param restRateLimitSettings Sets the rate limit settings for the instance.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource. (optional)
     * @return RestRateLimitSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRateLimitSettings setSettings2(RestRateLimitSettings restRateLimitSettings) throws RestClientException {
        return setSettings2WithHttpInfo(restRateLimitSettings).getBody();
    }

    /**
     * Set rate limit
     * Sets the rate limit settings for the instance.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A response containing the updated rate limit plugin settings for the instance.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details):  - The request is empty - The enabled field of the request is not a boolean - The defaultSettings field of the request does not contain both capacity and fillRate - The capacity and fillRate are not positive integers   
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to set rate limit settings.
     * @param restRateLimitSettings Sets the rate limit settings for the instance.  The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource. (optional)
     * @return ResponseEntity&lt;RestRateLimitSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRateLimitSettings> setSettings2WithHttpInfo(RestRateLimitSettings restRateLimitSettings) throws RestClientException {
        Object localVarPostBody = restRateLimitSettings;
        

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

        ParameterizedTypeReference<RestRateLimitSettings> localReturnType = new ParameterizedTypeReference<RestRateLimitSettings>() {};
        return apiClient.invokeAPI("/api/latest/admin/rate-limit/settings", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Start export job
     * Starts a background job that exports the selected repositories.  Only 2 concurrent exports are supported _per cluster node_. If a request ends up on a node that is already running that many export jobs, the request will be rejected and an error returned.  The response includes a description of the job that has been started, and its ID can be used to query these details again, including the current progress, warnings and errors that occurred while processing the job, and to interrupt and cancel the execution of this job.  The request to start an export is similar to the one for previewing an export. Additionally, it accepts an optional parameter, &#x60;exportLocation&#x60;, which can be used to specify a _relative_ path within &#x60;data/migration/export&#x60; in the shared home directory. No locations outside of that directory will be accepted for exports.  There are essentially three ways to select repositories for export. Regardless of which you use, a few general rules apply:  - You can supply a list of selectors. The selection will be additive. - Repositories that are selected more than once due to overlapping selectors will be de-duplicated and effectively exported only once. - For every selected repository, its full fork hierarchy will be considered selected, even if parts of that hierarchy would otherwise not be matched by the provided selectors. For example, when you explicitly select a single repository only, but that repository is a fork, then its origin will be exported (and eventually imported), too.  Now, a single repository can be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;PRJ\&quot;,       \&quot;slug\&quot;: \&quot;my-repo\&quot; }  &#x60;&#x60;&#x60;  Second, all repositories in a specific project can be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;PRJ\&quot;,       \&quot;slug\&quot;: *\&quot; }  &#x60;&#x60;&#x60;  And third, all projects and repositories in the system would be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;*\&quot;,       \&quot;slug\&quot;: *\&quot; }  &#x60;&#x60;&#x60;  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - Details about the export job.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to start anexport
     * <p><b>503</b> - The export could not be started because the limit of concurrent migration jobs has been reached.
     * @param restExportRequest The request (optional)
     * @return RestJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestJob startExport(RestExportRequest restExportRequest) throws RestClientException {
        return startExportWithHttpInfo(restExportRequest).getBody();
    }

    /**
     * Start export job
     * Starts a background job that exports the selected repositories.  Only 2 concurrent exports are supported _per cluster node_. If a request ends up on a node that is already running that many export jobs, the request will be rejected and an error returned.  The response includes a description of the job that has been started, and its ID can be used to query these details again, including the current progress, warnings and errors that occurred while processing the job, and to interrupt and cancel the execution of this job.  The request to start an export is similar to the one for previewing an export. Additionally, it accepts an optional parameter, &#x60;exportLocation&#x60;, which can be used to specify a _relative_ path within &#x60;data/migration/export&#x60; in the shared home directory. No locations outside of that directory will be accepted for exports.  There are essentially three ways to select repositories for export. Regardless of which you use, a few general rules apply:  - You can supply a list of selectors. The selection will be additive. - Repositories that are selected more than once due to overlapping selectors will be de-duplicated and effectively exported only once. - For every selected repository, its full fork hierarchy will be considered selected, even if parts of that hierarchy would otherwise not be matched by the provided selectors. For example, when you explicitly select a single repository only, but that repository is a fork, then its origin will be exported (and eventually imported), too.  Now, a single repository can be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;PRJ\&quot;,       \&quot;slug\&quot;: \&quot;my-repo\&quot; }  &#x60;&#x60;&#x60;  Second, all repositories in a specific project can be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;PRJ\&quot;,       \&quot;slug\&quot;: *\&quot; }  &#x60;&#x60;&#x60;  And third, all projects and repositories in the system would be selected like this:  &#x60;&#x60;&#x60;    {       \&quot;projectKey\&quot;: \&quot;*\&quot;,       \&quot;slug\&quot;: *\&quot; }  &#x60;&#x60;&#x60;  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - Details about the export job.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to start anexport
     * <p><b>503</b> - The export could not be started because the limit of concurrent migration jobs has been reached.
     * @param restExportRequest The request (optional)
     * @return ResponseEntity&lt;RestJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestJob> startExportWithHttpInfo(RestExportRequest restExportRequest) throws RestClientException {
        Object localVarPostBody = restExportRequest;
        

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

        ParameterizedTypeReference<RestJob> localReturnType = new ParameterizedTypeReference<RestJob>() {};
        return apiClient.invokeAPI("/api/latest/migration/exports", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Start import job
     * Starts a background job that imports the specified archive.  Only 1 import at a time is supported _per cluster_. If another request is made while an import is already running, the request will be rejected and an error returned.  The path in the request must point to a valid archive file. The file must be located within the &#x60;data/migration/import&#x60; directory in the shared home directory.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - Details about the export job.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to start an import.
     * <p><b>503</b> - The import could not be started because the limit of concurrent migration jobs has been reached.
     * @param restImportRequest The request (optional)
     * @return RestJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestJob startImport(RestImportRequest restImportRequest) throws RestClientException {
        return startImportWithHttpInfo(restImportRequest).getBody();
    }

    /**
     * Start import job
     * Starts a background job that imports the specified archive.  Only 1 import at a time is supported _per cluster_. If another request is made while an import is already running, the request will be rejected and an error returned.  The path in the request must point to a valid archive file. The file must be located within the &#x60;data/migration/import&#x60; directory in the shared home directory.  The authenticated user must have **ADMIN** permission or higher to call this resource.
     * <p><b>200</b> - Details about the export job.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to start an import.
     * <p><b>503</b> - The import could not be started because the limit of concurrent migration jobs has been reached.
     * @param restImportRequest The request (optional)
     * @return ResponseEntity&lt;RestJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestJob> startImportWithHttpInfo(RestImportRequest restImportRequest) throws RestClientException {
        Object localVarPostBody = restImportRequest;
        

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

        ParameterizedTypeReference<RestJob> localReturnType = new ParameterizedTypeReference<RestJob>() {};
        return apiClient.invokeAPI("/api/latest/migration/imports", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Start Mesh migration job
     * Starts a background job that migrates selected projects/repositories to Mesh.   Only 1 job is supported _per cluster_.  The response includes a description of the job that has been started, and its ID can be used to query these details again, including the current progress, and to interrupt and cancel the execution of this job.   The request to start a migration is similar to the one for previewing a migration.   There are essentially three ways to select repositories for migration. Regardless of which you use, a few general rules apply:       - You can supply a list of repository IDs and project IDs. The selection will be additive. All repositories     in the system are migrated if both lists are empty.     - Repositories that are selected more than once due to overlapping IDs will be de-duplicated and     effectively migrated only once.     - For every selected repository, its full fork hierarchy will be considered selected, even if parts of that     hierarchy would otherwise not be matched by the provided IDs. For example, when you explicitly     select a single repository only, but that repository is a fork, then its origin will be migrated too.   Now, a single repository can be selected like this:   &#x60;&#x60;&#x60;       {      \&quot;repositoryIds\&quot;: [1]      } &#x60;&#x60;&#x60;  Multiple repositories can be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;repositoryIds\&quot;: [1, 2]      } &#x60;&#x60;&#x60;  Second, all repositories in a specific project can be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;projectIds\&quot;: [1]      } &#x60;&#x60;&#x60;  And third, all projects and repositories in the system would be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;projectIds\&quot;: [],      \&quot;repositoryIds\&quot;: []      } &#x60;&#x60;&#x60;  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The started job
     * <p><b>400</b> - The migration request failed one/more validation checks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>503</b> - A migration job is already in progress
     * @param startMeshMigrationRequest  (optional)
     * @return RestJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestJob startMeshMigration(StartMeshMigrationRequest startMeshMigrationRequest) throws RestClientException {
        return startMeshMigrationWithHttpInfo(startMeshMigrationRequest).getBody();
    }

    /**
     * Start Mesh migration job
     * Starts a background job that migrates selected projects/repositories to Mesh.   Only 1 job is supported _per cluster_.  The response includes a description of the job that has been started, and its ID can be used to query these details again, including the current progress, and to interrupt and cancel the execution of this job.   The request to start a migration is similar to the one for previewing a migration.   There are essentially three ways to select repositories for migration. Regardless of which you use, a few general rules apply:       - You can supply a list of repository IDs and project IDs. The selection will be additive. All repositories     in the system are migrated if both lists are empty.     - Repositories that are selected more than once due to overlapping IDs will be de-duplicated and     effectively migrated only once.     - For every selected repository, its full fork hierarchy will be considered selected, even if parts of that     hierarchy would otherwise not be matched by the provided IDs. For example, when you explicitly     select a single repository only, but that repository is a fork, then its origin will be migrated too.   Now, a single repository can be selected like this:   &#x60;&#x60;&#x60;       {      \&quot;repositoryIds\&quot;: [1]      } &#x60;&#x60;&#x60;  Multiple repositories can be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;repositoryIds\&quot;: [1, 2]      } &#x60;&#x60;&#x60;  Second, all repositories in a specific project can be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;projectIds\&quot;: [1]      } &#x60;&#x60;&#x60;  And third, all projects and repositories in the system would be selected like this:    &#x60;&#x60;&#x60;       {      \&quot;projectIds\&quot;: [],      \&quot;repositoryIds\&quot;: []      } &#x60;&#x60;&#x60;  The authenticated user must have **SYS_ADMIN** permission to call this resource.
     * <p><b>200</b> - The started job
     * <p><b>400</b> - The migration request failed one/more validation checks.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * <p><b>503</b> - A migration job is already in progress
     * @param startMeshMigrationRequest  (optional)
     * @return ResponseEntity&lt;RestJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestJob> startMeshMigrationWithHttpInfo(StartMeshMigrationRequest startMeshMigrationRequest) throws RestClientException {
        Object localVarPostBody = startMeshMigrationRequest;
        

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

        ParameterizedTypeReference<RestJob> localReturnType = new ParameterizedTypeReference<RestJob>() {};
        return apiClient.invokeAPI("/api/latest/migration/mesh", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update global SSH key settings
     * Updates the global settings that enforces the maximum expiry of SSH keys and restrictions on SSH key types.
     * <p><b>204</b> - The ssh key global settings were updated.
     * <p><b>400</b> - The request was invalid, which may be due to:   - attempted to set expiry to less than 1 day - attempted to set expiry using partial days - attempted to set a restriction on a key type which was invalid   The exact reason for the error will be provided in the error message.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update these settings.
     * @param restSshKeySettings A request containing expiry length to be set for SSH keys and a list of SSH key type restrictions. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateGlobalSettings(RestSshKeySettings restSshKeySettings) throws RestClientException {
        updateGlobalSettingsWithHttpInfo(restSshKeySettings);
    }

    /**
     * Update global SSH key settings
     * Updates the global settings that enforces the maximum expiry of SSH keys and restrictions on SSH key types.
     * <p><b>204</b> - The ssh key global settings were updated.
     * <p><b>400</b> - The request was invalid, which may be due to:   - attempted to set expiry to less than 1 day - attempted to set expiry using partial days - attempted to set a restriction on a key type which was invalid   The exact reason for the error will be provided in the error message.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update these settings.
     * @param restSshKeySettings A request containing expiry length to be set for SSH keys and a list of SSH key type restrictions. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateGlobalSettingsWithHttpInfo(RestSshKeySettings restSshKeySettings) throws RestClientException {
        Object localVarPostBody = restSshKeySettings;
        

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
        return apiClient.invokeAPI("/admin", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update a hook script
     * Updates a hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * <p><b>409</b> - A hook script with the same name already exists.
     * <p><b>422</b> - One or more fields to update must be specified: content, description and/or name.
     * @param scriptId The ID of the hook script (required)
     * @param examplePutMultipartFormData The multipart form data containing the hook script (optional)
     * @return RestHookScript
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestHookScript updateHookScript(String scriptId, ExamplePutMultipartFormData examplePutMultipartFormData) throws RestClientException {
        return updateHookScriptWithHttpInfo(scriptId, examplePutMultipartFormData).getBody();
    }

    /**
     * Update a hook script
     * Updates a hook script.  This endpoint requires **SYS_ADMIN** permission.
     * <p><b>200</b> - The updated hook script.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions.
     * <p><b>404</b> - The hook script ID supplied does not exist.
     * <p><b>409</b> - A hook script with the same name already exists.
     * <p><b>422</b> - One or more fields to update must be specified: content, description and/or name.
     * @param scriptId The ID of the hook script (required)
     * @param examplePutMultipartFormData The multipart form data containing the hook script (optional)
     * @return ResponseEntity&lt;RestHookScript&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestHookScript> updateHookScriptWithHttpInfo(String scriptId, ExamplePutMultipartFormData examplePutMultipartFormData) throws RestClientException {
        Object localVarPostBody = examplePutMultipartFormData;
        
        // verify the required parameter 'scriptId' is set
        if (scriptId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scriptId' when calling updateHookScript");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RestHookScript> localReturnType = new ParameterizedTypeReference<RestHookScript>() {};
        return apiClient.invokeAPI("/api/latest/hook-scripts/{scriptId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update license
     * Decodes the provided encoded license and sets it as the active license. If no license was provided, a 400 is returned. If the license cannot be decoded, or cannot be applied, a 409 is returned. Some possible reasons a license may not be applied include:   - It is for a different product - It is already expired   Otherwise, if the license is updated successfully, details for the new license are returned with a 200 response.  &lt;b&gt;Warning&lt;/b&gt;: It is possible to downgrade the license during update, applying a license with a lower number of permitted users. If the number of currently-licensed users exceeds the limits of the new license, pushing will be disabled until the licensed user count is brought into compliance with the new license.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission. &lt;b&gt;ADMIN&lt;/b&gt; users may &lt;i&gt;view&lt;/i&gt; the current license details, but they may not &lt;i&gt;update&lt;/i&gt; the license.
     * <p><b>200</b> - The newly-installed license.
     * <p><b>400</b> - No encoded license was provided in the JSON body for the POST.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the license.
     * <p><b>409</b> - The encoded license could not be decoded, or it is not valid for use on this server. For example, it may be for a different product, or it may have already expired.
     * @param restBitbucketLicense a JSON payload containing the encoded license to apply (optional)
     * @return RestBitbucketLicense
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBitbucketLicense updateLicense(RestBitbucketLicense restBitbucketLicense) throws RestClientException {
        return updateLicenseWithHttpInfo(restBitbucketLicense).getBody();
    }

    /**
     * Update license
     * Decodes the provided encoded license and sets it as the active license. If no license was provided, a 400 is returned. If the license cannot be decoded, or cannot be applied, a 409 is returned. Some possible reasons a license may not be applied include:   - It is for a different product - It is already expired   Otherwise, if the license is updated successfully, details for the new license are returned with a 200 response.  &lt;b&gt;Warning&lt;/b&gt;: It is possible to downgrade the license during update, applying a license with a lower number of permitted users. If the number of currently-licensed users exceeds the limits of the new license, pushing will be disabled until the licensed user count is brought into compliance with the new license.  The authenticated user must have &lt;b&gt;SYS_ADMIN&lt;/b&gt; permission. &lt;b&gt;ADMIN&lt;/b&gt; users may &lt;i&gt;view&lt;/i&gt; the current license details, but they may not &lt;i&gt;update&lt;/i&gt; the license.
     * <p><b>200</b> - The newly-installed license.
     * <p><b>400</b> - No encoded license was provided in the JSON body for the POST.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the license.
     * <p><b>409</b> - The encoded license could not be decoded, or it is not valid for use on this server. For example, it may be for a different product, or it may have already expired.
     * @param restBitbucketLicense a JSON payload containing the encoded license to apply (optional)
     * @return ResponseEntity&lt;RestBitbucketLicense&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBitbucketLicense> updateLicenseWithHttpInfo(RestBitbucketLicense restBitbucketLicense) throws RestClientException {
        Object localVarPostBody = restBitbucketLicense;
        

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

        ParameterizedTypeReference<RestBitbucketLicense> localReturnType = new ParameterizedTypeReference<RestBitbucketLicense>() {};
        return apiClient.invokeAPI("/api/latest/admin/license", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update Mesh node
     * Update a Mesh node.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The updated Mesh node.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param id The ID of the Mesh node to update. (required)
     * @param restMeshNode The request specifying the updated Mesh node. (optional)
     * @return RestMeshNode
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMeshNode updateMeshNode(String id, RestMeshNode restMeshNode) throws RestClientException {
        return updateMeshNodeWithHttpInfo(id, restMeshNode).getBody();
    }

    /**
     * Update Mesh node
     * Update a Mesh node.  The authenticated user must have **SYS_ADMIN** permission.
     * <p><b>200</b> - The updated Mesh node.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to call this resource.
     * @param id The ID of the Mesh node to update. (required)
     * @param restMeshNode The request specifying the updated Mesh node. (optional)
     * @return ResponseEntity&lt;RestMeshNode&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMeshNode> updateMeshNodeWithHttpInfo(String id, RestMeshNode restMeshNode) throws RestClientException {
        Object localVarPostBody = restMeshNode;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateMeshNode");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<RestMeshNode> localReturnType = new ParameterizedTypeReference<RestMeshNode>() {};
        return apiClient.invokeAPI("/api/latest/admin/git/mesh/nodes/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user settings
     * Update the entries of a map of user setting key/values for a specific user identified by the user slug.
     * <p><b>204</b> - The UserSettings were updated successfully
     * <p><b>401</b> - The currently authenticated user is not a project administrator.
     * @param userSlug The user slug. (required)
     * @param exampleSettingsMap A map with the UserSettings entries which must be updated. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSettings(String userSlug, ExampleSettingsMap exampleSettingsMap) throws RestClientException {
        updateSettingsWithHttpInfo(userSlug, exampleSettingsMap);
    }

    /**
     * Update user settings
     * Update the entries of a map of user setting key/values for a specific user identified by the user slug.
     * <p><b>204</b> - The UserSettings were updated successfully
     * <p><b>401</b> - The currently authenticated user is not a project administrator.
     * @param userSlug The user slug. (required)
     * @param exampleSettingsMap A map with the UserSettings entries which must be updated. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSettingsWithHttpInfo(String userSlug, ExampleSettingsMap exampleSettingsMap) throws RestClientException {
        Object localVarPostBody = exampleSettingsMap;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling updateSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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
        return apiClient.invokeAPI("/api/latest/users/{userSlug}/settings", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user details
     * Update the currently authenticated user&#39;s details. The update will always be applied to the currently authenticated user.
     * <p><b>200</b> - The updated user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param userUpdate The user update details (optional)
     * @return RestApplicationUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestApplicationUser updateUserDetails1(UserUpdate userUpdate) throws RestClientException {
        return updateUserDetails1WithHttpInfo(userUpdate).getBody();
    }

    /**
     * Update user details
     * Update the currently authenticated user&#39;s details. The update will always be applied to the currently authenticated user.
     * <p><b>200</b> - The updated user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param userUpdate The user update details (optional)
     * @return ResponseEntity&lt;RestApplicationUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestApplicationUser> updateUserDetails1WithHttpInfo(UserUpdate userUpdate) throws RestClientException {
        Object localVarPostBody = userUpdate;
        

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

        ParameterizedTypeReference<RestApplicationUser> localReturnType = new ParameterizedTypeReference<RestApplicationUser>() {};
        return apiClient.invokeAPI("/api/latest/users", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set password
     * Update the currently authenticated user&#39;s password.
     * <p><b>204</b> - The user&#39;s password was successfully updated.
     * <p><b>400</b> - The request was malformed or the old password was incorrect.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param userPasswordUpdate The password update details (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateUserPassword1(UserPasswordUpdate userPasswordUpdate) throws RestClientException {
        updateUserPassword1WithHttpInfo(userPasswordUpdate);
    }

    /**
     * Set password
     * Update the currently authenticated user&#39;s password.
     * <p><b>204</b> - The user&#39;s password was successfully updated.
     * <p><b>400</b> - The request was malformed or the old password was incorrect.
     * <p><b>401</b> - Authentication failed or was not attempted.
     * @param userPasswordUpdate The password update details (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateUserPassword1WithHttpInfo(UserPasswordUpdate userPasswordUpdate) throws RestClientException {
        Object localVarPostBody = userPasswordUpdate;
        

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
        return apiClient.invokeAPI("/api/latest/users/credentials", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user avatar
     * Update the avatar for the user with the supplied &lt;strong&gt;slug&lt;/strong&gt;.   This resource accepts POST multipart form data, containing a single image in a form-field named &#39;avatar&#39;.   There are configurable server limits on both the dimensions (1024x1024 pixels by default) and uploaded file size (1MB by default). Several different image formats are supported, but &lt;strong&gt;PNG&lt;/strong&gt; and &lt;strong&gt;JPEG&lt;/strong&gt; are preferred due to the file size limit.   This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to upload an image name &#39;avatar.png&#39; would be: &#x60;&#x60;&#x60; curl -X POST -u username:password -H \&quot;X-Atlassian-Token: no-check\&quot; http://example.com/rest/api/latest/users/jdoe/avatar.png -F avatar&#x3D;@avatar.png &#x60;&#x60;&#x60;   Users are always allowed to update their own avatar. To update someone else&#39;s avatar the authenticated user must have global &lt;strong&gt;ADMIN&lt;/strong&gt; permission, or global &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to update a &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; user&#39;s avatar.
     * <p><b>201</b> - The avatar was uploaded successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the avatar.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @param xAtlassianToken This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;. (optional)
     * @param avatar The avatar file to upload. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void uploadAvatar1(String userSlug, String xAtlassianToken, File avatar) throws RestClientException {
        uploadAvatar1WithHttpInfo(userSlug, xAtlassianToken, avatar);
    }

    /**
     * Update user avatar
     * Update the avatar for the user with the supplied &lt;strong&gt;slug&lt;/strong&gt;.   This resource accepts POST multipart form data, containing a single image in a form-field named &#39;avatar&#39;.   There are configurable server limits on both the dimensions (1024x1024 pixels by default) and uploaded file size (1MB by default). Several different image formats are supported, but &lt;strong&gt;PNG&lt;/strong&gt; and &lt;strong&gt;JPEG&lt;/strong&gt; are preferred due to the file size limit.   This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;.   An example &lt;a href&#x3D;\&quot;http://curl.haxx.se/\&quot;&gt;curl&lt;/a&gt; request to upload an image name &#39;avatar.png&#39; would be: &#x60;&#x60;&#x60; curl -X POST -u username:password -H \&quot;X-Atlassian-Token: no-check\&quot; http://example.com/rest/api/latest/users/jdoe/avatar.png -F avatar&#x3D;@avatar.png &#x60;&#x60;&#x60;   Users are always allowed to update their own avatar. To update someone else&#39;s avatar the authenticated user must have global &lt;strong&gt;ADMIN&lt;/strong&gt; permission, or global &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; permission to update a &lt;strong&gt;SYS_ADMIN&lt;/strong&gt; user&#39;s avatar.
     * <p><b>201</b> - The avatar was uploaded successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to update the avatar.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug (required)
     * @param xAtlassianToken This resource has Cross-Site Request Forgery (XSRF) protection. To allow the request to pass the XSRF check the caller needs to send an &lt;code&gt;X-Atlassian-Token&lt;/code&gt; HTTP header with the value &lt;code&gt;no-check&lt;/code&gt;. (optional)
     * @param avatar The avatar file to upload. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> uploadAvatar1WithHttpInfo(String userSlug, String xAtlassianToken, File avatar) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling uploadAvatar1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (xAtlassianToken != null)
        localVarHeaderParams.add("X-Atlassian-Token", apiClient.parameterToString(xAtlassianToken));

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
        return apiClient.invokeAPI("/api/latest/users/{userSlug}/avatar.png", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
