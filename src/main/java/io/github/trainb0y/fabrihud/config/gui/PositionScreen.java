package io.github.trainb0y.fabrihud.config.gui;

import dev.lambdaurora.spruceui.screen.SpruceScreen;
import io.github.trainb0y.fabrihud.elements.Element;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;


public class PositionScreen extends SpruceScreen {

    private final MinecraftClient client = MinecraftClient.getInstance();
    private final List<Element> elements;
    private final Screen parent;

    @Nullable
    private Element lastElement;

    public PositionScreen(Screen parent, List<Element> elements) {
        super(Text.translatable("config.fabrihud.title"));
        this.elements = elements;
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        // Render the elements
        elements.forEach((element) -> {
            element.render(client, matrices);
        });
    }

    @Override // Moves selected elements to mouse position
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && lastElement != null) {
            this.lastElement.x = (int) mouseX;
            this.lastElement.y = (int) mouseY;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }


    @Override // Handles selecting of elements
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            elements.forEach((element) -> {
                if (Math.pow(element.y - mouseY, 2) + Math.pow(element.x - mouseX, 2) < 200) {
                    this.lastElement = element;
                }
            });
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override // Handles dropping of selected elements
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) this.lastElement = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() { // Close screen and switch to parent
        super.close();
        client.setScreen(parent);
    }
}
