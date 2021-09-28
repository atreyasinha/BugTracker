pipeline {
    agent none
    
    stages {
        stage('Build') {
            steps {
                sh 'ls'
            }
        }

        stage('Test') {
            agent {
                docker { 
                  image 'cypress/base:10' }
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