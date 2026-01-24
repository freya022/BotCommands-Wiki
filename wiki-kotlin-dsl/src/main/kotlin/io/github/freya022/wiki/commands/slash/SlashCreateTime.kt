package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import net.dv8tion.jda.api.utils.TimeFormat
import java.time.Instant

// --8<-- [start:create_time-kotlin_dsl]
@Command
class SlashCreateTime : GlobalApplicationCommandProvider {
    suspend fun onSlashCreateTime(event: GuildSlashEvent, timestamp: Instant) {
        event.reply("I was created on ${TimeFormat.DATE_TIME_SHORT.format(timestamp)}").await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        manager.slashCommand("create_time", function = ::onSlashCreateTime) {
            description = "Shows the creation time of this command"

            // Create a snapshot of the instant the command was created
            val now = Instant.now()
            generatedOption("timestamp") {
                // Give back the instant snapshot, as this will be called every time the command is run
                return@generatedOption now
            }
        }
    }
}
// --8<-- [end:create_time-kotlin_dsl]
