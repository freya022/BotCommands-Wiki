# Using Unicode emojis

This framework uses [jda-emojis](https://github.com/freya022/jda-emojis),
providing all the emojis supported by Discord, in JDA's `UnicodeEmoji` format.

It is highly recommended using them as:

- You no longer have encoding issues from them
- You no longer need to declare those emojis anywhere
- You no longer need to find the correct Unicode from any website
- You will have a readable name in any editor

Using them is nothing more simple than looking in `Emojis` (make sure it's from `dev.freya02.jda.emojis.unicode`) or `UnicodeEmojis`
for an emoji with the same name as you'd see on Discord!

## Resolving emojis
If you need to get the corresponding emoji object from a shortcode (such as `:joy:`),
you can use [EmojiUtils](https://docs.bc.freya02.dev/-bot-commands/io.github.freya022.botcommands.api.utils/-emoji-utils/index.html) to resolve it.

!!! note

    This utility uses [JEmoji](https://github.com/felldo/JEmoji),
    which supports more emojis than `jda-emojis`,
    meaning some resolved emojis may not be recognized by Discord.

    If you need to only resolve Discord's emojis, please make a feature request on [jda-emojis](https://github.com/freya022/jda-emojis).

## Other parsing purposes
For any other parsing purpose, you should use JEmoji's `EmojiManager`.