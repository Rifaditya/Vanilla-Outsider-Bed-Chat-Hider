# ⚙️ 設定とゼロ構成設計

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

*Related Pages*: [[ホーム|ja_jp-Home]] | [[セッション状態の保持|ja_jp-Session-State-Persistence]] | [[ゼロ依存アーキテクチャ|ja_jp-Zero-Dependency-Design]]
