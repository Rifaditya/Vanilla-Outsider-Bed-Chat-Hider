# 🛌 Vanilla Outsider: Bed Chat Hider Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

<div align="center">

<p align="center">
  <img src="https://img.shields.io/badge/Zero_Dependencies-Standalone-red?style=for-the-badge" alt="Zero Dependencies">
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=openjdk" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
  <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge&logo=minecraft" alt="Minecraft 26.2+">
</p>

</div>

---

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Master Documentation Portal

Welcome to the official technical documentation for **Vanilla Outsider: Bed Chat Hider**, a lightweight client-only Fabric mod designed under the **Vanilla Outsider (VO)** philosophy to enhance your sleeping experience in Minecraft.

Bed Chat Hider introduces a clean, responsive **"Hide Chat" / "Show Chat"** toggle button right next to the vanilla **"Leave Bed"** button. It allows players to clear chat text, suggestion overlays, and the edit box to enjoy an unobstructed sleeping view, especially when paired with celestial time-lapse mods like **True Sleep**.

---

## 🌟 Core Feature Matrix

| Feature Subsystem | Technical Description | Reference Documentation |
| :--- | :--- | :--- |
| **🎛️ Bed Screen UI & Layout** | Precision button splitting math ($W/2 - 100$, $98\text{px}$ width, $4\text{px}$ gap) for seamless GUI integration. | [[Bed Screen UI & Layout\|Bed-Screen-UI-and-Layout]] |
| **🛡️ Chat Visibility & Input Blocking** | 4-point input interception (`extractRenderState`, `keyPressed`, `mouseClicked`, `mouseScrolled`) ensuring zero ghost inputs. | [[Chat Visibility & Input Blocking\|Chat-Visibility-and-Input-Blocking]] |
| **🌌 True Sleep Synergy** | Removes chat obstructions during seamless celestial sky time-lapses and cinematic sleeping. | [[True Sleep Synergy\|True-Sleep-Synergy]] |
| **🧠 Session State Persistence** | In-memory toggle state tracking across night cycles with zero disk clutter. | [[Session State Persistence\|Session-State-Persistence]] |
| **⚡ Zero Runtime Dependencies** | 100% standalone mod. Does NOT require Fabric API at runtime. | [[Zero-Dependency Design\|Zero-Dependency-Design]] |
| **🛡️ ModVersionGuard Safety** | Built-in Knot ClassLoader resolution checking for runtime API compatibility to prevent world save corruption. | [[Version Compatibility\|Version-Compatibility]] |
| **🚀 Zero-Tick Overhead** | Event-driven UI rendering with net reduction in GPU text rasterization when hidden. | [[Performance & Rendering\|Performance-and-Rendering]] |

---

## 🎥 Feature Showcase Video

* **Demonstration Video**: [Watch on YouTube](https://youtu.be/jk0l7DBCTGM)
*(Showcase video demonstrates Bed Chat Hider running alongside the companion True Sleep mod).*

---

## 🚀 Quick Start Guide

### 1. Installation
1. Install **[Fabric Loader](https://fabricmc.net/)** (`>=0.16.9`).
2. Download `vanilla-outsider-bed-chat-hider-1.0.4+26.2.jar`.
3. Drop the JAR file directly into your `.minecraft/mods` directory.
4. Launch the game using **Java 25+**.
*(Note: Bed Chat Hider has **zero runtime dependencies** and does NOT require Fabric API).*

### 2. In-Game Usage
1. Right-click any Bed at night or during a thunderstorm to enter sleep mode.
2. Observe the split button layout at the bottom of the screen:
   - **Left Button**: `Leave Bed` ($98\text{px}$).
   - **Right Button**: `Hide Chat` ($98\text{px}$).
3. Click **Hide Chat** (or click it again to **Show Chat**) to toggle visibility instantly.
4. When hidden, chat text, suggestions, and typing boxes are completely hidden and keyboard/mouse clicks pass safely through!

---

## 📚 Quick Navigation Index

```
Wiki Navigation Root
├── 🧭 Compatibility & Guides
│   ├── Version Compatibility & Lifecycle ------> [[Version Compatibility|Version-Compatibility]]
│   ├── Developer Setup & Gradle Builds --------> [[Developer Setup & Building|Developer-Setup-and-Building]]
│   └── Troubleshooting & FAQ ------------------> [[Troubleshooting & FAQ|Troubleshooting-and-FAQ]]
├── 🎮 Core Mechanics & Features
│   ├── Bed Screen UI & Coordinate Math --------> [[Bed Screen UI & Layout|Bed-Screen-UI-and-Layout]]
│   ├── Chat Visibility & Input Blocking -------> [[Chat Visibility & Input Blocking|Chat-Visibility-and-Input-Blocking]]
│   ├── True Sleep Cinematic Synergy -----------> [[True Sleep Synergy|True-Sleep-Synergy]]
│   └── Session State Persistence --------------> [[Session State Persistence|Session-State-Persistence]]
├── 📋 Scope & Absence Policies
│   ├── GameRules Absence Policy ---------------> [[GameRules Reference|GameRules]]
│   ├── Brigadier Commands Policy --------------> [[Commands & Absence Policy|Commands]]
│   ├── Advancements System Policy -------------> [[Advancements|Advancements]]
│   └── Zero Configuration Architecture --------> [[Configuration & Settings|Configuration]]
└── 💻 Technical Architecture
    ├── Mixin Dissection & Class Hierarchy -----> [[Architecture & Mixins|Architecture-and-Mixins]]
    ├── Zero-Dependency Design -----------------> [[Zero-Dependency Design|Zero-Dependency-Design]]
    └── Performance & GPU Rendering Profiling --> [[Performance & Rendering|Performance-and-Rendering]]
```

---

## 📜 Credits & License

* **Architect & Developer**: **Dasik (Rifaditya)**
* **Collection**: **Vanilla Outsider (VO)**
* **License**: **GNU General Public License v3.0 (GPLv3)**
* **GitHub Repository**: [Vanilla-Outsider-Bed-Chat-Hider](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider)
