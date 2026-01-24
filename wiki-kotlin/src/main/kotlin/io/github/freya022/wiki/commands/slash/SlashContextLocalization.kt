package io.github.freya022.wiki.commands.slash

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.localization.annotations.LocalizationBundle
import io.github.freya022.botcommands.api.localization.context.AppLocalizationContext
import io.github.freya022.botcommands.api.localization.context.localizeUser
import net.dv8tion.jda.api.utils.TimeFormat

// --8<-- [start:context_localization-kotlin]
@Command
class SlashContextLocalization {

    @JDASlashCommand(name = "context_localization", description = "Send localized responses using a context!")
    fun onSlashEventLocalization(
        event: GuildSlashEvent,
        @LocalizationBundle("ContextReplies") lc: AppLocalizationContext,
    ) {
        event.reply(lc.localizeUser("commands.replies.context_localization", "send_timestamp" to TimeFormat.RELATIVE.now()))
            .setEphemeral(true)
            .queue()
    }
}
// --8<-- [end:context_localization-kotlin]
