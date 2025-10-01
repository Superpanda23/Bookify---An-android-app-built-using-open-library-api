package ph.alolodstudios.bookify.presentation.book_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import ph.alolodstudios.bookify.R
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens

@Composable
fun BookCover(
    modifier: Modifier = Modifier,
    bookTitle: String,
    coverUrl: List<String>,
    width: Dp = Dimens.BookCoverWidth,
    height: Dp = Dimens.BookCoverHeight
) {
    val pagerState = rememberPagerState(pageCount = { coverUrl.size })

    if (coverUrl.isNotEmpty()) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = coverUrl[page],
                    contentDescription = "$bookTitle cover $page",
                    modifier = modifier
                        .width(width)
                        .height(height)
                        .padding(Dimens.MediumPadding)
                        .clip(RoundedCornerShape(Dimens.CornerMedium)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.no_cover_image_available),
                    error = painterResource(R.drawable.no_cover_image_available)
                )
            }
        }
    }
}