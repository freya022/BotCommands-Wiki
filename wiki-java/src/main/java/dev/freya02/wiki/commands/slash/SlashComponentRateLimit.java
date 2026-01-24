package dev.freya02.wiki.commands.slash;

import dev.freya02.jda.emojis.unicode.Emojis;
import dev.freya02.wiki.ratelimit.WikiRateLimitProvider;
import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.components.Buttons;
import io.github.freya022.botcommands.api.components.ratelimit.ComponentRateLimitReference;
import net.dv8tion.jda.api.components.actionrow.ActionRow;

@SuppressWarnings("CodeBlock2Expr")
// --8<-- [start:component_rate_limit-java]
@Command
public class SlashComponentRateLimit {

    private final Buttons buttons;
    private final ComponentRateLimitReference upvoteRateLimitReference;
    private final ComponentRateLimitReference downvoteRateLimitReference;

    public SlashComponentRateLimit(Buttons buttons) {
        this.buttons = buttons;
        // The combination of the group and discriminator must be unique
        this.upvoteRateLimitReference = buttons.createRateLimitReference(
                // The name of the rate limiter
                WikiRateLimitProvider.RATE_LIMIT_GROUP,
                // The "discriminator" for this component,
                // this is important as this differentiates a component from others
                "upvote"
        );
        this.downvoteRateLimitReference = buttons.createRateLimitReference(WikiRateLimitProvider.RATE_LIMIT_GROUP, "downvote");
    }

    @JDASlashCommand(name = "component_rate_limit")
    public void onSlashComponentRateLimit(GuildSlashEvent event) {
        final var upvoteButton = buttons.success("Upvote", Emojis.ARROW_UP)
                .ephemeral()
                .rateLimitReference(upvoteRateLimitReference)
                .bindTo(buttonEvent -> {
                    buttonEvent.reply("Your vote has been taken into account").setEphemeral(true).queue();
                })
                .build();

        final var downvoteButton = buttons.danger("Downvote", Emojis.ARROW_DOWN)
                .ephemeral()
                .rateLimitReference(downvoteRateLimitReference)
                .bindTo(buttonEvent -> {
                    buttonEvent.reply("Your anger has been taken into account").setEphemeral(true).queue();
                })
                .build();

        event.reply("[Insert controversial sentence]")
                .addComponents(ActionRow.of(upvoteButton, downvoteButton))
                .queue();
    }
}
// --8<-- [end:component_rate_limit-java]
