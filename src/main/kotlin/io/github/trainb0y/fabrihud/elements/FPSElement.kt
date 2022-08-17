package io.github.trainb0y.fabrihud.elements

import io.github.trainb0y.fabrihud.mixin.MinecraftClientMixin
import net.minecraft.client.MinecraftClient
import java.util.List

// HUD element displaying framerate
class FPSElement : Element {
	constructor() : super()
	constructor(x: Int, y: Int, enabled: Boolean) : super(x, y, enabled)

	override fun getArgs(client: MinecraftClient): Array<Any?> {
		return List.of((client as MinecraftClientMixin?)!!.currentFPS).toTypedArray()
	}

	override val key = "element.fabrihud.fps"
}