package io.github.trainb0y.fabrihud.elements

import io.github.trainb0y.fabrihud.mixin.MinecraftClientMixin
import net.minecraft.client.MinecraftClient

// HUD element displaying framerate
class FPSElement : TextElement() {
	override fun getArgs(client: MinecraftClient): List<Any?> {
		return listOf((client as MinecraftClientMixin).currentFPS)
	}

	override val key = "element.fabrihud.fps"
}