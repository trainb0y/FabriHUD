package io.github.trainb0y.simplehud.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class LatencyElement extends Element {
    public LatencyElement() {super();}
    public LatencyElement(int x, int y, boolean enabled) {super(x, y, enabled);}

    public Text getText(MinecraftClient client) {
        int ping = -1;
        if (client.getNetworkHandler() != null) {
            if (client.getNetworkHandler().getPlayerListEntry(client.player != null ? client.player.getUuid() : null) != null) {
                ping = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
            }
        }
        return Text.translatable(getKey() + ".display", ping);
    }

    @Override
    public String getKey() {
        return "element.simplehud.latency";
    }
}
