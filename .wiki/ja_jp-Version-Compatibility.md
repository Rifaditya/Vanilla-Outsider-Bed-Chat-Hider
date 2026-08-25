# 🧭 バージョン互換性

| Technical Parameter | Specification |
| :--- | :--- |
| **Minecraft Release Era** | **Modern Sovereign Era (`MC 26.2+`)** |
| **Target Version Dependency** | `minecraft: >=26.2-` (Open-Ended Forward Compatible) |
| **Fabric Loader Requirement** | `fabricloader: >=0.16.9` |
| **Java Development Kit** | `Java 25+` (`release = 25`, Eclipse Adoptium Hotspot) |
| **Runtime Dependencies** | **Zero Runtime Dependencies** (Fabric API NOT required) |
| **Build Tooling** | Loom 1.15+ / Gradle 9.3+ (`--no-daemon`) |
| **Active Version Policy** | **1 JAR for 1 Version** (Current active release focus) |

---

## 📌 Sovereign Era Version Policy

Vanilla Outsider: Bed Chat Hider adheres strictly to the **1 JAR for 1 Version** development standard:
* Active development and bug fixes are focused exclusively on the latest target release drop (`Minecraft 26.2+`).
* When a major new annual drop is released (e.g. 26.3), the older version branch is cleanly archived without legacy backport cycles.
* Thanks to open-ended bounds (`>=26.2-`), compatible point releases run seamlessly without requiring artificial mod updates.

---

## 🛡️ ModVersionGuard Architecture

To protect world saves and guarantee crash-free startup, Bed Chat Hider includes zero-dependency runtime class verification:

```
                  [ Game Launch / Mod Init ]
                              │
                              ▼
            [ BedChatHiderClient.onInitializeClient ]
                              │
                              ▼
              [ ModVersionGuard.checkClass ]
                              │
               ┌──────────────┴──────────────┐
               ▼                             ▼
       [ Class Found ]              [ Class Not Found ]
       (Compatible API)             (Incompatible API)
               │                             │
               ▼                             ▼
    [ Load Client Success ]      [ Halt Game with ASCII Banner ]
                                 (Prevent World Save Corruption)
```

### Verification Implementation
```java
// Verified in BedChatHiderClient.java
ModVersionGuard.checkClass("Bed Chat Hider", "net.minecraft.client.gui.screens.ChatScreen");
```

If an incompatible game runtime is detected, execution halts safely with an explicit ASCII diagnostic banner rather than causing silent chunk corruption or cryptic bytecode crashes.

---

## 📊 Compatibility Matrix

| Minecraft Version | Mod Release | Fabric Loader | Java Target | Fabric API Runtime | Support Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **26.2** | `1.0.4+26.2` | `0.19.3+` | `Java 25` | **Not Required** | 🟢 **Active / Supported** |
| **26.2** | `1.0.3+26.2` | `0.19.3+` | `Java 25` | **Not Required** | 🟡 Archived |
| **26.2** | `1.0.2-26.2` | `0.19.3+` | `Java 25` | **Not Required** | 🟡 Archived |
| **26.2** | `1.0.1-26.2` | `0.19.3+` | `Java 25` | Required (v0.152.2) | 🔴 Yanked |
| **26.2** | `1.0.0-26.2` | `0.19.3+` | `Java 25` | Required (v0.152.2) | 🟡 Archived |

---

*Related Pages*: [[ホーム|ja_jp-Home]] | [[ゼロ依存アーキテクチャ|ja_jp-Zero-Dependency-Design]] | [[開発環境のセットアップとビルド|ja_jp-Developer-Setup-and-Building]]
