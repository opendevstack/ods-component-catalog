package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.ProjectComponentsMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.cache.ProjectComponentsCacheService;
import org.opendevstack.component_catalog.server.services.exceptions.ComponentAlreadyExistsException;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.UnableToDeserializeEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsServiceTest {

    @Mock
    private BitbucketService bitbucketService;

    @Mock
    private ProjectComponentsService projectComponentsService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ObjectWriter objectWriter;

    @Mock
    private BitbucketPathAt.BitbucketPathAtBuilder bitbucketPathAtBuilder;

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private ProjectComponentsCacheService projectComponentsCacheService;

    private ProvisionerActionsConfiguration provisionerActionsConfiguration;

    // helper
    private ProjectComponentRequest request(String componentId,
                                            String catalogItemId,
                                            Status status,
                                            String url,
                                            String workflowJobId,
                                            String createdAt,
                                            String updatedAt,
                                            List<Parameter> params) {
        return ProjectComponentRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .status(status)
                .componentUrl(url)
                .workflowJobId(workflowJobId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .parameters(params)
                .build();
    }

    @BeforeEach
    void setUp() {
        provisionerActionsConfiguration = new  ProvisionerActionsConfiguration();
        populateProvisionerActionsConfiguration();

        provisionerActionsService = new ProvisionerActionsService(bitbucketService, objectMapper,
                projectComponentsService, provisionerActionsConfiguration, projectComponentsCacheService);
    }

    @Test
    void givenAProvisionersObject_andCreatingStatus_whenValidate_andComponentIdExists_thenThrowException() {
        // given
        var componentId = "componentId";
        var status = Status.CREATING;

        ProjectComponents projectComponents = ProjectComponentsMother.of();


        // when
        var exception = assertThrows(ComponentAlreadyExistsException.class, () ->
                provisionerActionsService.validate(projectComponents, componentId, status)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("Component with id 'componentId' already exists in the project components.");
    }

    @Test
    void givenAProvisionersObject_andCreatingStatus_whenUpdateComponentProvisioningStatus_thenBitbucketFileIsUpdated() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status  = Status.CREATING;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "catalogUrl";
        var createdAt = "created";
        var updatedAt = "updated";

        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "sourceCommitId";

        var projectComponents = new ProjectComponents();
        var updatedProjectComponents = ProjectComponentsMother.of();

        var parameters = List.of(Parameter.builder().name("parameterName").values(List.of("parameterValue")).build());

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));

        prepareMocksForGetNonExistingProjectComponents(pathAt, projectComponents);
        when(projectComponentsService.addNewComponent(
                eq(projectComponents),
                any(ProjectComponentRequest.class)
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave();

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                projectKey,
                request(componentId, catalogItemId, status, componentUrl, null, createdAt, updatedAt, parameters)
        );

        // then
        verify(bitbucketService).pushFile(
                pathAt,
                sourceCommitId,
                serializedUpdatedProjectComponents
        );
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = { "CREATED", "FAILED", "DELETING", "UNKNOWN" })
    void givenAProvisionersObject_andSelectedStatuses_whenUpdateComponentProvisioningStatus_thenBitbucketFileIsUpdated(
            Status status
    ) throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "catalogUrl";
        var createdAt = "created";
        var updatedAt = "updated";

        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "sourceCommitId";

        var projectComponents = ProjectComponentsMother.of();
        var updatedProjectComponents = ProjectComponentsMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));

        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);
        when(projectComponentsService.updateExistingComponent(
                eq(projectComponents),
                any(ProjectComponentRequest.class)
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave();

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                projectKey,
                request(componentId, catalogItemId, status, componentUrl, null, createdAt, updatedAt, List.of())
        );

        // then
        verify(bitbucketService).pushFile(
                pathAt,
                sourceCommitId,
                serializedUpdatedProjectComponents
        );
    }

    @Test
    void givenASetOfProjectComponents_andACatalogItemId_andCatalogItemIdInComponents_whenIsProvisioned_thenReturnTrue() throws InvalidEntityException {
        // given
        var rawId = "my-item";
        var rawIdWithBranch = "my-item?at=refs/heads/main";

        var encodedId = Base64.getEncoder().encodeToString(rawId.getBytes());
        var encodedIdWithBranch = Base64.getEncoder().encodeToString(rawIdWithBranch.getBytes());

        var matchingComponent = new ProjectComponent();
        matchingComponent.setCatalogItemId(encodedId);

        var dummy1 = new ProjectComponent();
        dummy1.setCatalogItemId(Base64.getEncoder().encodeToString("dummy-1".getBytes()));

        var dummy2 = new ProjectComponent();
        dummy2.setCatalogItemId(Base64.getEncoder().encodeToString("dummy-2".getBytes()));

        var components = new HashMap<String, ProjectComponent>();
        components.put("c1", dummy1);
        components.put("c2", matchingComponent); // only this one matches
        components.put("c3", dummy2);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(components);

        when(projectComponentsService.getRepoPathFromCatalogItemId(anyString())).thenCallRealMethod();

        // when
        var result = provisionerActionsService.isProvisioned(projectComponents, encodedIdWithBranch);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenASetOfProjectComponents_andACatalogItemId_andCatalogItemIdNotInComponents_whenIsProvisioned_thenReturnFalse() throws InvalidEntityException {
        // given
        var rawId = "my-item";
        var encodedId = Base64.getEncoder().encodeToString(rawId.getBytes());

        var dummy1 = new ProjectComponent();
        dummy1.setCatalogItemId(Base64.getEncoder().encodeToString("dummy-1".getBytes()));

        var dummy2 = new ProjectComponent();
        dummy2.setCatalogItemId(Base64.getEncoder().encodeToString("dummy-2".getBytes()));

        var components = new HashMap<String, ProjectComponent>();
        components.put("c1", dummy1);
        components.put("c2", dummy2);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(components);

        when(projectComponentsService.getRepoPathFromCatalogItemId(anyString())).thenCallRealMethod();

        // when
        var result = provisionerActionsService.isProvisioned(projectComponents, encodedId);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenProjectComponentsWithNullCatalogItemId_whenIsProvisioned_thenReturnFalse() throws InvalidEntityException {
        // given
        var component = new ProjectComponent();
        component.setCatalogItemId("null");

        var components = new HashMap<String, ProjectComponent>();
        components.put("c1", component);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(components);

        when(projectComponentsService.getRepoPathFromCatalogItemId(anyString())).thenCallRealMethod();

        // when
        var result = provisionerActionsService.isProvisioned(projectComponents, Base64.getEncoder().encodeToString("my-item".getBytes()));

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenProjectKey_whenIsCatalogItemAlreadyProvisionedInProject_thenDelegateAndReturnTrue() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var catalogItemId = Base64.getEncoder().encodeToString("my-item".getBytes());

        var pathAt = BitbucketPathAtMother.of();
        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(Map.of(componentId, new ProjectComponent()));
        projectComponents.getComponents().get(componentId).setCatalogItemId(catalogItemId);

        prepareMocksForGetBitbucketPathAt(pathAt);
        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(anyString())).thenCallRealMethod();

        // when
        var result = provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenInvalidProjectComponentsJson_whenGetProjectComponents_thenThrowUnableToDeserializeEntityException() throws JsonProcessingException {
        // given
        var pathAt = BitbucketPathAtMother.of();
        var serializedProjectComponents = "{ invalid-json";
        var bitbucketFileContent = Pair.of(MediaType.APPLICATION_JSON, serializedProjectComponents);

        when(bitbucketService.getTextFileContents(pathAt)).thenReturn(Optional.of(bitbucketFileContent));
        when(objectMapper.readValue(serializedProjectComponents, ProjectComponents.class))
                .thenThrow(new JsonProcessingException("boom") {});

        // when
        var exception = assertThrows(UnableToDeserializeEntityException.class, () ->
                provisionerActionsService.getProjectComponents(pathAt)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("Unable to deserialize ProjectComponents.");
    }

    @Test
    void givenProjectKeyAndProjectFiles_whenGetAllProjectComponentsProjectKeys_thenOnlyJsonFilesAreReturned() {
        // given
        var pathAt = BitbucketPathAtMother.of();
        var expectedFilenames = List.of("PROJECT_A_KEY.json", "PROJECT_B_KEY.txt", "PROJECT_C_KEY.json");

        when(bitbucketService.pathAtBuilder()).thenReturn(bitbucketPathAtBuilder);

        when(bitbucketPathAtBuilder.projectKey(provisionerActionsConfiguration.getProjectKey()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.repoSlug(provisionerActionsConfiguration.getRepositorySlug()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.at(provisionerActionsConfiguration.getBranchName()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.subPath(provisionerActionsConfiguration.getProjectsPath()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.build()).thenReturn(pathAt);

        when(bitbucketService.getFilenamesFromRemoteDirectory(pathAt))
                .thenReturn(expectedFilenames);

        // when
        var result = provisionerActionsService.getAllProjectComponentsProjectKeys();

        // then
        assertThat(result).containsExactly("PROJECT_A_KEY", "PROJECT_C_KEY");
    }

    @Test
    void givenAtomicSaveAndBitbucketRejectsNoChanges_whenSaveProjectComponents_thenExceptionIsIgnored() throws JsonProcessingException {
        // given
        var projectComponentPathAt = BitbucketPathAtMother.of();
        var projectComponentHistoryPathAt = BitbucketPathAtMother.of();
        var projectComponentSourceCommitId = "sourceCommitId";
        var projectComponentsHistorySourceCommitId = "historySourceCommitId";
        var updatedProjectComponents = ProjectComponentsMother.of();
        var updatedProjectComponentsHistory = ProjectComponentsMother.of();
        var requester = "test.user";
        var httpClientErrorException = new HttpClientErrorException(HttpStatus.CONFLICT, """
                {"errors":[{"context":null,"message":"The content provided is the same as what already exists. No change was committed.","exceptionName":"com.atlassian.bitbucket.content.FileContentUnmodifiedException"}]}
                """);

        doThrow(httpClientErrorException).when(bitbucketService).pushFilesAtomically(anyList(), anyString(), anyString());
        prepareMocksForSave();

        // when
        assertDoesNotThrow(() -> provisionerActionsService.saveProjectComponents(
                projectComponentPathAt,
                projectComponentHistoryPathAt,
                projectComponentSourceCommitId,
                projectComponentsHistorySourceCommitId,
                updatedProjectComponents,
                updatedProjectComponentsHistory,
                requester
        ));

        // then
        verify(bitbucketService).pushFilesAtomically(anyList(), anyString(), anyString());
    }

    @Test
    void givenAProjectKey_andAComponentId_whenDeleteComponentProvisioningStatus_thenBitbucketServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var requester = "test.user";

        var pathAtBuilder = mock(BitbucketPathAt.BitbucketPathAtBuilder.class);
        var pathAt = mock(BitbucketPathAt.class);
        var sourceCommitId = "sourceCommitId";

        var projectComponents = ProjectComponentsMother.of();
        var projectComponentsWithoutComponentId = ProjectComponentsMother.of();

        when(bitbucketService.pathAtBuilder()).thenReturn(pathAtBuilder);
        when(pathAtBuilder.projectKey(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.repoSlug(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.subPath(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.at(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.build()).thenReturn(pathAt);

        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));
        when(projectComponentsService.deleteComponent(projectComponents, componentId)).thenReturn(projectComponentsWithoutComponentId);

        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);
        prepareMocksForSave();

        // when
        provisionerActionsService.deleteComponentProvisioningStatus(projectKey, componentId, requester);

        // then
        verify(bitbucketService).pushFilesAtomically(argThat(fileUpdates ->
                        fileUpdates != null && fileUpdates.size() == 2),
                eq("Delete component and archive provisioning history"),
                eq("Deletion requested by " + requester));
    }

    @Test
    void givenAProjectKey_andAComponentId_whenDeleteComponentProvisioningStatus_andNoProjectComponentsForProjectKey_thenExceptionIsThrown() {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var requester = "test.user";

        var pathAtBuilder = mock(BitbucketPathAt.BitbucketPathAtBuilder.class);
        var pathAt = mock(BitbucketPathAt.class);

        var projectComponents = ProjectComponentsMother.of();

        when(bitbucketService.pathAtBuilder()).thenReturn(pathAtBuilder);
        when(pathAtBuilder.projectKey(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.repoSlug(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.subPath(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.at(any())).thenReturn(pathAtBuilder);
        when(pathAtBuilder.build()).thenReturn(pathAt);

        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.empty());

        assertThat(projectComponents.getComponents()).containsKey(componentId);

        // when
        var exception = assertThrows(RestEntityNotFoundException.class, () ->
                provisionerActionsService.deleteComponentProvisioningStatus(projectKey, componentId, requester)
        );

        // then
        assertThat(exception.getMessage()).startsWith("No component provisioning status for pathAt:");
        verify(bitbucketService, times(0)).pushFile(any(), any(), anyString());
    }

    @Test
    void givenAProjectComponents_whenSaveProjectComponents_andBitbucketRejectAsNoUpdates_ThenExceptionIsIgnored() throws JsonProcessingException {
        // given
        var pathAt = mock(BitbucketPathAt.class);
        var sourceCommitId = "sourceCommitId";
        var updatedProjectComponents = ProjectComponentsMother.of();
        var httpClientErrorException = new HttpClientErrorException(HttpStatus.CONFLICT, "\"{\"errors\":" +
                "[{\"context\":null,\"message\":\"The content provided is the same as what already exists. No change was committed.\"" +
                ",\"exceptionName\":\"com.atlassian.bitbucket.content.FileContentUnmodifiedException\"}]}\"");

        doThrow(httpClientErrorException).when(bitbucketService).pushFile(eq(pathAt), eq(sourceCommitId), anyString());
        prepareMocksForSave();

        // when
        assertDoesNotThrow(() -> provisionerActionsService.saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents));

        // then
        verify(bitbucketService).pushFile(eq(pathAt), eq(sourceCommitId), anyString());
    }

    @Test
    void givenAProjectComponents_whenSaveProjectComponents_andBitbucketReject_ThenExceptionIsThrown() throws JsonProcessingException {
        // given
        var pathAt = mock(BitbucketPathAt.class);
        var sourceCommitId = "sourceCommitId";
        var updatedProjectComponents = ProjectComponentsMother.of();
        var httpClientErrorException = new HttpClientErrorException(HttpStatus.CONFLICT, "Client Error");

        doThrow(httpClientErrorException).when(bitbucketService).pushFile(eq(pathAt), eq(sourceCommitId), anyString());
        prepareMocksForSave();

        // when
        var exception = assertThrows(HttpClientErrorException.class, () ->
                provisionerActionsService.saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents)
        );

        // then
        assertThat(exception).isEqualTo(httpClientErrorException);
    }

    @Test
    void givenExistingProjectComponents_whenUpdatePartiallyComponentProvisioningStatus_thenBitbucketFileIsUpdated()
            throws JsonProcessingException {

        // given
        var projectKey = "projectKey";
        var status = Status.FAILED; // any status is allowed
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var parameterList = List.of(Parameter.builder().name("paramName").values(List.of("paramValue")).build());
        var workflowJobId = "workflowJobId";
        var createdAt = "created";
        var updatedAt = "updated";

        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "sourceCommitId";

        var projectComponents = ProjectComponentsMother.of();
        var updatedProjectComponents = ProjectComponentsMother.of();

        // get path
        prepareMocksForGetBitbucketPathAt(pathAt);

        // last commit exists
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));

        // project components exist
        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        // partial update call
        when(projectComponentsService.updatePartiallyExistingComponent(
                eq(projectComponents),
                any(ProjectComponentRequest.class)
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave();

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                projectKey,
                request(componentId, catalogItemId, status, componentUrl, workflowJobId, createdAt, updatedAt, parameterList)
        );

        // then
        verify(bitbucketService).pushFile(
                pathAt,
                sourceCommitId,
                serializedUpdatedProjectComponents
        );
    }

    @Test
    void givenNoProjectComponents_whenUpdatePartiallyComponentProvisioningStatus_thenThrowException(){

        // given
        var projectKey = "projectKey";
        var status = Status.FAILED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var workflowJobId = "workflowJobId";
        var createdAt = "created";
        var updatedAt = "updated";

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);

        // Simulate null projectComponents
        when(bitbucketService.getTextFileContents(pathAt)).thenReturn(Optional.empty());
        when(projectComponentsService.createNewComponent()).thenReturn(null);

        var request = request(componentId, catalogItemId, status, componentUrl, workflowJobId, createdAt, updatedAt, List.of());

        // when
        var exception = assertThrows(ElementNotFoundException.class, () -> provisionerActionsService.updatePartiallyComponentProvisioningStatus(projectKey, request));

        // then
        assertThat(exception.getMessage())
                .isEqualTo("In a partial update, the projectComponent should exist.");
    }

    @Test
    void givenNewFile_whenUpdatePartiallyComponentProvisioningStatus_thenPushFileIsCalledWithNullCommit()
            throws JsonProcessingException {

        // given
        var projectKey = "projectKey";
        var status = Status.UNKNOWN;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "url";
        var workflowJobId = "workflowJobId";
        var createdAt = "created";
        var updatedAt = "updated";

        var pathAt = BitbucketPathAtMother.of();

        var projectComponents = ProjectComponentsMother.of();
        var updatedProjectComponents = ProjectComponentsMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);

        // no last commit
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.empty());

        // components exist
        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        when(projectComponentsService.updatePartiallyExistingComponent(
                eq(projectComponents),
                any(ProjectComponentRequest.class)
        )).thenReturn(updatedProjectComponents);

        var serialized = prepareMocksForSave();

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                projectKey,
                request(componentId, catalogItemId, status, componentUrl, workflowJobId, createdAt, updatedAt, List.of())
        );

        // then
        verify(bitbucketService).pushFile(pathAt, null, serialized);
    }
    @Test
    void givenExistingComponent_whenUpdate_thenCreatedAtIsPreserved() throws Exception {
        // given
        var componentId = "componentId";

        var existing = new ProjectComponent();
        existing.setCreatedAt("originalCreatedAt");

        var components = new HashMap<String, ProjectComponent>();
        components.put(componentId, existing);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(components);

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of("commit"));
        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        when(projectComponentsService.updateExistingComponent(
                any(), any(ProjectComponentRequest.class)
        )).thenReturn(ProjectComponentsMother.of());

        prepareMocksForSave();

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                "projectKey",
                request(componentId, "catalogItemId", Status.CREATED, "url", null, "created", "updated", List.of())
        );

        verify(projectComponentsService).updateExistingComponent(
                eq(projectComponents),
                argThat(req -> "originalCreatedAt".equals(req.getCreatedAt()))
        );
    }

    @Test
    void givenExistingComponent_whenUpdate_thenUpdatedAtIsGenerated() throws Exception {
        // given
        var componentId = "componentId";

        var existing = new ProjectComponent();
        existing.setCreatedAt("originalCreatedAt");

        var components = new HashMap<String, ProjectComponent>();
        components.put(componentId, existing);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(components);

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of("commit"));
        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        when(projectComponentsService.updateExistingComponent(
                any(), any(ProjectComponentRequest.class)
        )).thenReturn(ProjectComponentsMother.of());

        prepareMocksForSave();

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                "projectKey",
                request(componentId, "catalogItemId", Status.CREATED, "url", null, "created", "updated", List.of())
        );

        // then
        verify(projectComponentsService).updateExistingComponent(
                any(),
                argThat(req -> {
                    try {
                        return Long.parseLong(req.getUpdatedAt()) > 0;
                    } catch (Exception e) {
                        return false;
                    }
                })
        );
    }

    @Test
    void givenExistingComponent_whenPartialUpdate_thenUpdatedAtIsModified() throws Exception {
        // given
        var componentId = "componentId";

        var component = new ProjectComponent();
        component.setUpdatedAt("123");

        var map = new HashMap<String, ProjectComponent>();
        map.put(componentId, component);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(map);

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of("commit"));

        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        when(projectComponentsService.updatePartiallyExistingComponent(
                any(),
                any(ProjectComponentRequest.class)
        )).thenReturn(ProjectComponents.builder().components(
                Map.of(componentId, component)
        ).build());

        prepareMocksForSave();

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                "projectKey",
                request(componentId, "catalogItemId", Status.FAILED, "url", "jobId", "created", "updated", List.of())
        );

        // then
        assertThat(Long.parseLong(component.getUpdatedAt())).isGreaterThan(0);
    }

    @Test
    void givenExistingComponent_whenPartialUpdate_thenCreatedAtIsNotModified() throws Exception {
        // given
        var componentId = "componentId";

        var originalCreatedAt = "123";
        var anotherCreatedAt = "wrongNumber!";
        var component = new ProjectComponent();
        component.setCreatedAt(originalCreatedAt);

        var map = new HashMap<String, ProjectComponent>();
        map.put(componentId, component);

        var projectComponents = new ProjectComponents();
        projectComponents.setComponents(map);

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of("commit"));

        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);

        when(projectComponentsService.updatePartiallyExistingComponent(
                any(),
                any(ProjectComponentRequest.class)
        )).thenReturn(ProjectComponentsMother.of());

        prepareMocksForSave();

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                "projectKey",
                request(componentId, "catalogItemId", Status.FAILED, "url", "jobId", anotherCreatedAt, "updated", List.of())
        );

        // then
        assertThat(component.getCreatedAt()).isEqualTo(originalCreatedAt);
    }

    @Test
    void givenConfiguration_whenListAllProjectsJsons_thenBitbucketServiceIsCalledAndResultReturned() {
        // given
        var pathAt = BitbucketPathAtMother.of();
        var expectedFilenames = List.of("PROJECT_A_KEY.json", "PROJECT_B_KEY.json");

        when(bitbucketService.pathAtBuilder()).thenReturn(bitbucketPathAtBuilder);

        when(bitbucketPathAtBuilder.projectKey(provisionerActionsConfiguration.getProjectKey()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.repoSlug(provisionerActionsConfiguration.getRepositorySlug()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.at(provisionerActionsConfiguration.getBranchName()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.subPath(provisionerActionsConfiguration.getProjectsPath()))
                .thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.build()).thenReturn(pathAt);

        when(bitbucketService.getFilenamesFromRemoteDirectory(pathAt))
                .thenReturn(expectedFilenames);

        // when
        var result = provisionerActionsService.listAllProjectsJsons();

        // then
        assertThat(result).isEqualTo(expectedFilenames);

        verify(bitbucketService).getFilenamesFromRemoteDirectory(pathAt);
    }

    private String prepareMocksForSave() throws JsonProcessingException {
        var serializedUpdatedProjectComponents = "{ serialized-updated-json: true }";

        when(objectMapper.writerWithDefaultPrettyPrinter()).thenReturn(objectWriter);
        when(objectWriter.writeValueAsString(any())).thenReturn(serializedUpdatedProjectComponents);

        return serializedUpdatedProjectComponents;
    }

    private void prepareMocksForGetNonExistingProjectComponents(BitbucketPathAt bitbucketPathAt, ProjectComponents projectComponents){
        when(bitbucketService.getTextFileContents(bitbucketPathAt)).thenReturn(Optional.empty());
        when(projectComponentsService.createNewComponent()).thenReturn(projectComponents);
    }

    private void prepareMocksForGetExistingProjectComponents(BitbucketPathAt bitbucketPathAt, ProjectComponents projectComponents) throws JsonProcessingException {
        var serializedProjectComponents = "{ serialized-json: true }";
        var bitbucketFileContent = Pair.of(MediaType.APPLICATION_JSON, serializedProjectComponents);

        when(bitbucketService.getTextFileContents(bitbucketPathAt)).thenReturn(Optional.of(bitbucketFileContent));
        when(objectMapper.readValue(serializedProjectComponents, ProjectComponents.class)).thenReturn(projectComponents);
    }

    private void populateProvisionerActionsConfiguration() {
        var projectKey = "configuredProjectKey";
        var repoSlug =  "repoSlug";
        var subPath = "subPath";
        var projectHistorySubPath = "projectHistorySubPath";
        var subPathToken = "subPathToken";
        var branchName = "branchName";

        provisionerActionsConfiguration.setProjectKey(projectKey);
        provisionerActionsConfiguration.setRepositorySlug(repoSlug);
        provisionerActionsConfiguration.setSubPath(subPath);
        provisionerActionsConfiguration.setProjectHistorySubPath(projectHistorySubPath);
        provisionerActionsConfiguration.setSubPathToken(subPathToken);
        provisionerActionsConfiguration.setBranchName(branchName);
    }

    private void prepareMocksForGetBitbucketPathAt(BitbucketPathAt bitbucketPathAt) {
        when(bitbucketService.pathAtBuilder()).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.projectKey(provisionerActionsConfiguration.getProjectKey())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.repoSlug(provisionerActionsConfiguration.getRepositorySlug())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.subPath(provisionerActionsConfiguration.getSubPath())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.at(provisionerActionsConfiguration.getBranchName())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.build()).thenReturn(bitbucketPathAt);
    }
}