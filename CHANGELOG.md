## [1.0.5+26.3] - 2026-09-24

### Added
- **Dasik Library Integration**: Powered by Dasik Library 1.9.2+ for unified cross-mod community links, creator support, and centralized runtime safeguards.
- **Automated Verification Suite**: Added JUnit 5 test suite covering state toggling logic and runtime safety checks.
- **Enhanced Community Links**: Added direct links to Discord and Ko-fi in ModMenu metadata.

## [1.0.5+26.2] - 2026-09-24

### Added
- **Dasik Library Integration**: Powered by Dasik Library 1.9.2+ for unified cross-mod community links, creator support, and centralized runtime safeguards.
- **Automated Verification Suite**: Added JUnit 5 test suite covering state toggling logic and runtime safety checks.
- **Enhanced Community Links**: Added direct links to Discord and Ko-fi in ModMenu metadata.

## [1.0.4+26.3] - 2026-09-14

### Added
- **Minecraft 26.3 Port**: Initial release supporting Minecraft 26.3 snapshot-6.
- **Dependency Upgrades**: Compiled against Fabric Loader 0.19.3 and Fabric API 0.156.1+26.3.
- **Java 25 Baseline**: Upgraded toolchain and execution target to Java 25.

## [1.0.4+26.2] - 2026-07-22

### ⚠️ Version Guard Notice
- Includes zero-dependency ModVersionGuard pre-release protection. Halts startup with an explicit warning banner if run on incompatible Minecraft drops or missing core dependencies to prevent world save corruption.

### Fixed
- **ModVersionGuard Protection Banner**: Updated ModVersionGuard.java to use Knot ClassLoader resolution (Thread.currentThread().getContextClassLoader()) and display explicit pre-release protection warnings upon an API mismatch.

## [1.0.3+26.2] - 2026-07-22

### Added
- **Forward Compatibility & Version Guard**: Configured `fabric.mod.json` with `"minecraft": ">=26.2-"` for open-ended forward compatibility. Added zero-dependency `ModVersionGuard` check on startup to display human-readable guidance if an incompatible Minecraft API version is encountered.

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
