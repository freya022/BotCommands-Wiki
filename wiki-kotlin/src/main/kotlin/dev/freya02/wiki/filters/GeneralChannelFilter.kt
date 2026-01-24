package dev.freya02.wiki.filters

import dev.freya02.botcommands.jda.ktx.coroutines.await
import dev.freya02.botcommands.jda.ktx.messages.reply_
import io.github.freya022.botcommands.api.components.ComponentInteractionFilter
import io.github.freya022.botcommands.api.core.service.annotations.BService
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent

// --8<-- [start:component_filter-kotlin]
@BService
class GeneralChannelFilter : ComponentInteractionFilter {
    private val channelId = 722891685755093076

    // So we can apply this filter on specific components
    override val global: Boolean = false

    override suspend fun checkSuspend(
        event: GenericComponentInteractionCreateEvent,
        handlerName: String?
    ): String? {
        if (event.channelIdLong == channelId) {
            event.reply_("This button can only be used in <#$channelId>", ephemeral = true).await()
            return "Button was used in the wrong channel"
        }
        return null
    }
}
// --8<-- [end:component_filter-kotlin]
