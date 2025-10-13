package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetKeysForUser200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetRepositoriesRecentlyAccessed200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestGpgKey;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRepositorySelector;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSecretScanningAllowlistRule;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSecretScanningAllowlistRuleSetRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSecretScanningRule;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSecretScanningRuleSetRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.Search2200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.Search3200Response;
import java.util.Set;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.SecurityApi")
public class SecurityApi extends BaseApi {

    public SecurityApi() {
        super(new ApiClient());
    }

    @Autowired
    public SecurityApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Exempt a repo from secret scanning
     * Exempt a repository from being scanned for secrets   &lt;strong&gt;Deprecated since 8.6&lt;/strong&gt;. Exemptions are now managed by scope.  Use POST /rest/api/1.0/secret-scanning/exempt for global scope  Use POST /rest/api/1.0/projects/{projectKey}/secret-scanning/exempt for the project scope
     * <p><b>204</b> - An exempt repo was added
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning
     * <p><b>409</b> - At least one of specified repositories have already been previously made exempt.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addExemptRepo(String projectKey, String repositorySlug) throws RestClientException {
        addExemptRepoWithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Exempt a repo from secret scanning
     * Exempt a repository from being scanned for secrets   &lt;strong&gt;Deprecated since 8.6&lt;/strong&gt;. Exemptions are now managed by scope.  Use POST /rest/api/1.0/secret-scanning/exempt for global scope  Use POST /rest/api/1.0/projects/{projectKey}/secret-scanning/exempt for the project scope
     * <p><b>204</b> - An exempt repo was added
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning
     * <p><b>409</b> - At least one of specified repositories have already been previously made exempt.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addExemptRepoWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling addExemptRepo");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling addExemptRepo");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/exempt", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a GPG key
     * Add a GPG key to the authenticated user&#39;s account. Optionally, users with ADMIN and higher permissions may choose to specify the &lt;code&gt;user&lt;/code&gt; parameter to add a GPG key for another user.  Only authenticated users may call this endpoint.
     * <p><b>200</b> - Response contains the GPG key that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param user The name of the user to add a key for (optional; requires ADMIN permission or higher). (optional)
     * @param restGpgKey The request body. (optional)
     * @return RestGpgKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestGpgKey addKey(String user, RestGpgKey restGpgKey) throws RestClientException {
        return addKeyWithHttpInfo(user, restGpgKey).getBody();
    }

    /**
     * Create a GPG key
     * Add a GPG key to the authenticated user&#39;s account. Optionally, users with ADMIN and higher permissions may choose to specify the &lt;code&gt;user&lt;/code&gt; parameter to add a GPG key for another user.  Only authenticated users may call this endpoint.
     * <p><b>200</b> - Response contains the GPG key that was just created.
     * <p><b>400</b> - The request has failed validation.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param user The name of the user to add a key for (optional; requires ADMIN permission or higher). (optional)
     * @param restGpgKey The request body. (optional)
     * @return ResponseEntity&lt;RestGpgKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestGpgKey> addKeyWithHttpInfo(String user, RestGpgKey restGpgKey) throws RestClientException {
        Object localVarPostBody = restGpgKey;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "user", user));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestGpgKey> localReturnType = new ParameterizedTypeReference<RestGpgKey>() {};
        return apiClient.invokeAPI("/gpg/latest/keys", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Bulk exempt repos from secret scanning
     * Bulk exempt a  list of repositories from being scanned for secrets. User must be have global **ADMIN** permissions.
     * <p><b>204</b> - All requested repositories were made exempt
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning. No repositories were made exempt.
     * <p><b>409</b> - At least one of specified repositories have already been previously made exempt.
     * @param restRepositorySelector  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void bulkAddExemptRepositories(Set<RestRepositorySelector> restRepositorySelector) throws RestClientException {
        bulkAddExemptRepositoriesWithHttpInfo(restRepositorySelector);
    }

    /**
     * Bulk exempt repos from secret scanning
     * Bulk exempt a  list of repositories from being scanned for secrets. User must be have global **ADMIN** permissions.
     * <p><b>204</b> - All requested repositories were made exempt
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning. No repositories were made exempt.
     * <p><b>409</b> - At least one of specified repositories have already been previously made exempt.
     * @param restRepositorySelector  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> bulkAddExemptRepositoriesWithHttpInfo(Set<RestRepositorySelector> restRepositorySelector) throws RestClientException {
        Object localVarPostBody = restRepositorySelector;
        

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
        return apiClient.invokeAPI("/api/latest/secret-scanning/exempt", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Bulk exempt repos from secret scanning
     * Bulk exempt a  list of repositories from being scanned for secrets. User must be have **PROJECT ADMIN** permissions.
     * <p><b>204</b> - All requested repositories were made exempt
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning. No repositories were made exempt.
     * @param projectKey The project key. (required)
     * @param restRepositorySelector  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void bulkAddExemptRepositories1(String projectKey, Set<RestRepositorySelector> restRepositorySelector) throws RestClientException {
        bulkAddExemptRepositories1WithHttpInfo(projectKey, restRepositorySelector);
    }

    /**
     * Bulk exempt repos from secret scanning
     * Bulk exempt a  list of repositories from being scanned for secrets. User must be have **PROJECT ADMIN** permissions.
     * <p><b>204</b> - All requested repositories were made exempt
     * <p><b>401</b> - The authenticated user is not permitted to exempt a repository from secret scanning. No repositories were made exempt.
     * @param projectKey The project key. (required)
     * @param restRepositorySelector  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> bulkAddExemptRepositories1WithHttpInfo(String projectKey, Set<RestRepositorySelector> restRepositorySelector) throws RestClientException {
        Object localVarPostBody = restRepositorySelector;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling bulkAddExemptRepositories1");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/exempt", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create project secret scanning allowlist rule
     * Create a new project level secret scanning allowlist rule. Project allowlist rules are used when scanning all non exempt repositories in the provided project.  Project **Admin** is required
     * <p><b>200</b> - The created allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create project allowlist rules.
     * @param projectKey The project key. (required)
     * @param restSecretScanningAllowlistRuleSetRequest Allowlist rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule createAllowlistRule(String projectKey, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        return createAllowlistRuleWithHttpInfo(projectKey, restSecretScanningAllowlistRuleSetRequest).getBody();
    }

    /**
     * Create project secret scanning allowlist rule
     * Create a new project level secret scanning allowlist rule. Project allowlist rules are used when scanning all non exempt repositories in the provided project.  Project **Admin** is required
     * <p><b>200</b> - The created allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create project allowlist rules.
     * @param projectKey The project key. (required)
     * @param restSecretScanningAllowlistRuleSetRequest Allowlist rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> createAllowlistRuleWithHttpInfo(String projectKey, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningAllowlistRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createAllowlistRule");
        }
        
        // verify the required parameter 'restSecretScanningAllowlistRuleSetRequest' is set
        if (restSecretScanningAllowlistRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningAllowlistRuleSetRequest' when calling createAllowlistRule");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/allowlist", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create repository secret scanning allowlist rule
     * Create a new repository secret scanning allowlist rule. Repository allowlist rules are used when scanning the given repository.  Repository **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningAllowlistRuleSetRequest Allowlist rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule createAllowlistRule1(String projectKey, String repositorySlug, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        return createAllowlistRule1WithHttpInfo(projectKey, repositorySlug, restSecretScanningAllowlistRuleSetRequest).getBody();
    }

    /**
     * Create repository secret scanning allowlist rule
     * Create a new repository secret scanning allowlist rule. Repository allowlist rules are used when scanning the given repository.  Repository **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningAllowlistRuleSetRequest Allowlist rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> createAllowlistRule1WithHttpInfo(String projectKey, String repositorySlug, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningAllowlistRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createAllowlistRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createAllowlistRule1");
        }
        
        // verify the required parameter 'restSecretScanningAllowlistRuleSetRequest' is set
        if (restSecretScanningAllowlistRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningAllowlistRuleSetRequest' when calling createAllowlistRule1");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/allowlist", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create project secret scanning rule
     * Create a new project level secret scanning rule. Project rules are used when scanning all non exempt repositories in the provided project.  Project **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create project rules.
     * @param projectKey The project key. (required)
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule createRule(String projectKey, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return createRuleWithHttpInfo(projectKey, restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Create project secret scanning rule
     * Create a new project level secret scanning rule. Project rules are used when scanning all non exempt repositories in the provided project.  Project **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create project rules.
     * @param projectKey The project key. (required)
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> createRuleWithHttpInfo(String projectKey, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createRule");
        }
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling createRule");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/rules", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create repository secret scanning rule
     * Create a new repository secret scanning rule. Repository rules are used when scanning the given repository.  Repository **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule createRule1(String projectKey, String repositorySlug, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return createRule1WithHttpInfo(projectKey, repositorySlug, restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Create repository secret scanning rule
     * Create a new repository secret scanning rule. Repository rules are used when scanning the given repository.  Repository **Admin** is required
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> createRule1WithHttpInfo(String projectKey, String repositorySlug, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling createRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling createRule1");
        }
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling createRule1");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/rules", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create global secret scanning rule
     * Create a new global secret scanning rule. Global rules are used when scanning all non exempt repositories.
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create global rules
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule createRule2(RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return createRule2WithHttpInfo(restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Create global secret scanning rule
     * Create a new global secret scanning rule. Global rules are used when scanning all non exempt repositories.
     * <p><b>200</b> - The created rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to create global rules
     * @param restSecretScanningRuleSetRequest Rule to create, either the line regular expression or the path regular expression must be present (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> createRule2WithHttpInfo(RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling createRule2");
        }
        

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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/rules", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a project secret scanning allowlist rule
     * Delete a project secret scanning allowlist rule with the provided ID.  Project **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted, or not found at this location
     * <p><b>401</b> - The authenticated user is not permitted to delete project rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAllowlistRule(String projectKey, String id) throws RestClientException {
        deleteAllowlistRuleWithHttpInfo(projectKey, id);
    }

    /**
     * Delete a project secret scanning allowlist rule
     * Delete a project secret scanning allowlist rule with the provided ID.  Project **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted, or not found at this location
     * <p><b>401</b> - The authenticated user is not permitted to delete project rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAllowlistRuleWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAllowlistRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteAllowlistRule");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/allowlist/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a repository secret scanning allowlist rule
     * Delete a repository secret scanning allowlist rule with the provided ID.  Repository **Admin** is required
     * <p><b>204</b> - Empty response indicating that the allowlist rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete repository allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAllowlistRule1(String projectKey, String id, String repositorySlug) throws RestClientException {
        deleteAllowlistRule1WithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete a repository secret scanning allowlist rule
     * Delete a repository secret scanning allowlist rule with the provided ID.  Repository **Admin** is required
     * <p><b>204</b> - Empty response indicating that the allowlist rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete repository allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAllowlistRule1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteAllowlistRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteAllowlistRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteAllowlistRule1");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/allowlist/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete an exempt repository
     * Remove a repository from being exempt from secret scanning
     * <p><b>204</b> - Empty response indicating that the exempt repository was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete an exempt repository
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteExemptRepo(String projectKey, String repositorySlug) throws RestClientException {
        deleteExemptRepoWithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Delete an exempt repository
     * Remove a repository from being exempt from secret scanning
     * <p><b>204</b> - Empty response indicating that the exempt repository was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete an exempt repository
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteExemptRepoWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteExemptRepo");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteExemptRepo");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/exempt", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete all GPG keys for user
     * Delete all GPG keys for a supplied user.
     * <p><b>204</b> - The GPG keys matching the supplied &lt;strong&gt;user&lt;/strong&gt; were deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the GPG keys. This is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;.
     * @param user The username of the user to delete the keys for. If no username is specified, the GPG keys will be deleted for the currently authenticated user. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteForUser(String user) throws RestClientException {
        deleteForUserWithHttpInfo(user);
    }

    /**
     * Delete all GPG keys for user
     * Delete all GPG keys for a supplied user.
     * <p><b>204</b> - The GPG keys matching the supplied &lt;strong&gt;user&lt;/strong&gt; were deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the GPG keys. This is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;.
     * @param user The username of the user to delete the keys for. If no username is specified, the GPG keys will be deleted for the currently authenticated user. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteForUserWithHttpInfo(String user) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "user", user));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/gpg/latest/keys", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a GPG key
     * Delete the GPG key with the specified ID or Key Fingerprint.
     * <p><b>204</b> - The key has been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param fingerprintOrId The GPG fingerprint or ID. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteKey(String fingerprintOrId) throws RestClientException {
        deleteKeyWithHttpInfo(fingerprintOrId);
    }

    /**
     * Delete a GPG key
     * Delete the GPG key with the specified ID or Key Fingerprint.
     * <p><b>204</b> - The key has been deleted successfully.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param fingerprintOrId The GPG fingerprint or ID. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteKeyWithHttpInfo(String fingerprintOrId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fingerprintOrId' is set
        if (fingerprintOrId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fingerprintOrId' when calling deleteKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fingerprintOrId", fingerprintOrId);

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
        return apiClient.invokeAPI("/gpg/latest/keys/{fingerprintOrId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a project secret scanning rule
     * Delete a project secret scanning rule with the provided ID.  Project **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted, or not found at this location
     * <p><b>401</b> - The authenticated user is not permitted to delete project rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRule(String projectKey, String id) throws RestClientException {
        deleteRuleWithHttpInfo(projectKey, id);
    }

    /**
     * Delete a project secret scanning rule
     * Delete a project secret scanning rule with the provided ID.  Project **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted, or not found at this location
     * <p><b>401</b> - The authenticated user is not permitted to delete project rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRuleWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRule");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/rules/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a repository secret scanning rule
     * Delete a repository secret scanning rule with the provided ID.  Repository **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete repository rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRule1(String projectKey, String id, String repositorySlug) throws RestClientException {
        deleteRule1WithHttpInfo(projectKey, id, repositorySlug);
    }

    /**
     * Delete a repository secret scanning rule
     * Delete a repository secret scanning rule with the provided ID.  Repository **Admin** is required
     * <p><b>204</b> - Empty response indicating that the rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete repository rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRule1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteRule1");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/rules/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a global secret scanning rule
     * Delete a global secret scanning rule with the provided ID
     * <p><b>204</b> - Empty response indicating that the rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete global rules
     * @param id The rule id. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteRule2(String id) throws RestClientException {
        deleteRule2WithHttpInfo(id);
    }

    /**
     * Delete a global secret scanning rule
     * Delete a global secret scanning rule with the provided ID
     * <p><b>204</b> - Empty response indicating that the rule was deleted
     * <p><b>401</b> - The authenticated user is not permitted to delete global rules
     * @param id The rule id. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteRule2WithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRule2");
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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/rules/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit an existing project secret scanning allowlist rule
     * Edit a project secret scanning allowlist rule.  Project **Admin** is required
     * <p><b>200</b> - The updated allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to modify project allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param restSecretScanningAllowlistRuleSetRequest  (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule editAllowlistRule(String projectKey, String id, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        return editAllowlistRuleWithHttpInfo(projectKey, id, restSecretScanningAllowlistRuleSetRequest).getBody();
    }

    /**
     * Edit an existing project secret scanning allowlist rule
     * Edit a project secret scanning allowlist rule.  Project **Admin** is required
     * <p><b>200</b> - The updated allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to modify project allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param restSecretScanningAllowlistRuleSetRequest  (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> editAllowlistRuleWithHttpInfo(String projectKey, String id, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningAllowlistRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling editAllowlistRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling editAllowlistRule");
        }
        
        // verify the required parameter 'restSecretScanningAllowlistRuleSetRequest' is set
        if (restSecretScanningAllowlistRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningAllowlistRuleSetRequest' when calling editAllowlistRule");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/allowlist/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit an existing repository secret scanning allowlist rule
     * Edit a repository secret scanning allowlist rule.  Repository **Admin** is required
     * <p><b>200</b> - The updated allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to edit repository allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningAllowlistRuleSetRequest  (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule editAllowlistRule1(String projectKey, String id, String repositorySlug, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        return editAllowlistRule1WithHttpInfo(projectKey, id, repositorySlug, restSecretScanningAllowlistRuleSetRequest).getBody();
    }

    /**
     * Edit an existing repository secret scanning allowlist rule
     * Edit a repository secret scanning allowlist rule.  Repository **Admin** is required
     * <p><b>200</b> - The updated allowlist rule
     * <p><b>400</b> - The request did not contain a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to edit repository allowlist rules
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningAllowlistRuleSetRequest  (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> editAllowlistRule1WithHttpInfo(String projectKey, String id, String repositorySlug, RestSecretScanningAllowlistRuleSetRequest restSecretScanningAllowlistRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningAllowlistRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling editAllowlistRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling editAllowlistRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling editAllowlistRule1");
        }
        
        // verify the required parameter 'restSecretScanningAllowlistRuleSetRequest' is set
        if (restSecretScanningAllowlistRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningAllowlistRuleSetRequest' when calling editAllowlistRule1");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/allowlist/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit an existing project secret scanning rule
     * Edit a project secret scanning rule.  Project **Admin** is required
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to modify project rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule editRule(String projectKey, String id, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return editRuleWithHttpInfo(projectKey, id, restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Edit an existing project secret scanning rule
     * Edit a project secret scanning rule.  Project **Admin** is required
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to modify project rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> editRuleWithHttpInfo(String projectKey, String id, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling editRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling editRule");
        }
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling editRule");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/rules/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit an existing repository secret scanning rule
     * Edit a repository secret scanning rule.  Repository **Admin** is required
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to edit repository rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule editRule1(String projectKey, String id, String repositorySlug, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return editRule1WithHttpInfo(projectKey, id, repositorySlug, restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Edit an existing repository secret scanning rule
     * Edit a repository secret scanning rule.  Repository **Admin** is required
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to edit repository rules
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> editRule1WithHttpInfo(String projectKey, String id, String repositorySlug, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling editRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling editRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling editRule1");
        }
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling editRule1");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/rules/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Edit a global secret scanning rule.
     * Edit an existing global secret scanning rule
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to update global rules
     * @param id The rule id. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule editRule2(String id, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        return editRule2WithHttpInfo(id, restSecretScanningRuleSetRequest).getBody();
    }

    /**
     * Edit a global secret scanning rule.
     * Edit an existing global secret scanning rule
     * <p><b>200</b> - The updated rule
     * <p><b>400</b> - The request did not contain a correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to update global rules
     * @param id The rule id. (required)
     * @param restSecretScanningRuleSetRequest  (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> editRule2WithHttpInfo(String id, RestSecretScanningRuleSetRequest restSecretScanningRuleSetRequest) throws RestClientException {
        Object localVarPostBody = restSecretScanningRuleSetRequest;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling editRule2");
        }
        
        // verify the required parameter 'restSecretScanningRuleSetRequest' is set
        if (restSecretScanningRuleSetRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'restSecretScanningRuleSetRequest' when calling editRule2");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/rules/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find repos exempt from secret scanning for a project
     * Find repositories exempt from secret scanning in a project
     * <p><b>200</b> - Page of repositories
     * <p><b>401</b> - The authenticated user is not permitted to search exempt repositories for this project
     * @param projectKey The project key. (required)
     * @param order Order by project name followed by repository name either ascending or descending, defaults to ascending. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response findExemptReposByProject(String projectKey, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findExemptReposByProjectWithHttpInfo(projectKey, order, start, limit).getBody();
    }

    /**
     * Find repos exempt from secret scanning for a project
     * Find repositories exempt from secret scanning in a project
     * <p><b>200</b> - Page of repositories
     * <p><b>401</b> - The authenticated user is not permitted to search exempt repositories for this project
     * @param projectKey The project key. (required)
     * @param order Order by project name followed by repository name either ascending or descending, defaults to ascending. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> findExemptReposByProjectWithHttpInfo(String projectKey, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling findExemptReposByProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

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

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/exempt", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find all repos exempt from secret scan
     * Find all repositories exempt from secret scanning
     * <p><b>200</b> - Page of repositories
     * <p><b>401</b> - The authenticated user is not permitted to search exempt repositories globally
     * @param order Order by project name followed by repository name either ascending or descending, defaults to ascending. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetRepositoriesRecentlyAccessed200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetRepositoriesRecentlyAccessed200Response findExemptReposByScope(String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findExemptReposByScopeWithHttpInfo(order, start, limit).getBody();
    }

    /**
     * Find all repos exempt from secret scan
     * Find all repositories exempt from secret scanning
     * <p><b>200</b> - Page of repositories
     * <p><b>401</b> - The authenticated user is not permitted to search exempt repositories globally
     * @param order Order by project name followed by repository name either ascending or descending, defaults to ascending. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetRepositoriesRecentlyAccessed200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetRepositoriesRecentlyAccessed200Response> findExemptReposByScopeWithHttpInfo(String order, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response> localReturnType = new ParameterizedTypeReference<GetRepositoriesRecentlyAccessed200Response>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/exempt", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a project secret scanning allowlist rule
     * Get a project secret scanning allowlist rule by ID.  Project **Admin** is required
     * <p><b>200</b> - The requested allowlist rule
     * <p><b>401</b> - The authenticated user is not permitted to view project allowlist rules
     * <p><b>404</b> - The requested allowlist rules was not found
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule getAllowlistRule(String projectKey, String id) throws RestClientException {
        return getAllowlistRuleWithHttpInfo(projectKey, id).getBody();
    }

    /**
     * Get a project secret scanning allowlist rule
     * Get a project secret scanning allowlist rule by ID.  Project **Admin** is required
     * <p><b>200</b> - The requested allowlist rule
     * <p><b>401</b> - The authenticated user is not permitted to view project allowlist rules
     * <p><b>404</b> - The requested allowlist rules was not found
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> getAllowlistRuleWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAllowlistRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAllowlistRule");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/allowlist/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a repository secret scanning allowlist rule
     * Get a repository secret scanning allowlist rule by ID.  Repository **Admin** is required
     * <p><b>200</b> - The requested allowlist rule
     * <p><b>401</b> - The authenticated user is not permitted to view repository allowlist rules
     * <p><b>404</b> - The requested allowlist rule was not found
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestSecretScanningAllowlistRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningAllowlistRule getAllowlistRule1(String projectKey, String id, String repositorySlug) throws RestClientException {
        return getAllowlistRule1WithHttpInfo(projectKey, id, repositorySlug).getBody();
    }

    /**
     * Get a repository secret scanning allowlist rule
     * Get a repository secret scanning allowlist rule by ID.  Repository **Admin** is required
     * <p><b>200</b> - The requested allowlist rule
     * <p><b>401</b> - The authenticated user is not permitted to view repository allowlist rules
     * <p><b>404</b> - The requested allowlist rule was not found
     * @param projectKey The project key. (required)
     * @param id The allowlist rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestSecretScanningAllowlistRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningAllowlistRule> getAllowlistRule1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAllowlistRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAllowlistRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAllowlistRule1");
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

        ParameterizedTypeReference<RestSecretScanningAllowlistRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningAllowlistRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/allowlist/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get all GPG keys
     * Find all the keys for the currently authenticated user. Optionally, users with ADMIN and higher permissions may choose to specify the &lt;code&gt;user&lt;/code&gt; parameter to retrieve GPG keys for another user.  Only authenticated users may call this endpoint.
     * <p><b>200</b> - Returns a paged response of of keys for the user.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param user The name of the user to get keys for (optional; requires ADMIN permission or higher). (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetKeysForUser200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetKeysForUser200Response getKeysForUser(String user, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getKeysForUserWithHttpInfo(user, start, limit).getBody();
    }

    /**
     * Get all GPG keys
     * Find all the keys for the currently authenticated user. Optionally, users with ADMIN and higher permissions may choose to specify the &lt;code&gt;user&lt;/code&gt; parameter to retrieve GPG keys for another user.  Only authenticated users may call this endpoint.
     * <p><b>200</b> - Returns a paged response of of keys for the user.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to perform this operation.
     * @param user The name of the user to get keys for (optional; requires ADMIN permission or higher). (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetKeysForUser200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetKeysForUser200Response> getKeysForUserWithHttpInfo(String user, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "user", user));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<GetKeysForUser200Response> localReturnType = new ParameterizedTypeReference<GetKeysForUser200Response>() {};
        return apiClient.invokeAPI("/gpg/latest/keys", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a project secret scanning rule
     * Get a project secret scanning rule by ID.  Project **Admin** is required
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to view project rules
     * <p><b>404</b> - The requested rules was not found
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule getRule(String projectKey, String id) throws RestClientException {
        return getRuleWithHttpInfo(projectKey, id).getBody();
    }

    /**
     * Get a project secret scanning rule
     * Get a project secret scanning rule by ID.  Project **Admin** is required
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to view project rules
     * <p><b>404</b> - The requested rules was not found
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> getRuleWithHttpInfo(String projectKey, String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRule");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRule");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/rules/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a repository secret scanning rule
     * Get a repository secret scanning rule by ID.  Repository **Admin** is required
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * <p><b>404</b> - The requested rule was not found
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule getRule1(String projectKey, String id, String repositorySlug) throws RestClientException {
        return getRule1WithHttpInfo(projectKey, id, repositorySlug).getBody();
    }

    /**
     * Get a repository secret scanning rule
     * Get a repository secret scanning rule by ID.  Repository **Admin** is required
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * <p><b>404</b> - The requested rule was not found
     * @param projectKey The project key. (required)
     * @param id The rule id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> getRule1WithHttpInfo(String projectKey, String id, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getRule1");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRule1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getRule1");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/rules/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a global secret scanning rule
     * Get a global secret scanning rule by ID.
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to get global rules
     * <p><b>404</b> - The requested rule was not found
     * @param id The rule id. (required)
     * @return RestSecretScanningRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSecretScanningRule getRule2(String id) throws RestClientException {
        return getRule2WithHttpInfo(id).getBody();
    }

    /**
     * Get a global secret scanning rule
     * Get a global secret scanning rule by ID.
     * <p><b>200</b> - The requested rule
     * <p><b>401</b> - The authenticated user is not permitted to get global rules
     * <p><b>404</b> - The requested rule was not found
     * @param id The rule id. (required)
     * @return ResponseEntity&lt;RestSecretScanningRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSecretScanningRule> getRule2WithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRule2");
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

        ParameterizedTypeReference<RestSecretScanningRule> localReturnType = new ParameterizedTypeReference<RestSecretScanningRule>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/rules/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get whether a repository is exempt
     * Check whether a repository is exempt from secret scanning
     * <p><b>200</b> - True if the repository is exempt from secret scanning, false otherwise
     * <p><b>401</b> - The authenticated user is not permitted to check whether a repository is exempt from secret scanning
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void isRepoExempt(String projectKey, String repositorySlug) throws RestClientException {
        isRepoExemptWithHttpInfo(projectKey, repositorySlug);
    }

    /**
     * Get whether a repository is exempt
     * Check whether a repository is exempt from secret scanning
     * <p><b>200</b> - True if the repository is exempt from secret scanning, false otherwise
     * <p><b>401</b> - The authenticated user is not permitted to check whether a repository is exempt from secret scanning
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> isRepoExemptWithHttpInfo(String projectKey, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling isRepoExempt");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling isRepoExempt");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/exempt", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find project secret scanning rules
     * Find project secret scanning rules by filtering.  Project **Admin** is required
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request was not correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view project rules
     * @param projectKey The project key. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return Search3200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Search3200Response search1(String projectKey, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return search1WithHttpInfo(projectKey, filter, order, start, limit).getBody();
    }

    /**
     * Find project secret scanning rules
     * Find project secret scanning rules by filtering.  Project **Admin** is required
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request was not correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view project rules
     * @param projectKey The project key. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Search3200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Search3200Response> search1WithHttpInfo(String projectKey, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling search1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
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

        ParameterizedTypeReference<Search3200Response> localReturnType = new ParameterizedTypeReference<Search3200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/rules", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find repository secret scanning allowlist rules
     * Find repository secret scanning allowlist rules by filtering.  Repository **Admin** is required
     * <p><b>200</b> - Page of allowlist rules
     * <p><b>400</b> - The request was not a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return Search2200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Search2200Response search2(String projectKey, String repositorySlug, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return search2WithHttpInfo(projectKey, repositorySlug, filter, order, start, limit).getBody();
    }

    /**
     * Find repository secret scanning allowlist rules
     * Find repository secret scanning allowlist rules by filtering.  Repository **Admin** is required
     * <p><b>200</b> - Page of allowlist rules
     * <p><b>400</b> - The request was not a correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Search2200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Search2200Response> search2WithHttpInfo(String projectKey, String repositorySlug, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling search2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling search2");
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

        ParameterizedTypeReference<Search2200Response> localReturnType = new ParameterizedTypeReference<Search2200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/allowlist", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find repository secret scanning rules
     * Find repository secret scanning rules by filtering.  Repository **Admin** is required
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request was not correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return Search3200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Search3200Response search3(String projectKey, String repositorySlug, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return search3WithHttpInfo(projectKey, repositorySlug, filter, order, start, limit).getBody();
    }

    /**
     * Find repository secret scanning rules
     * Find repository secret scanning rules by filtering.  Repository **Admin** is required
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request was not correctly formed rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view repository rules
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Search3200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Search3200Response> search3WithHttpInfo(String projectKey, String repositorySlug, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling search3");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling search3");
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

        ParameterizedTypeReference<Search3200Response> localReturnType = new ParameterizedTypeReference<Search3200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/secret-scanning/rules", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find global secret scanning rules
     * Find global secret scanning rules by filtering.
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request did not contain a correctly formed search request, see returned error for more details.
     * <p><b>401</b> - The authenticated user is not permitted to search global rules
     * @param filter Filter by rule name (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return Search3200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Search3200Response search4(String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return search4WithHttpInfo(filter, order, start, limit).getBody();
    }

    /**
     * Find global secret scanning rules
     * Find global secret scanning rules by filtering.
     * <p><b>200</b> - Page of rules
     * <p><b>400</b> - The request did not contain a correctly formed search request, see returned error for more details.
     * <p><b>401</b> - The authenticated user is not permitted to search global rules
     * @param filter Filter by rule name (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Search3200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Search3200Response> search4WithHttpInfo(String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
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

        ParameterizedTypeReference<Search3200Response> localReturnType = new ParameterizedTypeReference<Search3200Response>() {};
        return apiClient.invokeAPI("/api/latest/secret-scanning/rules", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find project secret scanning allowlist rules
     * Find project secret scanning allowlist rules by filtering.  Project **Admin** is required
     * <p><b>200</b> - Page of allowlist rules
     * <p><b>400</b> - The request was not correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view project allowlist rules
     * @param projectKey The project key. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return Search2200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Search2200Response searchAllowlistRule(String projectKey, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        return searchAllowlistRuleWithHttpInfo(projectKey, filter, order, start, limit).getBody();
    }

    /**
     * Find project secret scanning allowlist rules
     * Find project secret scanning allowlist rules by filtering.  Project **Admin** is required
     * <p><b>200</b> - Page of allowlist rules
     * <p><b>400</b> - The request was not correctly formed allowlist rule. See returned error for more details
     * <p><b>401</b> - The authenticated user is not permitted to view project allowlist rules
     * @param projectKey The project key. (required)
     * @param filter Filter names by the provided text (optional)
     * @param order Order by (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;Search2200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Search2200Response> searchAllowlistRuleWithHttpInfo(String projectKey, String filter, String order, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling searchAllowlistRule");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
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

        ParameterizedTypeReference<Search2200Response> localReturnType = new ParameterizedTypeReference<Search2200Response>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/secret-scanning/allowlist", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
