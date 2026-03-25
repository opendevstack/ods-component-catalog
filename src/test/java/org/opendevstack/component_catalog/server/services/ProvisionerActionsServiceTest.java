package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.ProjectComponentsMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.ComponentAlreadyExistsException;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    private ProvisionerActionsConfiguration provisionerActionsConfiguration;

    private ProvisionerActionsService provisionerActionsService;

    @BeforeEach
    void setUp() {
        provisionerActionsConfiguration = new  ProvisionerActionsConfiguration();
        populateProvisionerActionsConfiguration();

        provisionerActionsService = new ProvisionerActionsService(bitbucketService, objectMapper, projectComponentsService, provisionerActionsConfiguration);
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
    void givenAProvisionersObject_andCreatedStatus_whenValidate_andNoCreatingStatus_thenThrowException() {
        // given
        var componentId = "componentId";
        var status = Status.CREATED;

        ProjectComponents projectComponents = ProjectComponentsMother.of(Status.DELETING);


        // when
        var exception = assertThrows(InvalidComponentStateException.class, () ->
                provisionerActionsService.validate(projectComponents, componentId, status)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("Component with id 'componentId' is not in Creating state.");
    }

    @Test
    void givenAProvisionersObject_andCreatingStatus_whenUpdateComponentProvisioningStatus_thenBitbucketFileIsUpdated() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status  = Status.CREATING;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "catalogUrl";

        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "sourceCommitId";

        var projectComponents = new ProjectComponents();
        var updatedProjectComponents = ProjectComponentsMother.of();

        var parameterParam = Pair.of("parameterName", "parameterValue");

        var parameter = Parameter.builder().name(parameterParam.getLeft()).value(parameterParam.getValue()).build();
        var parameters = List.of(parameter);

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));

        prepareMocksForGetNonExistingProjectComponents(pathAt, projectComponents);
        when(projectComponentsService.addNewComponent(
                projectComponents,
                componentId,
                catalogItemId,
                status,
                componentUrl,
                parameters
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave(updatedProjectComponents);

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                projectKey,
                status,
                componentId,
                catalogItemId,
                componentUrl,
                List.of(parameterParam)
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

        var pathAt = BitbucketPathAtMother.of();
        var sourceCommitId = "sourceCommitId";

        var projectComponents = ProjectComponentsMother.of();
        var updatedProjectComponents = ProjectComponentsMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);
        when(bitbucketService.getLastCommit(pathAt)).thenReturn(Optional.of(sourceCommitId));

        prepareMocksForGetExistingProjectComponents(pathAt, projectComponents);
        when(projectComponentsService.updateExistingComponent(
                projectComponents,
                componentId,
                catalogItemId,
                status,
                componentUrl,
                Collections.emptyList()
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave(updatedProjectComponents);

        // when
        provisionerActionsService.updateComponentProvisioningStatus(
                projectKey,
                status,
                componentId,
                catalogItemId,
                componentUrl,
                Collections.emptyList()
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
    void givenAProjectKey_andAComponentId_whenDeleteComponentProvisioningStatus_thenBitbucketServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

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
        var serializedProjectComponentsWithoutComponentId = prepareMocksForSave(projectComponentsWithoutComponentId);

        // when
        provisionerActionsService.deleteComponentProvisioningStatus(projectKey, componentId);

        // then
        verify(bitbucketService).pushFile(pathAt, sourceCommitId, serializedProjectComponentsWithoutComponentId);
    }

    @Test
    void givenAProjectKey_andAComponentId_whenDeleteComponentProvisioningStatus_andNoProjectComponentsForProjectKey_thenExceptionIsThrown() {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

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
                provisionerActionsService.deleteComponentProvisioningStatus(projectKey, componentId)
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
        prepareMocksForSave(updatedProjectComponents);

        // when
        provisionerActionsService.saveProjectComponents(pathAt, sourceCommitId, updatedProjectComponents);

        // then
        // no exception is thrown
    }

    @Test
    void givenAProjectComponents_whenSaveProjectComponents_andBitbucketReject_ThenExceptionIsThrown() throws JsonProcessingException {
        // given
        var pathAt = mock(BitbucketPathAt.class);
        var sourceCommitId = "sourceCommitId";
        var updatedProjectComponents = ProjectComponentsMother.of();
        var httpClientErrorException = new HttpClientErrorException(HttpStatus.CONFLICT, "Client Error");

        doThrow(httpClientErrorException).when(bitbucketService).pushFile(eq(pathAt), eq(sourceCommitId), anyString());
        prepareMocksForSave(updatedProjectComponents);

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
        var parameterPair = Pair.of("paramName", "paramValue");

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
                projectComponents,
                componentId,
                catalogItemId,
                status,
                componentUrl,
                List.of(Parameter.builder().name("paramName").value("paramValue").build())
        )).thenReturn(updatedProjectComponents);

        var serializedUpdatedProjectComponents = prepareMocksForSave(updatedProjectComponents);

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                projectKey,
                status,
                componentId,
                catalogItemId,
                componentUrl,
                List.of(parameterPair)
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

        var pathAt = BitbucketPathAtMother.of();

        prepareMocksForGetBitbucketPathAt(pathAt);

        // Simulate null projectComponents
        when(bitbucketService.getTextFileContents(pathAt)).thenReturn(Optional.empty());
        when(projectComponentsService.createNewComponent()).thenReturn(null);

        // when
        var exception = assertThrows(ElementNotFoundException.class, () ->
                provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                        projectKey,
                        status,
                        componentId,
                        catalogItemId,
                        componentUrl,
                        List.of()
                )
        );

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
                eq(componentId),
                eq(catalogItemId),
                eq(status),
                eq(componentUrl),
                any()
        )).thenReturn(updatedProjectComponents);

        var serialized = prepareMocksForSave(updatedProjectComponents);

        // when
        provisionerActionsService.updatePartiallyComponentProvisioningStatus(
                projectKey,
                status,
                componentId,
                catalogItemId,
                componentUrl,
                List.of()
        );

        // then
        verify(bitbucketService).pushFile(pathAt, null, serialized);
    }

    private String prepareMocksForSave(ProjectComponents updatedProjectComponents) throws JsonProcessingException {
        var serializedUpdatedProjectComponents = "{ serialized-updated-json: true }";

        when(objectMapper.writerWithDefaultPrettyPrinter()).thenReturn(objectWriter);
        when(objectWriter.writeValueAsString(updatedProjectComponents)).thenReturn(serializedUpdatedProjectComponents);

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
        var subPathToken = "subPathToken";
        var branchName = "branchName";

        provisionerActionsConfiguration.setProjectKey(projectKey);
        provisionerActionsConfiguration.setRepositorySlug(repoSlug);
        provisionerActionsConfiguration.setSubPath(subPath);
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
