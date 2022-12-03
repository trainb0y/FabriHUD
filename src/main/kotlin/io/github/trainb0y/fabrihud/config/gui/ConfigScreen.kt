package io.github.trainb0y.fabrihud.config.gui

import dev.isxander.yacl.api.Binding
import dev.isxander.yacl.api.ButtonOption
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.OptionGroup
import io.github.trainb0y.fabrihud.config.Config
import io.github.trainb0y.fabrihud.elements.Element
import io.github.trainb0y.fabrihud.elements.TextElement
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.YACLScreen
import dev.isxander.yacl.gui.controllers.ActionController
import dev.isxander.yacl.gui.controllers.TickBoxController
import dev.isxander.yacl.gui.controllers.string.StringController
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text


fun openConfigScreen(parent: Screen?): Screen =
	// Welcome to Builder Hell:tm:
	// We hope you brought popcorn!

	// tbh the SpruceUI config screen was a lot cleaner,
	// but it was kind of ugly, and FabriZoom needed some YACL features,
	// and I'd rather have consistency between mods.

	YetAnotherConfigLib.createBuilder()
		.title(Text.translatable("config.fabrihud.global"))
		.category(ConfigCategory.createBuilder()
			.name(Text.translatable("config.fabrihud.primary"))
			.option(Option.createBuilder(Boolean::class.java)
				.name(Text.translatable("config.fabrihud.option.enabled"))
				.tooltip(Text.translatable("config.fabrihud.option.enabled.tooltip"))
				.binding(
					Binding.generic(
						Config.hudEnabled,
						{ Config.hudEnabled },
						{value -> Config.hudEnabled = value}
					)
				)
				.controller { option ->
					TickBoxController(option)
				}
				.build()
			)
			.option(ButtonOption.createBuilder()
				.name(Text.translatable("config.fabrihud.editpositions"))
				.tooltip(Text.translatable("config.fabrihud.editpositions.tooltip"))
				.action { screen: YACLScreen?, _: ButtonOption? ->
					MinecraftClient.getInstance().setScreen(
						PositionScreen(
							screen as Screen,
							Config.elements.stream().filter { element: Element -> element.enabled }.toList()
						)
					)
				}.controller { option: ButtonOption? ->
					ActionController(option)
				}
				.build()
			)
			.option(ButtonOption.createBuilder()
				.name(Text.translatable("config.fabrihud.reset"))
				.tooltip(Text.translatable("config.fabrihud.reset.tooltip"))
				.action { screen: YACLScreen?, _: ButtonOption? ->
					Config.applyDefaultConfig()
					Config.saveConfig()
					screen?.close()

				}.controller { option: ButtonOption? ->
					ActionController(option)
				}
				.build()
			)
			.build()
		)
		.category(ConfigCategory.createBuilder()
			.name(Text.translatable("config.fabrihud.elements"))
			.also { category ->
				Config.elements.forEach { element ->
					category.group(OptionGroup.createBuilder()
						.name(Text.translatable(element.key + ".name"))
						.tooltip(Text.translatable(element.key + ".tooltip"))
						.option(Option.createBuilder(Boolean::class.java)
							.name(Text.translatable("config.fabrihud.enabled"))
							.tooltip(Text.translatable("config.fabrihud.enabled.tooltip"))
							.binding(
								Binding.generic(
									element.enabled,
									{ element.enabled },
									{value -> element.enabled = value}
								)
							)
							.controller { option ->
								TickBoxController(option)
							}
							.build()
						)
						.option(ButtonOption.createBuilder()
							.name(Text.translatable("config.fabrihud.editposition"))
							.tooltip(Text.translatable("config.fabrihud.editposition.tooltip"))
							.action { screen: YACLScreen?, _: ButtonOption? ->
								MinecraftClient.getInstance().setScreen(
									PositionScreen(
										screen as Screen,
										listOf(element)
									)
								)
							}.controller { option: ButtonOption? ->
								ActionController(option)
							}
							.build()
						)
						.also {group ->
							if (element !is TextElement) return@also
							group.option(Option.createBuilder(String::class.java)
								.name(Text.translatable("config.fabrihud.override"))
								.tooltip(Text.translatable("config.fabrihud.override.tooltip"))
								.binding(
									Binding.generic(
										element.override ?: "",
										{ element.override ?: ""},
										{value -> element.override = value}
									)
								)
								.controller { option ->
									StringController(option)
								}
								.build()
							)
							group.option(Option.createBuilder(Boolean::class.java)
								.name(Text.translatable("config.fabrihud.shadow"))
								.tooltip(Text.translatable("config.fabrihud.shadow.tooltip"))
								.binding(
									Binding.generic(
										element.shadow,
										{ element.shadow },
										{value -> element.shadow = value}
									)
								)
								.controller { option ->
									TickBoxController(option)
								}
								.build()
							)
							group.option(Option.createBuilder(Boolean::class.java)
								.name(Text.translatable("config.fabrihud.background"))
								.tooltip(Text.translatable("config.fabrihud.background.tooltip"))
								.binding(
									Binding.generic(
										element.background,
										{ element.background },
										{value -> element.background = value}
									)
								)
								.controller { option ->
									TickBoxController(option)
								}
								.build()
							)
						}
						.option(Option.createBuilder(Int::class.java)
							.name(Text.translatable("config.fabrihud.editposition.x"))
							.tooltip(Text.translatable("config.fabrihud.editposition.x.tooltip"))
							.binding(
								Binding.generic(
									element.x,
									{ element.x },
									{value -> element.x = value}
								)
							)
							.controller { option ->
								IntegerSliderController(
									option,
									0,
									MinecraftClient.getInstance().window.width,
									1
								)
							}
							.build()
						)
						.option(Option.createBuilder(Int::class.java)
							.name(Text.translatable("config.fabrihud.editposition.y"))
							.tooltip(Text.translatable("config.fabrihud.editposition.y.tooltip"))
							.binding(
								Binding.generic(
									element.y,
									{ element.y },
									{value -> element.y = value}
								)
							)
							.controller { option ->
								IntegerSliderController(
									option,
									0,
									MinecraftClient.getInstance().window.height,
									1
								)
							}
							.build()
						)
						.build()
					)
				}
			}
			.build()
		)
		.save(Config::saveConfig)
		.build()
		.generateScreen(parent)