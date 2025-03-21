package com.boehringer.componentcatalog.util;

import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFormatUtils {
    private static final TikaConfig TIKA_DEFAULT_CONFIG = TikaConfig.getDefaultConfig();
    private static final Tika TIKA_DEFAULT = new Tika(TIKA_DEFAULT_CONFIG);
    private static final MediaTypeRegistry REGISTRY = MediaTypeRegistry.getDefaultRegistry();

    private static final List<MediaType> YAML_TYPES;
    private static final List<MediaType> MARKDOWN_TYPES;
    private static final List<MediaType> IMG_TYPES;

    static {

        YAML_TYPES = Stream.of(
                        MediaType.text("x-yaml"),
                        MediaType.text("yaml"),
                        MediaType.application("yaml"))
                .map(REGISTRY::normalize)
                .toList();

        MARKDOWN_TYPES = Stream.of(
                        MediaType.text("x-web-markdown"),
                        MediaType.text("x-markdown"),
                        MediaType.text("markdown"))
                .map(REGISTRY::normalize)
                .toList();

        IMG_TYPES = REGISTRY.getTypes().stream()
                .filter(t -> t.getType().equals("image"))
                .map(REGISTRY::normalize)
                .toList();
    }

    private FileFormatUtils() {
        // Hide public default constructor
    }

    @SneakyThrows
    public static org.springframework.http.MediaType detectMediaType(String filename, byte[] fileBytes) {
        var metaNoFile = new Metadata();

        var meta = new Metadata();
        meta.set(TikaCoreProperties.RESOURCE_NAME_KEY, filename);

        var inputNoFile = TikaInputStream.get(fileBytes, metaNoFile);
        var input = TikaInputStream.get(fileBytes, meta);

        var mediaNoFile = TIKA_DEFAULT.getDetector().detect(inputNoFile, metaNoFile);
        var media = TIKA_DEFAULT.getDetector().detect(input, meta);

        // Workaround: Tika sometimes detects files with binary contents as text
        // Be more restrictive: it's a text file only if both contents and contents + filename agree
        var selectedMedia = isTextType(mediaNoFile) && isTextType(media)
                ? media
                : mediaNoFile;

        return org.springframework.http.MediaType.parseMediaType(selectedMedia.toString());
    }

    public static boolean isImage(org.springframework.http.MediaType type) {
        var mediaType = MediaType.parse(type.toString());

        return isType(mediaType, IMG_TYPES);
    }

    public static boolean isMarkdown(org.springframework.http.MediaType type) {
        return isType(MediaType.parse(type.toString()), MARKDOWN_TYPES);
    }

    public static boolean isYaml(org.springframework.http.MediaType type) {
        return isType(MediaType.parse(type.toString()), YAML_TYPES);
    }

    private static boolean isTextType(MediaType type) {
        var allTypes = getSuperTypes(type);
        allTypes.add(0, type);

        return allTypes.stream()
                .anyMatch(t -> REGISTRY.isInstanceOf(t, MediaType.TEXT_PLAIN));
    }

    private static ArrayList<MediaType> getSuperTypes(MediaType type) {
        var sup = REGISTRY.getSupertype(type);

        if(sup == null) {
            return new ArrayList<>();
        }

        var supSup = getSuperTypes(sup);
        supSup.add(0, sup);

        return supSup;
    }

    private static boolean isType(MediaType type, List<MediaType> baseTypes) {
        var normType = REGISTRY.normalize(type);

        return baseTypes.stream()
                .anyMatch(bt -> REGISTRY.isInstanceOf(normType, bt));
    }
}
