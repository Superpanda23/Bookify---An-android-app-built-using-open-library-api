package ph.alolodstudios.bookify.domain.use_case.get_book

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ph.alolodstudios.bookify.common.Constants.ErrorMessages
import ph.alolodstudios.bookify.common.Resource
import ph.alolodstudios.bookify.domain.model.BookDetail
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(workKey: String): Flow<Resource<BookDetail>> = flow {
        try {
            emit(Resource.Loading())
            val books = repository.getBookDetailByWorkKey(workKey = workKey)
            emit(Resource.Success(books))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessages.NO_INTERNET))
        }
    }
}