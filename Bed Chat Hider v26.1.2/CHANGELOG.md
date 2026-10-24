# Changelog - Vanilla Outsider: Bed Chat Hider (MC 26.1.2)

All notable changes to the Minecraft 26.1.2 port of Vanilla Outsider: Bed Chat Hider will be documented in this file.

## [1.1.0+26.1.2] - 2026-10-24

### Added
- **Mod Menu Configuration**: Added optional Mod Menu in-game configuration screen via DasikLibrary's `UniversalConfigScreen`.
- **Sleep Vignette Suppression**: Added `suppress_sleep_vignette` option to eliminate the dark sleep vignette overlay.
- **Collision Avoidance**: Added smart collision avoidance: cleanly defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** when installed.
- **Auto-Hide In-Bed Chat**: Added `auto_hide_chat` option to configure default chat toggle state upon entering bed.

## [1.0.6+26.1.2] - 2026-10-12

### Fixed
- **Runtime Safety**: Purged ModVersionGuard reflection runtime hazard and Knot ClassLoader startup checks.
- **Dependency Alignment**: Aligned Dasik Library dependency bounds to `>=1.9.0`.

## [1.0.5+26.1.2] - 2026-10-01

### Added
- **Modern Sovereign Anchor Port**: Initial port to Minecraft 26.1.2 under the 1 Jar 1 Version Policy.
- **In-Bed Chat Toggle**: Seamless button toggle to hide or reveal chat screen widgets while resting in bed.
- **Dasik Library Integration**: Powered by Dasik Library for unified cross-mod community links, creator support, and centralized runtime safeguards.
- **Automated Verification Suite**: Full test suite covering state toggling logic and runtime safety checks.
- **Enhanced Community Links**: Direct links to Discord and Ko-fi in ModMenu metadata.
