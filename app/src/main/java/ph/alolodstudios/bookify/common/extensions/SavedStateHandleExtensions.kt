package ph.alolodstudios.bookify.common.extensions

import androidx.lifecycle.SavedStateHandle
import ph.alolodstudios.bookify.common.Constants

fun SavedStateHandle.getWorkKey(): String? = this[Constants.PARAM_WORK_KEY]
fun SavedStateHandle.getEditionId(): String? = this[Constants.PARAM_EDITION_KEY]