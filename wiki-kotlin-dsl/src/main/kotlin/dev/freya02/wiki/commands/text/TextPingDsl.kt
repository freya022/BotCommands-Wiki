package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.CommandEvent
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandManager
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandProvider

// --8<-- [start:ping-kotlin]
@Command
class TextPing : TextCommandProvider {
    suspend fun onTextPing(
        // Fallback command event since we have no args
        event: CommandEvent
    ) {
        val message = event.reply("Pong!").await()

        val ping = event.jda.getRestPing().await()
        message.editMessage("Pong! $ping ms").queue()
    }

    override fun declareTextCommands(manager: TextCommandManager) {
        manager.textCommand("ping") {
            // Shared top level properties here

            category = "Utility"
            description = "Pong!"

            variation(::onTextPing) {
                // Properties of this variation only
            }
        }
    }
}
// --8<-- [end:ping-kotlin]
