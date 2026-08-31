package org.opendevstack.component_catalog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "provisioner.bitbucket")
@Getter
@Setter
public class ProvisionerActionsConfiguration {
    private String projectKey;
    private String projectComponentsRepositorySlug;
    private String projectConfigurationsRepositorySlug;
    private String projectConfigurationsRolesWhitelistedPath;
    private String projectsPath;
    private String subPath;
    private String projectHistorySubPath;
    private String subPathToken;
    private String branchName;
}
