package com.boehringer.componentcatalog.client.bitbucket.v89.api;

import com.boehringer.componentcatalog.client.bitbucket.v89.ApiClient;
import com.boehringer.componentcatalog.client.bitbucket.v89.BaseApi;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.AdminPasswordUpdate;
import java.math.BigDecimal;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.FindUsersInGroup200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetAll401Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetGroups1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetGroups200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetGroupsWithAnyPermission200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetLikers200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GetUsersWithAnyPermission1200Response;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GroupAndUsers;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.GroupPickerContext;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDetailedGroup;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestDetailedUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestErasedUser;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserAndGroups;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserPickerContext;
import com.boehringer.componentcatalog.client.bitbucket.v89.model.UserRename;
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

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-02-28T09:35:07.114611322+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
@Component("com.boehringer.componentcatalog.client.bitbucket.v89.api.PermissionManagementApi")
public class PermissionManagementApi extends BaseApi {

    public PermissionManagementApi() {
        super(new ApiClient());
    }

    @Autowired
    public PermissionManagementApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group. This is very similar to &lt;code&gt;groups/add-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addGroupToUser(GroupPickerContext groupPickerContext) throws RestClientException {
        addGroupToUserWithHttpInfo(groupPickerContext);
    }

    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group. This is very similar to &lt;code&gt;groups/add-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addGroupToUserWithHttpInfo(GroupPickerContext groupPickerContext) throws RestClientException {
        Object localVarPostBody = groupPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/add-group", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void addUserToGroup(UserPickerContext userPickerContext) throws RestClientException {
        addUserToGroupWithHttpInfo(userPickerContext);
    }

    /**
     * Add user to group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/add-groups instead.  Add a user to a group.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> addUserToGroupWithHttpInfo(UserPickerContext userPickerContext) throws RestClientException {
        Object localVarPostBody = userPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/groups/add-user", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add user to groups
     * Add a user to one or more groups.    The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to &lt;em&gt;all&lt;/em&gt; the groups
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userAndGroups  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void addUserToGroups(UserAndGroups userAndGroups) throws RestClientException {
        addUserToGroupsWithHttpInfo(userAndGroups);
    }

    /**
     * Add user to groups
     * Add a user to one or more groups.    The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was added to &lt;em&gt;all&lt;/em&gt; the groups
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userAndGroups  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addUserToGroupsWithHttpInfo(UserAndGroups userAndGroups) throws RestClientException {
        Object localVarPostBody = userAndGroups;
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/add-groups", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Add multiple users to group
     * Add multiple users to a group.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - &lt;em&gt;All&lt;/em&gt; the users were added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupAndUsers  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void addUsersToGroup(GroupAndUsers groupAndUsers) throws RestClientException {
        addUsersToGroupWithHttpInfo(groupAndUsers);
    }

    /**
     * Add multiple users to group
     * Add multiple users to a group.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - &lt;em&gt;All&lt;/em&gt; the users were added to the group
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s licensing limit, or the groups permissions exceed the authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupAndUsers  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addUsersToGroupWithHttpInfo(GroupAndUsers groupAndUsers) throws RestClientException {
        Object localVarPostBody = groupAndUsers;
        

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
        return apiClient.invokeAPI("/api/latest/admin/groups/add-users", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clear CAPTCHA for user
     * Clears any CAPTCHA challenge that may constrain the user with the supplied username when they authenticate. Additionally any counter or metric that contributed towards the user being issued the CAPTCHA challenge (for instance too many consecutive failed logins) will also be reset.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource, and may not clear the CAPTCHA of a user with greater permissions than themselves.
     * <p><b>204</b> - The CAPTCHA was successfully cleared.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the user to clear captcha for.
     * <p><b>404</b> - The specified user does not exist.
     * @param name The username (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void clearUserCaptchaChallenge(String name) throws RestClientException {
        clearUserCaptchaChallengeWithHttpInfo(name);
    }

    /**
     * Clear CAPTCHA for user
     * Clears any CAPTCHA challenge that may constrain the user with the supplied username when they authenticate. Additionally any counter or metric that contributed towards the user being issued the CAPTCHA challenge (for instance too many consecutive failed logins) will also be reset.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource, and may not clear the CAPTCHA of a user with greater permissions than themselves.
     * <p><b>204</b> - The CAPTCHA was successfully cleared.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the user to clear captcha for.
     * <p><b>404</b> - The specified user does not exist.
     * @param name The username (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> clearUserCaptchaChallengeWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling clearUserCaptchaChallenge");
        }
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/captcha", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create group
     * Create a new group.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The newly created group.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * <p><b>409</b> - A group with this name already exists.
     * @param name Name of the group. (required)
     * @return RestDetailedGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedGroup createGroup(String name) throws RestClientException {
        return createGroupWithHttpInfo(name).getBody();
    }

    /**
     * Create group
     * Create a new group.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - The newly created group.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * <p><b>409</b> - A group with this name already exists.
     * @param name Name of the group. (required)
     * @return ResponseEntity&lt;RestDetailedGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedGroup> createGroupWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling createGroup");
        }
        

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

        ParameterizedTypeReference<RestDetailedGroup> localReturnType = new ParameterizedTypeReference<RestDetailedGroup>() {};
        return apiClient.invokeAPI("/api/latest/admin/groups", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create user
     * Creates a new user from the assembled query parameters.  The default group can be used to control initial permissions for new users, such as granting users the ability to login or providing read access to certain projects or repositories. If the user is not added to the default group, they may not be able to login after their account is created until explicit permissions are configured.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The user was successfully created.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user is not an administrator.
     * <p><b>403</b> - Adding the user to the default group would exceed the server&#39;s license limit.
     * <p><b>409</b> - Another user with the same name already exists.
     * @param emailAddress The e-mail address for the new user. (required)
     * @param displayName The display name for the new user. (required)
     * @param name The username for the new user. (required)
     * @param password The password for the new user. Required if the &lt;code&gt;notify&lt;/code&gt; parameter is not present or is set to &lt;code&gt;false&lt;/false&gt; (optional)
     * @param addToDefaultGroup Set &lt;code&gt;true&lt;/code&gt; to add the user to the default group, which can be used to grant them a set of initial permissions; otherwise, &lt;code&gt;false&lt;/code&gt; to not add them to a group. (optional, default to true)
     * @param notify If present and not &lt;code&gt;false&lt;/code&gt; instead of requiring a password, the create user will be notified via email their account has been created and requires a password to be reset. This option can only be used if a mail server has been configured. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createUser(String emailAddress, String displayName, String name, String password, Boolean addToDefaultGroup, Boolean notify) throws RestClientException {
        createUserWithHttpInfo(emailAddress, displayName, name, password, addToDefaultGroup, notify);
    }

    /**
     * Create user
     * Creates a new user from the assembled query parameters.  The default group can be used to control initial permissions for new users, such as granting users the ability to login or providing read access to certain projects or repositories. If the user is not added to the default group, they may not be able to login after their account is created until explicit permissions are configured.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - The user was successfully created.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user is not an administrator.
     * <p><b>403</b> - Adding the user to the default group would exceed the server&#39;s license limit.
     * <p><b>409</b> - Another user with the same name already exists.
     * @param emailAddress The e-mail address for the new user. (required)
     * @param displayName The display name for the new user. (required)
     * @param name The username for the new user. (required)
     * @param password The password for the new user. Required if the &lt;code&gt;notify&lt;/code&gt; parameter is not present or is set to &lt;code&gt;false&lt;/false&gt; (optional)
     * @param addToDefaultGroup Set &lt;code&gt;true&lt;/code&gt; to add the user to the default group, which can be used to grant them a set of initial permissions; otherwise, &lt;code&gt;false&lt;/code&gt; to not add them to a group. (optional, default to true)
     * @param notify If present and not &lt;code&gt;false&lt;/code&gt; instead of requiring a password, the create user will be notified via email their account has been created and requires a password to be reset. This option can only be used if a mail server has been configured. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createUserWithHttpInfo(String emailAddress, String displayName, String name, String password, Boolean addToDefaultGroup, Boolean notify) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'emailAddress' is set
        if (emailAddress == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'emailAddress' when calling createUser");
        }
        
        // verify the required parameter 'displayName' is set
        if (displayName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'displayName' when calling createUser");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling createUser");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "emailAddress", emailAddress));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "password", password));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "addToDefaultGroup", addToDefaultGroup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayName", displayName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "notify", notify));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/users", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove group
     * Deletes the specified group, removing them from the system. This also removes any permissions that may have been granted to the group.  A user may not delete the last group that is granting them administrative permissions, or a group with greater permissions than themselves.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The deleted group.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the group being deleted.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would lower the authenticated user&#39;s permission level.
     * @param name The name identifying the group to delete. (required)
     * @return RestDetailedGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedGroup deleteGroup(String name) throws RestClientException {
        return deleteGroupWithHttpInfo(name).getBody();
    }

    /**
     * Remove group
     * Deletes the specified group, removing them from the system. This also removes any permissions that may have been granted to the group.  A user may not delete the last group that is granting them administrative permissions, or a group with greater permissions than themselves.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The deleted group.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the group being deleted.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would lower the authenticated user&#39;s permission level.
     * @param name The name identifying the group to delete. (required)
     * @return ResponseEntity&lt;RestDetailedGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedGroup> deleteGroupWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling deleteGroup");
        }
        

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

        ParameterizedTypeReference<RestDetailedGroup> localReturnType = new ParameterizedTypeReference<RestDetailedGroup>() {};
        return apiClient.invokeAPI("/api/latest/admin/groups", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove user
     * Deletes the specified user, removing them from the system. This also removes any permissions that may have been granted to the user.  A user may not delete themselves, and a user with &lt;strong&gt;ADMIN&lt;/strong&gt; permissions may not delete a user with &lt;strong&gt;SYS_ADMIN&lt;/strong&gt;permissions.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The deleted user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the user being deleted.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as a user can not delete themselves.
     * @param name The username identifying the user to delete. (required)
     * @return RestDetailedUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedUser deleteUser(String name) throws RestClientException {
        return deleteUserWithHttpInfo(name).getBody();
    }

    /**
     * Remove user
     * Deletes the specified user, removing them from the system. This also removes any permissions that may have been granted to the user.  A user may not delete themselves, and a user with &lt;strong&gt;ADMIN&lt;/strong&gt; permissions may not delete a user with &lt;strong&gt;SYS_ADMIN&lt;/strong&gt;permissions.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The deleted user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the authenticated user has a lower permission level than the user being deleted.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as a user can not delete themselves.
     * @param name The username identifying the user to delete. (required)
     * @return ResponseEntity&lt;RestDetailedUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedUser> deleteUserWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling deleteUser");
        }
        

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

        ParameterizedTypeReference<RestDetailedUser> localReturnType = new ParameterizedTypeReference<RestDetailedUser>() {};
        return apiClient.invokeAPI("/api/latest/admin/users", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Erase user information
     * Erases personally identifying user data for a deleted user.  References in the application to the original username will be either removed or updated to a new non-identifying username. Refer to the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/gdpr/bitbucket-right-to-erasure-949770560.html\&quot;&gt;support guide&lt;/a&gt; for details about what data is and isn&#39;t erased.  User erasure can only be performed on a deleted user. If the user has not been deleted first then this endpoint will return a bad request and no erasure will be performed.  Erasing user data is &lt;strong&gt;irreversible&lt;/strong&gt; and may lead to a degraded user experience. This method should not be used as part of a standard user deletion and cleanup process.  Plugins can participate in user erasure by defining a &lt;code&gt;&amp;lt;user-erasure-handler&amp;gt;&lt;/code&gt; module. If one or more plugin modules fail, an error summary of the failing modules is returned.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The identifier of the erased user.
     * <p><b>400</b> - The request was malformed (e.g. if no username was supplied).
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being erased.
     * <p><b>404</b> - The requested username does not exist.
     * <p><b>409</b> - The requested username is the username of a not deleted user.
     * @param name The username identifying the user to erase. (required)
     * @return RestErasedUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestErasedUser eraseUser(String name) throws RestClientException {
        return eraseUserWithHttpInfo(name).getBody();
    }

    /**
     * Erase user information
     * Erases personally identifying user data for a deleted user.  References in the application to the original username will be either removed or updated to a new non-identifying username. Refer to the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/gdpr/bitbucket-right-to-erasure-949770560.html\&quot;&gt;support guide&lt;/a&gt; for details about what data is and isn&#39;t erased.  User erasure can only be performed on a deleted user. If the user has not been deleted first then this endpoint will return a bad request and no erasure will be performed.  Erasing user data is &lt;strong&gt;irreversible&lt;/strong&gt; and may lead to a degraded user experience. This method should not be used as part of a standard user deletion and cleanup process.  Plugins can participate in user erasure by defining a &lt;code&gt;&amp;lt;user-erasure-handler&amp;gt;&lt;/code&gt; module. If one or more plugin modules fail, an error summary of the failing modules is returned.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The identifier of the erased user.
     * <p><b>400</b> - The request was malformed (e.g. if no username was supplied).
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being erased.
     * <p><b>404</b> - The requested username does not exist.
     * <p><b>409</b> - The requested username is the username of a not deleted user.
     * @param name The username identifying the user to erase. (required)
     * @return ResponseEntity&lt;RestErasedUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestErasedUser> eraseUserWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling eraseUser");
        }
        

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

        ParameterizedTypeReference<RestErasedUser> localReturnType = new ParameterizedTypeReference<RestErasedUser>() {};
        return apiClient.invokeAPI("/api/latest/admin/users/erasure", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups for user
     * Retrieves a list of users that are &lt;em&gt;not&lt;/em&gt; members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindUsersInGroup200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindUsersInGroup200Response findGroupsForUser(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findGroupsForUserWithHttpInfo(context, filter, start, limit).getBody();
    }

    /**
     * Get groups for user
     * Retrieves a list of users that are &lt;em&gt;not&lt;/em&gt; members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindUsersInGroup200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindUsersInGroup200Response> findGroupsForUserWithHttpInfo(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'context' is set
        if (context == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'context' when calling findGroupsForUser");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "context", context));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<FindUsersInGroup200Response> localReturnType = new ParameterizedTypeReference<FindUsersInGroup200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/users/more-members", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Find other groups for user
     * Retrieves a list of groups the specified user is &lt;em&gt;not&lt;/em&gt; a member of. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of groups.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The user which should be used to locate groups. (required)
     * @param filter If specified only groups with names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups1200Response findOtherGroupsForUser(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findOtherGroupsForUserWithHttpInfo(context, filter, start, limit).getBody();
    }

    /**
     * Find other groups for user
     * Retrieves a list of groups the specified user is &lt;em&gt;not&lt;/em&gt; a member of. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of groups.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The user which should be used to locate groups. (required)
     * @param filter If specified only groups with names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups1200Response> findOtherGroupsForUserWithHttpInfo(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'context' is set
        if (context == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'context' when calling findOtherGroupsForUser");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "context", context));
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
        return apiClient.invokeAPI("/api/latest/admin/users/more-non-members", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get group members
     * Retrieves a list of users that are members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindUsersInGroup200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindUsersInGroup200Response findUsersInGroup(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findUsersInGroupWithHttpInfo(context, filter, start, limit).getBody();
    }

    /**
     * Get group members
     * Retrieves a list of users that are members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindUsersInGroup200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindUsersInGroup200Response> findUsersInGroupWithHttpInfo(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'context' is set
        if (context == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'context' when calling findUsersInGroup");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "context", context));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<FindUsersInGroup200Response> localReturnType = new ParameterizedTypeReference<FindUsersInGroup200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/groups/more-members", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get members not in group
     * Retrieves a list of users that are &lt;em&gt;not&lt;/em&gt; members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindUsersInGroup200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindUsersInGroup200Response findUsersNotInGroup(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return findUsersNotInGroupWithHttpInfo(context, filter, start, limit).getBody();
    }

    /**
     * Get members not in group
     * Retrieves a list of users that are &lt;em&gt;not&lt;/em&gt; members of a specified group. &lt;p&gt;The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param context The group which should be used to locate members. (required)
     * @param filter If specified only users with usernames, display names or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindUsersInGroup200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindUsersInGroup200Response> findUsersNotInGroupWithHttpInfo(String context, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'context' is set
        if (context == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'context' when calling findUsersNotInGroup");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "context", context));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<FindUsersInGroup200Response> localReturnType = new ParameterizedTypeReference<FindUsersInGroup200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/groups/more-non-members", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get group names
     * Retrieve a page of group names.  The authenticated user must have &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of group names.
     * <p><b>401</b> - The currently authenticated user is not a project administrator.
     * @param filter  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups200Response getGroups(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get group names
     * Retrieve a page of group names.  The authenticated user must have &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of group names.
     * <p><b>401</b> - The currently authenticated user is not a project administrator.
     * @param filter  (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups200Response> getGroupsWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetGroups200Response> localReturnType = new ParameterizedTypeReference<GetGroups200Response>() {};
        return apiClient.invokeAPI("/api/latest/groups", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups
     * Retrieve a page of groups.   The authenticated user must have &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups1200Response getGroups1(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroups1WithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get groups
     * Retrieve a page of groups.   The authenticated user must have &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups1200Response> getGroups1WithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetGroups1200Response> localReturnType = new ParameterizedTypeReference<GetGroups1200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/groups", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups with a global permission
     * Retrieve a page of groups that have been granted at least one global permission.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups and their highest global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroupsWithAnyPermission200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroupsWithAnyPermission200Response getGroupsWithAnyPermission(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithAnyPermissionWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get groups with a global permission
     * Retrieve a page of groups that have been granted at least one global permission.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups and their highest global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only group names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroupsWithAnyPermission200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroupsWithAnyPermission200Response> getGroupsWithAnyPermissionWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetGroupsWithAnyPermission200Response> localReturnType = new ParameterizedTypeReference<GetGroupsWithAnyPermission200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/groups", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups with permission to repository
     * Retrieve a page of groups that have been granted at least one permission for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of groups and their highest permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroupsWithAnyPermission200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroupsWithAnyPermission200Response getGroupsWithAnyPermission2(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithAnyPermission2WithHttpInfo(projectKey, repositorySlug, filter, start, limit).getBody();
    }

    /**
     * Get groups with permission to repository
     * Retrieve a page of groups that have been granted at least one permission for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of groups and their highest permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroupsWithAnyPermission200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroupsWithAnyPermission200Response> getGroupsWithAnyPermission2WithHttpInfo(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getGroupsWithAnyPermission2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getGroupsWithAnyPermission2");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups with no global permission
     * Retrieve a page of groups that have no granted global permissions.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups that have not been granted any global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups1200Response getGroupsWithoutAnyPermission(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithoutAnyPermissionWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get groups with no global permission
     * Retrieve a page of groups that have no granted global permissions.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of groups that have not been granted any global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups1200Response> getGroupsWithoutAnyPermissionWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetGroups1200Response> localReturnType = new ParameterizedTypeReference<GetGroups1200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/groups/none", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get groups without repository permission
     * Retrieve a page of groups that have no granted permissions for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of groups that have not been granted any permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroups1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroups1200Response getGroupsWithoutAnyPermission2(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getGroupsWithoutAnyPermission2WithHttpInfo(projectKey, repositorySlug, filter, start, limit).getBody();
    }

    /**
     * Get groups without repository permission
     * Retrieve a page of groups that have no granted permissions for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of groups that have not been granted any permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only group names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroups1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroups1200Response> getGroupsWithoutAnyPermission2WithHttpInfo(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getGroupsWithoutAnyPermission2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getGroupsWithoutAnyPermission2");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/groups/none", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users
     * Retrieve a page of users.    The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param filter If specified only users with usernames, display name or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return FindUsersInGroup200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FindUsersInGroup200Response getUsers1(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsers1WithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get users
     * Retrieve a page of users.    The authenticated user must have the &lt;strong&gt;LICENSED_USER&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - A page of users.
     * <p><b>401</b> - The currently authenticated user is not a licensed user.
     * @param filter If specified only users with usernames, display name or email addresses containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;FindUsersInGroup200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FindUsersInGroup200Response> getUsers1WithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<FindUsersInGroup200Response> localReturnType = new ParameterizedTypeReference<FindUsersInGroup200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/users", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users with a global permission
     * Retrieve a page of users that have been granted at least one global permission.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of users and their highest global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetGroupsWithAnyPermission200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetGroupsWithAnyPermission200Response getUsersWithAnyPermission(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithAnyPermissionWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get users with a global permission
     * Retrieve a page of users that have been granted at least one global permission.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of users and their highest global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetGroupsWithAnyPermission200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetGroupsWithAnyPermission200Response> getUsersWithAnyPermissionWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetGroupsWithAnyPermission200Response> localReturnType = new ParameterizedTypeReference<GetGroupsWithAnyPermission200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/users", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users with permission to repository
     * Retrieve a page of users that have been granted at least one permission for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of users and their highest permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only user names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetUsersWithAnyPermission1200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetUsersWithAnyPermission1200Response getUsersWithAnyPermission2(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithAnyPermission2WithHttpInfo(projectKey, repositorySlug, filter, start, limit).getBody();
    }

    /**
     * Get users with permission to repository
     * Retrieve a page of users that have been granted at least one permission for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of users and their highest permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only user names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetUsersWithAnyPermission1200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetUsersWithAnyPermission1200Response> getUsersWithAnyPermission2WithHttpInfo(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getUsersWithAnyPermission2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getUsersWithAnyPermission2");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/users", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users with no global permission
     * Retrieve a page of users that have no granted global permissions.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLikers200Response getUsersWithoutAnyPermission(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithoutAnyPermissionWithHttpInfo(filter, start, limit).getBody();
    }

    /**
     * Get users with no global permission
     * Retrieve a page of users that have no granted global permissions.   The authenticated user must have &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any global permissions.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * @param filter If specified only user names containing the supplied string will be returned (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLikers200Response> getUsersWithoutAnyPermissionWithHttpInfo(String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
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

        ParameterizedTypeReference<GetLikers200Response> localReturnType = new ParameterizedTypeReference<GetLikers200Response>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/users/none", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get users without repository permission
     * Retrieve a page of &lt;i&gt;licensed&lt;/i&gt; users that have no granted permissions for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only user names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return GetLikers200Response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GetLikers200Response getUsersWithoutPermission1(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        return getUsersWithoutPermission1WithHttpInfo(projectKey, repositorySlug, filter, start, limit).getBody();
    }

    /**
     * Get users without repository permission
     * Retrieve a page of &lt;i&gt;licensed&lt;/i&gt; users that have no granted permissions for the specified repository.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.
     * <p><b>200</b> - A page of users that have not been granted any permissions for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param filter If specified only user names containing the supplied string will be returned. (optional)
     * @param start Start number for the page (inclusive). If not passed, first page is assumed. (optional)
     * @param limit Number of items to return. If not passed, a page size of 25 is used. (optional)
     * @return ResponseEntity&lt;GetLikers200Response&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GetLikers200Response> getUsersWithoutPermission1WithHttpInfo(String projectKey, String repositorySlug, String filter, BigDecimal start, BigDecimal limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling getUsersWithoutPermission1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling getUsersWithoutPermission1");
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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/users/none", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove user from group
     * Remove a user from a group. This is very similar to &lt;code&gt;groups/remove-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void removeGroupFromUser(GroupPickerContext groupPickerContext) throws RestClientException {
        removeGroupFromUserWithHttpInfo(groupPickerContext);
    }

    /**
     * Remove user from group
     * Remove a user from a group. This is very similar to &lt;code&gt;groups/remove-user&lt;/code&gt;, but with the &lt;em&gt;context&lt;/em&gt; and &lt;em&gt;itemName&lt;/em&gt; attributes of the supplied request entity reversed. On the face of it this may appear redundant, but it facilitates a specific UI component in the application.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the user and the &lt;em&gt;itemName&lt;/em&gt; is the group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param groupPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> removeGroupFromUserWithHttpInfo(GroupPickerContext groupPickerContext) throws RestClientException {
        Object localVarPostBody = groupPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/remove-group", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Remove user from group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/remove-groups instead.  Remove a user from a group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public void removeUserFromGroup(UserPickerContext userPickerContext) throws RestClientException {
        removeUserFromGroupWithHttpInfo(userPickerContext);
    }

    /**
     * Remove user from group
     * &lt;strong&gt;Deprecated since 2.10&lt;/strong&gt;. Use /rest/users/remove-groups instead.  Remove a user from a group.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.  In the request entity, the &lt;em&gt;context&lt;/em&gt; attribute is the group and the &lt;em&gt;itemName&lt;/em&gt; is the user.
     * <p><b>200</b> - The user was removed from the group.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission.
     * <p><b>403</b> - The action was disallowed as the group has a higher permission level than the context user.
     * <p><b>404</b> - The specified user or group does not exist.
     * @param userPickerContext  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<Void> removeUserFromGroupWithHttpInfo(UserPickerContext userPickerContext) throws RestClientException {
        Object localVarPostBody = userPickerContext;
        

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
        return apiClient.invokeAPI("/api/latest/admin/groups/remove-user", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Rename user
     * Rename a user.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The renamed user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being renamed.
     * <p><b>404</b> - The specified user does not exist.
     * @param userRename  (optional)
     * @return RestDetailedUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedUser renameUser(UserRename userRename) throws RestClientException {
        return renameUserWithHttpInfo(userRename).getBody();
    }

    /**
     * Rename user
     * Rename a user.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The renamed user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being renamed.
     * <p><b>404</b> - The specified user does not exist.
     * @param userRename  (optional)
     * @return ResponseEntity&lt;RestDetailedUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedUser> renameUserWithHttpInfo(UserRename userRename) throws RestClientException {
        Object localVarPostBody = userRename;
        

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

        ParameterizedTypeReference<RestDetailedUser> localReturnType = new ParameterizedTypeReference<RestDetailedUser>() {};
        return apiClient.invokeAPI("/api/latest/admin/users/rename", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke all repository permissions for users and groups
     * Revoke all permissions for the specified repository for the given groups and users.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified repository or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permission if their own permission would be revoked as a result, nor may they revoke their own permission unless they have a global permission that already implies that permission.
     * <p><b>204</b> - All repository permissions were revoked from the users and groups for the specified repository.
     * <p><b>400</b> - No permissions were revoked because the request was invalid. No users or groups were provided.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist, or one or more of the users or groups provided does not exist.
     * <p><b>409</b> - The action was disallowed as it would revoke the currently authenticated user&#39;s permission on the repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param user The names of the users (optional)
     * @param group The names of the groups (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissions1(String projectKey, String repositorySlug, String user, String group) throws RestClientException {
        revokePermissions1WithHttpInfo(projectKey, repositorySlug, user, group);
    }

    /**
     * Revoke all repository permissions for users and groups
     * Revoke all permissions for the specified repository for the given groups and users.  The authenticated user must have &lt;strong&gt;PROJECT_ADMIN&lt;/strong&gt; permission for the specified repository or a higher global permission to call this resource.  In addition, a user may not revoke a group&#39;s permission if their own permission would be revoked as a result, nor may they revoke their own permission unless they have a global permission that already implies that permission.
     * <p><b>204</b> - All repository permissions were revoked from the users and groups for the specified repository.
     * <p><b>400</b> - No permissions were revoked because the request was invalid. No users or groups were provided.
     * <p><b>401</b> - The currently authenticated user is not an administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist, or one or more of the users or groups provided does not exist.
     * <p><b>409</b> - The action was disallowed as it would revoke the currently authenticated user&#39;s permission on the repository.
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param user The names of the users (optional)
     * @param group The names of the groups (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissions1WithHttpInfo(String projectKey, String repositorySlug, String user, String group) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissions1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling revokePermissions1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke all global permissions for group
     * Revoke all global permissions for a group.    The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - greater or equal permissions than the current permission level of the group (a user may not demote the     permission level of a group with higher permissions than them)   to call this resource. In addition, a user may not revoke a group&#39;s permissions if their own permission level would be reduced as a result.
     * <p><b>204</b> - All global permissions were revoked from the group.
     * <p><b>401</b> - TThe currently authenticated user is not an administrator.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the group they are attempting to modify.
     * @param name The name of the group (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForGroup(String name) throws RestClientException {
        revokePermissionsForGroupWithHttpInfo(name);
    }

    /**
     * Revoke all global permissions for group
     * Revoke all global permissions for a group.    The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - greater or equal permissions than the current permission level of the group (a user may not demote the     permission level of a group with higher permissions than them)   to call this resource. In addition, a user may not revoke a group&#39;s permissions if their own permission level would be reduced as a result.
     * <p><b>204</b> - All global permissions were revoked from the group.
     * <p><b>401</b> - TThe currently authenticated user is not an administrator.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the group they are attempting to modify.
     * @param name The name of the group (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForGroupWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling revokePermissionsForGroup");
        }
        

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
        return apiClient.invokeAPI("/api/latest/admin/permissions/groups", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke group repository permission
     * Revoke all permissions for the specified repository for a group.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.  In addition, a user may not revoke a group&#39;s permissions if it will reduce their own permission level.
     * <p><b>204</b> - All repository permissions were revoked from the group for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * @param projectKey The project key. (required)
     * @param name The name of the group. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForGroup2(String projectKey, String name, String repositorySlug) throws RestClientException {
        revokePermissionsForGroup2WithHttpInfo(projectKey, name, repositorySlug);
    }

    /**
     * Revoke group repository permission
     * Revoke all permissions for the specified repository for a group.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.  In addition, a user may not revoke a group&#39;s permissions if it will reduce their own permission level.
     * <p><b>204</b> - All repository permissions were revoked from the group for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * @param projectKey The project key. (required)
     * @param name The name of the group. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForGroup2WithHttpInfo(String projectKey, String name, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissionsForGroup2");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling revokePermissionsForGroup2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling revokePermissionsForGroup2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/groups", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke all global permissions for user
     * Revoke all global permissions for a user.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - greater or equal permissions than the current permission level of the user (a user may not demote the     permission level of a user with higher permissions than them)   to call this resource. In addition, a user may not demote their own permission level.
     * <p><b>204</b> - All global permissions were revoked from the user.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the user they are attempting to modify.
     * @param name The name of the user (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForUser(String name) throws RestClientException {
        revokePermissionsForUserWithHttpInfo(name);
    }

    /**
     * Revoke all global permissions for user
     * Revoke all global permissions for a user.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - greater or equal permissions than the current permission level of the user (a user may not demote the     permission level of a user with higher permissions than them)   to call this resource. In addition, a user may not demote their own permission level.
     * <p><b>204</b> - All global permissions were revoked from the user.
     * <p><b>401</b> - The currently authenticated user is not an administrator.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the user they are attempting to modify.
     * @param name The name of the user (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForUserWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling revokePermissionsForUser");
        }
        

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
        return apiClient.invokeAPI("/api/latest/admin/permissions/users", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Revoke user repository permission
     * Revoke all permissions for the specified repository for a user.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.  In addition, a user may not revoke their own repository permissions if they do not have a higher project or global permission.
     * <p><b>204</b> - All repository permissions were revoked from the user for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * @param projectKey The project key. (required)
     * @param name The name of the user. (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void revokePermissionsForUser2(String projectKey, String name, String repositorySlug) throws RestClientException {
        revokePermissionsForUser2WithHttpInfo(projectKey, name, repositorySlug);
    }

    /**
     * Revoke user repository permission
     * Revoke all permissions for the specified repository for a user.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource.  In addition, a user may not revoke their own repository permissions if they do not have a higher project or global permission.
     * <p><b>204</b> - All repository permissions were revoked from the user for the specified repository.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>404</b> - The specified repository does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * @param projectKey The project key. (required)
     * @param name The name of the user. (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> revokePermissionsForUser2WithHttpInfo(String projectKey, String name, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling revokePermissionsForUser2");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling revokePermissionsForUser2");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling revokePermissionsForUser2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

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
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/users", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Search repository permissions
     * Search direct and implied permissions of users and groups. This endpoint returns a superset of the results returned by the /users and /groups endpoints because it allows filtering by project and global permissions too.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project/global permission to call this resource.
     * <p><b>0</b> - default response
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param permission Permissions to filter by. See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions)for a detailed explanation of what each permission entails. This parameter can be specified multiple times to filter by more than one permission, and can contain repository, project, and global permissions.   (optional)
     * @param filterText Name of the user or group to filter the name of (optional)
     * @param type Type of entity (user or group)Valid entity types are:  - USER- GROUP (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String searchPermissions1(String projectKey, String repositorySlug, String permission, String filterText, String type) throws RestClientException {
        return searchPermissions1WithHttpInfo(projectKey, repositorySlug, permission, filterText, type).getBody();
    }

    /**
     * Search repository permissions
     * Search direct and implied permissions of users and groups. This endpoint returns a superset of the results returned by the /users and /groups endpoints because it allows filtering by project and global permissions too.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project/global permission to call this resource.
     * <p><b>0</b> - default response
     * @param projectKey The project key. (required)
     * @param repositorySlug The repository slug. (required)
     * @param permission Permissions to filter by. See the [permissions documentation](https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions)for a detailed explanation of what each permission entails. This parameter can be specified multiple times to filter by more than one permission, and can contain repository, project, and global permissions.   (optional)
     * @param filterText Name of the user or group to filter the name of (optional)
     * @param type Type of entity (user or group)Valid entity types are:  - USER- GROUP (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> searchPermissions1WithHttpInfo(String projectKey, String repositorySlug, String permission, String filterText, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling searchPermissions1");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling searchPermissions1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterText", filterText));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/search", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update group repository permission
     * Promote or demote a group&#39;s permission level for the specified repository. Available repository permissions are:  - REPO_READ - REPO_WRITE - REPO_ADMIN   See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource. In addition, a user may not demote a group&#39;s permission level if their own permission level would be reduced as a result.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param name The names of the groups. (required)
     * @param permission The permission to grant (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForGroup(String projectKey, List<String> name, String permission, String repositorySlug) throws RestClientException {
        setPermissionForGroupWithHttpInfo(projectKey, name, permission, repositorySlug);
    }

    /**
     * Update group repository permission
     * Promote or demote a group&#39;s permission level for the specified repository. Available repository permissions are:  - REPO_READ - REPO_WRITE - REPO_ADMIN   See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource. In addition, a user may not demote a group&#39;s permission level if their own permission level would be reduced as a result.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param name The names of the groups. (required)
     * @param permission The permission to grant (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForGroupWithHttpInfo(String projectKey, List<String> name, String permission, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setPermissionForGroup");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling setPermissionForGroup");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling setPermissionForGroup");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setPermissionForGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/groups", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update global permission for group
     * Promote or demote a group&#39;s global permission level. Available global permissions are:   - LICENSED_USER - PROJECT_CREATE - ADMIN - SYS_ADMIN  See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Global+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - the permission they are attempting to grant or higher; and - greater or equal permissions than the current permission level of the group (a user may not demote the     permission level of a group with higher permissions than them)   to call this resource. In addition, a user may not demote a group&#39;s permission level if their own permission level would be reduced as a result.
     * <p><b>204</b> - The specified permission was granted to the specified user.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator or doesn&#39;t have the specified permission they are attempting to grant.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s license limits.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the group they are attempting to modify.
     * @param name The names of the groups (required)
     * @param permission The permission to grant (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForGroups(List<String> name, String permission) throws RestClientException {
        setPermissionForGroupsWithHttpInfo(name, permission);
    }

    /**
     * Update global permission for group
     * Promote or demote a group&#39;s global permission level. Available global permissions are:   - LICENSED_USER - PROJECT_CREATE - ADMIN - SYS_ADMIN  See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Global+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - the permission they are attempting to grant or higher; and - greater or equal permissions than the current permission level of the group (a user may not demote the     permission level of a group with higher permissions than them)   to call this resource. In addition, a user may not demote a group&#39;s permission level if their own permission level would be reduced as a result.
     * <p><b>204</b> - The specified permission was granted to the specified user.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator or doesn&#39;t have the specified permission they are attempting to grant.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s license limits.
     * <p><b>404</b> - The specified group does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the group they are attempting to modify.
     * @param name The names of the groups (required)
     * @param permission The permission to grant (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForGroupsWithHttpInfo(List<String> name, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling setPermissionForGroups");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling setPermissionForGroups");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/groups", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user repository permission
     * Promote or demote a user&#39;s permission level for the specified repository. Available repository permissions are:  - REPO_READ&lt;/li&gt;- REPO_WRITE&lt;/li&gt;- REPO_ADMIN&lt;/li&gt;&lt;/ul&gt;See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource. In addition, a user may not reduce their own permission level unless they have a project or global permission that already implies that permission.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param name The names of the users. (required)
     * @param permission The permission to grant (required)
     * @param repositorySlug The repository slug. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForUser(String projectKey, List<String> name, String permission, String repositorySlug) throws RestClientException {
        setPermissionForUserWithHttpInfo(projectKey, name, permission, repositorySlug);
    }

    /**
     * Update user repository permission
     * Promote or demote a user&#39;s permission level for the specified repository. Available repository permissions are:  - REPO_READ&lt;/li&gt;- REPO_WRITE&lt;/li&gt;- REPO_ADMIN&lt;/li&gt;&lt;/ul&gt;See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Using+repository+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.  The authenticated user must have &lt;strong&gt;REPO_ADMIN&lt;/strong&gt; permission for the specified repository or a higher project or global permission to call this resource. In addition, a user may not reduce their own permission level unless they have a project or global permission that already implies that permission.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not a repository administrator for the specified repository.
     * <p><b>403</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level.
     * <p><b>404</b> - The specified repository does not exist.
     * @param projectKey The project key. (required)
     * @param name The names of the users. (required)
     * @param permission The permission to grant (required)
     * @param repositorySlug The repository slug. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForUserWithHttpInfo(String projectKey, List<String> name, String permission, String repositorySlug) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'projectKey' is set
        if (projectKey == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'projectKey' when calling setPermissionForUser");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling setPermissionForUser");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling setPermissionForUser");
        }
        
        // verify the required parameter 'repositorySlug' is set
        if (repositorySlug == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'repositorySlug' when calling setPermissionForUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("projectKey", projectKey);
        uriVariables.put("repositorySlug", repositorySlug);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/projects/{projectKey}/repos/{repositorySlug}/permissions/users", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update global permission for user
     * Promote or demote the global permission level of a user. Available global permissions are:   - LICENSED_USER - PROJECT_CREATE - ADMIN - SYS_ADMIN   See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Global+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - the permission they are attempting to grant; and - greater or equal permissions than the current permission level of the user (a user may not demote the     permission level of a user with higher permissions than them)   to call this resource. In addition, a user may not demote their own permission level.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator or doesn&#39;t have the specified permission they are attempting to grant.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s license limits.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the user they are attempting to modify.
     * @param name The names of the users (required)
     * @param permission The permission to grant (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setPermissionForUsers(List<String> name, String permission) throws RestClientException {
        setPermissionForUsersWithHttpInfo(name, permission);
    }

    /**
     * Update global permission for user
     * Promote or demote the global permission level of a user. Available global permissions are:   - LICENSED_USER - PROJECT_CREATE - ADMIN - SYS_ADMIN   See the &lt;a href&#x3D;\&quot;https://confluence.atlassian.com/display/BitbucketServer/Global+permissions\&quot;&gt;Bitbucket Server documentation&lt;/a&gt; for a detailed explanation of what each permission entails.   The authenticated user must have:   - &lt;strong&gt;ADMIN&lt;/strong&gt; permission or higher; and - the permission they are attempting to grant; and - greater or equal permissions than the current permission level of the user (a user may not demote the     permission level of a user with higher permissions than them)   to call this resource. In addition, a user may not demote their own permission level.
     * <p><b>204</b> - The requested permission was granted.
     * <p><b>400</b> - The request was malformed or the specified permission does not exist.
     * <p><b>401</b> - The currently authenticated user is not an administrator or doesn&#39;t have the specified permission they are attempting to grant.
     * <p><b>403</b> - The action was disallowed as it would exceed the server&#39;s license limits.
     * <p><b>404</b> - The specified user does not exist.
     * <p><b>409</b> - The action was disallowed as it would reduce the currently authenticated user&#39;s permission level or the currently authenticated user has a lower permission level than the user they are attempting to modify.
     * @param name The names of the users (required)
     * @param permission The permission to grant (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setPermissionForUsersWithHttpInfo(List<String> name, String permission) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling setPermissionForUsers");
        }
        
        // verify the required parameter 'permission' is set
        if (permission == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permission' when calling setPermissionForUsers");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "name", name));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "permission", permission));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "bearerAuth" };

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/latest/admin/permissions/users", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Update user details
     * Update a user&#39;s details.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The updated user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being updated.
     * <p><b>404</b> - The specified user does not exist.
     * @param userUpdate  (optional)
     * @return RestDetailedUser
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RestDetailedUser updateUserDetails(UserUpdate userUpdate) throws RestClientException {
        return updateUserDetailsWithHttpInfo(userUpdate).getBody();
    }

    /**
     * Update user details
     * Update a user&#39;s details.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>200</b> - The updated user.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being updated.
     * <p><b>404</b> - The specified user does not exist.
     * @param userUpdate  (optional)
     * @return ResponseEntity&lt;RestDetailedUser&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RestDetailedUser> updateUserDetailsWithHttpInfo(UserUpdate userUpdate) throws RestClientException {
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

        ParameterizedTypeReference<RestDetailedUser> localReturnType = new ParameterizedTypeReference<RestDetailedUser>() {};
        return apiClient.invokeAPI("/api/latest/admin/users", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Set password for user
     * Update a user&#39;s password.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource, and may not update the password of a user with greater permissions than themselves.
     * <p><b>204</b> - The user&#39;s password was successfully updated.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user having their password updated.
     * <p><b>404</b> - The specified user does not exist.
     * @param adminPasswordUpdate  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateUserPassword(AdminPasswordUpdate adminPasswordUpdate) throws RestClientException {
        updateUserPasswordWithHttpInfo(adminPasswordUpdate);
    }

    /**
     * Set password for user
     * Update a user&#39;s password.   The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource, and may not update the password of a user with greater permissions than themselves.
     * <p><b>204</b> - The user&#39;s password was successfully updated.
     * <p><b>400</b> - The request was malformed.
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user having their password updated.
     * <p><b>404</b> - The specified user does not exist.
     * @param adminPasswordUpdate  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateUserPasswordWithHttpInfo(AdminPasswordUpdate adminPasswordUpdate) throws RestClientException {
        Object localVarPostBody = adminPasswordUpdate;
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/credentials", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Check user removal
     * Validate if a user can be erased.  A username is only valid for erasure if it exists as the username of a deleted user. This endpoint will return an appropriate error response if the supplied username is invalid for erasure.  This endpoint does &lt;strong&gt;not&lt;/strong&gt; perform the actual user erasure, and will not modify the application in any way.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - the user is erasable
     * <p><b>400</b> - The request was malformed (e.g. if no username was supplied).
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being erased.
     * <p><b>404</b> - The requested username does not exist.
     * <p><b>409</b> - The requested username is the username of a not deleted user.
     * @param name The username of the user to validate erasability for. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void validateErasable(String name) throws RestClientException {
        validateErasableWithHttpInfo(name);
    }

    /**
     * Check user removal
     * Validate if a user can be erased.  A username is only valid for erasure if it exists as the username of a deleted user. This endpoint will return an appropriate error response if the supplied username is invalid for erasure.  This endpoint does &lt;strong&gt;not&lt;/strong&gt; perform the actual user erasure, and will not modify the application in any way.  The authenticated user must have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission to call this resource.
     * <p><b>204</b> - the user is erasable
     * <p><b>400</b> - The request was malformed (e.g. if no username was supplied).
     * <p><b>401</b> - The authenticated user does not have the &lt;strong&gt;ADMIN&lt;/strong&gt; permission or has a lower permission level than the user being erased.
     * <p><b>404</b> - The requested username does not exist.
     * <p><b>409</b> - The requested username is the username of a not deleted user.
     * @param name The username of the user to validate erasability for. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> validateErasableWithHttpInfo(String name) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling validateErasable");
        }
        

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
        return apiClient.invokeAPI("/api/latest/admin/users/erasure", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
