package dev.freya02.wiki.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSource;
import dev.freya02.botcommands.typesafe.messages.api.LocalePreference;
import dev.freya02.botcommands.typesafe.messages.api.annotations.LocalizedContent;
import dev.freya02.botcommands.typesafe.messages.api.annotations.PreferLocale;

// --8<-- [start:command_replies_preferred_locale-java]
public interface CommandRepliesPreferredLocale extends IMessageSource {

    // Prefers using GuildLocaleProvider
    @PreferLocale(LocalePreference.GUILD)
    @LocalizedContent("bot.info")
    String botInfo(
            long guildCount,
            String uptimeTimestamp
    );
}
// --8<-- [end:command_replies_preferred_locale-java]
