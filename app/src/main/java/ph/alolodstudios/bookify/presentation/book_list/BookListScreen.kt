package ph.alolodstudios.bookify.presentation.book_list

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.FlowPreview
import ph.alolodstudios.bookify.presentation.Screen
import ph.alolodstudios.bookify.presentation.book_list.components.BookListContent
import ph.alolodstudios.bookify.presentation.book_list.components.BookSearchBar
import ph.alolodstudios.bookify.presentation.components.AppTitle
import ph.alolodstudios.bookify.presentation.subject_browse.SubjectBrowse


@SuppressLint("ContextCastToActivity")
@OptIn(FlowPreview::class, ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    viewModel: BookListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val activity = (LocalContext.current as? Activity)
    var isSearchActive by remember { mutableStateOf(false) }


    BackHandler(enabled = true) {
        when {
            state.query.isNotBlank() -> { viewModel.updateQuery("") }
            else -> activity?.finish()
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!isSearchActive) {
                TopAppBar(
                    title = { AppTitle() },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        },
    ) { paddingValues ->

        Column(
            modifier = modifier.padding(paddingValues), horizontalAlignment = (Alignment.CenterHorizontally)
        ) {
            Box(
               modifier = modifier.fillMaxWidth()
            ) {
                BookSearchBar(
                    query = state.query,
                    suggestions = state.suggestions,
                    onQueryChange = { viewModel.updateQuery(it) },
                    onSearch = { queryFromEvent -> viewModel.searchBooks(queryFromEvent) },
                    expanded = isSearchActive,
                    onExpandedChange = { isSearchActive = it},
                    onFilter = { filterType -> viewModel.updateFilter(filterType) },
                    onRemoveSuggestion = {suggestion -> viewModel.removeSuggestion(suggestion)}
                )
            }
            if(state.query.isBlank()) {
                SubjectBrowse(navController = navController)
            } else {
                BookListContent(
                    uiState = state.bookList,
                    onBookClick = { book ->
                        val coverEditionKey = book.coverEditionKey ?: ""
                        navController.navigate(
                            Screen.BookDetailScreen.route +
                                    "/${book.workKey}/$coverEditionKey"
                        )
                    },
                    navController = navController
                )
            }
        }
    }
}




