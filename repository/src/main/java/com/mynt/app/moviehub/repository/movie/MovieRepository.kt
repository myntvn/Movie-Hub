package com.mynt.app.moviehub.repository.movie

import com.mynt.app.moviehub.model.Movie

interface MovieRepository {
    suspend fun getMovies(query: String): Result<List<Movie>>
}
