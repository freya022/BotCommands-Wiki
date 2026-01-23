package io.github.freya022.wiki

import ch.qos.logback.classic.ClassicConstants
import io.github.freya022.botcommands.api.core.BotCommands
import io.github.freya022.botcommands.api.core.config.DevConfig
import io.github.freya022.wiki.config.Config
import io.github.freya022.wiki.config.Environment
import io.github.oshai.kotlinlogging.KotlinLogging
import net.dv8tion.jda.api.interactions.DiscordLocale
import kotlin.io.path.absolutePathString
import kotlin.system.exitProcess

private val logger by lazy { KotlinLogging.logger {} } // Must not load before system property is set

private const val mainPackageName = "io.github.freya022.wiki"

object Main {
    @JvmStatic
    fun main(args: Array<out String>) {
        try {
            System.setProperty(ClassicConstants.CONFIG_FILE_PROPERTY, Environment.logbackConfigPath.absolutePathString())
            logger.info { "Loading logback configuration at ${Environment.logbackConfigPath.absolutePathString()}" }

            val config = Config.instance
            BotCommands.create {
                disableExceptionsInDMs = Environment.isDev

                // Optionally set the owner IDs if they differ from the owners in the Discord dashboard
//                addPredefinedOwners(*config.ownerIds.toLongArray())

                addSearchPath(mainPackageName)

                // You can customize the coroutines scopes of various features,
                // here we change the one used for responding to JDA events
                coroutineScopes {
                    eventManagerScopeFactory = defaultFactory("WikiBot Coroutine", corePoolSize = 4)
                }

                textCommands {
                    //Use ping as prefix if configured
                    usePingAsPrefix = "<ping>" in config.prefixes
                    prefixes += config.prefixes - "<ping>"
                }

                applicationCommands {
                    @OptIn(DevConfig::class)
                    disableAutocompleteCache = Environment.isDev

                    // It is safer (and also more convenient in containers) to use a database
                    // If you don't have a database, you can either remove this, which defaults to a file cache
                    // or use "fileCache" instead
                    databaseCache {
                        // Check command updates based on Discord's commands.
                        // This is only useful during development,
                        // as you can develop on multiple machines (but not simultaneously!).
                        // Using this in production is only going to waste API requests.
                        @OptIn(DevConfig::class)
                        checkOnline = Environment.isDev
                    }

                    // Guilds in which `@Test` commands will be inserted
                    testGuildIds += config.testGuildIds

                    // Add french (and root, for default descriptions) localization for application commands
                    addLocalizations("Commands", DiscordLocale.FRENCH)
                }

                localization {
                    addResponseBundle("EventReplies")
                }

                components {
                    // Enables usage of components
                    enable = true
                }

                appEmojis {
                    enable = true
                }
            }

            // There is no JDABuilder going on here, it's taken care of in Bot

            logger.info { "Loaded bot" }
        } catch (e: Exception) {
            logger.error(e) { "Unable to start the bot" }
            exitProcess(1)
        }
    }
}
