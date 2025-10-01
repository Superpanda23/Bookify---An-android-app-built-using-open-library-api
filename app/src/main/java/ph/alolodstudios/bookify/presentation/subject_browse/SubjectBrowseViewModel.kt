package ph.alolodstudios.bookify.presentation.subject_browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ph.alolodstudios.bookify.common.extensions.toUiState
import ph.alolodstudios.bookify.domain.model.Subjects
import ph.alolodstudios.bookify.domain.use_case.get_subjects.GetSubjectsUseCase
import javax.inject.Inject

@HiltViewModel
class SubjectBrowseViewModel @Inject constructor(
    private val subjectsUseCase: GetSubjectsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(SubjectBrowseUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadAllSubjects()
    }

    private fun loadAllSubjects() {
        Subjects.entries.forEach { subject ->
            getBooksBySubject(subject)
        }
    }

    private fun getBooksBySubject(subject: Subjects) {
        subjectsUseCase(subject.name.lowercase()).onEach { result ->
            _uiState.update { current ->
                current.copy(
                    subjects = current.subjects + (subject to result.toUiState())
                )
            }
        }.launchIn(viewModelScope)
    }
}