package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItemUserActionParameterLocation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogItemUserActionParameterLocation {

  private String location;

  private String value;

  public CatalogItemUserActionParameterLocation location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   */
  
  @Schema(name = "location", example = "EU", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public CatalogItemUserActionParameterLocation value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", example = "1234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemUserActionParameterLocation catalogItemUserActionParameterLocation = (CatalogItemUserActionParameterLocation) o;
    return Objects.equals(this.location, catalogItemUserActionParameterLocation.location) &&
        Objects.equals(this.value, catalogItemUserActionParameterLocation.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemUserActionParameterLocation {\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

    private CatalogItemUserActionParameterLocation instance;

    public Builder() {
      this(new CatalogItemUserActionParameterLocation());
    }

    protected Builder(CatalogItemUserActionParameterLocation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemUserActionParameterLocation value) { 
      this.instance.setLocation(value.location);
      this.instance.setValue(value.value);
      return this;
    }

    public CatalogItemUserActionParameterLocation.Builder location(String location) {
      this.instance.location(location);
      return this;
    }
    
    public CatalogItemUserActionParameterLocation.Builder value(String value) {
      this.instance.value(value);
      return this;
    }
    
    /**
    * returns a built CatalogItemUserActionParameterLocation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemUserActionParameterLocation build() {
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
  public static CatalogItemUserActionParameterLocation.Builder builder() {
    return new CatalogItemUserActionParameterLocation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemUserActionParameterLocation.Builder toBuilder() {
    CatalogItemUserActionParameterLocation.Builder builder = new CatalogItemUserActionParameterLocation.Builder();
    return builder.copyOf(this);
  }

}

