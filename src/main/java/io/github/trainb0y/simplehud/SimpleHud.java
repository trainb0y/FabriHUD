package io.github.trainb0y.simplehud;

import io.github.trainb0y.simplehud.config.Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleHud implements ClientModInitializer {
    public static final Logger logger = LoggerFactory.getLogger("simplehud");

    public void onInitializeClient() {
        Config.loadConfig();
    }
}
