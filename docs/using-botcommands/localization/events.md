# Responding using events

The framework's events extend a few interfaces:

- Interactions which can be replied to: [[LocalizableReplyCallback]]
- Interactions which can edit: [[LocalizableEditCallback]]
- Interaction hooks also have [[LocalizableInteractionHook]]
- Text command events have [[LocalizableTextCommand]]

These interfaces allow you to:

- Reply/edit using the user (`replyUser`), guild locale (`replyGuild`) or best locale (`replyLocalized`)
- Get messages using the user or guild locale (`get(User/Guild/Localized)Message`)
- Get the bundle of default messages (`getBotCommandsMessages`)
- Create localization contexts (`getLocalizationContext`)

You can also configure the event to [use a particular bundle][[LocalizableAction#localizationBundle]],
or change the [path prefix][[LocalizableAction#localizationPrefix]].

## Example

Using the following localization bundle:

```json title="Root bundle @ src/main/resources/bc_localization/EventReplies.json"
--8<-- "bc_localization/EventReplies.json"
```

The root bundle **must** exist as a fallback.

```json title="French bundle @ src/main/resources/bc_localization/EventReplies_fr.json"
--8<-- "bc_localization/EventReplies_fr.json"
```

Which we configure to use:

```kotlin
BotCommands.create {
    // ...

    localization {
        // This is only for localization methods directly present on the event
        addResponseBundle("EventReplies")
    }
}
```

Then, reply using the user's locale (as provided by [[UserLocaleProvider]]), passing the `send_timestamp` argument.

=== "Kotlin"
    ```kotlin
    --8<-- "commands/slash/SlashEventLocalization.kt:event_localization-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "commands/slash/SlashEventLocalization.java:event_localization-java"
    ```
