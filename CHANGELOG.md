# Changelog

## [1.0.2-26.2] - 2026-07-14

### Removed
- Fabric API dependency, making the mod fully standalone with zero runtime dependencies.

## [1.0.1-26.2] - 2026-07-14

### Fixed
- Syntax error in fabric.mod.json causing ModResolutionException during startup.

## [1.0.0-26.2] - 2026-07-14

### Added
- Initial release of Bed Chat Hider (Vanilla Outsider Collection) for Minecraft 26.2.
- Simple client-only button to toggle chat visibility when sleeping in bed.
- In-memory session state tracking.
- Bypasses input events (typing, clicks, scrolling) when hidden to prevent background interaction.
