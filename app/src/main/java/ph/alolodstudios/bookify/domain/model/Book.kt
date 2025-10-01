package ph.alolodstudios.bookify.domain.model

import ph.alolodstudios.bookify.common.Constants

data class Book(
    val title: String,
    val coverId: Int?,
    val authors: List<String> = emptyList(),
    val firstPublishYear: Int? = null,
    val workKey: String,
    val coverEditionKey: String?,
    val subjects: List<String>
) {
    val coverUrl: String?
        get() = coverId?.let { "${Constants.COVER_URL}$it-M.jpg" }
}
