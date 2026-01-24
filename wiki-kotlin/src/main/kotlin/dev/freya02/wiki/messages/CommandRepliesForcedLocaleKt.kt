package dev.freya02.wiki.messages

import dev.freya02.botcommands.typesafe.messages.api.IMessageSource
import dev.freya02.botcommands.typesafe.messages.api.annotations.LocalizedContent
import net.dv8tion.jda.api.interactions.DiscordLocale

// --8<-- [start:command_replies_forced_locale-kotlin]
interface CommandRepliesForcedLocaleKt : IMessageSource {

    @LocalizedContent("bot.info")
    fun botInfo(
        // Forces the locale to this one
        locale: DiscordLocale,
        guildCount: Long,
        uptimeTimestamp: String,
    ): String
}
// --8<-- [end:command_replies_forced_locale-kotlin]
