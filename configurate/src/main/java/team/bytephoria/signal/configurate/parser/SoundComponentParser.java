package team.bytephoria.signal.configurate.parser;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;

import team.bytephoria.signal.component.SoundSignalComponent;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;

public final class SoundComponentParser {

    private static final float DEFAULT_VOLUME = 1.0f;
    private static final float DEFAULT_PITCH = 1.0f;

    private SoundComponentParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull SoundSignalComponent parse(final @NotNull ConfigurationNode configurationNode) {
        if (!configurationNode.isMap()) {
            return new SoundSignalComponent(resolveSound(configurationNode.getString(), DEFAULT_VOLUME, DEFAULT_PITCH));
        }

        final float volume = configurationNode.node("volume").getFloat(DEFAULT_VOLUME);
        final float pitch = configurationNode.node("pitch").getFloat(DEFAULT_PITCH);
        final Sound sound = resolveSound(configurationNode.node("key").getString(), volume, pitch);

        return new SoundSignalComponent(sound);
    }

    private static @NotNull Sound resolveSound(
            final @Nullable String key,
            final float volume,
            final float pitch
    ) {
        if (key == null) {
            throw new IllegalStateException("Falta la clave 'type'/valor de sonido");
        }

        return Sound.sound(Key.key(key), Sound.Source.MASTER, volume, pitch);
    }
}
