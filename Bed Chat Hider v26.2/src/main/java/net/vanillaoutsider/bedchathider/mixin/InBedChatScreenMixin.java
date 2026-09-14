/*
 * This file is part of Bed Chat Hider.
 *
 * Bed Chat Hider is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bed Chat Hider is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bed Chat Hider.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.vanillaoutsider.bedchathider.mixin;

// Verified against: InBedChatScreen.java (26.2+)

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.network.chat.Component;
import net.vanillaoutsider.bedchathider.BedChatHiderClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InBedChatScreen.class)
public abstract class InBedChatScreenMixin extends ChatScreen {

    @Shadow
    private Button leaveBedButton;

    protected InBedChatScreenMixin(String initial, boolean isDraft) {
        super(initial, isDraft);
    }

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

    @Unique
    private void bedchathider$updateChatState() {
        boolean hide = BedChatHiderClient.hideChat;
        if (this.input != null) {
            this.input.visible = !hide;
            this.input.active = !hide;
            if (hide) {
                this.input.setFocused(false);
                if (this.getFocused() == this.input) {
                    this.setFocused(null);
                }
            } else {
                this.input.setFocused(true);
                this.setFocused(this.input);
            }
        }
    }

    @Unique
    private Component bedchathider$getButtonMessage() {
        return BedChatHiderClient.hideChat
            ? Component.translatable("vanilla-outsider-bed-chat-hider.showChat")
            : Component.translatable("vanilla-outsider-bed-chat-hider.hideChat");
    }
}
