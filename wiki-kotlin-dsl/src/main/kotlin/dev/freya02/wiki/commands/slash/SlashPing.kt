package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent

// --8<-- [start:ping-kotlin_dsl]
@Command
class SlashPing : GlobalApplicationCommandProvider {
    suspend fun onSlashPing(event: GuildSlashEvent) {
        event.deferReply(true).queue()

        val ping = event.jda.getRestPing().await()
        event.hook.editOriginal("Pong! $ping ms").await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        // Default scope is global, guild-only (GUILD_NO_DM)
        manager.slashCommand("ping", function = ::onSlashPing) {
            description = "Pong!"
        }
    }
}
// --8<-- [end:ping-kotlin_dsl]
