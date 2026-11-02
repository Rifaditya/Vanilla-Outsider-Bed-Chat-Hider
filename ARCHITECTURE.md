# Architecture & Symbol Index: Vanilla Outsider: Bed Chat Hider

## 1. Mod Metadata & Entrypoints
- **Mod ID**: `vanilla-outsider-bed-chat-hider`
- **Client Entrypoint**: `net.vanillaoutsider.bedchathider.BedChatHiderClient` (`net.fabricmc.api.ClientModInitializer`)
- **ModMenu Entrypoint**: `net.vanillaoutsider.bedchathider.config.BedChatHiderModMenu` (`com.terraformersmc.modmenu.api.ModMenuApi`)
- **Environment**: Client-side sovereign mod (zero server dependency; safe to connect to any server).

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Injection Point | Purpose |
| :--- | :--- | :--- | :--- |
| `net.minecraft.client.gui.screens.ChatScreen` | `net.vanillaoutsider.bedchathider.mixin.ChatScreenMixin` | `extractRenderState`, `keyPressed`, `mouseClicked`, `mouseScrolled` | Suppresses rendering and blocks input/clicking/scrolling when bed chat is hidden |
| `net.minecraft.client.gui.screens.InBedChatScreen` | `net.vanillaoutsider.bedchathider.mixin.InBedChatScreenMixin` | `init` | Adjusts `leaveBedButton` layout, adds "Hide Chat" / "Show Chat" toggle button |

## 3. Core Mechanics & Subsystems
- **Session State**: `BedChatHiderClient.hideChat` (in-memory boolean persisting toggle state across beds during the game session).
- **Companion Inter-Mod Support**: `BedChatHiderSupport` checks `isTrueSleepLoaded()` to allow Vanilla Outsider: True Sleep to manage sleep darkening without visual collision.
- **Client Sidedness**: 100% client-side architecture; zero network packets or server-side classes.

## 4. Configuration & Storage
- **Config Class**: `net.vanillaoutsider.bedchathider.config.BedChatHiderConfig`
- **Config Path**: `.minecraft/config/bedchathider.json`
- **Options**:
  - `suppressSleepVignette` (boolean, default: `false`): Eliminates the dark sleeping vignette overlay when resting in a bed.
  - `autoHideChat` (boolean, default: `false`): Automatically hides the chat overlay upon entering any bed.
- **GUI Screen**: Optional ModMenu and YetAnotherConfigLib (YACL) support via `BedChatHiderModMenu` and Dasik Library's `UniversalConfigScreen`.
