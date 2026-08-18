package team.bytephoria.signal;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface SignalComponent {

    @NotNull SignalType type();

    void apply(
            final @NotNull Player player,
            final @NotNull SignalContext context
    );

}
