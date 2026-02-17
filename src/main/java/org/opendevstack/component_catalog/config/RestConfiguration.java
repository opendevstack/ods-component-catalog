package org.opendevstack.component_catalog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@Slf4j
public class RestConfiguration implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        // Required for Bitbucket API client
        // Avoid encoding URI variables, e.g. {path} in the URL, to avoid http 404 errors because of invalid urls
        var uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler)
                .build();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add extra converters and formatters for REST controllers API parameters
        ApplicationConversionService.configure(registry);
    }
}
