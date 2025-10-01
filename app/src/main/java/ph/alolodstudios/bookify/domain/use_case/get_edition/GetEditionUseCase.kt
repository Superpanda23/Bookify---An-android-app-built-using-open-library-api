package ph.alolodstudios.bookify.domain.use_case.get_edition

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ph.alolodstudios.bookify.common.Constants.ErrorMessages
import ph.alolodstudios.bookify.common.Resource
import ph.alolodstudios.bookify.domain.model.BookDetail
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import retrofit2.HttpException
import java.io.IOException

class GetEditionUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(editionId: String): Flow<Resource<BookDetail>> = flow {
        try {
            emit(Resource.Loading())
            val book = repository.getBookDetailByEditionId(editionId)
            emit(Resource.Success(book))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessages.NO_INTERNET))
        }
    }
}