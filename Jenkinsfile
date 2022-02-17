pipeline {
    agent any
    
    stages {        
        stage('Build') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        
        stage('Test') {
            steps {
                sh './node_modules/.bin/cypress run'
            }
        }

        stage('Get GCP access Keys from Storj') {
            steps {
                sh 'uplink cp sj://keys/bug-tracker-sa-credentials.json .'
            }
        }

        stage('Destroy Cloud Run Infrastructure') {
            steps {
                sh 'terraform init'
//                 sh 'terraform destroy -auto-approve'
            }
        }

        stage('Build Container for Deployment') {
            steps {
                sh 'docker build -t b-t-registry .'
            }
        }

        stage('Push build to Container Registry') {
            steps {
		withEnv(['GCLOUD_PATH=/var/jenkins_home/google-cloud-sdk/bin']) {
			sh 'gcloud auth activate-service-account --key-file bug-tracker-sa-credentials.json'
			sh 'gcloud auth configure-docker Bug-Tracker'
			sh 'docker tag b-t-registry gcr.io/bug-tracker-334700/b-t-registry'
			sh 'docker push gcr.io/bug-tracker-334700/b-t-registry'
            	}
            }
        }

        stage('Remove docker builds on local') {
            steps {
                sh 'docker rmi web:local -f'
                sh 'docker rmi b-t-registry'
                sh 'docker rmi gcr.io/bug-tracker-334700/b-t-registry'
            }
        }

        stage('Deploy to Cloud Run') {
            steps {
                sh 'terraform plan'
                sh 'terraform apply -auto-approve'
            }
        }

        stage('Delete GCP access Keys for Security') {
            steps {
                sh 'rm bug-tracker-sa-credentials.json'
            }
        }
    }
}
