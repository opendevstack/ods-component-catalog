package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.FilesApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.model.FileFormat;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.FileEntitiesService;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

import static org.opendevstack.component_catalog.util.FileFormatUtils.*;
import static java.lang.String.format;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class FilesApiController implements FilesApi {

    private final NativeWebRequest request;
    private final AuthorizationInfo authInfo;
    private final FileEntitiesService fileEntitiesService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<byte[]> getFileById(String id, FileFormat format) {
        log.debug("User '{}' [{}] requested file with id: '{}'",
                authInfo.getCurrentName(),
                authInfo.getCurrentPrincipalName(),
                id);
        try {
            var fileContents = this.fileEntitiesService.getFileById(id);

            if(fileContents.isEmpty()) {
                throw new RestEntityNotFoundException("File not found, file id: " + id);
            }

            var contentType = fileContents.get().getLeft();

            if(!contentTypeMatchesFormat(contentType, format)) {
                throw new BadRequestException("Requested file format: %s, does not match the actual file format, file id: %s"
                                .formatted(format, id));
            }

            var body = fileContents.get().getRight();

            if(isMarkdown(contentType)) {
                var markdown = new String(body);
                body = this.fileEntitiesService.inlineMarkdownImgs(id, markdown).getBytes();
            }

            var headers = new HttpHeaders();
            headers.setContentType(contentType);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(body);
        } catch (InvalidIdException e) {
            throw new RestEntityNotFoundException("File not found, file id: " + id);
        } catch (InvalidEntityException e) {
            throw new InvalidRestEntityException("Invalid file, file id: " + id);
        }
    }

    private static boolean contentTypeMatchesFormat(MediaType contentType, FileFormat format) {
        return switch (format) {
            case MARKDOWN -> isMarkdown(contentType);
            case YAML -> isYaml(contentType);
            case IMAGE -> isImage(contentType);
        };
    }
}
