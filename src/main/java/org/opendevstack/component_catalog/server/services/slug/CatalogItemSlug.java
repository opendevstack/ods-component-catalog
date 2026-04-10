package org.opendevstack.component_catalog.server.services.slug;

import lombok.Getter;

/**
 * Value object representing a parsed composite catalog item slug.
 * <p>
 * The raw slug format is: {@code {project-key}_{catalog-item-repository-name}}.
 * The separator is the <em>first</em> underscore ({@code _}); everything before it is the
 * {@code projectKey} and everything after (which may itself contain underscores) is the
 * {@code repoName}.
 * <ul>
 *   <li>{@code projectKey} – the normalised (lowercase) Bitbucket project key that owns the catalog item's repository.</li>
 *   <li>{@code repoName} – the Bitbucket repository slug of the catalog item.</li>
 * </ul>
 * Example: {@code myproject_my-component-repo}
 */
@Getter
public class CatalogItemSlug {

    public static final String SEPARATOR = "_";

    private final String projectKey;
    private final String repoName;

    private CatalogItemSlug(String projectKey, String repoName) {
        this.projectKey = projectKey;
        this.repoName = repoName;
    }

    /**
     * Parses a raw composite slug into its two components.
     *
     * @param rawSlug the composite slug in the form {@code {project-key}_{repo-name}}
     * @return a {@link CatalogItemSlug} instance
     * @throws InvalidCatalogItemSlugException if the slug does not contain the separator
     *                                          or either component is blank
     */
    public static CatalogItemSlug parse(String rawSlug) {
        if (rawSlug == null || rawSlug.isBlank()) {
            throw new InvalidCatalogItemSlugException("Slug must not be blank.");
        }

        int separatorIndex = rawSlug.indexOf(SEPARATOR);
        if (separatorIndex <= 0 || separatorIndex == rawSlug.length() - 1) {
            throw new InvalidCatalogItemSlugException(
                    "Slug '%s' does not conform to expected format '{project-key}_{repo-name}'.".formatted(rawSlug));
        }

        String projectKey = rawSlug.substring(0, separatorIndex).trim();
        String repoName = rawSlug.substring(separatorIndex + 1).trim();

        if (projectKey.isBlank() || repoName.isBlank()) {
            throw new InvalidCatalogItemSlugException(
                    "Slug '%s' contains blank project-key or repo-name component.".formatted(rawSlug));
        }

        return new CatalogItemSlug(projectKey, repoName);
    }

    /**
     * Normalises an arbitrary string to lowercase form so it can be
     * compared against the {@code projectKey} component of a composite slug.
     *
     * @param value the value to normalise
     * @return the normalised value (lowercase, spaces replaced by hyphens)
     */
    public static String normalise(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().toLowerCase().replace(" ", "-");
    }

    @Override
    public String toString() {
        return projectKey + SEPARATOR + repoName;
    }
}

