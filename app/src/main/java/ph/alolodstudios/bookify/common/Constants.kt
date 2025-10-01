package ph.alolodstudios.bookify.common

object Constants {
    const val BASE_URL = "https://openlibrary.org/"
    const val PARAM_WORK_KEY = "workKey"
    const val PARAM_EDITION_KEY = "editionId"
    const val PARAM_SUBJECT = "subject"
    const val PARAM_LIMIT = "limit"
    const val PARAM_OFFSET = "offset"
    const val SEARCH_ENDPOINT = "search.json"
    const val COVER_URL = "https://covers.openlibrary.org/b/id/"
    const val WORKS_ENDPOINT = "works/{$PARAM_WORK_KEY}.json"
    const val SUBJECTS_ENDPOINT = "subjects/{$PARAM_SUBJECT}.json"
    const val WORKS_EDITION_ENDPOINT = "works/{$PARAM_WORK_KEY}/editions.json"
    const val EDITION_ENDPOINT = "books/{$PARAM_EDITION_KEY}.json"
    const val IA_BASE_URL = "https://archive.org/download/"

    object ErrorMessages {
        const val UNEXPECTED_ERROR = "An unexpected error occurred."
        const val NO_INTERNET = "Couldn't reach server. Check your internet connection."
    }

}