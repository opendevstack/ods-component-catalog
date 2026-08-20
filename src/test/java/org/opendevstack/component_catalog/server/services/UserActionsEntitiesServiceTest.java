package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionMapper;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionParameterMapper;
import org.opendevstack.component_catalog.server.mappers.EntitiesMapper;
import org.opendevstack.component_catalog.server.mother.UserActionEntityMessageDefinitionMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.UserActionEntityMessageType;
import org.opendevstack.component_catalog.server.services.catalog.business.*;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserAction;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserActionMother;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserActionsEntitiesServiceTest {

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    private UserActionsEntitiesService userActionsEntitiesService;

    @BeforeEach
    void setUp() {
        var groupsRestrictionProps =
                ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> Pair.of(true, "");

        var catalogItemUserActionMapper = new CatalogItemUserActionMapper(new CatalogItemUserActionParameterMapper(),
                List.of(dummyEvaluator), groupsRestrictionProps);

        var entitiesMapper = new EntitiesMapper(catalogItemUserActionMapper);

        this.userActionsEntitiesService = new UserActionsEntitiesService(catalogServiceAdapter, entitiesMapper);
        ReflectionTestUtils.setField(userActionsEntitiesService, "defaultUserActionsBitbucketId", "testId");
        ReflectionTestUtils.setField(userActionsEntitiesService, "defaultMessageDefinitionId", "DEFAULT_ERROR");
    }

    @Test
    void getUserActionsEntity_success() throws InvalidIdException {
        var entityId = "testId";

        UserActionsEntity entity = mock(UserActionsEntity.class);
        var userActionsPathAt = mock(BitbucketPathAt.class);
        when(catalogServiceAdapter.bitbucketPathAtFromId(entityId)).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        assertThat(userActionsEntitiesService.getUserActionsEntity(entityId)).isEqualTo(entity);
    }

    @Test
    void getUserActionsEntity_invalidIdException() throws InvalidIdException {
        var entityId = "testId";

        when(catalogServiceAdapter.bitbucketPathAtFromId("testId")).thenThrow(new InvalidIdException("err"));

        assertThatThrownBy(() -> userActionsEntitiesService.getUserActionsEntity(entityId))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getUserActionsEntity_notFound() throws InvalidIdException {
        var entityId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        when(catalogServiceAdapter.bitbucketPathAtFromId("testId")).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userActionsEntitiesService.getUserActionsEntity(entityId))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getUserActionEntityMessageDefinitionWithTitle_found() throws InvalidIdException {
        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntityMessageTitle title = mock(UserActionEntityMessageTitle.class);
        UserActionEntityMessageDefinition def = mock(UserActionEntityMessageDefinition.class);
        when(def.getId()).thenReturn("defId");
        when(def.getType()).thenReturn(UserActionEntityMessageType.SUCCESS);

        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesTitles()).thenReturn(new UserActionEntityMessageTitle[]{title});
        when(action.getMessagesDefinitions()).thenReturn(new UserActionEntityMessageDefinition[]{def});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId("actionBbId")).thenReturn(userActionsPathAt);

        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        when(title.getId()).thenReturn(UserActionEntityMessageType.SUCCESS);

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle(
                        "actionBbId",
                        "actionId",
                        "defId"
                );

        assertThat(result).isPresent();
        assertThat(result.orElseThrow().getLeft()).isEqualTo(title);
        assertThat(result.orElseThrow().getRight()).isEqualTo(def);
    }

    @Test
    void getUserActionEntityMessageDefinitionWithTitle_notFound_returnsEmpty() throws InvalidIdException {
        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntityMessageDefinition def = mock(UserActionEntityMessageDefinition.class);
        when(def.getId()).thenReturn("otherId");

        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesTitles())
                .thenReturn(new UserActionEntityMessageTitle[]{mock(UserActionEntityMessageTitle.class)});
        when(action.getMessagesDefinitions()).thenReturn(new UserActionEntityMessageDefinition[]{def});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId("actionBbId")).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle(
                        "actionBbId",
                        "actionId",
                        "defId"
                );

        assertThat(result).isEmpty();
    }

    @Test
    void getUserActionEntityMessageDefinitionWithTitle_defaultMessageDefinition_used() throws InvalidIdException {
        var userActionsPathAt = mock(BitbucketPathAt.class);

        UserActionEntityMessageDefinition defaultDef = mock(UserActionEntityMessageDefinition.class);
        when(defaultDef.getId()).thenReturn("DEFAULT_ERROR");
        when(defaultDef.getType()).thenReturn(UserActionEntityMessageType.ERROR);

        UserActionEntityMessageTitle errorTitle = mock(UserActionEntityMessageTitle.class);
        when(errorTitle.getId()).thenReturn(UserActionEntityMessageType.ERROR);

        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesTitles()).thenReturn(new UserActionEntityMessageTitle[]{errorTitle});
        when(action.getMessagesDefinitions()).thenReturn(new UserActionEntityMessageDefinition[]{defaultDef});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId("actionBbId")).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle(
                        "actionBbId",
                        "actionId",
                        "nonexistentId"
                );

        assertThat(result).isPresent();
        assertThat(result.orElseThrow().getLeft()).isEqualTo(errorTitle);
        assertThat(result.orElseThrow().getRight()).isEqualTo(defaultDef);
    }

    @Test
    void getUserActionsEntityMessageTitles_found() throws InvalidIdException {
        var bitbucketId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntityMessageTitle title = mock(UserActionEntityMessageTitle.class);
        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesTitles()).thenReturn(new UserActionEntityMessageTitle[]{title});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        List<UserActionEntityMessageTitle> result =
                userActionsEntitiesService.getUserActionsEntityMessageTitles(bitbucketId, "actionId");
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(title);
    }

    @Test
    void getUserActionsEntityMessageTitles_notFound_throws() throws InvalidIdException {
        var bitbucketId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("otherId");

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        assertThatThrownBy(
                () -> userActionsEntitiesService.getUserActionsEntityMessageTitles(bitbucketId, "actionId")
        )
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getUserActionsEntityMessageDefinitions_found() throws InvalidIdException {
        var bitbucketId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntityMessageDefinition def = mock(UserActionEntityMessageDefinition.class);
        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesDefinitions()).thenReturn(new UserActionEntityMessageDefinition[]{def});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        List<UserActionEntityMessageDefinition> result =
                userActionsEntitiesService.getUserActionsEntityMessageDefinitions(bitbucketId, "actionId");
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(def);
    }

    @Test
    void getUserActionsEntityMessageDefinitions_notFound_throws() throws InvalidIdException {
        var bitbucketId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("otherId");

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(userActionsPathAt)).thenReturn(Optional.of(entity));

        assertThatThrownBy(
                () -> userActionsEntitiesService.getUserActionsEntityMessageDefinitions(bitbucketId, "actionId")
        )
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void givenCatalogItemIdAndMessageDefinition_whenGetDefinitionWithTitle_thenCustomMessageIsReturned()
            throws InvalidIdException {
        // Given
        var bitbucketId = "testId";

        String catalogItemId = "catalogItemId";
        String userActionId = "userActionId";
        String messageDefinitionId = "messageDefinitionId";

        String customMessageToOverrideDefaultMessage =
                "This is a custom message that overrides the default message";

        buildAndInitializeDefaultUserActionsMocks(bitbucketId, userActionId, messageDefinitionId);
        buildAndInitializeCustomUserActionsMocks(
                catalogItemId,
                userActionId,
                messageDefinitionId,
                customMessageToOverrideDefaultMessage
        );

        // when
        var optMessageDefinitionWithTitle =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(
                        catalogItemId,
                        userActionId,
                        messageDefinitionId
                );

        // Then
        assertThat(optMessageDefinitionWithTitle).isPresent();
        assertThat(optMessageDefinitionWithTitle.get().getLeft().getTitle())
                .isEqualTo("User Action Entity Message Title for success");
        assertThat(optMessageDefinitionWithTitle.get().getRight().getMessage())
                .isEqualTo(customMessageToOverrideDefaultMessage);
    }

    @Test
    void givenCatalogItemIdAndNoCustomDefinition_whenGetDefinitionWithTitle_thenDefaultMessageIsReturned()
            throws InvalidIdException {
        // Given
        var bitbucketId = "testId";

        String catalogItemId = "catalogItemId";
        String userActionId = "userActionId";
        String messageDefinitionId = "messageDefinitionId";

        buildAndInitializeDefaultUserActionsMocks(bitbucketId, userActionId, messageDefinitionId);
        buildAndInitializeCustomUserActionsMocksWithoutDefinitionId(catalogItemId);

        // when
        var optMessageDefinitionWithTitle =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(
                        catalogItemId,
                        userActionId,
                        messageDefinitionId
                );

        // Then
        assertThat(optMessageDefinitionWithTitle).isPresent();
        assertThat(optMessageDefinitionWithTitle.get().getLeft().getTitle())
                .isEqualTo("User Action Entity Message Title for success");
        assertThat(optMessageDefinitionWithTitle.get().getRight().getMessage())
                .isEqualTo("Simple message for testing purposes for messageDefinitionId with type success");
    }

    private void buildAndInitializeDefaultUserActionsMocks(
            String bitbucketId,
            String userActionId,
            String messageDefinitionId
    ) throws InvalidIdException {
        // Initialize data
        UserActionEntityMessageDefinition[] messageDefinitionsArray = UserActionEntityMessageDefinitionMother.ofArray(
                UserActionEntityMessageDefinitionMother.of(messageDefinitionId, UserActionEntityMessageType.SUCCESS)
        );

        List<UserActionEntity> userActions = List.of(
                UserActionEntityMother.of("userActionId1"),
                UserActionEntityMother.of("userActionId2"),
                UserActionEntityMother.of(userActionId, messageDefinitionsArray)
        );

        UserActionsEntity defaultUserActionsEntity = UserActionsEntityMother.of(userActions);

        // define mocks
        BitbucketPathAt bitbucketIdUserActionsPathAt = mock(BitbucketPathAt.class);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(bitbucketIdUserActionsPathAt);
        when(catalogServiceAdapter.getUserActionsEntity(bitbucketIdUserActionsPathAt))
                .thenReturn(Optional.of(defaultUserActionsEntity));
    }

    private void buildAndInitializeCustomUserActionsMocks(
            String catalogItemId,
            String userActionId,
            String messageDefinitionId,
            String messageDefinitionMessage
    ) throws InvalidIdException {
        // Initialize data
        UserActionEntityMessageDefinition[] messageDefinitionsArray = UserActionEntityMessageDefinitionMother.ofArray(
                UserActionEntityMessageDefinitionMother.of(
                        messageDefinitionId,
                        UserActionEntityMessageType.SUCCESS,
                        messageDefinitionMessage
                )
        );

        List<CatalogItemEntityUserAction> userActions = List.of(
                CatalogItemEntityUserActionMother.of("customUserActionId1"),
                CatalogItemEntityUserActionMother.of("customUserActionId2"),
                CatalogItemEntityUserActionMother.of(userActionId, messageDefinitionsArray)
        );

        buildAndInitializeCustomUserActionsMocks(catalogItemId, userActions);

    }

    private void buildAndInitializeCustomUserActionsMocksWithoutDefinitionId(String catalogItemId)
            throws InvalidIdException {
        // Initialize data
        List<CatalogItemEntityUserAction> userActions = List.of(
                CatalogItemEntityUserActionMother.of("customUserActionId1"),
                CatalogItemEntityUserActionMother.of("customUserActionId2")
        );

        buildAndInitializeCustomUserActionsMocks(catalogItemId, userActions);

    }

    private void buildAndInitializeCustomUserActionsMocks(
            String catalogItemId,
            List<CatalogItemEntityUserAction> userActions
    ) throws InvalidIdException {
        // Initialize data
        CatalogItemEntity customUserActionsEntity =
                CatalogItemEntityMother.of(userActions.toArray(new CatalogItemEntityUserAction[0]));

        // define mocks
        BitbucketPathAt catalogItemIdUserActionsPathAt = mock(BitbucketPathAt.class);


        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId))
                .thenReturn(catalogItemIdUserActionsPathAt);
        when(catalogServiceAdapter.getCatalogItemEntity(catalogItemIdUserActionsPathAt))
                .thenReturn(Optional.of(customUserActionsEntity));

    }
}
