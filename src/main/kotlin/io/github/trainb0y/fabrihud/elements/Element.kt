package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import org.spongepowered.configurate.objectmapping.ConfigSerializable

/**
 * Represents a HUD element
 */
@ConfigSerializable
abstract class Element {
	/** Whether to display this Element */
	@JvmField
	var enabled = false

	/** X component of this Element's position */
	var x = 0

	/** Y component of this Element's position */
	var y = 0

	/** The width of this element when rendered */
	abstract val width: Int

	/** The height of this element when rendered */
	abstract val height: Int

	/** Renders this Element */
	abstract fun render(matrices: MatrixStack?)

	abstract fun getArgs(client: MinecraftClient): List<Any?>

	/** The translation key for this Element's related text */
	abstract val key: String
}