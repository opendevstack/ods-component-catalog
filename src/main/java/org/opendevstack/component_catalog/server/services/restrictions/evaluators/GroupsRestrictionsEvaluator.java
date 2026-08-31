package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.services.RolesWhitelistedService;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.stream.Stream;

@Service
@Order(30)
@Slf4j
@RequiredArgsConstructor
public class GroupsRestrictionsEvaluator implements RestrictionsEvaluator {

    private final RolesWhitelistedService rolesWhitelistedService;

    @Override
    public RestrictionsEvaluatorResult evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        UserActionEntityRestrictions updatedRestrictions = addWhitelistedSuffixRolesIfNeeded(restrictions.restrictions(), params.getCatalogItemId());
        if (validate(updatedRestrictions, params) && evaluateConditions(updatedRestrictions, params)) {
            return new RestrictionsEvaluatorResult(true, true, "");
        } else {
            log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

            return new RestrictionsEvaluatorResult(false, true,"Only project members with Manager or Team roles can provision this component.");
        }
    }

    private UserActionEntityRestrictions addWhitelistedSuffixRolesIfNeeded(UserActionEntityRestrictions restrictions, String catalogItemId) {
        var whitelistedRoles = rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(catalogItemId);

        if (whitelistedRoles.isEmpty()) {
            log.debug("No whitelisted roles where found for catalog item {}", Base64.getDecoder().decode(catalogItemId));
            return restrictions;
        }

        log.debug("Whitelisted roles found for catalog item {}: {}", Base64.getUrlDecoder().decode(catalogItemId), whitelistedRoles);
        var updatedSuffixes = Stream.concat(restrictions.getGroups().getSuffix().stream(), whitelistedRoles.stream()).distinct().toList();

        return restrictions.toBuilder()
                .groups(restrictions.getGroups().toBuilder()
                        .suffix(updatedSuffixes)
                        .build())
                .build();
    }

    private boolean validate(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        return restrictions.getGroups() != null
                && params.getUserGroups() != null
                && restrictions.getGroups().getPrefix() != null
                && restrictions.getGroups().getSuffix() != null
                && params.getProjectKey() != null;
    }

    private boolean evaluateConditions(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        return params.getUserGroups().stream().anyMatch(group ->
                restrictions.getGroups().getPrefix().stream().anyMatch(group::startsWith) &&
                restrictions.getGroups().getSuffix().stream().anyMatch(group::endsWith) &&
                group.contains(params.getProjectKey())
        );
    }
}
