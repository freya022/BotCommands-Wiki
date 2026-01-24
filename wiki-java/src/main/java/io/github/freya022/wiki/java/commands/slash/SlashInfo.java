package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.wiki.java.messages.CommandReplies;
import net.dv8tion.jda.api.utils.TimeFormat;

import java.lang.management.ManagementFactory;

// --8<-- [start:slash_info-java]
@Command
public class SlashInfo {

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
