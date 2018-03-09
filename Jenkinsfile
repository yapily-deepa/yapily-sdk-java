@Library('helper-library') _

node {
    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']],
                    parameters([string(defaultValue: 'jenkins@jenkins-acacia.iam.gserviceaccount.com',
                                    description: 'The gcloud service account that will be used for accessing the project resources', name: 'GcloudServiceAccount')])])

    def helper = new yapily.jenkins.Helper()

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Deployment Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }


    def GCLOUD_SERVICE_ACCOUNT = params.GcloudServiceAccount
    sh "gcloud auth activate-service-account ${GCLOUD_SERVICE_ACCOUNT} --key-file=/home/tomcat/gcloud-jenkins-acacia-service-account-credentials.json"

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
            helper.mavenTest('sdk')
        }

        stage('Build') {
            helper.mavenBuild('sdk')
            helper.mavenBuild('example')
        }

        if(BRANCH_NAME == "master" || BRANCH_NAME =~ "release/") {

            stage('Deploy sdk') {
                helper.mavenDeploy('sdk')
            }

            stage('Deploy example') {
                helper.mavenDeploy('example')
            }

        }

    }
}
