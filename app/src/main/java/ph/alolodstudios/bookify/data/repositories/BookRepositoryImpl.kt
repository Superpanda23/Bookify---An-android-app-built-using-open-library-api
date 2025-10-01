package ph.alolodstudios.bookify.data.repositories

import ph.alolodstudios.bookify.data.remote.OpenLibraryApi
import ph.alolodstudios.bookify.data.remote.dto.BookDto
import ph.alolodstudios.bookify.data.remote.dto.toBook
import ph.alolodstudios.bookify.data.remote.dto.toBookDetail
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.model.BookDetail
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: OpenLibraryApi
): BookRepository {

    override suspend fun searchBooks(query: String): List<BookDto> {
        return api.searchBooks(query).docs
    }

    override suspend fun getBooksBySubject(subject: String): List<Book> {
        return api.getBooksBySubject(subject = subject).works.map { it.toBook() }
    }

    override suspend fun getBookDetailByWorkKey(workKey: String): BookDetail {
        return api.getBookDetailByWorkKey(workKey).toBookDetail()
    }
    override suspend fun getBookDetailByEditionId(editionId: String): BookDetail {
        return api.getBookDetailByEditionId(editionId).toBookDetail()
    }
}