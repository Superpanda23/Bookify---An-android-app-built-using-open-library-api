package ph.alolodstudios.bookify.presentation.book_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookInfoList(
    modifier: Modifier = Modifier,
    items: List<BookMetadataItem>
) {
    Column(modifier = modifier.padding(Dimens.MediumPadding)) {
        items.forEach { item ->
            MetaInfoRow(label = item.label, value = item.value)
        }
    }
}

@Composable
private fun MetaInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$label ")
            }
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                append(value)
            }
        },
        modifier = modifier.padding(vertical = 2.dp),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

data class BookMetadataItem(
    val label: String,
    val value: String
)