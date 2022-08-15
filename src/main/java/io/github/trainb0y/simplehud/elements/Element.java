package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public abstract class Element {
    @Nullable
    public String override;
    public boolean enabled;
    public int x;
    public int y;
    public Element(int x, int y, boolean enabled) {
        this.x = x;
        this.y = y;
        this.enabled = enabled;
    }
    // Needed for Configurate
    // "Objects must have a zero-argument constructor to be able to create new instances"
    public Element() {
    }

    public void render(MinecraftClient client, MatrixStack matrices) {
        Text text;
        if (this.override != null) {
            text = Text.literal(override.formatted(getArgs(client)));
        } else {
            text = Text.translatable(getKey() + ".display", getArgs(client));
        }
        client.textRenderer.draw(matrices, text, this.x, this.y, -1);
    }

    public abstract Object[] getArgs(MinecraftClient client);

    public abstract String getKey();
}

