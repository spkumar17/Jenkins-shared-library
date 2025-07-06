// vars/buildAndPushDocker.groovy
import com.mycompany.ci.DockerUtils
import com.mycompany.ci.BuildHelper

def call(Map config) {
    def dockerUtils = new DockerUtils(this)
    def buildHelper = config.buildHelper ?: new BuildHelper(this)

    buildHelper.printMessage("Building Docker image for ${config.serviceName}...")
    dockerUtils.buildImage(config.serviceName, config.dockerfilePath, config.version)

    buildHelper.printMessage("Pushing Docker image for ${config.serviceName}...")
    dockerUtils.pushImage(config.serviceName, config.version, config.registry)

    buildHelper.printMessage("Docker build and push complete for ${config.serviceName}.")
}
