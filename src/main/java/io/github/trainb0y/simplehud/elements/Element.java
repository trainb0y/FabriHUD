package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public abstract class Element {
    public Element(int x, int y, boolean enabled) {
        this.x = x;
        this.y = y;
        this.enabled = enabled;
    }

    // Needed for Configurate
    // "Objects must have a zero-argument constructor to be able to create new instances"
    public Element() {
    }

    public boolean enabled;
    public int x;
    public int y;

    public void render(MinecraftClient client, MatrixStack matrices) {
        client.textRenderer.draw(matrices, this.getText(client), this.x, this.y, -1);
    }

    public abstract Text getText(MinecraftClient client);

    public abstract String getKey();
}

