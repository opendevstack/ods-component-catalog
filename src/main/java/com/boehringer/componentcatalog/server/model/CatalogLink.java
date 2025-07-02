package com.boehringer.componentcatalog.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * CatalogLink
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-11T10:37:51.884432900+02:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class CatalogLink {

  private String url;

  private String name;

  public CatalogLink url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
   */
  
  @Schema(name = "url", example = "http://some-link.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public CatalogLink name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "aSdFam...yCg==", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogLink catalogLink = (CatalogLink) o;
    return Objects.equals(this.url, catalogLink.url) &&
        Objects.equals(this.name, catalogLink.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogLink {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

    private CatalogLink instance;

    public Builder() {
      this(new CatalogLink());
    }

    protected Builder(CatalogLink instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogLink value) { 
      this.instance.setUrl(value.url);
      this.instance.setName(value.name);
      return this;
    }

    public Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    /**
    * returns a built CatalogLink instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogLink build() {
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

