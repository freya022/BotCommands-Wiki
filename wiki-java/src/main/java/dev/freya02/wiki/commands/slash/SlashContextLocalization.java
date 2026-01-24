package dev.freya02.wiki.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.localization.annotations.LocalizationBundle;
import io.github.freya022.botcommands.api.localization.context.AppLocalizationContext;
import net.dv8tion.jda.api.utils.TimeFormat;

import static io.github.freya022.botcommands.api.localization.Localization.Entry.entry;

// --8<-- [start:context_localization-java]
@Command
public class SlashContextLocalization {

    @JDASlashCommand(name = "context_localization", description = "Send localized responses using the event!")
    public void onSlashEventLocalization(GuildSlashEvent event,
                                         @LocalizationBundle("ContextReplies") AppLocalizationContext lc) {
        // Use a static import for io.github.freya022.botcommands.api.localization.Localization.Entry.entry
        event.reply(lc.localizeUser("commands.replies.context_localization", entry("send_timestamp", TimeFormat.RELATIVE.now())))
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:context_localization-java]
