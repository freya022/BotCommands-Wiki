package io.github.freya022.wiki.java.filters;

import io.github.freya022.botcommands.api.components.ComponentInteractionFilter;
import io.github.freya022.botcommands.api.core.service.annotations.BService;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// --8<-- [start:component_filter-java]
@BService
public class GeneralChannelFilter implements ComponentInteractionFilter {
    private static final long CHANNEL_ID = 722891685755093076L;

    @Override
    public boolean getGlobal() {
        // So we can apply this filter on specific components
        return false;
    }

    @Nullable
    @Override
    public String check(@NotNull GenericComponentInteractionCreateEvent event,
                        @Nullable String handlerName) {
        if (event.getChannelIdLong() == CHANNEL_ID) {
            event.reply("This button can only be used in <#" + CHANNEL_ID + ">")
                    .setEphemeral(true)
                    .queue();
            return "Button was used in the wrong channel";
        }
        return null;
    }
}
// --8<-- [end:component_filter-java]
