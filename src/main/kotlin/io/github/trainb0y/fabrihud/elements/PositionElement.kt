package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.BlockPos
import java.util.List

// HUD Element displaying position of player
class PositionElement : Element {
	constructor() : super()
	constructor(x: Int, y: Int, enabled: Boolean) : super(x, y, enabled)

	override fun getArgs(client: MinecraftClient): Array<Any?> {
		val pos = if (client!!.player != null) client.player!!.blockPos else BlockPos(0, 0, 0)
		return List.of(pos.x, pos.y, pos.z).toTypedArray()
	}

	override val key = "element.fabrihud.position"
}