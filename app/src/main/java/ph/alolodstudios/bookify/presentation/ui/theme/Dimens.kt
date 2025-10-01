package ph.alolodstudios.bookify.presentation.ui.theme

import androidx.compose.ui.unit.dp

object Dimens {
    val ExtraSmallPadding = 4.dp
    val SmallPadding = 8.dp
    val MediumPadding = 12.dp  // ✅ often better than 16.dp for balanced layouts
    val LargePadding = 16.dp   // ✅ standard gutter
    val ExtraLargePadding = 24.dp
    val HugePadding = 32.dp    // ✅ only for full-bleed or big screen sections

    val IconSmall = 16.dp
    val IconMedium = 24.dp
    val IconLarge = 32.dp
    val IconExtraLarge = 48.dp

    val ButtonSmallHeight = 40.dp
    val ButtonMediumHeight = 48.dp // ✅ default
    val ButtonLargeHeight = 56.dp

    val CornerExtraSmall = 4.dp
    val CornerSmall = 8.dp
    val CornerMedium = 12.dp
    val CornerLarge = 16.dp
    val CornerExtraLarge = 28.dp
    val CornerFull = 100.dp // ✅ for circular images/avatars

    val ElevationLevel1 = 1.dp
    val ElevationLevel2 = 3.dp
    val ElevationLevel3 = 6.dp
    val ElevationLevel4 = 8.dp
    val ElevationLevel5 = 12.dp

    val BookCoverWidth = 180.dp  // good for phones
    val BookCoverHeight = 260.dp // ~1.4 ratio
}