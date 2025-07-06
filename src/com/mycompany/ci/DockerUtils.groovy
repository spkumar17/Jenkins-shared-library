// src/com/mycompany/ci/DockerUtils.groovy
package com.mycompany.ci

class DockerUtils implements Serializable {
    def script

    DockerUtils(script) {
        this.script = script
    }

    void buildImage(String serviceName, String dockerfilePath, String version) {
        script.sh "docker build -t ${serviceName}:${version} -f ${dockerfilePath} ."
    }

    void pushImage(String serviceName, String version, String registry) {
        script.withCredentials([script.usernamePassword(
            credentialsId: 'docker-hub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )]) {
            script.sh "echo \"${script.DOCKER_PASS}\" | docker login -u \"${script.DOCKER_USER}\" --password-stdin ${registry}"
            script.sh "docker push ${serviceName}:${version}"
        }
    }
}
