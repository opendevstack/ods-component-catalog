package com.boehringer.componentcatalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntitySpec {
    private CatalogItemEntityUserAction[] userActions;
}
