package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;

public class BitbucketPathAtMother {

    public static BitbucketPathAt of() {
        return of(
                "SomeFileOrDir"
        );
    }

    public static BitbucketPathAt of(String suffix) {
        return of(
                "https://my-bitbucket-server.com",
                "https://my-bitbucket-server.com/rest/api/latest",
                "https://my-bitbucket-server.com/projects/MYPROJECT/repos/repo-slug/raw/some-package/" + suffix + "?at=refs%2Fheads%2Fmaster"
        );
    }

    public static BitbucketPathAt of(String baseRawUrl, String baseRestUrl, String rawUrl) {
        return BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .rawUrl(rawUrl)
                .build();
    }


}
