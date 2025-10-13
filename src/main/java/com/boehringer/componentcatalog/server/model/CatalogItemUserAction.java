package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItemUserAction
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogItemUserAction {

  private String id;

  private String displayName;

  private JsonNullable<String> url = JsonNullable.<String>undefined();

  private JsonNullable<String> triggerMessage = JsonNullable.<String>undefined();

  @Valid
  private List<@Valid CatalogItemUserActionParameter> parameters = new ArrayList<>();

  public CatalogItemUserAction id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "CODE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CatalogItemUserAction displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Get displayName
   * @return displayName
   */
  
  @Schema(name = "displayName", example = "View Code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public CatalogItemUserAction url(String url) {
    this.url = JsonNullable.of(url);
    return this;
  }

  /**
   * Get url
   * @return url
   */
  
  @Schema(name = "url", example = "https://quickstarter", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public JsonNullable<String> getUrl() {
    return url;
  }

  public void setUrl(JsonNullable<String> url) {
    this.url = url;
  }

  public CatalogItemUserAction triggerMessage(String triggerMessage) {
    this.triggerMessage = JsonNullable.of(triggerMessage);
    return this;
  }

  /**
   * Get triggerMessage
   * @return triggerMessage
   */
  
  @Schema(name = "triggerMessage", example = "Provisioning a component", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("triggerMessage")
  public JsonNullable<String> getTriggerMessage() {
    return triggerMessage;
  }

  public void setTriggerMessage(JsonNullable<String> triggerMessage) {
    this.triggerMessage = triggerMessage;
  }

  public CatalogItemUserAction parameters(List<@Valid CatalogItemUserActionParameter> parameters) {
    this.parameters = parameters;
    return this;
  }

  public CatalogItemUserAction addParametersItem(CatalogItemUserActionParameter parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

  /**
   * Get parameters
   * @return parameters
   */
  @Valid 
  @Schema(name = "parameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parameters")
  public List<@Valid CatalogItemUserActionParameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<@Valid CatalogItemUserActionParameter> parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemUserAction catalogItemUserAction = (CatalogItemUserAction) o;
    return Objects.equals(this.id, catalogItemUserAction.id) &&
        Objects.equals(this.displayName, catalogItemUserAction.displayName) &&
        equalsNullable(this.url, catalogItemUserAction.url) &&
        equalsNullable(this.triggerMessage, catalogItemUserAction.triggerMessage) &&
        Objects.equals(this.parameters, catalogItemUserAction.parameters);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, displayName, hashCodeNullable(url), hashCodeNullable(triggerMessage), parameters);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemUserAction {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    triggerMessage: ").append(toIndentedString(triggerMessage)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private CatalogItemUserAction instance;

    public Builder() {
      this(new CatalogItemUserAction());
    }

    protected Builder(CatalogItemUserAction instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemUserAction value) { 
      this.instance.setId(value.id);
      this.instance.setDisplayName(value.displayName);
      this.instance.setUrl(value.url);
      this.instance.setTriggerMessage(value.triggerMessage);
      this.instance.setParameters(value.parameters);
      return this;
    }

    public CatalogItemUserAction.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public CatalogItemUserAction.Builder displayName(String displayName) {
      this.instance.displayName(displayName);
      return this;
    }
    
    public CatalogItemUserAction.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public CatalogItemUserAction.Builder url(JsonNullable<String> url) {
      this.instance.url = url;
      return this;
    }
    
    public CatalogItemUserAction.Builder triggerMessage(String triggerMessage) {
      this.instance.triggerMessage(triggerMessage);
      return this;
    }
    
    public CatalogItemUserAction.Builder triggerMessage(JsonNullable<String> triggerMessage) {
      this.instance.triggerMessage = triggerMessage;
      return this;
    }
    
    public CatalogItemUserAction.Builder parameters(List<@Valid CatalogItemUserActionParameter> parameters) {
      this.instance.parameters(parameters);
      return this;
    }
    
    /**
    * returns a built CatalogItemUserAction instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemUserAction build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static CatalogItemUserAction.Builder builder() {
    return new CatalogItemUserAction.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemUserAction.Builder toBuilder() {
    CatalogItemUserAction.Builder builder = new CatalogItemUserAction.Builder();
    return builder.copyOf(this);
  }

}

