package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.mappers.EntitiesMapper;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntitySpec;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserActionsEntitiesService {

    private final CatalogServiceAdapter catalogServiceAdapter;
    private final EntitiesMapper entitiesMapper;

    @Value("${user-actions.id}")
    private String defaultUserActionsBitbucketId;

    @Value("${user-actions.default-message-definition-id:DEFAULT_ERROR}")
    private String defaultMessageDefinitionId;

    public UserActionsEntitiesService(CatalogServiceAdapter catalogServiceAdapter, EntitiesMapper entitiesMapper) {
        this.catalogServiceAdapter = catalogServiceAdapter;
        this.entitiesMapper = entitiesMapper;
    }

    public UserActionsEntity getCatalogEntityUserActionsEntity(String userActionsId) {
        try {
            var userActionsIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(userActionsId);

            var catalogEntity = catalogServiceAdapter.getCatalogEntity(userActionsIdPathAt, CatalogItemEntity.class)
                    .orElseThrow(() -> new IllegalStateException("Catalog entity not found, userActionsId: %s"
                            .formatted(userActionsId)));

            var userActions = Optional.ofNullable(catalogEntity.getSpec())
                    .map(CatalogItemEntitySpec::getUserActions)
                    .orElseThrow(() -> new IllegalStateException("User actions entity not found, userActionsId: %s"
                            .formatted(userActionsId)));


            var userActionEntities = Arrays.stream(userActions)
                    .map(entitiesMapper::asUserActionsEntity)
                    .toArray(UserActionEntity[]::new);

            var userActionsEntitySpec = UserActionsEntitySpec.builder()
                    .actions(userActionEntities)
                    .build();

            return UserActionsEntity.builder()
                    .kind(catalogEntity.getKind())
                    .spec(userActionsEntitySpec)
                    .build();
        } catch (InvalidIdException | IllegalStateException e) {
            throw new IllegalArgumentException(("Unable to get user actions, either userActionsId is misconfigured " +
                    "or Bitbucket service is down, userActionsId: '%s'")
                    .formatted(userActionsId), e);
        }
    }

    public UserActionsEntity getDefaultUserActionsEntity() {
        return getUserActionsEntity(defaultUserActionsBitbucketId);
    }

    public UserActionsEntity getUserActionsEntity(String userActionsId) {
        try {
            var userActionsIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(userActionsId);

            return catalogServiceAdapter.getCatalogEntity(userActionsIdPathAt, UserActionsEntity.class)
                    .orElseThrow(() -> new IllegalStateException("User actions entity not found, userActionsId: %s"
                            .formatted(userActionsId)));
        } catch (InvalidIdException | IllegalStateException e) {
            throw new IllegalArgumentException(("Unable to get user actions, either userActionsId is misconfigured " +
                    "or Bitbucket service is down, userActionsId: '%s'")
                    .formatted(userActionsId), e);
        }
    }

    public Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(
            String catalogItemId,
            String userActionId,
            String messageDefinitionId) {
        var defaultMaybeMessageDefinitionWithTitle = getUserActionEntityMessageDefinitionWithTitle(defaultUserActionsBitbucketId, userActionId, messageDefinitionId);

        // If there is no configuration in the default user actions entity, even when defined in the custom, we consider it as not found
        if (defaultMaybeMessageDefinitionWithTitle.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> customMaybeMessageDefinitionWithTitle;

            try {
                // If messageDefinitionId is not found in the custom user actions entity, we fallback to the default one
                customMaybeMessageDefinitionWithTitle = getCatalogItemUserActionEntityMessageDefinitionWithTitle(catalogItemId, userActionId, messageDefinitionId);
            } catch (Exception e) {
                log.warn("Invalid catalog item id: '{}', unable to get user action entity message definition with title for message definition id: '{}'",
                        catalogItemId, messageDefinitionId, e);

                customMaybeMessageDefinitionWithTitle = Optional.empty();
            }

            if (customMaybeMessageDefinitionWithTitle.isEmpty() || customMaybeMessageDefinitionWithTitle.get().getRight().getId().equals(defaultMessageDefinitionId)) {
                return defaultMaybeMessageDefinitionWithTitle;
            } else {
                // If both are present, we return the custom one
                return customMaybeMessageDefinitionWithTitle;
            }
        }
    }

    public Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> getUserActionEntityMessageDefinitionWithTitle(
            String userActionsBitbucketId,
            String userActionId,
            String messageDefinitionId) {

        var userActionEntityTitles = getUserActionsEntityMessageTitles(userActionsBitbucketId, userActionId);
        var userActionEntityMessageDefinitions = getUserActionsEntityMessageDefinitions(userActionsBitbucketId, userActionId);

        var maybeUserActionEntityMessageDefinition = findMessageDefinition(userActionEntityMessageDefinitions, messageDefinitionId)
                .or(() -> findMessageDefinition(userActionEntityMessageDefinitions, defaultMessageDefinitionId));

        if (maybeUserActionEntityMessageDefinition.isEmpty()) {
            return Optional.empty();
        }

        var maybeUserActionEntityTitle = userActionEntityTitles.stream()
                .filter(title -> title.getId() == maybeUserActionEntityMessageDefinition.get().getType())
                .findFirst();

        if (maybeUserActionEntityTitle.isEmpty()) {
            throw new IllegalArgumentException(("Misconfiguration error: user action entity message title not found for " +
                    "userActionId: '%s', messageDefinitionId: '%s'")
                    .formatted(userActionId, messageDefinitionId));
        }

        return Optional.of(Pair.of(
                maybeUserActionEntityTitle.get(),
                maybeUserActionEntityMessageDefinition.get()));
    }

    public Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> getCatalogItemUserActionEntityMessageDefinitionWithTitle(
            String userActionsBitbucketId,
            String userActionId,
            String messageDefinitionId) {

        var userActionEntityTitles = getCatalogEntityUserActionsEntityMessageTitles(userActionsBitbucketId, userActionId);
        var userActionEntityMessageDefinitions = getCatalogEntityUserActionsEntityMessageDefinitions(userActionsBitbucketId, userActionId);

        var maybeUserActionEntityMessageDefinition = findMessageDefinition(userActionEntityMessageDefinitions, messageDefinitionId)
                .or(() -> findMessageDefinition(userActionEntityMessageDefinitions, defaultMessageDefinitionId));

        if (maybeUserActionEntityMessageDefinition.isEmpty()) {
            return Optional.empty();
        }

        var maybeUserActionEntityTitle = userActionEntityTitles.stream()
                .filter(title -> title.getId() == maybeUserActionEntityMessageDefinition.get().getType())
                .findFirst();

        if (maybeUserActionEntityTitle.isEmpty()) {
            throw new IllegalArgumentException(("Misconfiguration error: user action entity message title not found for " +
                    "userActionId: '%s', messageDefinitionId: '%s'")
                    .formatted(userActionId, messageDefinitionId));
        }

        return Optional.of(Pair.of(
                maybeUserActionEntityTitle.get(),
                maybeUserActionEntityMessageDefinition.get()));
    }

    public List<UserActionEntityMessageTitle> getUserActionsEntityMessageTitles(String userActionsBitbucketId, String userActionId) {
        var userActionEntities = getUserActionsEntity(userActionsBitbucketId).getSpec().getActions();

        return Stream.of(userActionEntities)
                .filter(action -> action.getId().equals(userActionId))
                .findFirst()
                .map(UserActionEntity::getMessagesTitles)
                .map(List::of)
                .orElseThrow(() -> new IllegalStateException(("User action entity message titles not found " +
                        "for user action userActionId: %s")
                        .formatted(userActionId)));
    }

    public List<UserActionEntityMessageTitle> getCatalogEntityUserActionsEntityMessageTitles(String userActionsBitbucketId, String userActionId) {
        var userActionEntities = getCatalogEntityUserActionsEntity(userActionsBitbucketId).getSpec().getActions();

        return Stream.of(userActionEntities)
                .filter(action -> action.getId().equals(userActionId))
                .findFirst()
                .map(UserActionEntity::getMessagesTitles)
                .map(List::of)
                .orElseThrow(() -> new IllegalStateException(("User action entity message titles not found " +
                        "for user action userActionId: %s")
                        .formatted(userActionId)));
    }

    public List<UserActionEntityMessageDefinition> getCatalogEntityUserActionsEntityMessageDefinitions(String userActionsBitbucketId, String userActionId) {
        var userActionEntities = getCatalogEntityUserActionsEntity(userActionsBitbucketId).getSpec().getActions();

        return Stream.of(userActionEntities)
                .filter(action -> action.getId().equals(userActionId))
                .findFirst()
                .map(UserActionEntity::getMessagesDefinitions)
                .map(List::of)
                .orElseThrow(() -> new IllegalStateException(("Misconfiguration error: user action entity not found " +
                        "for user action message definitions, userActionId: %s")
                        .formatted(userActionId)));
    }

    public List<UserActionEntityMessageDefinition> getUserActionsEntityMessageDefinitions(String userActionsBitbucketId, String userActionId) {
        var userActionEntities = getUserActionsEntity(userActionsBitbucketId).getSpec().getActions();

        return Stream.of(userActionEntities)
                .filter(action -> action.getId().equals(userActionId))
                .findFirst()
                .map(UserActionEntity::getMessagesDefinitions)
                .map(List::of)
                .orElseThrow(() -> new IllegalStateException(("Misconfiguration error: user action entity not found " +
                        "for user action message definitions, userActionId: %s")
                        .formatted(userActionId)));
    }

    private Optional<UserActionEntityMessageDefinition> findMessageDefinition(List<UserActionEntityMessageDefinition> definitions, String id) {
        return definitions.stream()
                .filter(def -> def.getId().equals(id))
                .findFirst();
    }

}
