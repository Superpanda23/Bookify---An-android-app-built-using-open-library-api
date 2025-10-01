package ph.alolodstudios.bookify.data.remote.dto

import com.google.gson.annotations.SerializedName
import ph.alolodstudios.bookify.domain.model.Book

data class BookDto(
    val title: String,
    @SerializedName("cover_i") val coverId: Int?,
    @SerializedName("author_name") val authors: List<String>?,
    @SerializedName("first_publish_year") val firstPublishedYear: Int?,
    @SerializedName("key") val workKey: String,
    @SerializedName("cover_edition_key") val coverEditionKey: String?,
    @SerializedName("subjects") val subjects: List<String>?,
)

fun BookDto.toBook(): Book {
    return Book(
        title = title,
        coverId = coverId,
        authors = authors ?: emptyList(),
        firstPublishYear = firstPublishedYear,
        workKey = workKey.removePrefix("/works/"),
        coverEditionKey = coverEditionKey,
        subjects = subjects ?: emptyList()
    )
}
