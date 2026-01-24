package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption
import java.util.concurrent.TimeUnit

// --8<-- [start:convert_simplified-kotlin]
@Command
class SlashConvertSimplified {
    @JDASlashCommand(name = "convert_simplified", description = "Convert time to another unit")
    suspend fun onSlashConvertSimplified(
        event: GuildSlashEvent,
        @SlashOption(description = "The time to convert") time: Long,
        @SlashOption(description = "The unit to convert from", usePredefinedChoices = true) from: TimeUnit,
        @SlashOption(description = "The unit to convert to", usePredefinedChoices = true) to: TimeUnit
    ) {
        event.reply("${to.convert(time, from)} ${to.name.lowercase()}").await()
    }
}
// --8<-- [end:convert_simplified-kotlin]
