package team.bytephoria.signal;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public final class SignalManager {

    private final Map<String, SignalDefinition> definitions;
    private SignalManager(final @NotNull SignalSource signalSource) {
        this.definitions = signalSource.load();
    }

    public static @NotNull Builder builder() {
        return new Builder();
    }

    public @NotNull SignalDispatch of(final @NotNull String path) {
        final SignalDefinition definition = this.definitions.get(path);
        if (definition == null) {
            return SignalDispatch.failed(path);
        }

        return SignalDispatch.of(definition);
    }

    public void send(final @NotNull Player player, String path) {
        this.of(path).send(player);
    }

    public void send(final @NotNull Player player, String path, SignalContext context) {
        this.of(path).context(context).send(player);
    }

    public static final class Builder {

        private SignalSource signalSource;

        private Builder() {
            this.signalSource = null;
        }

        public Builder source(final @NotNull SignalSource source) {
            this.signalSource = source;
            return this;
        }

        public @NotNull SignalManager build() {
            Objects.requireNonNull(this.signalSource, "'source' must not be null");
            return new SignalManager(this.signalSource);
        }
    }
}
