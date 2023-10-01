# Writing slash commands

Slash commands are the new way of defining commands, even though there are limitations with them, 
we do have some advantages such as being easier to fill in, choices and auto-completion.

## Annotated commands

Annotated commands are easy to create, but generally are hard to read, cannot be made dynamically,
and require usage of other methods for other features, such as retrieving choices.

A slash command can be defined in a class that extends `ApplicationCommand` and is annotated with `#!java @Command`,
on a public, non-static method with `#!java @JDASlashCommand`.

You can set the scope, name, description and other options, 
but the first parameter of your method must either be `GlobalSlashEvent` for global commands 
or `GuildSlashEvent` for guild commands, or guild-only global commands (default).

Example:
=== "Java"

    ```java
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashPingJava.java:ping_java"
    ```

=== "Kotlin"
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping_kotlin"
    ```

=== "Kotlin (DSL)"
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping_kotlin_dsl"
    ```

### Adding options

## DSL commands

## Default description

[//]: # (Describe how descriptions are retrieved from the root bundle, defined in BApplicationConfig)

## Adding option resolvers

## Predefined choices

## Advanced usage

### Composite options

### Kotlin's inline classes

## Examples

[//]: # (link to examples)
