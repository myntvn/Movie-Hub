package com.mynt.app.moviehub.ui.main.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.usecase.movie.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    var query by mutableStateOf("")
        private set

    private val _moviesScreenState =
        MutableStateFlow<MoviesScreenState>(MoviesScreenState.Ready(emptyList()))
    val moviesScreenState = _moviesScreenState.asStateFlow()

    fun updateQuery(query: String) {
        this.query = query
    }

    fun search() {
        if (_moviesScreenState.value == MoviesScreenState.Searching) return
        if (query.isBlank()) return

        viewModelScope.launch {
            _moviesScreenState.value = MoviesScreenState.Searching

            val result = getMoviesUseCase(query)

            _moviesScreenState.value = when {
                result.isSuccess -> {
                    val movies = result.getOrDefault(emptyList())
                    MoviesScreenState.Ready(movies)
                }

                else -> MoviesScreenState.Error
            }
        }
    }
}

sealed interface MoviesScreenState {
    data object Searching: MoviesScreenState
    data class Ready(val movies: List<Movie>): MoviesScreenState
    data object Error: MoviesScreenState
}
