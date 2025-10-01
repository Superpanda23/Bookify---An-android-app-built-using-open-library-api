package ph.alolodstudios.bookify.presentation.book_detail

import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.domain.model.BookDetail

data class BookDetailUiState(
    val book: UiState<BookDetail>? = UiState.Empty,
    val edition: UiState<BookDetail>? = UiState.Empty,
)

