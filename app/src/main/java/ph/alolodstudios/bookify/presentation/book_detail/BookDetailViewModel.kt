package ph.alolodstudios.bookify.presentation.book_detail

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ph.alolodstudios.bookify.common.extensions.getEditionId
import ph.alolodstudios.bookify.common.extensions.getWorkKey
import ph.alolodstudios.bookify.common.extensions.toUiState
import ph.alolodstudios.bookify.domain.use_case.get_book.GetBookUseCase
import ph.alolodstudios.bookify.domain.use_case.get_edition.GetEditionUseCase
import javax.inject.Inject
import androidx.core.net.toUri
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import ph.alolodstudios.bookify.domain.model.BookDetail
import java.net.URL

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val getEditionUseCase: GetEditionUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _screenState = MutableStateFlow(BookDetailUiState())
    val screenState = _screenState.asStateFlow()

    init {
        val workKey = savedStateHandle.getWorkKey()
        val editionId = savedStateHandle.getEditionId()

        if (!workKey.isNullOrBlank() || !editionId.isNullOrBlank()) {
            loadBookAndEdition(workKey,editionId)
        }
    }

    private fun collectBook(workKey: String) = getBookUseCase(workKey)

    private fun collectEdition(editionId: String) = getEditionUseCase(editionId)

    private fun loadBookAndEdition(workKey: String?, editionId: String?) {
        val bookFlow = workKey?.let { collectBook(it) } ?: flowOf(null)
        val editionFlow = editionId?.let { collectEdition(it) } ?: flowOf(null)

        viewModelScope.launch {
            combine(bookFlow, editionFlow) { bookResult, editionResult ->
                BookDetailUiState(
                    book = bookResult?.toUiState(),
                    edition = editionResult?.toUiState()
                )
            }.collectLatest { newState ->
                _screenState.update { newState }
            }
        }
    }
    fun downloadBook(context: Context, book: BookDetail, title: String) {
        val iaId = book.ia.firstOrNull() ?: book.ocaid

        if (iaId.isNullOrBlank()) {
            Log.e("Download", "No IA ID found for ${book.title}")
            return
        }

        viewModelScope.launch {
            val iaId = book.ocaid ?: book.ia.firstOrNull()
            if (iaId.isNullOrBlank()) {
                Log.e("Download", "No IA ID found for ${book.title}")
                return@launch
            }

            val downloadUrl = getDownloadUrl(iaId)
            if (downloadUrl == null) {
                Log.e("Download", "No downloadable PDF for $iaId")
                return@launch
            }

            val request = DownloadManager.Request(downloadUrl.toUri())
                .setTitle(title)
                .setDescription("Downloading $title")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$title.pdf")

            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
        }

    }

}

suspend fun getDownloadUrl(iaId: String): String? {
    val metadata = fetchIaMetadata(iaId) ?: return null
    val pdfFile = metadata.files.firstOrNull {
        it.name.endsWith(".pdf", ignoreCase = true)
    }
    return pdfFile?.let { "https://archive.org/download/$iaId/${it.name}" }
}


suspend fun fetchIaMetadata(iaId: String): IaMetadata? {
    val url = "https://archive.org/metadata/$iaId"
    return try {
        val response = withContext(Dispatchers.IO) {
            URL(url).openStream().bufferedReader().use { it.readText() }
        }
        Gson().fromJson(response, IaMetadata::class.java)
    } catch (e: Exception) {
        Log.e("Download", "Metadata fetch failed", e)
        null
    }
}

data class IaFile(
    val name: String,
    val format: String?
)

data class IaMetadata(
    val files: List<IaFile>
)
