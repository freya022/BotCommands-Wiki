package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation
import io.github.freya022.botcommands.api.commands.text.annotations.TextOption

// --8<-- [start:say-kotlin]
@Command
class TextSay {
    @JDATextCommandVariation(
        path = ["say"],
        description = "Says something",
        // This will show up in the help command, if the prefix is '!' then it will show:
        // !say I like trains
        example = "I like trains",
    )
    suspend fun onTextSay(event: BaseCommandEvent, @TextOption content: String) {
        event.reply(content).await()
    }
}
// --8<-- [end:say-kotlin]
