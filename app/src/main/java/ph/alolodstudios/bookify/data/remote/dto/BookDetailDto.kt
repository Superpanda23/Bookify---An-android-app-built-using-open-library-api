package ph.alolodstudios.bookify.data.remote.dto

import com.google.gson.annotations.SerializedName
import ph.alolodstudios.bookify.domain.model.BookDetail

data class BookDetailDto(
    val title: String,
    val description: Any? = null,
    val subjects: List<String>? = null,
    @SerializedName("first_publish_date") val firstPublishDate: String?,
    val covers: List<Int>? = null,
    @SerializedName("isbn_10") val isbn10: List<String>? = null,
    @SerializedName("isbn_13") val isbn13: List<String>? = null,
    val ia: List<String>? = null,
    val ocaid: String? = null
)
fun BookDetailDto.toBookDetail() : BookDetail{
    val desc = when (description) {
        is String -> description
        is Map<*, *> -> description["value"] as? String
        else -> null
    }
    return BookDetail(
        title = title,
        coverId = covers ?: emptyList(),
        description = desc ?: "No description available",
        subjects = subjects ?: emptyList(),
        firstPublishYear = firstPublishDate ?: "Unknown",
        isbn10 = isbn10 ?: emptyList(),
        isbn13 = isbn13 ?: emptyList(),
        ia = ia ?: emptyList(),
        ocaid = ocaid
    )
}

