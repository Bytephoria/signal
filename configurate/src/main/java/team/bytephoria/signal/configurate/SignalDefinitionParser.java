package team.bytephoria.signal.configurate;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;

import team.bytephoria.signal.SignalDefinition;
import team.bytephoria.signal.configurate.parser.*;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;
import team.bytephoria.signal.serializer.Serializer;

public final class SignalDefinitionParser {

    private SignalDefinitionParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull SignalDefinition parse(
            final @NotNull String path,
            final @NotNull ConfigurationNode configurationNode,
            final @NotNull Serializer serializer
    ) {
        final SignalDefinition.Builder builder = SignalDefinition.builder(path);
        final ConfigurationNode messageNode = configurationNode.node("message");
        if (!messageNode.virtual()) {
            builder.component(MessageComponentParser.parse(serializer, messageNode));
        }

        final ConfigurationNode actionbarNode = configurationNode.node("action-bar");
        if (!actionbarNode.virtual()) {
            builder.component(ActionbarComponentParser.parse(serializer, actionbarNode));
        }

        final ConfigurationNode titleNode = configurationNode.node("title");
        if (!titleNode.virtual()) {
            builder.component(TitleComponentParser.parse(serializer, titleNode));
        }

        final ConfigurationNode soundNode = configurationNode.node("sound");
        if (!soundNode.virtual()) {
            builder.component(SoundComponentParser.parse(soundNode));
        }

        final ConfigurationNode effectNode = configurationNode.node("effect");
        if (!effectNode.virtual()) {
            builder.component(EffectComponentParser.parse(effectNode));
        }

        return builder.build();
    }
}
