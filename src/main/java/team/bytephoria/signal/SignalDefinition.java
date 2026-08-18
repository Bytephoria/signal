package team.bytephoria.signal;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class SignalDefinition {

    private final String path;
    private final Map<SignalType, SignalComponent> components;

    public SignalDefinition(
            final @NotNull String path,
            final @NotNull Map<SignalType, SignalComponent> components
    ) {
        this.path = path;
        this.components = components;
    }

    public String path() {
        return this.path;
    }

    public @NotNull Optional<SignalComponent> component(final @NotNull SignalType type) {
        return Optional.ofNullable(this.components.get(type));
    }

    public @NotNull Set<SignalType> types() {
        return this.components.keySet();
    }

    public static @NotNull Builder builder(final @NotNull String path) {
        return new Builder(path);
    }

    public static final class Builder {

        private final String path;
        private final Map<SignalType, SignalComponent> components = new EnumMap<>(SignalType.class);

        private Builder(final @NotNull String path) {
            this.path = path;
        }

        public Builder component(final @NotNull SignalComponent component) {
            this.components.put(component.type(), component);
            return this;
        }

        public @NotNull SignalDefinition build() {
            return new SignalDefinition(this.path, Map.copyOf(this.components));
        }
    }
}
