package org.opendevstack.component_catalog.server.services.common;

import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_catalog.server.model.Pagination;

import java.net.URI;

public class PaginationUtils {

    public static Pagination buildPagination(int page, int size, int totalElements, String basePath) {
        int totalPages = (size == 0) ? 0 : (int) Math.ceil((double) totalElements / size);

        var nextPageCandidate = Math.max(page + 1, 1);
        var previousPageCandidate = Math.min(page - 1, totalPages - 1);

        var next = (page < totalPages - 1) ?
                URI.create(basePath + "?page=" + nextPageCandidate + "&size=" + size)
                : null;
        var previous = (page > 0) ?
                URI.create(basePath + "?page=" + previousPageCandidate + "&size=" + size)
                : null;

        return Pagination.builder()
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .next(JsonNullable.of(next))
                .previous(JsonNullable.of(previous))
                .build();
    }
}
