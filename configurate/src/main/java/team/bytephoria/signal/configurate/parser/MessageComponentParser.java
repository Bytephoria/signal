package team.bytephoria.signal.configurate.parser;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import team.bytephoria.signal.component.MessageSignalMessage;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;
import team.bytephoria.signal.serializer.Serializer;

import java.util.List;

public final class MessageComponentParser {

    private MessageComponentParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull MessageSignalMessage parse(
            final @NotNull Serializer serializer,
            final @NotNull ConfigurationNode configurationNode
    ) {
        try {
            if (configurationNode.isList()) {
                return new MessageSignalMessage(serializer, configurationNode.getList(String.class, List.of()));
            }

            return new MessageSignalMessage(serializer, List.of(configurationNode.getString("")));
        } catch (SerializationException exception) {
            throw new IllegalStateException("No se pudo parsear 'message' en " + configurationNode.path(), exception);
        }
    }
}
