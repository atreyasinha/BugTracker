pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'python --version'
        sh 'python manage.py runserver'
      }
    }

    stage('Test') {
      steps {
        sh 'npx cypress run'
      }
    }
  }
}