package team.bytephoria.signal.component;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import team.bytephoria.signal.SignalComponent;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.SignalType;

public final class EffectSignalComponent implements SignalComponent {

    private final PotionEffectType effectType;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean particles;

    public EffectSignalComponent(
        final @NotNull PotionEffectType effectType,
        final int duration,
        final int amplifier,
        final boolean ambient,
        final boolean particles
    ) {
        this.effectType = effectType;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.particles = particles;
    }

    @Override
    public @NonNull SignalType type() {
        return SignalType.EFFECT;
    }

    @Override
    public void apply(final @NotNull Player player, final @NotNull SignalContext context) {
        player.addPotionEffect(new PotionEffect(this.effectType, this.duration, this.amplifier, this.ambient, this.particles));
    }
}
