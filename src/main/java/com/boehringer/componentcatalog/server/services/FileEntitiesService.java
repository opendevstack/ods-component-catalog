package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketInvalidEntityException;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt.BitbucketPathAtBuilder;
import com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidEntityException;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import com.boehringer.componentcatalog.util.FileFormatUtils;
import com.boehringer.componentcatalog.util.MarkdownUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder.idDecode;
import static com.boehringer.componentcatalog.util.EitherUtils.maybeValueFrom;
import static com.boehringer.componentcatalog.util.EitherUtils.uncheckedFrom;

@Service
@AllArgsConstructor
@Slf4j
public class FileEntitiesService {

    private final BitbucketService bitbucketService;

    public Optional<Pair<MediaType, byte[]>> getFileById(String id) throws InvalidIdException, InvalidEntityException {
        var filePathAt = this.bitbucketPathAtFromId(id);

        try {
            return bitbucketService.getBinaryFileContents(filePathAt);
        } catch (BitbucketInvalidEntityException e) {
            throw new InvalidEntityException("Requested file exists but it's not a valid file, file path: " + filePathAt, e);
        }
    }

    private BitbucketPathAt bitbucketPathAtFromId(String id) throws InvalidIdException {
        var builder = bitbucketService.pathAtBuilder();

        return Optional.of(idDecode(id))
                .flatMap(maybeValueFrom(builder::pathAt))
                .flatMap(maybeValueFrom(BitbucketPathAtBuilder::build))
                .orElseThrow(() -> new InvalidIdException(id));
    }

    public String inlineMarkdownImgs(String id, String markdown) {
        var markdownPathAt = bitbucketService.pathAtBuilder()
                .pathAt(uncheckedFrom(IdEncoderDecoder::idDecode).apply(id))
                .build();

        var parentPathAt = markdownPathAt.getParent();
        var imgRelLinks = MarkdownUtils.parseMarkdownImgRelLinks(markdown);

        // Retrieve images in parallel, in order to be more efficient and take advantage of caching
        var imLinkPairs = imgRelLinks.parallelStream()
                .map(imgLink -> {
                    var imgRelUrl = imgLink.getUri().getPath();
                    var imgPathAt = parentPathAt.copy().appendSubPath(imgRelUrl);
                    var maybeImgDataLink = MarkdownUtils.asDataImgLink(imgLink, imgSupplier(imgPathAt));
                    return Pair.of(imgLink, maybeImgDataLink);
                })
                .filter(pair -> pair.getRight().isPresent())
                .map(pair -> Pair.of(pair.getLeft(), pair.getRight().get()))
                .collect(Collectors.toSet());

        return MarkdownUtils.replaceImgLinks(markdown, imLinkPairs);
    }

    private Supplier<Pair<String, byte[]>> imgSupplier(BitbucketPathAt imgPathAt) {
        return () -> {
            var imgContents = bitbucketService.getBinaryFileContents(imgPathAt);
            var imgMediaType = imgContents.get().getLeft();

            if(!FileFormatUtils.isImage(imgMediaType)) {
                throw new BitbucketInvalidEntityException("Requested image is problably corrupted, image path: " + imgPathAt.getPathAt());
            }

            var imgBytes = imgContents.get().getRight();
            var imgMediaTypeStr = MimeTypeUtils.toString(List.of(imgMediaType));

            return Pair.of(imgMediaTypeStr, imgBytes);
        };
    }

}
