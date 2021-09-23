package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build")) {
    expectSteps {
    }
    steps {
        insert(0) {
            maven {
                goals = "clean test"
                pomLocation = ".teamcity/pom.xml"
                runnerArgs = "-Dmaven.test.failure.ignore=true"
            }
        }
        insert(1) {
            python {
                environment = venv {
                }
                command = file {
                    filename = "manage.py"
                }
            }
        }
    }
}
