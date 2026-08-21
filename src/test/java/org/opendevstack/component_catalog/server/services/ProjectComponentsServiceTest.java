package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectComponentsServiceTest {

    private final ProjectComponentsService service = new ProjectComponentsService();

    private String base64(String val) {
        return Base64.getUrlEncoder().encodeToString(val.getBytes(StandardCharsets.UTF_8));
    }

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

    @Test
    void givenNothing_whenCreateNewComponent_thenReturnEmptyProjectComponents() {
        //given //when
        ProjectComponents result = service.createNewComponent();

        //then
        assertThat(result).isNotNull();
        assertThat(result.getComponents()).isNull();
    }

    @Test
    void givenValidInput_whenAddNewComponent_thenComponentAdded() {
        //given
        ProjectComponents pc = new ProjectComponents();
        pc.setComponents(new HashMap<>());

        String encoded = base64("repo/path?at=refs/heads/main");

        //when
        ProjectComponents updated = service.addNewComponent(
                pc,
                request("comp1", encoded, Status.CREATING, "url", null, "created", "updated", Collections.emptyList())
        );

        //then
        assertThat(updated.getComponents()).containsKey("comp1");

        ProjectComponent added = updated.getComponents().get("comp1");

        assertThat(added.getCatalogItemId()).isEqualTo(base64("repo/path"));
        assertThat(added.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/main"));
        assertThat(added.getStatus()).isEqualTo(Status.CREATING);
        assertThat(added.getComponentUrl()).isEqualTo("url");
        assertThat(added.getCreatedAt()).isEqualTo("created");
        assertThat(added.getUpdatedAt()).isEqualTo("updated");
    }

    @Test
    void givenWorkflowJobIds_whenAddNewComponent_thenWorkflowFieldsAreMapped() {
        //given
        ProjectComponents pc = new ProjectComponents();
        pc.setComponents(new HashMap<>());

        String encoded = base64("repo/path?at=refs/heads/main");

        ProjectComponentRequest request = ProjectComponentRequest.builder()
                .componentId("comp1")
                .catalogItemId(encoded)
                .status(Status.CREATING)
                .componentUrl("url")
                .workflowJobId("wf-123")
                .deletionWorkflowJobId("del-wf-456")
                .createdAt("created")
                .updatedAt("updated")
                .parameters(Collections.emptyList())
                .build();

        //when
        ProjectComponents updated = service.addNewComponent(pc, request);

        //then
        ProjectComponent added = updated.getComponents().get("comp1");

        assertThat(added.getWorkflowJobId()).isEqualTo("wf-123");
        assertThat(added.getDeletionWorkflowJobId()).isEqualTo("del-wf-456");
    }

    @Test
    void givenNullComponentsAndCatalogWithoutBranch_whenAddNewComponent_thenMapCreatedAndMasterRefUsed() {
        //given
        ProjectComponents pc = new ProjectComponents();
        String encoded = base64("repo/path");

        //when
        ProjectComponents updated = service.addNewComponent(
                pc,
                request("comp1", encoded, Status.CREATING, "url", null, "created", "updated", Collections.emptyList())
        );

        //then
        assertThat(updated.getComponents()).containsKey("comp1");
        assertThat(updated.getComponents().get("comp1").getCatalogItemRef())
                .isEqualTo(base64(ProjectComponentsService.REFS_HEADS_MASTER));
    }

    @Test
    void givenExistingComponent_whenUpdateExistingComponent_thenUpdatedCorrectly() {
        //given
        String encodedFull = base64("repo/z?at=refs/heads/main");
        String encodedRepo = base64("repo/z");
        var parameter = new Parameter("param1", List.of("value1"));
        var parameters = List.of(parameter);

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .catalogItemRef(null)
                .componentUrl("oldUrl")
                .status(Status.CREATING)
                .createdAt("oldCreated")
                .updatedAt("oldUpdated")
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updateExistingComponent(
                pc,
                request("comp1", encodedFull, Status.CREATED, "newUrl", null, "created", "updated", parameters)
        );

        //then
        ProjectComponent updatedComp = updated.getComponents().get("comp1");

        assertThat(updatedComp.getStatus()).isEqualTo(Status.CREATED);
        assertThat(updatedComp.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/main"));
        assertThat(updatedComp.getComponentUrl()).isEqualTo("newUrl");
        assertThat(updatedComp.getParameters()).containsExactly(parameter);
        assertThat(updatedComp.getCreatedAt()).isEqualTo("created");
        assertThat(updatedComp.getUpdatedAt()).isEqualTo("updated");
    }

    @Test
    void givenWorkflowJobIds_whenUpdateExistingComponent_thenWorkflowFieldsAreMapped() {
        //given
        String encodedFull = base64("repo/z?at=refs/heads/main");
        String encodedRepo = base64("repo/z");

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .catalogItemRef(null)
                .componentUrl("oldUrl")
                .status(Status.CREATING)
                .workflowJobId("old-wf")
                .deletionWorkflowJobId("old-del-wf")
                .createdAt("oldCreated")
                .updatedAt("oldUpdated")
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        ProjectComponentRequest request = ProjectComponentRequest.builder()
                .componentId("comp1")
                .catalogItemId(encodedFull)
                .status(Status.CREATED)
                .componentUrl("newUrl")
                .workflowJobId("new-wf")
                .deletionWorkflowJobId("new-del-wf")
                .createdAt("created")
                .updatedAt("updated")
                .parameters(Collections.emptyList())
                .build();

        //when
        ProjectComponents updated = service.updateExistingComponent(pc, request);

        //then
        ProjectComponent updatedComp = updated.getComponents().get("comp1");

        assertThat(updatedComp.getWorkflowJobId()).isEqualTo("new-wf");
        assertThat(updatedComp.getDeletionWorkflowJobId()).isEqualTo("new-del-wf");
    }

    @Test
    void givenDifferentRepoPath_whenUpdateExistingComponent_thenDoNotUpdateComponent() {
        //given
        String encodedRepoOriginal = base64("repo/original");
        String encodedFullDifferent = base64("repo/other?at=refs/heads/main");

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepoOriginal)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updateExistingComponent(
                pc,
                request(
                        "comp1",
                        encodedFullDifferent,
                        Status.CREATED,
                        "x",
                        null,
                        "created",
                        "updated",
                        Collections.emptyList()
                )
        );

        //then
        assertThat(updated.getComponents().get("comp1").getCatalogItemId())
                .isEqualTo(encodedRepoOriginal); // unchanged
    }

    @Test
    void givenNonExistingComponent_whenUpdateExistingComponent_thenThrow() {
        //given
        ProjectComponents pc = ProjectComponents.builder()
                .components(new HashMap<>())
                .build();

        //when //then
        assertThatThrownBy(() ->
                service.updateExistingComponent(
                        pc,
                                request(
                                        "unknown",
                                        "zzz",
                                        Status.CREATED,
                                        "x",
                                        null,
                                        "created",
                                        "updated",
                                        Collections.emptyList()
                                )
                ))
                .isInstanceOf(InvalidComponentStateException.class);
    }

    @Test
    void givenExistingComponent_whenUpdatePartially_thenUpdatesOnlyFieldsProvided() {
        //given
        String encodedRepo = base64("repo/a");
        String encodedFull = base64("repo/a?at=refs/heads/dev");

        var parameter = new Parameter("param1", List.of("value1"));
        var parameters = List.of(parameter);

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .catalogItemRef(base64("?at=refs/heads/main"))
                .componentUrl("oldUrl")
                .status(Status.CREATING)
                .createdAt("oldCreatedAt")
                .updatedAt("oldUpdatedAt")
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(
                pc,
                request("comp1", encodedFull, Status.CREATED, null, null, "created", "updated", parameters)
        );

        //then
        ProjectComponent result = updated.getComponents().get("comp1");

        assertThat(result.getStatus()).isEqualTo(Status.CREATED);
        assertThat(result.getComponentUrl()).isEqualTo("oldUrl"); // unchanged
        assertThat(result.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/dev"));
        assertThat(result.getCreatedAt()).isEqualTo("created");
        assertThat(result.getUpdatedAt()).isEqualTo("updated");
    }

    @Test
    void givenNonExistingComponent_whenUpdatePartially_thenThrow() {
        //given
        ProjectComponents pc = ProjectComponents.builder()
                .components(new HashMap<>())
                .build();

        //when //then
        assertThatThrownBy(() ->
                service.updatePartiallyExistingComponent(
                        pc,
                                request(
                                        "missing",
                                        "zzz",
                                        Status.CREATED,
                                        "x",
                                        null,
                                        "created",
                                        "updated",
                                        Collections.emptyList()
                                )
                ))
                .isInstanceOf(InvalidComponentStateException.class);
    }

    @Test
    void givenExistingComponent_whenDeleteComponent_thenRemoved() {
        //given
        ProjectComponent comp = new ProjectComponent();
        ProjectComponents pc = ProjectComponents.builder()
                .components(new HashMap<>(Map.of("comp1", comp)))
                .build();

        //when
        ProjectComponents updated = service.deleteComponent(pc, "comp1");

        //then
        assertThat(updated.getComponents()).doesNotContainKey("comp1");
    }

    @Test
    void givenBlankWorkflowJobId_whenUpdatePartially_thenKeepsExistingWorkflowJobId() {
        //given
        String encodedRepo = base64("repo/a");
        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .workflowJobId("existing-job-id")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(
                pc,
                request("comp1", null, Status.CREATED, null, "", null, null, Collections.emptyList())
        );

        //then
        assertThat(updated.getComponents().get("comp1").getWorkflowJobId()).isEqualTo("existing-job-id");
    }

    @Test
    void givenNewWorkflowJobId_whenUpdatePartially_thenUsesNewWorkflowJobId() {
        //given
        String encodedRepo = base64("repo/a");
        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .workflowJobId("old-job-id")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(
                pc,
                request("comp1", null, Status.CREATED, null, "new-job-id", null, null, Collections.emptyList())
        );

        //then
        assertThat(updated.getComponents().get("comp1").getWorkflowJobId()).isEqualTo("new-job-id");
    }

    @Test
    void givenBlankDeletionWorkflowJobId_whenUpdatePartially_thenKeepsExistingDeletionWorkflowJobId() {
        //given
        String encodedRepo = base64("repo/a");
        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .deletionWorkflowJobId("existing-deletion-job-id")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        ProjectComponentRequest request = ProjectComponentRequest.builder()
                .componentId("comp1")
                .status(Status.CREATED)
                .deletionWorkflowJobId("")
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(pc, request);

        //then
        assertThat(updated.getComponents().get("comp1").getDeletionWorkflowJobId()).isEqualTo("existing-deletion-job-id");
    }

    @Test
    void givenNewDeletionWorkflowJobId_whenUpdatePartially_thenUsesNewDeletionWorkflowJobId() {
        //given
        String encodedRepo = base64("repo/a");
        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .deletionWorkflowJobId("old-deletion-job-id")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        ProjectComponentRequest request = ProjectComponentRequest.builder()
                .componentId("comp1")
                .status(Status.CREATED)
                .deletionWorkflowJobId("new-deletion-job-id")
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(pc, request);

        //then
        assertThat(updated.getComponents().get("comp1").getDeletionWorkflowJobId()).isEqualTo("new-deletion-job-id");
    }

    @Test
    void givenValidCatalogItemId_whenGetRepoPath_thenReturnsPathWithoutBranch() {
        //given
        String encoded = base64("repo/x?at=refs/heads/main");

        //when
        String result = service.getRepoPathFromCatalogItemId(encoded);

        //then
        assertThat(result).isEqualTo(base64("repo/x"));
    }

    @Test
    void givenNullCatalogItemId_whenGetRepoPath_thenThrowInvalidEntityException() {
        //given
        String catalogItemId = null;

        //when //then
        assertThatThrownBy(() -> service.getRepoPathFromCatalogItemId(catalogItemId))
                .isInstanceOf(InvalidEntityException.class)
                .hasMessageContaining("Invalid Base64 encoded catalogItemId");
    }

    @Test
    void givenValidCatalogItemId_whenExtractBranchRef_thenReturnEncodedBranch() {
        //given
        String encoded = base64("repo/x?at=refs/heads/feature123");

        //when
        String repoPath = service.getRepoPathFromCatalogItemId(encoded);

        //then
        assertThat(repoPath).isEqualTo(base64("repo/x"));
    }

    @Test
    void givenNullTimestamps_whenUpdatePartially_thenTimestampsAreOverwrittenWithNull() {
        //given
        String encodedRepo = base64("repo/a");

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .createdAt("oldCreated")
                .updatedAt("oldUpdated")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated = service.updatePartiallyExistingComponent(
                pc,
                request("comp1", null, Status.CREATED, null, null, null, null, Collections.emptyList())
        );

        //then
        ProjectComponent result = updated.getComponents().get("comp1");

        assertThat(result.getCreatedAt()).isNull();
        assertThat(result.getUpdatedAt()).isNull();
    }

}