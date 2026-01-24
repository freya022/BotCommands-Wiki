# Responding with type-safe messages

[//]: # (TODO when this becomes stable, add tips on other pages mentioning this is recommended)

!!! warning

    This method works but the API is not stable yet, a new version could bring breaking changes.

The framework provides a module to define functions which retrieves translated messages,
without having to implement anything, alongside a few other benefits:

- Checks if the templates exists in your bundles, ensuring your content can always be displayed
- Checks if function parameters exists in your template's arguments, meaning all parameters map to an argument
- Checks if template arguments map to function parameters, so all arguments have values
- Removes the need for magic strings (for the arguments), improving type safety and making regressions appear immediately

!!! tip

    Even if you don't provide translations,
    using a "root" bundle is still useful to separate content from your code,
    and if you decide to add translations later, your code is already ready for it!

## Installation

See the [README](https://github.com/freya022/BotCommands/blob/3.X/BotCommands-typesafe-messages/README.md#installation).

## Example

### Creating a localization bundle

Assuming we have a `MyBotMessages` bundle:

```json
{
  "bot.info": "I am in {guild_count, number} {guild_count, choice, 0#guilds|1#guild|1<guilds} and I am up since {uptime_timestamp}."
}
```

??? info "Bundle content details"

    - The key is `bot.info`
    - The template is `I am in {guild_count, number} {guild_count, choice, 0#guilds|1#guild|1<guilds} and I am up since {uptime_timestamp}.`
        - `guild_count` and `uptime_timestamp` are variables
        - `number` and `choice` are format types
        - `0#guilds|1#guild|1<guilds` is a subformat pattern for [ChoiceFormat](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/text/ChoiceFormat.html)
        - See [MessageFormat](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/text/MessageFormat.html) for more details!

### Creating our message source

Create an interface which extends [[IMessageSource]];
it will contain functions annotated with [[LocalizedContent]],
the annotation's value is the key present in your localization bundle,
and the function needs to return a `String`.

=== "Kotlin"

    ```kotlin
    --8<-- "messages/CommandRepliesKt.kt:command_replies-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "messages/CommandReplies.java:command_replies-java"
    ```

!!! tip
    You can inject instances of this interface in any interaction handler such as application commands, components and modals.

!!! tip
    You can override the locale using [[PreferLocale]] or by passing a [`DiscordLocale`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/DiscordLocale.html) or a [`Locale`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Locale.html) in the first parameter.

### Creating a factory for our source

We then need a way to get instances of our source;
create an interface extending [`IMessageSourceFactory<CommandReplies>`][[IMessageSourceFactory]]
and annotate it with [`@MessageSourceFactory("MyBotMessages")`][[MessageSourceFactory]],
the `MyBotMessages` string is the name of the bundle we added in the first step.

=== "Kotlin"

    ```kotlin
    --8<-- "messages/CommandRepliesKtFactory.kt:command_replies_factory-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "messages/CommandRepliesFactory.java:command_replies_factory-java"
    ```

Instances of this interface can be injected like any other service,
and will allow you to create `CommandReplies` instances from an `Interaction`.

### Usage

=== "Kotlin"

    ```kotlin
    --8<-- "commands/slash/SlashInfo.kt:slash_info-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "commands/slash/SlashInfo.java:slash_info-java"
    ```

!!! tip
    Injecting the `CommandReplies` instance in the slash command function
    is the same as injecting `CommandRepliesFactory` in your class then using it in your command to create instances of `CommandReplies`.

Try out `/info`!

## Improve safety across locales

It is **highly** recommended to put the locales your "message source" supports,
this enables the framework to check that everything from your bundles and your methods matches.
This way, errors come at startup instead of when it is used.

You can do so by setting [[MessageSourceFactory#discordLocales]] and/or [[MessageSourceFactory#locales]].

If you don't have translations and only wish to use bundles to separate your responses from your code,
you can set [[MessageSourceFactory#ignoreEmptyLocales]] to `true`.

## Changing the locale

The default locales for interactions are taken from [[UserLocaleProvider]] (default if locale is unspecified) and [[GuildLocaleProvider]].

### Forcing a locale

You can force a specific [`DiscordLocale`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/DiscordLocale.html) or [`Locale`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Locale.html) by passing it as a first argument:

=== "Kotlin"

    ```kotlin
    --8<-- "messages/CommandRepliesForcedLocaleKt.kt:command_replies_forced_locale-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "messages/CommandRepliesForcedLocale.java:command_replies_forced_locale-java"
    ```

### Setting the preferred locale

You can also make a method or an entire message source prefer a locale provider over another:

=== "Kotlin"

    ```kotlin
    --8<-- "messages/CommandRepliesPreferredLocaleKt.kt:command_replies_preferred_locale-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "messages/CommandRepliesPreferredLocale.java:command_replies_preferred_locale-java"
    ```
