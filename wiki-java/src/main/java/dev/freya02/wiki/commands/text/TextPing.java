package dev.freya02.wiki.commands.text;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.text.CommandEvent;
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation;

// --8<-- [start:ping-java]
@Command
public class TextPing {
    // There are other properties you can set
    @JDATextCommandVariation(
            // Add more for subcommands
            path = { "ping" },
            description = "Pong!"
    )
    public void onTextPing(
            // Fallback command event since we have no args
            CommandEvent event
    ) {
        event.reply("Pong!").queue(message -> {
            event.getJDA().getRestPing().queue(ping -> {
                message.editMessage("Pong! " + ping + " ms").queue();
            });
        });
    }
}
// --8<-- [end:ping-java]
