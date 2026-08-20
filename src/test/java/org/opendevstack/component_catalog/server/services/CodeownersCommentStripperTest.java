package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CodeownersCommentStripperTest {

    // ---------------------------
    // strip(List<String>) tests
    // ---------------------------

    @Test
    void givenFullLineComments_whenStrip_thenTheyAreRemoved() {
        // given
        List<String> in = List.of(
                "# comment",
                "   # indented comment",
                "valid/path @team",
                ""
        );

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "valid/path @team",
                ""
        ));
    }

    @Test
    void givenInlineComments_whenStrip_thenOnlyInlineCommentsAreRemoved() {
        // given
        List<String> in = List.of(
                "src/** @team/core   # comment",
                "docs/**  @docs    # another"
        );

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "src/** @team/core   ",
                "docs/**  @docs    "
        ));
    }

    @Test
    void givenEscapedHash_whenStrip_thenLiteralHashIsPreserved() {
        // given
        List<String> in = List.of(
                "scripts/** @ci/ops  \\#notAComment   # real",
                "path\\#with @team   # trailing"
        );

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "scripts/** @ci/ops  #notAComment   ",
                "path#with @team   "
        ));
    }

    @Test
    void givenEvenBackslashesBeforeHash_whenStrip_thenHashStartsComment() {
        // given
        List<String> in = List.of(
                "path \\\\#commentStartsHere @team"
        );
        // Explanation:
        // `\\\\#` = two backslashes, then '#'
        // Two backslashes (even) => '#' NOT escaped => comment starts

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "path \\\\"
        ));
    }

    @Test
    void givenOddBackslashesBeforeHash_whenStrip_thenHashIsEscapedAndKept() {
        // given
        List<String> in = List.of(
                "path \\\\\\#literalHash @team # comment"
        );
        // `\\\\\#` => 3 backslashes (odd) => '#' escaped => one backslash consumed

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "path \\\\#literalHash @team "
        ));
    }

    @Test
    void givenWhitespaceOnlyLines_whenStrip_thenTheyArePreserved() {
        // given
        List<String> in = List.of(
                "",
                "   ",
                "\t\t",
                "src/** @a/b",
                "   "
        );

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "",
                "   ",
                "\t\t",
                "src/** @a/b",
                "   "
        ));
    }

    @Test
    void givenIndentedContent_whenStrip_thenIndentationAndSpacingArePreserved() {
        // given
        List<String> in = List.of(
                "   src/**     @team/core    # comment"
        );

        // when
        List<String> out = CodeownersCommentStripper.strip(in);

        // then
        assertThat(out).isEqualTo(List.of(
                "   src/**     @team/core    "
        ));
    }

    // ---------------------------
    // strip(String) tests
    // ---------------------------

    @Test
    void givenLFSeparators_whenStrip_thenLFIsPreserved() {
        // given
        String input = "a @x\n#comment\nb @y\n";
        String expected = "a @x\nb @y\n";

        // when
        String out = CodeownersCommentStripper.strip(input);

        // then
        assertThat(out).isEqualTo(expected);
    }

    @Test
    void givenCRLFSeparators_whenStrip_thenCRLFIsPreserved() {
        // given
        String input = "a @x\r\n#comment\r\nb @y\r\n";
        String expected = "a @x\r\nb @y\r\n";

        // when
        String out = CodeownersCommentStripper.strip(input);

        // then
        assertThat(out).isEqualTo(expected);
    }

    @Test
    void givenTrailingBlankLines_whenStrip_thenTrailingBlanksArePreserved() {
        // given
        String input = "a @x\n\n#comment\n\n";
        String expected = "a @x\n\n\n";

        // when
        String out = CodeownersCommentStripper.strip(input);

        // then
        assertThat(out).isEqualTo(expected);
    }

    @Test
    void givenNullOrEmpty_whenStrip_thenReturnedAsIs() {
        // given
        String nullInput = null;
        String emptyInput = "";

        // when
        String outNull = CodeownersCommentStripper.strip(nullInput);
        String outEmpty = CodeownersCommentStripper.strip(emptyInput);

        // then
        assertThat(outNull).isNull();
        assertThat(outEmpty).isEqualTo("");
    }

    @Test
    void givenWhitespaceOnlyLinesInString_whenStrip_thenTheyRemain() {
        // given
        String input = "   \n\t\t\n#comment\nx @y\n";
        String expected = "   \n\t\t\nx @y\n";

        // when
        String out = CodeownersCommentStripper.strip(input);

        // then
        assertThat(out).isEqualTo(expected);
    }

    @Test
    void givenEscapedHashInString_whenStrip_thenLiteralHashIsPreserved() {
        // given
        String input =
                """
                        scripts/**   @devops/ci     \\#not-a-comment  # real comment
                        frontend/**  @web/ux   # UX owns frontend
                        """;
        String expected =
                """
                        scripts/**   @devops/ci     #not-a-comment \s
                        frontend/**  @web/ux  \s
                        """;

        // when
        String out = CodeownersCommentStripper.strip(input);

        // then
        assertThat(out).isEqualTo(expected);
    }
}
