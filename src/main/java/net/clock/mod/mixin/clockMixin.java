package net.clock.mod.mixin;

import net.clock.mod.clock;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.clock.mod.client.clockClient;
@Mixin(InGameHud.class)
public class clockMixin {

    @Inject(method = "render", at = @At(value = "HEAD"),cancellable = true)
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();

        String time = localDateTime.format(dtf).toString();


        MinecraftClient.getInstance().textRenderer.draw(matrixStack, time, clockClient.x, clockClient.y, clockClient.colour);


    }
}
