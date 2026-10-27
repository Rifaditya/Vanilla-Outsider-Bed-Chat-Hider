// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.bedchathider.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.vanillaoutsider.bedchathider.client.CinematicSleepController;
import net.vanillaoutsider.bedchathider.util.BedChatHiderSupport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class HudMixin {

    @Redirect(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;getSleepTimer()I"
        )
    )
    private int bedchathider$suppressSleepVignette(LocalPlayer player) {
        if (BedChatHiderSupport.shouldSuppressSleepVignette()) {
            return 0;
        }
        return player.getSleepTimer();
    }

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At("HEAD"), cancellable = true)
    private void bedchathider$suppressCrosshairWhenSleeping(GuiGraphics graphics, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null && client.player.isSleeping() && CinematicSleepController.isCrosshairSuppressed()) {
            ci.cancel();
        }
    }
}
