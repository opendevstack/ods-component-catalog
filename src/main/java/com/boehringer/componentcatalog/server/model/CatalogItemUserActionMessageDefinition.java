package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionMessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItemUserActionMessageDefinition
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogItemUserActionMessageDefinition {

  private String id;

  private CatalogItemUserActionMessageType type;

  private String title;

  private String message;

  public CatalogItemUserActionMessageDefinition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "OPENSHIFT_CONNECTION_ERROR", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CatalogItemUserActionMessageDefinition type(CatalogItemUserActionMessageType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public CatalogItemUserActionMessageType getType() {
    return type;
  }

  public void setType(CatalogItemUserActionMessageType type) {
    this.type = type;
  }

  public CatalogItemUserActionMessageDefinition title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  
  @Schema(name = "title", example = "An error occurred while connecting to OpenShift", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CatalogItemUserActionMessageDefinition message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Authorization error: please check your user credentials for deployment  and try again later. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemUserActionMessageDefinition catalogItemUserActionMessageDefinition = (CatalogItemUserActionMessageDefinition) o;
    return Objects.equals(this.id, catalogItemUserActionMessageDefinition.id) &&
        Objects.equals(this.type, catalogItemUserActionMessageDefinition.type) &&
        Objects.equals(this.title, catalogItemUserActionMessageDefinition.title) &&
        Objects.equals(this.message, catalogItemUserActionMessageDefinition.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, title, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemUserActionMessageDefinition {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

    private CatalogItemUserActionMessageDefinition instance;

    public Builder() {
      this(new CatalogItemUserActionMessageDefinition());
    }

    protected Builder(CatalogItemUserActionMessageDefinition instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemUserActionMessageDefinition value) { 
      this.instance.setId(value.id);
      this.instance.setType(value.type);
      this.instance.setTitle(value.title);
      this.instance.setMessage(value.message);
      return this;
    }

    public CatalogItemUserActionMessageDefinition.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public CatalogItemUserActionMessageDefinition.Builder type(CatalogItemUserActionMessageType type) {
      this.instance.type(type);
      return this;
    }
    
    public CatalogItemUserActionMessageDefinition.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public CatalogItemUserActionMessageDefinition.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built CatalogItemUserActionMessageDefinition instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemUserActionMessageDefinition build() {
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
  public static CatalogItemUserActionMessageDefinition.Builder builder() {
    return new CatalogItemUserActionMessageDefinition.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemUserActionMessageDefinition.Builder toBuilder() {
    CatalogItemUserActionMessageDefinition.Builder builder = new CatalogItemUserActionMessageDefinition.Builder();
    return builder.copyOf(this);
  }

}

