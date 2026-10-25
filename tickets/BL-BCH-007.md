# 🎫 [BL-BCH-007] Cinematic Sleep HUD: Idle Mouse Inactivity Auto-Fade for Bed Buttons & Crosshair

- **Ticket ID**: `[BL-BCH-007]`
- **Category**: `[FEATURE]`
- **Priority**: `[HIGH]`
- **Scope**: `All Anchors` (`1.20.1`, `1.21.1`, `1.21.11`, `26.1`, `26.2`, `26.3+`)
- **Status**: `📌 DEFERRED`
- **Date Added**: `2026-10-25`
- **Target Repository / Root**: `Vanilla Outsider Collections\Bed Chat Hider`

---

## 🎯 1. Problem Statement & Player Impact
Community feedback from player `mybox` on Discord highlighted that when playing with **True Sleep** (or Bed Chat Hider with vignette suppression enabled) at relaxed or extended sleep speeds, players often spend 1–2 minutes resting in bed to watch the panoramic sunrise, celestial movements, and morning twilight. 

In standard gameplay without F1 mode, static UI buttons (`[ Leave Bed ]`, `[ Show Chat ]` / `[ Hide Chat ]`), active chat input boxes, and the center crosshair remain permanently visible in the viewport. Toggling `F1` manually can feel disruptive and prevents players from seeing when someone chats. 

A natural, hands-off **"Cinematic Sleep HUD"** mode where UI clutter smoothly fades out after a few seconds of idle stillness—and instantly returns upon mouse movement or keypress—provides an immersive time-lapse view without sacrificing instant interactivity.

---

## 🔬 2. Architectural Blueprint & Target Classes
1. **Inactivity Controller (`CinematicSleepController.java`)**:
   - Location: `net.vanillaoutsider.bedchathider.client.CinematicSleepController`.
   - Tracks:
     - `lastInteractionTimeMs`: timestamp of last mouse cursor movement or keypress.
     - `currentAlpha`: smooth float transition ($0.0 \dots 1.0$) using linear or ease-out interpolation.
   - Core API:
     - `void onInteraction()`: resets idle timestamp and immediately snaps or quickly eases alpha back to `1.0f`.
     - `void tick(float delta)`: calculates elapsed idle time; if `elapsed > idleTimeout`, decays alpha toward `0.0f`.
     - `float getAlpha()`: current rendering opacity factor.
     - `boolean isFullyHidden()`: returns `currentAlpha <= 0.01f`.

2. **GUI Hook (`InBedChatScreenMixin.java`)**:
   - Intercept mouse motion (`mouseMoved`), mouse clicks (`mouseClicked`), and key presses (`keyPressed`) to invoke `CinematicSleepController.onInteraction()`.
   - In render pass:
     - Apply `CinematicSleepController.getAlpha()` to renderable widgets (`leaveBedButton`, toggle button, chat box).
     - If `CinematicSleepController.isFullyHidden()`, skip rendering widgets entirely.

3. **Crosshair & HUD Hook (`HudMixin.java`)**:
   - Mixin target: `net.minecraft.client.gui.Hud` / `net.minecraft.client.gui.Gui`.
   - In crosshair render method (`renderCrosshair` / `extractCrosshair`):
     - Suppress crosshair rendering if the player is sleeping (`player.isSleeping()`) and `CinematicSleepController.isFullyHidden()` or config flag `fadeCrosshair` is enabled.

4. **Configuration (`BedChatHiderConfig.java`)**:
   - `@ConfigOption public static boolean enableCinematicSleepFade = true;`
   - `@ConfigOption public static float cinematicFadeDelaySeconds = 2.5f;`
   - `@ConfigOption public static boolean fadeCrosshairWhenSleeping = true;`
   - Config persistence via `bedchathider.json` and Mod Menu YACL GUI integration.

---

## ⚓ 3. Multi-Era Anchor Parity Specifications
- **MC 1.20.1 (Java 17, Mojang mappings)**: `GuiGraphics` rendering, `renderCrosshair(GuiGraphics)`.
- **MC 1.21.1 / 1.21.11 (Java 21, Mojang mappings)**: `GuiGraphics` rendering, `renderCrosshair(GuiGraphics, DeltaTracker)`.
- **MC 26.1 / 26.2 / 26.3+ Sovereign Lead (Java 25, Modern Pipeline)**: `GuiGraphicsExtractor`, `extractCrosshair(GuiGraphicsExtractor, DeltaTracker)`.

---

## 🛡️ 4. Edge Cases, Collisions & Failure Modes
- **Mouse Optical Sensor Drift**: Microscopic mouse drift (sub-pixel sensor noise) should not disrupt fade-out. Apply a deadzone threshold (e.g. cumulative cursor delta $> 2.0$ pixels) before registering an interaction.
- **Immediate Click Guarantee**: If a player clicks their mouse while the buttons are completely invisible, the click must immediately wake up the UI and reveal the buttons rather than triggering unintended background clicks.
- **Incoming Chat Messages**: If a chat message arrives while the player is sleeping with chat visible, option to temporarily wake the UI or maintain idle state.
- **Accessibility & Anti-Frustration**: Provide an instant off-switch in configuration (`enableCinematicSleepFade = false`) for players who prefer traditional persistent UI buttons.

---

## 🚀 5. Implementation Steps (Code-Level Plan)
1. Author `net.vanillaoutsider.bedchathider.client.CinematicSleepController.java` with deadzone drift filtering and delta-time alpha interpolation.
2. In `InBedChatScreenMixin`, forward mouse movement and keypress events to `CinematicSleepController.onInteraction()`.
3. In `InBedChatScreenMixin.render` / `extractRenderState`, modulate button and widget alpha using `CinematicSleepController.getAlpha()`.
4. In `HudMixin`, hook crosshair rendering to suppress crosshair when sleeping and idle fade is active.
5. Register config fields in `BedChatHiderConfig.java` and localized strings in `assets/vanilla-outsider-bed-chat-hider/lang/en_us.json`.
6. Run 5-Pillar client test suite and verify across all anchors.

---

## 🏁 6. 5-Pillar Verification Checklist
- [ ] **Pillar 1 (Chaos Fuzzing)**: Fuzz `CinematicSleepController` delta ticking and alpha bounds over 50,000 randomized steps without NaN, Infinity, or out-of-range alpha values ($0.0 \le \alpha \le 1.0$).
- [ ] **Pillar 2 (Concurrency Load)**: 50 virtual worker threads polling alpha and registering interactions without race conditions.
- [ ] **Pillar 3 (Headless Screen State AST)**: Headless assertion verifying widgets render with correct alpha states based on simulated idle timer.
- [ ] **Pillar 4 (Client State Simulation)**: Headless simulation asserting mouse movement instantly restores full $1.0$ alpha.
- [ ] **Pillar 5 (Sidedness & License Hygiene)**: 100% client-isolated logic, zero server references, GPLv3 license header compliance.
