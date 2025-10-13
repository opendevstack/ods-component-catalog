package com.boehringer.componentcatalog.server.mappers;

import org.openapitools.jackson.nullable.JsonNullable;

public class MapperUtils {

    private MapperUtils() {
        // Utility class, no instantiation allowed
    }

    public static boolean nullish(Object value) {
        return value == null || (value instanceof JsonNullable<?> jn && (!jn.isPresent() || jn.get() == null));
    }
}
