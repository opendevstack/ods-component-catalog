package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestBuildCapabilities;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.CapabilitiesApi")
public class CapabilitiesApi extends BaseApi {

    public CapabilitiesApi() {
        super(new ApiClient());
    }

    @Autowired
    public CapabilitiesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Get build capabilities
     * Returns the build capabilities of this instance
     * <p><b>200</b> - capabilities
     * @return RestBuildCapabilities
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestBuildCapabilities getCapabilities() throws RestClientException {
        return getCapabilitiesWithHttpInfo().getBody();
    }

    /**
     * Get build capabilities
     * Returns the build capabilities of this instance
     * <p><b>200</b> - capabilities
     * @return ResponseEntity&lt;RestBuildCapabilities&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestBuildCapabilities> getCapabilitiesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<RestBuildCapabilities> localReturnType = new ParameterizedTypeReference<RestBuildCapabilities>() {};
        return apiClient.invokeAPI("/api/latest/build/capabilities", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get deployment capabilities
     * Returns the Deployment capabilities of this instance
     * <p><b>200</b> - capabilities
     * <p><b>404</b> - This instance of Bitbucket Server does not support deployments (for example, it has been disabled using the property &#39;plugin.deployment.capability.disabled&#39;).
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getCapabilities1() throws RestClientException {
        getCapabilities1WithHttpInfo();
    }

    /**
     * Get deployment capabilities
     * Returns the Deployment capabilities of this instance
     * <p><b>200</b> - capabilities
     * <p><b>404</b> - This instance of Bitbucket Server does not support deployments (for example, it has been disabled using the property &#39;plugin.deployment.capability.disabled&#39;).
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getCapabilities1WithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/api/latest/deployment/capabilities", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
