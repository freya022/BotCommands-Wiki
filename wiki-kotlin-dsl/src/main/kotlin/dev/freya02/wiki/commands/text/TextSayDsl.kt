package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandManager
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandProvider

// --8<-- [start:say-kotlin]
@Command
class TextSay : TextCommandProvider {
    suspend fun onTextSay(event: BaseCommandEvent, content: String) {
        event.reply(content).await()
    }

    override fun declareTextCommands(manager: TextCommandManager) {
        manager.textCommand("say") {
            // Shared top level properties here

            description = "Says something"

            // The "say" command can only be parsed one way, so, one variation
            variation(::onTextSay) {
                // Properties of this variation only

                // This will show up in the help command, if the prefix is '!' then it will show:
                // !say I like trains
                example = "I like trains"

                // Declare the "content" parameter as a regular option
                option("content")
            }
        }
    }
}
// --8<-- [end:say-kotlin]
