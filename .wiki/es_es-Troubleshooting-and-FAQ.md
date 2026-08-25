# ❓ Solución de Problemas y Preguntas Frecuentes

| Topic | Quick Solution |
| :--- | :--- |
| **ModVersionGuard Startup Halt** | Verify you are running Minecraft 26.2+ with Java 25. |
| **Button Overlapping** | Check if other mods heavily alter the `InBedChatScreen` layout. |
| **Chat Still Typing** | Make sure you clicked 'Hide Chat' so the button displays 'Show Chat'. |

---

## ❓ Frequently Asked Questions (FAQ)

### Q1: Does Bed Chat Hider work on multiplayer servers?
**A**: Yes! Bed Chat Hider is 100% client-side. The server does not need the mod installed, and you will not get kicked or banned for using it.

### Q2: Does this mod require Fabric API?
**A**: No. Bed Chat Hider has **zero runtime dependencies** and runs with pure Fabric Loader.

### Q3: Why does my game refuse to launch with an ASCII warning banner?
**A**: The built-in `ModVersionGuard` protects your worlds. If you attempt to run the mod on an unsupported Minecraft version where `ChatScreen` is missing or relocated, the guard halts startup safely to prevent save corruption. Update to Minecraft 26.2+.

### Q4: Can I use this with shaders and sleep mods?
**A**: Yes! Bed Chat Hider is fully compatible with Iris/Sodium shaders and works especially well with **Vanilla Outsider: True Sleep**.

---

*Related Pages*: [[Inicio|es_es-Home]] | [[Compatibilidad de Versiones|es_es-Version-Compatibility]] | [[Sinergia con True Sleep|es_es-True-Sleep-Synergy]]
