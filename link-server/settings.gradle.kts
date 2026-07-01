rootProject.name = "link-server"

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
                includeVersionByRegex("io\\.github\\.freya022", "BotCommands.*", ".+_DEV$")
            }
        }
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
