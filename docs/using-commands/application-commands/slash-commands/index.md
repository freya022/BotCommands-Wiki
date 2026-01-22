# Creating slash commands

Slash commands are the new way of defining commands, even though there are limitations with them,
we do have some advantages such as being easier to fill in, choices and auto-completion.

All slash command methods must be public and have `GlobalSlashEvent` (for global commands) or `GuildSlashEvent` (for guild commands)
as their first parameter.

## Notable top-level properties
- Scope: Where the command is pushed, either once globally, or on each guild.
    - Global commands are recommended, they do not mean "available everywhere",
      only that the definition is the same for everyone. Updates always lead to a single request.
    - Guild commands have extra capabilities, such as allowing its declaration on a per-guild basis,
      pushing it to test guilds, or having per-guild choices. Updates lead to a request **on each guild**.
- Inte**raction** contexts: Where the command can be used, such as guilds, bot DMs, and friend DMs
- Inte**gration** types: Where the command can be installed, on a user's account or on a guild
- Description: The command description, remember that it applies only on the top-level command
- `defaultLocked`: Locks the commands to administrators until further configuration from them

## Default description

You can avoid setting the (non-localized) descriptions of your commands and options
by putting them in a localization file, using the root locale (i.e., no locale suffix),
for more details, see ["Localizing application commands"](../../../using-botcommands/localization/commands.md).

## What's next?

You can look at [annotated commands](annotated.md) and [declarative commands](declarative.md).
