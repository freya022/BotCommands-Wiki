# Annotated slash commands

Annotated commands are quick to use but are more limited and may look complex with multiple subcommands,
for slash commands, the command method must be annotated with `#!java @JDASlashCommand`.

[//]: # (TODO add tip with live template)

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/commands/slash/SlashPing.kt:ping-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/commands/slash/SlashPing.java:ping-java"
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
        --8<-- "wiki/commands/slash/SlashTag.kt:slash_subcommands-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashTag.java:slash_subcommands-java"
        ```

!!! note

    You cannot have both subcommands and top-level commands (i.e., an annotation with only `name` set).

    However, you can have both subcommand groups and subcommands groups containing subcommands.

## Adding options

Options can be added with a parameter annotated with `#!java @SlashOption`.

All supported types are documented under `ParameterResolver`, and [other types can be added](../option-resolvers.md).

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashSay.kt:say-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashSay.java:say-java"
        ```

!!! tip "Inferred option names"
    Display names of options can be set on the annotation,
    but can also be deduced from the parameter name, this is natively supported in Kotlin,
    but for Java, you will need to [enable parameter names](../../../using-botcommands/parameter-names.md) on the Java compiler.

### Using choices

You must implement [[SlashOptionChoiceProvider]] in order to return a list of choices, 
be careful to check against the command path as well as the option's display name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashConvert.kt:convert-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashConvert.java:convert-java"
        ```

    As you can see, despite the short choice list, 
    the method is quite lengthy and causes duplications with multiple commands.
    This issue is solved with [predefined choices](./basics.md#using-predefined-choices).

### Using autocomplete

!!! info "Learn how to create an autocomplete handler [here](using-autocomplete.md)"

Enabling autocompletion for an option is done by referencing an existing handler,
in the [`autocomplete`][[SlashOption#autocomplete]] property of your [[SlashOption]].

!!! example

    Using the autocomplete handler we made ["Creating autocomplete handlers"](using-autocomplete.md):

    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashWord.kt:word_command-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashWord.java:word_command-java"
        ```

## Generated values

Generated values are parameters that get their values from a lambda everytime a command is run.

You must give one by implementing [[ApplicationGeneratedValueSupplierProvider]].

As always, make sure to check against the command path as well as the option's display name.

!!! example
    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashCreateTime.kt:create_time-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashCreateTime.java:create_time-java"
        ```

## Rate limiting
This lets you reject application commands if the user tries to use them too often.

### Using an (anonymous) rate limiter
Use [[RateLimit]] or [[Cooldown]] to define one on an application command.

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/commands/slash/SlashRateLimit.kt:rate_limit-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/commands/slash/SlashRateLimit.java:rate_limit-java"
    ```

### Using an existing rate limiter
Nothing as simple as using [[RateLimitReference]] with the `group` of a rate limiter defined in a `RateLimitProvider`.

!!! info "Learn how to create a rate limiter with ["Defining a rate limit"](../../../using-botcommands/ratelimit.md)"

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/ratelimit/WikiRateLimitProvider.kt:rate_limit_provider-kotlin"
    ```

    ```kotlin
    --8<-- "wiki/commands/slash/SlashRateLimitExisting.kt:rate_limit_existing-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/ratelimit/WikiRateLimitProvider.java:rate_limit_provider-java"
    ```

    ```java
    --8<-- "wiki/java/commands/slash/SlashRateLimitExisting.java:rate_limit_existing-java"
    ```

### Cooldown
A cooldown is a rate limit, but with fewer parameters, it can be used as `#!java @Cooldown(5, ChronoUnit.SECONDS /* also scope and deleteOnRefill */)`.

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
        --8<-- "wiki/filters/declaration/BotOwnerIsGuildOwnerDeclarationFilter.kt:command_declaration_filter-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "wiki/java/filters/declaration/BotOwnerIsGuildOwnerDeclarationFilter.java:command_declaration_filter-java"
        ```

### Using the filter
Add a [[DeclarationFilter]] on your command and reference your filter inside it.

!!! example

    === "Kotlin"
        ```kotlin
        --8<-- "wiki/commands/slash/SlashBotOwnerIsGuildOwner.kt:declaration_filter-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "wiki/java/commands/slash/SlashBotOwnerIsGuildOwner.java:declaration_filter-java"
        ```
