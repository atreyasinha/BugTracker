pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'docker-compose up -d web'
            }
        }
        
        stage('Test') {
            steps {
                sh './node_modules/.bin/cypress run'
            }
        }

        stage('Check') {
            steps {
                sh 'echo "This should not run"'
            }
        }
    }
}
