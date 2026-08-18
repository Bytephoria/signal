package team.bytephoria.signal.component;

import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import team.bytephoria.signal.SignalComponent;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.SignalType;

public final class SoundSignalComponent implements SignalComponent {

    private final Sound sound;
    public SoundSignalComponent(final @NotNull Sound sound) {
        this.sound = sound;
    }

    @Override
    public @NonNull SignalType type() {
        return SignalType.SOUND;
    }

    @Override
    public void apply(final @NotNull Player player, final @NotNull SignalContext context) {
        player.playSound(this.sound);
    }
}
