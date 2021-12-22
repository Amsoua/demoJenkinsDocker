pipeline {
    environment {
        imagename = "amsoua/demoJenkinsDocker"
        registryCredential = 'docker-hub-demo-token'
        dockerImage = ''
      }
   agent any
   tools {
        maven 'Maven 3.8.4'
    }
   stages {
       stage ('Initialize') {
           steps {
               sh '''
                   echo "PATH = ${PATH}"
                   echo "M2_HOME = ${M2_HOME}"
               '''
          }
        }
        stage ('Clean') {
           steps {
                echo "Cleaning up...."
                sh 'mvn clean'
           }
        }
       stage('Build') {
           steps {
              echo 'Building...'
              echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"
              sh 'mvn -Dmaven.test.failure.ignore=true -DskipTests=true install'

          }
       }
   stage('Test') {
         steps {
            echo 'Testing...'
            sh 'mvn test'
        }
   }
   stage('Deploy') {
     steps {
       echo 'Deploying...'
     }
   }
   stage('Cloning Git') {
         steps {
           git([url: 'https://github.com/Amsoua/demoJenkinsDocker.git', branch: 'master', credentialsId: 'github-demo-token'])

         }
       }
       stage('Building image') {
         steps{
           script {
             dockerImage = docker.build imagename
           }
         }
       }
       stage('Deploy Image') {
         steps{
           script {
             docker.withRegistry( '', registryCredential ) {
               dockerImage.push("$BUILD_NUMBER")
                dockerImage.push('latest')

             }
           }
         }
       }
       stage('Remove Unused docker image') {
         steps{
           sh "docker rmi $imagename:$BUILD_NUMBER"
            sh "docker rmi $imagename:latest"

         }
       }
  }
}