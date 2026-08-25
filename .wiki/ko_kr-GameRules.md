# 📜 게임 규칙 (GameRules)

| Parameter | Policy & Scope |
| :--- | :--- |
| **Mod Category** | **Pure Client-Side Quality-of-Life Mod** |
| **Registered Custom GameRules** | `0` (Zero server-side rules) |
| **Server Interaction** | None (Client-local UI modification only) |
| **Multiplayer Compatibility** | Fully compatible with 100% vanilla and modded servers |

---

## 📌 Absence Policy & Design Scope

Bed Chat Hider intentionally registers **no custom GameRules**:
* The mod operates purely inside the client's screen renderer (`InBedChatScreen` and `ChatScreen`).
* Server gameplay rules regarding sleeping (such as `playersSleepingPercentage` or `doDaylightCycle`) remain 100% untouched and function normally.
* Players can connect to any vanilla or modded dedicated server with Bed Chat Hider installed on their client without needing server-side support or permissions.

---

*Related Pages*: [[홈|ko_kr-Home]] | [[명령어 및 설계 정책|ko_kr-Commands]] | [[설정 및 무구성 설계|ko_kr-Configuration]]
