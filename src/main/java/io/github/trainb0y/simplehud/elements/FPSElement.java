package io.github.trainb0y.simplehud.elements;

import io.github.trainb0y.simplehud.mixin.MinecraftClientMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class FPSElement extends Element {

    public FPSElement() {
        super();
    }

    public FPSElement(int x, int y, boolean enabled) {
        super(x, y, enabled);
    }

    public Text getText(MinecraftClient client) {
        return Text.translatable(getKey() + ".display", ((MinecraftClientMixin) client).getCurrentFPS());
    }

    @Override
    public String getKey() {
        return "element.simplehud.fps";
    }
}
