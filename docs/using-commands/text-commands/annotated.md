# Annotated text commands

Annotated commands are quick to use but are more limited and may look complex with multiple subcommands,
for text commands, the command method must be annotated with [[JDATextCommandVariation]].

To set the order in which text command variations are checked, set [[JDATextCommandVariation#order]].

=== "Kotlin"
    ```kotlin
    --8<-- "commands/text/TextPing.kt:ping-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "commands/text/TextPing.java:ping-java"
    ```

## Additional data

You can set more stuff that applies to all variations of a command, use [[TextCommandData]] on one of the variations.

There are other attributes, see references in [[JDATextCommandVariation]]'s docs.

## Adding options

Options can be added with a parameter annotated with [[TextOption]].

All supported types are documented under [[TextParameterResolver]], and [other types can be added](../../using-botcommands/option-resolvers.md#text-commands).

!!! example

    === "Kotlin"
        ```kotlin
        --8<-- "commands/text/TextSay.kt:say-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "commands/text/TextSay.java:say-java"
        ```

!!! tip "Inferred option names"
    Display names of options can be set on the annotation,
    but can also be deduced from the parameter name, this is natively supported in Kotlin,
    but for Java, you will need to [enable parameter names](../../setup/parameter-names.md) on the Java compiler.

## Generated values

Generated values are parameters that get their values from a lambda everytime a command is run.

You must give one by implementing `TextGeneratedValueSupplierProvider`.

As always, make sure to check against the command path as well as the option's name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "commands/text/TextCreateTime.kt:create_time-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "commands/text/TextCreateTime.java:create_time-java"
        ```

## Rate limiting
This lets you reject text commands if the user tries to use them too often,
see ["Using rate limiters in commands"](../../using-botcommands/ratelimit/usage-in-commands.md).
