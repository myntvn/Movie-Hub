package com.mynt.app.moviehub.network.data.movie

import com.mynt.app.moviehub.model.Movie

interface MovieRemoteDataSource {
    suspend fun getMovies(query: String): Result<List<Movie>>
}
