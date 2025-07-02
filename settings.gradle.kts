pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cinejet"
include(":app")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:model")
include(":features:home:data")
include(":features:home:domain")

include(":core:design-system")
include(":features:home:ui")
include(":features:detail")
include(":features:detail:data")
include(":features:detail:domain")
include(":features:detail:ui")
