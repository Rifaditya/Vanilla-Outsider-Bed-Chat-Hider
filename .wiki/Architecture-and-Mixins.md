# 🧩 Architecture & Mixins

| Parameter | Technical Details |
| :--- | :--- |
| **Root Package** | `net.vanillaoutsider.bedchathider` |
| **Mixin Configuration** | `vanilla-outsider-bed-chat-hider.mixins.json` |
| **Compatibility Level** | `JAVA_25` |
| **Default Require Count** | `1` (`injectors.defaultRequire: 1`) |

---

## 🏛️ Class Architecture Overview

```
                        [ BedChatHiderClient ]
                        (ClientModInitializer)
                                  │
                  ┌───────────────┴───────────────┐
                  ▼                               ▼
       [ InBedChatScreenMixin ]           [ ChatScreenMixin ]
       (Widget & Layout Injection)        (Input & Render Gating)
                  │                               │
                  ▼                               ▼
     Reposition 'Leave Bed' &             Bypass chat rendering,
     Inject 'Hide Chat' Button            keys, clicks, and scroll
```

---

## 🔍 Detailed Mixin Injection Inventory

### 1. `InBedChatScreenMixin` (`InBedChatScreen.class`)
* **Target Method**: `init()`
* **Injection Point**: `@At("TAIL")`
* **Actions**:
  - Resizes `leaveBedButton` from $200\text{px}$ to $98\text{px}$ at $X = W/2 - 100$.
  - Adds toggle `Button` of $98\text{px}$ at $X = W/2 + 2, Y = H - 40$.
  - Synchronizes `input` visibility and focus state via `bedchathider$updateChatState()`.

### 2. `ChatScreenMixin` (`ChatScreen.class`)
* **Target Method 1**: `extractRenderState(GuiGraphicsExtractor, int, int, float, CallbackInfo)`
  - `@At("HEAD")`, `cancellable = true`
  - When `hideChat` is true on `InBedChatScreen`: calls `super.extractRenderState` and cancels `ChatScreen` rendering.
* **Target Method 2**: `keyPressed(KeyEvent, CallbackInfoReturnable<Boolean>)`
  - `@At("HEAD")`, `cancellable = true`
  - Re-routes to `super.keyPressed(event)` to disable chat typing and arrow navigation.
* **Target Method 3**: `mouseClicked(MouseButtonEvent, boolean, CallbackInfoReturnable<Boolean>)`
  - `@At("HEAD")`, `cancellable = true`
  - Re-routes to `super.mouseClicked(event, doubleClick)` to disable clicking chat URLs.
* **Target Method 4**: `mouseScrolled(double, double, double, double, CallbackInfoReturnable<Boolean>)`
  - `@At("HEAD")`, `cancellable = true`
  - Re-routes to `super.mouseScrolled(x, y, scrollX, scrollY)` to disable log scrolling.

---

*Related Pages*: [[Home|Home]] | [[Bed Screen UI & Layout|Bed-Screen-UI-and-Layout]] | [[Chat Visibility & Input Blocking|Chat-Visibility-and-Input-Blocking]]
