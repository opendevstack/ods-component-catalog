package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentcatalog.server.model.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ValidationResult {

  private Boolean valid;

  @Valid
  private List<@Valid ValidationMessage> errors = new ArrayList<>();

  public ValidationResult valid(Boolean valid) {
    this.valid = valid;
    return this;
  }

  /**
   * Get valid
   * @return valid
   */
  
  @Schema(name = "valid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valid")
  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public ValidationResult errors(List<@Valid ValidationMessage> errors) {
    this.errors = errors;
    return this;
  }

  public ValidationResult addErrorsItem(ValidationMessage errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
   */
  @Valid 
  @Schema(name = "errors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errors")
  public List<@Valid ValidationMessage> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid ValidationMessage> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationResult validationResult = (ValidationResult) o;
    return Objects.equals(this.valid, validationResult.valid) &&
        Objects.equals(this.errors, validationResult.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valid, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationResult {\n");
    sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

    private ValidationResult instance;

    public Builder() {
      this(new ValidationResult());
    }

    protected Builder(ValidationResult instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationResult value) { 
      this.instance.setValid(value.valid);
      this.instance.setErrors(value.errors);
      return this;
    }

    public ValidationResult.Builder valid(Boolean valid) {
      this.instance.valid(valid);
      return this;
    }
    
    public ValidationResult.Builder errors(List<@Valid ValidationMessage> errors) {
      this.instance.errors(errors);
      return this;
    }
    
    /**
    * returns a built ValidationResult instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationResult build() {
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
  public static ValidationResult.Builder builder() {
    return new ValidationResult.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationResult.Builder toBuilder() {
    ValidationResult.Builder builder = new ValidationResult.Builder();
    return builder.copyOf(this);
  }

}

