# 📜 Permanent Developer Ledger & Backlog History: Bed Chat Hider

All technical tickets, architectural decisions, and resolved backlog items are permanently archived here.

## [1.2.0+26.3 / 1.2.0+26.2 / 1.2.0+26.1.2 / 1.2.0+1.21.11 / 1.2.0+1.21.1 / 1.2.0+1.20.1] - 2026-10-27

### [BL-BCH-007] Cinematic Sleep HUD: Idle Mouse Inactivity Auto-Fade for Bed Buttons & Crosshair
- **Category**: `[FEATURE]`
- **Priority**: `[HIGH]`
- **Target Version**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`)
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-25
- **Date Resolved**: 2026-10-27
- **Resolution**:
  - Implemented `CinematicSleepController.java` client inactivity controller tracking cursor position, timestamp, and deadzone drift filtering ($> 2.0\text{ px}$) to smoothly fade UI elements after idle periods.
  - Intercepted `ChatScreenMixin.java` / `InBedChatScreenMixin.java`:
    - Injected blind-click protection: when widgets are fully faded (`alpha <= 0.01f`), clicks immediately awaken the UI instead of accidentally triggering background elements or bed exit.
    - Added alpha modulation across `AbstractWidget` instances (`leaveBedButton`, toggle chat button, chat box) with linear/ease-out interpolation based on `CinematicSleepController.getAlpha()`.
  - Intercepted `HudMixin.java` crosshair rendering (`renderCrosshair` / `extractCrosshair`) to suppress the center reticle during sleep when `fadeCrosshairWhenSleeping` is enabled.
  - Added omni-channel configuration options in `BedChatHiderConfig`:
    - `enableCinematicSleepFade` (default: `true`)
    - `cinematicFadeDelaySeconds` (default: `2.5f`)
    - `fadeCrosshairWhenSleeping` (default: `true`)
    - Localized descriptions and option labels in `assets/vanilla-outsider-bed-chat-hider/lang/en_us.json` across all 6 anchors.
  - Authored headless verification suite `BedChatHiderCinematicFadeTest.java` fulfilling the 5-pillar verification harness (chaos fuzzing, concurrency load, screen state AST, client interaction simulation, sidedness hygiene).
  - Built, tested, and validated all 6 version anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`) with 100% green test passes.

## [1.1.1+26.3 / 1.1.1+26.2 / 1.1.1+26.1.2 / 1.1.1+1.21.11 / 1.1.1+1.21.1 / 1.1.1+1.20.1] - 2026-10-27

### [BL-BCH-006] InBedChatScreen F1 Mode Visibility Suppression (F1 Honor Guard)
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Target Version**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`)
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-25
- **Date Resolved**: 2026-10-27
- **Resolution**:
  - Intercepted `InBedChatScreen` rendering at `HEAD` inside `ChatScreenMixin.java` under `(Object) this instanceof InBedChatScreen`, canceling the render pass whenever GUI visibility suppression is active.
  - Successfully bridged generational API shifts:
    - Anchors `1.20.1`, `1.21.1`, `1.21.11`, and `26.1.2`: Checked `this.minecraft.options != null && this.minecraft.options.hideGui`.
    - Anchors `26.2` and `26.3+`: Handled Mojang's deletion of `Options.hideGui` by checking `this.minecraft.gui.hud.isHidden()` (`[PAT-20261027-001]`).
  - Preserved input focus, draft retention, and keyboard event routing (`Escape` cleanly wakes the player up even while buttons are visually hidden).
  - Authored headless verification tests in `BedChatHiderSleepSimulationTest.java` across all 6 anchors.
  - Built, remapped, and deployed `1.1.1+<MC>` release JARs across all 4 universal distribution points.

## [1.1.0+26.3 / 1.1.0+26.2 / 1.1.0+26.1.2 / 1.1.0+1.21.11 / 1.1.0+1.21.1 / 1.1.0+1.20.1] - 2026-10-24

### [BL-BCH-004] Adopt 5-Pillar Testing & Sidedness Verification Harness Across All Anchors
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Target Version**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`)
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-24
- **Date Resolved**: 2026-10-24
- **Resolution**:
  - Implemented comprehensive 5-Pillar Testing Harness tailored to Bed Chat Hider's 100% client environment across all 6 Minecraft version anchors in lockstep:
    - **Pillar 5 (Sidedness & License Hygiene)**: Authored `BedChatHiderSidednessContractTest.java` verifying 100% exact single-line GPLv3 headers, client-only manifest isolation in `fabric.mod.json`, mixin registration consistency, and zero unmapped `Class.forName` reflection.
    - **Pillar 1 (Chaos Property & Config Fuzzing)**: Authored `BedChatHiderStressFuzzTest.java` executing 45,000 fuzzing iterations per anchor (270,000 total across matrix) verifying zero crashes and graceful recovery from adversarial/malformed JSON under the Freedom Over Anti-Crash policy.
    - **Pillar 2 (Virtual Concurrency Simulation)**: Authored `BedChatHiderConcurrencyLoadTest.java` running 50 concurrent worker threads under high-contention config read/write cycles and True Sleep collision overrides with zero deadlocks or race conditions.
    - **Pillars 3 & 4 (Sleep Simulation & Translation Coverage)**: Authored `BedChatHiderSleepSimulationTest.java` simulating headless player sleep state machine transitions, auto-hide toggle flows, True Sleep collision priority, and 100% coverage of language keys in `en_us.json`.
  - Resolved Step 0 baseline quarantine by sanitizing 3 legacy license headers in root `src/`, achieving 100% clean audit score (`code_auditor.py`).
  - Executed full test verification: 168 tests total passing 100% green across all 6 anchors.

### [BL-BCH-005] Optional Sleep Vignette Suppression with True Sleep Collision Avoidance & Mod Menu Config GUI
- **Category**: `[FEATURE]`
- **Priority**: `[HIGH]`
- **Target Version**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`)
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-24
- **Date Resolved**: 2026-10-24
- **Resolution**:
  - Implemented client configuration `BedChatHiderConfig` with options `suppressSleepVignette` and `autoHideChat`, persisted to `.minecraft/config/bedchathider.json`.
  - Added optional Mod Menu integration `BedChatHiderModMenu` powered by DasikLibrary's `UniversalConfigScreen` with safe YACL fallback.
  - Implemented smart companion mod collision avoidance in `BedChatHiderSupport.shouldSuppressSleepVignette()`: dynamically defers vignette suppression to companion mod **Vanilla Outsider: True Sleep** (`vanilla-outsider-true-sleep`) when loaded.
  - Injected `HudMixin` into in-game HUD rendering to cancel sleep vignette overlay when suppression is active.
  - Verified 100% headless tests across all 6 anchors. Built and deployed `1.1.0+<MC>` release JARs across all 4 deployment archive tiers.

## [1.0.6+26.3 / 1.0.6+26.2 / 1.0.6+26.1.2 / 1.0.1+1.21.11 / 1.0.1+1.21.1 / 1.0.1+1.20.1] - 2026-10-12

### [BL-BCH-003] Purge ModVersionGuard String Reflection Runtime Hazard Across All Anchors
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Target Version**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3` + legacy root)
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-12
- **Date Resolved**: 2026-10-12
- **Resolution**:
  - Completely purged `ModVersionGuard.checkClass(...)` and unremapped string reflection from `BedChatHiderClient.java` across all 6 version anchors and legacy root `src/`.
  - Removed unused imports of `net.dasik.social.util.ModVersionGuard`.
  - Eliminated Knot ClassLoader runtime crash hazards (`ClassNotFoundException`) in production environments (Modrinth App, Prism Launcher, vanilla launcher).
  - Relies strictly on Fabric Loader's native metadata dependency system (`fabric.mod.json`) and direct bytecode classloading.
  - All test suites and builds verified 100% green.

### [BL-BCH-002] Validate dasik-library Minimum Version Constraint and Platform Metadata (GitHub Issue #1)
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Target Version**: `All Anchors`
- **Status**: `✅ RESOLVED`
- **Date Added**: 2026-10-11
- **Date Resolved**: 2026-10-12
- **GitHub Reference**: [Issue #1](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/issues/1)
- **Resolution**:
  - Aligned `fabric.mod.json` `dasik-library` dependency bounds to `>=1.9.0` for Modern anchors (`26.1.2`, `26.2`, `26.3`) and `>=1.1.0` for Older anchors (`1.20.1`, `1.21.1`, `1.21.11`).
  - Parameterized subproject build scripts to use `dasik_library_version` from `gradle.properties`.
  - Aligned `MASTER_RELEASE_QUEUE.md` and subproject `RELEASE_QUEUE.md` queues across all anchors.
  - Eliminated dependency resolution collisions reported in GitHub Issue #1.

---

## [Historical Archival] - 2026-10-01

### [BL-BCH-001] Downstream Toolchain Alignment & Dasik Library Wiring (Initial Multi-Era Parity)
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Status**: `✅ RESOLVED`
- **Date Resolved**: 2026-10-01
- **Resolution**:
  - Multi-era anchor scaffolding across MC 1.20.1, MC 1.21.1, MC 1.21.11, MC 26.1.2, MC 26.2, and MC 26.3.
  - Standardized Dasik Library integration and clean sweep.
