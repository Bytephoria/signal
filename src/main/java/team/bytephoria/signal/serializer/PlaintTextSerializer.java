package team.bytephoria.signal.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class PlaintTextSerializer implements Serializer {

    public static final PlaintTextSerializer INSTANCE = new PlaintTextSerializer();

    private PlaintTextSerializer() {}

    @Override
    public @NotNull Component serialize(final @NotNull String raw) {
        return Component.text(raw);
    }
}
