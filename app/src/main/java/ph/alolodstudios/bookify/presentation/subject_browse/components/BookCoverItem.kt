package ph.alolodstudios.bookify.presentation.subject_browse.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ph.alolodstudios.bookify.common.Constants
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookCoverItem(
    book: Book,
    onItemClick: (Book) -> Unit,
    width: Dp = 100.dp,
    height: Dp = 150.dp
) {
    Column(
        modifier = Modifier
            .width(width)
        .clickable { onItemClick(book) }
    ) {
        AsyncImage(
            model = "${Constants.COVER_URL}${book.coverId}-L.jpg",
            contentDescription = book.title,
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .clip(RoundedCornerShape(Dimens.MediumPadding)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = book.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = Dimens.SmallPadding)
        )
    }
}