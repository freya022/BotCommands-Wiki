package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand

// --8<-- [start:ping-kotlin]
@Command
class SlashPing {
    // Default scope is global, guild-only (GUILD_NO_DM)
    @JDASlashCommand(name = "ping", description = "Pong!")
    suspend fun onSlashPing(event: GuildSlashEvent) {
        event.deferReply(true).queue()

        val ping = event.jda.getRestPing().await()
        event.hook.editOriginal("Pong! $ping ms").await()
    }
}
// --8<-- [end:ping-kotlin]
