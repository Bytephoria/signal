package team.bytephoria.signal.serializer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public final class MiniMessageSerializer implements Serializer {

    public static final MiniMessageSerializer INSTANCE = new MiniMessageSerializer();

    private MiniMessageSerializer() {}

    @Override
    public @NotNull Component serialize(final @NotNull String raw) {
        return MiniMessage.miniMessage().deserialize(raw);
    }

}
