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
    private String repositorySlug;
    private String subPath;
    private String subPathToken;
    private String branchName;
}
