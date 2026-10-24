# 🎫 [BL-BCH-005]: Optional Sleep Vignette Suppression with True Sleep Collision Avoidance & Mod Menu Config GUI

> **Status**: `✅ RESOLVED`  
> **Priority**: `[HIGH]`  
> **Category**: `[FEATURE]`  
> **Target Version**: `1.1.0+26.3` (Lead Anchor)  
> **Target Anchors**: `MC 26.3` (Lead), cascading to `MC 26.2`, `MC 26.1.2`, `MC 1.21.11`, `MC 1.21.1`, `MC 1.20.1`  
> **Estimated Difficulty**: `Normal`  

---

## 1. Problem Statement & Motivation
Player inquiries revealed that players desire the option to suppress the dark sleep vignette directly in Bed Chat Hider when playing without companion mods. Currently, Bed Chat Hider has zero configuration files and no Mod Menu GUI integration. Furthermore, if sleep vignette suppression is introduced into Bed Chat Hider, it must be architected to detect companion mod **Vanilla Outsider: True Sleep** (`vanilla-outsider-true-sleep`) to prevent duplicate mixin interventions or configuration ambiguity.

---

## 2. Technical Blueprint & Architecture
1. **Configuration System (`BedChatHiderConfig`)**:
   - Location: `.minecraft/config/bedchathider.json`.
   - Fields:
     - `@ConfigOption public static boolean suppressSleepVignette = false;`
     - `@ConfigOption public static boolean autoHideChat = false;`
   - Auto-load on client startup (`BedChatHiderClient.onInitializeClient()`), auto-save on change.
2. **Mod Menu Entrypoint (`BedChatHiderModMenu`)**:
   - Implements `com.terraformersmc.modmenu.api.ModMenuApi`.
   - Factory: `UniversalConfigScreen.createFactory("vanilla-outsider-bed-chat-hider", BedChatHiderConfig.class, BedChatHiderConfig::save)`.
   - Graceful fallback: If YACL is absent, `UniversalConfigScreen` yields `parent -> null` safely via `YaclSafetyBridge`.
3. **Collision Avoidance Helper (`BedChatHiderSupport`)**:
   - `isTrueSleepLoaded()`: checks `FabricLoader.getInstance().isModLoaded("vanilla-outsider-true-sleep")`.
   - `shouldSuppressSleepVignette()`: yields `false` if True Sleep is loaded (True Sleep owns vignette cancellation); otherwise yields `BedChatHiderConfig.suppressSleepVignette`.
4. **Bytecode Hook (`HudMixin`)**:
   - Mixin target: `net.minecraft.client.gui.Hud`.
   - Injects into `extractSleepOverlay(GuiGraphicsExtractor, DeltaTracker, CallbackInfo)` at `HEAD` (cancellable = true).
   - Cancels overlay if `BedChatHiderSupport.shouldSuppressSleepVignette()` returns true.
5. **Localization (`en_us.json`)**:
   - Add translation keys for config title, options, and True Sleep awareness tooltips.

---

## 3. Verification & Acceptance Criteria
* [x] Headless unit & sidedness tests pass (`./gradlew test`).
* [x] Mod Menu registers gear icon when ModMenu & YACL are present.
* [x] When `suppressSleepVignette` is true and True Sleep is NOT loaded, sleep darkening is canceled.
* [x] When True Sleep IS loaded, Bed Chat Hider defers vignette ownership to True Sleep without collision.
* [x] Config persists accurately across game restarts in `bedchathider.json`.
