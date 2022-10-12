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

	override val height: Int
		get() = MinecraftClient.getInstance().inGameHud.textRenderer.fontHeight + 2

	override val width: Int
		get() = MinecraftClient.getInstance().inGameHud.textRenderer.getWidth(getText())


	override fun render(matrices: MatrixStack?) {
		val client = MinecraftClient.getInstance()
		val text = getText()

		if (background) DrawableHelper.fill(matrices, x, y, x + width + 3, y + height, backgroundColor)
		if (shadow) client.inGameHud.textRenderer.drawWithShadow(matrices, text, x + 2f, y + 2f, -1)
		else client.inGameHud.textRenderer.draw(matrices, text, x + 2f, y + 2f, -1)

	}

	private fun getText(): Text = try {
		val client = MinecraftClient.getInstance()

		if (override != null) Text.literal(override!!.format(*getArgs(client).toTypedArray()))
		else Text.translatable("$key.display", *getArgs(client).toTypedArray())

	} catch (e: IllegalFormatException) {
		Text.translatable("error.fabrihud.formatting")
	}
}