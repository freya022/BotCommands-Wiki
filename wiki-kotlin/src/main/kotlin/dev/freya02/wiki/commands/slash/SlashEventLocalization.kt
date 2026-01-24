package dev.freya02.wiki.commands.slash

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.localization.interaction.replyUser
import net.dv8tion.jda.api.utils.TimeFormat

// --8<-- [start:event_localization-kotlin]
@Command
class SlashEventLocalization {

    @JDASlashCommand(name = "event_localization", description = "Send localized responses using the event!")
    fun onSlashEventLocalization(event: GuildSlashEvent) {
        event.replyUser("commands.replies.event_localization", "send_timestamp" to TimeFormat.RELATIVE.now())
            .setEphemeral(true)
            .queue()
    }
}
// --8<-- [end:event_localization-kotlin]
