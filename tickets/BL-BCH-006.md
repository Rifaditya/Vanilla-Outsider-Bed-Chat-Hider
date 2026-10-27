# 🎫 [BL-BCH-006] InBedChatScreen F1 Mode Visibility Suppression (F1 Honor Guard)

- **Ticket ID**: `[BL-BCH-006]`
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Scope**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1`, `26.2`, `26.3+`)
- **Status**: `✅ RESOLVED`
- **Date Added**: `2026-10-25`
- **Date Resolved**: `2026-10-27`
- **Target Repository / Root**: `Vanilla Outsider Collections\Bed Chat Hider`

---

## 🎯 1. Problem Statement & Player Impact
In vanilla Minecraft and Bed Chat Hider, pressing `F1` toggles `options.hideGui = true`, which successfully suppresses the standard in-game HUD (`Gui.render` / hotbar, health, hunger, status effects, and crosshair). However, [`InBedChatScreen`](src/main/java/net/vanillaoutsider/bedchathider/mixin/InBedChatScreenMixin.java) is a GUI `Screen` instance (`this.screen`), which Minecraft's main rendering loop unconditionally renders regardless of the `hideGui` state.

Consequently, when players in bed toggle `F1` hoping to capture clean screenshots or enjoy the sunrise/nighttime celestial time-lapse, the `[ Leave Bed ]` button and Bed Chat Hider's docked `[ Show Chat ]` / `[ Hide Chat ]` toggle button remain prominently rendered at the bottom of the screen. Suppressing `InBedChatScreen` during `hideGui == true` restores vanilla F1 parity and delivers a 100% clean, unobstructed viewport.

---

## 🔬 2. Architectural Blueprint & Target Classes
- **Target Mixin**: `net.vanillaoutsider.bedchathider.mixin.ChatScreenMixin`
- **Decompiled Source Reality**: `InBedChatScreen` inherits `render` / `extractRenderState` from `ChatScreen` and does NOT override them. Hooking `InBedChatScreen` directly results in `InvalidMixinException` (method not found). Therefore, the interception cleanly resides in `ChatScreenMixin`, where `(Object) this instanceof InBedChatScreen` rendering is already governed.
- **Bytecode Interception**:
  - **Sovereign Modern Anchors (MC 26.1 - 26.3+)**:
    ```java
    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void onExtractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if ((Object) this instanceof InBedChatScreen) {
            if (this.minecraft != null && this.minecraft.options != null && this.minecraft.options.hideGui) {
                ci.cancel();
                return;
            }
            if (BedChatHiderClient.hideChat) {
                super.extractRenderState(graphics, mouseX, mouseY, a);
                ci.cancel();
            }
        }
    }
    ```
  - **Older Anchors (MC 1.20.1 - 1.21.11)**:
    ```java
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(GuiGraphics graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if ((Object) this instanceof InBedChatScreen) {
            if (this.minecraft != null && this.minecraft.options != null && this.minecraft.options.hideGui) {
                ci.cancel();
                return;
            }
            if (BedChatHiderClient.hideChat) {
                super.render(graphics, mouseX, mouseY, delta);
                ci.cancel();
            }
        }
    }
    ```
- **Input Pass-Through Safety**:
  - In F1 mode, keyboard shortcuts (`F1` to toggle back, `F2` for screenshot, `Escape` to close/leave) must continue to function normally. Canceling only the render pass guarantees that keyboard event routing remains intact.

---

## ⚓ 3. Multi-Era Anchor Parity Specifications
- **MC 1.20.1 (Java 17, Mojang mappings)**: `render(GuiGraphics, int, int, float)` at `HEAD`.
- **MC 1.21.1 / 1.21.11 (Java 21, Mojang mappings)**: `render(GuiGraphics, int, int, float)` at `HEAD`.
- **MC 26.1 / 26.2 / 26.3+ Sovereign Lead (Java 25, Modern Render Pipeline)**: `extractRenderState(GuiGraphicsExtractor, int, int, float)` at `HEAD`.

---

## 🛡️ 4. Edge Cases, Collisions & Failure Modes
- **F1 Toggled While Chat Input Has Focus**: If the player toggles F1 while typing in bed chat, the screen render is suppressed; toggling F1 again immediately restores the screen with the typed draft preserved.
- **Escape Key Press While Hidden**: Hitting `Escape` while in F1 mode triggers `onClose()`, allowing the player to leave bed cleanly even if buttons are visually suppressed.
- **Null Safety**: All checks must guard against `this.minecraft == null` and `this.minecraft.options == null`.

---

## 🚀 5. Implementation Steps (Code-Level Plan)
1. In `net.vanillaoutsider.bedchathider.mixin.ChatScreenMixin`, extend the existing `@Inject` targeting `HEAD` of the primary render method (`render` or `extractRenderState`) to check `if (this.minecraft != null && this.minecraft.options != null && this.minecraft.options.hideGui) { ci.cancel(); return; }`.
2. Add Pillar 3 and 4 headless assertion tests verifying that when `hideGui == true`, the screen render is suppressed, while key events and draft state remain resilient.
3. Verify headless test compilation across all anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`).
4. Validate in-game that pressing `F1` in bed removes both `[ Leave Bed ]` and `[ Show Chat ]` buttons without blocking `Escape` to wake up.

---

## 🏁 6. 5-Pillar Verification Checklist
- [x] **Pillar 1 (Chaos Fuzzing)**: 45,000 iterations of rapid F1 toggle and screen lifecycle fuzzing pass with zero NPEs (`BedChatHiderStressFuzzTest`).
- [x] **Pillar 2 (Concurrency Load)**: 50 virtual worker threads stressing UI option reads without deadlocks (`BedChatHiderConcurrencyLoadTest`).
- [x] **Pillar 3 (Headless Screen State AST)**: Automated headless test asserting `ChatScreenMixin` cancels render at `HEAD` when `options.hideGui == true` / `gui.hud.isHidden()`.
- [x] **Pillar 4 (Client State Simulation)**: Headless simulation asserting key event pass-through, draft preservation, and wakeup handling while hidden (`BedChatHiderSleepSimulationTest`).
- [x] **Pillar 5 (Sidedness & License Hygiene)**: 100% client-side isolation, zero server classes, single-line GPLv3 headers verified (`BedChatHiderSidednessContractTest`).

---

## 🏆 7. Resolution Summary
- Implemented F1 Honor Guard suppression in `ChatScreenMixin.java` targeting `(Object) this instanceof InBedChatScreen` across all 6 Minecraft version anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`).
- Seamlessly handled API divergence: `options.hideGui` for 1.20.1–26.1.2; `gui.hud.isHidden()` for modern snapshot 26.2/26.3+ (`[PAT-20261027-001]`).
- All 6 anchors verified with 100% headless test suites passing. Release JARs built at SemVer `1.1.1` and distributed across all 4 distribution points.
