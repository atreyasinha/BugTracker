pipeline {
    agent none
    
    stages {
        stage('Build') {
            // agent {
            //     dockerfile {
            //       filename "back-end/dockerfiles/ci/Dockerfile"
            //     }
            // }

            // steps {
            //     sh 'python manage.py migrate'
            //     sh 'python manage.py runserver'
            // }
            steps {
                sh 'ls'
            }

        }

        stage('Test') {
            agent {
                docker { image 'cypress/base:10' }
            }
            environment {
              CYPRESS_RECORD_KEY = credentials('cypress-example-kitchensink-record-key')
            }

            steps {
                sh 'npm ci'
                sh "npm run test:ci:record"
            }
        }
    }
}