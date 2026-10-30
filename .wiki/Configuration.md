# ⚙️ Configuration & Settings

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

## 📦 Uninstallation & World Save Lifecycle

Because this mod utilizes native vanilla GameRules for per-world configuration (rather than a static global config file), active rule values are saved directly inside each world's data file (`saves/<world>/level.dat` under `Data.GameRules`).

### What Happens If You Remove the Mod:
If you uninstall this mod and load an existing world that was previously played with it:
1. **Informational Log Warning:** Vanilla Minecraft will log warning lines in `latest.log`:
   `Unknown registry key in ResourceKey[minecraft:root / minecraft:game_rule]: bedchathider:...`
2. **`minecraft:root` is an in-memory Java registry, NOT a folder:** `ResourceKey[minecraft:root / ...]` is Mojang's internal code identifier for the game's in-memory registry hierarchy. It is strictly an in-memory data structure in Java, not a physical directory on your drive. Zero files in your `.minecraft` directory or system are modified or overwritten.
3. **Identical to Mojang's Engine Mechanics:** This operates **exactly like how Mojang handles removed, renamed, or changed GameRules between vanilla updates** (or disabled experimental data packs). Minecraft safely skips over unknown keys during gameplay.
4. **Automatic Vanilla Pruning on Save:** Vanilla Minecraft only serializes active, registered GameRules when saving. The moment you save the world (**"Save and Quit to Title"** or after an auto-save), vanilla writes a clean `level.dat` containing only recognized keys, permanently purging all orphaned rules. Subsequent world loads will produce zero warnings.
5. **Harmless:** The warning is completely safe to ignore.

---

*Related Pages*: [[Home|Home]] | [[Session State Persistence|Session-State-Persistence]] | [[Zero-Dependency Design|Zero-Dependency-Design]]
