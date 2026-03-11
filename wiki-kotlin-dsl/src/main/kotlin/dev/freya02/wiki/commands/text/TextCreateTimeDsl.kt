package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent
import io.github.freya022.botcommands.api.commands.text.CommandEvent
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandManager
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandProvider
import net.dv8tion.jda.api.utils.TimeFormat
import java.time.Instant

// --8<-- [start:create_time-kotlin]
@Command
class TextCreateTime : TextCommandProvider {
    suspend fun onTextCreateTime(event: CommandEvent, timestamp: Instant) {
        event.reply("I was created on ${TimeFormat.DATE_TIME_SHORT.format(timestamp)}").await()
    }

    override fun declareTextCommands(manager: TextCommandManager) {
        manager.textCommand("create_time") {
            // Shared top level properties here

            description = "Shows the creation time of this command"

            variation(::onTextCreateTime) {
                // Properties of this variation only

                // Create a snapshot of the instant the command was created
                val now = Instant.now()
                // Declare the "timestamp" parameter as a generated option
                // The closure is executed everytime the command runs
                generatedOption("timestamp") { _: BaseCommandEvent ->
                    // Give back the instant snapshot, as this will be called every time the command runs
                    now
                }
            }
        }
    }
}
// --8<-- [end:create_time-kotlin]
