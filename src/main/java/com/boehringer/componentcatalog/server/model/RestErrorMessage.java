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
 * RestErrorMessage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class RestErrorMessage {

  private String message;

  public RestErrorMessage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestErrorMessage(String message) {
    this.message = message;
  }

  public RestErrorMessage message(String message) {
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
    RestErrorMessage restErrorMessage = (RestErrorMessage) o;
    return Objects.equals(this.message, restErrorMessage.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestErrorMessage {\n");
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

    private RestErrorMessage instance;

    public Builder() {
      this(new RestErrorMessage());
    }

    protected Builder(RestErrorMessage instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RestErrorMessage value) { 
      this.instance.setMessage(value.message);
      return this;
    }

    public RestErrorMessage.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built RestErrorMessage instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RestErrorMessage build() {
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
  public static RestErrorMessage.Builder builder() {
    return new RestErrorMessage.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RestErrorMessage.Builder toBuilder() {
    RestErrorMessage.Builder builder = new RestErrorMessage.Builder();
    return builder.copyOf(this);
  }

}

