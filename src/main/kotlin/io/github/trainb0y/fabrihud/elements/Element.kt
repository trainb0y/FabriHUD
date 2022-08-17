package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import java.util.*

/**
 * Represents a HUD element
 */
@ConfigSerializable
abstract class Element {
	var override: String? = null

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

	constructor(x: Int, y: Int, enabled: Boolean) {
		this.x = x
		this.y = y
		this.enabled = enabled
	}

	// Needed for Configurate
	// "Objects must have a zero-argument constructor to be able to create new instances"
	constructor()

	/**
	 * Renders this Element
	 */
	fun render(client: MinecraftClient, matrices: MatrixStack?) {
		val text: Text?
		text = try {
			if (override != null) {
				Text.literal(override!!.formatted(*getArgs(client)))
			} else {
				Text.translatable(key + ".display", *getArgs(client))
			}
		} catch (e: IllegalFormatException) {
			Text.literal("FORMATTING ERROR")
		}
		client.textRenderer.draw(matrices, text, x.toFloat(), y.toFloat(), -1)
	}

	abstract fun getArgs(client: MinecraftClient): Array<Any?>

	/**
	 * @return the translation key for this Element's related text
	 */
	abstract val key: String
}