# Responding using contexts

The framework allows you to inject contexts which allow you to get messages:

- For interactions: [[AppLocalizationContext]]
- In text commands: [[TextLocalizationContext]]

To get an instance of them, add them as a parameter of your interaction handler or text command,
and annotate them with [[LocalizationBundle]].

## Example

Using the following localization bundles:

```json title="Root bundle @ src/main/resources/bc_localization/ContextReplies.json"
--8<-- "bc_localization/ContextReplies.json"
```

The root bundle **must** exist as a fallback.

```json title="French bundle @ src/main/resources/bc_localization/ContextReplies_fr.json"
--8<-- "bc_localization/ContextReplies_fr.json"
```

We reply using the user's locale (as provided by [[UserLocaleProvider]]), passing the `send_timestamp` argument.

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/commands/slash/SlashContextLocalization.kt:context_localization-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/commands/slash/SlashContextLocalization.java:context_localization-java"
    ```
