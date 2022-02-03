# Writing slash commands

Slash commands are the new way of defining commands, even though Discord provides limitations about them, we do have some advantages such as auto-completion and easier parsing - things that regular commands can't do, unless you use an argument style like `-option=value`

## A few keywords

* `ApplicationCommand` - Must be extended by the class which contains applications commands
* `@AppOption` - Mandatory on options, also allows you to set the option name and description, by default the name is the parameter name (of the method) and description is `No description`
* `@JDASlashCommand` - Annotation for methods which marks slash commands

## Making a slash command

A slash command is similar to a regex prefixed command - You extend `ApplicationCommand` on your class and use `@JDASlashCommand` on every method you want to be a slash command

Your method has to:
* Be public
* Have `GuildSlashEvent` (for guild-only slash commands, if not specified explicitly, a slash command is guild-only) as first parameter, or a `GlobalSlashEvent` for global commands
* Be annotated `@JDASlashCommand`

## Examples

??? note "Basic `/ping` command"

    ```java
    public class SlashPing extends ApplicationCommand {
        @JDASlashCommand(
                guildOnly = false,
                name = "ping",
                description = "Pong !"
        )
        public void onPing(GlobalSlashEvent event) {
            event.deferReply().queue();
    
            final long gatewayPing = event.getJDA().getGatewayPing();
            event.getJDA().getRestPing()
                    .queue(l -> event.getHook()
                            .sendMessageFormat("Gateway ping: **%d ms**\nRest ping: **%d ms**", gatewayPing, l)
                            .queue());
        }
    }
    ```

??? note "Example with choices"

    ```java
    public class SlashSay extends ApplicationCommand {
        // If the method is placed in the same file then it is guaranteed to be only the "say" command path,
        // so it won't interfere with other commands
        @Override
        @NotNull
        public List<Command.Choice> getOptionChoices(@Nullable Guild guild, @NotNull CommandPath commandPath, int optionIndex) {
            if (optionIndex == 0) { //First option
                return List.of(
                        //Only choices here are "Hi" and "Hello" and gets "translated" to their respective values
                        new Command.Choice("Hi", "Greetings, comrad"),
                        new Command.Choice("Hello", "Oy")
                );
            }
    
            return List.of();
        }
    
        @JDASlashCommand(
                //This command is guild-only by default
                name = "say",
                description = "Says what you type"
        )
        public void say(GuildSlashEvent event,
                        //Option name is by default the parameter name
                        @AppOption(description = "What you want to say") String text) {
            event.reply("Your choice: " + text).queue();
        }
    }
    ```

## Adding or removing *application* commands from certain Guilds

You can specify which application commands are available on a per-guild basis by using a `SettingsProvider`

You do need to set the `SettingsProvider` with `CommandsBuilder#setSettingsProvider`

Suppose you have a slash command `info`:
??? note "The `/info` command"

    ```java
    public class SlashInfo extends ApplicationCommand {
        @JDASlashCommand(name = "info", subcommand = "user")
        public void userInfo(GuildSlashEvent event, @AppOption User user) {
            event.reply("User: " + user).queue();
        }
    
        @JDASlashCommand(name = "info", subcommand = "channel")
        public void channelInfo(GuildSlashEvent event, @AppOption TextChannel channel) {
            event.reply("Channel: " + channel).queue();
        }
    
        @JDASlashCommand(name = "info", subcommand = "role")
        public void roleInfo(GuildSlashEvent event, @AppOption Role role) {
            event.reply("Role: " + role).queue();
        }
    }
    ```

You then want this command to be disabled by default in every guild (so require a later manual activation by Guild moderators for example):
??? note "The `BasicSettingsProvider` class"

    ```java
    public class BasicSettingsProvider implements SettingsProvider {
        private static final Logger LOGGER = Logging.getLogger();
        private final Map<Long, List<String>> disabledCommandsMap = new HashMap<>();
        private final BContext context;
    
        public BasicSettingsProvider(BContext context) {
            this.context = context;
        }
    
        @Override
        @NotNull
        public CommandList getGuildCommands(@NotNull Guild guild) {
            return CommandList.notOf(getBlacklist(guild));
        }
    
        @NotNull
        private List<String> getBlacklist(Guild guild) {
            //Blacklist filter - the ArrayList is created only if the guild's ID was not already in the map.
            return disabledCommandsMap.computeIfAbsent(guild.getIdLong(), x -> {
                final ArrayList<String> disabledCommands = new ArrayList<>();
    
                //Let's say the info command is disabled by default
                disabledCommands.add("info");
    
                return disabledCommands;
            });
        }
    
        //This is for the part where you want to update the command list later
        // So you can use this method to "enable" an application command for a guild
        // For example in a text command
        public void addCommand(Guild guild, String commandName) {
            getBlacklist(guild).remove(commandName); //Removes the command from the blacklist
    
            //You should handle the exceptions inside the completable future, in case an error occurred
            context.scheduleApplicationCommandsUpdate(guild, false, false);
        }
    }
    ```

You can then simply set the `SettingsProvider` in `CommandsBuilder`:

??? note "How to set the settings provider"

    ```java
    var builder = CommandsBuilder.withPrefix(...)
    builder
        .setSettingsProvider(new BasicSettingsProvider(builder.getContext()))
        ...
        .build(...);
    ```

## Updating existing commands on the fly

Let's say a Guild moderator decides to enable the /info command in his Guild, you would need to take your `BasicSettingsProvider` (from `BContext#getSettingsProvider`), cast it to your class and then use a method to remove the command from the Guild's blacklist, then finally call `BContext#scheduleApplicationCommandsUpdate` to update the commands and the slash commands local cache

??? note "How to enable back the command and update the slash commands list - Using a regular command"

    ```java
    @Category("Moderation")
    @UserPermissions(Permission.MANAGE_ROLES)
    public class EnableInfoCommand extends TextCommand {
        @JDATextCommand(
                name = "enableinfocommand",
                description = "Enables the /info command"
        )
        public void execute(CommandEvent event) {
            if (event.getMember().canInteract(event.getGuild().getSelfMember())) {
                final BasicSettingsProvider settingsProvider = (BasicSettingsProvider) event.getContext().getSettingsProvider();
    
                if (settingsProvider == null) {
                    event.indicateError("No settings provider has been set").queue();
    
                    return;
                }
    
                settingsProvider.addCommand(event.getGuild(), "info");
    
                event.reactSuccess().queue();
            } else {
                event.indicateError("You cannot do this").queue();
            }
        }
    }
    ```