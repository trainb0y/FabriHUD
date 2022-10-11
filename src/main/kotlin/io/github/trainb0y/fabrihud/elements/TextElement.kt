package io.github.trainb0y.fabrihud.elements

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.util.IllegalFormatException

abstract class TextElement : Element() {
	var override: String? = null

	var shadow: Boolean = false
	var background: Boolean = true
	var backgroundColor: Int = -0x6FAFAFB0

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
		if (background) {
			val w: Int = client.inGameHud.textRenderer.getWidth(text)
			val h = client.inGameHud.textRenderer.fontHeight
			DrawableHelper.fill(matrices, x - 2, y - 2, x + w + 1, y + h, backgroundColor)
		}
		if (shadow) {
			client.inGameHud.textRenderer.drawWithShadow(matrices, text, x.toFloat(), y.toFloat(), -1)
		} else {
			client.inGameHud.textRenderer.draw(matrices, text, x.toFloat(), y.toFloat(), -1)
		}
	}
}