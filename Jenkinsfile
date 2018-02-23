@Library('helper-library') _

node {

    def helper = new com.yapily.jenkins.Helper()

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Deployment Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
            dir('sdk') {
                sh "mvn test"
            }
        }

        stage('Build') {
            dir('sdk') {
                sh "mvn clean package install -Dmaven.test.skip=true"
            }
            dir('example') {
                sh "mvn clean package install -Dmaven.test.skip=true"
            }
        }

        if(BRANCH_NAME == "master" || BRANCH_NAME =~ "release/") {

            stage('Deploy sdk') {
                dir('sdk') {
                    def gcsFolder = helper.createGoogleStorageDirectory()
                    helper.uploadPomAndArtifact(gcsFolder)
                }
            }

            stage('Deploy example') {
                dir('example') {
                    def gcsFolder = helper.createGoogleStorageDirectory()
                    helper.uploadPomAndArtifact(gcsFolder)
                }
            }

        }

    }
}
