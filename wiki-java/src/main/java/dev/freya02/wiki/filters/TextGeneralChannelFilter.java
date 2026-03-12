package dev.freya02.wiki.filters;

import io.github.freya022.botcommands.api.commands.text.TextCommandFilter;
import io.github.freya022.botcommands.api.commands.text.TextCommandVariation;
import io.github.freya022.botcommands.api.core.service.annotations.BService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

// --8<-- [start:text_filter]
@BService
@NullMarked // Everything is non-null unless @Nullable
public class TextGeneralChannelFilter implements TextCommandFilter {
    private static final long CHANNEL_ID = 722891685755093076L;

    @Override
    public boolean getGlobal() {
        // So we can apply this filter on specific commands
        return false;
    }

    @Nullable
    @Override
    public String check(MessageReceivedEvent event, TextCommandVariation commandVariation, String args) {
        if (event.getChannel().getIdLong() != CHANNEL_ID) {
            event.getMessage()
                    .reply("This command can only be used in <#" + CHANNEL_ID + ">")
                    .queue();
            return "Text command was used in the wrong channel";
        }

        // Correct channel, return no error message
        return null;
    }
}
// --8<-- [end:text_filter]
