package io.github.trainb0y.fabrihud.config.gui

import io.github.trainb0y.fabrihud.elements.Element
import io.github.trainb0y.fabrihud.elements.TextElement
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW
import java.util.function.Consumer
import kotlin.math.roundToInt

class PositionScreen(private val parent: Screen, private val elements: List<Element>) :
	Screen(Text.translatable("config.fabrihud.title")) {

	private var lastElement: Element? = null
	private var relativeX = 0.0
	private var relativeY = 0.0

	override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
		super.render(matrices, mouseX, mouseY, delta)
		// Render the elements
		elements.forEach(Consumer { element: Element -> element.render(matrices) })
	}

	// Moves selected elements to mouse position
	override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && lastElement != null) {
			lastElement!!.x = (mouseX - relativeX).roundToInt()
			lastElement!!.y = (mouseY - relativeY).roundToInt()
		}
		return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
	}

	// Handles selecting of elements
	override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
			elements.forEach(Consumer { element: Element ->
				if (element !is TextElement) return@Consumer // we'll have to figure out something different for other elements
				// this could be done better
				// checks if the mouse is over the element
				if (element.x <= mouseX && mouseX <= element.x + element.width && element.y <= mouseY && mouseY <= element.y + element.height) {
					relativeX = mouseX - element.x
					relativeY = mouseY - element.y
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