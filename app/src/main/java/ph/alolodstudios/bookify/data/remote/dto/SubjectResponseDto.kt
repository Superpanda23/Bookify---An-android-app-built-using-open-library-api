package ph.alolodstudios.bookify.data.remote.dto

import ph.alolodstudios.bookify.domain.model.Book

data class SubjectResponseDto(
    val key: String,
    val name: String,
    val work_count: Int,
    val works: List<SubjectWorkDto>
)

data class SubjectWorkDto(
    val key: String,
    val title: String,
    val edition_count: Int?,
    val cover_id: Int?,
    val cover_edition_key: String?,
    val authors: List<SubjectAuthorDto>?,
    val subject: List<String>?,
    val first_publish_year: Int?
)

data class SubjectAuthorDto(
    val name: String,
    val key: String
)

fun SubjectWorkDto.toBook(): Book {
    return Book(
        workKey = key.removePrefix("/works/"),
        title = title,
        authors = authors?.map { it.name } ?: emptyList(),
        coverId = cover_id,
        subjects = subject ?: emptyList(),
        coverEditionKey = cover_edition_key,
        firstPublishYear = first_publish_year ?: 0
    )
}
