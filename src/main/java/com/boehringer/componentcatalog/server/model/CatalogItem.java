package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentcatalog.server.model.CatalogItemTag;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-19T10:54:28.944058747+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class CatalogItem {

  private String id;

  private String path;

  private String title;

  private String shortDescription;

  private String descriptionFileId;

  private String imageFileId;

  private String itemSrc;

  @Valid
  private List<@Valid CatalogItemTag> tags = new ArrayList<>();

  @Valid
  private List<String> authors = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  public CatalogItem() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CatalogItem(String id, String title, String shortDescription, List<String> authors, OffsetDateTime date) {
    this.id = id;
    this.title = title;
    this.shortDescription = shortDescription;
    this.authors = authors;
    this.date = date;
  }

  public CatalogItem id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "aSdFam...yCg==", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CatalogItem path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
   */
  
  @Schema(name = "path", example = "projects/SOMEPROJECT/repos/some-repo/raw/CatalogItem.yaml?at=refs/heads/master", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public CatalogItem title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "An item title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CatalogItem shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

  /**
   * Get shortDescription
   * @return shortDescription
   */
  @NotNull 
  @Schema(name = "shortDescription", example = "This is a short description for the item", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("shortDescription")
  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public CatalogItem descriptionFileId(String descriptionFileId) {
    this.descriptionFileId = descriptionFileId;
    return this;
  }

  /**
   * Get descriptionFileId
   * @return descriptionFileId
   */
  
  @Schema(name = "descriptionFileId", example = "cHJvam...0ZXIK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("descriptionFileId")
  public String getDescriptionFileId() {
    return descriptionFileId;
  }

  public void setDescriptionFileId(String descriptionFileId) {
    this.descriptionFileId = descriptionFileId;
  }

  public CatalogItem imageFileId(String imageFileId) {
    this.imageFileId = imageFileId;
    return this;
  }

  /**
   * Get imageFileId
   * @return imageFileId
   */
  
  @Schema(name = "imageFileId", example = "cHJvam...YXN0Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imageFileId")
  public String getImageFileId() {
    return imageFileId;
  }

  public void setImageFileId(String imageFileId) {
    this.imageFileId = imageFileId;
  }

  public CatalogItem itemSrc(String itemSrc) {
    this.itemSrc = itemSrc;
    return this;
  }

  /**
   * Get itemSrc
   * @return itemSrc
   */
  
  @Schema(name = "itemSrc", example = "https://bitbucket.some-company.com/projects/SOMEPROJECT/repos/some-repo/browse/CatalogItem.yaml?at=refs/heads/master", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("itemSrc")
  public String getItemSrc() {
    return itemSrc;
  }

  public void setItemSrc(String itemSrc) {
    this.itemSrc = itemSrc;
  }

  public CatalogItem tags(List<@Valid CatalogItemTag> tags) {
    this.tags = tags;
    return this;
  }

  public CatalogItem addTagsItem(CatalogItemTag tagsItem) {
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
  @Valid 
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<@Valid CatalogItemTag> getTags() {
    return tags;
  }

  public void setTags(List<@Valid CatalogItemTag> tags) {
    this.tags = tags;
  }

  public CatalogItem authors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public CatalogItem addAuthorsItem(String authorsItem) {
    if (this.authors == null) {
      this.authors = new ArrayList<>();
    }
    this.authors.add(authorsItem);
    return this;
  }

  /**
   * Get authors
   * @return authors
   */
  @NotNull 
  @Schema(name = "authors", example = "[\"@SomeAuthor\",\"@SomeOtherAuthor\"]", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("authors")
  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public CatalogItem date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @NotNull @Valid 
  @Schema(name = "date", example = "2021-07-01T00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItem catalogItem = (CatalogItem) o;
    return Objects.equals(this.id, catalogItem.id) &&
        Objects.equals(this.path, catalogItem.path) &&
        Objects.equals(this.title, catalogItem.title) &&
        Objects.equals(this.shortDescription, catalogItem.shortDescription) &&
        Objects.equals(this.descriptionFileId, catalogItem.descriptionFileId) &&
        Objects.equals(this.imageFileId, catalogItem.imageFileId) &&
        Objects.equals(this.itemSrc, catalogItem.itemSrc) &&
        Objects.equals(this.tags, catalogItem.tags) &&
        Objects.equals(this.authors, catalogItem.authors) &&
        Objects.equals(this.date, catalogItem.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, path, title, shortDescription, descriptionFileId, imageFileId, itemSrc, tags, authors, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItem {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
    sb.append("    descriptionFileId: ").append(toIndentedString(descriptionFileId)).append("\n");
    sb.append("    imageFileId: ").append(toIndentedString(imageFileId)).append("\n");
    sb.append("    itemSrc: ").append(toIndentedString(itemSrc)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

    private CatalogItem instance;

    public Builder() {
      this(new CatalogItem());
    }

    protected Builder(CatalogItem instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItem value) { 
      this.instance.setId(value.id);
      this.instance.setPath(value.path);
      this.instance.setTitle(value.title);
      this.instance.setShortDescription(value.shortDescription);
      this.instance.setDescriptionFileId(value.descriptionFileId);
      this.instance.setImageFileId(value.imageFileId);
      this.instance.setItemSrc(value.itemSrc);
      this.instance.setTags(value.tags);
      this.instance.setAuthors(value.authors);
      this.instance.setDate(value.date);
      return this;
    }

    public CatalogItem.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public CatalogItem.Builder path(String path) {
      this.instance.path(path);
      return this;
    }
    
    public CatalogItem.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public CatalogItem.Builder shortDescription(String shortDescription) {
      this.instance.shortDescription(shortDescription);
      return this;
    }
    
    public CatalogItem.Builder descriptionFileId(String descriptionFileId) {
      this.instance.descriptionFileId(descriptionFileId);
      return this;
    }
    
    public CatalogItem.Builder imageFileId(String imageFileId) {
      this.instance.imageFileId(imageFileId);
      return this;
    }
    
    public CatalogItem.Builder itemSrc(String itemSrc) {
      this.instance.itemSrc(itemSrc);
      return this;
    }
    
    public CatalogItem.Builder tags(List<@Valid CatalogItemTag> tags) {
      this.instance.tags(tags);
      return this;
    }
    
    public CatalogItem.Builder authors(List<String> authors) {
      this.instance.authors(authors);
      return this;
    }
    
    public CatalogItem.Builder date(OffsetDateTime date) {
      this.instance.date(date);
      return this;
    }
    
    /**
    * returns a built CatalogItem instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItem build() {
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
  public static CatalogItem.Builder builder() {
    return new CatalogItem.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItem.Builder toBuilder() {
    CatalogItem.Builder builder = new CatalogItem.Builder();
    return builder.copyOf(this);
  }

}

