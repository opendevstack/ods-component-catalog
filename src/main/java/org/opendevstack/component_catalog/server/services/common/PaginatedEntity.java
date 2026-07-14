package org.opendevstack.component_catalog.server.services.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.opendevstack.component_catalog.server.model.Pagination;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginatedEntity<T> {

    private List<T> data;

    private Pagination pagination;
}
