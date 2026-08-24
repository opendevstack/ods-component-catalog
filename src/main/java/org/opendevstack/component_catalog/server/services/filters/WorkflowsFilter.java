package org.opendevstack.component_catalog.server.services.filters;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Order(20)
@Slf4j
public class WorkflowsFilter implements CatalogItemsFilter {
    @Override
    public boolean filter(CatalogItem item, List<String> params) {
        log.debug("filter. item: {}, params: {}", item, params);

        return true;
    }
}
