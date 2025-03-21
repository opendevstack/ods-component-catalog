package com.boehringer.componentcatalog.util;

import lombok.Data;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.tuple.Pair;

import java.net.URI;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static com.boehringer.componentcatalog.util.EitherUtils.maybeValue;

public class MarkdownUtils {
    private static final Pattern IMG_LINK_PATTERN = Pattern.compile("!\\[([^\\]]+)]\\(([^)]+)\\)");

    private MarkdownUtils() {
        // Hide the implicit public constructor
    }

    public static Set<MarkdownImgLink> parseMarkdownImgLinks(String markdown) {
        return StreamEx.of(new Scanner(markdown).findAll(IMG_LINK_PATTERN))
                .map(MatchResult::group)
                .map(grp -> maybeValue(MarkdownImgLink::from, grp))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toSet();
    }

    public static Set<MarkdownImgLink> parseMarkdownImgRelLinks(String markdown) {
        return StreamEx.of(parseMarkdownImgLinks(markdown).stream())
                .filter(imgLink -> !imgLink.getUri().isAbsolute())
                .filter(imgLink -> !Objects.equals(imgLink.getUri().getScheme(), "data"))
                .toSet();
    }

    public static String replaceImgLinks(String markdown, Set<Pair<MarkdownImgLink, MarkdownImgLink>> imgLinkPairs) {
        var replacedMarkdown = markdown;

        for(var imgLinkPair : imgLinkPairs) {
            replacedMarkdown = replaceImgLink(replacedMarkdown, imgLinkPair);
        }

        return replacedMarkdown;
    }

    public static String replaceImgLink(String markdown, Pair<MarkdownImgLink, MarkdownImgLink> imgLinkPair) {
        var origLink = imgLinkPair.getLeft();
        var newLink = imgLinkPair.getRight();

        return markdown.replace(origLink.getMarkdownLink(), newLink.getMarkdownLink());
    }

    public static Optional<MarkdownImgLink> asDataImgLink(MarkdownImgLink imgLink, Supplier<Pair<String, byte[]>> imgSupplier) {
        var missingImgLink = MarkdownImgLink.from(imgLink.getText(), URI.create("missing_img.png"));

        return maybeValue(imgSupplier)
                .map(imgContents -> asDataImgLink(imgLink, imgContents))
                .or(() -> Optional.of(missingImgLink));
    }

    public static MarkdownImgLink asDataImgLink(MarkdownImgLink imgLink, Pair<String, byte[]> imgContents) {
        var mimeType = imgContents.getLeft();
        var imgBytes = imgContents.getRight();
        var dataUri = asDataUri(mimeType, imgBytes);

        return MarkdownImgLink.from(imgLink.getText(), dataUri);
    }

    private static URI asDataUri(String mimeType, byte[] bytes) {
        var uriStr = "data:%s;base64,%s".formatted(mimeType, Base64.getEncoder().encodeToString(bytes));
        return URI.create(uriStr);
    }

    @Data
    public static class MarkdownImgLink {

        public static MarkdownImgLink from(String markdownLink) {
            var matcher = IMG_LINK_PATTERN.matcher(markdownLink);
            if(!matcher.matches()) {
                throw new IllegalArgumentException("Invalid markdown link: " + markdownLink);
            }
            return new MarkdownImgLink(markdownLink, matcher.group(1), URI.create(matcher.group(2)));
        }

        public static MarkdownImgLink from(String text, URI uri) {
            var markdownLink = "![%s](%s)".formatted(text, uri);
            return new MarkdownImgLink(markdownLink, text, uri);
        }

        private final String markdownLink;
        private final String text;
        private final URI uri;
    }

}
