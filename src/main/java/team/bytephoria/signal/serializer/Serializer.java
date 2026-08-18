package team.bytephoria.signal.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Serializer {

    @NotNull Component serialize(final @NotNull String raw);

    default @NotNull Component serializeOrEmpty(final @Nullable String raw) {
        return raw == null || raw.isBlank() ? Component.empty() : serialize(raw);
    }


}
