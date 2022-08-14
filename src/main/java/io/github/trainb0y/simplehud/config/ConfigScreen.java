package io.github.trainb0y.simplehud.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.simplehud.title"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory main = builder.getOrCreateCategory(Text.translatable("config.simplehud.category.main"));
        main.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.simplehud.option.enabled"), Config.hudEnabled)
                        .setDefaultValue(true)
                        .setTooltip(Text.translatable("config.simplehud.option.enabled.tooltip"))
                        .setSaveConsumer(newValue -> Config.hudEnabled = newValue)
                .build()
        );

        ConfigCategory elements = builder.getOrCreateCategory(Text.translatable("config.simplehud.category.elements"));
        Config.elements.forEach((element) -> {
            elements.addEntry(entryBuilder.startBooleanToggle(Text.translatable(element.key + ".name"), element.enabled)
                    .setDefaultValue(element.enabled)
                    .setTooltip(Text.translatable(element.key + ".tooltip"))
                    .setSaveConsumer(newValue -> element.enabled = newValue)
                    .build()
            );
        });

        return builder.build();
    }
}
