package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.botcommands.jda.ktx.messages.reply_
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption
import io.github.freya022.botcommands.api.commands.application.slash.autocomplete.annotations.AutocompleteHandler
import io.github.freya022.botcommands.api.core.annotations.Handler
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent

// --8<-- [start:word_command-kotlin]
@Command
class SlashWord {
    @JDASlashCommand(name = "word", description = "Autocompletes a word")
    suspend fun onSlashWord(
        event: GuildSlashEvent,
        @SlashOption(description = "The word", autocomplete = SlashWordAutocomplete.WORD_AUTOCOMPLETE_NAME) word: String,
    ) {
        event.reply_("Your word was $word", ephemeral = true).await()
    }
}
// --8<-- [end:word_command-kotlin]

// --8<-- [start:word_autocomplete-kotlin]
@Handler // Required by the AutocompleteHandler annotation, can be replaced with @Command
class SlashWordAutocomplete {
    // https://en.wikipedia.org/wiki/Dolch_word_list#Dolch_list:_Nouns
    // but 30 words
    private val words = listOf(
        "apple", "baby", "back", "ball", "bear", "bed", "bell", "bird", "birthday", "boat",
        "box", "boy", "bread", "brother", "cake", "car", "cat", "chair", "chicken", "children",
        "Christmas", "coat", "corn", "cow", "day", "dog", "doll", "door", "duck", "egg"
    )

    // You can also make this return a collection of Choice, see the annotation docs
    @AutocompleteHandler(WORD_AUTOCOMPLETE_NAME)
    fun onWordAutocomplete(event: CommandAutoCompleteInteractionEvent): Collection<String> {
        // Here you would typically filter the words based on what the user inputs,
        // but it is already done when you return a Collection<String>
        return words
    }

    companion object {
        const val WORD_AUTOCOMPLETE_NAME = "SlashWord: word"
    }
}
// --8<-- [end:word_autocomplete-kotlin]
