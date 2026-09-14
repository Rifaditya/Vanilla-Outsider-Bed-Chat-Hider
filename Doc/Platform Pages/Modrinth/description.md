<p align="center">
  <a href="https://discord.gg/EV99bgAFqb"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
  <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
  <img src="https://img.shields.io/badge/Environment-Client_Side-success?style=for-the-badge" alt="Client Side">
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# 🌙 Bed Chat Hider

> **"Rest in Peace. A Serene, Distraction-Free Sleeping HUD for Minecraft."**

---

## 📖 Introduction

In vanilla Minecraft, climbing into bed immediately covers your screen with an intrusive, opaque chat overlay, an awkward dark gray vignette, and a giant "Leave Bed" button smack in the middle of your view. If you are playing on an active multiplayer server or realm, sleeping becomes a chaotic barrage of rolling chat messages, server announcements, and spam that completely shatters your nighttime immersion. Even in singleplayer, you can't simply gaze out your bedroom window at the starry sky, moon, or nocturnal landscape while resting.

**Bed Chat Hider** restores tranquility to your nighttime slumber under the **Vanilla Outsider** design philosophy. It intelligently intercepts the client-side sleeping screen state, automatically suppressing the obtrusive chat box, background tint, and clutter the instant your head hits the pillow. With smooth alpha-fading transitions, intuitive one-key chat recall, and zero performance overhead, you can finally enjoy a peaceful, cinematic sleeping experience without missing important server communications.

> [!NOTE]
> **1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.
> 
> **Client-Side Sovereignty:** 100% client-side mod. You can install Bed Chat Hider on your client and join ANY vanilla, Fabric, Paper, Spigot, or Forge server without requiring the mod on the server!

Part of the **Vanilla Outsider Collection** — modern mods designed to refine and elevate the vanilla Minecraft experience.

---

## ✨ Features

### 🔕 Automatic Sleeping Screen Decoupling
- **Instant Overlay Suppression:** The moment your player entity enters the sleeping pose, Bed Chat Hider smoothly detaches the foreground chat rendering layer. No more walls of green text, automated server greetings, or trading chatter obscuring your screen.
- **Cinematic Horizon View:** Unlocks your field of view so you can watch rain showers, lightning strikes, passing clouds, or tranquil bedroom interior lighting while waiting for morning to arrive.
- **Zero World-Tick Impact:** Hooks directly into client GUI rendering pipeline via targeted Mixins, executing in $O(1)$ constant time with zero heap allocations on the render loop.

### ⌨️ On-Demand Chat Recall (Press Enter or T)
- **Fluid Keyboard Interception:** Need to reply to a friend while resting? Simply press `Enter` or `T` (or your custom chat keybind) to immediately reveal the chat interface and typing bar without leaving your bed.
- **Seamless Auto-Dismiss:** Once your message is sent, or when you press `Escape`, the chat interface gracefully fades back out into serenity while you remain cozy in bed.
- **No Accidental Ejections:** Prevents clumsy misclicks on the vanilla "Leave Bed" button when trying to interact with chat.

### 🎚️ Configurable HUD Ergonomics & Button Auto-Hide
- **Minimalist "Leave Bed" Button:** Configure whether the prominent vanilla button remains visible, fades out after a customizable delay (e.g. 3 seconds), or is hidden entirely in favor of the standard `Shift` sneak-to-wake mechanic.
- **Adjustable Sleep Vignette:** Soften or eliminate the harsh vanilla dark gray sleeping tint for clean, vibrant nocturnal vistas.
- **Multiplayer Sleeper Count Integration:** Plays nicely with multiplayer percentage sleeping mods (Harbor, Sleep Warp, VanillaTweaks) by keeping essential sleep progression notifications clean and readable.

---

## 📊 Feature Comparison Matrix

| Feature Dimension | Vanilla Minecraft | Bed Chat Hider |
| :--- | :--- | :--- |
| **Sleeping Screen Visibility** | Obscured by permanent chat box & scrim | **100% Clear, cinematic panoramic view** |
| **Multiplayer Chat Spam in Bed** | Covers the screen continuously | **Suppressed automatically while resting** |
| **Chat Access While Sleeping** | Permanent on-screen or forces wake | **Press `Enter`/`T` to summon, `Esc` to hide** |
| **"Leave Bed" Button Behavior** | Static, prominent screen-cluttering button | **Configurable auto-fade or hidden** |
| **Server Requirements** | N/A | **100% Client-Side (Works on all servers)** |
| **Memory / Render Heap Overhead** | Standard GUI allocation | **0 Bytes/frame clean render bypass** |

---

## ⚙️ Configuration & Ergonomics

Bed Chat Hider includes optional in-game configuration through **ModMenu** and **Cloth Config / YACL**. You can adjust settings live in singleplayer or while connected to remote multiplayer servers:

```json
{
  "auto_hide_chat": true,
  "fade_duration_ticks": 10,
  "allow_chat_toggle_in_bed": true,
  "leave_bed_button_mode": "FADE_AFTER_DELAY",
  "button_delay_seconds": 3.0,
  "suppress_sleep_vignette": false
}
```

- **`auto_hide_chat`** *(Boolean, Default: `true`)*: Automatically suppresses the chat HUD upon entering any bed.
- **`fade_duration_ticks`** *(Integer, Default: `10`, Range: `0–40`)*: Smooth alpha fade transition time between sleeping HUD states.
- **`allow_chat_toggle_in_bed`** *(Boolean, Default: `true`)*: Enables one-touch toggling of chat using `Enter` or `T`.
- **`leave_bed_button_mode`** *(Enum, Default: `FADE_AFTER_DELAY`)*: Choose between `ALWAYS_VISIBLE`, `FADE_AFTER_DELAY`, or `HIDDEN`.

---

## 📖 In-Depth How-To & Gameplay Playbook

### Step 1: Effortless Installation
1. Ensure you have **Fabric Loader** and **Fabric API** installed for your exact Minecraft version.
2. Drop `bed-chat-hider-x.y.z+<version>.jar` into your `.minecraft/mods` folder.
3. Launch Minecraft! The mod activates automatically with zero initial configuration required.

### Step 2: Sleeping on Multiplayer Servers
- When playing on multiplayer servers with high chat traffic, right-click any bed as usual.
- Notice your view is crystal clear! The chat box is hidden, allowing you to watch the night sky.
- If someone whispers you or asks a question, tap `Enter` or `T` to open chat, type your response, and hit `Enter` to send. The chat will immediately hide again.
- To wake up early, tap `Shift` (sneak) or click the "Leave Bed" button if visible.

### Step 3: Aesthetic Screenshots & Time-Lapse Recording
- Bed Chat Hider is ideal for content creators and streamers recording nocturnal timelapses or cozy cabin ambience.
- Pair with shaderpacks (Iris / Complementary / Bliss) for stunning, UI-free nighttime sleep panoramas!

---

## ☕ Support & Creator Community

I am an independent solo developer creating lightweight, vanilla-enhancing mods that respect your time and game performance. If Bed Chat Hider makes your nighttime adventures more peaceful, consider supporting future development:

<p align="center">
  <a href="https://ko-fi.com/rifaditya"><img src="https://img.shields.io/badge/Ko--fi-Support_on_Ko--fi-F16061?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Support on Ko-fi"></a>
  <a href="https://sociabuzz.com/rifaditya"><img src="https://img.shields.io/badge/SocioBuzz-Support_Creator-00A651?style=for-the-badge" alt="Support on SocioBuzz"></a>
  <a href="https://saweria.co/rifaditya"><img src="https://img.shields.io/badge/Saweria-Support_Local-FFA500?style=for-the-badge" alt="Support on Saweria"></a>
</p>

> [!TIP]
> **🇮🇩 Indonesian Local Payment Note:** Indonesian supporters can also support my development work directly using local payment options (**GoPay, OVO, Dana, QRIS, LinkAja**) via **Saweria** or **SocioBuzz**!

Join our official Discord community for live development updates, early test builds, and friendly support:
- 💬 **Discord Community:** [https://discord.gg/EV99bgAFqb](https://discord.gg/EV99bgAFqb)

---

## 📜 Metadata & Permissions

| Property | Value |
| :--- | :--- |
| **Mod Name** | Bed Chat Hider |
| **Namespace / Mod ID** | `bed_chat_hider` |
| **License** | GNU General Public License v3.0 (GPLv3) |
| **Side Safety** | 100% Client-Side (Vanilla Server Safe) |
| **Source Code** | [GitHub Repository](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/issues) |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
