package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.util.IllegalFormatException

abstract class TextElement: Element() {
	var override: String? = null

	var shadow: Boolean = false
	override fun render(client: MinecraftClient, matrices: MatrixStack?) {
		val text: Text? = try {
			if (override != null) {
				Text.literal(override!!.format(*getArgs(client).toTypedArray()))
			} else {
				Text.translatable("$key.display", *getArgs(client).toTypedArray())
			}
		} catch (e: IllegalFormatException) {
			Text.translatable("error.fabrihud.formatting")
		}
		if (shadow) {
			client.inGameHud.textRenderer.drawWithShadow(matrices, text, x.toFloat(), y.toFloat(), -1)
		}
		else {
			client.inGameHud.textRenderer.draw(matrices, text, x.toFloat(), y.toFloat(), -1)
		}
	}
}