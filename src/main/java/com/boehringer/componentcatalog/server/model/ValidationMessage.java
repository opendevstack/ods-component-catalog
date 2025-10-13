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
 * ValidationMessage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ValidationMessage {

  private String type;

  private String code;

  private String message;

  public ValidationMessage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ValidationMessage(String message) {
    this.message = message;
  }

  public ValidationMessage type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ValidationMessage code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ValidationMessage message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @NotNull 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
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
    ValidationMessage validationMessage = (ValidationMessage) o;
    return Objects.equals(this.type, validationMessage.type) &&
        Objects.equals(this.code, validationMessage.code) &&
        Objects.equals(this.message, validationMessage.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationMessage {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
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

    private ValidationMessage instance;

    public Builder() {
      this(new ValidationMessage());
    }

    protected Builder(ValidationMessage instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationMessage value) { 
      this.instance.setType(value.type);
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      return this;
    }

    public ValidationMessage.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public ValidationMessage.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ValidationMessage.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ValidationMessage instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationMessage build() {
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
  public static ValidationMessage.Builder builder() {
    return new ValidationMessage.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationMessage.Builder toBuilder() {
    ValidationMessage.Builder builder = new ValidationMessage.Builder();
    return builder.copyOf(this);
  }

}

