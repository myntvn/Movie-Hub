package com.mynt.app.moviehub.network.api

import com.mynt.app.moviehub.network.BuildConfig
import com.mynt.app.moviehub.network.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {
    @GET("/")
    suspend fun getMovies(
        @Query("s") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): Response<MovieSearchResponse>
}
