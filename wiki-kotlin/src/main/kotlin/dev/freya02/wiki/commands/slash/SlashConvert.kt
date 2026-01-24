package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.wiki.switches.WikiDetailProfile
import io.github.freya022.botcommands.api.commands.CommandPath
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.SlashOptionChoiceProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption
import io.github.freya022.botcommands.api.parameters.Resolvers
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import java.util.concurrent.TimeUnit

@WikiDetailProfile(WikiDetailProfile.Profile.DETAILED)
// --8<-- [start:convert-kotlin]
@Command
class SlashConvert : SlashOptionChoiceProvider {
    override fun getOptionChoices(guild: Guild?, commandPath: CommandPath, optionName: String): List<Choice> {
        if (commandPath.name == "convert") {
            if (optionName == "from" || optionName == "to") {
                return listOf(TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS)
                    // The Resolvers class helps us by providing resolvers for any enum type.
                    // We're just using the helper method to change an enum value to a more natural name.
                    .map { Choice(Resolvers.toHumanName(it), it.name) }
            }
        }

        return emptyList()
    }

    @JDASlashCommand(name = "convert", description = "Convert time to another unit")
    suspend fun onSlashConvert(
        event: GuildSlashEvent,
        @SlashOption(description = "The time to convert") time: Long,
        @SlashOption(description = "The unit to convert from") from: TimeUnit,
        @SlashOption(description = "The unit to convert to") to: TimeUnit
    ) {
        event.reply("${to.convert(time, from)} ${to.name.lowercase()}").await()
    }
}
// --8<-- [end:convert-kotlin]
