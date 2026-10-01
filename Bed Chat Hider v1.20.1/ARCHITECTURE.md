# Architecture & Symbol Index: Vanilla Outsider: Bed Chat Hider (MC 1.20.1)

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `vanilla-outsider-bed-chat-hider`
- **Main Entrypoint**: None (Client-only)
- **Client Entrypoint**: `net.vanillaoutsider.bedchathider.BedChatHiderClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `net.minecraft.client.gui.screens.ChatScreen` | `net.vanillaoutsider.bedchathider.mixin.ChatScreenMixin` | Suppresses chat rendering and input events when sleeping and chat hidden |
| `net.minecraft.client.gui.screens.InBedChatScreen` | `net.vanillaoutsider.bedchathider.mixin.InBedChatScreenMixin` | Injects Hide/Show Chat button beside Leave Bed button |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`
- **Session State**: `BedChatHiderClient.hideChat` (in-memory boolean toggle)

## 4. Dependencies & Ecosystem
- **Dasik Library**: `net.dasik.social:dasik-library:1.1.0+1.20.1` for community and creator support integration.
- **Fabric Loader**: `>=0.15.11`
- **Minecraft**: `~1.20.1`
