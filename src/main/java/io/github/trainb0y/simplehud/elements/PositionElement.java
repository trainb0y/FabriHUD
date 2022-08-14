package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class PositionElement extends Element {
    public PositionElement() {super();}
    public PositionElement(int x, int y, boolean enabled) {super(x, y, enabled);}

    public Text getText(MinecraftClient client) {
        BlockPos pos = client.player != null ? client.player.getBlockPos() : new BlockPos(0, 0,0);
        return Text.translatable(getKey() + ".display", pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public String getKey() {
        return "element.simplehud.position";
    }
}