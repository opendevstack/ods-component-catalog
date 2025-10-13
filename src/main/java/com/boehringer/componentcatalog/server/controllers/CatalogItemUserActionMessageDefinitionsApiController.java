package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.api.CatalogItemUserActionMessageDefinitionsApi;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentcatalog.server.services.UserActionsEntitiesService;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageDefinition;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageTitle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.boehringer.componentcatalog.server.controllers.CatalogApiAdapter.asCatalogItemUserActionMessageDefinition;

import static org.apache.commons.text.StringSubstitutor.replace;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogItemUserActionMessageDefinitionsApiController implements CatalogItemUserActionMessageDefinitionsApi {

    private final UserActionsEntitiesService userActionsEntitiesService;

    @Override
    public ResponseEntity<CatalogItemUserActionMessageDefinition> getMessageDefinitionByCatalogItemIdAndMessageId(String catalogItemId, String userActionId, String messageDefinitionId, Map<String, String> placeholdersValues) {
        return getMessageDefinition(
                () -> userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(catalogItemId, userActionId, messageDefinitionId),
                placeholdersValues
        );
    }

    private ResponseEntity<CatalogItemUserActionMessageDefinition> getMessageDefinition(Supplier<Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>>> supplier, Map<String, String> placeholdersValues) {
        var maybeUserActionEntityMsgDefPair = supplier.get();

        if (maybeUserActionEntityMsgDefPair.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var userActionEntityMsgDefPair = maybeUserActionEntityMsgDefPair.get();

        var itemUserActionMessageDefinition = asCatalogItemUserActionMessageDefinition(
                userActionEntityMsgDefPair.getLeft(),
                userActionEntityMsgDefPair.getRight());

        itemUserActionMessageDefinition
                .title(replace(itemUserActionMessageDefinition.getTitle(), placeholdersValues))
                .message(replace(itemUserActionMessageDefinition.getMessage(), placeholdersValues));

        return ResponseEntity.ok(itemUserActionMessageDefinition);
    }
}
