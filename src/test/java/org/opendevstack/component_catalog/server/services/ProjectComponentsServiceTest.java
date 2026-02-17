package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectComponentsServiceTest {

    private final ProjectComponentsService service = new ProjectComponentsService();

    private String base64(String val) {
        return java.util.Base64.getUrlEncoder().encodeToString(val.getBytes(StandardCharsets.UTF_8));
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
        ProjectComponents updated = service.addNewComponent(pc, "comp1", encoded, Status.CREATING, "url");

        //then
        assertThat(updated.getComponents()).containsKey("comp1");

        ProjectComponent added = updated.getComponents().get("comp1");

        assertThat(added.getCatalogItemId()).isEqualTo(base64("repo/path"));
        assertThat(added.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/main"));
        assertThat(added.getStatus()).isEqualTo(Status.CREATING);
        assertThat(added.getComponentUrl()).isEqualTo("url");
    }

    @Test
    void givenExistingComponent_whenUpdateExistingComponent_thenUpdatedCorrectly() {
        //given
        String encodedFull = base64("repo/z?at=refs/heads/main");
        String encodedRepo = base64("repo/z");

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .catalogItemRef(null)
                .componentUrl("oldUrl")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated =
                service.updateExistingComponent(pc, "comp1", encodedFull, Status.CREATED, "newUrl");

        //then
        ProjectComponent updatedComp = updated.getComponents().get("comp1");

        assertThat(updatedComp.getStatus()).isEqualTo(Status.CREATED);
        assertThat(updatedComp.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/main"));
        assertThat(updatedComp.getComponentUrl()).isEqualTo("newUrl");
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
        ProjectComponents updated =
                service.updateExistingComponent(pc, "comp1", encodedFullDifferent, Status.CREATED, "x");

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
                service.updateExistingComponent(pc, "unknown", "zzz", Status.CREATED, "x"))
                .isInstanceOf(InvalidComponentStateException.class);
    }

    @Test
    void givenExistingComponent_whenUpdatePartially_thenUpdatesOnlyFieldsProvided() {
        //given
        String encodedRepo = base64("repo/a");
        String encodedFull = base64("repo/a?at=refs/heads/dev");

        ProjectComponent existing = ProjectComponent.builder()
                .componentId("comp1")
                .catalogItemId(encodedRepo)
                .catalogItemRef(base64("?at=refs/heads/main"))
                .componentUrl("oldUrl")
                .status(Status.CREATING)
                .build();

        ProjectComponents pc = ProjectComponents.builder()
                .components(Map.of("comp1", existing))
                .build();

        //when
        ProjectComponents updated =
                service.updatePartiallyExistingComponent(pc, "comp1", encodedFull, Status.CREATED, null);

        //then
        ProjectComponent result = updated.getComponents().get("comp1");

        assertThat(result.getStatus()).isEqualTo(Status.CREATED);
        assertThat(result.getComponentUrl()).isEqualTo("oldUrl"); // unchanged
        assertThat(result.getCatalogItemRef()).isEqualTo(base64("?at=refs/heads/dev"));
    }

    @Test
    void givenNonExistingComponent_whenUpdatePartially_thenThrow() {
        //given
        ProjectComponents pc = ProjectComponents.builder()
                .components(new HashMap<>())
                .build();

        //when //then
        assertThatThrownBy(() ->
                service.updatePartiallyExistingComponent(pc, "missing", "zzz", Status.CREATED, "x"))
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
    void givenValidCatalogItemId_whenGetRepoPath_thenReturnsPathWithoutBranch() {
        //given
        String encoded = base64("repo/x?at=refs/heads/main");

        //when
        String result = service.getRepoPathFromCatalogItemId(encoded);

        //then
        assertThat(result).isEqualTo(base64("repo/x"));
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

}