package org.opendevstack.component_catalog.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@UtilityClass
@Slf4j
public class JwtUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Extracts the value of a claim from a JWT token without validating the signature.
     * Suitable for use as a cache key — the token signature is already validated upstream by the
     * Azure AD Spring Security filter.
     *
     * @param token the JWT bearer token (header.payload.signature)
     * @param claim the claim name to extract (e.g. "oid", "upn", "sub")
     * @return the claim value as String, or empty if not found or the token is malformed
     */
    public Optional<String> extractClaim(String token, String claim) {
        try {
            var parts = token.split("\\.");
            if (parts.length < 2) {
                return Optional.empty();
            }
            var payloadJson = new String(Base64.getUrlDecoder().decode(padBase64(parts[1])));
            Map<String, Object> claims = OBJECT_MAPPER.readValue(payloadJson, new TypeReference<>() {});
            return Optional.ofNullable(claims.get(claim)).map(Object::toString);
        } catch (Exception e) {
            log.warn("Unable to extract claim '{}' from JWT token", claim, e);
            return Optional.empty();
        }
    }

    private String padBase64(String base64) {
        return switch (base64.length() % 4) {
            case 2 -> base64 + "==";
            case 3 -> base64 + "=";
            default -> base64;
        };
    }
}

