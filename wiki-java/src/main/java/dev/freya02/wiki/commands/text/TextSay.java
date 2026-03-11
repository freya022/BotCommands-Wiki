package dev.freya02.wiki.commands.text;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent;
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation;
import io.github.freya022.botcommands.api.commands.text.annotations.TextOption;

// --8<-- [start:say-java]
@Command
public class TextSay {
    @JDATextCommandVariation(
            path = "say",
            description = "Says something",
            // This will show up in the help command, if the prefix is '!' then it will show:
            // !say I like trains
            example = "I like trains"
    )
    public void onTextSay(BaseCommandEvent event, @TextOption String content) {
        event.reply(content).queue();
    }
}
// --8<-- [end:say-java]
