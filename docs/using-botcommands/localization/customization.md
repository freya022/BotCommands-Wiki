# Customizing localization

{{ wiki_stub }}

## Creating readers

The reader is the last step in loading a bundle.
You get handed an object with the requested bundle (base name + locale),
and its job is to read that bundle however it wants. This is most likely what you'll want to customize.

For example, you can change:

- The format the bundle uses (JSON, YAML, TOML...)
- The location it is read from (local filesystem, from the classpath...)
- The templates it uses (instead of using [[DefaultLocalizationTemplate]] which uses Java's `MessageFormat`, use [ICU4J](https://www.baeldung.com/java-localization-messages-formatting#4-icus-messageformat))

!!! tip

    For bundles which are [readable by Jackson](https://github.com/FasterXML/jackson?tab=readme-ov-file#data-format-modules), you can help yourself with [[AbstractJacksonLocalizationMapReader]].

!!! example "Reading bundles from the local filesystem"

    === "Kotlin"
        ```kotlin
        --8<-- "localization/LocalLocalizationMapReader.kt:local_localization_map_reader-kotlin"
        ```
    
    === "Java"
        ```java
        --8<-- "localization/LocalLocalizationMapReader.java:local_localization_map_reader-java"
        ```
