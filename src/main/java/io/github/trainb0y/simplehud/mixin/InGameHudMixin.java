package io.github.trainb0y.simplehud.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {

        MinecraftClient.getInstance().textRenderer.draw(matrices, "Hey look, a thing!", 5, 5, -1);

    }
}
