package team.bytephoria.signal.component;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.SignalType;
import team.bytephoria.signal.serializer.Serializer;

import java.util.List;

public final class MessageSignalMessage extends TextSignalMessage {

    private final List<String> lines;

    public MessageSignalMessage(
            final @NotNull Serializer serializer,
            final @NotNull List<String> lines
    ) {
        super(serializer);
        this.lines = lines;
    }

    @Override
    public @NonNull SignalType type() {
        return SignalType.MESSAGE;
    }

    @Override
    public void apply(final @NotNull Player player, final @NotNull SignalContext context) {
        for (final String line : this.lines) {
            player.sendMessage(super.render(line, context));
        }
    }
}
