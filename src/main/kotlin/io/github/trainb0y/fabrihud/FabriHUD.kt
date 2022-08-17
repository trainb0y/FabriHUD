package io.github.trainb0y.fabrihud

import io.github.trainb0y.fabrihud.config.Config.loadConfig
import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FabriHUD : ClientModInitializer {
	override fun onInitializeClient() {
		loadConfig()
	}

	companion object {
		val logger: Logger = LoggerFactory.getLogger("io/github/trainb0y/fabrihud")
	}
}