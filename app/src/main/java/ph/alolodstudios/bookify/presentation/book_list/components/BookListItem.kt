package ph.alolodstudios.bookify.presentation.book_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ph.alolodstudios.bookify.domain.model.Book
import ph.alolodstudios.bookify.R
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookListItem(
    book: Book,
    onItemClick: (Book) -> Unit,
    maxLines: Int = 2,
    height: Dp = 120.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(book)}
            .padding(Dimens.MediumPadding)
            .height(height)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = book.title,
                alignment = Alignment.TopStart,
                placeholder = rememberVectorPainter(Icons.Default.Book) ,
                error = painterResource(R.drawable.no_cover_image_available),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(Dimens.LargePadding))
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.authors.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(R.string.no_authors),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.firstPublishYear?.toString() ?: stringResource(R.string.unknown_year),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}