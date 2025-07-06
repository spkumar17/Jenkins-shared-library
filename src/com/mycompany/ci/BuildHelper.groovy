package com.mycompany.ci

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BuildHelper implements Serializable {
    def script

    BuildHelper(script) {
        this.script = script
    }

    void printMessage(String message) {
        def timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        script.echo "[${timestamp}] [BUILD HELPER] ${message}"
    }
}
