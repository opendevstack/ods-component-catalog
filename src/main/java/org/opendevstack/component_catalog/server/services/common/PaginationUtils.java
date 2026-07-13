package org.opendevstack.component_catalog.server.services.common;

import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_catalog.server.model.Pagination;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class PaginationUtils {

    private PaginationUtils() {}

    public static void validatePagination(int page, int size, int maxSize) {
        if (page < 0 || size < 0 || size > maxSize) {
            throw new IllegalArgumentException(
                    "Page must be >= 0 and size must be between 0 and " + maxSize
            );
        }
    }

    public static <T> PaginatedEntity<T> buildPagination(
            int page,
            int size,
            List<T> elements,
            String basePath) {

        if (page < 0) {
            throw new IllegalArgumentException("Page must be greater than or equal to 0");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        int totalElements = elements.size();

        int fromIndex = page * size;

        List<T> pageElements;
        if (fromIndex >= totalElements) {
            pageElements = Collections.emptyList();
        } else {
            int toIndex = Math.min(fromIndex + size, totalElements);
            pageElements = elements.subList(fromIndex, toIndex);
        }

        var pagination = buildPagination(
                page,
                size,
                totalElements,
                basePath);

        return new PaginatedEntity<>(pageElements, pagination);
    }

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
