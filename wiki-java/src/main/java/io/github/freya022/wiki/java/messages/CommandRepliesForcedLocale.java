package io.github.freya022.wiki.java.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSource;
import dev.freya02.botcommands.typesafe.messages.api.annotations.LocalizedContent;
import net.dv8tion.jda.api.interactions.DiscordLocale;

// --8<-- [start:command_replies_forced_locale-java]
public interface CommandRepliesForcedLocale extends IMessageSource {

    @LocalizedContent("bot.info")
    String botInfo(
            // Forces the locale to this one
            DiscordLocale locale,
            long guildCount,
            String uptimeTimestamp
    );
}
// --8<-- [end:command_replies_forced_locale-java]
