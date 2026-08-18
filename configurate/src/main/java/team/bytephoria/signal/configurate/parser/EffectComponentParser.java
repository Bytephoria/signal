package team.bytephoria.signal.configurate.parser;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;

import team.bytephoria.signal.component.EffectSignalComponent;
import team.bytephoria.signal.configurate.util.NonInstantiableClassException;

import java.util.Locale;

public final class EffectComponentParser {

    private static final int DEFAULT_DURATION = 100;
    private static final int DEFAULT_AMPLIFIER = 0;

    private EffectComponentParser() {
        throw new NonInstantiableClassException();
    }

    public static @NotNull EffectSignalComponent parse(final @NotNull ConfigurationNode configurationNode) {
        final PotionEffectType effectType = resolveEffectType(configurationNode.node("type").getString());

        final int duration = configurationNode.node("duration").getInt(DEFAULT_DURATION);
        final int amplifier = configurationNode.node("amplifier").getInt(DEFAULT_AMPLIFIER);
        final boolean ambient = configurationNode.node("ambient").getBoolean(false);
        final boolean particles = configurationNode.node("particles").getBoolean(true);

        return new EffectSignalComponent(effectType, duration, amplifier, ambient, particles);
    }

    private static @NotNull PotionEffectType resolveEffectType(final @Nullable String key) {
        if (key == null) {
            throw new IllegalStateException("Falta la clave 'type' del effect");
        }

        final NamespacedKey namespacedKey = NamespacedKey.minecraft(key.toLowerCase(Locale.ROOT));
        final PotionEffectType potionEffectType = Registry.EFFECT.get(namespacedKey);
        if (potionEffectType == null) {
            throw new IllegalStateException("Efecto de poción desconocido: " + key);
        }

        return potionEffectType;
    }
}
