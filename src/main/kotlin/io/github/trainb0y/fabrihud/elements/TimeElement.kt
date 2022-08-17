package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import java.util.List

// HUD element displaying 24-hour ingame time
class TimeElement : Element {
	constructor() : super()
	constructor(x: Int, y: Int, enabled: Boolean) : super(x, y, enabled)

	override fun getArgs(client: MinecraftClient): Array<Any?> {
		val hours = ((if (client!!.world != null) client.world!!.timeOfDay else 0) / 1000 + 6).toInt() % 24
		val minutes = ((if (client.world != null) client.world!!.timeOfDay else 0) % 1000).toInt() * 60 / 1000
		return List.of(hours, minutes).toTypedArray()
	}

	override val key = "element.fabrihud.time"
}