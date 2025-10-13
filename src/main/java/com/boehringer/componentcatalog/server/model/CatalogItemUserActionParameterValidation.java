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
 * CatalogItemUserActionParameterValidation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogItemUserActionParameterValidation {

  private String regex;

  private String errorMessage;

  public CatalogItemUserActionParameterValidation regex(String regex) {
    this.regex = regex;
    return this;
  }

  /**
   * Get regex
   * @return regex
   */
  
  @Schema(name = "regex", example = "/^[a-z\\s]{0,255}$/i", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("regex")
  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public CatalogItemUserActionParameterValidation errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Get errorMessage
   * @return errorMessage
   */
  
  @Schema(name = "errorMessage", example = "There is an error in the provided value, please check it and try again.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorMessage")
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemUserActionParameterValidation catalogItemUserActionParameterValidation = (CatalogItemUserActionParameterValidation) o;
    return Objects.equals(this.regex, catalogItemUserActionParameterValidation.regex) &&
        Objects.equals(this.errorMessage, catalogItemUserActionParameterValidation.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regex, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemUserActionParameterValidation {\n");
    sb.append("    regex: ").append(toIndentedString(regex)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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

    private CatalogItemUserActionParameterValidation instance;

    public Builder() {
      this(new CatalogItemUserActionParameterValidation());
    }

    protected Builder(CatalogItemUserActionParameterValidation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemUserActionParameterValidation value) { 
      this.instance.setRegex(value.regex);
      this.instance.setErrorMessage(value.errorMessage);
      return this;
    }

    public CatalogItemUserActionParameterValidation.Builder regex(String regex) {
      this.instance.regex(regex);
      return this;
    }
    
    public CatalogItemUserActionParameterValidation.Builder errorMessage(String errorMessage) {
      this.instance.errorMessage(errorMessage);
      return this;
    }
    
    /**
    * returns a built CatalogItemUserActionParameterValidation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemUserActionParameterValidation build() {
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
  public static CatalogItemUserActionParameterValidation.Builder builder() {
    return new CatalogItemUserActionParameterValidation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemUserActionParameterValidation.Builder toBuilder() {
    CatalogItemUserActionParameterValidation.Builder builder = new CatalogItemUserActionParameterValidation.Builder();
    return builder.copyOf(this);
  }

}

