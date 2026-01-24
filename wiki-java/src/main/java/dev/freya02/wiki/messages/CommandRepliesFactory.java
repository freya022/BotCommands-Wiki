package dev.freya02.wiki.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSourceFactory;
import dev.freya02.botcommands.typesafe.messages.api.annotations.MessageSourceFactory;

// --8<-- [start:command_replies_factory-java]
// No need to implement this interface
@MessageSourceFactory(
        bundleName = "MyBotMessages",
        ignoreEmptyLocales = true // We set it to true as we only have a root bundle
)
public interface CommandRepliesFactory extends IMessageSourceFactory<CommandReplies> {

}
// --8<-- [end:command_replies_factory-java]
