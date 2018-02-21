node {

    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']]])

    slackSend "Deployment Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} - (<${env.BUILD_URL}|Open>)"

    stage('Checkout') {
        checkout scm
    }

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Test') {
            sh "mvn clean package install"
        }

        stage('Build') {
            sh "mvn clean package install -Dmaven.test.skip=true"
        }

        if(BRANCH_NAME == "master" || BRANCH_NAME =~ "release/") {

            stage('Deploy sdk') {
                dir('sdk') {
                    def gcsFolder = createModuleGoogleStorageDirectory()
                    uploadPomAndArtifact(gcsFolder)
                }
            }

            stage('Deploy example') {
                dir('example') {
                    def gcsFolder = createModuleGoogleStorageDirectory()
                    uploadPomAndArtifact(gcsFolder)
                }
            }

        }

    }
}

def createGoogleStorageDirectory() {

    pom = readMavenPom file: 'pom.xml'
    groupId = pom.groupId
    artifactId = pom.artifactId
    version = pom.version

    def gcsFolder = groupId.replace(".","/")+"/"+artifactId+"/"+version
    gcsFolder
}


def createModuleGoogleStorageDirectory() {

    pom = readMavenPom file: 'pom.xml'
    groupId = pom.parent.groupId
    artifactId = pom.artifactId
    version = pom.parent.version
    def gcsFolder = groupId.replace(".","/")+"/"+artifactId+"/"+version
    gcsFolder
}

def uploadPom(gcsFolder) {

    sh 'gsutil cp pom.xml gs://maven.yapily.com/'+gcsFolder+'/pom.xml'
}

def uploadPomAndArtifact(gcsFolder) {

    uploadPom(gcsFolder)
    def jarFile = getJarFromTarget()
    sh 'gsutil cp target/'+jarFile+' gs://maven.yapily.com/'+gcsFolder+'/'+jarFile
}

def getJarFromTarget() {

    findFiles(glob: 'target/*.jar')[0].name
}
