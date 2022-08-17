package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import java.util.List

// HUD element displaying the client-server latency (ping)
class LatencyElement : Element {
	constructor() : super()
	constructor(x: Int, y: Int, enabled: Boolean) : super(x, y, enabled)

	override fun getArgs(client: MinecraftClient): Array<Any?> {
		var ping = -1
		if (client!!.networkHandler != null) {
			if (client.networkHandler!!.getPlayerListEntry(if (client.player != null) client.player!!.uuid else null) != null) {
				ping = client.networkHandler!!.getPlayerListEntry(client.player!!.uuid)!!.latency
			}
		}
		return List.of(ping).toTypedArray()
	}

	override val key = "element.fabrihud.latency"
}