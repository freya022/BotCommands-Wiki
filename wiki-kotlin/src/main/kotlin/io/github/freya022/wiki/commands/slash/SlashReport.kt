package io.github.freya022.wiki.commands.slash

import dev.freya02.botcommands.jda.ktx.components.TextInput
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption
import io.github.freya022.botcommands.api.modals.ModalEvent
import io.github.freya022.botcommands.api.modals.Modals
import io.github.freya022.botcommands.api.modals.annotations.ModalData
import io.github.freya022.botcommands.api.modals.annotations.ModalHandler
import io.github.freya022.botcommands.api.modals.annotations.ModalInput
import io.github.freya022.botcommands.api.modals.create
import net.dv8tion.jda.api.components.textinput.TextInputStyle
import net.dv8tion.jda.api.entities.Member

// --8<-- [start:report-kotlin]
@Command
class SlashReport(private val modals: Modals) {
    @JDASlashCommand(name = "report", description = "Reports a member")
    fun onSlashReport(
        event: GuildSlashEvent,
        @SlashOption(description = "The member to report") member: Member,
    ) {
        val modal = modals.create("Report a member") {
            label("Report message") {
                child = TextInput(INPUT_MESSAGE, TextInputStyle.PARAGRAPH) {
                    minLength = 10
                }
            }

            bindTo(MODAL_NAME, member)
        }

        event.replyModal(modal).queue()
    }

    @ModalHandler(MODAL_NAME)
    fun onReportModal(
        event: ModalEvent,
        @ModalData member: Member,
        @ModalInput(INPUT_MESSAGE) message: String,
    ) {
        event.reply("""
            You reported ${member.asMention} with the following message:
            $message
        """.trimIndent())
            .setEphemeral(true)
            .queue()
    }

    companion object {
        private const val MODAL_NAME = "SlashReport: modal"
        private const val INPUT_MESSAGE = "SlashReport: modal message"
    }
}
// --8<-- [end:report-kotlin]
