rootProject.name = "BotCommands-Wiki"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()

        exclusiveContent {
            forRepository {
                mavenLocal()
            }

            filter {
                includeVersionByRegex("io\\.github\\.freya022", "BotCommands-.+", ".+_DEV$")
            }
        }
    }
}

include(":wiki-commons")
include(":wiki-java")
include(":wiki-kotlin-commons")
include(":wiki-kotlin")
include(":wiki-kotlin-dsl")
