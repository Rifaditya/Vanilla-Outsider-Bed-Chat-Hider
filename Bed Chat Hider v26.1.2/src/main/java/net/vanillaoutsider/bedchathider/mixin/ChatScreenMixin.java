// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.mixin;

// Verified against: ChatScreen.java (26.1.2)

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.vanillaoutsider.bedchathider.BedChatHiderClient;
import net.vanillaoutsider.bedchathider.client.CinematicSleepController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {

    protected ChatScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void onExtractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if ((Object) this instanceof InBedChatScreen) {
            CinematicSleepController.onMouseMove(mouseX, mouseY);
            CinematicSleepController.update();

            float alpha = CinematicSleepController.getAlpha();
            for (GuiEventListener listener : this.children()) {
                if (listener instanceof AbstractWidget widget) {
                    widget.setAlpha(alpha);
                }
            }

            if (this.minecraft != null && this.minecraft.options != null && this.minecraft.options.hideGui) {
                ci.cancel();
                return;
            }
            if (CinematicSleepController.isFullyHidden()) {
                ci.cancel();
                return;
            }
            if (BedChatHiderClient.hideChat) {
                super.extractRenderState(graphics, mouseX, mouseY, a);
                ci.cancel();
            }
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void onKeyPressed(KeyEvent event, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof InBedChatScreen) {
            CinematicSleepController.onInteraction();
            if (BedChatHiderClient.hideChat) {
                cir.setReturnValue(super.keyPressed(event));
            }
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof InBedChatScreen) {
            if (CinematicSleepController.isFullyHidden()) {
                CinematicSleepController.onInteraction();
                for (GuiEventListener listener : this.children()) {
                    if (listener instanceof AbstractWidget widget) {
                        widget.setAlpha(1.0f);
                    }
                }
                cir.setReturnValue(true);
                return;
            }
            CinematicSleepController.onInteraction();
            if (BedChatHiderClient.hideChat) {
                cir.setReturnValue(super.mouseClicked(event, doubleClick));
            }
        }
    }

    @Inject(method = "mouseScrolled", at = @At("HEAD"), cancellable = true)
    private void onMouseScrolled(double x, double y, double scrollX, double scrollY, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof InBedChatScreen) {
            CinematicSleepController.onInteraction();
            if (BedChatHiderClient.hideChat) {
                cir.setReturnValue(super.mouseScrolled(x, y, scrollX, scrollY));
            }
        }
    }
}
