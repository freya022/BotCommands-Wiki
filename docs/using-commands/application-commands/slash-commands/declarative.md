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
    --8<-- "wiki/commands/slash/SlashPing.kt:ping-kotlin_dsl"
    ```

## Subcommands

As top-level commands cannot be made alongside subcommands, the top-level `function` must be `null`.

You can then add a subcommand by using `subcommand`, where each subcommand is its own function.

!!! example
    ```kotlin
    --8<-- "wiki/commands/slash/SlashTag.kt:slash_subcommands-kotlin_dsl"
    ```

!!! info
    
    You can still create both subcommands, and subcommand groups containg subcommands.

## Adding options

Options can be added with a parameter and declaring it using `option` in your command builder,
where the `declaredName` is the name of your parameter, the block will let you change the description, choices, etc.

All supported types are documented under [[ParameterResolver]], and [other types can be added](../option-resolvers.md).

!!! example
    ```kotlin
    --8<-- "wiki/commands/slash/SlashSay.kt:say-kotlin_dsl"
    ```

!!! tip
    You can override the option name by setting `optionName` in the option declaration:
    ```kotlin
    option("content", optionName = "sentence") {
        ...
    }
    ```

### Using choices

Adding choices is very straight forward, you only have to give a list of choices to the `choice` property.

!!! example
    ```kotlin
    --8<-- "wiki/commands/slash/SlashConvert.kt:convert-kotlin_dsl"
    ```

    As you can see, despite the short choice list, this causes duplications with multiple commands.
    This issue is solved with [predefined choices](./basics.md#using-predefined-choices).

### Using autocomplete

!!! info "Learn how to create an autocomplete handler [here](using-autocomplete.md)"

Enabling autocompletion for an option is done by referencing an existing handler:

- (recommended) Reference autocomplete handlers by their function with [`autocompleteByFunction`][[SlashCommandOptionBuilder#autocompleteByFunction]] 
- Reference named autocomplete handlers with [`autocompleteByName`][[SlashCommandOptionBuilder#autocompleteByName]]

!!! example

    Using the autocomplete handler we made in ["Creating autocomplete handlers"](using-autocomplete.md#__tabbed_1_1):

    ```kotlin
    --8<-- "wiki/commands/slash/SlashWord.kt:word_command-kotlin_dsl"
    ```

## Rate limiting
This lets you reject application commands if the user tries to use them too often.

!!! info "Learn how to create a rate limiter with ["Defining a rate limit"](../../../using-botcommands/ratelimit.md)"

### Using an (anonymous) rate limiter
```kotlin
--8<-- "wiki/commands/slash/SlashRateLimitDsl.kt:rate_limit-kotlin_dsl"
```

### Using an existing rate limiter
Nothing as simple as using `rateLimitReference` with the `group` of a rate limiter defined in a [[RateLimitProvider]].

```kotlin
--8<-- "wiki/ratelimit/WikiRateLimitProvider.kt:rate_limit_provider-kotlin"
```

```kotlin
--8<-- "wiki/commands/slash/SlashRateLimitExistingDsl.kt:rate_limit_existing-kotlin_dsl"
```

### Cooldown
A cooldown is a rate limit, but with fewer parameters, it can be used as `cooldown(5.seconds /* also scope and deleteOnRefill */)`.

## Generated values

Generated values are a command parameter that gets their values computed by the given block everytime the command run.

Contrary to the annotated commands, no checks are required, as this is tied to the currently built command.

!!! example
    ```kotlin
    --8<-- "wiki/commands/slash/SlashCreateTime.kt:create_time-kotlin_dsl"
    ```
