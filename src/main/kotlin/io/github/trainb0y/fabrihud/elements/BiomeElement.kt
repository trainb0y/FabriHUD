package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.util.registry.Registry

// Hud element displaying the player's current biome
class BiomeElement : TextElement() {
	override fun getArgs(client: MinecraftClient): List<Any?> {
		val biome = client.world?.getBiome(client.player?.blockPos)?.value()
		var name = client.world?.registryManager?.get(Registry.BIOME_KEY)?.getId(biome).toString()
		try {
			name = name.split(":")[1].replace("_", " ")
		} catch (ignored: IndexOutOfBoundsException) {
		}
		return listOf(name) // formatted biome name
	}

	override val key = "element.fabrihud.biome"
}