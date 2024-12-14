package com.mynt.app.moviehub.network.data.movie

import com.mynt.app.moviehub.model.Movie
import com.mynt.app.moviehub.network.api.OMDbApi
import com.mynt.app.moviehub.network.model.toMovie
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val omDbApi: OMDbApi,
) : MovieRemoteDataSource {

    override suspend fun getMovies(query: String): Result<List<Movie>> {
        return try {
            val movieResponses = omDbApi.getMovies(query).body()?.movieResponses

            val movies = movieResponses?.map { movieResponse ->
                movieResponse.toMovie()
            }

            movies?.let { Result.success(it) } ?: Result.failure(Exception())

        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
