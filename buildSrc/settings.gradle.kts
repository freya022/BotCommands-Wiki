rootProject.name = "buildSrc"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../link-server") {
    dependencySubstitution {
        substitute(module("dev.freya02:link-server")).using(project(":"))
    }
}
