package org.opendevstack.component_catalog.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies that date/time values are serialized as RFC 3339 strings in UTC using the Spring-configured
 * {@link ObjectMapper}. This behavior was previously provided by a custom {@code RFC3339DateFormat} class and is now
 * configured through standard {@code spring.jackson.*} properties in {@code application.yml}.
 */
@SpringBootTest
@ActiveProfiles("testing")
class JacksonDateSerializationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenLegacyDateWhenSerializeThenReturnsRfc3339StringInUtc() throws JsonProcessingException {
        // given
        var date = Date.from(Instant.parse("2026-08-28T12:34:56.789Z"));

        // when
        var json = objectMapper.writeValueAsString(date);

        // then
        assertThat(json).isEqualTo("\"2026-08-28T12:34:56.789Z\"");
    }

    @Test
    void givenLegacyDateWithOffsetWhenSerializeThenNormalizesToUtc() throws JsonProcessingException {
        // given
        // 14:34:56.789 at +02:00 is 12:34:56.789 UTC
        var date = Date.from(OffsetDateTime.of(2026, 8, 28, 14, 34, 56, 789_000_000, ZoneOffset.ofHours(2))
                .toInstant());

        // when
        var json = objectMapper.writeValueAsString(date);

        // then
        assertThat(json).isEqualTo("\"2026-08-28T12:34:56.789Z\"");
    }

    @Test
    void givenOffsetDateTimeWhenSerializeThenNormalizesToUtcRfc3339String() throws JsonProcessingException {
        // given
        var dateTime = OffsetDateTime.of(2026, 8, 28, 14, 34, 56, 789_000_000, ZoneOffset.ofHours(2));

        // when
        var json = objectMapper.writeValueAsString(dateTime);

        // then
        // The configured UTC time-zone normalizes the offset, so 14:34:56.789+02:00 becomes 12:34:56.789Z.
        assertThat(json).isEqualTo("\"2026-08-28T12:34:56.789Z\"");
    }

    @Test
    void givenRfc3339StringWhenDeserializeToDateThenParsesInstant() throws JsonProcessingException {
        // given
        var json = "\"2026-08-28T14:34:56.789+02:00\"";

        // when
        var date = objectMapper.readValue(json, Date.class);

        // then
        assertThat(date).isEqualTo(Date.from(Instant.parse("2026-08-28T12:34:56.789Z")));
    }
}

