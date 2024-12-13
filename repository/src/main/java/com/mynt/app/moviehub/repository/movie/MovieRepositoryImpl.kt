package com.mynt.app.moviehub.repository.movie

import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.network.data.movie.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(query: String): Result<List<Movie>> {
        return movieRemoteDataSource.getMovies(query)
    }
}
