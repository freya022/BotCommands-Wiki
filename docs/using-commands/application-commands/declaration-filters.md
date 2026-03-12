# Filtering guild application command registrations

!!! info "This only applies to <u>annotated guild application commands</u>"

We'll be using a slash command as the example, but this works the same for context menu commands.

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

!!! tip

    The `scope` on context commands can be set directly, there is no separate top-level annotation.
