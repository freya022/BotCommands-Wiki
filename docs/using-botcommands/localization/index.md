# Using localization

This will focus on how bundles are defined using the default configuration.

## Bundle paths

By default, bundles have a `.json` extension and are located in a `bc_localization` folder of your app's resources.

### Root bundle

The first bundle we create is the called the "root" bundle ([`Locale.ROOT`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Locale.html#ROOT)), it does not have a specific language.

This is the last fallback if a locale is not available for a given bundle name.

!!! tip

    Even if you don't provide translations,
    using a root bundle is still useful to separate text content from your code.

!!! example
    The path to a root bundle would be `src/main/resources/bc_localization/MyBundle.json`.

### Localized bundles

Additional bundles can be added for translation purposes, they merge in parent bundles. (parent locales and root locale)

Localized bundles are not required to override all templates,
also, if a bundle with a locale cannot be found, it will try to get the nearest parent locale.

!!! example
    The path to a localized bundle would be `src/main/resources/bc_localization/MyBundle_fr_FR.json`.

### Default bundles

For a given bundle name and locale, the [`DefaultLocalizationMapProvider`](https://docs.bc.freya02.dev/BotCommands-core/io.github.freya022.botcommands.api.localization.providers/-default-localization-map-provider/index.html) will look up bundles for that locale using both the provided name,
but also a bundle with a `-default` suffix before the locale.

The "non-default" bundle will be merged into the `-default` suffixed bundle.

!!! example
    The path to a localized default bundle would be `src/main/resources/bc_localization/MyBundle-default_fr_FR.json`.

## Templates

By default, templates are based on [`MessageFormat`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/text/MessageFormat.html). (except we use named arguments, not 0-based indexes)

!!! example

    === "Simple template"

        ```json
        {
            "path.to.my.template": "Hello world!"
        }
        ```

    === "Nested keys"

        ```json
        {
            "path.to": {
                "my.template": "Hello world!"            
            }
        }
        ```

    === "With arguments"

        ```json
        {
            "path.to.my.template": "Hello {name}!"
        }
        ```

!!! tip
    You can find more details about `MessageFormat` in section 4.1 and 4.2 on [Baeldung](https://www.baeldung.com/java-localization-messages-formatting#1-javas-messageformat).

## Locale providers

A few interfaces define methods which, for a given context, returns a locale.

You can override those providers with your own implementations,
for example, if you wish to have your own per-user or per-guild locale settings.

### For interactions

- [[UserLocaleProvider]] — Should provide the locale of a user, returns [`Interaction#getUserLocale`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/Interaction.html#getUserLocale()) by default
- [[GuildLocaleProvider]] — Should provide the locale of a guild, returns [`Interaction#getGuildLocale`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/Interaction.html#getGuildLocale()) by default

### For text commands

- [[TextCommandLocaleProvider]] — Provides the locale based on the text command's context, by default, this returns [`Guild#getLocale()`](https://docs.jda.wiki/net/dv8tion/jda/api/entities/Guild.html#getLocale()) 
