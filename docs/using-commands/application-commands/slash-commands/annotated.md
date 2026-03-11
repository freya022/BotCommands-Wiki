# Annotated slash commands

Annotated commands are quick to use but are more limited and may look complex with multiple subcommands,
for slash commands, the command method must be annotated with `#!java @JDASlashCommand`.

[//]: # (TODO add tip with live template)

=== "Kotlin"
    ```kotlin
    --8<-- "commands/slash/SlashPing.kt:ping-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "commands/slash/SlashPing.java:ping-java"
    ```

## Command configuration
You can configure properties which applies to different layers of a command,
we will use `/ban temp users` in our example:

- Top level (`/ban`): [[TopLevelSlashCommandData]]
- Subcommand group (`/ban temp`): [[SlashCommandGroupData]]
- Full command (`/ban temp users`): [[JDASlashCommand]]

!!! info "Properties shared by nested commands"

    Some properties exist on all layers, you must to use the appropriate annotation for each layer.
    With our previous example, you can set the description for all levels with:
    
    - [[JDASlashCommand#description]]: Sets the description of `/ban temp users`
    - [[SlashCommandGroupData#description]]: Sets the description of `/ban temp`
    - [[TopLevelSlashCommandData#description]]: Sets the description of `/ban`

## Subcommands

To make a subcommand, set the `name` and `subcommand` on the annotation.

You will also need to add a [[TopLevelSlashCommandData]],
it must only be used **once per top-level** command, this allows you to set top-level attributes.

!!! example

    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashTag.kt:slash_subcommands-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashTag.java:slash_subcommands-java"
        ```

!!! note

    You cannot have both subcommands and top-level commands (i.e., an annotation with only `name` set).

    However, you can have both subcommand groups and subcommands groups containing subcommands.

## Adding options

Options can be added with a parameter annotated with `#!java @SlashOption`.

All supported types are documented under [[SlashParameterResolver]], and [other types can be added](../../../using-botcommands/option-resolvers.md#slash-commands).

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashSay.kt:say-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashSay.java:say-java"
        ```

!!! tip "Inferred option names"
    Display names of options can be set on the annotation,
    but can also be deduced from the parameter name, this is natively supported in Kotlin,
    but for Java, you will need to [enable parameter names](../../../using-botcommands/parameter-names.md) on the Java compiler.

### Using choices

There are two ways of setting choices for an option.

#### With a choice provider

This one is useful if the choices are local to the command's class and may differ from other similar options.

You must implement [[SlashOptionChoiceProvider]] in order to return a list of choices,
remember to check against the command path as well as the option's display name.

??? example "Making a choice provider"
    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashConvert.kt:convert-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashConvert.java:convert-java"
        ```

#### With choices predefined by the resolver

This one is useful if the choices are the same for all parameters of the same type.

After implementing [[SlashParameterResolver#getPredefinedChoices]], you can enable them on your option with [[SlashOption#usePredefinedChoices]].

??? example "Using the predefined choices"
    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashConvertSimplified.kt:convert_simplified-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashConvertSimplified.java:convert_simplified-java"
        ```

### Using autocomplete

!!! info "Learn how to create an autocomplete handler [here](autocomplete-handlers.md)"

Enabling autocompletion for an option is done by referencing an existing handler,
in the [`autocomplete`][[SlashOption#autocomplete]] property of your [[SlashOption]].

!!! example

    Using the autocomplete handler we made ["Creating autocomplete handlers"](autocomplete-handlers.md):

    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashWord.kt:word_command-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashWord.java:word_command-java"
        ```

## Generated values

Generated values are parameters that get their values from a lambda everytime a command is run.

You must give one by implementing [[ApplicationGeneratedValueSupplierProvider]].

As always, make sure to check against the command path as well as the option's display name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashCreateTime.kt:create_time-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashCreateTime.java:create_time-java"
        ```

## Rate limiting
This lets you reject application commands if the user tries to use them too often,
see ["Using rate limiters in commands"](../../../using-botcommands/ratelimit/usage-in-commands.md#annotated-commands).

## Filtering commands
You can use [[DeclarationFilter]] if you wish to declare a **guild** command conditionally.

!!! note

    There is no equivalent for declarative commands as you can check and return early with your own code.

### Creating the filter
Create a service implementing [[CommandDeclarationFilter]],
and make the method return `true` if the command can be declared.
All filters must return `true` for the command to be declared.

!!! example

    === "Kotlin"
        ```kotlin
        --8<-- "filters/declaration/BotOwnerIsGuildOwnerDeclarationFilter.kt:command_declaration_filter-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "filters/declaration/BotOwnerIsGuildOwnerDeclarationFilter.java:command_declaration_filter-java"
        ```

### Using the filter
Add a [[DeclarationFilter]] on your command and reference your filter inside it.

!!! example

    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashBotOwnerIsGuildOwner.kt:declaration_filter-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "commands/slash/SlashBotOwnerIsGuildOwner.java:declaration_filter-java"
        ```
