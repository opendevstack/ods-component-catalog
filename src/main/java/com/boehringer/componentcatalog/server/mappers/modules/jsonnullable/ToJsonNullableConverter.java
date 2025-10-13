package com.boehringer.componentcatalog.server.mappers.modules.jsonnullable;

import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;
import org.openapitools.jackson.nullable.JsonNullable;

import static org.modelmapper.internal.util.MappingContextHelper.resolveDestinationGenericType;

public class ToJsonNullableConverter implements ConditionalConverter<Object, JsonNullable<Object>> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return (!JsonNullable.class.equals(sourceType) && JsonNullable.class.equals(destinationType))
                ? MatchResult.FULL
                : MatchResult.NONE;
    }

    @Override
    public JsonNullable<Object> convert(MappingContext<Object, JsonNullable<Object>> mappingContext) {
        // Assume that: nulls will be mapped to JsonNullable.undefined(), which will cause
        // for these fields *not to be present* on API REST responses!
        if (mappingContext.getSource() == null) {
            return JsonNullable.undefined();
        }

        var propertyContext = mappingContext.create(
                mappingContext.getSource(),
                resolveDestinationGenericType(mappingContext));

        var destination = mappingContext.getMappingEngine().map(propertyContext);

        return JsonNullable.of(destination);
    }
}
