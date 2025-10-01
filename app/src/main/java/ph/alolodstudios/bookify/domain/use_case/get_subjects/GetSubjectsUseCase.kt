package ph.alolodstudios.bookify.domain.use_case.get_subjects

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ph.alolodstudios.bookify.common.Constants
import ph.alolodstudios.bookify.common.Resource
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import retrofit2.HttpException
import java.io.IOException

class GetSubjectsUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(subject: String): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val books = repository.getBooksBySubject(subject)
            emit(Resource.Success(books))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Constants.ErrorMessages.NO_INTERNET))
        }
    }
}