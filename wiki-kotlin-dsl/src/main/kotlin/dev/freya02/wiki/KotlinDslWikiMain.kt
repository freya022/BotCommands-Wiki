package dev.freya02.wiki

import dev.freya02.wiki.switches.WikiDetailProfile
import dev.freya02.wiki.switches.WikiDetailProfileChecker

fun main() {
    WikiDetailProfileChecker.currentProfile = WikiDetailProfile.Profile.SIMPLIFIED

    WikiMain.run()
}
