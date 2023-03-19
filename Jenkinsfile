pipeline {
    agent { docker { image 'maven:3.8.1-eclipse-temurin-17' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}