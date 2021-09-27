import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

version = "2021.1"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = "dockerd"
        }

        script {
            scriptContent = "docker run -i -v .:/e2e -w /e2e cypress/included:3.4.0"
        }
    }

    triggers {
        vcs {
        }
    }
})
