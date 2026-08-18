package team.bytephoria.signal;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface SignalSource {

    @NotNull Map<String, SignalDefinition> load();

}
