# 📌 Bed Chat Hider Backlog

This file tracks planned features, technical refinements, performance optimizations, and deferred bug fixes for **Bed Chat Hider**.

---

## 📊 Backlog Summary

| ID | Category | Title | Priority | Target Version | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `[BL-BCH-004]` | `[TECH_DEBT]` | Adopt 5-Pillar Testing & Sidedness Verification Harness | `[MEDIUM]` | All Anchors | 📌 DEFERRED |

---

## 🏷 Legend & Status Tags
- **Categories**: `[FEATURE]`, `[REFINEMENT]`, `[BUGFIX]`, `[PERF]`, `[TECH_DEBT]`
- **Priorities**: `[HIGH]` (Important logic fix/enhancement), `[MEDIUM]` (Quality of life), `[LOW]` (Minor polish)
- **Statuses**: `📌 DEFERRED` (Queued for future work), `🚧 IN_PROGRESS` (Active development), `✅ RESOLVED` (Implemented and verified)

---

## 📝 Detailed Backlog Entries

### [BL-BCH-004] Adopt 5-Pillar Testing & Sidedness Verification Harness
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Status**: `📌 DEFERRED`
- **Target Version**: `All Anchors`

#### ❓ Problem / Context
Citing `Testing & Runtime Verification Policy.md` and `gradle-tester/SKILL.md`, existing tests do not fully guard against the 5 Core Failure Modes (Headless Sidedness, Dynamic Mixin Transformation Drift, Emergent State, Concurrency, and Test Oracle limitations).

#### 💡 Proposed Solution
1. **Pillar 1**: Implement `BedChatHiderStressFuzzTest.java` (chaos property fuzzing with 10k-50k iterations, NaN/Infinity bounds).
2. **Pillar 2**: Implement `BedChatHiderConcurrencyLoadTest.java` (50+ worker threads, atomic quotas).
3. **Pillar 3**: Implement `BedChatHiderCommandTest.java` (Brigadier command tree syntax and permission checks).
4. **Pillar 4**: Implement `BedChatHiderGameTest.java` (Mojang headless in-engine world ticking).
5. **Pillar 5**: Implement `BedChatHiderSidednessContractTest.java` (bytecode scan verifying zero `net.minecraft.client.*` imports in common packages).

#### 🧪 Verification & Acceptance Criteria
- [ ] 5-Pillar test suite passes on `./gradlew test --no-daemon`.
- [ ] Zero client class references in common code.
- [ ] Clean build and deploy across all targeted anchors.
