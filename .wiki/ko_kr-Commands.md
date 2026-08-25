# 💬 명령어 및 설계 정책

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

*Related Pages*: [[홈|ko_kr-Home]] | [[게임 규칙 (GameRules)|ko_kr-GameRules]] | [[침대 화면 UI 및 레이아웃|ko_kr-Bed-Screen-UI-and-Layout]]
