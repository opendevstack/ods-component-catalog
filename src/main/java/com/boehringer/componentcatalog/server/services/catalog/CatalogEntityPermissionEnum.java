package com.boehringer.componentcatalog.server.services.catalog;

import com.boehringer.componentcatalog.client.bitbucket.v89.model.RestPermittedUser.PermissionEnum;
import lombok.Getter;

// This enum mimics the RestPermittedUser.PermissionEnum enum from the Bitbucket API,
// given that there are no specific permissions for the Component Catalog, but for the
// Bitbucket user.
@Getter
public enum CatalogEntityPermissionEnum {
    USER_ADMIN(PermissionEnum.USER_ADMIN),

    PROJECT_VIEW(PermissionEnum.PROJECT_VIEW),

    REPO_READ(PermissionEnum.REPO_READ),

    REPO_WRITE(PermissionEnum.REPO_WRITE),

    REPO_ADMIN(PermissionEnum.REPO_ADMIN),

    PROJECT_READ(PermissionEnum.PROJECT_READ),

    PROJECT_WRITE(PermissionEnum.PROJECT_WRITE),

    REPO_CREATE(PermissionEnum.REPO_CREATE),

    PROJECT_ADMIN(PermissionEnum.PROJECT_ADMIN),

    LICENSED_USER(PermissionEnum.LICENSED_USER),

    PROJECT_CREATE(PermissionEnum.PROJECT_CREATE),

    ADMIN(PermissionEnum.ADMIN),

    SYS_ADMIN(PermissionEnum.SYS_ADMIN);

    private final String value;

    CatalogEntityPermissionEnum(PermissionEnum bitbucketPermission) {
        this.value = bitbucketPermission.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CatalogEntityPermissionEnum fromValue(String value) {
        for (var b : CatalogEntityPermissionEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
