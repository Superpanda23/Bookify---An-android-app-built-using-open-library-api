package ph.alolodstudios.bookify.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkBookColors = darkColorScheme(
    primary = Color(0xFFD7CCC8), // Muted parchment (highlight color)
    onPrimary = Color(0xFF2E2B26),

    secondary = Color(0xFFBCAAA4),
    onSecondary = Color(0xFF2E2B26),

    background = Color(0xFF1C1B19), // 🔹 Darker background (near black-brown)
    onBackground = Color(0xFFEDE0D4), // Cream text

    surface = Color(0xFF2E2B26), // 🔹 Slightly lighter than background (used for cards/SearchBar)
    onSurface = Color(0xFFEDE0D4),

    error = Color(0xFFEF9A9A),
    onError = Color(0xFF2E2B26),

    tertiaryContainer = Color(0xFF4A3B35),
    onTertiaryContainer = Color(0xFFEDE0D4),
)

private val LightBookColors = lightColorScheme(
    primary = Color(0xFF6B4226), // Warm brown (highlight)
    onPrimary = Color.White,

    secondary = Color(0xFF8D6E63),
    onSecondary = Color.White,

    background = Color(0xFFF0E6D6), // 🔹 Darker cream background (less white)
    onBackground = Color(0xFF3E2723),

    surface = Color(0xFFFFF8E7), // 🔹 Lighter "parchment" for cards/SearchBar
    onSurface = Color(0xFF3E2723),

    error = Color(0xFFB00020),
    onError = Color.White,

    tertiaryContainer = Color(0xFFFFF0D9),
    onTertiaryContainer = Color(0xFF3E2723),
)

@Composable
fun Bookify_Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 🚫 Disable dynamic color completely, always use our custom scheme
    val colors = if (darkTheme) DarkBookColors else LightBookColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}