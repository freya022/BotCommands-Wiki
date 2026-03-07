package dev.freya02.wiki.emojis

import io.github.freya022.botcommands.api.emojis.AppEmojisRegistry
import io.github.freya022.botcommands.api.emojis.annotations.AppEmojiContainer
import net.dv8tion.jda.api.entities.emoji.ApplicationEmoji

// --8<-- [start:lazy_app_emojis-kotlin]
@AppEmojiContainer
object LazyAppEmojis {
    // Can't use @AppEmoji here, you can override stuff in the function call
    val kotlin: ApplicationEmoji by AppEmojisRegistry.lazy(::kotlin, /* Overrides default values */ emojiName = "kotlin_lazy")
}
// --8<-- [end:lazy_app_emojis-kotlin]
