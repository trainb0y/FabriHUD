package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient

class LightElement : Element() {
	override fun getArgs(client: MinecraftClient): List<Any?> {

		return listOf(client.world?.lightingProvider?.getLight(client.player?.blockPos, 0) ?: -1)
	}

	override val key = "element.fabrihud.light"
}