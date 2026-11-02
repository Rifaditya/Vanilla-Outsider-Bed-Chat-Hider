<div align="center">

<p align="center">
    <a href="https://discord.gg/EV99bgAFqb"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
    <img src="https://img.shields.io/badge/Environment-Client_Side-success?style=for-the-badge" alt="Client Side">
    <img src="https://img.shields.io/badge/Requires-Fabric_API_%26_Dasik_Library-blue?style=for-the-badge" alt="Requires Fabric API & Dasik Library">
    <img src="https://img.shields.io/badge/Language-Java_17_to_25-orange?style=for-the-badge&logo=openjdk" alt="Java 17 to 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-1.20.1_to_26.3+-brightgreen?style=for-the-badge" alt="Minecraft 1.20.1 to 26.3+">
</p>

# 🛌 Vanilla Outsider: Bed Chat Hider

> **"A clean, distraction-free sleeping view with a single click."**

</div>

**1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.3, MC 26.2, MC 26.1.2, 1.21.11, 1.21.1, 1.20.1). Please download the exact build that matches your Minecraft installation.

**Vanilla Outsider: Bed Chat Hider** is a lightweight, client-only Fabric mod that adds an intuitive "Hide Chat" / "Show Chat" toggle button next to the "Leave Bed" button while sleeping.

---

> [!NOTE]
> **Client-Side Sovereignty:** Unlike server-side mods in the Vanilla Outsider Collection, Bed Chat Hider is 100% client-side. You can safely install Bed Chat Hider on your client and join ANY vanilla, Fabric, Paper, Spigot, Purpur, or NeoForge/Forge server without needing the mod installed on the server!

---

## 🎥 Feature Showcase

<p align="center">
    <a href="https://youtu.be/jk0l7DBCTGM"><img src="https://img.youtube.com/vi/jk0l7DBCTGM/maxresdefault.jpg" alt="Watch the Bed Chat Hider Showcase on YouTube" width="80%"></a>
    <br />
    <em>Disclaimer: The showcase video above was recorded with my <strong>True Sleep</strong> companion mod installed.</em>
</p>

---

## ✨ Features

### 🎞️ Clear Sleeping View
Hides the chat input background fill, autocomplete suggestions, and chat history log completely when toggled with the button while resting.

#### With True Sleep Mod (Accelerated Time-Lapse View)
When paired with my **[True Sleep](https://modrinth.com/mod/vanilla-outsider-true-sleep)** companion mod, the dark sleep vignette is removed, allowing you to watch the sky fly by in real-time time-lapse without UI overlays blocking your view!

* **Chat Hidden (True Sleep)**:
<p align="center"><img src="https://raw.githubusercontent.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/main/Doc/Images/Chat%20hiden.webp" alt="Chat Hidden with True Sleep" width="80%"></p>

* **Chat Shown (True Sleep)**:
<p align="center"><img src="https://raw.githubusercontent.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/main/Doc/Images/Chat%20shown.webp" alt="Chat Shown with True Sleep" width="80%"></p>

#### Without True Sleep Mod (Vanilla Dark Sleep Overlay)
Without the True Sleep mod, the vanilla black vignette sleep darkening remains present, but the chat overlay is hidden.

* **Chat Hidden (Vanilla)**:
<p align="center"><img src="https://raw.githubusercontent.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/main/Doc/Images/chat%20hidden%20without%20true%20sleep%20mod.webp" alt="Chat Hidden without True Sleep" width="80%"></p>

* **Chat Shown (Vanilla)**:
<p align="center"><img src="https://raw.githubusercontent.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/main/Doc/Images/chat%20shown%20without%20true%20sleep%20mod.webp" alt="Chat Shown without True Sleep" width="80%"></p>

### 💤 Session-Based Preference
Tracks your toggle preference in-memory during your current play session so you do not have to keep toggling it every in-game night.

### 🛡️ Input Interception & Protection
Safely intercepts and blocks keyboard typing, mouse scrolling, and clicks on the invisible chat log when chat is hidden, ensuring that only standard UI buttons are interactive and `Escape` closes the bed screen normally.

---

## 🔧 Optional Configuration & Ergonomics

Bed Chat Hider is completely plug-and-play with zero setup required, but also includes optional in-game configuration through **ModMenu** and **YetAnotherConfigLib (YACL)** (powered by Dasik Library):

* **`suppress_sleep_vignette`** *(Default: `false`)*: Eliminates the dark sleeping vignette overlay when resting in a bed. *(Note: If companion mod **[True Sleep](https://modrinth.com/mod/vanilla-outsider-true-sleep)** is installed, True Sleep automatically manages sleep visuals and takes precedence cleanly without conflict).*
* **`auto_hide_chat`** *(Default: `false`)*: Automatically hides the chat overlay upon entering any bed.

Settings are saved locally to `.minecraft/config/bedchathider.json`. If ModMenu or YACL are not installed, the mod runs seamlessly with safe defaults with zero crash risk.

---

## ☕ Support the Development

If you enjoy the **Vanilla Outsider** collection, consider supporting my work!

[![Ko-fi](https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/dasikigaijin/tip)
[![SocioBuzz](https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge)](https://sociabuzz.com/dasikigaijin/tribe)
[![Saweria](https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge)](https://saweria.co/DasikIgaijinn)

> [!NOTE]
> **Indonesian Supporters:** SocioBuzz and Saweria support local payment methods (GoPay, OVO, Dana, QRIS, etc.) if you wish to support me locally without PayPal/Ko-fi!

---

## 📜 Credits & Permissions

| Role | Author |
| :--- | :--- |
| **Architect** | **Dasik (Rifaditya)** |
| **Collection** | Vanilla Outsider |
| **License** | GPLv3 |
| **Source Code** | [GitHub Repository](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/issues) |

> [!IMPORTANT]
> **Modpack Permissions:** You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
