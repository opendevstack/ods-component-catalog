package com.boehringer.componentcatalog.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Catalog
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T15:55:16.196547600+02:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class Catalog {

  private String name;

  private String description;

  private String communityPageId;

  @Valid
  private List<@Valid CatalogLink> links = new ArrayList<>();

  @Valid
  private List<String> tags = new ArrayList<>();

  public Catalog name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */

  @Schema(name = "name", example = "catalog-name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Catalog description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */

  @Schema(name = "description", example = "A brief description for a catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Catalog communityPageId(String communityPageId) {
    this.communityPageId = communityPageId;
    return this;
  }

  /**
   * Get communityPageId
   * @return communityPageId
   */

  @Schema(name = "communityPageId", example = "aSdFam...yCg==", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("communityPageId")
  public String getCommunityPageId() {
    return communityPageId;
  }

  public void setCommunityPageId(String communityPageId) {
    this.communityPageId = communityPageId;
  }

  public Catalog links(List<@Valid CatalogLink> links) {
    this.links = links;
    return this;
  }

  public Catalog addLinksItem(CatalogLink linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<>();
    }
    this.links.add(linksItem);
    return this;
  }

  /**
   * Get links
   * @return links
   */
  @Valid
  @Schema(name = "links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("links")
  public List<@Valid CatalogLink> getLinks() {
    return links;
  }

  public void setLinks(List<@Valid CatalogLink> links) {
    this.links = links;
  }

  public Catalog tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public Catalog addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   */

  @Schema(name = "tags", example = "[\"tasks\",\"technologies\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Catalog catalog = (Catalog) o;
    return Objects.equals(this.name, catalog.name) &&
            Objects.equals(this.description, catalog.description) &&
            Objects.equals(this.communityPageId, catalog.communityPageId) &&
            Objects.equals(this.links, catalog.links) &&
            Objects.equals(this.tags, catalog.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, communityPageId, links, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Catalog {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    communityPageId: ").append(toIndentedString(communityPageId)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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

    private Catalog instance;

    public Builder() {
      this(new Catalog());
    }

    protected Builder(Catalog instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Catalog value) {
      this.instance.setName(value.name);
      this.instance.setDescription(value.description);
      this.instance.setCommunityPageId(value.communityPageId);
      this.instance.setLinks(value.links);
      this.instance.setTags(value.tags);
      return this;
    }

    public Catalog.Builder name(String name) {
      this.instance.name(name);
      return this;
    }

    public Catalog.Builder description(String description) {
      this.instance.description(description);
      return this;
    }

    public Catalog.Builder communityPageId(String communityPageId) {
      this.instance.communityPageId(communityPageId);
      return this;
    }

    public Catalog.Builder links(List<@Valid CatalogLink> links) {
      this.instance.links(links);
      return this;
    }

    public Catalog.Builder tags(List<String> tags) {
      this.instance.tags(tags);
      return this;
    }

    /**
     * returns a built Catalog instance.
     *
     * The builder is not reusable (NullPointerException)
     */
    public Catalog build() {
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
  public static Catalog.Builder builder() {
    return new Catalog.Builder();
  }

  /**
   * Create a builder with a shallow copy of this instance.
   */
  public Catalog.Builder toBuilder() {
    Catalog.Builder builder = new Catalog.Builder();
    return builder.copyOf(this);
  }

}

