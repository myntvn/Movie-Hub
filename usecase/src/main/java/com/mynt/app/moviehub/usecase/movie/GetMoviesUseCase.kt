package com.mynt.app.moviehub.usecase.movie

import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(query: String): Result<List<Movie>> {
        return movieRepository.getMovies(query)
    }
}
