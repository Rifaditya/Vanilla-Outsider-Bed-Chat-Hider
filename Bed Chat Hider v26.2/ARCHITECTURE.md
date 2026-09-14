# Architecture & Symbol Index: Vanilla Outsider: Bed Chat Hider

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `vanilla-outsider-bed-chat-hider`
- **Main Entrypoint**: `` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.vanillaoutsider.bedchathider.BedChatHiderClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.vanillaoutsider.bedchathider.mixin.ChatScreenMixin` | Core mixin hook |
| `Vanilla Class` | `net.vanillaoutsider.bedchathider.mixin.InBedChatScreenMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`vanilla-outsider-bed-chat-hider:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
