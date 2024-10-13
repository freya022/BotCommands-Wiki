package io.github.freya022.wiki.filters.declaration

import io.github.freya022.botcommands.api.commands.CommandPath
import io.github.freya022.botcommands.api.commands.application.CommandDeclarationFilter
import io.github.freya022.botcommands.api.core.BotOwners
import io.github.freya022.botcommands.api.core.service.annotations.BService
import io.github.freya022.wiki.switches.wiki.WikiLanguage
import net.dv8tion.jda.api.entities.Guild

@WikiLanguage(WikiLanguage.Language.KOTLIN)
// --8<-- [start:command_declaration_filter-kotlin]
@BService
class BotOwnerIsGuildOwnerDeclarationFilter(
    private val botOwners: BotOwners, // Provided by the framework
) : CommandDeclarationFilter {

    override fun filter(guild: Guild, path: CommandPath, commandId: String?): Boolean {
        // Only allow this command to be in guilds owned by the bot owner
        return guild.ownerIdLong in botOwners.ownerIds
    }
}
// --8<-- [end:command_declaration_filter-kotlin]