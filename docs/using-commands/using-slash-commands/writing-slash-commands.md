# Writing slash commands

Slash commands are the new way of defining commands, even though there are limitations with them, 
we do have some advantages such as being easier to fill in, choices and auto-completion.

## Defining the command method

Whether you're using annotated or DSL commands, you will have to write a method, 
which holds the user inputs as parameters.

The method must be public, non-static, with the first parameter being `GlobalSlashEvent` for global commands
or `GuildSlashEvent` for guild commands, or guild-only global commands (default).

Lastly, any class containing commands will need to be annotated with `#!java @Command`.

## Annotated commands

Annotated commands are easy to create, but generally are hard to read, cannot be made dynamically,
and require usage of other methods for other features, such as retrieving choices.

The command method must be annotated with `#!java @JDASlashCommand`,
where you can set the scope, name, description, etc..., 
while the declaring class must extend `ApplicationCommand`.

[//]: # (TODO add tip with live template)

Example:
=== "Java"

    ```java
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashPingJava.java:ping_java"
    ```

=== "Kotlin"
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping_kotlin"
    ```

### Adding options

## DSL commands (Kotlin)

DSL commands were added in V3 to help create commands dynamically, 
whether it's to let the user filter commands themselves, or adding subcommands/options in a loop; 
you can almost do anything you want while keeping the simplicity of your command method.

Application commands can be added with a public, non-static method annotated with `#!java @AppDeclaration`,
where the first parameter is a `GlobalApplicationCommandManager` (for global / guild-only global) commands, 
or `GuildApplicationCommandManager` for guild commands.

You can then simply use the `slashCommand` method, give it the command name, the command method, 
and then configure your command.

[//]: # (TODO add tip with live template)

Example:
=== "Kotlin (DSL)"
```kotlin
--8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping_kotlin_dsl"
```

### Adding options

## Default description

[//]: # (Describe how descriptions are retrieved from the root bundle, defined in BApplicationConfig)

## Adding option resolvers

## Predefined choices

## Advanced usage

### Composite options

### Kotlin's inline classes

## Examples

[//]: # (link to examples)
