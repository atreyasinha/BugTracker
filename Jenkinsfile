pipeline {
  agent any

  stages {
    stage('Build') {
      // step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartAllServices'], useCustomDockerComposeFile: true])
      steps {
        sh '/usr/local/bin/docker-compose up --build'
      }
    }

    // stage('Test') {
    //   steps {
    //     sh 'npx cypress run'
    //   }
    // }
  }
}