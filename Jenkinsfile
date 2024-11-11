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