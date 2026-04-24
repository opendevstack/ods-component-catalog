package org.opendevstack.component_catalog.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Base64;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilsTest {

    // Minimal JWT: header.payload.signature (signature not validated)
    private static String buildJwt(String payloadJson) {
        var header = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"RS256\",\"typ\":\"JWT\"}".getBytes());
        var payload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(payloadJson.getBytes());
        return header + "." + payload + ".fake-signature";
    }

    /**
     * Builds JWTs whose Base64url payload (without padding) has a specific length % 4.
     * Each case exercises a different branch of padBase64:
     *   % 4 == 0 → no padding needed (default branch)
     *   % 4 == 2 → "==" appended
     *   % 4 == 3 → "=" appended
     *   % 4 == 1 → invalid Base64 (default branch, decode will fail → empty Optional)
     */
    static Stream<String> payloadsWithEachPaddingCase() {
        // We craft JSON strings so that Base64url(withoutPadding) length % 4 hits each case.
        // The oid value is varied until we hit the desired remainder.
        return Stream.of(
            payloadWithRemainder(0),   // default — already aligned
            payloadWithRemainder(2),   // case 2 → append "=="
            payloadWithRemainder(3)    // case 3 → append "="
            // remainder 1 is mathematically impossible for valid Base64 input, skipped intentionally
        );
    }

    private static String payloadWithRemainder(int targetRemainder) {
        // Vary the oid suffix until Base64url payload length % 4 == targetRemainder
        for (int i = 0; i < 100; i++) {
            var json = "{\"oid\":\"test-oid-" + "x".repeat(i) + "\"}";
            var encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(json.getBytes());
            if (encoded.length() % 4 == targetRemainder) {
                return json;
            }
        }
        throw new IllegalStateException("Could not find payload for remainder " + targetRemainder);
    }

    @ParameterizedTest
    @MethodSource("payloadsWithEachPaddingCase")
    void givenPayloadWithVariousPaddingNeeds_whenExtractClaim_thenReturnOid(String payloadJson) {
        var jwt = buildJwt(payloadJson);

        var result = JwtUtils.extractClaim(jwt, "oid");

        assertThat(result).isPresent().get().asString().startsWith("test-oid-");
    }

    @Test
    void givenJwtWithOidClaim_whenExtractClaim_thenReturnOid() {
        var jwt = buildJwt("{\"oid\":\"a1b2c3d4-1234-5678-abcd-ef0123456789\",\"upn\":\"user@company.com\"}");

        var result = JwtUtils.extractClaim(jwt, "oid");

        assertThat(result).contains("a1b2c3d4-1234-5678-abcd-ef0123456789");
    }

    @Test
    void givenJwtWithoutClaim_whenExtractClaim_thenReturnEmpty() {
        var jwt = buildJwt("{\"sub\":\"user123\"}");

        var result = JwtUtils.extractClaim(jwt, "oid");

        assertThat(result).isEmpty();
    }

    @Test
    void givenMalformedToken_whenExtractClaim_thenReturnEmpty() {
        var result = JwtUtils.extractClaim("not-a-jwt", "oid");

        assertThat(result).isEmpty();
    }

    @Test
    void givenNullToken_whenExtractClaim_thenReturnEmpty() {
        var result = JwtUtils.extractClaim(null, "oid");

        assertThat(result).isEmpty();
    }
}

