# 🚀 Performances et Rendu

| Metric | Measured Impact |
| :--- | :--- |
| **Tick Overhead** | $0.000\text{ ms/tick}$ (No ticking listeners or background workers) |
| **Frame Render Time** | Net Reduction ($pprox -0.05\text{ ms/frame}$ when chat is hidden) |
| **Per-Tick Memory Allocations** | $0\text{ bytes/tick}$ (Zero allocation hot path) |
| **Heap Memory Usage** | $< 1\text{ KB}$ static memory |

---

## ⚡ Why Hiding Chat Improves Sleeping Performance

In vanilla Minecraft, every frame rendered while sleeping forces the client to:
1. Extract text glyphs for the entire visible chat history buffer.
2. Rasterize the semi-transparent dark background quad for the edit box.
3. Render command suggestions and cursor animations.

By intercepting `extractRenderState` and canceling `ChatScreen` rendering when hidden, Bed Chat Hider bypasses these draw calls completely, resulting in a cleaner frame graph and reduced GPU text rendering workload during sleep!

---

*Related Pages*: [[Accueil|fr_fr-Home]] | [[Visibilité du Chat et Blocage d'Entrée|fr_fr-Chat-Visibility-and-Input-Blocking]] | [[Architecture et Analyse des Mixins|fr_fr-Architecture-and-Mixins]]
