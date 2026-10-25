# 🎫 [BL-BCH-006] InBedChatScreen F1 Mode Visibility Suppression (F1 Honor Guard)

- **Ticket ID**: `[BL-BCH-006]`
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Scope**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1`, `26.2`, `26.3+`)
- **Status**: `📌 DEFERRED`
- **Date Added**: `2026-10-25`
- **Target Repository / Root**: `Vanilla Outsider Collections\Bed Chat Hider`

---

## 🎯 1. Problem Statement & Player Impact
In vanilla Minecraft and Bed Chat Hider, pressing `F1` toggles `options.hideGui = true`, which successfully suppresses the standard in-game HUD (`Gui.render` / hotbar, health, hunger, status effects, and crosshair). However, [`InBedChatScreen`](src/main/java/net/vanillaoutsider/bedchathider/mixin/InBedChatScreenMixin.java) is a GUI `Screen` instance (`this.screen`), which Minecraft's main rendering loop unconditionally renders regardless of the `hideGui` state.

Consequently, when players in bed toggle `F1` hoping to capture clean screenshots or enjoy the sunrise/nighttime celestial time-lapse, the `[ Leave Bed ]` button and Bed Chat Hider's docked `[ Show Chat ]` / `[ Hide Chat ]` toggle button remain prominently rendered at the bottom of the screen. Suppressing `InBedChatScreen` during `hideGui == true` restores vanilla F1 parity and delivers a 100% clean, unobstructed viewport.

---

## 🔬 2. Architectural Blueprint & Target Classes
- **Target Mixin**: `net.vanillaoutsider.bedchathider.mixin.InBedChatScreenMixin`
- **Bytecode Interception**:
  - **Sovereign Modern Anchors (MC 26.1 - 26.3+)**:
    ```java
    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void bedchathider$suppressF1Render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (this.minecraft != null && this.minecraft.options.hideGui) {
            ci.cancel();
        }
    }
    ```
  - **Older Anchors (MC 1.20.1 - 1.21.11)**:
    ```java
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void bedchathider$suppressF1Render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (this.minecraft != null && this.minecraft.options.hideGui) {
            ci.cancel();
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
1. In `net.vanillaoutsider.bedchathider.mixin.InBedChatScreenMixin`, add an `@Inject` targeting `HEAD` of the primary render method (`render` or `extractRenderState`) with `cancellable = true`.
2. Guard the injection with `if (this.minecraft != null && this.minecraft.options.hideGui) { ci.cancel(); }`.
3. Verify headless test compilation across all anchors (`1.20.1`, `1.21.1`, `1.21.11`, `26.1.2`, `26.2`, `26.3`).
4. Validate in-game that pressing `F1` in bed removes both `[ Leave Bed ]` and `[ Show Chat ]` buttons.

---

## 🏁 6. 5-Pillar Verification Checklist
- [ ] **Pillar 1 (Chaos Fuzzing)**: 45,000 iterations of rapid F1 toggle and screen lifecycle fuzzing pass with zero NPEs.
- [ ] **Pillar 2 (Concurrency Load)**: 50 virtual worker threads stressing UI option reads without deadlocks.
- [ ] **Pillar 3 (Headless Screen State AST)**: Automated headless test asserting `InBedChatScreen` cancels render when `options.hideGui == true`.
- [ ] **Pillar 4 (Client State Simulation)**: Headless simulation asserting key event pass-through while hidden.
- [ ] **Pillar 5 (Sidedness & License Hygiene)**: 100% client-side isolation, zero server classes, single-line GPLv3 headers.
