# Slash command basics

Slash commands are the new way of defining commands, even though there are limitations with them, 
we do have some advantages such as being easier to fill in, choices and auto-completion.

All slash command methods must be public and have `GlobalSlashEvent` (for global commands) or `GuildSlashEvent` (for guild commands)
as their first parameter.

## Notable top-level properties
- Scope: Where the command is pushed, either once globally, or on each guild.
    - Global commands are recommended, they do not mean "available everywhere",
      only that the definition is the same for everyone. Updates always lead to a single request.
    - Guild commands have extra capabilities, such as allowing its declaration on a per-guild basis,
      pushing it to test guilds, or having per-guild choices. Updates lead to a request **on each guild**.
- Inte**raction** contexts: Where the command can be used, such as guilds, bot DMs, and friend DMs
- Inte**gration** types: Where the command can be installed, on a user's account or on a guild
- Description: The command description, remember that it applies only on the top-level command
- `defaultLocked`: Locks the commands to administrators until further configuration from them

## Default description

You can avoid setting the (non-localized) descriptions of your commands and options 
by putting them in a localization file, using the root locale (i.e., no locale suffix),
for more details, see ["Localizing application commands"](../../../using-botcommands/localization/commands.md).

## Using predefined choices

If your choices stay the same for every command,
you can improve re-usability and avoid extra code by using choices on the resolver's level,
that is, the resolver will return the choices used for every option of their type.

All you now need to do is enable `usePredefinedChoices` on your option.

!!! example
    Here, the resolver for `TimeUnit` is already defined and will be explained in [Adding option resolvers](../option-resolvers.md).

    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin"
        ```

    === "Kotlin (DSL)"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin_dsl"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashConvertSimplified.java:convert_simplified-java"
        ```

## Update logs

You can optionally get more info on what changed in your application commands,
by enabling the `TRACE` logs on `io.github.freya022.botcommands.internal.commands.application.diff.DiffLogger`,
or any package it is in.
