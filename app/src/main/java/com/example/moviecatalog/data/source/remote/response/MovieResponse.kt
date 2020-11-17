package com.example.moviecatalog.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

	@Json(name = "results")
	val results: List<MovieResultItem>
)

@JsonClass(generateAdapter = true)
data class MovieResultItem(

	@Json(name = "id")
	val id: Int,

	@Json(name = "title")
	val title: String,

	@Json(name = "release_date")
	val releaseDate: String,

	@Json(name = "poster_path")
	val posterPath: String
)
