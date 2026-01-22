package io.github.freya022.wiki.messages

import dev.freya02.botcommands.typesafe.messages.api.IMessageSourceFactory
import dev.freya02.botcommands.typesafe.messages.api.annotations.MessageSourceFactory
import io.github.freya022.wiki.switches.wiki.WikiLanguage

@WikiLanguage(WikiLanguage.Language.KOTLIN)
// --8<-- [start:command_replies_factory-kotlin]
// No need to implement this interface
@MessageSourceFactory(
    bundleName = "MyBotMessages",
    ignoreEmptyLocales = true, // We set it to true as we only have a root bundle
)
interface CommandRepliesKtFactory : IMessageSourceFactory<CommandRepliesKt>
// --8<-- [end:command_replies_factory-kotlin]
