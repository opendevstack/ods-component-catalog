package com.boehringer.componentcatalog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean(name = "apiInfo")
    OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";

        // Copied from: openapi-componentcatalog-vx.x.x.yaml
        var edpCoreContact = new Contact()
                .name("EDPCore Team")
                .url("https://confluence.biscrum.com/pages/viewpage.action?spaceKey=EDP&title=Welcome");

        var info = new Info()
                .title("Component Catalog REST API")
                .description("""
                        The Component Catalog API allows clients to retrieve information about CatalogItems, CatalogFilters and Files entities.
                            
                        Catalog and File entities also exist internally, but only referenced by id's on requests and not returned on responses.
                            
                        **NOTES**:
                        - The OpenAPI specification file is also used to [generate](https://openapi-generator.tech/) REST client(s) and a server REST API.
                        - Clients and servers generated from the same OpenAPI specification version are guaranteed to be **compatible**.
                        """)
                .contact(edpCoreContact)
                .version("1.0.0");

        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Components securityComponents = new Components()
                .addSecuritySchemes(securitySchemeName,securityScheme);

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(securityComponents)
                .info(info);
    }
}