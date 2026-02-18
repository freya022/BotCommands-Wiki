package dev.freya02.wiki.messages

import dev.freya02.botcommands.typesafe.messages.api.IMessageSource
import dev.freya02.botcommands.typesafe.messages.api.annotations.LocalizedContent

// --8<-- [start:command_replies-kotlin]
// No need to implement this interface
interface CommandReplies : IMessageSource {

    // The function can have any name you want
    @LocalizedContent("bot.info")
    fun botInfo(
        // Parameter names are converted to snake_case for use in the template, here it's 'guild_count'
        guildCount: Long,
        // You could also pass a Timestamp as it has a proper `toString()`
        uptimeTimestamp: String,
    ): String
}
// --8<-- [end:command_replies-kotlin]
