package com.boehringer.componentcatalog.server.mappers.modules.jsonnullable;

import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;
import org.openapitools.jackson.nullable.JsonNullable;

public class FromJsonNullableConverter implements ConditionalConverter<JsonNullable<Object>, Object> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return (JsonNullable.class.equals(sourceType) && !JsonNullable.class.equals(destinationType))
                ? MatchResult.FULL
                : MatchResult.NONE;
    }

    @Override
    public Object convert(MappingContext<JsonNullable<Object>, Object> mappingContext) {
        if (mappingContext.getSource() == null
                || !mappingContext.getSource().isPresent()
                || mappingContext.getSource().get() == null) {
            return null;
        }

        var propertyContext = mappingContext.create(
                mappingContext.getSource().get(),
                mappingContext.getDestinationType());

        return mappingContext.getMappingEngine().map(propertyContext);
    }
}
