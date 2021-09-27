pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'python --version'
        sh 'python3 manage.py runserver'
      }
    }

    stage('Test') {
      steps {
        sh 'npx cypress run'
      }
    }
  }
}