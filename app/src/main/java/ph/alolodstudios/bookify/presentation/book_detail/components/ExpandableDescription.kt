package ph.alolodstudios.bookify.presentation.book_detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import ph.alolodstudios.bookify.R
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun ExpandableDescription(
    modifier: Modifier = Modifier,
    description: String,
    minimizedMaxLines: Int = 3,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.SmallPadding)
            .animateContentSize()
            .clip(RoundedCornerShape(Dimens.CornerMedium))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = if (isSystemInDarkTheme())
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f)
                    else
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
            ) {
                expanded = !expanded
            }
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = description,
            modifier = modifier.padding(Dimens.SmallPadding),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = if (expanded) stringResource(R.string.show_less) else stringResource(R.string.read_more),
            modifier = modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}