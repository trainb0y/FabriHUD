package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import java.util.IllegalFormatException

/**
 * Represents a HUD element
 */
@ConfigSerializable
abstract class Element() {
	/**
	 * Whether to display this Element
	 */
	@JvmField
	var enabled = false

	/**
	 * X component of this Element's position
	 */
	var x = 0

	/**
	 * Y component of this Element's position
	 */
	var y = 0

	constructor(x: Int, y: Int, enabled: Boolean) : this() {
		this.x = x
		this.y = y
		this.enabled = enabled
	}

	/**
	 * Renders this Element
	 */
	abstract fun render(client: MinecraftClient, matrices: MatrixStack?)

	abstract fun getArgs(client: MinecraftClient): List<Any?>

	/**
	 * @return the translation key for this Element's related text
	 */
	abstract val key: String
}