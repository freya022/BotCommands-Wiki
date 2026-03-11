package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.CommandEvent
import io.github.freya022.botcommands.api.commands.text.annotations.Category
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation

// --8<-- [start:ping-kotlin]
@Command
@Category("Utility")
class TextPing {
    // There are other properties you can set
    @JDATextCommandVariation(
        // Add more for subcommands
        path = ["ping"],
        description = "Pong!"
    )
    suspend fun onTextPing(
        // Fallback command event since we have no args
        event: CommandEvent
    ) {
        val message = event.reply("Pong!").await()

        val ping = event.jda.getRestPing().await()
        message.editMessage("Pong! $ping ms").queue()
    }
}
// --8<-- [end:ping-kotlin]
