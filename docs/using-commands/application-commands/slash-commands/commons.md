# Slash command commons

Here's a few common details and tips about slash commands.

## Using predefined choices

If your choices stay the same for every command,
you can improve re-usability and avoid extra code by using choices on the resolver's level,
that is, the resolver will return the choices used for every option of their type.

All you now need to do is enable `usePredefinedChoices` on your option.

!!! example
Here, the resolver for `TimeUnit` is already defined and will be explained in [Adding option resolvers](../option-resolvers.md).

    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin"
        ```

    === "Kotlin (DSL)"
        ```kotlin
        --8<-- "commands/slash/SlashConvertSimplifiedDsl.kt:convert_simplified-kotlin_dsl"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashConvertSimplified.java:convert_simplified-java"
        ```
