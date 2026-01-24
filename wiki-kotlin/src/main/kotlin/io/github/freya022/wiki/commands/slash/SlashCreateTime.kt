package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.CommandPath
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.annotations.GeneratedOption
import io.github.freya022.botcommands.api.commands.application.ApplicationGeneratedValueSupplier
import io.github.freya022.botcommands.api.commands.application.ApplicationGeneratedValueSupplierProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.core.reflect.ParameterType
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.utils.TimeFormat
import java.time.Instant

// --8<-- [start:create_time-kotlin]
@Command
class SlashCreateTime : ApplicationGeneratedValueSupplierProvider {
    override fun getGeneratedValueSupplier(
        guild: Guild?,
        commandId: String?,
        commandPath: CommandPath,
        optionName: String,
        parameterType: ParameterType
    ): ApplicationGeneratedValueSupplier {
        if (commandPath.name == "create_time") {
            if (optionName == "timestamp") {
                // Create a snapshot of the instant the command was created
                val now = Instant.now()
                // Give back the instant snapshot, as this will be called every time the command runs
                return ApplicationGeneratedValueSupplier { now }
            }
        }

        error("Unknown generated option: $optionName")
    }

    @JDASlashCommand(name = "create_time", description = "Shows the creation time of this command")
    suspend fun onSlashCreateTime(
        event: GuildSlashEvent,
        @GeneratedOption timestamp: Instant
    ) {
        event.reply("I was created on ${TimeFormat.DATE_TIME_SHORT.format(timestamp)}").await()
    }
}
// --8<-- [end:create_time-kotlin]
