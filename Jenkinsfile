pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''
            cd nile-backend
            ./gradlew assemble
            cd ../nile-frontend
            npm i && npm run build
           '''
      }
    }

    stage('Test') {
      steps {
        sh '''
            cd nile-backend
            ./gradlew test
            cd ../nile-frontend
            npm run test
           '''
      }
    }

  }
}
