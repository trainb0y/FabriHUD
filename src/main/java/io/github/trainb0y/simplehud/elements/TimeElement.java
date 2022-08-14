package io.github.trainb0y.simplehud.elements;

import io.github.trainb0y.simplehud.mixin.MinecraftClientMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TimeElement extends Element {
    public TimeElement() {super();}
    public TimeElement(int x, int y, boolean enabled) {super(x, y, enabled);}

    public Text getText(MinecraftClient client) {
        int hours= (int) ((client.world != null ? client.world.getTimeOfDay() : 0) / 1000 + 6 ) % 24;
        int minutes = (int) ((client.world != null ? client.world.getTimeOfDay() : 0) % 1000) * 60 / 1000;
        return Text.translatable(getKey() + ".display", String.format("%02d:%02d", hours, minutes));
    }

    @Override
    public String getKey() {
        return "element.simplehud.time";
    }
}