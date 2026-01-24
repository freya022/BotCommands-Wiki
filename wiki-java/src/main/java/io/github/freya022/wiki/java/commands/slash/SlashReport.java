package io.github.freya022.wiki.java.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.SlashOption;
import io.github.freya022.botcommands.api.modals.ModalEvent;
import io.github.freya022.botcommands.api.modals.Modals;
import io.github.freya022.botcommands.api.modals.annotations.ModalData;
import io.github.freya022.botcommands.api.modals.annotations.ModalHandler;
import io.github.freya022.botcommands.api.modals.annotations.ModalInput;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.entities.Member;

// --8<-- [start:report-java]
@Command
public class SlashReport {
    private static final String MODAL_NAME = "SlashReport: modal";
    private static final String INPUT_MESSAGE = "SlashReport: modal message";

    private final Modals modals;

    public SlashReport(Modals modals) {
        this.modals = modals;
    }

    @JDASlashCommand(name = "report", description = "Reports a member")
    public void onSlashReport(
            GuildSlashEvent event,
            @SlashOption(description = "The member to report") Member member
    ) {
        var modal = modals.create("Report a member")
                .addComponents(
                        Label.of("Report message",
                                TextInput.create(INPUT_MESSAGE, TextInputStyle.PARAGRAPH)
                                        .setMinLength(10)
                                        .build()
                        )
                )
                .bindTo(MODAL_NAME, member)
                .build();

        event.replyModal(modal).queue();
    }

    @ModalHandler(MODAL_NAME)
    public void onReportModal(
            ModalEvent event,
            @ModalData Member member,
            @ModalInput(INPUT_MESSAGE) String message
    ) {
        event.replyFormat("""
                                You reported %s with the following message:
                                %s
                                """,
                        member.getAsMention(),
                        message)
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:report-java]
