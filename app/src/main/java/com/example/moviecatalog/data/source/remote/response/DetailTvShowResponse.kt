package com.example.moviecatalog.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailTvShowResponse(

	@Json(name = "first_air_date")
	val releaseDate: String,

	@Json(name = "overview")
	val overview: String,

	@Json(name = "original_language")
	val originalLanguage: String,

	@Json(name = "networks")
	val networks: List<TvShowNetworksItem>,

	@Json(name = "type")
	val type: String,

	@Json(name = "poster_path")
	val posterPath: String,

	@Json(name = "genres")
	val genres: List<TvShowGenresItem>,

	@Json(name = "vote_average")
	val userScore: Double,

	@Json(name = "name")
	val title: String,

	@Json(name = "episode_run_time")
	val episodeRunTime: List<Int>,

	@Json(name = "id")
	val id: Int,

	@Json(name = "content_ratings")
	val ageRatings: TvShowAgeRatings,

	@Json(name = "homepage")
	val homepage: String,

	@Json(name = "status")
	val status: String
)

@JsonClass(generateAdapter = true)
data class TvShowNetworksItem(

	@Json(name = "name")
	val name: String
)

@JsonClass(generateAdapter = true)
data class TvShowGenresItem(

	@Json(name = "name")
	val name: String,

	@Json(name = "id")
	val id: Int
)

@JsonClass(generateAdapter = true)
data class TvShowAgeRatings(

	@Json(name = "results")
	val results: List<TvShowAgeRating>
)

@JsonClass(generateAdapter = true)
data class TvShowAgeRating(

	@Json(name = "iso_3166_1")
	val country: String,

	@Json(name = "rating")
	val rating: String
)
