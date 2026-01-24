package dev.freya02.wiki.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.TopLevelSlashCommandData;

// Exists only for @TopLevelSlashCommandData
@Command
public class SlashClickerDummy {
    @JDASlashCommand(name = "clicker", subcommand = "dummy")
    @TopLevelSlashCommandData
    public void onSlashClicker(GuildSlashEvent event) {
        throw new UnsupportedOperationException();
    }
}
