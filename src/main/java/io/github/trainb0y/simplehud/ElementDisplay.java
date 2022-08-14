package io.github.trainb0y.simplehud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public interface ElementDisplay {
    public Text getText(MinecraftClient client);
}
