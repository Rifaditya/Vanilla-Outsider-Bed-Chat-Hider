# 🛡️ 채팅 가시성 및 입력 차단

| Parameter | Technical Details |
| :--- | :--- |
| **Target Screen Class** | `net.minecraft.client.gui.screens.ChatScreen` |
| **Mixin Handler** | [`ChatScreenMixin.java`](file:///src/main/java/net/vanillaoutsider/bedchathider/mixin/ChatScreenMixin.java) |
| **In-Memory State** | `BedChatHiderClient.hideChat` (`boolean`) |
| **Interception Targets** | `extractRenderState`, `keyPressed`, `mouseClicked`, `mouseScrolled` |
| **Focus Gatekeeper** | `bedchathider$updateChatState()` |

---

## 🔄 State Machine Lifecycle

```
                 [ User Clicks 'Hide Chat' ]
                              │
                              ▼
                BedChatHiderClient.hideChat = true
                              │
                              ▼
                   this.rebuildWidgets()
                              │
         ┌────────────────────┴────────────────────┐
         ▼                                         ▼
[ EditBox Input State ]                   [ Input Interception ]
• input.visible = false                   • extractRenderState -> cancel
• input.active  = false                   • keyPressed -> super.keyPressed
• input.setFocused(false)                 • mouseClicked -> super.mouseClicked
• screen.setFocused(null)                 • mouseScrolled -> super.mouseScrolled
```

---

## 🛡️ 4-Point Input Interception Breakdown

When chat is hidden, `ChatScreenMixin` intercepts 4 critical input pathways to prevent background ghost typing or accidental link clicking:

| Intercepted Method | Vanilla Behavior | Bed Chat Hider Action (When Hidden) | Result |
| :--- | :--- | :--- | :--- |
| `extractRenderState` | Renders chat background, history text, and command suggestions. | Invokes `super.extractRenderState` and executes `ci.cancel()`. | Chat text and background box are completely invisible. |
| `keyPressed` | Types characters into `input`, autocompletes commands, cycles history. | Returns `cir.setReturnValue(super.keyPressed(event))`. | Typing and arrow cycling are disabled; `Escape` closes screen normally. |
| `mouseClicked` | Clicks chat text URLs, suggestions, or chat log selection. | Returns `cir.setReturnValue(super.mouseClicked(event, doubleClick))`. | Chat selection disabled; GUI buttons (`Leave Bed`, `Show Chat`) work cleanly. |
| `mouseScrolled` | Scrolls chat log history up and down. | Returns `cir.setReturnValue(super.mouseScrolled(x, y, scrollX, scrollY))`. | Log scrolling disabled; screen remains static. |

---

## 💻 Source Code Verification

```java
// Sourced from ChatScreenMixin.java
@Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
private void onExtractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if ((Object) this instanceof InBedChatScreen && BedChatHiderClient.hideChat) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        ci.cancel();
    }
}

@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
private void onKeyPressed(KeyEvent event, CallbackInfoReturnable<Boolean> cir) {
    if ((Object) this instanceof InBedChatScreen && BedChatHiderClient.hideChat) {
        cir.setReturnValue(super.keyPressed(event));
    }
}
```

---

*Related Pages*: [[홈|ko_kr-Home]] | [[침대 화면 UI 및 레이아웃|ko_kr-Bed-Screen-UI-and-Layout]] | [[아키텍처 및 Mixin 분석|ko_kr-Architecture-and-Mixins]]
