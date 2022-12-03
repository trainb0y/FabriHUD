package io.github.trainb0y.fabrihud.config.gui

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.gui.screen.Screen

class ModMenuIntegration : ModMenuApi {
	override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
		return ConfigScreenFactory<Screen> { parent: Screen? -> openConfigScreen(parent) }
	}
}