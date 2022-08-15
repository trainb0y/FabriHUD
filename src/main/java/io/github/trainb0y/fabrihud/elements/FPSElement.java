package io.github.trainb0y.fabrihud.elements;

import io.github.trainb0y.fabrihud.mixin.MinecraftClientMixin;
import net.minecraft.client.MinecraftClient;

import java.util.List;

// HUD element displaying framerate
public class FPSElement extends Element {
    public FPSElement() {
        super();
    }

    public FPSElement(int x, int y, boolean enabled) {
        super(x, y, enabled);
    }

    public Object[] getArgs(MinecraftClient client) {
        return List.of(((MinecraftClientMixin) client).getCurrentFPS()).toArray();
    }

    @Override
    public String getKey() {
        return "element.fabrihud.fps";
    }
}
