pipeline {
    agent any

    environment {
        DB_HOST = 'localhost'
        DB_PORT = '5432'
        DB_NAME = 'ci-database'
        DB_USER = 'ciuser'
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
        stage('run') {
            steps {
                sh 'mv target/dronejobs-0.0.1-SNAPSHOT.jar ~/backend/dronejobs.jar'
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