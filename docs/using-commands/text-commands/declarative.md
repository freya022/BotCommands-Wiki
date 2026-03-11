# Declarative text commands

Declarative commands allow you full control over your commands, start with a service implementing [[TextCommandProvider]].

!!! info "They must be declared with Kotlin, but the command itself may be in any language."

The implemented method gives you a manager, you can use the [`textCommand`][[TextCommandManager#textCommand]] function to create a new top-level text command.

!!! example
    ```kotlin
    --8<-- "commands/text/TextPingDsl.kt:ping-kotlin"
    ```

!!! tip
    You are free to skip registration of any command/subcommand/group by `return`ing.

## Subcommands

With text commands, you're free to have any command structure

Add a subcommand by using `subcommand`, you'll find text subcommands to be very similar to top-level commands.

!!! example
    ```kotlin
    --8<-- "commands/text/TextTagDsl.kt:text_subcommands-kotlin"
    ```

## Adding options

Options can be added with a parameter and declaring it using `option` in your command builder,
where the `declaredName` is the name of your parameter, the block will let you change properties.

All supported types are documented under [[TextParameterResolver]], and [other types can be added](../../using-botcommands/option-resolvers.md#text-commands).

!!! example
    ```kotlin
    --8<-- "commands/text/TextSayDsl.kt:say-kotlin"
    ```

!!! tip
    You can override the option name by setting `optionName` in the option declaration:
    ```kotlin
    option("content", optionName = "sentence") {
        ...
    }
    ```

## Generated values

Generated values are a command parameter that gets their values computed by the given block everytime the command run.

Contrary to the annotated commands, no checks are required, as this is tied to the currently built command.

!!! example
    ```kotlin
    --8<-- "commands/text/TextCreateTimeDsl.kt:create_time-kotlin"
    ```

## Rate limiting
This lets you reject application commands if the user tries to use them too often,
see ["Using rate limiters in commands"](../../using-botcommands/ratelimit/usage-in-commands.md#declarative-commands).
