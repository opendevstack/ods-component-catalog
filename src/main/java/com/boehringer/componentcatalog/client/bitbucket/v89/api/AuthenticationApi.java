package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.AddSshKeyRequest;
import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetForRepository1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetSshKeys200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAccessToken;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestAccessTokenRequest;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestRawAccessToken;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSshAccessKey;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSshKey;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestSshSettings;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RevokeManyRequest;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.AuthenticationApi")
public class AuthenticationApi extends BaseApi {

    public AuthenticationApi() {
        super(new ApiClient());
    }

    @Autowired
    public AuthenticationApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Add project SSH key
     * Register a new SSH key and grants access to the project identified in the URL.
     * <p><b>201</b> - The newly created access key.
     * <p><b>400</b> - The current request contains invalid or missing values.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to add an access key to the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param restSshAccessKey  (optional)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey addForProject(String projectKey, RestSshAccessKey restSshAccessKey) throws RestClientException {
        return addForProjectWithHttpInfo(projectKey, restSshAccessKey).getBody();
    }

    /**
     * Add project SSH key
     * Register a new SSH key and grants access to the project identified in the URL.
     * <p><b>201</b> - The newly created access key.
     * <p><b>400</b> - The current request contains invalid or missing values.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to add an access key to the project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param restSshAccessKey  (optional)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> addForProjectWithHttpInfo(String projectKey, RestSshAccessKey restSshAccessKey) throws RestClientException {
        Object localVarPostBody = restSshAccessKey;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling addForProject");
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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/ssh", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add repository SSH key
     * Register a new SSH key and grants access to the repository identified in the URL.
     * <p><b>201</b> - The newly created access key.
     * <p><b>400</b> - The current request contains invalid or missing values.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to add an access key to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param restSshAccessKey  (optional)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey addForRepository(String projectKey, String repositorySlug, RestSshAccessKey restSshAccessKey) throws RestClientException {
        return addForRepositoryWithHttpInfo(projectKey, repositorySlug, restSshAccessKey).getBody();
    }

    /**
     * Add repository SSH key
     * Register a new SSH key and grants access to the repository identified in the URL.
     * <p><b>201</b> - The newly created access key.
     * <p><b>400</b> - The current request contains invalid or missing values.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to add an access key to the repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param repositorySlug The repository slug (required)
     * @param restSshAccessKey  (optional)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> addForRepositoryWithHttpInfo(String projectKey, String repositorySlug, RestSshAccessKey restSshAccessKey) throws RestClientException {
        Object localVarPostBody = restSshAccessKey;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling addForRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling addForRepository");
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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/repos/{repositorySlug}/ssh", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add SSH key for user
     * Add a new SSH key to a supplied user.
     * <p><b>201</b> - The newly created SSH key.
     * <p><b>400</b> - The SSH key was not created because the key was not a valid RSA/DSA/ECDSA/Ed25519 key of a supported length.
     * <p><b>401</b> - Either there is no authenticated user or the currently authenticated user has insufficient permissions to add an SSH key. The latter is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * <p><b>409</b> - The SSH key already exists on the system.
     * @param user the username of the user to add the SSH key for. If no username is specified, the SSH key will be added for the current authenticated user. (optional)
     * @param addSshKeyRequest  (optional)
     * @return RestSshKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshKey addSshKey(RestSshKey user, AddSshKeyRequest addSshKeyRequest) throws RestClientException {
        return addSshKeyWithHttpInfo(user, addSshKeyRequest).getBody();
    }

    /**
     * Add SSH key for user
     * Add a new SSH key to a supplied user.
     * <p><b>201</b> - The newly created SSH key.
     * <p><b>400</b> - The SSH key was not created because the key was not a valid RSA/DSA/ECDSA/Ed25519 key of a supported length.
     * <p><b>401</b> - Either there is no authenticated user or the currently authenticated user has insufficient permissions to add an SSH key. The latter is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * <p><b>409</b> - The SSH key already exists on the system.
     * @param user the username of the user to add the SSH key for. If no username is specified, the SSH key will be added for the current authenticated user. (optional)
     * @param addSshKeyRequest  (optional)
     * @return ResponseEntity&lt;RestSshKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshKey> addSshKeyWithHttpInfo(RestSshKey user, AddSshKeyRequest addSshKeyRequest) throws RestClientException {
        Object localVarPostBody = addSshKeyRequest;
        

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

        ParameterizedTypeReference<RestSshKey> localReturnType = new ParameterizedTypeReference<RestSshKey>() {};
        return apiClient.invokeAPI("/ssh/latest/keys", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create project HTTP token
     * Create an access token for the project according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name - The request does not contain a list of permissions, or the list of permissions is empty - One of the provided permission levels are unknown - The project already has the maximum number of tokens 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token for this project or authentication failed.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return RestRawAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRawAccessToken create1(String projectKey, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return create1WithHttpInfo(projectKey, restAccessTokenRequest).getBody();
    }

    /**
     * Create project HTTP token
     * Create an access token for the project according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name - The request does not contain a list of permissions, or the list of permissions is empty - One of the provided permission levels are unknown - The project already has the maximum number of tokens 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token for this project or authentication failed.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return ResponseEntity&lt;RestRawAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRawAccessToken> create1WithHttpInfo(String projectKey, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling create1");
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

        ParameterizedTypeReference<RestRawAccessToken> localReturnType = new ParameterizedTypeReference<RestRawAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create repository HTTP token
     * Create an access token for the repository according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name- The request does not contain a list of permissions, or the list of permissions is empty- One of the provided permission levels are unknown- The repository already has the maximum number of tokens
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token for this repository or authentication failed.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return RestRawAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRawAccessToken create2(String projectKey, String repositorySlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return create2WithHttpInfo(projectKey, repositorySlug, restAccessTokenRequest).getBody();
    }

    /**
     * Create repository HTTP token
     * Create an access token for the repository according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name- The request does not contain a list of permissions, or the list of permissions is empty- One of the provided permission levels are unknown- The repository already has the maximum number of tokens
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token for this repository or authentication failed.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return ResponseEntity&lt;RestRawAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRawAccessToken> create2WithHttpInfo(String projectKey, String repositorySlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling create2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling create2");
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

        ParameterizedTypeReference<RestRawAccessToken> localReturnType = new ParameterizedTypeReference<RestRawAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create personal HTTP token
     * Create an access token for the user according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name - The request does not contain a list of permissions, or the list of permissions is empty - One of the provided permission levels are unknown - The user already has their maximum number of tokens 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token on behalf of this user or authentication failed
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return RestRawAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestRawAccessToken create3(String userSlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return create3WithHttpInfo(userSlug, restAccessTokenRequest).getBody();
    }

    /**
     * Create personal HTTP token
     * Create an access token for the user according to the given request.
     * <p><b>200</b> - A response containing the raw access token and associated details.
     * <p><b>400</b> - One of the following error cases occurred (check the error message for more details).  - The request does not contain a token name - The request does not contain a list of permissions, or the list of permissions is empty - One of the provided permission levels are unknown - The user already has their maximum number of tokens 
     * <p><b>401</b> - The currently authenticated user is not permitted to create an access token on behalf of this user or authentication failed
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to create. (optional)
     * @return ResponseEntity&lt;RestRawAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestRawAccessToken> create3WithHttpInfo(String userSlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling create3");
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

        ParameterizedTypeReference<RestRawAccessToken> localReturnType = new ParameterizedTypeReference<RestRawAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/users/{userSlug}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteById(String projectKey, String tokenId) throws RestClientException {
        deleteByIdWithHttpInfo(projectKey, tokenId);
    }

    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteByIdWithHttpInfo(String projectKey, String tokenId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteById");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling deleteById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);

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
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/{tokenId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteById1(String projectKey, String tokenId, String repositorySlug) throws RestClientException {
        deleteById1WithHttpInfo(projectKey, tokenId, repositorySlug);
    }

    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteById1WithHttpInfo(String projectKey, String tokenId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling deleteById1");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling deleteById1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling deleteById1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);
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
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/repos/{repositorySlug}/{tokenId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteById2(String tokenId, String userSlug) throws RestClientException {
        deleteById2WithHttpInfo(tokenId, userSlug);
    }

    /**
     * Delete a HTTP token
     * Delete the access token identified by the given ID.
     * <p><b>204</b> - An empty response indicating that the token has been deleted.
     * <p><b>401</b> - The currently authenticated user is not permitted to delete an access token on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteById2WithHttpInfo(String tokenId, String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling deleteById2");
        }
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling deleteById2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("tokenId", tokenId);
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
        return apiClient.invokeAPI("/access-tokens/latest/users/{userSlug}/{tokenId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove SSH key
     * Delete an SSH key.
     * <p><b>204</b> - The SSH key matching the supplied &lt;strong&gt;id&lt;/strong&gt; was deleted or did not exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the SSH key.
     * @param keyId the id of the key to delete. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteSshKey(String keyId) throws RestClientException {
        deleteSshKeyWithHttpInfo(keyId);
    }

    /**
     * Remove SSH key
     * Delete an SSH key.
     * <p><b>204</b> - The SSH key matching the supplied &lt;strong&gt;id&lt;/strong&gt; was deleted or did not exist.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the SSH key.
     * @param keyId the id of the key to delete. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteSshKeyWithHttpInfo(String keyId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling deleteSshKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("keyId", keyId);

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
        return apiClient.invokeAPI("/ssh/latest/keys/{keyId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete all user SSH key
     * Delete all SSH keys for a supplied user.
     * <p><b>204</b> - The SSH keys matching the supplied &lt;strong&gt;user&lt;/strong&gt; were deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the SSH keys. This is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * @param userName the username of the user to delete the keys for. If no username is specified, the SSH keys will be deleted for the current authenticated user. (optional)
     * @param user  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteSshKeys(String userName, String user) throws RestClientException {
        deleteSshKeysWithHttpInfo(userName, user);
    }

    /**
     * Delete all user SSH key
     * Delete all SSH keys for a supplied user.
     * <p><b>204</b> - The SSH keys matching the supplied &lt;strong&gt;user&lt;/strong&gt; were deleted.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to delete the SSH keys. This is only possible when a &lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * @param userName the username of the user to delete the keys for. If no username is specified, the SSH keys will be deleted for the current authenticated user. (optional)
     * @param user  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteSshKeysWithHttpInfo(String userName, String user) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "userName", userName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "user", user));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/ssh/latest/keys", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get project HTTP tokens
     * Get all access tokens associated with the given project.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens for this project or authentication failed.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetAll200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetAll200Response getAll(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getAllWithHttpInfo(projectKey, start, limit).getBody();
    }

    /**
     * Get project HTTP tokens
     * Get all access tokens associated with the given project.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens for this project or authentication failed.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetAll200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetAll200Response> getAllWithHttpInfo(String projectKey, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAll");
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

        ParameterizedTypeReference<GetAll200Response> localReturnType = new ParameterizedTypeReference<GetAll200Response>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository HTTP tokens
     * Get all access tokens associated with the given repository.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens for this repository or authentication failed.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetAll200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetAll200Response getAll1(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getAll1WithHttpInfo(projectKey, repositorySlug, start, limit).getBody();
    }

    /**
     * Get repository HTTP tokens
     * Get all access tokens associated with the given repository.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens for this repository or authentication failed.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetAll200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetAll200Response> getAll1WithHttpInfo(String projectKey, String repositorySlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getAll1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getAll1");
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

        ParameterizedTypeReference<GetAll200Response> localReturnType = new ParameterizedTypeReference<GetAll200Response>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/repos/{repositorySlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get personal HTTP tokens
     * Get all access tokens associated with the given user.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetAll200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetAll200Response getAll2(String userSlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getAll2WithHttpInfo(userSlug, start, limit).getBody();
    }

    /**
     * Get personal HTTP tokens
     * Get all access tokens associated with the given user.
     * <p><b>200</b> - A response containing a page of access tokens and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user does not exist.
     * @param userSlug The user slug. (required)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetAll200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetAll200Response> getAll2WithHttpInfo(String userSlug, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling getAll2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userSlug", userSlug);

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

        ParameterizedTypeReference<GetAll200Response> localReturnType = new ParameterizedTypeReference<GetAll200Response>() {};
        return apiClient.invokeAPI("/access-tokens/latest/users/{userSlug}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken getById(String projectKey, String tokenId) throws RestClientException {
        return getByIdWithHttpInfo(projectKey, tokenId).getBody();
    }

    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> getByIdWithHttpInfo(String projectKey, String tokenId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getById");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling getById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);

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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/{tokenId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken getById1(String projectKey, String tokenId, String repositorySlug) throws RestClientException {
        return getById1WithHttpInfo(projectKey, tokenId, repositorySlug).getBody();
    }

    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> getById1WithHttpInfo(String projectKey, String tokenId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getById1");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling getById1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getById1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);
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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/repos/{repositorySlug}/{tokenId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken getById2(String tokenId, String userSlug) throws RestClientException {
        return getById2WithHttpInfo(tokenId, userSlug).getBody();
    }

    /**
     * Get HTTP token by ID
     * Get the access token identified by the given ID.
     * <p><b>200</b> - A response containing the access token and associated details.
     * <p><b>401</b> - The currently authenticated user is not permitted to get access tokens on behalf of this user or authentication failed.
     * <p><b>404</b> - The specified user or token does not exist.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> getById2WithHttpInfo(String tokenId, String userSlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling getById2");
        }
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling getById2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("tokenId", tokenId);
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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/users/{userSlug}/{tokenId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get project SSH key
     * Retrieves the access key for the SSH key with id &lt;code&gt;keyId&lt;/code&gt; on the project identified in the URL.
     * <p><b>200</b> - The access key for the repository and SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository.
     * <p><b>404</b> - The specified repository or key does not exist or the key does not have access on the repository.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey getForProject1(String projectKey, String keyId) throws RestClientException {
        return getForProject1WithHttpInfo(projectKey, keyId).getBody();
    }

    /**
     * Get project SSH key
     * Retrieves the access key for the SSH key with id &lt;code&gt;keyId&lt;/code&gt; on the project identified in the URL.
     * <p><b>200</b> - The access key for the repository and SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository.
     * <p><b>404</b> - The specified repository or key does not exist or the key does not have access on the repository.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> getForProject1WithHttpInfo(String projectKey, String keyId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getForProject1");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling getForProject1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);

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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/ssh/{keyId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get project SSH keys
     * Retrieves all project-related access keys for the SSH key with id &lt;code&gt;keyId&lt;/code&gt;. If the current user is not an admin any of the projects the key provides access to, none are returned.
     * <p><b>200</b> - The SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>404</b> - The specified key does not exist
     * @param keyId  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getForProjects(Integer keyId) throws RestClientException {
        getForProjectsWithHttpInfo(keyId);
    }

    /**
     * Get project SSH keys
     * Retrieves all project-related access keys for the SSH key with id &lt;code&gt;keyId&lt;/code&gt;. If the current user is not an admin any of the projects the key provides access to, none are returned.
     * <p><b>200</b> - The SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>404</b> - The specified key does not exist
     * @param keyId  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getForProjectsWithHttpInfo(Integer keyId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling getForProjects");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("keyId", keyId);

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
        return apiClient.invokeAPI("/keys/latest/ssh/{keyId}/projects", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository SSH key
     * Retrieves all repository-related access keys for the SSH key with id &lt;code&gt;keyId&lt;/code&gt;. If the current user is not an admin of any of the projects the key provides access to, none are returned.
     * <p><b>200</b> - The SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>404</b> - The specified key does not exist.
     * @param keyId The key id (required)
     * @param withRestrictions Include the readOnly field. The &#x60;readOnly&#x60; field is contextual for the user making the request. &#x60;readOnly&#x60; returns true if there is a restriction and the user does not have&#x60;PROJECT_ADMIN&#x60; access for the repository the key is associated with. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getForRepositories(String keyId, String withRestrictions) throws RestClientException {
        getForRepositoriesWithHttpInfo(keyId, withRestrictions);
    }

    /**
     * Get repository SSH key
     * Retrieves all repository-related access keys for the SSH key with id &lt;code&gt;keyId&lt;/code&gt;. If the current user is not an admin of any of the projects the key provides access to, none are returned.
     * <p><b>200</b> - The SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>404</b> - The specified key does not exist.
     * @param keyId The key id (required)
     * @param withRestrictions Include the readOnly field. The &#x60;readOnly&#x60; field is contextual for the user making the request. &#x60;readOnly&#x60; returns true if there is a restriction and the user does not have&#x60;PROJECT_ADMIN&#x60; access for the repository the key is associated with. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getForRepositoriesWithHttpInfo(String keyId, String withRestrictions) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling getForRepositories");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("keyId", keyId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRestrictions", withRestrictions));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/keys/latest/ssh/{keyId}/repos", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository SSH key
     * Retrieves the access key for the SSH key with id &lt;code&gt;keyId&lt;/code&gt; on the repository identified in the URL.
     * <p><b>200</b> - The access key for the repository and SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository.
     * <p><b>404</b> - The specified repository or key does not exist or the key does not have access on the repository.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @param repositorySlug The repository slug (required)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey getForRepository(String projectKey, String keyId, String repositorySlug) throws RestClientException {
        return getForRepositoryWithHttpInfo(projectKey, keyId, repositorySlug).getBody();
    }

    /**
     * Get repository SSH key
     * Retrieves the access key for the SSH key with id &lt;code&gt;keyId&lt;/code&gt; on the repository identified in the URL.
     * <p><b>200</b> - The access key for the repository and SSH key with ID &lt;code&gt;keyId&lt;/code&gt;.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository.
     * <p><b>404</b> - The specified repository or key does not exist or the key does not have access on the repository.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> getForRepositoryWithHttpInfo(String projectKey, String keyId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getForRepository");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling getForRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getForRepository");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);
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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/repos/{repositorySlug}/ssh/{keyId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get repository SSH keys
     * Retrieves the access keys for the repository identified in the URL.
     * <p><b>200</b> - A single page of access keys for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only SSH access keys with a label prefixed with the supplied string will be returned (optional)
     * @param effective Controls whether SSH access keys configured at the project level should be included in the results or not. When set to &lt;code&gt;true&lt;/code&gt; all keys that have &lt;em&gt;access&lt;/em&gt; to the repository (including project level keys) are included in the results. When set to &lt;code&gt;false&lt;/code&gt;, only access keys configured for the specified &lt;code&gt;repository&lt;/code&gt; are considered. Default is &lt;code&gt;false&lt;/code&gt;. (optional)
     * @param minimumPermission If specified only SSH access keys with at least the supplied permission will be returned. Default is &lt;code&gt;Permission.REPO_READ&lt;/code&gt;. (optional)
     * @param permission  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetForRepository1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetForRepository1200Response getForRepository1(String projectKey, String repositorySlug, String filter, String effective, String minimumPermission, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getForRepository1WithHttpInfo(projectKey, repositorySlug, filter, effective, minimumPermission, permission, start, limit).getBody();
    }

    /**
     * Get repository SSH keys
     * Retrieves the access keys for the repository identified in the URL.
     * <p><b>200</b> - A single page of access keys for the repository.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this repository
     * <p><b>404</b> - The specified repository does not exist
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only SSH access keys with a label prefixed with the supplied string will be returned (optional)
     * @param effective Controls whether SSH access keys configured at the project level should be included in the results or not. When set to &lt;code&gt;true&lt;/code&gt; all keys that have &lt;em&gt;access&lt;/em&gt; to the repository (including project level keys) are included in the results. When set to &lt;code&gt;false&lt;/code&gt;, only access keys configured for the specified &lt;code&gt;repository&lt;/code&gt; are considered. Default is &lt;code&gt;false&lt;/code&gt;. (optional)
     * @param minimumPermission If specified only SSH access keys with at least the supplied permission will be returned. Default is &lt;code&gt;Permission.REPO_READ&lt;/code&gt;. (optional)
     * @param permission  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetForRepository1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetForRepository1200Response> getForRepository1WithHttpInfo(String projectKey, String repositorySlug, String filter, String effective, String minimumPermission, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getForRepository1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getForRepository1");
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
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "effective", effective));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "minimumPermission", minimumPermission));
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

        ParameterizedTypeReference<GetForRepository1200Response> localReturnType = new ParameterizedTypeReference<GetForRepository1200Response>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/repos/{repositorySlug}/ssh", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get SSH key for user by keyId
     * Retrieve an SSH key by keyId
     * <p><b>200</b> - An SSH key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissionsto retrieve the SSH key. This is only possible when a&lt;strong&gt;keyId&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No SSH key matches the supplied &lt;strong&gt;keyId&lt;/strong&gt;
     * @param keyId the ID of the key to retrieve. (required)
     * @return RestSshKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshKey getSshKey(String keyId) throws RestClientException {
        return getSshKeyWithHttpInfo(keyId).getBody();
    }

    /**
     * Get SSH key for user by keyId
     * Retrieve an SSH key by keyId
     * <p><b>200</b> - An SSH key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissionsto retrieve the SSH key. This is only possible when a&lt;strong&gt;keyId&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No SSH key matches the supplied &lt;strong&gt;keyId&lt;/strong&gt;
     * @param keyId the ID of the key to retrieve. (required)
     * @return ResponseEntity&lt;RestSshKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshKey> getSshKeyWithHttpInfo(String keyId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling getSshKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("keyId", keyId);

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

        ParameterizedTypeReference<RestSshKey> localReturnType = new ParameterizedTypeReference<RestSshKey>() {};
        return apiClient.invokeAPI("/ssh/latest/keys/{keyId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get SSH key
     * Retrieves the access keys for the project identified in the URL.
     * <p><b>200</b> - A single page of access keys associated with the project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only SSH access keys with a label prefixed with the supplied string will be returned. (optional)
     * @param permission If specified only SSH access keys with at least the supplied permission will be returned Default is PROJECT_READ. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetForRepository1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetForRepository1200Response getSshKeyForProject(String projectKey, String filter, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getSshKeyForProjectWithHttpInfo(projectKey, filter, permission, start, limit).getBody();
    }

    /**
     * Get SSH key
     * Retrieves the access keys for the project identified in the URL.
     * <p><b>200</b> - A single page of access keys associated with the project.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to retrieve the access keys for this project.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param filter If specified only SSH access keys with a label prefixed with the supplied string will be returned. (optional)
     * @param permission If specified only SSH access keys with at least the supplied permission will be returned Default is PROJECT_READ. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetForRepository1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetForRepository1200Response> getSshKeyForProjectWithHttpInfo(String projectKey, String filter, String permission, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getSshKeyForProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
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

        ParameterizedTypeReference<GetForRepository1200Response> localReturnType = new ParameterizedTypeReference<GetForRepository1200Response>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/ssh", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get SSH keys for user
     * Retrieve a page of SSH keys.
     * <p><b>200</b> - A page of SSH keys.
     * <p><b>401</b> - The currently authenticated user has insufficient permissionsto retrieve the SSH keys. This is only possible when a&lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * @param userName the username of the user to retrieve the keys for. If no username is specified, the SSH keys will be retrieved for the current authenticated user. (optional)
     * @param user  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetSshKeys200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetSshKeys200Response getSshKeys(String userName, String user, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getSshKeysWithHttpInfo(userName, user, start, limit).getBody();
    }

    /**
     * Get SSH keys for user
     * Retrieve a page of SSH keys.
     * <p><b>200</b> - A page of SSH keys.
     * <p><b>401</b> - The currently authenticated user has insufficient permissionsto retrieve the SSH keys. This is only possible when a&lt;strong&gt;user&lt;/strong&gt; is explicitly supplied.
     * <p><b>404</b> - No user matches the supplied &lt;strong&gt;user&lt;/strong&gt;
     * @param userName the username of the user to retrieve the keys for. If no username is specified, the SSH keys will be retrieved for the current authenticated user. (optional)
     * @param user  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetSshKeys200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetSshKeys200Response> getSshKeysWithHttpInfo(String userName, String user, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "userName", userName));
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

        ParameterizedTypeReference<GetSshKeys200Response> localReturnType = new ParameterizedTypeReference<GetSshKeys200Response>() {};
        return apiClient.invokeAPI("/ssh/latest/keys", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke project SSH key
     * Remove an existing access key for the project identified in the URL. If the same SSH key is used as an access key for multiple projects or repositories, only the access to the project identified in the URL will be revoked.
     * <p><b>204</b> - The access key was deleted (or none was found matching the given id).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for this project.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokeForProject(String projectKey, String keyId) throws RestClientException {
        revokeForProjectWithHttpInfo(projectKey, keyId);
    }

    /**
     * Revoke project SSH key
     * Remove an existing access key for the project identified in the URL. If the same SSH key is used as an access key for multiple projects or repositories, only the access to the project identified in the URL will be revoked.
     * <p><b>204</b> - The access key was deleted (or none was found matching the given id).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for this project.
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokeForProjectWithHttpInfo(String projectKey, String keyId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokeForProject");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling revokeForProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);

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
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/ssh/{keyId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke repository SSH key
     * Remove an existing access key for the repository identified in the URL. If the same SSH key is used as an access key for multiple projects or repositories, only the access to the repository identified in the URL will be revoked.
     * <p><b>204</b> - The access key was deleted (or none was found matching the given id).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for this repository
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @param repositorySlug The repository slug (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokeForRepository(String projectKey, String keyId, String repositorySlug) throws RestClientException {
        revokeForRepositoryWithHttpInfo(projectKey, keyId, repositorySlug);
    }

    /**
     * Revoke repository SSH key
     * Remove an existing access key for the repository identified in the URL. If the same SSH key is used as an access key for multiple projects or repositories, only the access to the repository identified in the URL will be revoked.
     * <p><b>204</b> - The access key was deleted (or none was found matching the given id).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for this repository
     * @param projectKey The project key (required)
     * @param keyId The key id (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokeForRepositoryWithHttpInfo(String projectKey, String keyId, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokeForRepository");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling revokeForRepository");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling revokeForRepository");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);
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
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/repos/{repositorySlug}/ssh/{keyId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke project SSH key
     * Remove an existing access key for the projects and repositories in the submitted entity. If the same SSH key is used as an access key for multiple projects or repositories not supplied, only the access to the projects or repositories identified will be revoked.
     * <p><b>204</b> - The access keys were deleted (or none was found matching the given id and repositories or projects).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for one or more of the specified projects or repositories.
     * <p><b>404</b> - On or more of the specified repositories or projects does not exist or the key itself does not exist.
     * @param keyId The identifier of the SSH key (required)
     * @param revokeManyRequest  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokeMany(String keyId, RevokeManyRequest revokeManyRequest) throws RestClientException {
        revokeManyWithHttpInfo(keyId, revokeManyRequest);
    }

    /**
     * Revoke project SSH key
     * Remove an existing access key for the projects and repositories in the submitted entity. If the same SSH key is used as an access key for multiple projects or repositories not supplied, only the access to the projects or repositories identified will be revoked.
     * <p><b>204</b> - The access keys were deleted (or none was found matching the given id and repositories or projects).
     * <p><b>401</b> - The currently authenticated user has insufficient permissions to remove access keys for one or more of the specified projects or repositories.
     * <p><b>404</b> - On or more of the specified repositories or projects does not exist or the key itself does not exist.
     * @param keyId The identifier of the SSH key (required)
     * @param revokeManyRequest  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokeManyWithHttpInfo(String keyId, RevokeManyRequest revokeManyRequest) throws RestClientException {
        Object localVarPostBody = revokeManyRequest;
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling revokeMany");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("keyId", keyId);

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
        return apiClient.invokeAPI("/keys/latest/ssh/{keyId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - The ssh settings from upstream
     * <p><b>401</b> - The request was not authenticated
     * @return RestSshSettings
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshSettings sshSettings() throws RestClientException {
        return sshSettingsWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - The ssh settings from upstream
     * <p><b>401</b> - The request was not authenticated
     * @return ResponseEntity&lt;RestSshSettings&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshSettings> sshSettingsWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestSshSettings> localReturnType = new ParameterizedTypeReference<RestSshSettings>() {};
        return apiClient.invokeAPI("/ssh/latest/settings", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken update(String projectKey, String tokenId, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return updateWithHttpInfo(projectKey, tokenId, restAccessTokenRequest).getBody();
    }

    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> updateWithHttpInfo(String projectKey, String tokenId, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling update");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling update");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);

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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/{tokenId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken update1(String projectKey, String tokenId, String repositorySlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return update1WithHttpInfo(projectKey, tokenId, repositorySlug, restAccessTokenRequest).getBody();
    }

    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param projectKey The project key. (required)
     * @param tokenId The token id. (required)
     * @param repositorySlug The repository slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> update1WithHttpInfo(String projectKey, String tokenId, String repositorySlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling update1");
        }
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling update1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling update1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("tokenId", tokenId);
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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/projects/{projectKey}/repos/{repositorySlug}/{tokenId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return RestAccessToken
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestAccessToken update2(String tokenId, String userSlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        return update2WithHttpInfo(tokenId, userSlug, restAccessTokenRequest).getBody();
    }

    /**
     * Update HTTP token
     * Modify an access token according to the given request. Any fields not specified will not be altered.
     * <p><b>200</b> - A response containing the updated access token and associated details.
     * <p><b>400</b> - One of the provided permission levels are unknown.
     * <p><b>401</b> - The currently authenticated user is not permitted to update an access token on behalf of this user or authentication failed.
     * @param tokenId The token id. (required)
     * @param userSlug The user slug. (required)
     * @param restAccessTokenRequest The request containing the details of the access token to modify (optional)
     * @return ResponseEntity&lt;RestAccessToken&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestAccessToken> update2WithHttpInfo(String tokenId, String userSlug, RestAccessTokenRequest restAccessTokenRequest) throws RestClientException {
        Object localVarPostBody = restAccessTokenRequest;
        
        // verify the required parameter 'tokenId' is set
        if (tokenId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tokenId' when calling update2");
        }
        
        // verify the required parameter 'userSlug' is set
        if (userSlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSlug' when calling update2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("tokenId", tokenId);
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

        ParameterizedTypeReference<RestAccessToken> localReturnType = new ParameterizedTypeReference<RestAccessToken>() {};
        return apiClient.invokeAPI("/access-tokens/latest/users/{userSlug}/{tokenId}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update repository SSH key permission
     * Updates the permission granted to the specified SSH key to the repository identified in the URL.
     * <p><b>200</b> - The newly created access key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions on the repository to edit its access keys.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param keyId The newly created access key (required)
     * @param permission The new permission to be granted to the SSH key (required)
     * @param repositorySlug The repository slug (required)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey updatePermission(String projectKey, String keyId, String permission, String repositorySlug) throws RestClientException {
        return updatePermissionWithHttpInfo(projectKey, keyId, permission, repositorySlug).getBody();
    }

    /**
     * Update repository SSH key permission
     * Updates the permission granted to the specified SSH key to the repository identified in the URL.
     * <p><b>200</b> - The newly created access key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions on the repository to edit its access keys.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key (required)
     * @param keyId The newly created access key (required)
     * @param permission The new permission to be granted to the SSH key (required)
     * @param repositorySlug The repository slug (required)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> updatePermissionWithHttpInfo(String projectKey, String keyId, String permission, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePermission");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling updatePermission");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling updatePermission");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling updatePermission");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);
        uriVariables.put("permission", permission);
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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/repos/{repositorySlug}/ssh/{keyId}/permission/{permission}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update project SSH key permission
     * Updates the permission granted to the specified SSH key to the project identified in the URL.
     * <p><b>200</b> - The newly created access key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions on the project to edit its access keys.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param keyId The newly created access key (required)
     * @param permission The new permission to be granted to the SSH key (required)
     * @return RestSshAccessKey
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestSshAccessKey updatePermission1(String projectKey, String keyId, String permission) throws RestClientException {
        return updatePermission1WithHttpInfo(projectKey, keyId, permission).getBody();
    }

    /**
     * Update project SSH key permission
     * Updates the permission granted to the specified SSH key to the project identified in the URL.
     * <p><b>200</b> - The newly created access key.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions on the project to edit its access keys.
     * <p><b>404</b> - The specified project does not exist.
     * @param projectKey The project key (required)
     * @param keyId The newly created access key (required)
     * @param permission The new permission to be granted to the SSH key (required)
     * @return ResponseEntity&lt;RestSshAccessKey&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestSshAccessKey> updatePermission1WithHttpInfo(String projectKey, String keyId, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling updatePermission1");
        }
        
        // verify the required parameter 'keyId' is set
        if (keyId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'keyId' when calling updatePermission1");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling updatePermission1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("keyId", keyId);
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

        ParameterizedTypeReference<RestSshAccessKey> localReturnType = new ParameterizedTypeReference<RestSshAccessKey>() {};
        return apiClient.invokeAPI("/keys/latest/projects/{projectKey}/ssh/{keyId}/permission/{permission}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
