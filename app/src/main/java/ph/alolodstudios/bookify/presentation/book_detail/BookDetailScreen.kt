package ph.alolodstudios.bookify.presentation.book_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import ph.alolodstudios.bookify.R
import ph.alolodstudios.bookify.common.UiState
import ph.alolodstudios.bookify.presentation.book_detail.components.BookCover
import ph.alolodstudios.bookify.presentation.book_detail.components.BookDetailTitle
import ph.alolodstudios.bookify.presentation.book_detail.components.BookInfoList
import ph.alolodstudios.bookify.presentation.book_detail.components.BookMetadataItem
import ph.alolodstudios.bookify.presentation.book_detail.components.ExpandableDescription
import ph.alolodstudios.bookify.presentation.book_detail.components.Subject
import ph.alolodstudios.bookify.presentation.components.ErrorText
import ph.alolodstudios.bookify.presentation.components.Loading
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens
import kotlin.collections.buildList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel : BookDetailViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()
    val bookState = screenState.book
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            ),
        topBar = {
            CenterAlignedTopAppBar(
                title = { if (bookState is UiState.Success)  BookDetailTitle(title = bookState.data.title)  else { Text(stringResource( R.string.book_detail_title)) } },
                navigationIcon = { Icon(Icons.Default.ArrowBackIosNew, contentDescription = stringResource(R.string.back_to_book_list), modifier = Modifier.clickable{navController.navigateUp()}) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            when (bookState) {
                is UiState.Loading -> Loading()
                is UiState.Success -> {
                    val iaId = bookState.data.ocaid ?: bookState.data.ia.firstOrNull()
                    if(!iaId.isNullOrBlank()) {
                        Button(
                            onClick = {
                                val edition = screenState.edition
                                if (edition is UiState.Success) {
                                    viewModel.downloadBook(
                                        context = context,
                                        book = edition.data,
                                        title = edition.data.title
                                    )
                                }
                            },
                            modifier = Modifier.padding(Dimens.MediumPadding)
                        ) {
                            Text(stringResource(R.string.download_pdf))
                        }
                    } else {
                        Text(stringResource(R.string.book_cannot_be_download))
                    }

                    BookCover(coverUrl = bookState.data.coverUrl, bookTitle = bookState.data.title)
                    when(val edition = screenState.edition) {
                        is UiState.Success -> {
                            bookState.let { detail ->
                                val metadataItems = remember(detail, edition) {
                                    buildList {
                                        detail.data.firstPublishYear.takeIf { it.isNotBlank() }?.also {
                                            add(BookMetadataItem(context.getString(R.string.first_publish_year_label), it))
                                        }
                                        edition.data.isbn10.forEach { isbn ->
                                            add(BookMetadataItem(context.getString(R.string.isbn_10_label), isbn))
                                        }
                                        edition.data.isbn13.forEach { isbn ->
                                            add(BookMetadataItem(context.getString(R.string.isbn_13_label), isbn))
                                        }
                                    }
                                }
                                BookInfoList(items = metadataItems)
                            }
                        }
                        is UiState.Error -> ErrorText(stringResource(R.string.no_details_available))
                        else -> {}
                    }
                    ExpandableDescription(description = bookState.data.description)
                    Spacer(modifier = modifier.height(Dimens.LargePadding))
                    Subject(subjects = bookState.data.subjects)
                }
                is UiState.Error -> ErrorText(message = bookState.message)
                is UiState.Empty ->  Text(stringResource(R.string.empty), textAlign = TextAlign.Center)
                else -> {}
            }
        }
    }
}