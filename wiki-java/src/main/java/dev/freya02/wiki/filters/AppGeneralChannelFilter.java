package dev.freya02.wiki.filters;

import io.github.freya022.botcommands.api.commands.application.ApplicationCommandFilter;
import io.github.freya022.botcommands.api.commands.application.ApplicationCommandInfo;
import io.github.freya022.botcommands.api.core.service.annotations.BService;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

// --8<-- [start:app_filter]
@BService
@NullMarked // Everything is non-null unless @Nullable
public class AppGeneralChannelFilter implements ApplicationCommandFilter {
    private static final long CHANNEL_ID = 722891685755093076L;

    @Override
    public boolean getGlobal() {
        // So we can apply this filter on specific commands
        return false;
    }

    @Nullable
    @Override
    public String check(GenericCommandInteractionEvent event, ApplicationCommandInfo commandInfo) {
        if (event.getChannelIdLong() != CHANNEL_ID) {
            event.reply("This command can only be used in <#" + CHANNEL_ID + ">")
                    .setEphemeral(true)
                    .queue();
            return "Application command was used in the wrong channel";
        }

        // Correct channel, return no error message
        return null;
    }
}
// --8<-- [end:app_filter]
