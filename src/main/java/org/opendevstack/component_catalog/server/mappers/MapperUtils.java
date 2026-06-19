package org.opendevstack.component_catalog.server.mappers;

import org.openapitools.jackson.nullable.JsonNullable;

public class MapperUtils {

    private MapperUtils() {
        // Utility class, no instantiation allowed
    }

    public static boolean isAbsent(Object value) {
        return value == null || (value instanceof JsonNullable<?> jn && !jn.isPresent());
    }

    public static boolean isNull(Object value) {
        return value == null || (value instanceof JsonNullable<?> jn && jn.isPresent() && jn.get() == null);
    }
}
