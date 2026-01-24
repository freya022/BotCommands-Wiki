package dev.freya02.wiki.commands.slash;

import dev.freya02.wiki.switches.WikiDetailProfile;
import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption;

import java.util.concurrent.TimeUnit;

@WikiDetailProfile(WikiDetailProfile.Profile.SIMPLIFIED)
// --8<-- [start:convert_simplified-java]
@Command
public class SlashConvertSimplified {
    @JDASlashCommand(name = "convert", description = "Convert time to another unit")
    public void onSlashTimeInSimplified(
            GuildSlashEvent event,
            @SlashOption(description = "The time to convert") long time,
            @SlashOption(description = "The unit to convert from", usePredefinedChoices = true) TimeUnit from,
            @SlashOption(description = "The unit to convert to", usePredefinedChoices = true) TimeUnit to
    ) {
        event.reply(to.convert(time, from) + " " + to.toString().toLowerCase()).queue();
    }
}
// --8<-- [end:convert_simplified-java]
