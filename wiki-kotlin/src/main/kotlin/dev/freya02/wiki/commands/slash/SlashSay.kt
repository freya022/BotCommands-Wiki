package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption

// --8<-- [start:say-kotlin]
@Command
class SlashSay {
    @JDASlashCommand(name = "say", description = "Says something")
    suspend fun onSlashSay(event: GuildSlashEvent, @SlashOption(description = "What to say") content: String) {
        event.reply(content).await()
    }
}
// --8<-- [end:say-kotlin]
