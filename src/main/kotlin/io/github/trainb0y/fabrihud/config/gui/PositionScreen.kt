package io.github.trainb0y.fabrihud.config.gui

import dev.lambdaurora.spruceui.screen.SpruceScreen
import io.github.trainb0y.fabrihud.elements.Element
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW
import java.util.function.Consumer

class PositionScreen(private val parent: Screen, private val elements: List<Element>) : SpruceScreen(Text.translatable("config.fabrihud.title")) {

	private var lastElement: Element? = null
	override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
		super.render(matrices, mouseX, mouseY, delta)
		// Render the elements
		elements.forEach(Consumer { element: Element -> element.render(client!!, matrices) })
	}

	// Moves selected elements to mouse position
	override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && lastElement != null) {
			lastElement!!.x = mouseX.toInt()
			lastElement!!.y = mouseY.toInt()
		}
		return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
	}

	// Handles selecting of elements
	override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
			elements.forEach(Consumer { element: Element ->
				if (Math.pow(element.y - mouseY, 2.0) + Math.pow(element.x - mouseX, 2.0) < 200) {
					lastElement = element
				}
			})
		}
		return super.mouseClicked(mouseX, mouseY, button)
	}

	// Handles dropping of selected elements
	override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) lastElement = null
		return super.mouseReleased(mouseX, mouseY, button)
	}

	override fun close() { // Close screen and switch to parent
		super.close()
		client!!.setScreen(parent)
	}
}