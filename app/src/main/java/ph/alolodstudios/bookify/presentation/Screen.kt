package ph.alolodstudios.bookify.presentation

sealed class Screen(val route: String) {
    object BookListScreen: Screen("book_list_screen")
    object BookDetailScreen: Screen("book_detail_screen")
    object SubjectBrowseScreen: Screen("subject_browse_screen")
}