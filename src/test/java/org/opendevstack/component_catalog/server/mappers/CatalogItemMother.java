package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.model.CatalogItem;

public class CatalogItemMother {
    public static CatalogItem of(String id, String imageFileId) {
        var item = new CatalogItem();
        item.setId(id);
        item.setImageFileId(imageFileId);
        return item;
    }

    public static CatalogItem of() {
        return of("catalogItemId1", "Logo1.png");
    }
}
