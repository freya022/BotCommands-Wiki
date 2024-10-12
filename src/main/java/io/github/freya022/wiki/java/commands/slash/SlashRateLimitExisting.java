package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.annotations.RateLimitReference;
import io.github.freya022.botcommands.api.commands.application.ApplicationCommand;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.wiki.java.ratelimit.WikiRateLimitProvider;
import io.github.freya022.wiki.switches.wiki.WikiCommandProfile;

@SuppressWarnings({"DataFlowIssue"})
@WikiCommandProfile(WikiCommandProfile.Profile.JAVA)
// --8<-- [start:rate_limit_existing-java]
@Command
public class SlashRateLimitExisting extends ApplicationCommand {

    // A rate limit for this slash command only
    @RateLimitReference(WikiRateLimitProvider.RATE_LIMIT_GROUP)
    @JDASlashCommand(name = "rate_limit_existing")
    public void onSlashRateLimit(GuildSlashEvent event) {
        // Assuming we have voice states cached
        if (!event.getMember().getVoiceState().inAudioChannel()) {
            // Don't consume a token as this command requires to be in a voice channel
            // Note that this would be easier done using a filter,
            // as no token would be used, and would also be cleaner.
            event.cancelRateLimit();
            event.reply("You must be in a voice channel").queue();
            return;
        }

        event.reply("Hello world!").queue();
    }
}
// --8<-- [end:rate_limit_existing-java]