package ph.alolodstudios.bookify.presentation.subject_browse.components

import androidx.compose.runtime.Composable
import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.model.Subjects
import ph.alolodstudios.bookify.presentation.components.EmptyState
import ph.alolodstudios.bookify.presentation.components.ErrorText
import ph.alolodstudios.bookify.presentation.components.Loading
import ph.alolodstudios.bookify.presentation.components.NoResult

@Composable
fun BookCoverListContent(
    uiState: UiState<List<Book>>,
    onBookClick: (Book) -> Unit,
    subject: Subjects,
) {
    when (uiState) {
        is UiState.Loading -> { Loading() }
        is UiState.Success -> { BookCoverList(uiState.data,onBookClick = onBookClick) }
        is UiState.Error -> { ErrorText(message = "Failed to load ${subject.name.lowercase()}") }
        is UiState.Empty -> { EmptyState(text = "No ${subject.name.lowercase()} books found") }
        is UiState.NoResult -> NoResult()
    }
}