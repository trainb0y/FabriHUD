package io.github.trainb0y.simplehud.config.gui;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.background.DirtTexturedBackground;
import dev.lambdaurora.spruceui.option.SpruceBooleanOption;
import dev.lambdaurora.spruceui.option.SpruceIntegerInputOption;
import dev.lambdaurora.spruceui.option.SpruceSeparatorOption;
import dev.lambdaurora.spruceui.option.SpruceSimpleActionOption;
import dev.lambdaurora.spruceui.option.SpruceStringOption;
import dev.lambdaurora.spruceui.option.SpruceToggleBooleanOption;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import io.github.trainb0y.simplehud.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Primary configuration screen
 */
public class ConfigScreen extends SpruceScreen {
    private final Screen parent;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private SpruceOptionListWidget optionList;


    public ConfigScreen(@Nullable Screen parent) {
        super(Text.translatable("config.simplehud.title"));
        this.parent = parent;
    }

    @Override
    public void renderTitle(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 0xFFFFFF);
    }


    @Override
    protected void init() {
        super.init();

        this.optionList = new SpruceOptionListWidget(Position.of(0, 22), this.width, this.height - 36 - 22);
        this.optionList.setBackground(DirtTexturedBackground.DARKENED);
        this.optionList.addOptionEntry(new SpruceBooleanOption("config.simplehud.option.enabled",
                () -> Config.hudEnabled,
                value -> Config.hudEnabled = value,
                Text.translatable("config.simplehud.option.enabled.tooltip")
        ), SpruceSimpleActionOption.of("config.simplehud.editpositions",
                btn -> {
                    client.setScreen(new PositionScreen(this, Config.elements.stream().filter(element -> element.enabled).toList()));
                },
                Text.translatable("config.simplehud.editposition.tooltip")
        ));

        Config.elements.forEach((element) -> {
            this.optionList.addSingleOptionEntry(new SpruceSeparatorOption(element.getKey() + ".name", true, Text.translatable(element.getKey() + ".tooltip")));
            this.optionList.addOptionEntry(new SpruceToggleBooleanOption("config.simplehud.enabled",
                            () -> element.enabled,
                            value -> element.enabled = value,
                            null
                    ),
                    SpruceSimpleActionOption.of("config.simplehud.editposition",
                            btn -> {
                                client.setScreen(new PositionScreen(this, List.of(element)));
                            },
                            Text.translatable("config.simplehud.editposition.tooltip")
                    ));
            this.optionList.addSingleOptionEntry(new SpruceStringOption("config.simplehud.override",
                    () -> element.override,
                    value -> {
                        if (value == "") element.override = null;
                        else element.override = value;
                    },
                    null,
                    Text.translatable("config.simplehud.override.tooltip")
            ));

            this.optionList.addOptionEntry(new SpruceIntegerInputOption("config.simplehud.editposition.x",
                    () -> element.x,
                    value -> element.x = value,
                    Text.translatable("config.simplehud.editposition.x.tooltip")
            ), new SpruceIntegerInputOption("config.simplehud.editposition.y",
                    () -> element.y,
                    value -> element.y = value,
                    Text.translatable("config.simplehud.editposition.y.tooltip")
            ));
        });

        this.addDrawableChild(this.optionList);

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 155 + 160, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                (btn) -> {
                    Config.saveConfig();
                    this.client.setScreen(this.parent);
                }).asVanilla());

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 155, this.height - 29), 150, 20, SpruceTexts.CONTROLS_RESET,
                (btn) -> {
                    Config.applyDefaultConfig();
                    Config.saveConfig();
                    this.client.setScreen(new ConfigScreen(this.parent)); // values don't automatically update, so...
                }).asVanilla());
    }

}
