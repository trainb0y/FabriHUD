package io.github.trainb0y.simplehud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Element {

    public Element(String key, int x, int y, ElementDisplay display, boolean enabled) {
        this.key = key;
        this.x = x;
        this.y = y;
        this.display = display;
        this.enabled = enabled;
    }

    public boolean enabled;
    public int x;
    public int y;
    public ElementDisplay display;
    public String key;

    public void render(MinecraftClient client, MatrixStack matrices) {
        client.textRenderer.draw(matrices, this.display.getText(client), this.x, this.y, -1);
    }
}

