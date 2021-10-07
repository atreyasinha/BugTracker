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
                sh 'npx cypress run'
            }
        }
    }
}
