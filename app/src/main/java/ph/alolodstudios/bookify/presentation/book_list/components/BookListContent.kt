package ph.alolodstudios.bookify.presentation.book_list.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.presentation.components.ErrorText
import ph.alolodstudios.bookify.presentation.components.Loading
import ph.alolodstudios.bookify.presentation.components.NoResult
import ph.alolodstudios.bookify.presentation.subject_browse.SubjectBrowse

@Composable
fun BookListContent(
    uiState: UiState<List<Book>>,
    onBookClick: (Book) -> Unit,
    navController: NavController
) {
    when (uiState) {
        is UiState.Loading -> Loading()
        is UiState.Error -> ErrorText(uiState.message)
        is UiState.Success -> BookList(books = uiState.data, onBookClick = onBookClick)
        is UiState.Empty -> SubjectBrowse(navController = navController)
        is UiState.NoResult -> NoResult()
    }
}