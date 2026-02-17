package org.opendevstack.component_catalog.server.mappers.modules.jsonnullable;

import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;
import org.openapitools.jackson.nullable.JsonNullable;

public class FromJsonNullableToJsonNullableConverter implements ConditionalConverter<JsonNullable<?>, JsonNullable<?>> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return (JsonNullable.class.equals(sourceType) && JsonNullable.class.equals(destinationType))
                ? MatchResult.FULL
                : MatchResult.NONE;
    }

    @Override
    @SuppressWarnings("all")
    public JsonNullable<?> convert(MappingContext<JsonNullable<?>, JsonNullable<?>> mappingContext) {
        var source = mappingContext.getSource();

        if (source == null) { // This should never happen!
            return JsonNullable.undefined();
        } else if (source.isPresent()) {
            // JsonNullable are immutable, so we can safely return the same instance.
            return source;
        } else {
            return JsonNullable.undefined();
        }
    }

}
