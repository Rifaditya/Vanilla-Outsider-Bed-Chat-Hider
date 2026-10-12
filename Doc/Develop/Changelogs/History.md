# 📜 Permanent Developer Ledger & Backlog History: Bed Chat Hider

All technical tickets, architectural decisions, and resolved backlog items are permanently archived here.

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
