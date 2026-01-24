package dev.freya02.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.components.row
import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.botcommands.jda.ktx.messages.deleteDelayed
import dev.freya02.botcommands.jda.ktx.messages.replaceWith
import dev.freya02.botcommands.jda.ktx.requests.awaitUnit
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.components.Buttons
import io.github.freya022.botcommands.api.components.awaitOrNull
import io.github.freya022.botcommands.api.components.event.ButtonEvent
import kotlin.time.Duration.Companion.seconds

// --8<-- [start:click_waiter-kotlin]
@Command
class SlashClickWaiter(private val buttons: Buttons) {
    @JDASlashCommand(name = "click_waiter", description = "Sends a button and waits for it to be clicked")
    suspend fun onSlashClickWaiter(event: GuildSlashEvent) {
        val button = buttons.primary("Click me").ephemeral {
            // Make it so this button is only usable once
            singleUse = true

            // Only allow the caller to use the button
            constraints += event.user
        }
        event.replyComponents(row(button)).await()

        // Wait for the allowed user to click the button
        val buttonEvent: ButtonEvent = button.awaitOrNull() // (1)!
            ?: return event.hook
                .replaceWith("Expired!")
                .awaitUnit() // (2)!

        buttonEvent.editMessage("!")
            // Replace the entire message
            .setReplace(true)
            // Delete after 5 seconds
            .deleteDelayed(5.seconds)
            // Note that the coroutine will resume *after the message is deleted*
            .await()
    }
}
// --8<-- [end:click_waiter-kotlin]
