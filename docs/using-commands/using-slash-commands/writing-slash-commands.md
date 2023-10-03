# Writing slash commands

Slash commands are the new way of defining commands, even though there are limitations with them, 
we do have some advantages such as being easier to fill in, choices and auto-completion.

## Defining the command method

Whether you're using annotated or DSL commands, you will have to write a method, 
which holds the user inputs as parameters.

The method must be public, non-static, with the first parameter being `GlobalSlashEvent` for global commands
or `GuildSlashEvent` for guild commands, or guild-only global commands (default).

Lastly, any class containing commands will need to be annotated with `#!java @Command`.

The command methods support coroutines, as well as nullable options, and optionals.

??? example "A method with everything mentioned above"
    === "Kotlin"
        ```kotlin
        suspend fun example(event: GuildSlashEvent, string: String, user: User?, integer: Int = 42) {}    
        ```

    === "Java"
        ```java
        public void example(@NotNull GuildSlashEvent event, @NotNull String string, @Nullable User user) {}
        ```

## Annotated commands

Annotated commands are easy to create, but generally are hard to read, cannot be made dynamically,
and require usage of other methods for other features, such as retrieving choices.

The command method must be annotated with `#!java @JDASlashCommand`,
where you can set the scope, name, description, etc..., 
while the declaring class must extend `ApplicationCommand`.

!!! question "Why do I need to extend `ApplicationCommand`?"
    As a limitation of annotated commands, 
    you are required to extend this class as it allows the framework to ask your commands for stuff,
    like what guilds a command should be pushed to, getting a value generator for one of their options,
    and also getting choices.

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

You must override `getOptionChoices` in order to return a list of choices, 
be careful to check against the command path as well as the option's display name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashConvert.kt:convert-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashConvertJava.java:convert-java"
        ```

As you can see, the choice lists, despite being simple, are quite lengthy and duplicated,
this issue is solved with [predefined choices](#using-predefined-choices).

### Generated values

Generated values are a command parameter which gets their values computed by a lambda everytime a command is run, 
given by `ApplicationCommand#getGeneratedValueSupplier`, which you must override, 
similarly to adding choices.

As always, make sure to check against the command path as well as the option's display name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashCreateTime.kt:create_time-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashCreateTimeJava.java:create_time-java"
        ```

## DSL commands (Kotlin)

DSL commands were added in V3 to help create commands dynamically, 
whether it's to let the user filter commands themselves, or adding subcommands/options in a loop; 
you can almost do anything you want while keeping the simplicity of your command method.

Application commands can be added with a public method annotated with `#!java @AppDeclaration`,
where the first parameter is a `GlobalApplicationCommandManager` (for global / guild-only global) commands, 
or `GuildApplicationCommandManager` for guild commands.

You can then simply use the `slashCommand` method, give it the command name, the command method, 
and then configure your command.

!!! tip
    You are allowed to not add any command at all, for example, 
    if the `guild` in `GuildApplicationCommandManager` isn't a guild you want the command to appear in.

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

#### Using choices

Adding choices is very straight forward, you just have to give a list of choices to the `choice` property.

!!! example
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashConvert.kt:convert-kotlin_dsl"
    ```

As you can see, the choice lists, despite being simple, are quite lengthy and duplicated, 
this issue is solved with [predefined choices](#using-predefined-choices).

### Generated values

Generated values are a command parameter that gets their values computed by the given block everytime the command run.

Contrary to the annotated commands, no checks are required, as this is tied to the currently built command.

!!! example
    ```kotlin
    --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashCreateTime.kt:create_time-kotlin_dsl"
    ```

## Default description

## Using predefined choices

If your choices stay the same for every command,
you can improve re-usability and avoid extra code by using choices on the resolver's level,
that is, the resolver will return the choices used for every option of their type.

All you now need to do is enable `usePredefinedChoices` on your option.

!!! example
    Here, the resolver for `TimeUnit` is already defined and will be explained in [Adding option resolvers](#adding-option-resolvers).

    === "Kotlin"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin"
        ```

    === "Kotlin (DSL)"
        ```kotlin
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/kotlin/io/github/freya022/bot/commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin_dsl"
        ```

    === "Java"
        ```java
        --8<-- "https://github.com/freya022/BotCommands/raw/3.X/examples/src/main/java/io/github/freya022/bot/commands/slash/SlashConvertSimplifiedJava.java:convert_simplified-java"
        ```

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
