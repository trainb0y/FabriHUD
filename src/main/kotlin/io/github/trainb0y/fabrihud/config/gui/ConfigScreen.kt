package io.github.trainb0y.fabrihud.config.gui

import dev.lambdaurora.spruceui.Position
import dev.lambdaurora.spruceui.SpruceTexts
import dev.lambdaurora.spruceui.background.DirtTexturedBackground
import dev.lambdaurora.spruceui.option.SpruceBooleanOption
import dev.lambdaurora.spruceui.option.SpruceIntegerInputOption
import dev.lambdaurora.spruceui.option.SpruceSeparatorOption
import dev.lambdaurora.spruceui.option.SpruceSimpleActionOption
import dev.lambdaurora.spruceui.option.SpruceStringOption
import dev.lambdaurora.spruceui.option.SpruceToggleBooleanOption
import dev.lambdaurora.spruceui.screen.SpruceScreen
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget
import io.github.trainb0y.fabrihud.config.Config
import io.github.trainb0y.fabrihud.elements.Element
import io.github.trainb0y.fabrihud.elements.TextElement
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.util.function.Consumer

/**
 * Primary configuration screen
 */
class ConfigScreen(private val parent: Screen?) : SpruceScreen(Text.translatable("config.fabrihud.title")) {
	private var optionList: SpruceOptionListWidget? = null

	override fun renderTitle(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
		drawCenteredText(matrices, textRenderer, title, width / 2, 8, 0xFFFFFF)
	}

	override fun init() {
		super.init()
		optionList = SpruceOptionListWidget(Position.of(0, 22), width, height - 36 - 22)
		optionList!!.background = DirtTexturedBackground.DARKENED
		optionList!!.addOptionEntry(SpruceBooleanOption("config.fabrihud.option.enabled",
				{ Config.hudEnabled },
				{ value: Boolean? -> Config.hudEnabled = value!! },
				Text.translatable("config.fabrihud.option.enabled.tooltip")
		), SpruceSimpleActionOption.of("config.fabrihud.editpositions",
				{ client!!.setScreen(PositionScreen(this, Config.elements.stream().filter { element: Element -> element.enabled }.toList())) },
				Text.translatable("config.fabrihud.editposition.tooltip")
		))
		Config.elements.forEach(Consumer { element: Element ->
			optionList!!.addSingleOptionEntry(SpruceSeparatorOption(element.key + ".name", true, Text.translatable(element.key + ".tooltip")))
			optionList!!.addOptionEntry(SpruceToggleBooleanOption("config.fabrihud.enabled",
					{ element.enabled },
					{ value: Boolean? -> element.enabled = value!! },
					null
			),
					SpruceSimpleActionOption.of("config.fabrihud.editposition",
							{ client!!.setScreen(PositionScreen(this, listOf(element))) },
							Text.translatable("config.fabrihud.editposition.tooltip")
					))
			if (element is TextElement) {
				optionList!!.addSingleOptionEntry(SpruceStringOption("config.fabrihud.override",
					{ element.override },
					{ value: String -> if (value.strip() === "") element.override = null else element.override = value },
					null,
					Text.translatable("config.fabrihud.override.tooltip")
				))
			}
			optionList!!.addOptionEntry(SpruceIntegerInputOption("config.fabrihud.editposition.x",
					{ element.x },
					{ value: Int? -> element.x = value!! },
					Text.translatable("config.fabrihud.editposition.x.tooltip")
			), SpruceIntegerInputOption("config.fabrihud.editposition.y",
					{ element.y },
					{ value: Int? -> element.y = value!! },
					Text.translatable("config.fabrihud.editposition.y.tooltip")
			))
		})
		addDrawableChild(optionList)
		addDrawableChild(SpruceButtonWidget(Position.of(this, width / 2 - 155 + 160, height - 29), 150, 20, SpruceTexts.GUI_DONE
		) {
			Config.saveConfig()
			this.client!!.setScreen(parent)
		}.asVanilla())
		addDrawableChild(SpruceButtonWidget(Position.of(this, width / 2 - 155, height - 29), 150, 20, SpruceTexts.CONTROLS_RESET
		) {
			Config.applyDefaultConfig()
			Config.saveConfig()
			this.client!!.setScreen(ConfigScreen(parent)) // values don't automatically update, so...
		}.asVanilla())
	}
}