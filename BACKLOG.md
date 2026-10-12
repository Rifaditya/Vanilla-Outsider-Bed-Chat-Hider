# 📌 Bed Chat Hider Backlog

This file tracks planned features, technical refinements, performance optimizations, and deferred bug fixes for **Bed Chat Hider**.

---

## 📊 Backlog Summary

| ID | Category | Title | Priority | Target Version | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `[BL-BCH-002]` | `[TECH_DEBT]` | Validate dasik-library Minimum Version Constraint and Platform Metadata (GitHub Issue #1) | `[MEDIUM]` | `All Anchors` | `📌 DEFERRED` |
| `[BL-BCH-003]` | `[BUGFIX]` | Purge ModVersionGuard String Reflection Runtime Hazard Across All Anchors | `[HIGH]` | `All Anchors` | `📌 DEFERRED` |

---

## 🏷 Legend & Status Tags
- **Categories**: `[FEATURE]`, `[REFINEMENT]`, `[BUGFIX]`, `[PERF]`, `[TECH_DEBT]`
- **Priorities**: `[HIGH]` (Important logic fix/enhancement), `[MEDIUM]` (Quality of life), `[LOW]` (Minor polish)
- **Statuses**: `📌 DEFERRED` (Queued for future work), `🚧 IN_PROGRESS` (Active development), `✅ RESOLVED` (Implemented and verified)

---

## 📝 Detailed Backlog Entries

### [BL-BCH-002] Validate dasik-library Minimum Version Constraint and Platform Metadata (GitHub Issue #1)
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Status**: `📌 DEFERRED`
- **Target Component(s)**: `fabric.mod.json` across all version anchors, `platform_projects.json`
- **Date Added**: 2026-10-11
- **GitHub Reference**: [Issue #1](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/issues/1)

#### ❓ Problem / Context
In [GitHub Issue #1](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/issues/1), a user reported a dependency collision when launching via Modrinth:
```
Mod 'Vanilla Outsider: Bed Chat Hider' 1.0.5+26.3 requires version 1.9.0 or later of mod 'Dasik Library', but only the wrong version is present: 1.8.38!
```
While the author clarified that `dasik-library` is a mandatory dependency, users installing via launchers can encounter conflicts if the minimum version declared in `fabric.mod.json` is ahead of what launcher dependency resolvers auto-fetch or if older library builds are retained in launcher instances.

#### 💡 Proposed Solution & Technical Specifications
1. Audit `fabric.mod.json` across all subproject anchors (`26.3`, `26.2`, `26.1.2`, `1.21.11`, `1.21.1`, `1.20.1`) to ensure declared dependency bounds (`>=1.8.x` vs `>=1.9.0`) accurately match published and accessible builds of `dasik-library`.
2. Verify Modrinth and CurseForge metadata relations so that installing Bed Chat Hider automatically downloads the correct matching `dasik-library` version.
3. Align `MASTER_RELEASE_QUEUE.md` to reflect live versions (`1.0.0+1.20.1`, `1.0.0+1.21.1`, `1.0.0+1.21.11`, `1.0.5+26.3`).
4. Add a clear, non-intrusive note in mod descriptions about the required library build.

#### 🧪 Verification & Acceptance Criteria
- [ ] `fabric.mod.json` dependency versions accurately match published library releases across all anchors.
- [ ] Installing via Modrinth launcher auto-resolves compatible `dasik-library` builds without dependency errors.
- [ ] Clean test and build pass across all anchors.

---

### [BL-BCH-003] Purge ModVersionGuard String Reflection Runtime Hazard Across All Anchors
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Status**: `📌 DEFERRED`
- **Target Component(s)**: `BedChatHiderClient.java` across all 6 anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`)
- **Date Added**: 2026-10-12

#### ❓ Problem / Context
In production Fabric environments using Knot classloading (Modrinth App, Prism Launcher, vanilla launcher), vanilla Minecraft classes are remapped to Intermediary at runtime (e.g., `net.minecraft.class_408` for `ChatScreen`).
In `BedChatHiderClient.java`, `ModVersionGuard.checkClass("Bed Chat Hider", "net.minecraft.client.gui.screens.ChatScreen")` executes `Class.forName(String)`.
Because string literals are not remapped by Fabric Loom, `Class.forName` fails with `ClassNotFoundException` at runtime in production environments, throwing a fatal `RuntimeException` ("Minecraft API Mismatch!") and crashing the client during startup. This violates the **Anti-Unremapped-Reflection Law (Knot ClassLoader Runtime Safety)** (`Studio Constitution.md`).

#### 💡 Proposed Solution & Technical Specifications
1. Remove `ModVersionGuard.checkClass(...)` invocation from `BedChatHiderClient.java` across all 6 version anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`).
2. Remove the unused import `net.dasik.social.util.ModVersionGuard`.
3. Rely on Fabric Loader's native metadata dependency system (`fabric.mod.json`) and direct bytecode classloading.
4. Verify that legacy root `src/` is either updated or kept clean of unremapped reflection.

#### 🧪 Verification & Acceptance Criteria
- [ ] `ModVersionGuard.checkClass` is completely eliminated from `BedChatHiderClient.java` across all version anchors.
- [ ] Zero unremapped `Class.forName` calls remain in the codebase.
- [ ] All 6 subproject anchors pass `./gradlew test --console=plain` and `./gradlew build`.
- [ ] Zero Knot ClassLoader runtime crash hazards remain in production environments.
