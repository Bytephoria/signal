package team.bytephoria.signal.component;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import team.bytephoria.signal.SignalContext;
import team.bytephoria.signal.SignalType;
import team.bytephoria.signal.serializer.Serializer;

import java.time.Duration;

public final class TitleSignalMessage extends TextSignalMessage {

    private final String title;
    private final String subtitle;

    private final Duration fadeIn;
    private final Duration stay;
    private final Duration fadeOut;

    public TitleSignalMessage(
            final @NotNull Serializer serializer,
            final @Nullable String title,
            final @Nullable String subtitle,
            final @NotNull Duration fadeIn,
            final @NotNull Duration stay,
            final @NotNull Duration fadeOut
    ) {
        super(serializer);
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @Override
    public @NonNull SignalType type() {
        return SignalType.TITLE;
    }

    @Override
    public void apply(final @NotNull Player player, final @NotNull SignalContext context) {
        final Component titleComponent = this.title == null
                ? Component.empty()
                : super.render(this.title, context);

        final Component subtitleComponent = this.subtitle == null
                ? Component.empty()
                : super.render(this.subtitle, context);

        final Title.Times times = Title.Times.times(this.fadeIn, this.stay, this.fadeOut);
        player.showTitle(Title.title(titleComponent, subtitleComponent, times));
    }
}
