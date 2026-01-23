package dev.freya02.link.server

// One solution to run a dev server on the CLI would have been to have tasks to start and stop a daemon
// but as IJ can run the server after building with Gradle, it is much easier and isn't worth implementing
fun main() {
    LinkServer.embeddedLinkServer().start(wait = true)
}
