package org.opendevstack.component_catalog.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to OpenAPI api documentation
 */
@Controller
public class SwaggerController {

    @RequestMapping({"/"}) //NOSONAR show the REST API documentation as the default page
    public String index() {
        return "redirect:swagger-ui/index.html";
    }
}