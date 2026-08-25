# 💬 指令與範圍政策

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

*Related Pages*: [[首頁面板|zh_tw-Home]] | [[遊戲規則與範圍政策|zh_tw-GameRules]] | [[睡眠界面佈局與座標計算|zh_tw-Bed-Screen-UI-and-Layout]]
