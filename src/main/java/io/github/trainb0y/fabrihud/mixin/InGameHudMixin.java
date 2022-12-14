package io.github.trainb0y.fabrihud.mixin;

import io.github.trainb0y.fabrihud.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
	@Final
	@Shadow
	private MinecraftClient client;

	@Inject(method = "render", at = @At("TAIL"))
	public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
		if (!Config.hudEnabled) return;
		if (client.options.debugEnabled) return; // don't render when f3 open
		Config.getElements().forEach((element) -> {
					if (element.enabled) element.render(matrices);
				}
		);
	}
}
