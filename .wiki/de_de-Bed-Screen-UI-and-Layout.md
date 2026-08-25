# 🎛️ Bett-Bildschirm UI & Layout

| Parameter | Technical Details |
| :--- | :--- |
| **Target Screen Class** | `net.minecraft.client.gui.screens.InBedChatScreen` |
| **Superclass** | `net.minecraft.client.gui.screens.ChatScreen` |
| **Mixin Handler** | [`InBedChatScreenMixin.java`](https://github.com/Rifaditya/Vanilla-Outsider-Bed-Chat-Hider/blob/main/src/main/java/net/vanillaoutsider/bedchathider/mixin/InBedChatScreenMixin.java) |
| **Injection Point** | `@Inject(method = "init", at = @At("TAIL"))` |
| **Button Width** | $98\text{ px}$ (Left: Leave Bed, Right: Toggle Chat) |
| **Button Height** | $20\text{ px}$ |
| **Button Gap** | $4\text{ px}$ |
| **Vertical Anchor** | $Y = H - 40\text{ px}$ |
| **Translation Keys** | `vanilla-outsider-bed-chat-hider.hideChat`, `...showChat` |

---

## 🎮 Player Interaction Workflow

When a player enters a bed:
1. Minecraft opens `InBedChatScreen`.
2. By default in vanilla, a single wide **Leave Bed** button ($200\text{px}$ wide) sits at screen bottom.
3. Bed Chat Hider dynamically resizes the **Leave Bed** button to $98\text{px}$ and adds a matching $98\text{px}$ **Hide Chat** / **Show Chat** button next to it.
4. Clicking the toggle button flips the in-memory state, immediately refreshing all widgets without closing the screen or waking the player up.

---

## 📐 Coordinate Geometry & Split Math

Vanilla Minecraft calculates the single button position as:
$$X_{\text{vanilla}} = \frac{W}{2} - 100, \quad \text{Width} = 200, \quad Y = H - 40$$

Bed Chat Hider decomposes this $200\text{px}$ span into a symmetrical dual-button cluster with a $4\text{px}$ center divide:

$$\text{Leave Bed Button: } X_1 = \frac{W}{2} - 100, \quad \text{Width}_1 = 98, \quad Y_1 = H - 40$$
$$\text{Center Gap: } \Delta X = 4\text{ px}$$
$$\text{Toggle Chat Button: } X_2 = \frac{W}{2} + 2, \quad \text{Width}_2 = 98, \quad Y_2 = H - 40$$

$$\text{Total Span: } W_1 + \Delta X + W_2 = 98 + 4 + 98 = 200\text{ px}$$

---

## 🖼️ Visual ASCII Screen Layout

### Vanilla Sleep Screen:
```
+-------------------------------------------------------------+
| [Chat Log Overlay Area]                                     |
| <Player1> Good night everyone!                              |
|                                                             |
| [Chat Input EditBox] ______________________________________ |
|                                                             |
|                 [      Leave Bed (200px)      ]             |
+-------------------------------------------------------------+
```

### Bed Chat Hider Sleep Screen (Chat Visible):
```
+-------------------------------------------------------------+
| [Chat Log Overlay Area]                                     |
| <Player1> Good night everyone!                              |
|                                                             |
| [Chat Input EditBox] ______________________________________ |
|                                                             |
|           [ Leave Bed (98px) ]  [ Hide Chat (98px) ]         |
+-------------------------------------------------------------+
```

### Bed Chat Hider Sleep Screen (Chat Hidden):
```
+-------------------------------------------------------------+
|                                                             |
|              (Clean, Unobstructed Sleep View)               |
|                                                             |
|                                                             |
|           [ Leave Bed (98px) ]  [ Show Chat (98px) ]         |
+-------------------------------------------------------------+
```

---

## 💻 Technical Code Implementation

```java
// Sourced from InBedChatScreenMixin.java
@Inject(method = "init", at = @At("TAIL"))
private void onInit(CallbackInfo ci) {
    if (this.leaveBedButton != null) {
        this.leaveBedButton.setX(this.width / 2 - 100);
        this.leaveBedButton.setWidth(98);
    }

    Button toggleButton = Button.builder(
        bedchathider$getButtonMessage(),
        button -> {
            BedChatHiderClient.hideChat = !BedChatHiderClient.hideChat;
            this.rebuildWidgets();
        }
    ).bounds(this.width / 2 + 2, this.height - 40, 98, 20).build();

    this.addRenderableWidget(toggleButton);
    bedchathider$updateChatState();
}
```

---

*Related Pages*: [[Startseite|de_de-Home]] | [[Chat-Sichtbarkeit & Eingabeblockierung|de_de-Chat-Visibility-and-Input-Blocking]] | [[Architektur & Mixin-Analyse|de_de-Architecture-and-Mixins]]
