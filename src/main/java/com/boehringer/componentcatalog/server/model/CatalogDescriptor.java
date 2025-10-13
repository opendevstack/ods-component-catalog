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
 * CatalogDescriptor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogDescriptor {

  private String id;

  private String slug;

  public CatalogDescriptor id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "aSdFam...yCg==", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CatalogDescriptor slug(String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Get slug
   * @return slug
   */
  
  @Schema(name = "slug", example = "aSdFam...yCg==", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("slug")
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogDescriptor catalogDescriptor = (CatalogDescriptor) o;
    return Objects.equals(this.id, catalogDescriptor.id) &&
        Objects.equals(this.slug, catalogDescriptor.slug);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, slug);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogDescriptor {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
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

    private CatalogDescriptor instance;

    public Builder() {
      this(new CatalogDescriptor());
    }

    protected Builder(CatalogDescriptor instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogDescriptor value) { 
      this.instance.setId(value.id);
      this.instance.setSlug(value.slug);
      return this;
    }

    public CatalogDescriptor.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public CatalogDescriptor.Builder slug(String slug) {
      this.instance.slug(slug);
      return this;
    }
    
    /**
    * returns a built CatalogDescriptor instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogDescriptor build() {
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
  public static CatalogDescriptor.Builder builder() {
    return new CatalogDescriptor.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogDescriptor.Builder toBuilder() {
    CatalogDescriptor.Builder builder = new CatalogDescriptor.Builder();
    return builder.copyOf(this);
  }

}

