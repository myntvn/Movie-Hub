package com.mynt.app.moviehub.ui.main.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mynt.app.moviehub.ui.common.LoadingScreen

const val moviesScreenRoute = "moviesScreen"
fun NavGraphBuilder.moviesScreen() {
    composable(moviesScreenRoute) {
        val viewModel = hiltViewModel<MoviesViewModel>()
        MoviesScreen(viewModel)
    }
}

@Composable
private fun MoviesScreen(
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier,
) {
    val screenState by viewModel.moviesScreenState.collectAsStateWithLifecycle()

    val keyboardManager = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.query,
            onValueChange = { viewModel.updateQuery(it) },
            placeholder = { Text(text = "Search movies") },
            trailingIcon = {
                Icon(Icons.Filled.Search, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardManager?.hide()
                viewModel.search()
            }),
            shape = CircleShape,
            singleLine = true
        )

        when (screenState) {
            MoviesScreenState.Searching -> LoadingScreen()

            is MoviesScreenState.Ready ->{
                val movies = (screenState as MoviesScreenState.Ready).movies
                if (movies.isEmpty()) EmptyPlaceHolder()
                else MovieList(movies)
            }

            MoviesScreenState.Error -> SearchErrorPlaceHolder()
        }
    }
}

@Composable
private fun EmptyPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No results",
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
private fun SearchErrorPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Search error, please try again later")
    }
}
