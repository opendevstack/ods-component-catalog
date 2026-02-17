package org.opendevstack.component_catalog.server.mappers.modules.jsonnullable;


import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

/**
 * Supports the JDK8 data types  with  ModelMapper
 *
 * @author Chun Han Hsiao
 */
public class JsonNullableModule implements Module {
    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.getConfiguration().getConverters().add(0, new FromJsonNullableConverter());
        modelMapper.getConfiguration().getConverters().add(0, new ToJsonNullableConverter());
        modelMapper.getConfiguration().getConverters().add(0, new FromJsonNullableToJsonNullableConverter());
    }
}
