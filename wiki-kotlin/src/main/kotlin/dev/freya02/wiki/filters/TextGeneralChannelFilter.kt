package dev.freya02.wiki.filters

import io.github.freya022.botcommands.api.commands.text.TextCommandFilter
import io.github.freya022.botcommands.api.commands.text.TextCommandVariation
import io.github.freya022.botcommands.api.core.service.annotations.BService
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

// --8<-- [start:text_filter]
private const val CHANNEL_ID = 722891685755093076

@BService
class TextGeneralChannelFilter : TextCommandFilter {

    // So we can apply this filter on specific commands
    override val global: Boolean get() = false

    override fun check(event: MessageReceivedEvent, commandVariation: TextCommandVariation, args: String): String? {
        if (event.getChannel().idLong != CHANNEL_ID) {
            event.message
                .reply("This command can only be used in <#$CHANNEL_ID>")
                .queue()
            return "Text command was used in the wrong channel"
        }

        // Correct channel, return no error message
        return null
    }
}
// --8<-- [end:text_filter]
