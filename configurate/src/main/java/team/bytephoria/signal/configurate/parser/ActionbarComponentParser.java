package team.bytephoria.signal.configurate.parser;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;

import team.bytephoria.signal.component.ActionbarSignalMessage;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;
import team.bytephoria.signal.serializer.Serializer;

public final class ActionbarComponentParser {

    private ActionbarComponentParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull ActionbarSignalMessage parse(
            final @NotNull Serializer serializer,
            final @NotNull ConfigurationNode configurationNode
    ) {
        return new ActionbarSignalMessage(serializer, configurationNode.getString(""));
    }
}
