pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Build And Push Image') {
            steps {
                sh './mvnw com.google.cloud.tools:jib-maven-plugin:1.0.0:dockerBuild'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}