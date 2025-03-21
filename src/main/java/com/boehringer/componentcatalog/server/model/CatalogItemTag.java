package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.LinkedHashSet;
import java.util.Set;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItemTag
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-19T10:54:28.944058747+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class CatalogItemTag {

  private String label;

  @Valid
  private Set<String> options = new LinkedHashSet<>();

  public CatalogItemTag label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
   */
  
  @Schema(name = "label", example = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public CatalogItemTag options(Set<String> options) {
    this.options = options;
    return this;
  }

  public CatalogItemTag addOptionsItem(String optionsItem) {
    if (this.options == null) {
      this.options = new LinkedHashSet<>();
    }
    this.options.add(optionsItem);
    return this;
  }

  /**
   * Get options
   * @return options
   */
  
  @Schema(name = "options", example = "[\"some-data-option\",\"some-other-data-option\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("options")
  public Set<String> getOptions() {
    return options;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setOptions(Set<String> options) {
    this.options = options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemTag catalogItemTag = (CatalogItemTag) o;
    return Objects.equals(this.label, catalogItemTag.label) &&
        Objects.equals(this.options, catalogItemTag.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemTag {\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

    private CatalogItemTag instance;

    public Builder() {
      this(new CatalogItemTag());
    }

    protected Builder(CatalogItemTag instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemTag value) { 
      this.instance.setLabel(value.label);
      this.instance.setOptions(value.options);
      return this;
    }

    public CatalogItemTag.Builder label(String label) {
      this.instance.label(label);
      return this;
    }
    
    public CatalogItemTag.Builder options(Set<String> options) {
      this.instance.options(options);
      return this;
    }
    
    /**
    * returns a built CatalogItemTag instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemTag build() {
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
  public static CatalogItemTag.Builder builder() {
    return new CatalogItemTag.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemTag.Builder toBuilder() {
    CatalogItemTag.Builder builder = new CatalogItemTag.Builder();
    return builder.copyOf(this);
  }

}

