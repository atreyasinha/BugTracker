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
                sh 'docker run -it -v $PWD:/e2e -w /e2e cypress/included:3.4.0'
            }
        }
    }
}
