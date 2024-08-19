pluginManagement {
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
        maven { url = uri ("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-android/") }
        google()
        mavenCentral()
    }
}

rootProject.name = "VkNewsClient"
include(":app")
 