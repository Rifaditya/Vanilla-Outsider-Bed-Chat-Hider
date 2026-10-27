# Changelog

All notable changes to Vanilla Outsider: Bed Chat Hider (MC 1.21.11) will be documented in this file.

## [1.2.0+1.21.11] - 2026-10-27

### Added
- **Cinematic Sleep HUD Auto-Fade**: Seamlessly fades out `[ Leave Bed ]` and chat toggle buttons during inactivity after an idle delay (default 2.5s) to allow serene, immersive observation of celestial twilight and morning sunrises.
- **Blind Click Protection**: Clicking while bed UI elements are fully faded wakes the HUD immediately without accidentally triggering the leave bed button or background clicks.
- **Sleep Crosshair Suppression**: Smoothly suppresses the center crosshair while resting in bed once idle fade engages (`fade_crosshair_when_sleeping`).
- **Sub-Pixel Deadzone Drift Filter**: 2.0px sensor jitter filtering prevents microscopic mouse noise from waking the UI prematurely.
- **Configurable Inactivity Delay & Toggles**: Fully configurable through Mod Menu (`enable_cinematic_sleep_fade`, `cinematic_fade_delay_seconds`, `fade_crosshair_when_sleeping`).

## 1.1.1+1.21.11

### Fixed
- Pressing F1 while in bed now cleanly hides the "[ Leave Bed ]" and "[ Show Chat ]" / "[ Hide Chat ]" buttons for pristine screenshot and celestial time-lapse capture.

## [1.1.0+1.21.11] - 2026-10-24

### Added
- **Mod Menu Configuration**: Added optional Mod Menu in-game configuration screen via DasikLibrary's `UniversalConfigScreen`.
- **Sleep Vignette Suppression**: Added `suppress_sleep_vignette` option to eliminate the dark sleep vignette overlay.
- **Collision Avoidance**: Added smart collision avoidance: cleanly defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** when installed.
- **Auto-Hide In-Bed Chat**: Added `auto_hide_chat` option to configure default chat toggle state upon entering bed.

## [1.0.1+1.21.11] - 2026-10-12

### Fixed
- **Runtime Safety**: Purged ModVersionGuard reflection runtime hazard and Knot ClassLoader startup checks.
- **Dependency Alignment**: Verified Dasik Library dependency bounds (`>=1.1.0`).

## [1.0.0+1.21.11] - 2026-10-01

### Added
- Transitional Late Anchor port for Minecraft 1.21.11.
- Client-side in-bed chat hide/show toggle button while sleeping.
- Full input blocking and widget visibility handling when chat is hidden.
- Integration with Dasik Library 1.1.0+1.21.11.
