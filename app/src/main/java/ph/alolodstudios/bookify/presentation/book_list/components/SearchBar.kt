package ph.alolodstudios.bookify.presentation.book_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ph.alolodstudios.bookify.R
import ph.alolodstudios.bookify.domain.model.FilterType
import ph.alolodstudios.bookify.presentation.ui.theme.Dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    suggestions: List<String>,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onFilter: (FilterType) -> Unit,
    onRemoveSuggestion: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
) {
    var filterExpanded by remember { mutableStateOf(false) }

    SearchBar(
        modifier = modifier.fillMaxWidth().then(
            if (!expanded) Modifier.padding(horizontal = Dimens.MediumPadding)
            else Modifier
        ),
        expanded = expanded,
        onExpandedChange = {
            onExpandedChange(it)
        },
        tonalElevation = Dimens.ElevationLevel3,
        shadowElevation = Dimens.ElevationLevel3,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    onQueryChange(it)
                },
                onSearch = {
                    onSearch(it)
                    onExpandedChange(false)
                },
                expanded = expanded,
                onExpandedChange = {
                    onExpandedChange(it)
                },
                placeholder = { Text(stringResource(R.string.search_books)) },
                leadingIcon = {
                    if (expanded || query.isNotBlank()) {
                        IconButton(
                            onClick = {
                                onExpandedChange(false)
                                onQueryChange("")
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
                    }
                },
                trailingIcon = {
                    Row {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { onQueryChange("") }) {
                                Icon(Icons.Default.Close, contentDescription = stringResource(R.string.clear))
                            }
                        }
                        IconButton(onClick = { filterExpanded = true }) {
                            Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = stringResource(R.string.filter))
                        }
                        DropdownMenu(
                            expanded = filterExpanded,
                            onDismissRequest = { filterExpanded = false }
                        ) {
                            FilterType.entries.forEach { filter ->
                                DropdownMenuItem(
                                    text = { Text(filter.name) },
                                    onClick = {
                                        filterExpanded = false
                                        onFilter(filter)
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(horizontal = Dimens.MediumPadding), horizontalAlignment = Alignment.Start
        ) {
            suggestions.forEachIndexed { index, suggestion ->
                key(suggestion) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSearch(suggestion)
                                onQueryChange(suggestion)
                                onExpandedChange(false)

                            }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(suggestion, style = MaterialTheme.typography.bodyMedium)
                        IconButton(
                            onClick = { onRemoveSuggestion(suggestion) }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Remove suggestion")
                        }
                    }
                }
            }
        }
    }
}