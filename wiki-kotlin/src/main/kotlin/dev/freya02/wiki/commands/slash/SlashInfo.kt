package dev.freya02.wiki.commands.slash

import dev.freya02.wiki.messages.CommandRepliesKt
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import net.dv8tion.jda.api.utils.TimeFormat
import java.lang.management.ManagementFactory

// --8<-- [start:slash_info-kotlin]
@Command
class SlashInfo {

    @JDASlashCommand(
        name = "info",
        description = "Sends info about the bot",
    )
    fun onSlashInfo(event: GuildSlashEvent, replies: CommandRepliesKt) {
        val response = replies.botInfo(
            // Use named parameters to make the arguments clearer!
            guildCount = event.jda.guildCache.size(),
            uptimeTimestamp = TimeFormat.RELATIVE.format(ManagementFactory.getRuntimeMXBean().startTime),
        )

        event.reply(response)
            .setEphemeral(true)
            .queue()
    }
}
// --8<-- [end:slash_info-kotlin]
