node {

    withMaven(jdk: 'Java8', includeSnapshotVersions:true) {

        stage('Build') {
              sh "mvn clean test"
        }

        stage('Build') {
            sh "mvn package install -Dmaven.test.skip=true"
        }
    }

}

