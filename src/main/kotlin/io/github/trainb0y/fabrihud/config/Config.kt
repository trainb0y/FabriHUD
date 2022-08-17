package io.github.trainb0y.fabrihud.config

import io.github.trainb0y.fabrihud.FabriHUD
import io.github.trainb0y.fabrihud.elements.BiomeElement
import io.github.trainb0y.fabrihud.elements.Element
import io.github.trainb0y.fabrihud.elements.FPSElement
import io.github.trainb0y.fabrihud.elements.LatencyElement
import io.github.trainb0y.fabrihud.elements.PositionElement
import io.github.trainb0y.fabrihud.elements.TimeElement
import net.fabricmc.loader.api.FabricLoader
import org.spongepowered.configurate.CommentedConfigurationNode
import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import java.util.*
import java.util.List

@ConfigSerializable
object Config {
	private const val version = "1.0"

	@JvmField
	var hudEnabled = true

	@JvmField
	var elements = ArrayList<Element>()

	@JvmStatic
	fun loadConfig() {
		val loader = HoconConfigurationLoader.builder()
				.path(FabricLoader.getInstance().configDir.resolve("fabrihud.conf"))
				.build()
		val root: CommentedConfigurationNode
		try {
			root = loader.load()
			val configVersion = root.node("version").string
			if (version == configVersion) {
				FabriHUD.logger.warn("Found config version: " + configVersion + ", current version: " + version)
				FabriHUD.logger.warn("Attempting to load anyway")
			}
			hudEnabled = root.node("enabled").boolean
			elements = ArrayList(Objects.requireNonNull(root.node("elements").getList(Element::class.java)))
			if (elements.size == 0) throw NullPointerException() // configurate doesn't error when file not found
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
		elements = ArrayList()
		hudEnabled = true
		elements.addAll(List.of(
				FPSElement(5, 5, true),
				BiomeElement(5, 10, false),
				LatencyElement(5, 10, false),
				PositionElement(5, 10, false),
				TimeElement(5, 10, false)
		))
		FabriHUD.logger.info("Applied default config settings")
	}

	fun saveConfig() {
		val loader = HoconConfigurationLoader.builder()
				.path(FabricLoader.getInstance().configDir.resolve("fabrihud.conf"))
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