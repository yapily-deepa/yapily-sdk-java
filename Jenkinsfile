node {

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Build Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - ${BRANCH_NAME} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
              sh "mvn clean test"
        }

        stage('Build') {
            sh "mvn package install -Dmaven.test.skip=true"
        }

        def GCLOUD_PROJECT_ID = "hydra-189817"
        def GCLOUD_SERVICE_ACCOUNT = "jenkins@jenkins-acacia.iam.gserviceaccount.com"

        sh "gcloud config set project ${GCLOUD_PROJECT_ID}"
        sh "gcloud auth activate-service-account ${GCLOUD_SERVICE_ACCOUNT} --key-file=/home/tomcat/gcloud-jenkins-acacia-service-account-credentials.json"

        stage('Deploy parent pom') {

            pom = readMavenPom file: 'pom.xml'
            groupId = pom.groupId
            artifactId = pom.artifactId

            def gcsFolder = createGoogleStorageDirectory(groupId,artifactId)

            sh "gsutil help"
        }

        stage('Deploy api-client') {

        }

        stage('Deploy sdk') {

        }

        stage('Deploy auth-client') {

        }
    }
}

def createGoogleStorageDirectory(groupId,artifactId) {

    def gcsFolder = groupId.replace(".","/")+"/"+artifactId

    sh "gcloud"

    gcsFolder
}

