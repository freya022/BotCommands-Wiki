package dev.freya02.wiki.commands.slash

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent

// --8<-- [start:slash_subcommands-kotlin_dsl]
@Command
class SlashTag : GlobalApplicationCommandProvider {
    fun onSlashTagCreate(event: GuildSlashEvent) {
        // ...
    }

    fun onSlashTagDelete(event: GuildSlashEvent) {
        // ...
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        // Pass a null function as this is not a top-level command
        manager.slashCommand("tag", function = null) {
            description = "Manage tags"

            subcommand("create", ::onSlashTagCreate) {
                description = "Creates a tag"
            }

            subcommand("delete", ::onSlashTagDelete) {
                description = "Deletes a tag"
            }
        }
    }
}
// --8<-- [end:slash_subcommands-kotlin_dsl]
