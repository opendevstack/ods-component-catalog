package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileEntitiesServiceTest {

    @Mock
    private BitbucketService bitbucketService;

    @Mock
    private BitbucketPathAt.BitbucketPathAtBuilder bitbucketPathAtBuilder;

    @InjectMocks
    private FileEntitiesService fileEntitiesService;

    @Test
    void givenAValidId_whenGetFileById_thenReturnsFileContents() throws InvalidIdException, InvalidEntityException {
        String id = "valid-id";
        BitbucketPathAt pathAt = BitbucketPathAtMother.of();

        byte[] fileContent = "file content".getBytes();
        MediaType mediaType = MediaType.TEXT_PLAIN;

        when(bitbucketService.pathAtBuilder()).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.pathAt(any())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.build()).thenReturn(pathAt);
        when(bitbucketService.getCachedBinaryFileContents(pathAt)).thenReturn(Optional.of(Pair.of(mediaType, fileContent)));

        Optional<Pair<MediaType, byte[]>> result = fileEntitiesService.getFileById(id);

        assertTrue(result.isPresent());
        assertEquals(mediaType, result.get().getLeft());
        assertArrayEquals(fileContent, result.get().getRight());
    }

    @Test
    void givenAnIdAndAMarkdown_whenInlineMarkdownImgs_thenReturnsMarkdownWithInlineImages() {
        // given
        String id = "valid-id";
        String markdown = "![image](image.png)";

        BitbucketPathAt pathAt = BitbucketPathAtMother.of();
        when(bitbucketService.pathAtBuilder()).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.pathAt(any())).thenReturn(bitbucketPathAtBuilder);
        when(bitbucketPathAtBuilder.build()).thenReturn(pathAt);

        String expectedMarkdown = "![image](missing_img.png)";

        // when
        String result = fileEntitiesService.inlineMarkdownImgs(id, markdown);

        // then
        assertEquals(expectedMarkdown, result);
    }
}