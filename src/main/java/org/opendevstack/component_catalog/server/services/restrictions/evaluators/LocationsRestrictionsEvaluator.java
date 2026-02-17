package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocation;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Order(50)
@Slf4j
public class LocationsRestrictionsEvaluator implements RestrictionsEvaluator {

    @Override
    public Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (validate(params) && evaluateConditions(restrictions.restrictions(), params)) {
            return Pair.of(true, "");
        } else {
            log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

            return Pair.of(false, "This product is not provisionable in the project location.");
        }
    }

    private boolean validate(RestrictionsParams params) {
        return params.getClusters() != null
                && !params.getClusters().isEmpty();
    }

    private boolean verifyFirstOfParamsClustersAndLocationsMatch(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        var allParameterLocations = getAllParameterLocations(params.getParameters())
                .stream()
                .flatMap(List::stream)
                .toList();

        var allRestrictionsLocations = List.of(restrictions.getLocations());

        var firstCluster = params.getClusters().getFirst();

        var firstClusterIsMatchingLocations = allParameterLocations.isEmpty() || allParameterLocations.contains(firstCluster);
        var firstClusterIsMatchingRestrictionsLocations = allRestrictionsLocations.isEmpty() || allRestrictionsLocations.contains(firstCluster);

        if (firstClusterIsMatchingLocations && firstClusterIsMatchingRestrictionsLocations) {
            return true;
        } else {
            log.debug("First of the project locations is not valid based on restrictions locations");

            return false;
        }
    }

    private boolean evaluateConditions(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        List<List<String>> allParameterLocations = getAllParameterLocations(params.getParameters());

        List<String> sharedLocations = computeSharedLocations(restrictions.getLocations(), allParameterLocations);

        return !sharedLocations.isEmpty()
                && verifyFirstOfParamsClustersAndLocationsMatch(restrictions, params)
                && !Collections.disjoint(sharedLocations, params.getClusters());
    }

    private List<List<String>> getAllParameterLocations(List<CatalogItemUserActionParameter> parameters) {
        return parameters.stream()
                .map(CatalogItemUserActionParameter::getLocations)
                .filter(Objects::nonNull)
                .filter(JsonNullable::isPresent)
                .map(JsonNullable::get)
                .map(list -> list.stream()
                        .map(CatalogItemUserActionParameterLocation::getLocation)
                        .map(String::toLowerCase).toList())
                .toList();
    }

    private List<String> computeSharedLocations(String[] restrictionLocations, List<List<String>> allParameterLocations) {
        // We translate the array into a set to make efficient operations.
        // We also put everything on lower case, to ensure String comparisons
        Set<String> restrictionSet = Optional.ofNullable(restrictionLocations)
                .map(locations -> Arrays.stream(restrictionLocations)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet()) )
                .orElse(Collections.emptySet());

        // We filter out empty or null location lists inside parameters, we do not need them
        List<List<String>> nonEmptyParamLists = allParameterLocations.stream()
                .filter(list -> list != null && !list.isEmpty())
                .toList();

        // Edge case, if there are no parameter locations, or all are empty, we can safely return the restriction set.
        if (allParameterLocations.isEmpty() || nonEmptyParamLists.isEmpty()) {
            return new ArrayList<>(restrictionSet);
        }

        // We initialize the intersection set (locations shared across parameter locations and restriction locations) as the restriction set
        // Then we intersect that set with each of the location list for each parameter
        Set<String> intersection = new HashSet<>(restrictionSet);
        for (List<String> paramLocations : nonEmptyParamLists) {
            intersection.retainAll(paramLocations);
            // If intersection is empty, means that there are no shared locations, we can break the loop
            if (intersection.isEmpty()) break;
        }
        return new ArrayList<>(intersection);
    }
}
