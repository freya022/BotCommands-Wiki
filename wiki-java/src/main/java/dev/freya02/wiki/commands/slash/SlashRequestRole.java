package dev.freya02.wiki.commands.slash;

import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.application.slash.GuildSlashEvent;
import io.github.freya022.botcommands.api.commands.application.slash.annotations.JDASlashCommand;
import io.github.freya022.botcommands.api.modals.ModalEvent;
import io.github.freya022.botcommands.api.modals.Modals;
import io.github.freya022.botcommands.api.modals.annotations.ModalData;
import io.github.freya022.botcommands.api.modals.annotations.ModalHandler;
import io.github.freya022.botcommands.api.modals.annotations.ModalInput;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.components.selections.EntitySelectMenu.SelectTarget;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.components.textdisplay.TextDisplay;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.entities.Role;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

// --8<-- [start:request_role-java]
@Command
public class SlashRequestRole {
    private static final String MODAL_NAME = "SlashRequestRole: request role";
    private static final String INPUT_REASON = "SlashRequestRole: reason";
    private static final String INPUT_ROLE = "SlashRequestRole: role";
    private static final String INPUT_DETAILS = "SlashRequestRole: details";

    private final Modals modals;

    public SlashRequestRole(Modals modals) {
        this.modals = modals;
    }

    @JDASlashCommand(name = "request_role", description = "Request a role")
    public void onSlashRequestRole(GuildSlashEvent event) {
        var modal = modals.create("Report a member")
                .addComponents(
                        TextDisplay.of("""
                                ### Welcome!
                                Please read the following before continuing:
                                1. Select the role you wish to get
                                2. Select the reason why you want this role
                                3. (Optional) Add any detail about your request
                                
                                -# Abuse of this system may result in penalties
                                """),

                        Label.of("Role",
                                EntitySelectMenu.create(INPUT_ROLE, SelectTarget.ROLE).build()
                        ),

                        Label.of("Reason",
                                StringSelectMenu.create(INPUT_REASON)
                                        .addOption("It looks cool!", "cool")
                                        .addOption("I like the color", "color")
                                        .addOption("I am interested in the relevant discussions", "discussions")
                                        .build()
                        ),

                        Label.of("Details",
                                TextInput.create(INPUT_DETAILS, TextInputStyle.PARAGRAPH)
                                        .setRequired(false)
                                        .build()
                        )
                )
                .bindTo(MODAL_NAME, Instant.now().toEpochMilli())
                .build();

        event.replyModal(modal).queue();
    }

    @ModalHandler(MODAL_NAME)
    public void onRequestRoleModal(
            ModalEvent event,
            // The data passed to "bindTo"
            @ModalData long startTime, // Epoch millis
            // The values of the modal inputs
            @ModalInput(INPUT_REASON) List<String> reason,
            @ModalInput(INPUT_ROLE) List<Role> roles,
            @ModalInput(INPUT_DETAILS) String details
    ) {
        var timeToRequest = Duration.between(Instant.ofEpochMilli(startTime), Instant.now());

        event.replyFormat("""
                                Your request has been submitted! It took you %ss to complete it.
                                Reason: %s
                                Roles: %s
                                Details: %s
                                """,
                        timeToRequest.toMillis() / 1000.0,
                        reason.getFirst(),
                        roles.stream().map(Role::getAsMention).collect(Collectors.joining(", ")),
                        details.isEmpty() ? "<empty>" : details
                )
                .setEphemeral(true)
                .queue();
    }
}
// --8<-- [end:request_role-java]
