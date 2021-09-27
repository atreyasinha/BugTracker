pipeline {
  agent any

  stages {
    // stage('Build') {
    //   step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartAllServices'], useCustomDockerComposeFile: true])
    // }

    stage('Test') {
      steps {
        sh 'npx cypress run'
      }
    }
  }
}