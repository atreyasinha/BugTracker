pipeline {
    agent none
    
    stages {
        stage('Build') {
            agent {
                label 'docker'
                dockerfile true
            }

            steps {
                sh 'python manage.py migrate'
                sh 'python manage.py runserver'
            }
        }

        stage('Test') {
            agent {
                docker { 
                  label 'docker'
                  image 'cypress/base:10' 
                }
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