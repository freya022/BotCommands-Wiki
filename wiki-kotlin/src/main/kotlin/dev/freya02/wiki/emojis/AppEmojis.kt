package dev.freya02.wiki.emojis

import io.github.freya022.botcommands.api.emojis.AppEmojisRegistry
import io.github.freya022.botcommands.api.emojis.annotations.AppEmoji
import io.github.freya022.botcommands.api.emojis.annotations.AppEmojiContainer
import net.dv8tion.jda.api.entities.emoji.ApplicationEmoji

// --8<-- [start:eager_app_emojis-kotlin]
@AppEmojiContainer // Here you can change the base location of all emojis contained here
object AppEmojis {
    // This annotation is optional,
    // you can use it to change where the emoji is fetched from,
    // or how it will be named on Discord.
    @AppEmoji(emojiName = "kotlin_eager")
    val kotlin: ApplicationEmoji by AppEmojisRegistry
}
// --8<-- [end:eager-app-emojis-kotlin]
