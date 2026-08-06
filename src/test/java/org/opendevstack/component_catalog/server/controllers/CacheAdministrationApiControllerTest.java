package org.opendevstack.component_catalog.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.services.cache.CacheAdministrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CacheAdministrationApiControllerTest {

    @Mock
    private CacheAdministrationService cacheAdministrationService;

    @InjectMocks
    private CacheAdministrationApiController controller;

    @Test
    void givenCacheName_whenRefreshCache_thenDelegateToServiceAndReturnAccepted() {
        // Given
        String cacheName = "catalogsCollectionCache";

        // When
        ResponseEntity<Void> response = controller.refreshCache(cacheName);

        // Then
        verify(cacheAdministrationService).refreshCache(cacheName);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void givenServiceThrowsException_whenRefreshCache_thenPropagateException() {
        // Given
        String cacheName = "catalogsCollectionCache";

        doThrow(new RuntimeException("boom"))
                .when(cacheAdministrationService)
                .refreshCache(cacheName);

        // Then
        assertThrows(
                RuntimeException.class,
                () -> controller.refreshCache(cacheName)
        );
    }
}
