package io.github.freya022.wiki.java.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSource;
import dev.freya02.botcommands.typesafe.messages.api.annotations.LocalizedContent;

// --8<-- [start:command_replies-java]
// No need to implement this interface
public interface CommandReplies extends IMessageSource {

    // The function can have any name you want
    @LocalizedContent("bot.info")
    String botInfo(
            // Parameter names are converted to snake_case for use in the template, here it's 'guild_count'
            long guildCount,
            // You could also pass a Timestamp as it has a proper `toString()`
            String uptimeTimestamp
    );
}
// --8<-- [end:command_replies-java]
