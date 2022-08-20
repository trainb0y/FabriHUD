package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient

// HUD element displaying 24-hour ingame time
class TimeElement : TextElement() {
	override fun getArgs(client: MinecraftClient): List<Any?> {
		val hours = ((client.world?.timeOfDay ?: 0) / 1000 + 6).toInt() % 24
		val minutes = ((client.world?.timeOfDay ?: 0) % 1000).toInt() * 60 / 1000
		return listOf(hours, minutes)
	}

	override val key = "element.fabrihud.time"
}