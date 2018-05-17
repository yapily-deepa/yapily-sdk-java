node {
	def helper = new yapily.jenkins.Helper()

	properties([disableConcurrentBuilds(),
                [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactNumToKeepStr: '5', numToKeepStr: '5']],
                parameters([string(description: '', name: 'API_VERSION'),
                            string(description: '', name: 'BRANCH_NAME')])
              ])

  slackSend "Started building Yapily SDK JAVA - ${env.JOB_NAME} ${env.BUILD_NUMBER} - (<${env.BUILD_URL}|Open>)"

  stage('Checkout') {
      checkout scm
  }

	if(params.API_VERSION){
		echo "Generate version: ${params.API_VERSION}"

	    stage('Publish to Git'){

        def updateClientModels = {
          sh "mvn versions:set-property -Dproperty=yapily-api-version -DnewVersion=${params.API_VERSION}"
          sh "mvn versions:set -DnewVersion=${params.API_VERSION}"
          sh "mvn versions:commit"
          Map replaceMap = [ "%SDK_VERSION%":params.API_VERSION ]
          def path = pwd()
          String readmeFile = "${path}/README.md"
          helper.replaceAllFile(readmeFile, replaceMap)
        }

        stage('Publish to Git'){

          helper.gitDeploy(params.BRANCH_NAME,
                           "Bump to ${params.API_VERSION}",
                           params.API_VERSION,
                           updateClientModels)
        }

        stage('Build') {
          helper.mavenBuild('sdk')
          helper.mavenBuild('examples')
        }

        stage('Install') {
          helper.mavenInstall('sdk')
          helper.mavenInstall('examples')
        }

        stage('Publish to Maven') {
          helper.mavenDeployPublic('sdk')
          helper.mavenDeployPublic('examples')
        }

	    slackSend color: "good", message: "Build successfully - ${params.API_VERSION}"
	}else{
		echo "No version set. Quitting."
	    slackSend color: "good", message: "No api version provided"
	}
}

