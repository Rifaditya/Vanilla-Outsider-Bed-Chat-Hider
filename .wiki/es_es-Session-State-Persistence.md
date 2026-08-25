# 🧠 Persistencia de Estado de Sesión

| Parameter | Technical Details |
| :--- | :--- |
| **Storage Mechanism** | Static In-Memory Field (`BedChatHiderClient.hideChat`) |
| **Data Type** | `boolean` (Default: `false`) |
| **Disk Footprint** | $0\text{ bytes}$ (No JSON / NBT files generated) |
| **Session Lifetime** | Persists across all bed entries during current game launch |
| **Reset Trigger** | Full game client restart |

---

## 🔄 Night-to-Night In-Memory Lifecycle

```
[ Game Launch ] ──> BedChatHiderClient.hideChat = false
                          │
                          ▼
[ Night 1: Enter Bed ] ──> User clicks 'Hide Chat' (State -> true)
                          │
                          ▼
[ Wake Up / Day 1 ] ─────> (State preserved in memory: true)
                          │
                          ▼
[ Night 2: Enter Bed ] ──> UI automatically initializes with Chat Hidden!
```

---

## 💡 Why Zero-Disk Persistence?

1. **Zero Disk I/O Overhead**: Eliminates unnecessary file reads/writes on every sleep cycle.
2. **Zero Corruption Risk**: No config file to become corrupted, desynchronized, or locked by filesystem errors.
3. **Session Agility**: Players can adjust their preference dynamically per gaming session without hunting through config folders.

---

*Related Pages*: [[Inicio|es_es-Home]] | [[Configuración y Opciones|es_es-Configuration]] | [[Diseño Sin Dependencias|es_es-Zero-Dependency-Design]]
