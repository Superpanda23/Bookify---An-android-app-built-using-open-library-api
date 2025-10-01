package ph.alolodstudios.bookify.domain.repositories

import ph.alolodstudios.bookify.data.remote.dto.BookDto
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.model.BookDetail

interface BookRepository {
    suspend fun searchBooks(query: String) : List<BookDto>
    suspend fun getBooksBySubject(subject: String) : List<Book>
    suspend fun getBookDetailByWorkKey(workKey: String) : BookDetail
    suspend fun getBookDetailByEditionId(editionId: String) : BookDetail
}