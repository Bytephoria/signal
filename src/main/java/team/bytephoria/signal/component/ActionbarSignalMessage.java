package team.bytephoria.signal.component;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.SignalType;
import team.bytephoria.signal.serializer.Serializer;

public final class ActionbarSignalMessage extends TextSignalMessage {

    private final String text;

    public ActionbarSignalMessage(
            final @NotNull Serializer serializer,
            final @NotNull String text
    ) {
        super(serializer);
        this.text = text;
    }

    @Override
    public @NonNull SignalType type() {
        return SignalType.ACTIONBAR;
    }

    @Override
    public void apply(final @NotNull Player player, final @NotNull SignalContext context) {
        final String resolved = context.resolve(this.text);
        player.sendActionBar(super.render(resolved, context));
    }
}
