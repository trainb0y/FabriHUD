package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;

import java.util.List;

public class TimeElement extends Element {
    public TimeElement() {
        super();
    }

    public TimeElement(int x, int y, boolean enabled) {
        super(x, y, enabled);
    }

    public Object[] getArgs(MinecraftClient client) {
        int hours = (int) ((client.world != null ? client.world.getTimeOfDay() : 0) / 1000 + 6) % 24;
        int minutes = (int) ((client.world != null ? client.world.getTimeOfDay() : 0) % 1000) * 60 / 1000;
        return List.of(hours, minutes).toArray();
    }

    @Override
    public String getKey() {
        return "element.simplehud.time";
    }
}