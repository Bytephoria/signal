package team.bytephoria.signal.configurate;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import team.bytephoria.signal.SignalDefinition;
import team.bytephoria.signal.SignalSource;
import team.bytephoria.signal.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ConfigurateSignalSource implements SignalSource {

    private static final Set<String> LEAF_KEYS =
            Set.of("message", "action-bar", "title", "sound", "effect");

    private final ConfigurationNode configurationNode;
    private final Serializer serializer;

    public ConfigurateSignalSource(
            final @NotNull ConfigurationNode configurationNode,
            final @NotNull Serializer serializer
    ) {
        this.configurationNode = configurationNode;
        this.serializer = serializer;
    }

    @Override
    public @NotNull Map<String, SignalDefinition> load() {
        final Map<String, SignalDefinition> result = new HashMap<>();
        this.walk(this.configurationNode, "", result);
        return result;
    }

    private void walk(final @NotNull ConfigurationNode node, final @NotNull String path, final @NotNull Map<String, SignalDefinition> out) {
        if (this.isLeaf(node)) {
            out.put(path, SignalDefinitionParser.parse(path, node, this.serializer));
            return;
        }

        for (final Map.Entry<Object, ? extends ConfigurationNode> entry : node.childrenMap().entrySet()) {
            final String childPath = path.isEmpty()
                    ? entry.getKey().toString()
                    : path + "." + entry.getKey();

            this.walk(entry.getValue(), childPath, out);
        }
    }

    private boolean isLeaf(final @NotNull ConfigurationNode node) {
        return node.childrenMap().keySet().stream()
                .anyMatch(key -> LEAF_KEYS.contains(key.toString()));
    }
}
