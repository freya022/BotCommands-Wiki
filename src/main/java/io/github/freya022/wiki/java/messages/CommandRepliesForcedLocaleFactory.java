package io.github.freya022.wiki.java.messages;

import dev.freya02.botcommands.typesafe.messages.api.IMessageSourceFactory;
import dev.freya02.botcommands.typesafe.messages.api.annotations.MessageSourceFactory;
import io.github.freya022.wiki.switches.wiki.WikiLanguage;

@WikiLanguage(WikiLanguage.Language.JAVA)
@MessageSourceFactory(bundleName = "MyBotMessages", ignoreEmptyLocales = true)
public interface CommandRepliesForcedLocaleFactory extends IMessageSourceFactory<CommandRepliesForcedLocale> {

}
