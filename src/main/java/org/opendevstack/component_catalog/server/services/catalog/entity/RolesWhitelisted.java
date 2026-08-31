package org.opendevstack.component_catalog.server.services.catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * This bean directly maps a RolesWhitelisted.yaml file
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolesWhitelisted {

    private Map<String, List<String>> roles;

}
