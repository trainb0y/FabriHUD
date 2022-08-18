package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.BlockPos

// HUD Element displaying position of player
class PositionElement : Element() {
	override fun getArgs(client: MinecraftClient): List<Any?> {
		val pos = client.player?.blockPos ?: BlockPos(0, 0, 0)
		return listOf(pos.x, pos.y, pos.z)
	}

	override val key = "element.fabrihud.position"
}