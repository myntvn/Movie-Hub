package com.mynt.app.moviehub.ui.main.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mynt.app.moviehub.R
import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.ui.theme.MovieHubTheme

@Composable
fun MovieList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(350.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = movies,
            key = { movie -> movie.id }
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
    ElevatedCard(
        modifier = modifier.clickable {  }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = movie.poster,
                error = painterResource(R.drawable.error_placeholder),
                contentDescription = "poster",
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Publish year: ${movie.publishYear}",
                        style = MaterialTheme.typography.bodyMedium
                    )


                }
                Text(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    text = "Type: ${movie.type}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListPreview(
    @PreviewParameter(MoviePreviewParameterProvider::class) movies: List<Movie>,
) {
    MovieHubTheme {
        Scaffold { padding ->
            MovieList(
                movies, modifier = Modifier
                    .padding(padding)
                    .padding(8.dp)
            )
        }
    }
}
