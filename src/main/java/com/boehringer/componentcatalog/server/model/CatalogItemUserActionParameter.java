package com.boehringer.componentcatalog.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionParameterLocation;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionParameterValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CatalogItemUserActionParameter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CatalogItemUserActionParameter {

  private String name;

  private String type;

  private Boolean required;

  private JsonNullable<String> defaultValue = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<List<String>> defaultValues = JsonNullable.<List<String>>undefined();

  @Valid
  private JsonNullable<List<String>> options = JsonNullable.<List<String>>undefined();

  @Valid
  private JsonNullable<List<@Valid CatalogItemUserActionParameterLocation>> locations = JsonNullable.<List<@Valid CatalogItemUserActionParameterLocation>>undefined();

  private String label;

  private JsonNullable<String> placeholder = JsonNullable.<String>undefined();

  private JsonNullable<String> hint = JsonNullable.<String>undefined();

  private Boolean visible;

  @Valid
  private JsonNullable<List<@Valid CatalogItemUserActionParameterValidation>> validations = JsonNullable.<List<@Valid CatalogItemUserActionParameterValidation>>undefined();

  public CatalogItemUserActionParameter name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "workflow", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CatalogItemUserActionParameter type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CatalogItemUserActionParameter required(Boolean required) {
    this.required = required;
    return this;
  }

  /**
   * Get required
   * @return required
   */
  
  @Schema(name = "required", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("required")
  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public CatalogItemUserActionParameter defaultValue(String defaultValue) {
    this.defaultValue = JsonNullable.of(defaultValue);
    return this;
  }

  /**
   * Get defaultValue
   * @return defaultValue
   */
  
  @Schema(name = "defaultValue", example = "123", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("defaultValue")
  public JsonNullable<String> getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(JsonNullable<String> defaultValue) {
    this.defaultValue = defaultValue;
  }

  public CatalogItemUserActionParameter defaultValues(List<String> defaultValues) {
    this.defaultValues = JsonNullable.of(defaultValues);
    return this;
  }

  public CatalogItemUserActionParameter addDefaultValuesItem(String defaultValuesItem) {
    if (this.defaultValues == null || !this.defaultValues.isPresent()) {
      this.defaultValues = JsonNullable.of(new ArrayList<>());
    }
    this.defaultValues.get().add(defaultValuesItem);
    return this;
  }

  /**
   * Get defaultValues
   * @return defaultValues
   */
  
  @Schema(name = "defaultValues", example = "[\"some-data-option\",\"some-other-data-option\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("defaultValues")
  public JsonNullable<List<String>> getDefaultValues() {
    return defaultValues;
  }

  public void setDefaultValues(JsonNullable<List<String>> defaultValues) {
    this.defaultValues = defaultValues;
  }

  public CatalogItemUserActionParameter options(List<String> options) {
    this.options = JsonNullable.of(options);
    return this;
  }

  public CatalogItemUserActionParameter addOptionsItem(String optionsItem) {
    if (this.options == null || !this.options.isPresent()) {
      this.options = JsonNullable.of(new ArrayList<>());
    }
    this.options.get().add(optionsItem);
    return this;
  }

  /**
   * Get options
   * @return options
   */
  
  @Schema(name = "options", example = "[\"some-data-option\",\"some-other-data-option\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("options")
  public JsonNullable<List<String>> getOptions() {
    return options;
  }

  public void setOptions(JsonNullable<List<String>> options) {
    this.options = options;
  }

  public CatalogItemUserActionParameter locations(List<@Valid CatalogItemUserActionParameterLocation> locations) {
    this.locations = JsonNullable.of(locations);
    return this;
  }

  public CatalogItemUserActionParameter addLocationsItem(CatalogItemUserActionParameterLocation locationsItem) {
    if (this.locations == null || !this.locations.isPresent()) {
      this.locations = JsonNullable.of(new ArrayList<>());
    }
    this.locations.get().add(locationsItem);
    return this;
  }

  /**
   * Get locations
   * @return locations
   */
  @Valid 
  @Schema(name = "locations", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("locations")
  public JsonNullable<List<@Valid CatalogItemUserActionParameterLocation>> getLocations() {
    return locations;
  }

  public void setLocations(JsonNullable<List<@Valid CatalogItemUserActionParameterLocation>> locations) {
    this.locations = locations;
  }

  public CatalogItemUserActionParameter label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
   */
  
  @Schema(name = "label", example = "Workflow to execute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public CatalogItemUserActionParameter placeholder(String placeholder) {
    this.placeholder = JsonNullable.of(placeholder);
    return this;
  }

  /**
   * Get placeholder
   * @return placeholder
   */
  
  @Schema(name = "placeholder", example = "some placeholder for a workflow", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("placeholder")
  public JsonNullable<String> getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder(JsonNullable<String> placeholder) {
    this.placeholder = placeholder;
  }

  public CatalogItemUserActionParameter hint(String hint) {
    this.hint = JsonNullable.of(hint);
    return this;
  }

  /**
   * Get hint
   * @return hint
   */
  
  @Schema(name = "hint", example = "some hint for a workflow", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hint")
  public JsonNullable<String> getHint() {
    return hint;
  }

  public void setHint(JsonNullable<String> hint) {
    this.hint = hint;
  }

  public CatalogItemUserActionParameter visible(Boolean visible) {
    this.visible = visible;
    return this;
  }

  /**
   * Get visible
   * @return visible
   */
  
  @Schema(name = "visible", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("visible")
  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public CatalogItemUserActionParameter validations(List<@Valid CatalogItemUserActionParameterValidation> validations) {
    this.validations = JsonNullable.of(validations);
    return this;
  }

  public CatalogItemUserActionParameter addValidationsItem(CatalogItemUserActionParameterValidation validationsItem) {
    if (this.validations == null || !this.validations.isPresent()) {
      this.validations = JsonNullable.of(new ArrayList<>());
    }
    this.validations.get().add(validationsItem);
    return this;
  }

  /**
   * Get validations
   * @return validations
   */
  @Valid 
  @Schema(name = "validations", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validations")
  public JsonNullable<List<@Valid CatalogItemUserActionParameterValidation>> getValidations() {
    return validations;
  }

  public void setValidations(JsonNullable<List<@Valid CatalogItemUserActionParameterValidation>> validations) {
    this.validations = validations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogItemUserActionParameter catalogItemUserActionParameter = (CatalogItemUserActionParameter) o;
    return Objects.equals(this.name, catalogItemUserActionParameter.name) &&
        Objects.equals(this.type, catalogItemUserActionParameter.type) &&
        Objects.equals(this.required, catalogItemUserActionParameter.required) &&
        equalsNullable(this.defaultValue, catalogItemUserActionParameter.defaultValue) &&
        equalsNullable(this.defaultValues, catalogItemUserActionParameter.defaultValues) &&
        equalsNullable(this.options, catalogItemUserActionParameter.options) &&
        equalsNullable(this.locations, catalogItemUserActionParameter.locations) &&
        Objects.equals(this.label, catalogItemUserActionParameter.label) &&
        equalsNullable(this.placeholder, catalogItemUserActionParameter.placeholder) &&
        equalsNullable(this.hint, catalogItemUserActionParameter.hint) &&
        Objects.equals(this.visible, catalogItemUserActionParameter.visible) &&
        equalsNullable(this.validations, catalogItemUserActionParameter.validations);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, required, hashCodeNullable(defaultValue), hashCodeNullable(defaultValues), hashCodeNullable(options), hashCodeNullable(locations), label, hashCodeNullable(placeholder), hashCodeNullable(hint), visible, hashCodeNullable(validations));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogItemUserActionParameter {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    defaultValues: ").append(toIndentedString(defaultValues)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    locations: ").append(toIndentedString(locations)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    placeholder: ").append(toIndentedString(placeholder)).append("\n");
    sb.append("    hint: ").append(toIndentedString(hint)).append("\n");
    sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
    sb.append("    validations: ").append(toIndentedString(validations)).append("\n");
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

    private CatalogItemUserActionParameter instance;

    public Builder() {
      this(new CatalogItemUserActionParameter());
    }

    protected Builder(CatalogItemUserActionParameter instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CatalogItemUserActionParameter value) { 
      this.instance.setName(value.name);
      this.instance.setType(value.type);
      this.instance.setRequired(value.required);
      this.instance.setDefaultValue(value.defaultValue);
      this.instance.setDefaultValues(value.defaultValues);
      this.instance.setOptions(value.options);
      this.instance.setLocations(value.locations);
      this.instance.setLabel(value.label);
      this.instance.setPlaceholder(value.placeholder);
      this.instance.setHint(value.hint);
      this.instance.setVisible(value.visible);
      this.instance.setValidations(value.validations);
      return this;
    }

    public CatalogItemUserActionParameter.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder required(Boolean required) {
      this.instance.required(required);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder defaultValue(String defaultValue) {
      this.instance.defaultValue(defaultValue);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder defaultValue(JsonNullable<String> defaultValue) {
      this.instance.defaultValue = defaultValue;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder defaultValues(List<String> defaultValues) {
      this.instance.defaultValues(defaultValues);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder defaultValues(JsonNullable<List<String>> defaultValues) {
      this.instance.defaultValues = defaultValues;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder options(List<String> options) {
      this.instance.options(options);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder options(JsonNullable<List<String>> options) {
      this.instance.options = options;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder locations(List<@Valid CatalogItemUserActionParameterLocation> locations) {
      this.instance.locations(locations);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder locations(JsonNullable<List<@Valid CatalogItemUserActionParameterLocation>> locations) {
      this.instance.locations = locations;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder label(String label) {
      this.instance.label(label);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder placeholder(String placeholder) {
      this.instance.placeholder(placeholder);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder placeholder(JsonNullable<String> placeholder) {
      this.instance.placeholder = placeholder;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder hint(String hint) {
      this.instance.hint(hint);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder hint(JsonNullable<String> hint) {
      this.instance.hint = hint;
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder visible(Boolean visible) {
      this.instance.visible(visible);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder validations(List<@Valid CatalogItemUserActionParameterValidation> validations) {
      this.instance.validations(validations);
      return this;
    }
    
    public CatalogItemUserActionParameter.Builder validations(JsonNullable<List<@Valid CatalogItemUserActionParameterValidation>> validations) {
      this.instance.validations = validations;
      return this;
    }
    
    /**
    * returns a built CatalogItemUserActionParameter instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CatalogItemUserActionParameter build() {
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
  public static CatalogItemUserActionParameter.Builder builder() {
    return new CatalogItemUserActionParameter.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CatalogItemUserActionParameter.Builder toBuilder() {
    CatalogItemUserActionParameter.Builder builder = new CatalogItemUserActionParameter.Builder();
    return builder.copyOf(this);
  }

}

