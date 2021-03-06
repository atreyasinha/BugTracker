pipeline {
    agent any
    
    stages {        
        stage('Build') {
            steps {
                sh '/usr/local/bin/docker-compose up -d'
            }
        }
        
        stage('Test') {
            steps {
                sh 'npm install cypress'
                sh './node_modules/.bin/cypress run'
            }
        }

        stage('Get GCP access Keys from Storj') {
            steps {
                sh '~/uplink cp sj://keys/bug-tracker-sa-credentials.json .'
                sh '~/uplink cp sj://terraform/terraform.tfstate .'
            }
        }

        stage('Destroy Cloud Run Infrastructure') {
            steps {
                sh 'terraform init'
                sh 'terraform destroy -auto-approve'
            }
        }

        stage('Build Container for Deployment') {
            steps {
                sh 'docker build -t b-t-registry .'
	   	        sh 'docker tag b-t-registry gcr.io/bug-tracker-334700/b-t-registry'
            }
        }

        stage('Push build to Container Registry') {
            steps {
                sh 'cat bug-tracker-sa-credentials.json | docker login -u _json_key --password-stdin https://gcr.io'
                sh 'docker push gcr.io/bug-tracker-334700/b-t-registry'
                sh 'docker stop $(docker ps -a -q)'
                sh 'docker system prune --all --volumes --force'
            }
        }

        stage('Deploy to Cloud Run') {
            steps {
                sh 'terraform plan'
                sh 'terraform apply -auto-approve'
                sh '~/uplink cp terraform.tfstate sj://terraform'
            }
        }

        stage('Delete GCP access Keys for Security') {
            steps {
                sh 'rm bug-tracker-sa-credentials.json'
            }
        }
    }

    post { 
        always { 
            sh 'docker stop $(docker ps -a -q)'
            sh 'docker system prune --all --volumes --force'
        }
    }
}
