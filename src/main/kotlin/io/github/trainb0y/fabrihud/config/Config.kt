package io.github.trainb0y.fabrihud.config

import io.github.trainb0y.fabrihud.FabriHUD
import io.github.trainb0y.fabrihud.elements.BiomeElement
import io.github.trainb0y.fabrihud.elements.Element
import io.github.trainb0y.fabrihud.elements.FPSElement
import io.github.trainb0y.fabrihud.elements.LatencyElement
import io.github.trainb0y.fabrihud.elements.LightElement
import io.github.trainb0y.fabrihud.elements.PositionElement
import io.github.trainb0y.fabrihud.elements.TimeElement
import net.fabricmc.loader.api.FabricLoader
import org.spongepowered.configurate.CommentedConfigurationNode
import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import java.nio.file.Path

@ConfigSerializable
object Config {
	private const val version = "1.0"

	@JvmField
	var hudEnabled = true


	@JvmStatic
	var elements = listOf<Element>()
		private set

	private val configPath: Path = FabricLoader.getInstance().configDir.resolve("fabrihud.conf")

	fun loadConfig() {
		val loader = HoconConfigurationLoader.builder()
			.path(configPath)
			.build()
		val root: CommentedConfigurationNode

		try {
			root = loader.load()
			val configVersion = root.node("version").string
			if (version != configVersion) {
				FabriHUD.logger.warn("Found config version: $configVersion, current version: $version")
				FabriHUD.logger.warn("Attempting to load anyway")
			}

			hudEnabled = root.node("enabled").boolean
			elements = root.node("elements").getList(Element::class.java)!!.toList()


			if (elements.isEmpty()) throw NullPointerException() // configurate doesn't error when file not found
			FabriHUD.logger.info("Loaded existing configuration")
			return

		} catch (e: ConfigurateException) {
			FabriHUD.logger.warn("Failed to load existing configuration! Using defaults. $e")
		} catch (e: NullPointerException) {
			FabriHUD.logger.warn("Invalid configuration file! Using defaults.")
		}
		applyDefaultConfig()
	}

	fun applyDefaultConfig() {
		hudEnabled = true
		elements = listOf(
			FPSElement(),
			BiomeElement(),
			LatencyElement(),
			PositionElement(),
			TimeElement(),
			LightElement()
		)
		elements.forEach { it.apply {
			this.x = 5
			this.y = 10
			this.enabled = false
		}}
		elements.first().apply {
			this.y = 5
			this.enabled = true
		}
		FabriHUD.logger.info("Applied default config settings")
	}

	fun saveConfig() {
		val loader = HoconConfigurationLoader.builder()
			.path(configPath)
			.build()
		val root: CommentedConfigurationNode
		try {
			root = loader.load()
			root.node("version").set(version)
			root.node("enabled").set(hudEnabled)
			root.node("elements").setList(Element::class.java, elements)
			loader.save(root)
			FabriHUD.logger.info("Saved configuration")
		} catch (e: ConfigurateException) {
			FabriHUD.logger.warn("Failed to save configuration! $e")
		}
	}
}