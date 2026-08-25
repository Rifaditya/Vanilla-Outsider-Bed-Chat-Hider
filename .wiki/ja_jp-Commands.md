# 💬 コマンドと設計方針

| Parameter | Policy & Scope |
| :--- | :--- |
| **Command System** | `0` Brigadier Commands Registered |
| **User Interaction** | Direct In-Bed Graphical Button (`ButtonBuilder`) |
| **Operator Permissions Required** | None (`Level 0` - Standard Client Player) |

---

## 📌 Absence Policy & Design Scope

Bed Chat Hider does NOT register any slash commands (`/bedchathider` or similar):
* Commands require keyboard typing, which is intentionally disabled when sleeping and chat is hidden.
* All interaction is handled seamlessly via the in-bed graphical buttons (**Hide Chat** / **Show Chat**).
* Zero command registrations keeps the client autocomplete tree clean and prevents packet overhead.

---

*Related Pages*: [[ホーム|ja_jp-Home]] | [[ゲームルール (GameRules)|ja_jp-GameRules]] | [[ベッド画面UIとレイアウト|ja_jp-Bed-Screen-UI-and-Layout]]
