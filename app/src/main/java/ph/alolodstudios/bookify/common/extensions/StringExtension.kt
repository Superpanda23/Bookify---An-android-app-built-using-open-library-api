package ph.alolodstudios.bookify.common.extensions

fun String.normalize(): String =
    this.trim().replace("\\s+".toRegex(), " ").lowercase()