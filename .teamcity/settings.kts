import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

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
        python {
            environment = venv {
            }
            command = file {
                filename = "manage.py"
            }
        }
        script {
            name = "Run tests"
            scriptContent = """ npx cypress run """
        }
    }

    triggers {
        vcs {
        }
    }
})
