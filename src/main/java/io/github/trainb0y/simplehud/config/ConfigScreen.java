package io.github.trainb0y.simplehud.config;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.background.Background;
import dev.lambdaurora.spruceui.background.DirtTexturedBackground;
import dev.lambdaurora.spruceui.option.*;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.SpruceWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import io.github.trainb0y.simplehud.Element;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        this.optionList = new SpruceOptionListWidget(Position.of(0,22), this.width, this.height - 36 -22);
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
            this.optionList.addSingleOptionEntry(new SpruceSeparatorOption(element.key + ".name", true, Text.translatable(element.key + ".tooltip")));
            this.optionList.addOptionEntry(new SpruceToggleBooleanOption("config.simplehud.enabled",
                    () -> element.enabled,
                    newValue -> element.enabled = newValue,
                    null
            ),
                    SpruceSimpleActionOption.of("config.simplehud.editposition",
                            btn -> {
                                client.setScreen(new PositionScreen(this, List.of(element)));
                            },
                            Text.translatable("config.simplehud.editposition.tooltip")
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
                btn -> this.client.setScreen(this.parent)).asVanilla());
    }

}