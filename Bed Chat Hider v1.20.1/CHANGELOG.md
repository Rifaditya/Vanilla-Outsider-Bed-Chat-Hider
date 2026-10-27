# Changelog

All notable changes to Vanilla Outsider: Bed Chat Hider (MC 1.20.1) will be documented in this file.

## 1.1.1+1.20.1

### Fixed
- Pressing F1 while in bed now cleanly hides the "[ Leave Bed ]" and "[ Show Chat ]" / "[ Hide Chat ]" buttons for pristine screenshot and celestial time-lapse capture.

## [1.1.0+1.20.1] - 2026-10-24

### Added
- **Mod Menu Configuration**: Added optional Mod Menu in-game configuration screen via DasikLibrary's `UniversalConfigScreen`.
- **Sleep Vignette Suppression**: Added `suppress_sleep_vignette` option to eliminate the dark sleep vignette overlay.
- **Collision Avoidance**: Added smart collision avoidance: cleanly defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** when installed.
- **Auto-Hide In-Bed Chat**: Added `auto_hide_chat` option to configure default chat toggle state upon entering bed.

## [1.0.1+1.20.1] - 2026-10-12

### Fixed
- **Runtime Safety**: Purged ModVersionGuard reflection runtime hazard and Knot ClassLoader startup checks.
- **Dependency Alignment**: Verified Dasik Library dependency bounds (`>=1.1.0`).

## [1.0.0+1.20.1] - 2026-10-01

### Added
- Legacy Anchor port for Minecraft 1.20.1.
- Client-side in-bed chat hide/show toggle button while sleeping.
- Full GLFW input blocking and widget visibility handling when chat is hidden.
- Integration with Dasik Library 1.1.0+1.20.1.
