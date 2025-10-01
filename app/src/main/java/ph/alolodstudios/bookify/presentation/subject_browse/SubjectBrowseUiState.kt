package ph.alolodstudios.bookify.presentation.subject_browse

import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.domain.model.Subjects

data class SubjectBrowseUiState(
    val subjects: Map<Subjects, UiState<List<Book>>> = emptyMap(),
)
