package dev.freya02.gradle.tasks

import dev.freya02.link.server.LinkServer
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import tools.jackson.dataformat.xml.XmlMapper
import java.lang.ProcessBuilder.Redirect
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

abstract class DeployWikiTask : DefaultTask() {

    @get:Input
    abstract val currentBCVersion: Property<String>

    @TaskAction
    fun deployWiki() {
        val latestBCVersion = getLatestBCVersion()
        val currentBCVersion = currentBCVersion.get()
        val alias = "${currentBCVersion[0]}.X"

        val embeddedLinkServer = LinkServer.embeddedLinkServer().start(wait = false)

        try {
            runProcessAndWait("uv", "run", "mike", "deploy", currentBCVersion, alias, "--update-aliases")
                .checkExitCode(message = "Failed to generate deployment")
        } finally {
            embeddedLinkServer.stop()
        }

        val isLatest = latestBCVersion == currentBCVersion
        if (isLatest) {
            runProcessAndWait("uv", "run", "mike", "set-default", alias)
                .checkExitCode(message = "Failed to set default")
        }
    }

    private fun getLatestBCVersion(): String {
        HttpClient.newHttpClient().use { client ->
            val mavenMetadata = client
                .send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI("https://repo1.maven.org/maven2/io/github/freya022/BotCommands/maven-metadata.xml"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString()
                )
                .body()
                .let(XmlMapper.shared()::readTree)

            return mavenMetadata.path("versioning").path("latest").stringValue()
        }
    }

    private fun runProcessAndWait(vararg args: String): Process {
        val process = ProcessBuilder(*args)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
        process.waitFor()
        return process
    }

    private fun Process.checkExitCode(message: String, expected: Int = 0) {
        val exitValue = exitValue()
        check(exitValue == expected) {
            "$message, exit code: $exitValue"
        }
    }
}
