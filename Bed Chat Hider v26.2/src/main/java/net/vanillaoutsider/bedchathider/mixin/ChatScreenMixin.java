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

// Verified against: ChatScreen.java (26.2+)

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.vanillaoutsider.bedchathider.BedChatHiderClient;
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

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof InBedChatScreen && BedChatHiderClient.hideChat) {
            cir.setReturnValue(super.mouseClicked(event, doubleClick));
        }
    }

    @Inject(method = "mouseScrolled", at = @At("HEAD"), cancellable = true)
    private void onMouseScrolled(double x, double y, double scrollX, double scrollY, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof InBedChatScreen && BedChatHiderClient.hideChat) {
            cir.setReturnValue(super.mouseScrolled(x, y, scrollX, scrollY));
        }
    }
}
