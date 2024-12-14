package com.mynt.app.moviehub.ui.main.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.ui.theme.MovieHubTheme

@Composable
fun MovieList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            key = { it.id },
            items = movies
        ) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Card {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = movie.poster,
                contentDescription = "poster",
                contentScale = ContentScale.Fit
            )

            Column {
                Text(movie.title)
                Text(movie.type)
                Text(movie.publishYear)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListPreview(modifier: Modifier = Modifier) {
    val movies = listOf(
        Movie(
            id = "001",
            title = "The Lord of the rings",
            type = "movie",
            publishYear = "2001",
            poster = "https://m.media-amazon.com/images/M/MV5BNzIxMDQ2YTctNDY4MC00ZTRhLTk4ODQtMTVlOWY4NTdiYmMwXkEyXkFqcGc@._V1_SX300.jpg"
        ),

        Movie(
            id = "002",
            title = "Robinhood",
            type = "movie",
            publishYear = "2023",
            poster = "https://m.media-amazon.com/images/M/MV5BODA1OTE2NjAtNjI4YS00NGJiLTkzNzYtMTE1MTVjOWE2NmZlXkEyXkFqcGdeQXVyNjkxMzE4NjM@._V1_SX300.jpg"
        )
    )

    MovieHubTheme {
        Scaffold { padding ->
            MovieList(movies, modifier = Modifier.padding(padding))
        }
    }
}