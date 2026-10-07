# Example CHANGELOG.md structure, following Keep a Changelog
see https://codersnexus.com/es/tutorials/github-complete-course/changelog-md-maintaining-human-readable-version-history

see https://keepachangelog.com/en/1.0.0/

# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

### Added
- Used for New features.
- Allow project components obtention via client credentials from marketplace tokens
- Create a new endpoint to delete one or more parameters from an existing component
- Add endpoints to update project component parameters

### FIXED
- Used for Bug fixes.

### Changed
- Used for Changes to existing functionality.

### Deprecated/Removed
- Used for Deprecated or removed functionality.

### Security
- Used for Security-related changes.

## [2.2.0] - 2026-09-22

### Added
- Added a new paginated endpoint to list all provisioned project components.
- Added support for retrieving all catalog items without requiring a specific catalog ID.
- Added a new catalog activity endpoint with pagination, sorting, and filtering support.
- Added an internal endpoint to refresh caches.
- Added catalog owners to the API response model.
- Added `componentCount` to catalog item responses.
- Added `deletionWorkflowJobId` and `canBeDeleted` to project component details.
- Added support for catalog-specific whitelisted roles.

### Changed
- Updated provisioning status handling to use a shared enum across the API.
- Extended provisioning status values to include `DELETION_FAILED`.
- Improved handling of hidden but provisionable catalog items.
- Improved validation for workflow configuration so incomplete workflow definitions are rejected.
- Updated component archival behavior to include workflow job metadata when components are removed.
- Updated component update handling to preserve the raw component URL value.
- Improved retrieval of Bitbucket component files so only root files are returned where applicable.

### Fixed
- Fixed token handling when retrieving catalog items and project groups.
- Fixed unnecessary `403` calls in project info lookup flows.
- Fixed missing mapping for the `visible` property in catalog item responses.
- Fixed `catalogItemId` resolution when validating whitelisted roles.

### Internal / Maintenance
- Removed the custom RFC3339 date format implementation in favor of standard serialization behavior.
- Applied multiple code quality and Sonar-related improvements.
- Updated project documentation and repository coding instructions.

## API Notes
- `CatalogItem.visible` is now included in the API response.
- `CatalogItem.componentCount` is now included in the API response.
- `ProjectComponentExtendedInfo.deletionWorkflowJobId` is now included in the API response.
- `ProvisioningStatus` now includes `DELETION_FAILED`.
- `hasAutomatedDeletionWorkflow` has been removed from the contract.
