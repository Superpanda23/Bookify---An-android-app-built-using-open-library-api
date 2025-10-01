package ph.alolodstudios.bookify.domain.use_case.get_books

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ph.alolodstudios.bookify.common.Constants.ErrorMessages
import ph.alolodstudios.bookify.common.Resource
import ph.alolodstudios.bookify.common.extensions.normalize
import ph.alolodstudios.bookify.data.remote.dto.toBook
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import ph.alolodstudios.bookify.domain.model.FilterType
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
){
    operator fun invoke(query: String,filterType: FilterType): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val normalizedQuery = query
                .trim()
                .replace("\\s+".toRegex(), " ") // collapse multiple spaces
                .lowercase()

            val books = repository.searchBooks(query).map { it.toBook() }

            val filteredBooks = when (filterType) {
                FilterType.ALL -> books
                FilterType.TITLE -> books.filter { it.title.normalize().contains(normalizedQuery, ignoreCase = true) }
                FilterType.AUTHOR -> books.filter { book ->
                    book.authors.any { it.contains(normalizedQuery, ignoreCase = true) }
                }
                FilterType.TEXT -> books.filter {
                    it.title.contains(normalizedQuery, ignoreCase = true) ||
                            it.authors.any { author -> author.normalize().contains(normalizedQuery, ignoreCase = true) } //||
                           // it.subjects.any { subject -> subject.contains(normalizedQuery, ignoreCase = true) }
                }
                FilterType.SUBJECT -> books.filter { book ->
                    book.subjects.any { subject -> subject.contains(normalizedQuery, ignoreCase = true) }
                }
                //FilterType.LISTS -> books.filter { it.listNames.any { list -> list.contains(query, ignoreCase = true) } }
            }

            if (filteredBooks.isEmpty()) {
                emit(Resource.NoResult()) // new state
            } else {
                emit(Resource.Success(filteredBooks))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessages.NO_INTERNET))
        }
    }
}