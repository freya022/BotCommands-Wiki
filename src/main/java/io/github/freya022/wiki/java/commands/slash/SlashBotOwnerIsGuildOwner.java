package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.ApplicationCommand;
import io.github.freya022.botcommands.api.commands.application.CommandScope;
import io.github.freya022.botcommands.api.commands.application.annotations.DeclarationFilter;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.TopLevelSlashCommandData;
import io.github.freya022.wiki.java.filters.declaration.BotOwnerIsGuildOwnerDeclarationFilter;
import io.github.freya022.wiki.switches.wiki.WikiLanguage;

@WikiLanguage(WikiLanguage.Language.JAVA)
// --8<-- [start:declaration_filter-java]
@Command
public class SlashBotOwnerIsGuildOwner extends ApplicationCommand {

    // All filters must return 'true' for the command to be declared
    @DeclarationFilter(BotOwnerIsGuildOwnerDeclarationFilter.class)
    // The command needs to be registered on guilds for it to be filtered
    @TopLevelSlashCommandData(scope = CommandScope.GUILD)
    @JDASlashCommand(name = "bot_owner_is_guild_owner")
    public void onSlashBotOwnerIsGuildOwner(GuildSlashEvent event) {
        event.reply("You are the owner of this bot and guild!")
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:declaration_filter-java]
