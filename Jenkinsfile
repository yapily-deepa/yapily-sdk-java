node {

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Build Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - ${BRANCH_NAME} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
              sh "mvn clean package install"
        }
    }
}
