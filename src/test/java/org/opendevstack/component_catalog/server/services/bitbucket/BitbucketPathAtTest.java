package org.opendevstack.component_catalog.server.services.bitbucket;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BitbucketPathAtTest {

    @Test
    public void givenABuild_whenRawUrl_andBuild_thenABitbucketPathAtIsCreated() {
        // given
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var rawUrl =
                "https://my-bitbucket-server.com/projects/MYPROJECT/repos/repo-slug/raw/some-package/SomeFileOrDir"
                        + "?at=refs%2Fheads%2Fmaster";

        // when
        var bitbucketPathAt = BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .rawUrl(rawUrl)
                .build();

        // then
        assertThat(bitbucketPathAt).isNotNull();
        assertThat(bitbucketPathAt.getProjectKey()).isEqualTo("MYPROJECT");
        assertThat(bitbucketPathAt.getRepoSlug()).isEqualTo("repo-slug");
        assertThat(bitbucketPathAt.getAt()).isEqualTo("refs/heads/master");
    }

    @Test
    public void givenABuild_whenRestUrl_andBuild_thenABitbucketPathAtIsCreated() {
        // given
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var restUrl =
                "https://my-bitbucket-server.com/projects/MYPROJECT/repos/repo-slug/raw/some-package/SomeFileOrDir"
                        + "?at=refs%2Fheads%2Fmaster";

        // when
        var bitbucketPathAt = BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .restUrl(restUrl)
                .build();

        // then
        assertThat(bitbucketPathAt).isNotNull();
        assertThat(bitbucketPathAt.getProjectKey()).isEqualTo("MYPROJECT");
        assertThat(bitbucketPathAt.getRepoSlug()).isEqualTo("repo-slug");
        assertThat(bitbucketPathAt.getAt()).isEqualTo("refs/heads/master");
    }

    @Test
    public void givenABuild_whenPathAt_andBuild_thenABitbucketPathAtIsCreated() {
        // given
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var pathAt = "/projects/MYPROJECT/repos/repo-slug/raw/some-package/SomeFileOrDir?at=refs%2Fheads%2Fmaster";

        // when
        var bitbucketPathAt = BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .pathAt(pathAt)
                .build();

        // then
        assertThat(bitbucketPathAt).isNotNull();
        assertThat(bitbucketPathAt.getProjectKey()).isEqualTo("MYPROJECT");
        assertThat(bitbucketPathAt.getRepoSlug()).isEqualTo("repo-slug");
        assertThat(bitbucketPathAt.getAt()).isEqualTo("refs/heads/master");
    }

    @Test
    public void givenASubPathWithExtension_whenGetProjectKeyFromSubPath_thenFileNameWithoutExtensionIsReturned() {
        // given
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var pathAt = "/projects/MYPROJECT/repos/repo-slug/raw/some-package/PROJECT_KEY.json?at=refs%2Fheads%2Fmaster";
        var subPath = "projects/PROJECT_KEY.json";

        var bitbucketPathAt = BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .pathAt(pathAt)
                .subPath(subPath)
                .build();

        // when
        var projectKey = bitbucketPathAt.getProjectKeyFromSubPath();

        // then
        assertThat(projectKey).isEqualTo("PROJECT_KEY");
    }

    @Test
    public void givenASubPathWithoutExtension_whenGetProjectKeyFromSubPath_thenFileNameIsReturned() {
        // given
        var baseRawUrl = "https://my-bitbucket-server.com";
        var baseRestUrl = "https://my-bitbucket-server.com/rest/api/latest";
        var pathAt = "/projects/MYPROJECT/repos/repo-slug/raw/some-package/PROJECT_KEY?at=refs%2Fheads%2Fmaster";
        var subPath = "projects/PROJECT_KEY";

        var bitbucketPathAt = BitbucketPathAt.builder()
                .baseRawUrl(baseRawUrl)
                .baseRestUrl(baseRestUrl)
                .pathAt(pathAt)
                .subPath(subPath)
                .build();

        // when
        var projectKey = bitbucketPathAt.getProjectKeyFromSubPath();

        // then
        assertThat(projectKey).isEqualTo("PROJECT_KEY");
    }

}
