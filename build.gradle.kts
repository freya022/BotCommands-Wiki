import dev.freya02.gradle.tasks.DeployWikiTask
import nl.littlerobots.vcu.plugin.resolver.ModuleVersionCandidate
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

plugins {
    alias(libs.plugins.version.catalog.update)
}

versionCatalogUpdate {
    versionSelector(object : nl.littlerobots.vcu.plugin.resolver.ModuleVersionSelector {
        override fun select(candidate: ModuleVersionCandidate): Boolean {
            // Don't update major
            if (candidate.currentVersion[0] != candidate.candidate.version[0])
                return false

            return VersionSelectors.PREFER_STABLE.select(candidate)
        }
    })
}

val deployWiki = tasks.register<DeployWikiTask>("deployWiki") {
    group = "wiki"
    description = "Generates the wiki deployment"

    currentBCVersion = libs.versions.botcommands
}
