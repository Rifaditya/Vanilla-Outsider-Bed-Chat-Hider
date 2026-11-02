# 🎫 [BL-BCH-008] Storefront Reconciliation: Align Mod Pages with Current Public Release

- **Ticket ID**: `[BL-BCH-008]`
- **Category**: `[GOVERNANCE]`
- **Priority**: `[HIGH]`
- **Scope**: `Platform Documentation & Metadata`
- **Status**: `✅ RESOLVED`
- **Target Repository / Root**: `Vanilla Outsider Collections/Bed Chat Hider`
- **Resolved Date**: 2026-11-02

---

## 🎯 1. Goal & Definition of Done (The 4-Pillar Objective Architecture)

1. **North Star Objective**: Reconcile Modrinth and CurseForge storefront descriptions to reflect 100% ground-truth features of the current public release (Modrinth & CurseForge `1.1.0+26.3` / `1.1.0+1.21.11` / `1.1.0+1.21.1` / `1.1.0+1.20.1`), strictly purging unreleased, in-development, or early-access mechanics.
2. **In-Game Experience (Before vs. After)**:
   - *Before*: Players reading Modrinth and CurseForge descriptions encountered stale claims, conflicting badges ("Zero Dependencies" vs "Requires Fabric API"), corrupted form feed control characters, and risks of advertising unreleased upcoming features.
   - *After*: Storefronts present 100% ground-truth features matching the published binary that players download from Modrinth and CurseForge, with verified dependencies (Fabric API & Dasik Library), in-memory session toggle, and optional ModMenu/YACL config options (`suppress_sleep_vignette`, `auto_hide_chat`).
3. **🚫 Anti-Goals (Off-Limits / Do Not Touch)**:
   - ❌ Do not document unreleased features from in-development or early-access builds (e.g. Cinematic Sleep HUD auto-fade from 1.2.0, F1 mode suppression from 1.1.1, rolling 26.x from 1.2.1); purge any unreleased claims.
   - ❌ Do not use corporate "we" voice in platform documentation; enforce the Solo Indie Voice ("I") invariant.
   - ❌ Do not modify Java source code or bump SemVer within this ticket.
4. **✅ Definition of Done (Binary Verification Checklist)**:
   - [x] Modrinth description (`Doc/Platform Pages/Modrinth/description.md`) aligned with public version.
   - [x] CurseForge markdown (`Doc/Platform Pages/CurseForge/description.md`) aligned with public version.
   - [x] CurseForge semantic HTML (`Doc/Platform Pages/CurseForge/description.html`) updated with valid semantic tags.
   - [x] Zero unreleased or hallucinated mechanics claimed in storefronts.
   - [x] Modrinth project description updated live via publisher API (`HTTP 204`).
   - [x] External project links synchronized via publisher API.

---

## ❓ 2. Problem Statement & Player Impact

Item 1.25 of the Studio Constitution (*Storefront Factual Accuracy & Anti-Hallucination Invariant*) establishes that storefronts (Modrinth/CurseForge) are binding representations. False advertising of unreleased or early-access mechanics erodes player trust, creates misleading gameplay expectations, and results in invalid bug reports from players who downloaded published releases.

---

## 🔬 3. Architectural Blueprint & Target Documentation Files

- **Target Documentation Files**:
  - `Doc/Platform Pages/Modrinth/description.md`
  - `Doc/Platform Pages/CurseForge/description.md`
  - `Doc/Platform Pages/CurseForge/description.html`
  - `ARCHITECTURE.md`
- **Target Repository**: `Vanilla Outsider Collections/Bed Chat Hider`
- **Live Public Releases**:
  - Modrinth: `1.1.0+26.3` / `1.1.0+1.21.11` / `1.1.0+1.21.1` / `1.1.0+1.20.1`
  - CurseForge: `1.1.0+26.3` / `1.1.0+1.21.11` / `1.1.0+1.21.1` / `1.1.0+1.20.1`

---

## ⚓ 4. Multi-Era Anchor Parity Specifications

- Storefront documentation uniformly reflects the supported public version anchors:
  - MC 1.20.1 (Legacy Immortal Anchor)
  - MC 1.21.1 (Tricky Trials Early Anchor)
  - MC 1.21.11 (Winter Drop Transitional Anchor)
  - MC 26.1.2, 26.2, 26.3+ (Modern Sovereign Anchors)

---

## 🛡️ 5. Edge Cases, Collisions & Failure Modes

- **CurseForge HTML Compatibility**: Storefront HTML uses valid semantic tags (`<h3>`, `<p>`, `<ul>`, `<code>`, `<blockquote>`, `<table>`) without prohibited JavaScript or invalid raw iframes.
- **Solo Indie Voice ("I" Voice)**: Comms and descriptions use first-person singular ("I built this mod", "my mod"), never corporate "we".
- **Zero Unreleased Mechanics**: Verified that queued/in-development features (`enableCinematicSleepFade`, `cinematicFadeDelaySeconds`, `fadeCrosshairWhenSleeping` from 1.2.0+, and F1 mode suppression from 1.1.1+) are strictly excluded from published platform pages.

---

## 🚀 6. Implementation Steps (Execution Plan)

1. Audited existing `Doc/Platform Pages` documents against live public release binaries (1.1.0 across anchors).
2. Purged corrupted control characters, conflicting dependency badges, and unreleased feature claims.
3. Updated Modrinth markdown documentation to match verified public release mechanics (`description.md`).
4. Updated CurseForge markdown documentation and regenerated clean semantic CurseForge HTML (`description.html`).
5. Overhauled `ARCHITECTURE.md` with accurate entrypoints, mixin tables, and config parameters.
6. Pushed live description update and link synchronization to Modrinth API via `platform_publisher.py`.

---

## 🏁 7. 5-Pillar Verification Checklist

- [x] **Pillar 1 (Source & Binary Parity)**: 100% of features claimed in platform descriptions exist in public release binaries (1.1.0).
- [x] **Pillar 2 (Zero-Hallucination Guard)**: Zero unreleased, early-access, or phantom mechanics remain across storefront docs (1.2.0 features excluded).
- [x] **Pillar 3 (Brigadier AST Alignment)**: N/A - Client-only mod with 0 commands.
- [x] **Pillar 4 (GameTest & Config Parity)**: Documented config keys (`suppress_sleep_vignette`, `auto_hide_chat`) and defaults match public release.
- [x] **Pillar 5 (Sidedness & Platform Hygiene)**: Solo indie voice ("I") verified across all documents; CurseForge HTML valid semantic markup.
