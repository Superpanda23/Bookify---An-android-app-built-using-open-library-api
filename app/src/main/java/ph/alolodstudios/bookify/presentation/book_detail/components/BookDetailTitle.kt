package ph.alolodstudios.bookify.presentation.book_detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BookDetailTitle(
    title: String,
    maxLines: Int = 2
) {
    Text(
        text = title,
        maxLines = maxLines,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    )
}