pipeline {
    agent none
    
    stages {
        stage('Build') {
            agent {
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
                  image 'cypress/base:10' 
                }
            }

            steps {
                sh 'npm ci'
                sh "npm run test:ci:record"
            }
        }
    }
}