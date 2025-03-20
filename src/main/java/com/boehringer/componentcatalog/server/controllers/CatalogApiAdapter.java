package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.model.CatalogItem;
import com.boehringer.componentcatalog.server.model.CatalogItemFilter;
import com.boehringer.componentcatalog.server.model.CatalogItemTag;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityPermissionEnum;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityContext;
import com.boehringer.componentcatalog.server.services.common.IdEncoderDecoder;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static one.util.streamex.MoreCollectors.entriesToCustomMap;

public class CatalogApiAdapter {

    private CatalogApiAdapter() {
        // Hide implicit public constructor
    }

    public static CatalogItem asCatalogItem(CatalogItemEntityContext itemEntityCtx, Set<CatalogEntityPermissionEnum> permissions) {
        // If the principal has *any* permissions, it has access to the repo
        var hasRepoAccess = !permissions.isEmpty();

        // TODO fully determine which fields are mandatory and which are optional //NOSONAR
        // Set mandatory fields
        var builder = CatalogItem.builder()
                .id(itemEntityCtx.getCatalogItemId())
                .title(itemEntityCtx.getName())
                .shortDescription(itemEntityCtx.getShortDescription())
                .itemSrc(hasRepoAccess ? itemEntityCtx.getRepoItemSrc() : null)
                .path(hasRepoAccess ? itemEntityCtx.getRepoItemPath() : null)
                .tags(toCatalogItemTags(itemEntityCtx.getRepoItemTags()))
                .date(itemEntityCtx.getRepoLastCommitDateUTC());

        // Set optional fields
        ofNullable(itemEntityCtx.getContributors())
                .ifPresent(builder::authors);

        // Encode paths to ids, only if paths are present
        // FIXME encoding/decoding id's is not the responsibility of this class or API layer, //NOSONAR
        //  move this coding/encoding to the service layer
        ofNullable(itemEntityCtx.getDescriptionPath())
                .map(IdEncoderDecoder::idEncode)
                .ifPresent(builder::descriptionFileId);

        ofNullable(itemEntityCtx.getImagePath())
                .map(IdEncoderDecoder::idEncode)
                .ifPresent(builder::imageFileId);

        return builder.build();
    }

    public static List<CatalogItemFilter> catalogItemFiltersFrom(CatalogEntity catalogEntity,
                                                                 List<CatalogItemEntityContext> itemEntitiesCtxs,
                                                                 Set<CatalogEntityPermissionEnum> permissions) {
        // Group all tags from all items and group them by label, in order to
        // get pairs: (itemLabel, List<CatalogItemTag> itemTags)
        var itemLabelsItemTags = StreamEx.of(itemEntitiesCtxs)
                .map(itemEntityCtx -> CatalogApiAdapter.asCatalogItem(itemEntityCtx, permissions))
                .flatCollection(CatalogItem::getTags)
                .groupingBy(CatalogItemTag::getLabel);

        var catalogLabels = catalogEntity
                .getMetadata()
                .getSpec()
                .getTags()
                .stream()
                .toList();

        // Keep only the items with labels that match the Catalog.yaml declared tags,
        // and replace item labels with the Catalog.yaml tags
        var catalogLabelsItemTags = EntryStream.of(itemLabelsItemTags)
                .filterKeys(catalogLabels::contains)
                .sortedByInt(labelOptions -> catalogLabels.indexOf(labelOptions.getKey()));

        // Keep the same order as in the Catalog.yml
        var catalogLabelsItemFilters = catalogLabelsItemTags
                .mapKeyValue(CatalogApiAdapter::labeledFilterEntry)
                .collect(entriesToCustomMap(LinkedHashMap<String, CatalogItemFilter>::new));

        // Return filters according to the order in the Catalog.yaml
        return List.copyOf(catalogLabelsItemFilters.values());
    }

    private static List<CatalogItemTag> toCatalogItemTags(Map<String, Set<String>> itemsEntitiesLabelsOptions) {
        return EntryStream.of(itemsEntitiesLabelsOptions)
                .mapKeyValue((label, options) -> CatalogItemTag.builder()
                        .label(label)
                        .options(options)
                        .build())
                .toList();
    }

    private static Map.Entry<String, CatalogItemFilter> labeledFilterEntry(String catalogLabel, List<CatalogItemTag> itemTags) {
        // Remove options duplicated on different catalog item tags
        var filterOptions = StreamEx.of(itemTags)
                .flatCollection(CatalogItemTag::getOptions)
                .toSet();

        // Build filter for that label, with the unique options selectable for that filter
        var filter = CatalogItemFilter.builder()
                .label(catalogLabel)
                .options(filterOptions)
                .build();

        return Map.entry(catalogLabel, filter);
    }
}
