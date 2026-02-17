package org.opendevstack.component_catalog.server.services.catalog.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionsEntitySpec {
    private UserActionEntity[] actions;
}
