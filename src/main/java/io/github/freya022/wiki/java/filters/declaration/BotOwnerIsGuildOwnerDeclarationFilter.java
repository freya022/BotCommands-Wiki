package io.github.freya022.wiki.java.filters.declaration;

import io.github.freya022.botcommands.api.commands.CommandPath;
import io.github.freya022.botcommands.api.commands.application.CommandDeclarationFilter;
import io.github.freya022.botcommands.api.core.BotOwners;
import io.github.freya022.botcommands.api.core.service.annotations.BService;
import io.github.freya022.wiki.switches.wiki.WikiLanguage;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@WikiLanguage(WikiLanguage.Language.JAVA)
// --8<-- [start:command_declaration_filter-java]
@BService
public class BotOwnerIsGuildOwnerDeclarationFilter implements CommandDeclarationFilter {
    private final BotOwners botOwners; // Provided by the framework

    public BotOwnerIsGuildOwnerDeclarationFilter(BotOwners botOwners) {
        this.botOwners = botOwners;
    }

    @Override
    public boolean filter(@NotNull Guild guild, @NotNull CommandPath commandPath, @Nullable String s) {
        // Only allow this command to be in guilds owned by the bot owner
        return botOwners.getOwnerIds().contains(guild.getOwnerIdLong());
    }
}
// --8<-- [end:command_declaration_filter-java]
