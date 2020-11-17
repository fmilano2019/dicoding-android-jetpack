package com.example.moviecatalog.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailMovieResponse(

	@Json(name = "overview")
	val overview: String,

	@Json(name = "original_language")
	val originalLanguageCode: String,

	@Json(name = "spoken_languages")
	val spokenLanguages: List<MovieLanguagesItem>,

	@Json(name = "runtime")
	val duration: Int,

	@Json(name = "title")
	val title: String,

	@Json(name = "poster_path")
	val posterPath: String,

	@Json(name = "revenue")
	val revenue: Int,

	@Json(name = "release_date")
	val releaseDate: String,

	@Json(name = "genres")
	val genres: List<MovieGenresItem>,

	@Json(name = "vote_average")
	val userScore: Double,

	@Json(name = "release_dates")
	val ageRatings: MovieAgeRatings,

	@Json(name = "id")
	val id: Int,

	@Json(name = "budget")
	val budget: Int,

	@Json(name = "homepage")
	val homepage: String,

	@Json(name = "status")
	val status: String
)

@JsonClass(generateAdapter = true)
data class MovieResultsItem(

	@Json(name = "release_dates")
	val ageRatings: List<MovieReleaseDatesItem>,

	@Json(name = "iso_3166_1")
	val country: String
)

@JsonClass(generateAdapter = true)
data class MovieGenresItem(

	@Json(name = "name")
	val name: String,
)

@JsonClass(generateAdapter = true)
data class MovieAgeRatings(

	@Json(name = "results")
	val results: List<MovieResultsItem>
)

@JsonClass(generateAdapter = true)
data class MovieReleaseDatesItem(

	@Json(name = "certification")
	val certification: String
)

@JsonClass(generateAdapter = true)
data class MovieLanguagesItem(

	@Json(name = "name")
	val countryName: String,

	@Json(name = "iso_639_1")
	val countryCode: String
)
