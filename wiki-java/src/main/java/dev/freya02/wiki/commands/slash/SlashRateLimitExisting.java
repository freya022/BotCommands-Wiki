package dev.freya02.wiki.commands.slash;

import dev.freya02.wiki.ratelimit.WikiRateLimitProvider;
import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.annotations.RateLimitReference;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;

// --8<-- [start:rate_limit_existing-java]
@Command
public class SlashRateLimitExisting {

    // A rate limit for this slash command only
    @RateLimitReference(WikiRateLimitProvider.RATE_LIMIT_GROUP)
    @JDASlashCommand(name = "rate_limit_existing")
    public void onSlashRateLimit(GuildSlashEvent event) {
        event.reply("Hello world!").queue();
    }
}
// --8<-- [end:rate_limit_existing-java]
