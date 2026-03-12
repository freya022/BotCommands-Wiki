# Filtering command usages

A command's execution can be rejected by filters, the filter type depends on the command type and must be registered as a service.

They can be used globally (for all commands of the same type), or applied to individual commands.

## Implementation

!!! tip "A single class can implement multiple filters."

### Text commands

??? example "Filtering text commands to only run in a specific channel"
    === "Kotlin"
        ```kotlin
        --8<-- "filters/TextGeneralChannelFilter.kt:text_filter"
        ```
    
    === "Java"
        ```java
        --8<-- "filters/TextGeneralChannelFilter.java:text_filter"
        ```

### Application commands

??? example "Filtering application commands to only run in a specific channel"

    We'll keep the same example for the sake of consistency, but with application commands,
    guild managers should restrict your application in Discord, to avoid showing unusable commands.

    === "Kotlin"
        ```kotlin
        --8<-- "filters/AppGeneralChannelFilter.kt:app_filter"
        ```

    === "Java"
        ```java
        --8<-- "filters/AppGeneralChannelFilter.java:app_filter"
        ```

## Usage

This section only applies to non-global filters.

### Annotated commands

Annotate your command with [`#!java @Filter`](https://docs.bc.freya02.dev/BotCommands-core/io.github.freya022.botcommands.api.commands.annotations/-filter/index.html) and specify your filter's type.

### Declarative commands

Use the `filter` function in your command builder. ([app commands](https://docs.bc.freya02.dev/BotCommands-core/io.github.freya022.botcommands.api.commands.application.builder/filter.html), [text commands](https://docs.bc.freya02.dev/BotCommands-core/io.github.freya022.botcommands.api.commands.text.builder/filter.html))
