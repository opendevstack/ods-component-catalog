package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestMarkup;

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
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.MarkupApi")
public class MarkupApi extends BaseApi {

    public MarkupApi() {
        super(new ApiClient());
    }

    @Autowired
    public MarkupApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Preview markdown render
     * Preview generated HTML for the given markdown content.  Only authenticated users may call this resource.
     * <p><b>200</b> - The rendered markdown.
     * <p><b>400</b> - The markdown was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions preview rendered markdown.
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. (optional)
     * @param urlMode (Optional) The mode to use when building URLs. One of: ABSOLUTE, RELATIVE or, CONFIGURED. By default this is RELATIVE. (optional)
     * @param includeHeadingId (Optional) true if headers should contain an ID based on the heading content. (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. By default this is false which reflects the standard markdown specification. (optional)
     * @param body  (optional)
     * @return RestMarkup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestMarkup preview(String htmlEscape, String urlMode, String includeHeadingId, String hardwrap, String body) throws RestClientException {
        return previewWithHttpInfo(htmlEscape, urlMode, includeHeadingId, hardwrap, body).getBody();
    }

    /**
     * Preview markdown render
     * Preview generated HTML for the given markdown content.  Only authenticated users may call this resource.
     * <p><b>200</b> - The rendered markdown.
     * <p><b>400</b> - The markdown was invalid.
     * <p><b>401</b> - The currently authenticated user has insufficient permissions preview rendered markdown.
     * @param htmlEscape (Optional) true if HTML should be escaped in the input markup, false otherwise. (optional)
     * @param urlMode (Optional) The mode to use when building URLs. One of: ABSOLUTE, RELATIVE or, CONFIGURED. By default this is RELATIVE. (optional)
     * @param includeHeadingId (Optional) true if headers should contain an ID based on the heading content. (optional)
     * @param hardwrap (Optional) Whether the markup implementation should convert newlines to breaks. By default this is false which reflects the standard markdown specification. (optional)
     * @param body  (optional)
     * @return ResponseEntity&lt;RestMarkup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestMarkup> previewWithHttpInfo(String htmlEscape, String urlMode, String includeHeadingId, String hardwrap, String body) throws RestClientException {
        Object localVarPostBody = body;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "htmlEscape", htmlEscape));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "urlMode", urlMode));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeHeadingId", includeHeadingId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "hardwrap", hardwrap));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<RestMarkup> localReturnType = new ParameterizedTypeReference<RestMarkup>() {};
        return apiClient.invokeAPI("/api/latest/markup/preview", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
