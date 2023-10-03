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

!!! example
    === "Java"
        ```java
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashPingJava.java:ping-java"
        ```
    
    === "Kotlin"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping-kotlin"
        ```

### Adding options

Options can simply be added with a parameter annotated with `#!java @SlashOption`.

All supported types are documented under `ParameterResolver`, and [other types can be added](#adding-option-resolvers).

!!! example
    === "Java"
        ```java
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashSayJava.java:say-java"
        ```
    
    === "Kotlin"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashSay.kt:say-kotlin"
        ```

!!! tip "Inferred option names"
    Display names of options can be set on the annotation,
    but can also be deduced from the parameter name, this is natively supported in Kotlin,
    but for Java, you will need to [enable parameter names](../Inferred-option-names.md) on the Java compiler.

#### Using choices

#### Predefined choices

## DSL commands (Kotlin)

DSL commands were added in V3 to help create commands dynamically, 
whether it's to let the user filter commands themselves, or adding subcommands/options in a loop; 
you can almost do anything you want while keeping the simplicity of your command method.

Application commands can be added with a public method annotated with `#!java @AppDeclaration`,
where the first parameter is a `GlobalApplicationCommandManager` (for global / guild-only global) commands, 
or `GuildApplicationCommandManager` for guild commands.

You can then simply use the `slashCommand` method, give it the command name, the command method, 
and then configure your command.

[//]: # (TODO add tip with live template)

!!! example
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashPing.kt:ping-kotlin_dsl"
    ```

### Adding options

Options can simply be added with a parameter and declaring it using `option` in your command builder,
where the `declaredName` is the name of your parameter, the block will let you change the description, choices, etc.

All supported types are documented under `ParameterResolver`, and [other types can be added](#adding-option-resolvers).

!!! example
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashSay.kt:say-kotlin_dsl"
    ```

!!! tip
    You can override the option name by setting `optionName` in the option declaration:
    ```kotlin
    option("content", optionName = "sentence") {
        ...
    }
    ```

#### Adding choices

#### Predefined choices

## Default description

[//]: # (Describe how descriptions are retrieved from the root bundle, defined in BApplicationConfig)

## Adding option resolvers

### Implementation

### Built-in resolver generators

[//]: # (Using `Resolvers`)

## Advanced usage

### Composite options

### Kotlin's inline classes

## Examples

[//]: # (link to examples)
