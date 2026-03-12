package dev.freya02.wiki.filters

import io.github.freya022.botcommands.api.commands.application.ApplicationCommandFilter
import io.github.freya022.botcommands.api.commands.application.ApplicationCommandInfo
import io.github.freya022.botcommands.api.core.service.annotations.BService
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent

// --8<-- [start:app_filter]
private const val CHANNEL_ID = 722891685755093076

@BService
class AppGeneralChannelFilter : ApplicationCommandFilter {

    // So we can apply this filter on specific commands
    override val global: Boolean get() = false

    override fun check(event: GenericCommandInteractionEvent, commandInfo: ApplicationCommandInfo): String? {
        if (event.channelIdLong != CHANNEL_ID) {
            event.reply("This command can only be used in <#$CHANNEL_ID>").queue()
            return "Application command was used in the wrong channel"
        }

        // Correct channel, return no error message
        return null
    }
}
// --8<-- [end:app_filter]
