package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeElement extends Element {
    public BiomeElement() {super();}
    public BiomeElement(int x, int y, boolean enabled) {super(x, y, enabled);}

    public Text getText(MinecraftClient client) {
        Biome biome = client.world != null ? client.world.getBiome(client.player != null ? client.player.getBlockPos() : null).value() : null;
        String name = String.valueOf(client.world != null ? client.world.getRegistryManager().get(Registry.BIOME_KEY).getId(biome) : null);
        try {
            name = name.split(":")[1].replace("_", " ");
        } catch (IndexOutOfBoundsException ignored) {}

        return Text.translatable(getKey() + ".display", name);
    }

    @Override
    public String getKey() {
        return "element.simplehud.biome";
    }
}
