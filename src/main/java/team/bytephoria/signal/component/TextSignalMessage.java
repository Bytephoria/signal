package team.bytephoria.signal.component;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import team.bytephoria.signal.SignalComponent;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.serializer.Serializer;

public abstract class TextSignalMessage implements SignalComponent {

    private final Serializer serializer;

    public TextSignalMessage(final @NotNull Serializer serializer) {
        this.serializer = serializer;
    }

    // Helper
    protected @NotNull Component render(final @NotNull String raw, final @NotNull SignalContext signalContext) {
        return this.serializer.serialize(signalContext.resolve(raw));
    }

}
