package io.github.trainb0y.fabrihud.elements;

import net.minecraft.client.MinecraftClient;

import java.util.List;

// HUD element displaying the client-server latency (ping)
public class LatencyElement extends Element {
    public LatencyElement() {
        super();
    }

    public LatencyElement(int x, int y, boolean enabled) {
        super(x, y, enabled);
    }

    public Object[] getArgs(MinecraftClient client) {
        int ping = -1;
        if (client.getNetworkHandler() != null) {
            if (client.getNetworkHandler().getPlayerListEntry(client.player != null ? client.player.getUuid() : null) != null) {
                ping = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
            }
        }
        return List.of(ping).toArray();
    }

    @Override
    public String getKey() {
        return "element.fabrihud.latency";
    }
}
