package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.model.*;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserAction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

import static org.opendevstack.component_catalog.server.mappers.MapperUtils.nullish;
import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.nullableIdEncode;

@Component
@Slf4j
@AllArgsConstructor
public class EntitiesMapper {
    private final CatalogItemUserActionMapper catalogItemUserActionMapper;

    public Catalog asCatalog(CatalogEntityContext catalogEntityCtx) {
        log.debug("Mapping CatalogEntityContext to Catalog: {}", catalogEntityCtx);

        var catalogLinks = catalogEntityCtx.getLinks().stream()
                .map(this::asCatalogLink)
                .toList();

        return Catalog.builder()
                .name(catalogEntityCtx.getName())
                .description(catalogEntityCtx.getDescription())
                .communityPageId(nullableIdEncode(catalogEntityCtx.getCommunityPagePath()))
                .links(catalogLinks)
                .tags(catalogEntityCtx.getTags())
                .build();
    }

    public CatalogDescriptor asCatalogDescriptor(CatalogsCollectionsEntityTarget collectionsTarget) {
        log.debug("Mapping CatalogsCollectionsEntityTarget to CatalogDescriptor: {}", collectionsTarget);

        return CatalogDescriptor.builder()
                .id(nullableIdEncode(collectionsTarget.getUrl()))
                .slug(collectionsTarget.getSlug())
                .build();
    }

    public CatalogItem asCatalogItem(CatalogItemEntityContext catalogItemEntityCtx, List<String> clusters, List<String> userGroups, String projectKey) {
        log.debug("Mapping CatalogItemEntityContext to CatalogItem: {}", catalogItemEntityCtx);

        var catalogItemEntity = catalogItemEntityCtx.getCatalogItemEntity();

        var catalogItemTags = catalogItemEntity.getMetadata().getTags().entrySet().stream()
                        .map(entry -> CatalogItemTag.builder().label(entry.getKey()).options(entry.getValue()).build())
                        .toList();

        var catalogItemUserActions = Optional.ofNullable(catalogItemEntity.getSpec())
                .map(CatalogItemEntitySpec::getUserActions)
                .map(actions -> Arrays.stream(catalogItemEntity.getSpec().getUserActions())
                        .map(entity ->
                                catalogItemUserActionMapper.asCatalogItemUserAction(entity, clusters, userGroups, projectKey, catalogItemEntityCtx.getId()))
                        .toList())
                .orElse(new ArrayList<>());

        var catalogItemRestrictions = Optional.ofNullable(catalogItemEntity.getSpec())
                .map(CatalogItemEntitySpec::getRestrictions)
                .map(EntitiesMapper::asCatalogItemRestriction)
                .orElse(CatalogItemRestriction.builder()
                        .projects(Collections.unmodifiableSet(new LinkedHashSet<>()))
                        .build());

        var catalogItem = CatalogItem.builder()
                .id(catalogItemEntityCtx.getId())
                .path(catalogItemEntityCtx.getPath())
                .title(catalogItemEntity.getMetadata().getName())
                .shortDescription(catalogItemEntity.getMetadata().getShortDescription())
                .descriptionFileId(nullableIdEncode(catalogItemEntityCtx.getDescriptionPath()))
                .imageFileId(nullableIdEncode(catalogItemEntityCtx.getImagePath()))
                .itemSrc(catalogItemEntityCtx.getItemSrc())
                .tags(catalogItemTags)
                .authors(catalogItemEntityCtx.getContributors())
                .date(catalogItemEntityCtx.getLastCommitDateUTC())
                .userActions(catalogItemUserActions)
                .restrictions(catalogItemRestrictions)
                .build();

        log.debug("Resulting CatalogItem: {}", catalogItem);

        return catalogItem;
    }

    public static CatalogItemRestriction asCatalogItemRestriction(CatalogItemEntityRestrictions source) {
        var projects = Optional.ofNullable(source)
                .map(CatalogItemEntityRestrictions::getProjects)
                .map(LinkedHashSet::new)
                .orElse(new LinkedHashSet<>());

        return CatalogItemRestriction.builder()
                .projects(projects)
                .build();
    }

    public static CatalogItemUserAction asCatalogItemUserAction(UserActionEntity userActionEntity) {
        log.debug("Mapping UserActionEntity to CatalogItemUserAction: {}", userActionEntity);

        var userActionEntityParameters = Optional.ofNullable(userActionEntity.getParameters())
                .map(parameters -> Arrays.stream(parameters)
                        .map(EntitiesMapper::asCatalogItemUserActionParameter)
                        .toList())
                .orElse(new ArrayList<>());

        return CatalogItemUserAction.builder()
                .id(userActionEntity.getId())
                .displayName(userActionEntity.getDisplayName())
                .url(userActionEntity.getUrl())
                .triggerMessage(userActionEntity.getTriggerMessage())
                .parameters(userActionEntityParameters)
                .build();
    }

    public static CatalogItemUserActionParameter asCatalogItemUserActionParameter(UserActionEntityParameter itemEntityUserActionParameter) {
        log.debug("Mapping UserActionEntityParameter to CatalogItemUserActionParameter: {}", itemEntityUserActionParameter);

        List<CatalogItemUserActionParameterValidation> validations = Optional.ofNullable(itemEntityUserActionParameter.getValidations())
                .map(itemValidations -> Stream.of(itemValidations)
                        .map(EntitiesMapper::asCatalogItemUserActionParameterValidation)
                        .toList())
                .orElse(Collections.emptyList());

        List<String> defaultValues = Optional.ofNullable(itemEntityUserActionParameter.getDefaultValues())
                .map(values -> Stream.of(values).toList())
                .orElse(Collections.emptyList());

        List<String> options = Optional.ofNullable(itemEntityUserActionParameter.getOptions())
                .map(opt -> Stream.of(opt).toList())
                .orElse(Collections.emptyList());

        List<CatalogItemUserActionParameterLocation> locations = Optional.ofNullable(itemEntityUserActionParameter.getLocations())
                .map(loc ->
                        Stream.of(loc)
                                .map(EntitiesMapper::asCatalogItemUserActionParameterLocation)
                                .toList())
                .orElse(Collections.emptyList());

        return CatalogItemUserActionParameter.builder()
                .name(itemEntityUserActionParameter.getName())
                .type(itemEntityUserActionParameter.getType())
                .required(itemEntityUserActionParameter.isRequired())
                .defaultValue(toJsonNullable(itemEntityUserActionParameter.getDefaultValue()))
                .defaultValues(defaultValues)
                .options(options)
                .locations(locations)
                .label(itemEntityUserActionParameter.getLabel())
                .placeholder(toJsonNullable(itemEntityUserActionParameter.getPlaceholder()))
                .hint(toJsonNullable(itemEntityUserActionParameter.getHint()))
                .visible(itemEntityUserActionParameter.isVisible())
                .validations(validations)
                .build();
    }

    public static CatalogItemUserActionParameterValidation asCatalogItemUserActionParameterValidation(UserActionEntityParameterValidation userActionEntityParameterValidation) {
        return CatalogItemUserActionParameterValidation.builder()
                .regex(userActionEntityParameterValidation.getRegex())
                .errorMessage(userActionEntityParameterValidation.getErrorMessage())
                .build();
    }

    public static CatalogItemUserActionParameterLocation asCatalogItemUserActionParameterLocation(UserActionEntityParameterLocation userActionEntityParameterLocation) {
        return CatalogItemUserActionParameterLocation.builder()
                .location(userActionEntityParameterLocation.getLocation())
                .value(userActionEntityParameterLocation.getValue())
                .build();
    }

    public  UserActionEntity asUserActionsEntity(CatalogItemEntityUserAction catalogItemEntityUserAction) {
        log.debug("Mapping CatalogItemEntityUserAction to UserActionEntity: {}", catalogItemEntityUserAction);

        var catalogItemEntityUserActionParameters = Optional.ofNullable(catalogItemEntityUserAction.getParameters())
                .map(parameters -> Arrays.stream(parameters)
                        .map(this::asUserActionEntityParameter)
                        .toArray(UserActionEntityParameter[]::new))
                .orElse(new UserActionEntityParameter[0]);

        var restrictions = asUserActionsRestrictions(catalogItemEntityUserAction.getRestrictions());

        return UserActionEntity.builder()
                .id(catalogItemEntityUserAction.getId())
                .displayName(catalogItemEntityUserAction.getDisplayName())
                .url(catalogItemEntityUserAction.getUrl())
                .triggerMessage(catalogItemEntityUserAction.getTriggerMessage())
                .parameters(catalogItemEntityUserActionParameters)
                .messagesTitles(catalogItemEntityUserAction.getMessagesTitles())
                .messagesDefinitions(catalogItemEntityUserAction.getMessagesDefinitions())
                .restrictions(restrictions)
                .build();
    }

    public UserActionEntityRestrictions asUserActionsRestrictions(UserActionEntityRestrictions userActionEntityRestrictions) {
        log.debug("Mapping UserActionRestrictions to UserActionEntityRestrictions: {}", userActionEntityRestrictions);

        if (userActionEntityRestrictions != null) {
            return UserActionEntityRestrictions.builder()
                    .oneTimeOnly(userActionEntityRestrictions.isOneTimeOnly())
                    .projects(userActionEntityRestrictions.getProjects())
                    .locations(userActionEntityRestrictions.getLocations())
                    .build();
        } else {
            return null;
        }
    }

    public  UserActionEntityParameter asUserActionEntityParameter(CatalogItemEntityUserActionParameter catalogItemEntityUserActionParameter) {
        log.debug("Mapping CatalogItemEntityUserActionParameter to UserActionEntityParameter: {}", catalogItemEntityUserActionParameter);

        return UserActionEntityParameter.builder()
                .name(catalogItemEntityUserActionParameter.getName())
                .type(catalogItemEntityUserActionParameter.getType())
                .required(catalogItemEntityUserActionParameter.isRequired())
                .defaultValue(catalogItemEntityUserActionParameter.getDefaultValue())
                .defaultValues(catalogItemEntityUserActionParameter.getDefaultValues())
                .options(catalogItemEntityUserActionParameter.getOptions())
                .label(catalogItemEntityUserActionParameter.getLabel())
                .placeholder(catalogItemEntityUserActionParameter.getPlaceholder())
                .hint(catalogItemEntityUserActionParameter.getHint())
                .visible(catalogItemEntityUserActionParameter.isVisible())
                .build();
    }

    public  CatalogLink asCatalogLink(CatalogEntityLink catalogEntityLink) {
        log.debug("Mapping CatalogEntityLink to CatalogLink: {}", catalogEntityLink);

        return CatalogLink.builder()
                .name(catalogEntityLink.getName())
                .url(catalogEntityLink.getUrl())
                .build();
    }

    public  CatalogItemUserActionMessageDefinition asCatalogItemUserActionMessageDefinition(UserActionEntityMessageTitle userActionEntityMessageTitle,
                                                                                                  UserActionEntityMessageDefinition userActionEntityMessageDefinition) {
        log.debug("Mapping UserActionEntityMessageTitle and UserActionEntityMessageDefinition to CatalogItemUserActionMessageDefinition: {}, {}", userActionEntityMessageTitle, userActionEntityMessageDefinition);

        var itemUserActionMessageDefinition = asCatalogItemUserActionMessageDefinition(userActionEntityMessageDefinition);
        itemUserActionMessageDefinition.setTitle(userActionEntityMessageTitle.getTitle());

        return CatalogItemUserActionMessageDefinition.builder()
                .id(userActionEntityMessageDefinition.getId())
                .type(itemUserActionMessageDefinition.getType())
                .title(itemUserActionMessageDefinition.getTitle())
                .message(itemUserActionMessageDefinition.getMessage())
                .createsIncident(itemUserActionMessageDefinition.getCreatesIncident())
                .build();
    }

    public  CatalogItemUserActionMessageDefinition asCatalogItemUserActionMessageDefinition(UserActionEntityMessageDefinition userActionEntityMessageDefinition) {
        log.debug("Mapping UserActionEntityMessageDefinition to CatalogItemUserActionMessageDefinition: {}", userActionEntityMessageDefinition);

        var type = asCatalogItemUserActionMessageType(userActionEntityMessageDefinition.getType());

        return CatalogItemUserActionMessageDefinition.builder()
                .id(userActionEntityMessageDefinition.getId())
                .type(type)
                .message(userActionEntityMessageDefinition.getMessage())
                .createsIncident(userActionEntityMessageDefinition.isCreatesIncident())
                .build();
    }

    public  CatalogItemUserActionMessageType asCatalogItemUserActionMessageType(UserActionEntityMessageType userActionEntityMessageType) {
        log.debug("Mapping UserActionEntityMessageType: {}", userActionEntityMessageType);

        return switch (userActionEntityMessageType) {
            case SUCCESS -> CatalogItemUserActionMessageType.SUCCESS;
            case ERROR -> CatalogItemUserActionMessageType.ERROR;
        };

    }

    public  ValidationMessage asValidationMessage(com.networknt.schema.ValidationMessage validationMessage) {
        return ValidationMessage.builder()
                .code(validationMessage.getCode())
                .message(validationMessage.getMessage())
                .type(validationMessage.getType())
                .build();
    }

    public static CatalogItemUserActionParameter overrideNullFields(CatalogItemUserActionParameter src, CatalogItemUserActionParameter dest) {
        log.debug("Overriding null/empty fields from CatalogItemUserActionParameter: {} from default {}", src, dest);

        return CatalogItemUserActionParameter.builder()
                .name(overrideNullFields(src.getName(), dest.getName()))
                .type(overrideNullFields(src.getType(), dest.getType()))
                .required(overrideNullFields(src.getRequired(), dest.getRequired()))
                .defaultValue(overrideNullFields(src.getDefaultValue(), dest.getDefaultValue()))
                .defaultValues(overrideNullFields(src.getDefaultValues(), dest.getDefaultValues()))
                .locations(overrideNullFields(src.getLocations(), dest.getLocations()))
                .options(overrideNullFields(src.getOptions(), dest.getOptions()))
                .label(overrideNullFields(src.getLabel(), dest.getLabel()))
                .placeholder(overrideNullFields(src.getPlaceholder(), dest.getPlaceholder()))
                .hint(overrideNullFields(src.getHint(), dest.getHint()))
                .visible(overrideNullFields(src.getVisible(), dest.getVisible()))
                .validations(overrideNullFields(src.getValidations(), dest.getValidations()))
                .build();
    }

    public static CatalogItemUserAction overrideNullFields(CatalogItemUserAction baseValues, CatalogItemUserAction userConfiguredValues) {
        log.debug("Overriding null/empty fields from CatalogItemUserAction: {} from default {}", baseValues, userConfiguredValues);

        // This was calculated above, and it should always be correct, but it is meaninful incorrect, as methods should have no dependencies.
        // We need to fix this, and apply the merge in here somehow.
        var parameters = userConfiguredValues.getParameters();


        return CatalogItemUserAction.builder()
                .id(overrideNullFields(baseValues.getId(), userConfiguredValues.getId()))
                .displayName(overrideNullFields(baseValues.getDisplayName(), userConfiguredValues.getDisplayName()))
                .url(overrideNullFields(baseValues.getUrl(), userConfiguredValues.getUrl()))
                .triggerMessage(overrideNullFields(baseValues.getTriggerMessage(), userConfiguredValues.getTriggerMessage()))
                .requestable(overrideNullFields(baseValues.getRequestable(), userConfiguredValues.getRequestable()))
                .restrictionMessage(overrideNullFields(baseValues.getRestrictionMessage(), userConfiguredValues.getRestrictionMessage()))
                .parameters(parameters)
                .build();
    }

    public static <T> T overrideNullFields(T baseValue, T userConfiguredValue) {
        if (userConfiguredValue == null) {
            return baseValue;
        } else {
            return userConfiguredValue;
        }
    }

    public static <T> JsonNullable<T> overrideNullFields(JsonNullable<T> baseValue, JsonNullable<T> userConfiguredValue) {
        if (nullish(userConfiguredValue)) {
            return baseValue;
        } else {
            return userConfiguredValue;
        }
    }

    private static JsonNullable<String> toJsonNullable(String value) {
        return value == null ? JsonNullable.undefined() : JsonNullable.of(value);
    }

}
