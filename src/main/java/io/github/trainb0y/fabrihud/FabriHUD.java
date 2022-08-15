package io.github.trainb0y.fabrihud;

import io.github.trainb0y.fabrihud.config.Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabriHUD implements ClientModInitializer {
    public static final Logger logger = LoggerFactory.getLogger("fabrihud");

    public void onInitializeClient() {
        Config.loadConfig();
    }
}
