package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.wiki.switches.wiki.WikiLanguage;
import net.dv8tion.jda.api.utils.TimeFormat;

import static io.github.freya022.botcommands.api.localization.Localization.Entry.entry;

@WikiLanguage(WikiLanguage.Language.JAVA)
// --8<-- [start:event_localization-java]
@Command
public class SlashEventLocalization {

    @JDASlashCommand(name = "event_localization", description = "Send localized responses using the event!")
    public void onSlashEventLocalization(GuildSlashEvent event) {
        // Use a static import for io.github.freya022.botcommands.api.localization.Localization.Entry.entry
        event.replyUser("commands.replies.event_localization", entry("send_timestamp", TimeFormat.RELATIVE.now()))
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:event_localization-java]
