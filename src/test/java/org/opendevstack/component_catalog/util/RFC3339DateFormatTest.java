package org.opendevstack.component_catalog.util;

import org.junit.jupiter.api.Test;

import java.text.ParsePosition;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class RFC3339DateFormatTest {

    @Test
    void givenInstantWhenFormatThenReturnRfc3339StringInUtc() {
        var dateFormat = new RFC3339DateFormat();
        var date = Date.from(Instant.parse("2026-08-28T12:34:56.789Z"));

        var result = dateFormat.format(date);

        assertThat(result).isEqualTo("2026-08-28T12:34:56.789Z");
    }

    @Test
    void givenParsePositionWhenParseThenReturnDateAndAdvanceIndex() {
        var dateFormat = new RFC3339DateFormat();
        var source = "prefix2026-08-28T14:34:56.789+02:00";
        var position = new ParsePosition("prefix".length());

        var result = dateFormat.parse(source, position);

        assertThat(result).isEqualTo(Date.from(Instant.parse("2026-08-28T12:34:56.789Z")));
        assertThat(position.getIndex()).isEqualTo(source.length());
        assertThat(position.getErrorIndex()).isEqualTo(-1);
    }

    @Test
    void givenTimestampWithoutFractionWhenParseThenReturnDate() {
        var dateFormat = new RFC3339DateFormat();
        var position = new ParsePosition(0);

        var result = dateFormat.parse("2026-08-28T12:34:56Z", position);

        assertThat(result).isEqualTo(Date.from(Instant.parse("2026-08-28T12:34:56Z")));
        assertThat(position.getIndex()).isEqualTo("2026-08-28T12:34:56Z".length());
        assertThat(position.getErrorIndex()).isEqualTo(-1);
    }

    @Test
    void givenInvalidValueWhenParseThenReturnNullAndSetErrorIndex() {
        var dateFormat = new RFC3339DateFormat();
        var source = "prefixinvalid";
        var position = new ParsePosition("prefix".length());

        var result = dateFormat.parse(source, position);

        assertThat(result).isNull();
        assertThat(position.getIndex()).isEqualTo("prefix".length());
        assertThat(position.getErrorIndex()).isGreaterThanOrEqualTo("prefix".length());
    }

    @Test
    void givenCloneWhenCloneThenReturnNewDateFormatInstance() {
        var dateFormat = new RFC3339DateFormat();

        var clone = dateFormat.clone();

        assertThat(clone).isInstanceOf(RFC3339DateFormat.class).isNotSameAs(dateFormat);
    }
}
