package com.boehringer.componentcatalog;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class,
        exclude = {SpringDocDataRestConfiguration.class}
)
@ComponentScan(
        basePackages = {
                "com.boehringer.componentcatalog.server.api",
                "com.boehringer.componentcatalog.server.controllers",
                "com.boehringer.componentcatalog.server.security",
                "com.boehringer.componentcatalog.config",
                "com.boehringer.componentcatalog.server.services",
                "com.boehringer.componentcatalog.client.bitbucket.v89"
        },
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@EnableAspectJAutoProxy
@EnableCaching
@EnableScheduling
public class ComponentCatalogApplication {

    public static void main(String[] args) {
		// Required to workaround parallel() stream issues
		// due to Spring DevTools classloader issues
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ComponentCatalogApplication.class, args);
    }

    @Bean(name = "jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
