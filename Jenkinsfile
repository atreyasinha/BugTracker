pipeline {
    agent none
    
    stages {
        stage('Build') {
            agent {
                dockerfile true
            }

            steps {
                sh 'python manage.py migrate'
                sh 'nohup python manage.py runserver 0.0.0.0:8080 &'
                echo 'This is working'
                sh 'netstat -nlp | grep :8080'
                sh 'kill pidnumber'
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