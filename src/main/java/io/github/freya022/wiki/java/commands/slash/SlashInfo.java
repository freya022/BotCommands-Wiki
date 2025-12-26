package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.ApplicationCommand;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.wiki.java.messages.CommandReplies;
import io.github.freya022.wiki.switches.wiki.WikiLanguage;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.lang.management.ManagementFactory;

@WikiLanguage(WikiLanguage.Language.JAVA)
// --8<-- [start:slash_info-java]
@Command
public class SlashInfo extends ApplicationCommand {

    @JDASlashCommand(
            name = "info",
            description = "Sends info about the bot"
    )
    public void onSlashInfo(GuildSlashEvent event, CommandReplies replies) {
        var response = replies.botInfo(
                event.getJDA().getGuildCache().size(),
                TimeFormat.RELATIVE.format(ManagementFactory.getRuntimeMXBean().getStartTime())
        );

        event.reply(response)
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:slash_info-java]
