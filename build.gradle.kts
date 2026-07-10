import dev.freya02.gradle.tasks.DeployWikiTask
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

plugins {
    alias(libs.plugins.version.catalog.update)
}

versionCatalogUpdate {
    versionSelector(VersionSelectors.PREFER_STABLE)
}

val deployWiki = tasks.register<DeployWikiTask>("deployWiki") {
    group = "wiki"
    description = "Generates the wiki deployment"

    currentBCVersion = libs.versions.botcommands
}
