package ph.alolodstudios.bookify.presentation.subject_browse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.presentation.Screen
import ph.alolodstudios.bookify.presentation.subject_browse.components.BookCoverListContent
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun SubjectBrowse(
    subjectBrowseViewModel: SubjectBrowseViewModel = hiltViewModel(),
    navController: NavController
) {
    val screenState by subjectBrowseViewModel.uiState.collectAsState()

    SubjectBrowseScreen(
        uiState = screenState,
        onBookClick = { book->
            val coverEditionKey = book.coverEditionKey ?: ""
            navController.navigate(Screen.BookDetailScreen.route + "/${book.workKey}/$coverEditionKey")
        }
    )
}
@Composable
fun SubjectBrowseScreen(
    uiState: SubjectBrowseUiState,
    onBookClick: (Book) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.LargePadding)
    ) {
        uiState.subjects.forEach { (subject, state) ->
            item {
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = Dimens.MediumPadding)
                )
                BookCoverListContent(uiState= state,onBookClick = onBookClick,subject = subject)
            }
        }
    }
}

