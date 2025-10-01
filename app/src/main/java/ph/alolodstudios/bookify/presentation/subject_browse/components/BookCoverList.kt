package ph.alolodstudios.bookify.presentation.subject_browse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookCoverList(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding)
    ) {
        itemsIndexed(books) {index, book ->
            BookCoverItem(
                book = book,
                onItemClick = {  onBookClick(book) }
            )
        }
    }
}