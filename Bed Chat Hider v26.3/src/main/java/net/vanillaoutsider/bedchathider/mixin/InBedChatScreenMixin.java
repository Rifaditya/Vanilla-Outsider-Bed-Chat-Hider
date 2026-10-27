// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.mixin;

// Verified against: InBedChatScreen.java (26.3+)

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.network.chat.Component;
import net.vanillaoutsider.bedchathider.BedChatHiderClient;
import net.vanillaoutsider.bedchathider.client.CinematicSleepController;
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

    @Unique
    private Button bedchathider$toggleButton;

    protected InBedChatScreenMixin(String initial, boolean isDraft) {
        super(initial, isDraft);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        if (this.leaveBedButton != null) {
            this.leaveBedButton.setX(this.width / 2 - 100);
            this.leaveBedButton.setWidth(98);
        }

        this.bedchathider$toggleButton = Button.builder(
            bedchathider$getButtonMessage(),
            button -> {
                BedChatHiderClient.hideChat = !BedChatHiderClient.hideChat;
                this.rebuildWidgets();
            }
        ).bounds(this.width / 2 + 2, this.height - 40, 98, 20).build();

        this.addRenderableWidget(this.bedchathider$toggleButton);
        bedchathider$updateChatState();
        CinematicSleepController.reset();
    }

    @Inject(method = "onClose", at = @At("HEAD"))
    private void onOnClose(CallbackInfo ci) {
        CinematicSleepController.reset();
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        CinematicSleepController.onMouseMove(mouseX, mouseY);
        super.mouseMoved(mouseX, mouseY);
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
