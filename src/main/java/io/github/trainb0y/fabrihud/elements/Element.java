package io.github.trainb0y.fabrihud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.IllegalFormatException;

/**
 * Represents a HUD element
 */
@ConfigSerializable
public abstract class Element {
    @Nullable
    public String override;

    /**
     * Whether to display this Element
     */
    public boolean enabled;

    /**
     * X component of this Element's position
     */
    public int x;

    /**
     * Y component of this Element's position
     */
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

    /**
     * Renders this Element
     */
    public void render(MinecraftClient client, MatrixStack matrices) {
        Text text;
        try {
            if (this.override != null) {
                text = Text.literal(override.formatted(getArgs(client)));
            } else {
                text = Text.translatable(getKey() + ".display", getArgs(client));
            }
        }
        catch (IllegalFormatException e) {
            text = Text.literal("FORMATTING ERROR");
        }
        client.textRenderer.draw(matrices, text, this.x, this.y, -1);
    }

    public abstract Object[] getArgs(MinecraftClient client);

    /**
     * @return the translation key for this Element's related text
     */
    public abstract String getKey();
}

