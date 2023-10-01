[set-logback-file-java]: https://github.com/freya022/BotCommands/blob/1269ca668d702d3d23eef3734eaf9c61da7de3c7/BotTemplate/BotTemplate-Java/src/main/java/io/github/freya022/bot/Main.java#L15
[set-logback-file-kotlin]: https://github.com/freya022/BotCommands/blob/1269ca668d702d3d23eef3734eaf9c61da7de3c7/BotTemplate/BotTemplate-Kotlin/src/main/kotlin/io/github/freya022/bot/Main.kt#L29

[//]: # (TODO separate bot template & logging in two files, but in the same "setup" folder)
This tutorial assumes you know how to use your IDE (preferably IntelliJ IDEA), using Maven or Gradle,
and how to install PostgreSQL.

## Using the bot template

The framework provides Kotlin and Java templates for a quick start, 
while the template uses Maven, it can be substituted with Gradle.

### Cloning the template 
You can clone the template with the following command: (use "Git Bash" on Windows)
=== "Kotlin"
    
    ```sh
    git clone -n --depth=1 --filter=tree:0 --branch=3.X https://github.com/freya022/BotCommands && cd BotCommands && git sparse-checkout set --no-cone BotTemplate/BotTemplate-Kotlin && git checkout && mv ./BotTemplate/BotTemplate-Kotlin/{.,}* . || rm -r BotTemplate && rm -rf .git
    ```

=== "Java"

    ```sh
    git clone -n --depth=1 --filter=tree:0 --branch=3.X https://github.com/freya022/BotCommands && cd BotCommands && git sparse-checkout set --no-cone BotTemplate/BotTemplate-Java && git checkout && mv ./BotTemplate/BotTemplate-Java/{.,}* . || rm -r BotTemplate && rm -rf .git
    ```

!!! info

    You can safely ignore these warnings:
    ``` { .txt .no-copy }
    mv: cannot move './BotTemplate/BotTemplate-[Lang]/.' to a subdirectory of itself, './.'
    mv: cannot move './BotTemplate/BotTemplate-[Lang]/..' to a subdirectory of itself, './..'
    ```

### Preparing your project
You can then rename the cloned `BotCommands` folder to your project's name, 
enable version control, and configure your build file:
=== "Maven"

    1. In your pom.xml, change the `groupId` to your base package name, such as `io.github.[your username]`, in lowercase.
    2. Replace the `artifactId` and `finalName` (in the `maven-shade-plugin`), with your project's name.

=== "Gradle"

    1. Create a new Gradle project, then copy all files, except `pom.xml`.

    2. Update your build script:

    ```kotlin title="build.gradle.kts"
    import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
    
    plugins {
        java
        application
        id("com.github.johnrengelman.shadow") version "7.1.2"
    }
    
    application.mainClass.set("io.github.name.bot.Main")    //TODO change here
    group = "io.github.name"                                //TODO change here
    version = "1.0-SNAPSHOT"
    
    tasks.withType<ShadowJar> {
        archiveFileName.set("your-project-name.jar")        //TODO change here
    }
    
    repositories {
        mavenLocal()
        mavenCentral()
    }
    
    dependencies {
        //Logging
        implementation("org.slf4j:slf4j-api:[VERSION]") //See https://mvnrepository.com/artifact/org.slf4j/slf4j-api/latest
        implementation("ch.qos.logback:logback-classic:1.2.11") //See https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/latest

        //JDA
        implementation("net.dv8tion:JDA:[VERSION]") //See https://mvnrepository.com/artifact/net.dv8tion/JDA/latest
        implementation("io.github.freya022:BotCommands:[VERSION]") //See https://mvnrepository.com/artifact/io.github.freya022/BotCommands/latest
    }
    
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.isIncremental = true
    
        //BC supports Java 17 and above
        options.release.set(17)
    }
    ```

You can then update the package of the template, so it matches your group ID.

!!! note
    
    Don't forget to update the main class in your build script, as well as the main package in the `Main` class.

### Running the bot

!!! warning

    The framework (optionally) uses a database for its components (and so other features depending on components),
    you are required to use PostgreSQL, and so the template is already configured for it,
    including a connection pool (HikariCP), and a database migrator (Flyway).

[//]: # (TODO update depending on SQLite compatibility)

#### Configuring the bot

=== "Dev config"

    1. Copy the `config-template` folder as `dev-config`, 
    and edit the `config.json` with your bot token, prefixes, owner ID and the database details.
    2. Delete `logback.xml`.

    ??? info "How your project folder should look like"

        ```
        IntelliJ-Projects/
        └── ProjectName/
            ├── dev-config/
            │   ├── config.json
            │   └── logback-test.xml
            ├── src/
            │   └── ..
            └── pom.xml/build.gradle.kts
        ```

=== "Prod config"

    1. Copy the `config-template` folder as `config`, 
    and edit the `config.json` with your bot token, prefixes, owner ID and the database details.
    2. Delete `logback-test.xml`.

    ??? info "How your deployed bot's folder should look like"
    
        ```
        ProjectName/
        ├── config/
        │   ├── config.json
        │   └── logback.xml
        └── ProjectName.jar
        ```

#### Making the JAR

During development, simply run the main class in your IDE.

For your production environment, making a JAR is as simple as opening "Run Anything"
by pressing ++ctrl++ twice in IntelliJ, then running:

=== "Maven"

    ```
    mvn package
    ```

=== "Gradle"

    ```
    gradle shadowJar
    ```

This will generate an executable jar, which you should be running using `#!sh java -jar YourBotName.jar`.

## Logging

In case you are **not** using the bot template, you can add a logger with the following dependencies:

- [slf4j-api](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/latest)
- [logback-classic](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/latest)

You can then create a `logback.xml` file, which you can put in the root of your resources (`src/main/resources`),
or in another place (such as in a config directory), as shown in the bot template ([Java][set-logback-file-java] / [Kotlin][set-logback-file-kotlin])

Here are the logback configs used in the [bot template](https://github.com/freya022/BotCommands/tree/3.X/BotTemplate):

=== "Dev config"

    ```xml title="logback-test.xml"
    --8<-- "https://raw.githubusercontent.com/freya022/BotCommands/3.X/BotTemplate/BotTemplate-Kotlin/config-template/logback-test.xml"
    ```

=== "Prod config"

    ```xml title="logback.xml"
    --8<-- "https://raw.githubusercontent.com/freya022/BotCommands/3.X/BotTemplate/BotTemplate-Kotlin/config-template/logback.xml"
    ```