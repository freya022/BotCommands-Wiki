package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.wiki.switches.WikiDetailProfile
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.parameters.Resolvers
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import java.util.concurrent.TimeUnit

@WikiDetailProfile(WikiDetailProfile.Profile.DETAILED)
// --8<-- [start:convert-kotlin_dsl]
@Command
class SlashConvert : GlobalApplicationCommandProvider {
    suspend fun onSlashConvert(event: GuildSlashEvent, time: Long, from: TimeUnit, to: TimeUnit) {
        event.reply("${to.convert(time, from)} ${to.name.lowercase()}").await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        manager.slashCommand("convert", function = ::onSlashConvert) {
            description = "Convert time to another unit"

            option("time") {
                description = "The time to convert"
            }

            option("from") {
                description = "The unit to convert from"

                choices = listOf(TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS)
                    // The Resolvers class helps us by providing resolvers for any enum type.
                    // We're just using the helper method to change an enum value to a more natural name.
                    .map { Choice(Resolvers.toHumanName(it), it.name) }
            }

            option("to") {
                description = "The unit to convert to"

                choices = listOf(TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS)
                    // The Resolvers class helps us by providing resolvers for any enum type.
                    // We're just using the helper method to change an enum value to a more natural name.
                    .map { Choice(Resolvers.toHumanName(it), it.name) }
            }
        }
    }
}
// --8<-- [end:convert-kotlin_dsl]
