pipeline {
    environment {
        imagename = "amsoua/demojenkinsdocker"
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
              withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'Sonar_Token') {
                   sh 'mvn -Dmaven.test.failure.ignore=true install sonar:sonar'
               }

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

        stage('Remove old deployment on prod') {
               steps {
                  script {
                     sh "kubectl delete services demojenkinsdocker-service"
                     sh "kubectl delete deploy demojenkinsdocker"
                  }
               }
            }
      stage('Deploy on prod') {
               steps {
                  script {
                     sh "kubectl apply -f deployment.yaml"
                  }
               }
            }
   }
}