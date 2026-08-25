package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.org.opendevstack.component_catalog.server.model.wrapper.CatalogItemUserActionWrapper;
import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserAction;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluatorResult;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogItemUserActionMapper {

    private CatalogItemUserActionParameterMapper catalogItemUserActionParameterMapper;

    private List<RestrictionsEvaluator> restrictionsEvaluators;

    private ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps  catalogItemUserActionGroupsRestrictionProps;

    public CatalogItemUserActionWrapper asCatalogItemUserAction(CatalogItemEntityUserAction catalogItemEntityUserAction,
                                                                List<String> clusters,
                                                                List<String> userGroups,
                                                                String projectKey,
                                                                String catalogItemId) {
        log.debug("Mapping CatalogItemEntityUserAction to CatalogItemUserAction: {}", catalogItemEntityUserAction);

        var userActionParameters = Optional.ofNullable(catalogItemEntityUserAction.getParameters())
                .map(parameters -> Arrays.stream(parameters)
                        .map(catalogItemUserActionParameterMapper::asCatalogItemUserActionParameter)
                        .toList())
                .orElse(new ArrayList<>());

        var groupRestrictions = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(catalogItemUserActionGroupsRestrictionProps.getPrefix())
                .suffix(catalogItemUserActionGroupsRestrictionProps.getSuffix())
                .build();
        var restrictions = Optional.ofNullable(catalogItemEntityUserAction.getRestrictions())
                .map(restrictionActions -> restrictionActions.toBuilder()
                        .groups(groupRestrictions)
                        .build())
                .orElse(UserActionEntityRestrictions.builder()
                        .groups(groupRestrictions)
                        .build()
                );

        var requestableAndMessage = evaluateRestrictions(restrictions, clusters, userActionParameters, userGroups, projectKey, catalogItemId);

        var catalogItemUserAction = CatalogItemUserAction.builder()
                .id(catalogItemEntityUserAction.getId())
                .displayName(catalogItemEntityUserAction.getDisplayName())
                .url(catalogItemEntityUserAction.getUrl())
                .triggerMessage(catalogItemEntityUserAction.getTriggerMessage())
                .parameters(userActionParameters)
                .requestable(requestableAndMessage.requestable())
                .restrictionMessage(requestableAndMessage.reason())
                .build();

        log.debug("Resulting CatalogItemUserAction: {}", catalogItemUserAction);

        return new CatalogItemUserActionWrapper(catalogItemUserAction, requestableAndMessage.valid());
    }

    public CatalogItemUserActionWrapper asCatalogItemUserAction(UserActionEntity userActionEntity,
                                                         List<String> clusters,
                                                         List<String> userGroups,
                                                         String projectKey,
                                                         String catalogItemId) {
        log.debug("Mapping UserActionEntity to CatalogItemUserAction: {}", userActionEntity);

        var userActionEntityParameters = Optional.ofNullable(userActionEntity.getParameters())
                .map(parameters -> Arrays.stream(parameters)
                        .map(catalogItemUserActionParameterMapper::asCatalogItemUserActionParameter)
                        .toList())
                .orElse(new ArrayList<>());

        var groupRestrictions = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(catalogItemUserActionGroupsRestrictionProps.getPrefix())
                .suffix(catalogItemUserActionGroupsRestrictionProps.getSuffix())
                .build();

        var restrictions = Optional.ofNullable(
                userActionEntity.getRestrictions()
                ).map(r -> r.toBuilder()
                        .groups(groupRestrictions)
                        .build())
                .orElse(UserActionEntityRestrictions.builder()
                        .groups(groupRestrictions)
                        .build()
                );

        var requestableAndMessage = evaluateRestrictions(restrictions, clusters, userActionEntityParameters, userGroups, projectKey, catalogItemId);

        var catalogItemUserAction = CatalogItemUserAction.builder()
                .id(userActionEntity.getId())
                .displayName(userActionEntity.getDisplayName())
                .url(userActionEntity.getUrl())
                .triggerMessage(userActionEntity.getTriggerMessage())
                .parameters(userActionEntityParameters)
                .requestable(requestableAndMessage.requestable())
                .restrictionMessage(requestableAndMessage.reason())
                .build();

        return new CatalogItemUserActionWrapper(catalogItemUserAction, requestableAndMessage.valid());
    }

    public RestrictionsEvaluatorResult evaluateRestrictions(UserActionEntityRestrictions restrictions,
                                                      List<String> clusters,
                                                      List<CatalogItemUserActionParameter> parameters,
                                                      List<String> userGroups,
                                                      String projectKey,
                                                      String catalogItemId) {

        RestrictionsParams restrictionsParams = RestrictionsParams.builder()
                .clusters(clusters)
                .parameters(parameters)
                .userGroups(userGroups)
                .projectKey(projectKey)
                .catalogItemId(catalogItemId)
                .build();

        RestrictionsEvaluatorResult evaluationResult = new RestrictionsEvaluatorResult(true, true, "");

        for (RestrictionsEvaluator restrictionsEvaluator : restrictionsEvaluators) {
            var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

            evaluationResult = restrictionsEvaluator.evaluate(evaluationRestrictions, restrictionsParams);

            if (evaluationResult.requestable()) {
                log.debug("Evaluation passed for evaluator {}, nothing to return", restrictionsEvaluator);
            } else {
                log.debug("Evaluation failed for evaluator {}, returning error: {}",  restrictionsEvaluator, evaluationResult.reason());

                return evaluationResult;
            }
        }

        return evaluationResult;
    }

}
