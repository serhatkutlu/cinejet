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
include(":features:search:data")
include(":features:search:domain")
include(":core:network-helper")
include(":features:see-all:data")
include(":core:data")

include(":features:see-all:domain")
include(":features:see-all:ui")
include(":features:search:ui")


include(":features:settings:ui")
include(":features:favorites:data")
include(":features:favorites:domain")
include(":features:favorites:ui")
include(":core:preferences")
include(":core:cache")
