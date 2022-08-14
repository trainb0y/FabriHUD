package io.github.trainb0y.simplehud;

import io.github.trainb0y.simplehud.config.Config;
import io.github.trainb0y.simplehud.mixin.MinecraftClientMixin;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;

import java.util.List;

public class SimpleHud implements ClientModInitializer {

    public void onInitializeClient() {

        Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        );
        Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        ); Config.elements.add(
                new Element(
                        "element.simplehud.test",
                        50,
                        5,
                        (client) -> Text.translatable("element.simplehud.test.display", ((MinecraftClientMixin) client).getCurrentFPS()),
                        true
                )
        );
    }
}
