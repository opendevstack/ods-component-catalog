package org.opendevstack.component_catalog.util;

import org.junit.jupiter.api.Test;

import java.util.Base64;

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

