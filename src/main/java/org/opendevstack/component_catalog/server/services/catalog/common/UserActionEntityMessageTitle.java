package org.opendevstack.component_catalog.server.services.catalog.common;

import org.opendevstack.component_catalog.server.services.catalog.UserActionEntityMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionEntityMessageTitle {
    private UserActionEntityMessageType id;
    private String title;
}
