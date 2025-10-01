package ph.alolodstudios.bookify.common.extensions

import ph.alolodstudios.bookify.common.Resource
import ph.alolodstudios.bookify.common.UiState

fun <T> Resource<T>.toUiState(): UiState<T> =
    when (this) {
        is Resource.Error -> UiState.Error(message ?: "Unknown error")
        is Resource.Loading -> UiState.Loading
        is Resource.Success -> data?.let { UiState.Success(it) } ?: UiState.NoResult
        is Resource.NoResult -> UiState.NoResult
    }