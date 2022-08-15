package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class PositionElement extends Element {
    public PositionElement() {
        super();
    }

    public PositionElement(int x, int y, boolean enabled) {
        super(x, y, enabled);
    }

    public Object[] getArgs(MinecraftClient client) {
        BlockPos pos = client.player != null ? client.player.getBlockPos() : new BlockPos(0, 0, 0);
        return List.of(pos.getX(), pos.getY(), pos.getZ()).toArray();
    }

    @Override
    public String getKey() {
        return "element.simplehud.position";
    }
}