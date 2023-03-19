pipeline {
    agent { docker { image 'maven:3.9.0-eclipse-temurin-17' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}