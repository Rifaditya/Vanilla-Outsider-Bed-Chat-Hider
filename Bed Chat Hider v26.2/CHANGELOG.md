# Changelog

## [1.2.0+26.2] - 2026-10-27

### Added
- **Cinematic Sleep HUD Auto-Fade**: Seamlessly fades out `[ Leave Bed ]` and chat toggle buttons during inactivity after an idle delay (default 2.5s) to allow serene, immersive observation of celestial twilight and morning sunrises.
- **Blind Click Protection**: Clicking while bed UI elements are fully faded wakes the HUD immediately without accidentally triggering the leave bed button or background clicks.
- **Sleep Crosshair Suppression**: Smoothly suppresses the center crosshair while resting in bed once idle fade engages (`fade_crosshair_when_sleeping`).
- **Sub-Pixel Deadzone Drift Filter**: 2.0px sensor jitter filtering prevents microscopic mouse noise from waking the UI prematurely.
- **Configurable Inactivity Delay & Toggles**: Fully configurable through Mod Menu (`enable_cinematic_sleep_fade`, `cinematic_fade_delay_seconds`, `fade_crosshair_when_sleeping`).

## 1.1.1+26.2

### Fixed
- Pressing F1 while in bed now cleanly hides the "[ Leave Bed ]" and "[ Show Chat ]" / "[ Hide Chat ]" buttons for pristine screenshot and celestial time-lapse capture.

## [1.1.0+26.2] - 2026-10-24

### Added
- **Mod Menu Configuration**: Added optional Mod Menu in-game configuration screen via DasikLibrary's `UniversalConfigScreen`.
- **Sleep Vignette Suppression**: Added `suppress_sleep_vignette` option to eliminate the dark sleep vignette overlay.
- **Collision Avoidance**: Added smart collision avoidance: cleanly defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** when installed.
- **Auto-Hide In-Bed Chat**: Added `auto_hide_chat` option to configure default chat toggle state upon entering bed.

## [1.0.6+26.2] - 2026-10-12

### Fixed
- **Runtime Safety**: Purged ModVersionGuard reflection runtime hazard and Knot ClassLoader startup checks.
- **Dependency Alignment**: Aligned Dasik Library dependency bounds to `>=1.9.0`.

## [1.0.5+26.2] - 2026-09-24

### Added
- **Dasik Library Integration**: Powered by Dasik Library 1.9.2+ for unified cross-mod community links, creator support, and centralized runtime safeguards.
- **Automated Verification Suite**: Added JUnit 5 test suite covering state toggling logic and runtime safety checks.
- **Enhanced Community Links**: Added direct links to Discord and Ko-fi in ModMenu metadata.

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
