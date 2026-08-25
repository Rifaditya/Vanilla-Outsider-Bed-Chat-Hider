# ⚙️ 설정 및 무구성 설계

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

*Related Pages*: [[홈|ko_kr-Home]] | [[세션 상태 유지|ko_kr-Session-State-Persistence]] | [[무의존성 architecture|ko_kr-Zero-Dependency-Design]]
