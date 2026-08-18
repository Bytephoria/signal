package team.bytephoria.signal;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class SignalContext {

    private final Map<String, String> placeholders;
    private SignalContext(final @NotNull Map<String, String> placeholders) {
        this.placeholders = placeholders;
    }

    public static @NotNull SignalContext empty() {
        return new SignalContext(Map.of());
    }

    public static @NotNull Builder builder() {
        return new Builder();
    }

    public @NotNull String resolve(final @NotNull String raw) {
        if (this.placeholders.isEmpty()) {
            return raw;
        }

        final char[] chars = raw.toCharArray();
        final StringBuilder output = new StringBuilder(chars.length);

        int i = 0;
        while (i < chars.length) {
            if (chars[i] != '{') {
                output.append(chars[i++]);
                continue;
            }

            final int start = ++i;
            while (i < chars.length && chars[i] != '}') {
                i++;
            }

            if (i >= chars.length) {
                output.append(chars, start - 1, chars.length - start + 1);
                break;
            }

            final String key = new String(chars, start, i - start);
            final String value = this.placeholders.get(key);

            if (value != null) {
                output.append(value);
            } else {
                output.append('{').append(key).append('}');
            }

            i++;
        }

        return output.toString();
    }

    public static final class Builder {

        private final Map<String, String> placeholders;

        private Builder() {
            this.placeholders = new HashMap<>();
        }

        public Builder placeholder(final @NotNull String key, final @NotNull String value) {
            this.placeholders.put(key, value);
            return this;
        }

        public Builder placeholder(final @NotNull String key, final int value) {
            this.placeholders.put(key, Integer.toString(value));
            return this;
        }

        public @NotNull SignalContext build() {
            return new SignalContext(Map.copyOf(this.placeholders));
        }
    }
}
