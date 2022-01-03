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
       stage('Deploy') {
         steps {
           echo 'Deploying...'
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
      stage('Deploy on prod') {
               steps {
                  script {
                     env.PIPELINE_NAMESPACE = "prod"
                     kubernetesDeploy kubeconfigId: 'docker', configs: 'deployment.yaml'
                  }
               }
            }
}