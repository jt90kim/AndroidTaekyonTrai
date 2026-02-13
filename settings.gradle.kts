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
        google()
        mavenCentral()
        flatDir {
            dirs(File(settingsDir, "../unity-export/unityLibrary/libs"))
        }
    }
}

rootProject.name = "AndroidTaekyonTraining"
include(":app")
include(":unityLibrary")
project(":unityLibrary").projectDir = File(settingsDir, "../unity-export/unityLibrary")
 