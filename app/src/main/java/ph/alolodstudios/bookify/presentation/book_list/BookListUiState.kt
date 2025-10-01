package ph.alolodstudios.bookify.presentation.book_list

import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.model.FilterType

data class BookListUiState(
    val query: String = "",
    val suggestions: List<String> = emptyList(),
    val filter: FilterType = FilterType.ALL,
    val bookList : UiState<List<Book>> = UiState.Empty
)