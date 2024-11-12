pipeline {
    agent any

    environment {
        DB_HOST = 'ci-postgres'
        DB_PORT = '5432'
        DB_NAME = 'dronejobs'
        DB_USER = 'postgres'
        DB_PASSWORD = 'postgres'
    }

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t my-app-image -f .docker/Dockerfile .'
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    // Stop and remove any existing container named "my-app-container"
                    sh """
                        docker stop my-app-container || true
                        docker rm my-app-container || true
                    """
                    // Run the Docker container with the jenkins_network and environment variables
                    sh """
                        docker run -d --name my-app-container --network jenkins_pipeline_syp_jenkins -p 5000:5000 \
                        -e DB_HOST=ci-postgres \
                        -e DB_PORT=5432 \
                        -e DB_NAME=dronejobs \
                        -e DB_USER=postgres \
                        -e DB_PASSWORD=postgres \
                        my-app-image
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}