package org.opendevstack.component_catalog.server.services.provisioner;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectComponent {
    private String componentId;
    private String catalogItemId;
    private String catalogItemRef;
    private Status status;
    private String componentUrl;

    @Value("${catalog-item.reference.encoded}")
    @JsonIgnore
    private String defaultCatalogItemReference;

    public String getCatalogItemRef() {
        return catalogItemRef != null ? catalogItemRef : defaultCatalogItemReference;
    }

    public Status getStatus() {
        return status != null ? status : Status.UNKNOWN;
    }

}
