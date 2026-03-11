# Text commands

The framework provides support for text (message) commands. They support:

- Registering them with annotations or in a declarative way
- Detecting them with [configurable prefix(es)][[BTextConfigProps#prefixes]] or your [bot's mention][[BTextConfigProps#usePingAsPrefix]]
- Parsing the arguments automatically, as best as it can, similar to application commands
- Or, letting you consume arguments manually

!!! info "You can enable/disable the feature with the [`enable`][[BTextConfigProps#enable]] property, it is recommended to set it explicitly."

## Variations

A single command path can be composed of multiple "variations", their first parameter is a `BaseCommandEvent`.

Each variation is a method with their own parameters, they are displayed separately in the built-in help content.

Each variation is checked based off its priority, the first one matching gets executed.

If all variations fail to match, a fallback version, with the first parameter being a `CommandEvent`, gets executed.
If none exists, help content is shown for the whole command.

## Help command
A built-in help command is available by default, you can make your own help command, but it must implement [[IHelpCommand]].
To completely disable the built-in help command, with no replacement, use [`BTextConfig.isHelpDisabled`][[BTextConfigProps#isHelpDisabled]].

The help content of a command is shown when its name is recognized, but the arguments don't match, which calls into [[IHelpCommand#onInvalidCommand]].

## Suggestions
If a user attempts to use a text command using a valid prefix, but which don't match a text command's name, they will be shown suggestions.
You can disable those with [`BTextConfig.showSuggestions`][[BTextConfigProps#showSuggestions]].

## What's next?

You can look at [annotated commands](annotated.md) and [declarative commands](declarative.md).
