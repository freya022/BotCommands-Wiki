package dev.freya02.wiki.emojis;

import io.github.freya022.botcommands.api.emojis.AppEmojisRegistry;
import io.github.freya022.botcommands.api.emojis.annotations.AppEmoji;
import io.github.freya022.botcommands.api.emojis.annotations.AppEmojiContainer;
import net.dv8tion.jda.api.entities.emoji.ApplicationEmoji;

// --8<-- [start:eager_app_emojis-java]
@AppEmojiContainer // Here you can change the base location of all emojis contained here
public class AppEmojis {
    // This annotation is optional,
    // you can use it to change where the emoji is fetched from,
    // or how it will be named on Discord.
    @AppEmoji(emojiName = "java_eager")
    public static final ApplicationEmoji java =
            // The field name must be the same as the string passed
            AppEmojisRegistry.get("java");
}
// --8<-- [end:eager_app_emojis-java]
