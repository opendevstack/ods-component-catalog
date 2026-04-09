package org.opendevstack.component_catalog.server.services.slug;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CatalogItemSlugTest {

    @Test
    void parse_validSlug_returnsCorrectComponents() {
        var slug = CatalogItemSlug.parse("myproject_my-component-repo");

        assertThat(slug.getProjectKey()).isEqualTo("myproject");
        assertThat(slug.getRepoName()).isEqualTo("my-component-repo");
    }

    @Test
    void parse_repoNameContainsUnderscores_fullPartAfterFirstSeparatorIsRepoName() {
        var slug = CatalogItemSlug.parse("myproject_repo_with_underscores");

        assertThat(slug.getProjectKey()).isEqualTo("myproject");
        assertThat(slug.getRepoName()).isEqualTo("repo_with_underscores");
    }

    @Test
    void parse_trimsWhitespaceAroundComponents() {
        var slug = CatalogItemSlug.parse("  myproject  _  my-repo  ");

        assertThat(slug.getProjectKey()).isEqualTo("myproject");
        assertThat(slug.getRepoName()).isEqualTo("my-repo");
    }

    @Test
    void parse_singleCharacterComponents_returnsCorrectComponents() {
        var slug = CatalogItemSlug.parse("p_r");

        assertThat(slug.getProjectKey()).isEqualTo("p");
        assertThat(slug.getRepoName()).isEqualTo("r");
    }

    @Test
    void parse_nullSlug_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse(null))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("blank");
    }

    @Test
    void parse_blankSlug_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("   "))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("blank");
    }

    @Test
    void parse_noSeparator_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("myprojectonly"))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("does not conform");
    }

    @Test
    void parse_separatorAtStart_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("_my-repo"))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("does not conform");
    }

    @Test
    void parse_separatorAtEnd_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("myproject_"))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("does not conform");
    }

    @Test
    void parse_blankProjectKeyAfterTrim_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("  _my-repo"))
                .isInstanceOf(InvalidCatalogItemSlugException.class);
    }

    @Test
    void parse_blankRepoNameAfterTrim_throwsInvalidCatalogItemSlugException() {
        assertThatThrownBy(() -> CatalogItemSlug.parse("myproject_  "))
                .isInstanceOf(InvalidCatalogItemSlugException.class)
                .hasMessageContaining("blank");
    }

    @Test
    void normalise_uppercaseInput_returnsLowercase() {
        assertThat(CatalogItemSlug.normalise("MYPROJECT")).isEqualTo("myproject");
    }

    @Test
    void normalise_spacesInInput_replacedWithHyphens() {
        assertThat(CatalogItemSlug.normalise("my project")).isEqualTo("my-project");
    }

    @Test
    void normalise_mixedCaseAndSpaces_returnsNormalisedValue() {
        assertThat(CatalogItemSlug.normalise("  My Project  ")).isEqualTo("my-project");
    }

    @Test
    void normalise_nullInput_returnsEmptyString() {
        assertThat(CatalogItemSlug.normalise(null)).isEmpty();
    }

    @Test
    void normalise_alreadyNormalisedInput_returnsUnchanged() {
        assertThat(CatalogItemSlug.normalise("myproject")).isEqualTo("myproject");
    }

    @Test
    void toString_returnsProjectKeyAndRepoNameJoinedBySeparator() {
        var slug = CatalogItemSlug.parse("myproject_my-repo");

        assertThat(slug.toString()).isEqualTo("myproject" + CatalogItemSlug.SEPARATOR + "my-repo");
    }

    @Test
    void toString_repoNameWithUnderscores_preservesFullRepoName() {
        var slug = CatalogItemSlug.parse("myproject_repo_part1_part2");

        assertThat(slug.toString()).isEqualTo("myproject_repo_part1_part2");
    }

    @Test
    void separator_isUnderscore() {
        assertThat(CatalogItemSlug.SEPARATOR).isEqualTo("_");
    }
}
