# ⚙️ 零配置架構設計

| Parameter | Specification |
| :--- | :--- |
| **Configuration Files** | `0` Disk Files (`config/` folder untouched) |
| **Configuration GUI** | None needed (Plug-and-play in-bed toggle) |
| **State Persistence Model** | In-Memory Session Storage (`BedChatHiderClient.hideChat`) |
| **ModMenu Integration** | Displayed in mod list with metadata (no config screen needed) |

---

## 📌 Zero-Configuration Architecture

Bed Chat Hider is built on a **zero-configuration** philosophy:
* No complex TOML, JSON, or YAML configuration files to maintain.
* No need to edit files before playing; simply launch the game, enter a bed, and click the button.
* Your toggle choice remains active throughout your entire game session.

---

*Related Pages*: [[首頁面板|zh_tw-Home]] | [[工作階段狀態與記憶體持久化|zh_tw-Session-State-Persistence]] | [[零依賴架構設計|zh_tw-Zero-Dependency-Design]]
