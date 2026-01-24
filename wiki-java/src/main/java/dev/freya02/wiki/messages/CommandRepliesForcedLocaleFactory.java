package dev.freya02.wiki.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSourceFactory;
import dev.freya02.botcommands.typesafe.messages.api.annotations.MessageSourceFactory;

@MessageSourceFactory(bundleName = "MyBotMessages", ignoreEmptyLocales = true)
public interface CommandRepliesForcedLocaleFactory extends IMessageSourceFactory<CommandRepliesForcedLocale> {

}
