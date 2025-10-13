package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.mappers.EntitiesMapper;
import com.boehringer.componentcatalog.server.model.*;
import com.boehringer.componentcatalog.server.services.catalog.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.boehringer.componentcatalog.util.FunctionalUtils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class CatalogApiAdapter {

    private static final String CODE_ID = "CODE";

    private CatalogApiAdapter() {
        // Hide implicit public constructor
    }

    public static CatalogItemUserActionMessageDefinition asCatalogItemUserActionMessageDefinition(
            UserActionEntityMessageTitle userActionEntityMessageTitles,
            UserActionEntityMessageDefinition userActionEntityMessageDefinition) {

        return EntitiesMapper.asCatalogItemUserActionMessageDefinition(userActionEntityMessageTitles, userActionEntityMessageDefinition);
    }

    public static CatalogItem asCatalogItem(CatalogItemEntityContext itemEntityCtx,
                                            UserActionsEntity userActionsEntity,
                                            Set<CatalogEntityPermissionEnum> permissions) {
        log.debug("asCatalogItem. itemEntityCtx: {}, userActionsEntity: {}, permissions: {}", itemEntityCtx, userActionsEntity, permissions);


        // If the principal has *any* permissions, it has access to the repo
        var hasRepoAccess = !permissions.isEmpty();

        var mappedItem = EntitiesMapper.asCatalogItem(itemEntityCtx);

        var item = updateItemIfNoRepoAccess(mappedItem, hasRepoAccess);

        // This his here just in case the "userActions" field is either not defined on CatalogItem.yaml,
        //  or is defined as null/missing, which is not expected, but it happens.
        if (isNull(item.getUserActions())) {
            // If there are no user actions defined on CatalogItem.yaml, just return
            item.userActions(new ArrayList<>());
        }

        var mergedCatalogItemUserActions = finalizeUserActions(item.getUserActions(), userActionsEntity.getSpec().getActions());
        item.setUserActions(mergedCatalogItemUserActions);


        // * The action with id="CODE" is a special case:
        //   * It is always mandatory and customizable, but only its "url" property.
        //   * It doesn't have any parameters, but parameters=[] for consistency.
        //   * If not present on CatalogItem.yaml's userActions list, it will be appended at the end of the list.
        //   * If the "url" property is not set, it will be set during processing to the CatalogItem.yaml's Bitbucket source folder URL.
        //  Tech debt: this "CODE" id we are dealing with here is a hardcoded value,
        //  because it's considered to be a "special" user action, which it shouldn't!


        var userActionsEntitiesCodeUrl = Stream.of(userActionsEntity.getSpec().getActions())
                .filter(ua -> Objects.equals(CODE_ID, ua.getId()))
                .findFirst()
                .map(UserActionEntity::getUrl)
                .orElse(mappedItem.getItemSrc());

        var itemEntityCodeUrl = Optional
                .ofNullable(itemEntityCtx.getCatalogItemEntity().getSpec())
                .map(CatalogItemEntitySpec::getUserActions)
                .map(Arrays::asList)
                .orElse(List.of())
                .stream()
                .filter(ua -> Objects.equals(CODE_ID, ua.getId()))
                .findFirst()
                .map(CatalogItemEntityUserAction::getUrl)
                .orElse(userActionsEntitiesCodeUrl);

        var itemCodeUserAction = item.getUserActions().stream()
                .filter(ua -> Objects.equals(CODE_ID, ua.getId()))
                .findFirst();

        // If the user does not have permissions to the repo, then the CODE user action URL will always be null
        // and the url set by the mapper or on other entities will be overridden by the null value.
        itemCodeUserAction.ifPresent(codeUserAction ->
                 codeUserAction.url(hasRepoAccess ? itemEntityCodeUrl : null));

        return item;
    }

    public static List<CatalogItem> sortCatalogItems(List<CatalogItem> items, SortOrder order) {
        return items.stream()
                .sorted(fieldSorter(CatalogItem::getTitle, order))
                .toList();
    }

    protected static List<CatalogItemUserAction> finalizeUserActions(List<CatalogItemUserAction> itemUserActions, UserActionEntity[] userActionEntities) {
        var userActionEntitiesList = Arrays.asList(userActionEntities);

        var userActions = mapList(
                userActionEntitiesList,
                EntitiesMapper::asCatalogItemUserAction
        );

        var userActionsEntitiesByMandatory = splitBy(
                userActionEntitiesList,
                UserActionEntity::isMandatory
        );

        // Convert the user actions entities, defined on UserActions.yaml to CatalogItemUserAction REST entities,
        // in order to make easier copying information from UserActions.yaml to CatalogItemUserActions REST entities
        var mandatoryUserActionsEntities = mapList(
                userActionsEntitiesByMandatory.getLeft(),
                EntitiesMapper::asCatalogItemUserAction
        );

        var itemUserActionsPairs = fullJoin(
                itemUserActions, CatalogItemUserAction::getId,
                userActions, CatalogItemUserAction::getId
        );

        var customizedItemUserActionsPairs = itemUserActionsPairs
                .stream()
                .filter(pair -> nonNull(pair.getLeft()) && nonNull(pair.getRight()))
                .toList();

        // Complete the missing information on the CatalogItem.yaml's user actions
        // with the information available on the UserActions.yaml


        List<CatalogItemUserAction> customizedItemUserActions = new ArrayList<>();

        customizedItemUserActionsPairs.forEach(pair -> {
            var userConfiguredUserAction = pair.getLeft();
            var baseUserAction = pair.getRight();

            List<CatalogItemUserActionParameter> mergedParameters = new ArrayList<>();

            // Complete null/missing CatalogItem.yaml's user actions params with non-null param values for the same action,
            // but defined on the UserActions.yaml
            userActionEntitiesList
                    .stream()
                    .filter(uae -> Objects.equals(uae.getId(), baseUserAction.getId()))
                    .findFirst()
                    .ifPresent(baseUserActionEntity -> {
                                if (isNull(userConfiguredUserAction.getParameters())) {
                                    userConfiguredUserAction.parameters(new ArrayList<>());
                                }

                                // We need to pass baseUserActionEntity.getParameters() to finalizeUserActionParameters,
                                // due to to the fact that the user action entity parameters contain information
                                // about whether the parameter is customizable or not, which is needed
                                // to finalize the parameters
                                var mergedUserActionParameters = finalizeUserActionParameters(
                                        userConfiguredUserAction.getParameters(),
                                        baseUserActionEntity.getParameters()
                                );

                                mergedParameters.addAll(mergedUserActionParameters);
                            }
                    );

            var mergedCatalogItemUserAction = EntitiesMapper.overrideNullFields(baseUserAction, userConfiguredUserAction);
            mergedCatalogItemUserAction.parameters(mergedParameters);

            customizedItemUserActions.add(mergedCatalogItemUserAction);
        });

        var customizedItemUserActionsIds = select(customizedItemUserActions, CatalogItemUserAction::getId);

        var mandatoryNonCustomizedItemUserActions = mandatoryUserActionsEntities.stream()
                .filter(ua -> !customizedItemUserActionsIds.contains(ua.getId()))
                .toList();

        // Sort according to the informal specification
        var itemUserActionsIds = select(itemUserActions, CatalogItemUserAction::getId);
        var userActionsIds = select(userActions, CatalogItemUserAction::getId);

        // 1. Actions customized on CatalogItem.yaml, same order as in CatalogItem.yaml
        var sortedCustomizedItemUserActions = sortBy(customizedItemUserActions, CatalogItemUserAction::getId, itemUserActionsIds);

        // 2. Actions not present in CatalogItem.yaml and mandatory=true, same order as in UserActions.yaml
        var sortedMandatoryNonCustomizedItemUserActions = sortBy(mandatoryNonCustomizedItemUserActions, CatalogItemUserAction::getId, userActionsIds);

        // The final list of user actions is the union of the two lists, mandatory and not present at the end
        List<CatalogItemUserAction> resultCatalogItemUserActions = new ArrayList<>();

        resultCatalogItemUserActions.addAll(sortedCustomizedItemUserActions);
        resultCatalogItemUserActions.addAll(sortedMandatoryNonCustomizedItemUserActions);

        return resultCatalogItemUserActions;
    }

    private static CatalogItem updateItemIfNoRepoAccess(CatalogItem sourceItem, boolean hasRepoAccess) {
        if (hasRepoAccess) {
            return sourceItem;
        } else {
            // Mind that, if user has no repo access, the path and itemSrc will NOT be populated
            return CatalogItem.builder()
                    .id(sourceItem.getId())
                    .title(sourceItem.getTitle())
                    .shortDescription(sourceItem.getShortDescription())
                    .descriptionFileId(sourceItem.getDescriptionFileId())
                    .imageFileId(sourceItem.getImageFileId())
                    .tags(sourceItem.getTags())
                    .authors(sourceItem.getAuthors())
                    .date(sourceItem.getDate())
                    .userActions(sourceItem.getUserActions())
                    .build();
        }
    }

    private static List<CatalogItemUserActionParameter> finalizeUserActionParameters(List<CatalogItemUserActionParameter> userConfiguredCatalogItemUserActionParameters,
                                                     UserActionEntityParameter[] baseUserActionEntityParameters) {
        var baseUserActionEntityParamsList = Arrays.asList(baseUserActionEntityParameters);

        var baseUserActionParams = mapList(
                baseUserActionEntityParamsList,
                EntitiesMapper::asCatalogItemUserActionParameter
        );

        var baseUserActionEntityParamsByCustomizable = splitBy(
                baseUserActionEntityParamsList,
                UserActionEntityParameter::isCustomizable
        );

        var baseCustomizableUserActionParams = mapList(
                baseUserActionEntityParamsByCustomizable.getLeft(),
                EntitiesMapper::asCatalogItemUserActionParameter
        );

        var baseNonCustomizableUserActionParams = mapList(
                baseUserActionEntityParamsByCustomizable.getRight(),
                EntitiesMapper::asCatalogItemUserActionParameter
        );

        var customizableParamsPairs = fullJoin(
                userConfiguredCatalogItemUserActionParameters, CatalogItemUserActionParameter::getName,
                baseCustomizableUserActionParams, CatalogItemUserActionParameter::getName
        );

        // Matched params: present in both CatalogItem.yaml and UserActions.yaml, customized on CatalogItem.yaml
        var customizedOnItemParamsPairs = customizableParamsPairs
                .stream()
                .filter(pair -> nonNull(pair.getLeft()) && nonNull(pair.getRight()))
                .toList();

        var nonCustomizedOnItemParamsPairs = customizableParamsPairs
                .stream()
                .filter(pair -> isNull(pair.getLeft()) && nonNull(pair.getRight()))
                .toList();

        // Non-matched params: defined on CatalogItem.yaml only
        var definedOnItemParams = customizableParamsPairs.stream()
                .filter(pair -> nonNull(pair.getLeft()) && isNull(pair.getRight()))
                .map(Pair::getLeft)
                .toList();

        // Complete null/missing CatalogItem.yaml params fields with non-null values defined on the UserActions.yaml params
        List<CatalogItemUserActionParameter> customizedOnItemParams = new ArrayList<>();

        customizedOnItemParamsPairs.forEach(pair -> {
            var itemUserActionParam = pair.getLeft();
            var userActionParam = pair.getRight();

            customizedOnItemParams.add(EntitiesMapper.overrideNullFields(userActionParam, itemUserActionParam));
        });

        var nonCustomizedOnItemParams = select(nonCustomizedOnItemParamsPairs, Pair::getRight);

        // Sort parameters according to order of the parameters is defined by the UserActions.yaml,
        // being that order dictated by the order of its names(ids) on those files.
        var userActionParametersIds = select(
                baseUserActionParams,
                CatalogItemUserActionParameter::getName
        );

        var itemUserActionParametersIds = select(
                userConfiguredCatalogItemUserActionParameters,
                CatalogItemUserActionParameter::getName
        );

        // The order of the parameters in a CatalogItem.yaml's userAction will be:
        // 1. Parameters defined in UserActions.yaml only, with customizable=false, same order as in UserActions.yaml
        // 2. Parameters defined in UserActions.yaml, with customizable=true, customized (or not) on CatalogItem.yaml,
        //    same order as in UserActions.yaml
        // If there are both 1. and 2., both parameters sets will be merged and sorted
        // according to the sorting order defined in UserActions.yaml
        List<CatalogItemUserActionParameter> userActionsDefinedParams = new ArrayList<>();

        userActionsDefinedParams.addAll(baseNonCustomizableUserActionParams);
        userActionsDefinedParams.addAll(customizedOnItemParams);
        userActionsDefinedParams.addAll(nonCustomizedOnItemParams);

        var sortedUserActionsDefinedParams = sortBy(userActionsDefinedParams, CatalogItemUserActionParameter::getName, userActionParametersIds);

        // 3. Parameters defined in CatalogItem.yaml only, same order as in CatalogItem.yaml
        var sortedDefinedOnItemParams = sortBy(definedOnItemParams, CatalogItemUserActionParameter::getName, itemUserActionParametersIds);

        // Replace the contents of the list passed as parameter, in order to
        // modify the list as expected by the caller
        List<CatalogItemUserActionParameter> resultItemUserActionParams = new ArrayList<>();

        resultItemUserActionParams.addAll(sortedUserActionsDefinedParams);
        resultItemUserActionParams.addAll(sortedDefinedOnItemParams);

        return resultItemUserActionParams;
    }

    public static List<CatalogItemFilter> catalogItemFiltersFrom(CatalogEntity catalogEntity,
                                                                 List<CatalogItemEntityContext> itemEntitiesCtxs,
                                                                 UserActionsEntity userActionsEntity,
                                                                 Set<CatalogEntityPermissionEnum> permissions) {
        log.debug("catalogItemFiltersFrom. catalogEntity: {}, itemEntitiesCtxs: {}, userActionsEntity: {}, permissions: {}", catalogEntity, itemEntitiesCtxs, userActionsEntity, permissions);

        // Group all tags from all items and group them by label, in order to
        // get pairs: (itemLabel, List<CatalogItemTag> itemTags)
        var itemLabelsByItemTags = itemEntitiesCtxs.stream()
                .map(itemEntityCtx -> CatalogApiAdapter.asCatalogItem(itemEntityCtx, userActionsEntity, permissions))
                .flatMap(item -> item.getTags().stream())
                .collect(Collectors.groupingBy(CatalogItemTag::getLabel));


        var catalogEntityLabels = catalogEntity
                .getMetadata()
                .getSpec()
                .getTags()
                .stream()
                .toList();

        // Keep only the items with labels that match the Catalog.yaml declared tags,
        // and replace item labels with the Catalog.yaml tags
        var catalogLabelsItemTags = itemLabelsByItemTags.entrySet().stream()
                .filter(entry -> catalogEntityLabels.contains(entry.getKey()))
                .sorted(Comparator.comparingInt(entry -> catalogEntityLabels.indexOf(entry.getKey())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));


        // Keep the same order as in the Catalog.yml
        var catalogLabelsItemFilters = catalogLabelsItemTags.entrySet().stream()
                .map(entry -> CatalogApiAdapter.labeledFilterEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        // Return filters according to the order in the Catalog.yaml
        return List.copyOf(catalogLabelsItemFilters.values());
    }

    public static Catalog asCatalog(CatalogEntityContext catalogEntityCtx) {
        return EntitiesMapper.asCatalog(catalogEntityCtx);
    }

    public static List<CatalogDescriptor> asCatalogDescriptors(CatalogsCollectionsEntity catalogsCollectionsEntity) {
        return Arrays.stream(catalogsCollectionsEntity.getMetadata().getSpec().getTargets())
                .map(EntitiesMapper::asCatalogDescriptor)
                .toList();
    }

    private static Map.Entry<String, CatalogItemFilter> labeledFilterEntry(String catalogLabel, List<CatalogItemTag> itemTags) {
        // Remove options duplicated on different catalog item tags
        var filterOptions = itemTags.stream()
                .flatMap(tag -> tag.getOptions().stream())
                .collect(Collectors.toSet());

        // Build filter for that label, with the unique options selectable for that filter
        var itemFilter = CatalogItemFilter.builder()
                .label(catalogLabel)
                .options(filterOptions)
                .build();

        return Map.entry(catalogLabel, itemFilter);
    }
}
