# Starting from scratch

Start by creating a project either using Maven or Gradle, it must run on Java 17+,
I recommend using [Java 25](https://adoptium.net/temurin/releases/?package=jdk&version=25).

!!! warning "Creating a new Maven project"

    When creating a Maven project in IntelliJ, do not choose `Maven Archetype` in `Generators`, you must use `New Project`.

## Adding the dependencies

The only strictly necessary dependencies are the framework and JDA:

[![](https://img.shields.io/maven-central/v/io.github.freya022/BotCommands?versionPrefix=3&label=BotCommands)](https://mvnrepository.com/artifact/io.github.freya022/BotCommands/latest)
[![](https://img.shields.io/maven-central/v/net.dv8tion/JDA?versionPrefix=5&label=JDA)](https://mvnrepository.com/artifact/net.dv8tion/JDA/latest)

!!! tip ""

    Omit the `v` prefix from the version, e.g. `5.0.0-beta.18`.

=== "Maven"

    ```xml
    <dependencies>
        ...

        <dependency>
            <groupId>net.dv8tion</groupId>
            <artifactId>JDA</artifactId>
            <version>JDA_VERSION</version>
        </dependency>
        <dependency>
            <groupId>io.github.freya022</groupId>
            <artifactId>BotCommands</artifactId>
            <version>BC_VERSION</version>
        </dependency>
    </dependencies>
    ```

=== "Kotlin Gradle"

    ```kotlin
    repositories {
        ...
        mavenCentral()
    }
    
    dependencies {
        ...

        implementation("net.dv8tion:JDA:JDA_VERSION")
        implementation("io.github.freya022:BotCommands:BC_VERSION")
    }
    ```

## Adding logging

Any SLF4J compatible logger should work; I recommend logback, which you can learn more [here](logging.md).

## Creating a config service

Create a small `Config` service, it can be a simple object with the properties you need, 
this will be useful when running your bot.

??? example

    === "Kotlin"
        ```kotlin
        class Config(val token: String, val ownerIds: List<Long>) {
            companion object {
                // Makes a service factory out of this property getter
                @get:BService
                val instance by lazy {
                    // Load your config
                }
            }
        }
        ```
    
    === "Java"
        ```java
        public class Config {
            private static Config INSTANCE = null;
        
            private String token;
            private List<Long> ownerIds;
        
            public String getToken() { return token; }
            public List<Long> getOwnerIds() { return ownerIds; }
        
            @BService // Makes this method a service factory that outputs Config objects
            public static Config getInstance() {
                if (INSTANCE == null) {
                    INSTANCE = // Load your config
                }
        
                return INSTANCE;
            }
        }
        ```

!!! info

    You can refer to [the Dependency Injection page](../using-botcommands/dependency-injection/index.md) for more details

## Creating the main class

As we've used a singleton pattern for your `Config` class, we can get the same instance anywhere, 
and still be able to get it as a service.

All you need to do to start the framework is `BotCommands#create`:

=== "Kotlin"

    ```kotlin title="Main.kt - Main function"
    val config = Config.instance

    BotCommands.create {
        // Optionally set the owner IDs if they differ from the owners in the Discord dashboard
        // addPredefinedOwners(config.ownerIds)

        // Add the base package of the application
        // All services and commands inside will be loaded
        addSearchPath("io.github.name.bot")

        textCommands {
            usePingAsPrefix = true // The bot will respond to his mention/ping
        }
    }    
    ```

=== "Java"

    ```java title="Main.java - Main method"
    final var config = Config.getInstance();

    BotCommands.create(builder -> {
        // Optionally set the owner IDs if they differ from the owners in the Discord dashboard
        // builder.addPredefinedOwners(config.getOwnerIds());

        // Add the base package of the application
        // All services and commands inside will be loaded
        builder.addSearchPath("io.github.name.bot");

        builder.textCommands(textCommands -> {
            textCommands.usePingAsPrefix(true);
        });
    });
    ```

## Creating a `JDAService`

Now if you try to start your bot, you will see an error about requesting a `JDAService` instance,
this is a service which is responsible for providing (part of) the configuration of your bot,
you must also start your JDA instance in `createJDA`, let's implement it!

??? info "What is it useful for?"

    - For the framework to receive all the events, useful for command updates, uploading [application emojis](../using-botcommands/app-emojis.md) and more
    - To start the bot when everything is ready
    - To check if event listeners have the required gateway intents/cache flags for them to be fired

=== "Kotlin"

    ```kotlin
    --8<-- "Bot.kt:jdaservice-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "Bot.java:jdaservice-java"
    ```

You can now run your bot! You should be able to run the help command, by mentioning your bot `@YourBot help`.

## Creating a runnable JAR

=== "Maven"

    ```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.6.1</version>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
                <configuration>
                    <transformers>
                        <transformer
                                implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                            <mainClass>io.github.name.bot.Main</mainClass> <!-- TODO change here -->
                        </transformer>
                        <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                    </transformers>
    
                    <createDependencyReducedPom>false</createDependencyReducedPom>
                    <finalName>${artifactId}</finalName>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```

=== "Kotlin Gradle"

    ```kotlin
    import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

    plugins {
        ...
        id("com.gradleup.shadow") version "9.3.1"
    }

    application.mainClass.set("io.github.name.bot.Main")    //TODO change here

    tasks.withType<ShadowJar> {
        // Multiple dependencies have files at the same place, keep all of them
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        mergeServiceFiles()
    
        // Set JAR name
        archiveFileName.set("${rootProject.name}.jar")
    }
    ```

While you can run the main class in your IDE during development,
you can create a JAR with all the dependencies by pressing ++ctrl++ twice in IntelliJ, then running:

=== "Maven"

    ```
    mvn package
    ```

=== "Kotlin Gradle"

    ```
    gradle shadowJar
    ```

## Other resources

Take a look at other wiki pages, such as [Dependency injection](../using-botcommands/dependency-injection/index.md), 
[Creating slash command](../using-commands/application-commands/slash-commands/index.md)
and [Using components](../using-components.md).

### Examples

You can find examples covering parts of the framework [here](https://github.com/freya022/BotCommands/tree/3.X/BotCommands-core/src/examples).

### Getting help

Don't hesitate to join [the support server](https://discord.gg/frpCcQfvTz) if you have any question!
