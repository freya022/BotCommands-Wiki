package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.botcommands.jda.ktx.messages.reply_
import dev.freya02.botcommands.jda.ktx.requests.awaitUnit
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent

@Suppress("unused")
class SlashCancelledRateLimit {

    // --8<-- [start:cancelled_rate_limit-kotlin]
    suspend fun onSlashRateLimit(event: GuildSlashEvent) {
        // Assuming we have voice states cached
        if (!event.member.voiceState!!.inAudioChannel()) {
            // Note that this would be easier done using a filter,
            // as no token would be used, and would also be cleaner.
            event.cancelRateLimit()
            return event.reply_("You must be in a voice channel").awaitUnit()
        }

        event.reply("Hello world!").await()
    }
    // --8<-- [end:cancelled_rate_limit-kotlin]
}
