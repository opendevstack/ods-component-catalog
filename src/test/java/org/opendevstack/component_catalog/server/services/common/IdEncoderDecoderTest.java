package org.opendevstack.component_catalog.server.services.common;

import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IdEncoderDecoderTest {

    @Test
    void givenAnId_whenIdEncode_thenIdIsAsExpected() {
        // given
        var sourceId = "/projects/CATALOGS/repos/catalogs-collection/raw/CatalogCollections.yaml?at=feature/EPIC-EDPC-3360-Enable-Marketplace-component-input-parameter-validation";

        // when
        var encodedId = IdEncoderDecoder.idEncode(sourceId);

        // then
        assertThat(encodedId).isEqualTo("L3Byb2plY3RzL0NBVEFMT0dTL3JlcG9zL2NhdGFsb2dzLWNvbGxlY3Rpb24vcmF3L0NhdGFsb2dDb2xsZWN0aW9ucy55YW1sP2F0PWZlYXR1cmUvRVBJQy1FRFBDLTMzNjAtRW5hYmxlLU1hcmtldHBsYWNlLWNvbXBvbmVudC1pbnB1dC1wYXJhbWV0ZXItdmFsaWRhdGlvbg==");
    }

    @Test
    void givenAnId_whenNullableIdEncode_thenIdIsAsExpected() {
        // given
        var sourceId = "/projects/CATALOGS/repos/catalogs-collection/raw/UserActions.yaml?at=feature/EPIC-EDPC-3360-Enable-Marketplace-component-input-parameter-validation";

        // when
        var encodedId = IdEncoderDecoder.nullableIdEncode(sourceId);

        // then
        assertThat(encodedId).isEqualTo("L3Byb2plY3RzL0NBVEFMT0dTL3JlcG9zL2NhdGFsb2dzLWNvbGxlY3Rpb24vcmF3L1VzZXJBY3Rpb25zLnlhbWw_YXQ9ZmVhdHVyZS9FUElDLUVEUEMtMzM2MC1FbmFibGUtTWFya2V0cGxhY2UtY29tcG9uZW50LWlucHV0LXBhcmFtZXRlci12YWxpZGF0aW9u");
    }

    @Test
    void givenAnNullId_whenNullableIdEncode_thenIdIsAsExpected() {
        // given
        String sourceId = null;

        // when
        var encodedId = IdEncoderDecoder.nullableIdEncode(sourceId);

        // then
        assertThat(encodedId).isNull();
    }

    @Test
    void givenAnEncodedId_whenIdDecode_thenIdIsAsExpected() throws InvalidIdException {
        // given
        var encodedId = "L3Byb2plY3RzL0NBVEFMT0dTL3JlcG9zL2NhdGFsb2dzLWNvbGxlY3Rpb24vcmF3L0NhdGFsb2dDb2xsZWN0aW9ucy55YW1sP2F0PWZlYXR1cmUvRVBJQy1FRFBDLTMzNjAtRW5hYmxlLU1hcmtldHBsYWNlLWNvbXBvbmVudC1pbnB1dC1wYXJhbWV0ZXItdmFsaWRhdGlvbg==";

        // when
        var decodedId = IdEncoderDecoder.idDecode(encodedId);

        // then
        assertThat(decodedId).isEqualTo("/projects/CATALOGS/repos/catalogs-collection/raw/CatalogCollections.yaml?at=feature/EPIC-EDPC-3360-Enable-Marketplace-component-input-parameter-validation");
    }
}
