package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.mappers.CatalogItemRestrictionMapper;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionMapper;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionParameterMapper;
import org.opendevstack.component_catalog.server.mappers.EntitiesMapper;
import org.opendevstack.component_catalog.server.model.*;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserAction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.opendevstack.component_catalog.util.FunctionalUtils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogApiAdapter {

    private static final String CODE_ID = "CODE";
    private final EntitiesMapper entitiesMapper;
    private final CatalogItemUserActionParameterMapper catalogItemUserActionParameterMapper;
    private final CatalogItemUserActionMapper  catalogItemUserActionMapper;

    public CatalogItemUserActionMessageDefinition asCatalogItemUserActionMessageDefinition(
            UserActionEntityMessageTitle userActionEntityMessageTitles,
            UserActionEntityMessageDefinition userActionEntityMessageDefinition) {
        return entitiesMapper.asCatalogItemUserActionMessageDefinition(userActionEntityMessageTitles, userActionEntityMessageDefinition);
    }

    public  CatalogItem asCatalogItem(CatalogRequestParams  catalogRequestParams,
                                        List<String> clusters,
                                        List<String> userGroups) {
        log.debug("asCatalogItem. params: {}, clusters: {}, userGroups: {}",
                catalogRequestParams, clusters, userGroups);


        // If the principal has *any* permissions, it has access to the repo
        var hasRepoAccess = !catalogRequestParams.getPermissions().isEmpty();

        var mappedItem = entitiesMapper.asCatalogItem(catalogRequestParams.getCatalogItemEntityContext(), clusters, userGroups, catalogRequestParams.getProjectKey());

        var item = updateItemIfNoRepoAccess(mappedItem, hasRepoAccess);

        // This his here just in case the "userActions" field is either not defined on CatalogItem.yaml,
        //  or is defined as null/missing, which is not expected, but it happens.
        if (isNull(item.getUserActions())) {
            // If there are no user actions defined on CatalogItem.yaml, just return
            item.userActions(new ArrayList<>());
        }

        var mergedCatalogItemUserActions = finalizeUserActions(item.getUserActions(),
                catalogRequestParams.getUserActionsEntity().getSpec().getActions(), clusters, userGroups,
                catalogRequestParams.getProjectKey(), catalogRequestParams.getCatalogItemEntityContext().getId());
        item.setUserActions(mergedCatalogItemUserActions);


        // * The action with id="CODE" is a special case:
        //   * It is always mandatory and customizable, but only its "url" property.
        //   * It doesn't have any parameters, but parameters=[] for consistency.
        //   * If not present on CatalogItem.yaml's userActions list, it will be appended at the end of the list.
        //   * If the "url" property is not set, it will be set during processing to the CatalogItem.yaml's Bitbucket source folder URL.
        //  Tech debt: this "CODE" id we are dealing with here is a hardcoded value,
        //  because it's considered to be a "special" user action, which it shouldn't!

        var userActionsEntitiesCodeUrl = Stream.of(catalogRequestParams.getUserActionsEntity().getSpec().getActions())
                .filter(ua -> Objects.equals(CODE_ID, ua.getId()))
                .findFirst()
                .map(UserActionEntity::getUrl)
                .orElse(mappedItem.getItemSrc());

        var itemEntityCodeUrl = Optional
                .ofNullable(catalogRequestParams.getCatalogItemEntityContext().getCatalogItemEntity().getSpec())
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
        itemCodeUserAction.ifPresent(codeUserAction -> {
            codeUserAction.url(hasRepoAccess ? itemEntityCodeUrl : null);
            codeUserAction.requestable(Boolean.TRUE);
            codeUserAction.restrictionMessage(Strings.EMPTY);
        });

        CatalogItemRestriction catalogItemRestriction = Optional.of(catalogRequestParams)
                .map(CatalogRequestParams::getCatalogItemEntityContext)
                .map(CatalogItemEntityContext::getCatalogItemEntity)
                .map(CatalogItemEntity::getSpec)
                .map(CatalogItemEntitySpec::getRestrictions)
                .map(CatalogItemRestrictionMapper::asCatalogItemRestriction)
                .orElse(
                        CatalogItemRestriction.builder()
                                .projects(Collections.emptySet())
                        .build()
                );

        item.setRestrictions(catalogItemRestriction);

        return item;
    }

    public  List<CatalogItemFilter> catalogItemFiltersFrom(CatalogRequestParams catalogRequestParams, List<String> clusters, List<String> userGroups) {
        log.debug("catalogItemFiltersFrom. params: {}", catalogRequestParams);

        // Group all tags from all items and group them by label, in order to
        // get pairs: (itemLabel, List<CatalogItemTag> itemTags)
        var itemLabelsByItemTags = catalogRequestParams.getCatalogItemEntityContextList().stream()
                .map(itemEntityCtx -> asCatalogItem(
                        CatalogRequestParams.builder()
                                .catalogItemEntityContext(itemEntityCtx)
                                .userActionsEntity(catalogRequestParams.getUserActionsEntity())
                                .permissions(catalogRequestParams.getPermissions())
                                .projectKey(catalogRequestParams.getProjectKey())
                                .build(),
                        clusters,
                        userGroups
                ))
                .flatMap(item -> item.getTags().stream())
                .collect(Collectors.groupingBy(CatalogItemTag::getLabel));


        var catalogEntityLabels = catalogRequestParams.getCatalogEntity()
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
                .map(entry -> labeledFilterEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        // Return filters according to the order in the Catalog.yaml
        return List.copyOf(catalogLabelsItemFilters.values());
    }

    public  Catalog asCatalog(CatalogEntityContext catalogEntityCtx) {
        return entitiesMapper.asCatalog(catalogEntityCtx);
    }

    public  List<CatalogDescriptor> asCatalogDescriptors(CatalogsCollectionsEntity catalogsCollectionsEntity) {
        return Arrays.stream(catalogsCollectionsEntity.getMetadata().getSpec().getTargets())
                .map(entitiesMapper::asCatalogDescriptor)
                .toList();
    }

    protected  List<CatalogItemUserAction> finalizeUserActions(List<CatalogItemUserAction> itemUserActions,
                                                               UserActionEntity[] userActionEntities,
                                                               List<String> clusters,
                                                               List<String> userGroups,
                                                               String projectKey,
                                                               String catalogItemId) {
        var userActionEntitiesList = Arrays.asList(userActionEntities);

        var userActions = mapList(
                userActionEntitiesList,
                entity -> catalogItemUserActionMapper.asCatalogItemUserAction(entity, clusters, userGroups, projectKey, catalogItemId)
        );

        var userActionsEntitiesByMandatory = splitBy(
                userActionEntitiesList,
                UserActionEntity::isMandatory
        );

        // Convert the user actions entities, defined on UserActions.yaml to CatalogItemUserAction REST entities,
        // in order to make easier copying information from UserActions.yaml to CatalogItemUserActions REST entities
        var mandatoryUserActionsEntities = mapList(
                userActionsEntitiesByMandatory.getLeft(),
                entity -> catalogItemUserActionMapper.asCatalogItemUserAction(entity, clusters, userGroups, projectKey, catalogItemId)
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

    private  List<CatalogItemUserActionParameter> finalizeUserActionParameters(List<CatalogItemUserActionParameter> userConfiguredCatalogItemUserActionParameters,
                                                                               UserActionEntityParameter[] baseUserActionEntityParameters) {
        var baseUserActionEntityParamsList = Arrays.asList(baseUserActionEntityParameters);

        var baseUserActionParams = mapList(
                baseUserActionEntityParamsList,
                catalogItemUserActionParameterMapper::asCatalogItemUserActionParameter
        );

        var baseUserActionEntityParamsByCustomizable = splitBy(
                baseUserActionEntityParamsList,
                UserActionEntityParameter::isCustomizable
        );

        var baseCustomizableUserActionParams = mapList(
                baseUserActionEntityParamsByCustomizable.getLeft(),
                catalogItemUserActionParameterMapper::asCatalogItemUserActionParameter
        );

        var baseNonCustomizableUserActionParams = mapList(
                baseUserActionEntityParamsByCustomizable.getRight(),
                catalogItemUserActionParameterMapper::asCatalogItemUserActionParameter
        );

        var customizableParamsPairsJoinedWithUserParams = fullJoin(
                userConfiguredCatalogItemUserActionParameters, CatalogItemUserActionParameter::getName,
                baseCustomizableUserActionParams, CatalogItemUserActionParameter::getName
        );

        // We may have values customized at user level that matches non customizable param names, but we are ignoring them at the moment
        // We need to filter them out
        var baseNonCustomizableParamsNames = baseNonCustomizableUserActionParams.stream()
                .map(CatalogItemUserActionParameter::getName)
                .collect(Collectors.toSet());
        var customizableParamsPairs = customizableParamsPairsJoinedWithUserParams.stream()
                .filter(pair -> !baseNonCustomizableParamsNames.contains(pair.getLeft().getName()))
                .toList();


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

    private static Map.Entry<String, CatalogItemFilter> labeledFilterEntry(String catalogLabel,
                                                                           List<CatalogItemTag> itemTags) {
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

    private static CatalogItem updateItemIfNoRepoAccess(CatalogItem sourceItem,
                                                        boolean hasRepoAccess) {
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
}
