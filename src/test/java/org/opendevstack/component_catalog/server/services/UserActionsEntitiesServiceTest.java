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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserActionsEntitiesServiceTest {

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    private UserActionsEntitiesService userActionsEntitiesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        var groupsRestrictionProps = ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        assertEquals(entity, userActionsEntitiesService.getUserActionsEntity(entityId));
    }

    @Test
    void getUserActionsEntity_invalidIdException() throws InvalidIdException {
        var entityId = "testId";

        when(catalogServiceAdapter.bitbucketPathAtFromId("testId")).thenThrow(new InvalidIdException("err"));

        assertThrows(IllegalArgumentException.class, () -> userActionsEntitiesService.getUserActionsEntity(entityId));
    }

    @Test
    void getUserActionsEntity_notFound() throws InvalidIdException {
        var entityId = "testId";

        var userActionsPathAt = mock(BitbucketPathAt.class);
        when(catalogServiceAdapter.bitbucketPathAtFromId("testId")).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userActionsEntitiesService.getUserActionsEntity(entityId));
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

        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        when(title.getId()).thenReturn(UserActionEntityMessageType.SUCCESS);

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle("actionBbId", "actionId", "defId");

        assertTrue(result.isPresent());
        assertEquals(title, result.get().getLeft());
        assertEquals(def, result.get().getRight());
    }

    @Test
    void getUserActionEntityMessageDefinitionWithTitle_notFound_returnsEmpty() throws InvalidIdException {
        var userActionsPathAt = mock(BitbucketPathAt.class);
        UserActionEntityMessageDefinition def = mock(UserActionEntityMessageDefinition.class);
        when(def.getId()).thenReturn("otherId");
        when(def.getType()).thenReturn(UserActionEntityMessageType.ERROR);

        UserActionEntity action = mock(UserActionEntity.class);
        when(action.getId()).thenReturn("actionId");
        when(action.getMessagesTitles()).thenReturn(new UserActionEntityMessageTitle[]{mock(UserActionEntityMessageTitle.class)});
        when(action.getMessagesDefinitions()).thenReturn(new UserActionEntityMessageDefinition[]{def});

        UserActionsEntitySpec spec = mock(UserActionsEntitySpec.class);
        when(spec.getActions()).thenReturn(new UserActionEntity[]{action});

        UserActionsEntity entity = mock(UserActionsEntity.class);
        when(entity.getSpec()).thenReturn(spec);

        when(catalogServiceAdapter.bitbucketPathAtFromId("actionBbId")).thenReturn(userActionsPathAt);
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle("actionBbId", "actionId", "defId");

        assertTrue(result.isEmpty());
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        Optional<Pair<UserActionEntityMessageTitle, UserActionEntityMessageDefinition>> result =
                userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitle("actionBbId", "actionId", "nonexistentId");

        assertTrue(result.isPresent());
        assertEquals(errorTitle, result.get().getLeft());
        assertEquals(defaultDef, result.get().getRight());
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        List<UserActionEntityMessageTitle> result = userActionsEntitiesService.getUserActionsEntityMessageTitles(bitbucketId, "actionId");
        assertEquals(1, result.size());
        assertEquals(title, result.getFirst());
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        assertThrows(IllegalStateException.class, () -> userActionsEntitiesService.getUserActionsEntityMessageTitles(bitbucketId, "actionId"));
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        List<UserActionEntityMessageDefinition> result = userActionsEntitiesService.getUserActionsEntityMessageDefinitions(bitbucketId, "actionId");
        assertEquals(1, result.size());
        assertEquals(def, result.getFirst());
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
        when(catalogServiceAdapter.getCatalogEntity(userActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(entity));

        assertThrows(IllegalStateException.class, () -> userActionsEntitiesService.getUserActionsEntityMessageDefinitions(bitbucketId, "actionId"));
    }

    @Test
    void givenACatalogItemId_andUserActionId_andMessageDefinitionId_whenGetUserActionEntityMessageDefinitionWithTitleFromCatalogItemId_thenPairTitleMessageDefinitionIsReturned() throws InvalidIdException {
        // Given
        var bitbucketId = "testId";

        String catalogItemId = "catalogItemId";
        String userActionId = "userActionId";
        String messageDefinitionId = "messageDefinitionId";

        String customMessageToOverrideDefaultMessage = "This is a custom message that overrides the default message";

        buildAndInitializeMocksForDefaultUserActionsEntity(bitbucketId, userActionId, messageDefinitionId);
        buildAndInitializeMocksForCustomUserActionsEntity(catalogItemId, userActionId, messageDefinitionId, customMessageToOverrideDefaultMessage);

        // when
        var optMessageDefinitionWithTitle = userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(catalogItemId, userActionId, messageDefinitionId);

        // Then
        assertThat(optMessageDefinitionWithTitle).isPresent();
        assertThat(optMessageDefinitionWithTitle.get().getLeft().getTitle()).isEqualTo("User Action Entity Message Title for success"); // Initialized at mother
        assertThat(optMessageDefinitionWithTitle.get().getRight().getMessage()).isEqualTo(customMessageToOverrideDefaultMessage);
    }

    @Test
    void givenACatalogItemId_andUserActionId_andMessageDefinitionId_whenGetUserActionEntityMessageDefinitionWithTitleFromCatalogItemId_AndNoUserDefinedMessageDefinitionId_thenPairTitleDefaultMessageDefinitionIsReturned() throws InvalidIdException {
        // Given
        var bitbucketId = "testId";

        String catalogItemId = "catalogItemId";
        String userActionId = "userActionId";
        String messageDefinitionId = "messageDefinitionId";

        buildAndInitializeMocksForDefaultUserActionsEntity(bitbucketId, userActionId, messageDefinitionId);
        buildAndInitializeMocksForCustomUserActionsEntityWithNoMessageDefinitionId(catalogItemId);

        // when
        var optMessageDefinitionWithTitle = userActionsEntitiesService.getUserActionEntityMessageDefinitionWithTitleFromCatalogItemId(catalogItemId, userActionId, messageDefinitionId);

        // Then
        assertThat(optMessageDefinitionWithTitle).isPresent();
        assertThat(optMessageDefinitionWithTitle.get().getLeft().getTitle()).isEqualTo("User Action Entity Message Title for success"); // Initialized at mother
        assertThat(optMessageDefinitionWithTitle.get().getRight().getMessage()).isEqualTo("Simple message for testing purposes for messageDefinitionId with type success"); // initialized at mother
    }

    private void buildAndInitializeMocksForDefaultUserActionsEntity(String bitbucketId, String userActionId, String messageDefinitionId) throws InvalidIdException {
        // Initialize data
        UserActionEntityMessageDefinition[] messageDefinitionsArray = UserActionEntityMessageDefinitionMother.ofArray(
                UserActionEntityMessageDefinitionMother.of(messageDefinitionId, UserActionEntityMessageType.SUCCESS)
        );

        List<UserActionEntity> userActions = List.of(
                UserActionEntityMother.of("userActionId1"),
                UserActionEntityMother.of("userActionId2"),
                UserActionEntityMother.of(userActionId, messageDefinitionsArray ) // The one we want to find
        );

        UserActionsEntity defaultUserActionsEntity = UserActionsEntityMother.of(userActions); // spec and actions

        // define mocks
        BitbucketPathAt bitbucketIdUserActionsPathAt = mock(BitbucketPathAt.class);

        when(catalogServiceAdapter.bitbucketPathAtFromId(bitbucketId)).thenReturn(bitbucketIdUserActionsPathAt);
        when(catalogServiceAdapter.getCatalogEntity(bitbucketIdUserActionsPathAt, UserActionsEntity.class)).thenReturn(Optional.of(defaultUserActionsEntity));
    }

    private void buildAndInitializeMocksForCustomUserActionsEntity(String catalogItemId, String userActionId, String messageDefinitionId, String messageDefinitionMessage) throws InvalidIdException {
        // Initialize data
        UserActionEntityMessageDefinition[] messageDefinitionsArray = UserActionEntityMessageDefinitionMother.ofArray(
                UserActionEntityMessageDefinitionMother.of(messageDefinitionId, UserActionEntityMessageType.SUCCESS, messageDefinitionMessage)
        );

        List<CatalogItemEntityUserAction> userActions = List.of(
                CatalogItemEntityUserActionMother.of("customUserActionId1"),
                CatalogItemEntityUserActionMother.of("customUserActionId2"),
                CatalogItemEntityUserActionMother.of(userActionId, messageDefinitionsArray) // The one we want to find
        );

        buildAndInitializeMocksForCustomUserActionsEntity(catalogItemId, userActions);

    }

    private void buildAndInitializeMocksForCustomUserActionsEntityWithNoMessageDefinitionId(String catalogItemId) throws InvalidIdException {
        // Initialize data
        List<CatalogItemEntityUserAction> userActions = List.of(
                CatalogItemEntityUserActionMother.of("customUserActionId1"),
                CatalogItemEntityUserActionMother.of("customUserActionId2")
        );

        buildAndInitializeMocksForCustomUserActionsEntity(catalogItemId, userActions);

    }

    private void buildAndInitializeMocksForCustomUserActionsEntity(String catalogItemId, List<CatalogItemEntityUserAction> userActions) throws InvalidIdException {
        // Initialize data
        CatalogItemEntity customUserActionsEntity = CatalogItemEntityMother.of(userActions.toArray(new CatalogItemEntityUserAction[0])); // spec and actions

        // define mocks
        BitbucketPathAt catalogItemIdUserActionsPathAt = mock(BitbucketPathAt.class);


        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId))
                .thenReturn(catalogItemIdUserActionsPathAt);
        when(catalogServiceAdapter.getCatalogEntity(catalogItemIdUserActionsPathAt, CatalogItemEntity.class))
                .thenReturn(Optional.of(customUserActionsEntity));

    }
}
