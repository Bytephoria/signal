package team.bytephoria.signal.configurate.parser;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import team.bytephoria.signal.component.TitleSignalMessage;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;
import team.bytephoria.signal.serializer.Serializer;

import java.time.Duration;

public final class TitleComponentParser {

    private static final int DEFAULT_FADE_IN = 10;
    private static final int DEFAULT_STAY = 40;
    private static final int DEFAULT_FADE_OUT = 10;

    private TitleComponentParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull TitleSignalMessage parse(
            final @NotNull Serializer serializer,
            final @NotNull ConfigurationNode configurationNode
    ) {
        final String title = configurationNode.node("title").getString();
        final String subtitle = configurationNode.node("subtitle").getString();

        final int fadeInTicks = configurationNode.node("fade-in").getInt(DEFAULT_FADE_IN);
        final int stayTicks = configurationNode.node("stay").getInt(DEFAULT_STAY);
        final int fadeOutTicks = configurationNode.node("fade-out").getInt(DEFAULT_FADE_OUT);

        return new TitleSignalMessage(
                serializer,
                title,
                subtitle,
                ticksToDuration(fadeInTicks),
                ticksToDuration(stayTicks),
                ticksToDuration(fadeOutTicks)
        );
    }

    private static Duration ticksToDuration(int ticks) {
        return Duration.ofMillis(ticks * 50L);
    }
}
