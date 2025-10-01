package ph.alolodstudios.bookify.data.remote

import ph.alolodstudios.bookify.common.Constants
import ph.alolodstudios.bookify.data.remote.dto.BookDetailDto
import ph.alolodstudios.bookify.data.remote.dto.BookDto
import ph.alolodstudios.bookify.data.remote.dto.SubjectResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenLibraryApi {

    @GET(Constants.SEARCH_ENDPOINT)
    suspend fun searchBooks(
        @Query("q") query: String,
    ): SearchResponse

    @GET(Constants.WORKS_ENDPOINT)
    suspend fun getBookDetailByWorkKey(
        @Path(Constants.PARAM_WORK_KEY) workId: String
    ): BookDetailDto

    @GET(Constants.EDITION_ENDPOINT)
    suspend fun getBookDetailByEditionId(
        @Path(Constants.PARAM_EDITION_KEY) editionId: String
    ): BookDetailDto

    @GET(Constants.SUBJECTS_ENDPOINT)
    suspend fun getBooksBySubject(
        @Path(Constants.PARAM_SUBJECT) subject: String,
        @Query(Constants.PARAM_LIMIT) limit : Int = 20,
        @Query(Constants.PARAM_OFFSET) offset: Int = 0
    ) : SubjectResponseDto
}

data class SearchResponse(
    val docs: List<BookDto>
)