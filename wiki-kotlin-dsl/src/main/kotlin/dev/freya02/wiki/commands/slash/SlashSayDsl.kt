package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent

// --8<-- [start:say-kotlin_dsl]
@Command
class SlashSay : GlobalApplicationCommandProvider {
    suspend fun onSlashSay(event: GuildSlashEvent, content: String) {
        event.reply(content).await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        manager.slashCommand("say", function = ::onSlashSay) {
            description = "Says something"

            option("content") {
                description = "What to say"
            }
        }
    }
}
// --8<-- [end:say-kotlin_dsl]
