import dev.freya02.wiki.WikiMain;
import dev.freya02.wiki.switches.WikiDetailProfile;
import dev.freya02.wiki.switches.WikiDetailProfileChecker;

void main() {
    WikiDetailProfileChecker.currentProfile = WikiDetailProfile.Profile.SIMPLIFIED;

    WikiMain.INSTANCE.run();
}
