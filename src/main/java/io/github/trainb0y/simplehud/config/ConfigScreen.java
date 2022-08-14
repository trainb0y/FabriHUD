package io.github.trainb0y.simplehud.config;

import dev.lambdaurora.spruceui.screen.SpruceScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ConfigScreen extends SpruceScreen {
    private final Screen parent;

    public ConfigScreen(@Nullable Screen parent) {
        super(Text.translatable("config.simplehud.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
    }

}
