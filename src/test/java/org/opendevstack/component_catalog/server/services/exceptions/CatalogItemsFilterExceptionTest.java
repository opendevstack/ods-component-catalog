package org.opendevstack.component_catalog.server.services.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogItemsFilterExceptionTest {

    @Test
    void GivenMessage_WhenCreatingCatalogItemsFilterException_ThenStoresMessageAndIsRuntimeException() {
        // given
        var message = "Only one project key is allowed";

        // when
        var exception = new CatalogItemsFilterException(message);

        // then
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }
}

