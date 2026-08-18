package team.bytephoria.signal;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class SignalDispatch {

    private final SignalDefinition definition;
    private final String missingPath;

    private SignalContext context = SignalContext.empty();
    private Set<SignalType> only;

    private SignalDispatch(
            final @Nullable SignalDefinition definition,
            final @Nullable String missingPath
    ) {
        this.definition = definition;
        this.missingPath = missingPath;
        this.only = definition != null ? definition.types() : Set.of();
    }

    static @NotNull SignalDispatch of(final @NotNull SignalDefinition definition) {
        return new SignalDispatch(definition,null);
    }

    static @NotNull SignalDispatch failed(final @NotNull String path) {
        return new SignalDispatch(null, path);
    }

    public SignalDispatch context(SignalContext context) {
        this.context = context;
        return this;
    }

    public SignalDispatch only(final @Nullable SignalType @NotNull ... types) {
        if (this.definition == null) {
            return this;
        }

        this.only = EnumSet.copyOf(Arrays.asList(types));
        return this;
    }

    public SignalDispatch without(final @NotNull SignalType @NotNull ... types) {
        if (this.definition == null) {
            return this;
        }

        final Set<SignalType> excluded = EnumSet.copyOf(Arrays.asList(types));
        this.only = this.definition.types().stream()
                .filter(type -> !excluded.contains(type))
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(SignalType.class)));

        return this;
    }

    public @Nullable String missingPath() {
        return this.missingPath;
    }

    public void send(final @NotNull Player player) {
        if (this.definition == null) {
            return;
        }

        for (final SignalType signalType : this.only) {
            this.definition.component(signalType)
                    .ifPresent(component -> component.apply(player, this.context));
        }

    }

    public void send(final @NotNull Collection<? extends Player> players) {
        players.forEach(this::send);
    }

}
