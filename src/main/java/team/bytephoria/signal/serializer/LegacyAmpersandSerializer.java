package team.bytephoria.signal.serializer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class LegacyAmpersandSerializer implements Serializer {

    public static final LegacyAmpersandSerializer INSTANCE = new LegacyAmpersandSerializer();

    private LegacyAmpersandSerializer() {}

    @Override
    public @NotNull Component serialize(final @NotNull String raw) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(raw);
    }
}
