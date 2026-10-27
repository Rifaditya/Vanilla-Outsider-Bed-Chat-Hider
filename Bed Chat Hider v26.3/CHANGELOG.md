# Changelog

## [1.2.0+26.3] - 2026-10-27

### Added
- **Cinematic Sleep HUD Auto-Fade**: Seamlessly fades out `[ Leave Bed ]` and chat toggle buttons during inactivity after an idle delay (default 2.5s) to allow serene, immersive observation of celestial twilight and morning sunrises.
- **Blind Click Protection**: Clicking while bed UI elements are fully faded wakes the HUD immediately without accidentally triggering the leave bed button or background clicks.
- **Sleep Crosshair Suppression**: Smoothly suppresses the center crosshair while resting in bed once idle fade engages (`fade_crosshair_when_sleeping`).
- **Sub-Pixel Deadzone Drift Filter**: 2.0px sensor jitter filtering prevents microscopic mouse noise from waking the UI prematurely.
- **Configurable Inactivity Delay & Toggles**: Fully configurable through Mod Menu (`enable_cinematic_sleep_fade`, `cinematic_fade_delay_seconds`, `fade_crosshair_when_sleeping`).

## 1.1.1+26.3

### Fixed
- Pressing F1 while in bed now cleanly hides the "[ Leave Bed ]" and "[ Show Chat ]" / "[ Hide Chat ]" buttons for pristine screenshot and celestial time-lapse capture.

## [1.1.0+26.3] - 2026-10-24

### Added
- **Mod Menu Configuration**: Added optional Mod Menu in-game configuration screen via DasikLibrary's `UniversalConfigScreen`.
- **Sleep Vignette Suppression**: Added `suppress_sleep_vignette` option to eliminate the dark sleep vignette overlay.
- **Collision Avoidance**: Added smart collision avoidance: cleanly defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** when installed.
- **Auto-Hide In-Bed Chat**: Added `auto_hide_chat` option to configure default chat toggle state upon entering bed.

## [1.0.6+26.3] - 2026-10-12

### Fixed
- **Runtime Safety**: Purged ModVersionGuard reflection runtime hazard and Knot ClassLoader startup checks.
- **Dependency Alignment**: Aligned Dasik Library dependency bounds to `>=1.9.0`.

## [1.0.5+26.3] - 2026-09-24

### Added
- **Dasik Library Integration**: Powered by Dasik Library 1.9.2+ for unified cross-mod community links, creator support, and centralized runtime safeguards.
- **Automated Verification Suite**: Added JUnit 5 test suite covering state toggling logic and runtime safety checks.
- **Enhanced Community Links**: Added direct links to Discord and Ko-fi in ModMenu metadata.

## [1.0.4+26.3] - 2026-09-14

### Added
- **Minecraft 26.3 Port**: Initial release supporting Minecraft 26.3 snapshot-6.
- **Dependency Upgrades**: Compiled against Fabric Loader 0.19.3 and Fabric API 0.156.1+26.3.
- **Java 25 Baseline**: Upgraded toolchain and execution target to Java 25.
