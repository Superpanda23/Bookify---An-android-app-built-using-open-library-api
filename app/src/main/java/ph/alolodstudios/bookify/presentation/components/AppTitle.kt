package ph.alolodstudios.bookify.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ph.alolodstudios.bookify.R

@Composable
fun AppTitle() {
    val subtitle = "powered by OpenLibrary API"

    var textToShow by rememberSaveable { mutableStateOf("") }
    var hasAnimated by rememberSaveable { mutableStateOf(false) }

    val visible = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible.value = true
        if (!hasAnimated) {
            subtitle.forEachIndexed { index, _ ->
                textToShow = subtitle.substring(0, index + 1)
                kotlinx.coroutines.delay(50)
            }
            hasAnimated = true
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        AnimatedVisibility(
            visible = visible.value,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

        Text(
            text = textToShow,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        )
    }
}