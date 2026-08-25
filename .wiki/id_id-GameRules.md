# 📜 Aturan Game (GameRules)

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

*Related Pages*: [[Beranda|id_id-Home]] | [[Perintah & Kebijakan Absensi|id_id-Commands]] | [[Konfigurasi & Pengaturan|id_id-Configuration]]
