package com.mynt.app.moviehub.network.model

import com.google.gson.annotations.SerializedName
import com.mynt.app.moviehub.model.Movie

data class MovieSearchResponse(
    @SerializedName("Search") val movieResponses: List<MovieResponse>,
)

data class MovieResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
)

fun MovieResponse.toMovie(): Movie = Movie(
    id = imdbId,
    title = title,
    type = type,
    publishYear = year,
    poster = poster
)
