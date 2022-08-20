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
import dev.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget
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

	private var tabbed: SpruceTabbedWidget? = null
	override fun shouldCloseOnEsc() = false  // force buttons

	override fun init() {
		super.init()

		tabbed = SpruceTabbedWidget(Position.of(this, 0, 4), this.width, this.height - 35 - 4, this.title)

		tabbed!!.addSeparatorEntry(Text.translatable("config.fabrihud.global"))

		tabbed!!.addTabEntry(Text.translatable("config.fabrihud.primary"), null) { w, h ->
			val globalOptions = SpruceOptionListWidget(Position.of(4, 4), w, h)
			globalOptions.background = DirtTexturedBackground.DARKENED
			globalOptions.addOptionEntry(SpruceBooleanOption("config.fabrihud.option.enabled",
				{ Config.hudEnabled },
				{ value: Boolean? -> Config.hudEnabled = value!! },
				Text.translatable("config.fabrihud.option.enabled.tooltip")
			), SpruceSimpleActionOption.of("config.fabrihud.editpositions",
				{ client!!.setScreen(PositionScreen(this, Config.elements.stream().filter { element: Element -> element.enabled }.toList())) },
				Text.translatable("config.fabrihud.editposition.tooltip")
			))
			globalOptions.addSingleOptionEntry(SpruceSeparatorOption("", false, null))
			globalOptions.addOptionEntry(SpruceSimpleActionOption.of("config.fabrihud.reset",
				{
					Config.applyDefaultConfig()
					Config.saveConfig()
					this.client!!.setScreen(ConfigScreen(parent)) // values don't automatically update, so...
				},
				Text.translatable("config.fabrihud.reset.tooltip")
			), SpruceSimpleActionOption.of("config.fabrihud.cancel",
				{
						Config.loadConfig()
						this.client!!.setScreen(parent)
				},
				Text.translatable("config.fabrihud.cancel.tooltip")
			))
			globalOptions.addSmallSingleOptionEntry(
				SpruceSimpleActionOption.of("config.fabrihud.apply",
					{
						Config.saveConfig()
						this.client!!.setScreen(parent)
					},
					Text.translatable("config.fabrihud.apply.tooltip")
			))
			globalOptions
		}

		tabbed!!.addSeparatorEntry(Text.translatable("config.fabrihud.elements"))

		Config.elements.forEach(Consumer { element: Element ->
			tabbed!!.addTabEntry(Text.translatable(element.key + ".name"), null) { w, h ->
				val optionList = SpruceOptionListWidget(Position.of(4, 4), w, h)
				optionList.addSingleOptionEntry(SpruceSeparatorOption(element.key + ".name", true, Text.translatable(element.key + ".tooltip")))
				optionList.addOptionEntry(SpruceToggleBooleanOption("config.fabrihud.enabled",
					{ element.enabled },
					{ value -> element.enabled = value },
					null
				),
					SpruceSimpleActionOption.of("config.fabrihud.editposition",
						{ client!!.setScreen(PositionScreen(this, listOf(element))) },
						Text.translatable("config.fabrihud.editposition.tooltip")
					))
				if (element is TextElement) {
					optionList.addSingleOptionEntry(SpruceStringOption("config.fabrihud.override",
						{ element.override },
						{ value: String -> if (value.strip() === "") element.override = null else element.override = value },
						null,
						Text.translatable("config.fabrihud.override.tooltip")
					))
					optionList.addOptionEntry(SpruceToggleBooleanOption("config.fabrihud.shadow",
						{ element.shadow },
						{ value -> element.shadow = value},
						Text.translatable("config.fabrihud.shadow.tooltip")
					), null)
				}
				optionList.addOptionEntry(SpruceIntegerInputOption("config.fabrihud.editposition.x",
					{ element.x },
					{ value: Int? -> element.x = value!! },
					Text.translatable("config.fabrihud.editposition.x.tooltip")
				), SpruceIntegerInputOption("config.fabrihud.editposition.y",
					{ element.y },
					{ value: Int? -> element.y = value!! },
					Text.translatable("config.fabrihud.editposition.y.tooltip")
				))
				optionList
			}
		})
		addDrawableChild(tabbed)
	}
}