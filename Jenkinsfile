pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''cd nile-backend
./gradlew build'''
      }
    }

    stage('Test') {
      steps {
        sh '''cd nile-backend
./gradlew clean test'''
      }
    }

  }
}