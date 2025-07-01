package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;

public class BitbucketPathAtMother {

    public static BitbucketPathAt of() {
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var rawUrl = "https://my-bitbucket-server.com/projects/MYPROJECT/repos/repo-slug/raw/some-package/SomeFileOrDir?at=refs%2Fheads%2Fmaster";

        return BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .rawUrl(rawUrl)
                .build();
    }
}
