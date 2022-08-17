package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.util.registry.Registry
import java.util.List

// Hud element displaying the player's current biome
class BiomeElement : Element {
	constructor() : super()
	constructor(x: Int, y: Int, enabled: Boolean) : super(x, y, enabled)

	override fun getArgs(client: MinecraftClient): Array<Any?> {
		val biome = if (client.world != null) client.world!!.getBiome(if (client.player != null) client.player!!.blockPos else null).value() else null
		var name = (if (client.world != null) client.world!!.registryManager.get(Registry.BIOME_KEY).getId(biome) else null).toString()
		try {
			name = name.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].replace("_", " ")
		} catch (ignored: IndexOutOfBoundsException) {
		}
		return List.of(name).toTypedArray() // formatted biome name
	}

	override val key = "element.fabrihud.biome"
}