// vars/buildAndPushDocker.groovy
import com.mycompany.ci.DockerUtils // Import the helper class

def call(Map config) {
    def dockerUtils = new DockerUtils(this) // Pass 'this' for pipeline steps

    buildHelper.printMessage("Building Docker image for ${config.serviceName}...")
    dockerUtils.buildImage(config.serviceName, config.dockerfilePath, config.version)

    buildHelper.printMessage("Pushing Docker image for ${config.serviceName}...")
    dockerUtils.pushImage(config.serviceName, config.version, config.registry)

    buildHelper.printMessage("Docker build and push complete for ${config.serviceName}.")
}
