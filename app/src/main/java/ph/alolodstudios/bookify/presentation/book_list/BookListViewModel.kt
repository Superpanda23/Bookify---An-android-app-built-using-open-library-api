package ph.alolodstudios.bookify.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.common.extensions.toUiState
import ph.alolodstudios.bookify.domain.use_case.get_books.GetBooksUseCase
import ph.alolodstudios.bookify.domain.model.FilterType
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(BookListUiState())
    val uiState = _uiState.asStateFlow()

    fun updateQuery(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun updateFilter(newFilter: FilterType) {
        _uiState.update { it.copy(filter = newFilter) }
        searchBooks(_uiState.value.query)
    }

    fun addSearchQueryToSuggestions(query: String) {
        _uiState.update { currentState ->
            val newSuggestion = query.trim()
            if(newSuggestion.isNotBlank() && !currentState.suggestions.contains(newSuggestion)) {
                currentState.copy(suggestions = listOf(newSuggestion) + currentState.suggestions)
            } else {
                currentState
            }
        }
    }

    fun removeSuggestion(suggestionToRemove: String) {
        _uiState.update { currentState ->
            currentState.copy(suggestions = currentState.suggestions.filter { it != suggestionToRemove })
        }
    }

    fun searchBooks(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(bookList = UiState.Empty) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(query = query) }
            addSearchQueryToSuggestions(query)
            getBooksUseCase(query, _uiState.value.filter).collectLatest { result ->
                _uiState.update { it.copy(bookList = result.toUiState() ) }
            }
        }
    }
}