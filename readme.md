# signal

<p align="center">
  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/Java-21+-blue" alt="Java"/>
  </a>
  <a href="https://papermc.io/">
    <img src="https://img.shields.io/badge/PaperMC-1.21%2B-green" alt="PaperMC"/>
  </a>
  <a href="license">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License"/>
  </a>
  <a href="https://discord.com/invite/3K9yrZQRmS">
    <img src="https://img.shields.io/discord/1350369915521204276?label=Discord&color=7289DA&logo=discord&logoColor=white" alt="Discord"/>
  </a>
</p>

**signal** is a config-driven, extensible library for dispatching player feedback on the
**Bukkit API**. It turns a single path (`companion.summon-success`) into a composite burst of
feedback: chat message, actionbar, title, sound, potion effect, sent all at once or filtered
down to exactly the pieces a caller needs.

---

## Overview

signal focuses on doing one thing well: letting a plugin say "send the player this feedback"
without hardcoding *which* channels that feedback travels through. The core module has **no
dependency on Configurate or any config format**. It's built purely against Bukkit/Paper, so you
can construct `SignalDefinition`s by hand, from a database, or from any source you want. Config
support is an optional second module (`configurate`) layered on top: it walks a generic
`ConfigurationNode` tree, so it works with any Configurate-supported format (YAML, HOCON, JSON,
GSON) depending on which loader you plug into it.

---

<br>
<p align="center">
    <a href="https://discord.com/invite/3K9yrZQRmS">
        <img src="https://imgur.com/DvyC4jL.png" width="600" alt="nothing">
    </a>
    <br/>
    <i>If you need help, join the discord server.</i>
</p>
<br>

---

## Features

- Composite signal types out of the box: `message`, `action-bar`, `title`, `sound`, `effect`.
- Dot-notation paths (`companion.summon-success`) resolved against arbitrarily nested config, with no fixed schema depth.
- Fluent `SignalDispatch`. Send every configured type, `.only(...)` a subset, `.without(...)` an exclusion, to one player or a whole collection.
- `SignalContext` placeholder resolution, decoupled from any specific player/menu context.
- Pluggable text rendering via `Serializer`. MiniMessage, legacy `&` codes, plain text, or your own implementation.
- Framework-agnostic core. Build `SignalDefinition`s programmatically with zero dependency on Configurate.
- Optional `configurate` module for config-driven definitions via recursive `ConfigurationNode` tree walking, format-agnostic (YAML, HOCON, JSON...).
- No external database, no menu dependency. A single-purpose library.

---

## Requirements

| Dependency  | Version                                    |
|-------------|---------------------------------------------|
| Java        | 21+                                         |
| PaperMC     | 1.21+                                       |
| Configurate | 4.2.0 (`configurate-core`, only required by the `configurate` module; add a loader like `configurate-yaml` yourself) |

---

## Installation

Add the Bytephoria repository, then pick one dependency depending on your needs. The
`configurate` module already brings `signal` transitively, so you don't need to declare both.
It only depends on `configurate-core`, so you also need to add a concrete loader for whichever
format you're using (`configurate-yaml`, `configurate-hocon`, etc.).

```kotlin
repositories {
  maven("https://repo.bytephoria.team/releases")
}

dependencies {
  // Core only: build SignalDefinitions by hand, no Configurate dependency
  implementation("team.bytephoria:signal:1.0.0")

  // Config-driven signal definitions (includes signal transitively)
  implementation("team.bytephoria:configurate:1.0.0")

  // Pick the loader for your format
  implementation("org.spongepowered:configurate-yaml:4.2.0")
}
```

## Quick example

```yaml
companion:
  summon-success:
    message: "<green>You summoned <white><companion></white>!"
    action-bar: "<gray>Your companion is ready"
    title:
      title: "<gold>Summoned!"
      subtitle: "<gray><companion>"
      fade-in: 10
      stay: 40
      fade-out: 10
    sound:
      key: "entity.player.levelup"
      volume: 1.0
      pitch: 1.2
```

```java
final SignalManager signalManager = SignalManager.builder()
        .source(new ConfigurateSignalSource(rootNode, MiniMessageSerializer.INSTANCE))
        .build();

signalManager.of("companion.summon-success")
    .context(SignalContext.builder()
        .placeholder("companion", companion.displayName())
        .build())
        .send(player);
```

---

## Contributing

1. Fork the repository.
2. Create a branch: `git checkout -b feature/my-feature` or `git checkout -b fix/my-fix`
3. Commit your changes and open a Pull Request.

Please follow the existing code style:
- Use `this.` for all instance field references.
- Use `final` on parameters, local variables and fields wherever applicable.
- Respect the module boundaries. `signal` stays framework-agnostic, no Configurate types leak into the core.
- No breaking changes to existing SPI interfaces (`SignalComponent`, `SignalSource`, `Serializer`) without prior discussion.

### Library Philosophy

signal is designed to be a **general-purpose** player feedback dispatcher, not a menu framework
or a plugin-specific tool.
Pull requests that push the library toward a specific use case, such as inventory/GUI rendering,
event hooks, or a hard dependency on a single config format, will be automatically rejected;
that's what your own plugin's integration layer is for.
If you need those kinds of features, you have two supported paths:

- **Register your own `SignalComponent` or `SignalSource`** via the SPI. The library is built
  specifically to be extended without touching its core.
- **Fork the project** if your use case requires tighter integration or custom behavior.

---

## License

This project is released under the [MIT License](license).
You are free to use, modify, and distribute it with attribution.