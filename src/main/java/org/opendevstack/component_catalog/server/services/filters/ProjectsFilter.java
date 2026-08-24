package org.opendevstack.component_catalog.server.services.filters;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemRestriction;
import org.opendevstack.component_catalog.server.services.exceptions.CatalogItemsFilterException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Order(10)
@Slf4j
public class ProjectsFilter implements CatalogItemsFilter {
    @Override
    public boolean filter(CatalogItem item, List<String> params) {
        log.debug("Projects filter. item: {}, params: {}", item, params);

        validate(params);
        String projectKey = params.getFirst();

        var projects = Optional.ofNullable(item.getRestrictions())
                .map(CatalogItemRestriction::getProjects)
                .orElse(Collections.emptySet());

        log.debug("Projects {} for the catalog item {}", projects, item.getId());
        return projects.isEmpty() || (projectKey != null && projects.contains(projectKey));
    }

    private void validate(List<String> params) {
        if (params != null && params.size() != 1) {
            throw new CatalogItemsFilterException("Only one project key is allowed");
        }
    }
}
