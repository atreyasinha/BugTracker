pipeline {
    agent none
    
    stages {
        stage('Build') {
            agent {
                dockerfile true
                label 'docker'
            }

            steps {
                sh 'python manage.py migrate'
                sh 'python manage.py runserver'
            }
        }

        stage('Test') {
            agent {
                docker { image 'cypress/base:10' }
                label 'docker'
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