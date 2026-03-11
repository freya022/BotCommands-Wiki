package dev.freya02.wiki.commands.text

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.text.CommandEvent
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandManager
import io.github.freya022.botcommands.api.commands.text.provider.TextCommandProvider

// --8<-- [start:text_subcommands-kotlin]
@Command
class TextTag : TextCommandProvider {
    fun onTextTagCreate(event: CommandEvent) {
        // ...
    }

    fun onTextTagDelete(event: CommandEvent) {
        // ...
    }

    override fun declareTextCommands(manager: TextCommandManager) {
        manager.textCommand("tag") {
            description = "Manage tags"

            subcommand("create") {
                description = "Creates a tag"

                variation(::onTextTagCreate) {
                    // ...
                }
            }

            subcommand("delete") {
                description = "Deletes a tag"

                variation(::onTextTagDelete) {
                    // ...
                }
            }
        }
    }
}
// --8<-- [end:text_subcommands-kotlin]
