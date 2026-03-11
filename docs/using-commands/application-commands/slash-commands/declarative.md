# Declarative slash commands

!!! info "Declarative commands must be declared with Kotlin, but the command itself may be in any language."

Declarative commands allow you full control over the declaration, implement at least one of them:

- [[GlobalApplicationCommandProvider]] (for everyone at once)
- [[GuildApplicationCommandProvider]] (on each guild)

You can then use the `slashCommand` method on the `manager`, give it the command name, the command method, 
and then configure your command.

!!! tip
    You are free to skip registration of any command/subcommand/group, for example, 
    if the `guild` in `GuildApplicationCommandManager` isn't a guild you want the command to appear in.

!!! example
    ```kotlin
    --8<-- "commands/slash/SlashPingDsl.kt:ping-kotlin_dsl"
    ```

## Subcommands

As top-level commands cannot be made alongside subcommands, the top-level `function` must be `null`.

You can then add a subcommand by using `subcommand`, where each subcommand is its own function.

!!! example
    ```kotlin
    --8<-- "commands/slash/SlashTagDsl.kt:slash_subcommands-kotlin_dsl"
    ```

!!! info
    
    You can still create both subcommands, and subcommand groups containg subcommands.

## Adding options

Options can be added with a parameter and declaring it using `option` in your command builder,
where the `declaredName` is the name of your parameter, the block will let you change the description, choices, etc.

All supported types are documented under [[SlashParameterResolver]], and [other types can be added](../../../using-botcommands/option-resolvers.md#slash-commands).

!!! example
    ```kotlin
    --8<-- "commands/slash/SlashSayDsl.kt:say-kotlin_dsl"
    ```

!!! tip
    You can override the option name by setting `optionName` in the option declaration:
    ```kotlin
    option("content", optionName = "sentence") {
        ...
    }
    ```

### Using choices

There are two ways of setting choices for an option.

#### With the builder property

A `choices` property is available for you to give a list of choices to be used.

This one is useful if the choices may differ from other similar options.

??? example
    ```kotlin
    --8<-- "commands/slash/SlashConvertDsl.kt:convert-kotlin_dsl"
    ```

#### With choices predefined by the resolver

This one is useful if the choices are the same for all parameters of the same type.

After implementing [[SlashParameterResolver#getPredefinedChoices]], you can enable them on your option with `usePredefinedChoices = true`.

??? example "Using the predefined choices"
    ```kotlin
    --8<-- "commands/slash/SlashConvertSimplifiedDsl.kt:convert_simplified-kotlin_dsl"
    ```

### Using autocomplete

!!! info "Learn how to create an autocomplete handler [here](autocomplete-handlers.md)"

Enabling autocompletion for an option is done by referencing an existing handler:

- (recommended) Reference autocomplete handlers by their function with [`autocompleteByFunction`][[SlashCommandOptionBuilder#autocompleteByFunction]] 
- Reference named autocomplete handlers with [`autocompleteByName`][[SlashCommandOptionBuilder#autocompleteByName]]

!!! example

    Using the autocomplete handler we made in ["Creating autocomplete handlers"](autocomplete-handlers.md#__tabbed_1_1):

    ```kotlin
    --8<-- "commands/slash/SlashWordDsl.kt:word_command-kotlin_dsl"
    ```

## Generated values

Generated values are a command parameter that gets their values computed by the given block everytime the command run.

Contrary to the annotated commands, no checks are required, as this is tied to the currently built command.

!!! example
    ```kotlin
    --8<-- "commands/slash/SlashCreateTimeDsl.kt:create_time-kotlin_dsl"
    ```

## Rate limiting
This lets you reject application commands if the user tries to use them too often,
see ["Using rate limiters in commands"](../../../using-botcommands/ratelimit/usage-in-commands.md#declarative-commands).
