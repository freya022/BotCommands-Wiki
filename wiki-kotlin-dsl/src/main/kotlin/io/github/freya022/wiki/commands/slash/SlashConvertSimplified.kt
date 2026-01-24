package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import java.util.concurrent.TimeUnit

// --8<-- [start:convert_simplified-kotlin_dsl]
@Command
class SlashConvertSimplified : GlobalApplicationCommandProvider {
    suspend fun onSlashConvertSimplified(event: GuildSlashEvent, time: Long, from: TimeUnit, to: TimeUnit) {
        event.reply("${to.convert(time, from)} ${to.name.lowercase()}").await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        manager.slashCommand("convert_simplified", function = ::onSlashConvertSimplified) {
            description = "Convert time to another unit"

            option("time") {
                description = "The time to convert"
            }

            option("from") {
                description = "The unit to convert from"

                usePredefinedChoices = true
            }

            option("to") {
                description = "The unit to convert to"

                usePredefinedChoices = true
            }
        }
    }
}
// --8<-- [end:convert_simplified-kotlin_dsl]
