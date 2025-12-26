package io.github.freya022.wiki.messages

import dev.freya02.botcommands.typesafe.messages.api.IMessageSourceFactory
import dev.freya02.botcommands.typesafe.messages.api.annotations.MessageSourceFactory
import io.github.freya022.wiki.switches.wiki.WikiLanguage

@WikiLanguage(WikiLanguage.Language.KOTLIN)
@MessageSourceFactory(bundleName = "MyBotMessages", ignoreEmptyLocales = true)
interface CommandRepliesPreferredLocaleKtFactory : IMessageSourceFactory<CommandRepliesPreferredLocaleKt>
