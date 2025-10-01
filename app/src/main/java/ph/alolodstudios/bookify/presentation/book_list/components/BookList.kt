package ph.alolodstudios.bookify.presentation.book_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookList(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(Dimens.LargePadding)) {
        items(books) { book ->
            BookListItem(
                book = book,
                onItemClick = { onBookClick(book) }
            )
        }
    }
}