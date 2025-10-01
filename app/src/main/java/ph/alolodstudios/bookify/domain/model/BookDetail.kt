package ph.alolodstudios.bookify.domain.model

import ph.alolodstudios.bookify.common.Constants

data class BookDetail(
    val title: String,
    val coverId: List<Int>,
    val description: String,
    val subjects: List<String>,
    val firstPublishYear: String,
    val isbn10: List<String>,
    val isbn13: List<String>,
    val ia: List<String> = emptyList(),
    val ocaid: String? = null
) {
    val coverUrl: List<String>
        get() = coverId.map { "${Constants.COVER_URL}$it-L.jpg" }
}
