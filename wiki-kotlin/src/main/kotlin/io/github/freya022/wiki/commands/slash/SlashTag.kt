package io.github.freya022.wiki.commands.slash

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.TopLevelSlashCommandData

// --8<-- [start:slash_subcommands-kotlin]
@Command
class SlashTag {
    // Data for /tag create
    @JDASlashCommand(name = "tag", subcommand = "create", description = "Creates a tag")
    // Data for /tag
    @TopLevelSlashCommandData(description = "Manage tags")
    fun onSlashTagCreate(event: GuildSlashEvent) {
        // ...
    }

    // Data for /tag delete
    @JDASlashCommand(name = "tag", subcommand = "delete", description = "Deletes a tag")
    fun onSlashTagDelete(event: GuildSlashEvent) {
        // ...
    }
}
// --8<-- [end:slash_subcommands-kotlin]
