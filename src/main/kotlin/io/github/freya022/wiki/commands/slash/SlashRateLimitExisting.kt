package io.github.freya022.wiki.commands.slash

import dev.minn.jda.ktx.coroutines.await
import dev.minn.jda.ktx.messages.reply_
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.annotations.RateLimitReference
import io.github.freya022.botcommands.api.commands.application.ApplicationCommand
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.core.utils.awaitUnit
import io.github.freya022.wiki.ratelimit.WikiRateLimitProvider
import io.github.freya022.wiki.switches.wiki.WikiCommandProfile

@WikiCommandProfile(WikiCommandProfile.Profile.KOTLIN)
// --8<-- [start:rate_limit_existing-kotlin]
@Command
class SlashRateLimitExisting : ApplicationCommand() {

    // A rate limit for this slash command only
    @RateLimitReference(WikiRateLimitProvider.RATE_LIMIT_GROUP)
    @JDASlashCommand(name = "rate_limit_existing")
    suspend fun onSlashRateLimit(event: GuildSlashEvent) {
        // Assuming we have voice states cached
        if (!event.member.voiceState!!.inAudioChannel()) {
            // Don't consume a token as this command requires to be in a voice channel
            // Note that this would be easier done using a filter,
            // as no token would be used, and would also be cleaner.
            event.cancelRateLimit()
            return event.reply_("You must be in a voice channel").awaitUnit()
        }

        event.reply("Hello world!").await()
    }
}
// --8<-- [end:rate_limit_existing-kotlin]
