package io.github.freya022.wiki

import io.github.freya022.botcommands.api.core.JDAService
import io.github.freya022.botcommands.api.core.config.JDAConfiguration
import io.github.freya022.botcommands.api.core.events.BReadyEvent
import io.github.freya022.botcommands.api.core.light
import io.github.freya022.wiki.switches.wiki.WikiLanguage
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.IEventManager
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@WikiLanguage(WikiLanguage.Language.KOTLIN)
// --8<-- [start:jdaservice-kotlin]
@Service
class SpringBot(
    private val jdaConfiguration: JDAConfiguration,
    @param:Value($$"${bot.token}")
    private val token: String,
) : JDAService() {
    override val intents: Set<GatewayIntent> get() = jdaConfiguration.intents

    override val cacheFlags: Set<CacheFlag> get() = jdaConfiguration.cacheFlags

    override fun createJDA(event: BReadyEvent, eventManager: IEventManager) {
        // This uses JDABuilder#createLight, with the intents and the additional cache flags set above
        // It also sets the EventManager and a special rate limiter
        light(
            token,
            activity = Activity.customStatus("In Kotlin with ❤️")
        ) {
            // Other configs
        }
    }
}
// --8<-- [end:jdaservice-kotlin]
