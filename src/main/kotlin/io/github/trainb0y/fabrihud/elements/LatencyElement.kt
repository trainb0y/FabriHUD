package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient

// HUD element displaying the client-server latency (ping)
class LatencyElement : Element() {
	override fun getArgs(client: MinecraftClient): List<Any?> {
		var ping = -1
		if (client.networkHandler != null) {
			if (client.networkHandler!!.getPlayerListEntry(client.player?.uuid) != null) {
				ping = client.networkHandler!!.getPlayerListEntry(client.player!!.uuid)!!.latency
			}
		}
		return listOf(ping)
	}

	override val key = "element.fabrihud.latency"
}