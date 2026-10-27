// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.vanillaoutsider.bedchathider.client.CinematicSleepController;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class HudMixin {

    @Inject(method = "renderSleepOverlay", at = @At("HEAD"), cancellable = true)
    private void bedchathider$suppressSleepVignette(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (BedChatHiderSupport.shouldSuppressSleepVignette()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"), cancellable = true)
    private void bedchathider$suppressCrosshairWhenSleeping(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null && client.player.isSleeping() && CinematicSleepController.isCrosshairSuppressed()) {
            ci.cancel();
        }
    }
}
