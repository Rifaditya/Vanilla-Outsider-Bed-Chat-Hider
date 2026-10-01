# 📌 Bed Chat Hider Backlog

This file tracks planned features, technical refinements, performance optimizations, and deferred bug fixes for **Bed Chat Hider**.

---

## 📊 Backlog Summary

| ID | Category | Title | Priority | Target Version | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `[BL-BCH-001]` | `[TECH_DEBT]` | Update Bed Chat Hider MC 26.2 to use Dasik Library MC 26.2 | `[MEDIUM]` | `26.2` | `✅ RESOLVED` |
| `[BL-BCH-002]` | `[TECH_DEBT]` | Update Bed Chat Hider MC 26.3 to use Dasik Library MC 26.3 | `[MEDIUM]` | `26.3` | `✅ RESOLVED` |
| `[BL-BCH-003]` | `[FEATURE]` | Multi-Era Anchor Porting: Bed Chat Hider | `[HIGH]` | `Multi-Era` | `📌 DEFERRED` |

---

## 🏷 Legend & Status Tags
- **Categories**: `[FEATURE]`, `[REFINEMENT]`, `[BUGFIX]`, `[PERF]`, `[TECH_DEBT]`
- **Priorities**: `[HIGH]` (Important logic fix/enhancement), `[MEDIUM]` (Quality of life), `[LOW]` (Minor polish)
- **Statuses**: `📌 DEFERRED` (Queued for future work), `🚧 IN_PROGRESS` (Active development), `✅ RESOLVED` (Implemented and verified)

---

## 📝 Detailed Backlog Entries

### [BL-BCH-001] Update Bed Chat Hider MC 26.2 to use Dasik Library MC 26.2
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Status**: `✅ RESOLVED`
- **Target Component(s)**: `build.gradle`, `fabric.mod.json`, dynamic gamerules
- **Date Added**: 2026-09-24

#### ❓ Problem / Context
Bed Chat Hider MC 26.2 currently lacks the `dasik-library` runtime dependency.

#### 💡 Proposed Solution & Technical Specifications
- Add `dasik-library` dependency in `build.gradle` and `fabric.mod.json`.
- Wire `net.dasik.social.api.*` for dynamic gamerules and creator support links.

#### 🧪 Verification & Acceptance Criteria
- [x] `./gradlew check` / test suite passes.
- [x] Mod compiles cleanly with `./gradlew build`.
- [x] Built JAR is triple-archived.

---

### [BL-BCH-002] Update Bed Chat Hider MC 26.3 to use Dasik Library MC 26.3
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Status**: `✅ RESOLVED`
- **Target Component(s)**: `build.gradle`, `fabric.mod.json`, dynamic gamerules
- **Date Added**: 2026-09-24

#### ❓ Problem / Context
Bed Chat Hider MC 26.3 currently lacks the `dasik-library` runtime dependency.

#### 💡 Proposed Solution & Technical Specifications
- Add `dasik-library` dependency in `build.gradle` and `fabric.mod.json`.
- Wire `net.dasik.social.api.*` for dynamic gamerules and creator support links.

#### 🧪 Verification & Acceptance Criteria
- [x] `./gradlew check` / test suite passes.
- [x] Mod compiles cleanly with `./gradlew build`.
- [x] Built JAR is triple-archived.

---

### [BL-BCH-003] Multi-Era Anchor Porting: Bed Chat Hider
- **Category**: `[FEATURE]`
- **Priority**: `[HIGH]`
- **Status**: `📌 DEFERRED`
- **Target Component(s)**: Multi-subproject directories, `InBedChatScreenMixin.java`, `BedChatHiderConfig.java`, `BedChatHiderGameRules.java`, `build.gradle`, `fabric.mod.json`, `RELEASE_QUEUE.md`
- **Date Added**: 2026-09-25

#### ❓ Problem / Context
Existing versions: `26.2`, `26.3`. Missing anchors: Modern `26.1 / 26.1.2`; Older `1.21.11`, `1.21.1`, `1.20.1`.
Per Multi-Era Version Matrix and 1 Jar 1 Version Policy, port mod across all missing anchors (Modern first, Older second).

#### 💡 Architectural Specifications & Toolchain Anchors
- **Phase 1: Modern Sovereign Anchors (Java 25+, Loom 1.15+, No Mappings Block)**:
  - `MC 26.1 / 26.1.2`: Java 25, Fabric Loom 1.15+, `Identifier.fromNamespaceAndPath`, `EntityTypes`, `dasik-library` 26.1.
  - `MC 26.2`: Java 25, Fabric Loom 1.15+, `Identifier.fromNamespaceAndPath`, `DynamicGameRuleManager` (already active baseline).
  - `MC 26.3`: Java 25, Fabric Loom 1.15+, `minecraft_version=26.3-snapshot-6`, `fabric_version=0.156.1+26.3` (already active baseline).
- **Phase 2: Older Anchors (Mojang Mappings, Java 21 / 17)**:
  - `MC 1.21.11`: Java 21, Loom 1.15-SNAPSHOT (`fabric-loom-remap`), Mojang mappings, `Identifier.of`, `Optional<T>` CompoundTag, relocated entity packages.
  - `MC 1.21.1`: Java 21, Loom 1.10+, Mojang mappings, `Identifier.of`, `DataComponents`, native `Attributes.SCALE`.
  - `MC 1.20.1`: Java 17, Loom 1.4–1.10, Mojang mappings, `new Identifier`, primitive NBT CompoundTag, `FabricItemSettings`, `GameRules.Category` enum.

#### 🧪 Verification & Acceptance Criteria

##### Phase 1: Modern Sovereign Anchors (Priority 1)
- [x] **Anchor: MC 26.1 / 26.1.2**
  - [x] Subproject directory & build script scaffolding (`build.gradle`, `gradle.properties`, `settings.gradle`)
  - [x] Source adaptation, API/mixin relocation, and dasik-library wiring for target version
  - [x] Headless unit & integration test suite pass (`./gradlew test --no-daemon`)
  - [x] Clean binary compilation (`./gradlew build --no-daemon`)
  - [x] Mandatory Universal 4-Point Distribution (Local Archive, Hub Archive, External Vault `D:\`, Launcher Test Profile)
  - [x] Release queue registration in `RELEASE_QUEUE.md` (`- [ ]`) and `CHANGELOG.md` entry
- [x] **Anchor: MC 26.2 - Already established baseline** (Subproject: `Bed Chat Hider v26.2`)
- [x] **Anchor: MC 26.3 - Already established baseline** (Subproject: `Bed Chat Hider v26.3`)

##### Phase 2: Older Anchors (Priority 2)
- [x] **Anchor: MC 1.21.11 (Java 21, Loom 1.15-SNAPSHOT `fabric-loom-remap`, Mojang mappings, `Identifier.of`, `Optional<T>` CompoundTag, relocated entity packages)**
  - [x] Subproject directory & build script scaffolding (`build.gradle`, `gradle.properties`, `settings.gradle`)
  - [x] Source adaptation, API/mixin relocation, and dasik-library wiring for target version
  - [x] Headless unit & integration test suite pass (`./gradlew test --no-daemon`)
  - [x] Clean binary compilation (`./gradlew build --no-daemon`)
  - [x] Mandatory Universal 4-Point Distribution (Local Archive, Hub Archive, External Vault `D:\`, Launcher Test Profile)
  - [x] Release queue registration in `RELEASE_QUEUE.md` (`- [ ]`) and `CHANGELOG.md` entry
- [ ] **Anchor: MC 1.21.1 (Java 21, Loom 1.10+, Mojang mappings, `Identifier.of`, `DataComponents`, native `Attributes.SCALE`)**
  - [ ] Subproject directory & build script scaffolding (`build.gradle`, `gradle.properties`, `settings.gradle`)
  - [ ] Source adaptation, API/mixin relocation, and dasik-library wiring for target version
  - [ ] Headless unit & integration test suite pass (`./gradlew test --no-daemon`)
  - [ ] Clean binary compilation (`./gradlew build --no-daemon`)
  - [ ] Mandatory Universal 4-Point Distribution (Local Archive, Hub Archive, External Vault `D:\`, Launcher Test Profile)
  - [ ] Release queue registration in `RELEASE_QUEUE.md` (`- [ ]`) and `CHANGELOG.md` entry
- [ ] **Anchor: MC 1.20.1 (Java 17, Loom 1.4-1.10, Mojang mappings, `new Identifier`, primitive NBT CompoundTag, `FabricItemSettings`, `GameRules.Category` enum)**
  - [ ] Subproject directory & build script scaffolding (`build.gradle`, `gradle.properties`, `settings.gradle`)
  - [ ] Source adaptation, API/mixin relocation, and dasik-library wiring for target version
  - [ ] Headless unit & integration test suite pass (`./gradlew test --no-daemon`)
  - [ ] Clean binary compilation (`./gradlew build --no-daemon`)
  - [ ] Mandatory Universal 4-Point Distribution (Local Archive, Hub Archive, External Vault `D:\`, Launcher Test Profile)
  - [ ] Release queue registration in `RELEASE_QUEUE.md` (`- [ ]`) and `CHANGELOG.md` entry

