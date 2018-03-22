@Library('helper-library') _

node {
    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    def helper = new yapily.jenkins.Helper()

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Deployment Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
            helper.mavenTest('sdk')
        }

        stage('Build') {
            helper.mavenBuild('sdk')
            helper.mavenBuild('examples')
        }

        if(BRANCH_NAME == "master" || BRANCH_NAME =~ "release/") {

            stage('Deploy sdk') {
                helper.mavenDeploy('sdk')
            }

            stage('Deploy examples') {
                helper.mavenDeploy('examples')
            }

        }

    }
}
