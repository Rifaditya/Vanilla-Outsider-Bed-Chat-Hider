# ⚡ 零依赖架构设计

| Parameter | Technical Details |
| :--- | :--- |
| **Runtime Dependencies** | **Zero Runtime Dependencies** (`fabricloader: >=0.16.9`, `minecraft: >=26.2-`, `java: >=25`) |
| **Fabric API at Runtime** | **Not Required** (Removed in v1.0.2) |
| **JAR File Size** | Minimal footprint (~528 KB) |
| **Modpack Mod Count** | Standalone plug-and-play |

---

## 🎯 The Philosophy of Zero Runtime Dependencies

Many quality-of-life mods unnecessarily depend on heavy runtime library bundles. Bed Chat Hider achieves complete functionality using only vanilla Minecraft GUI classes and standard Fabric Loader entrypoints:
1. **Zero Version Conflicts**: No crashes caused by mismatched Fabric API sub-module versions.
2. **Instant Loading**: Zero dependency graph resolution delay during client initialization.
3. **Universal Modpack Fit**: Can be dropped into any client instance regardless of installed mod framework versions.

---

*Related Pages*: [[首页面板|zh_cn-Home]] | [[版本兼容性与生命周期|zh_cn-Version-Compatibility]] | [[开发者环境与编译构建|zh_cn-Developer-Setup-and-Building]]
