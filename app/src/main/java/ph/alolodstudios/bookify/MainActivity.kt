package ph.alolodstudios.bookify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import ph.alolodstudios.bookify.common.Constants
import ph.alolodstudios.bookify.presentation.Screen
import ph.alolodstudios.bookify.presentation.book_detail.BookDetailScreen
import ph.alolodstudios.bookify.presentation.book_list.BookListScreen
import ph.alolodstudios.bookify.presentation.subject_browse.SubjectBrowse
import ph.alolodstudios.bookify.presentation.ui.theme.Bookify_Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bookify_Theme {
                val navController = rememberNavController()
                NavHost(
                navController = navController,
                startDestination = Screen.BookListScreen.route
                ) {
                    composable(route = Screen.BookListScreen.route) {
                        BookListScreen(navController= navController)
                    }
                    composable(
                        route = Screen.BookDetailScreen.route + "/{workKey}/{editionId}",
                        arguments = listOf(
                            navArgument(Constants.PARAM_WORK_KEY) { type = NavType.StringType},
                            navArgument(Constants.PARAM_EDITION_KEY) { type = NavType.StringType; defaultValue = "" }
                        )
                    ) { backStackEntry ->
//                        val workId = backStackEntry.arguments?.getString("workKey") ?: ""
//                        val coverId = backStackEntry.arguments?.getInt("coverId")
                        BookDetailScreen(navController= navController)
                    }
                    composable(route = Screen.SubjectBrowseScreen.route + "/{workKey}") {
                        SubjectBrowse(navController = navController)
                    }
                }
            }
        }
    }
}



