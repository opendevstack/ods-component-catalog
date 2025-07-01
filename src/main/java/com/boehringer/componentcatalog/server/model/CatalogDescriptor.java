package com.boehringer.componentcatalog.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * CatalogDescriptor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T15:29:02.750449100+02:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
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

    public Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public Builder slug(String slug) {
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
  public static Builder builder() {
    return new Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Builder toBuilder() {
    Builder builder = new Builder();
    return builder.copyOf(this);
  }

}

