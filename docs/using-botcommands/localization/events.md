# Localizing using events

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
