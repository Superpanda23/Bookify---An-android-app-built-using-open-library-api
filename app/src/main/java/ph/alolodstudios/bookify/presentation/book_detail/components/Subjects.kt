package ph.alolodstudios.bookify.presentation.book_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens


@Composable
fun Subject(
    subjects: List<String>,
    borderWidth: Dp = 1.dp
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = Dimens.MediumPadding),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding)
    ) {
        items(subjects.size) { index ->
            Chip(text = subjects[index], borderWidth = borderWidth)
        }
    }
}

@Composable
private fun Chip(
    text: String,
    borderWidth: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .border(
                width = borderWidth,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50)
            )
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            )
            .padding(horizontal = Dimens.MediumPadding, vertical = Dimens.SmallPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}