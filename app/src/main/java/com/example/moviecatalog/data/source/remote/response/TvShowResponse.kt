package com.example.moviecatalog.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowResponse(

	@Json(name = "results")
	val results: List<TvShowResultItem>
)

@JsonClass(generateAdapter = true)
data class TvShowResultItem(

	@Json(name = "id")
	val id: Int,

	@Json(name = "name")
	val name: String,

	@Json(name = "first_air_date")
	val firstAirDate: String,

	@Json(name = "poster_path")
	val posterPath: String
)
