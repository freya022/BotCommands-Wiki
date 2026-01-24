package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.botcommands.jda.ktx.messages.reply_
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandManager
import io.github.freya022.botcommands.api.commands.application.provider.GlobalApplicationCommandProvider
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.autocomplete.declaration.AutocompleteHandlerProvider
import io.github.freya022.botcommands.api.commands.application.slash.autocomplete.declaration.AutocompleteManager
import io.github.freya022.botcommands.api.core.service.annotations.BService
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent

// --8<-- [start:word_command-kotlin_dsl]
@Command
class SlashWord : GlobalApplicationCommandProvider {
    suspend fun onSlashWord(event: GuildSlashEvent, word: String) {
        event.reply_("Your word was $word", ephemeral = true).await()
    }

    override fun declareGlobalApplicationCommands(manager: GlobalApplicationCommandManager) {
        manager.slashCommand("word", function = ::onSlashWord) {
            description = "Autocompletes a word"

            option("word") {
                description = "The word"

                // Use an existing autocomplete declaration
                autocompleteByFunction(SlashWordAutocomplete::onWordAutocomplete)
            }
        }
    }
}
// --8<-- [end:word_command-kotlin_dsl]

// --8<-- [start:word_autocomplete-kotlin_dsl]
@BService
class SlashWordAutocomplete : AutocompleteHandlerProvider {
    // https://en.wikipedia.org/wiki/Dolch_word_list#Dolch_list:_Nouns
    // but 30 words
    private val words = listOf(
        "apple", "baby", "back", "ball", "bear", "bed", "bell", "bird", "birthday", "boat",
        "box", "boy", "bread", "brother", "cake", "car", "cat", "chair", "chicken", "children",
        "Christmas", "coat", "corn", "cow", "day", "dog", "doll", "door", "duck", "egg"
    )

    // You can also make this return a collection of Choice, see the AutocompleteManager#autocomplete docs
    fun onWordAutocomplete(event: CommandAutoCompleteInteractionEvent): Collection<String> {
        // Here you would typically filter the words based on what the user inputs,
        // but it is already done when you return a Collection<String>
        return words
    }

    // All autocomplete declarations run before any command is registered,
    // so you can, in theory, add autocomplete handlers anywhere,
    // and use them in any command.
    override fun declareAutocomplete(manager: AutocompleteManager) {
        manager.autocomplete(::onWordAutocomplete)
    }
}
// --8<-- [end:word_autocomplete-kotlin_dsl]
