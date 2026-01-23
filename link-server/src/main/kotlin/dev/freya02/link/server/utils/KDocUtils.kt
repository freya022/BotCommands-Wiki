package dev.freya02.link.server.utils

private val uppercaseRegex = Regex("[A-Z]")

fun String.toKDocCase(): String {
    return replace(uppercaseRegex) { "-${it.value.lowercase()}" }
}
