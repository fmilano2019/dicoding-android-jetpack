package com.example.moviecatalog.utils

import com.example.moviecatalog.BuildConfig
import com.example.moviecatalog.data.source.remote.response.*
import java.text.DecimalFormat
import kotlin.math.roundToInt

object MappingUtils {

    fun dateToYear(string: String): String {
        val dates = string.split("-").map { it.trim() }
        return dates.first()
    }

    fun findMovieAgeRating(ageRatings: MovieAgeRatings): String {
        var ageRating = ""
        ageRatings.results.find { findAgeRatingResult ->
            BuildConfig.AGE_RATING_COUNTRY == findAgeRatingResult.country
        }?.let { ageRatingResult ->
            ageRating = ageRatingResult.ageRatings.last().certification
        }
        if (ageRating.isEmpty()) {
            ageRatings.results.forEach { findAgeRatingResult ->
                val certification = findAgeRatingResult.ageRatings.last().certification
                if (certification.isNotEmpty())
                    ageRating = certification
            }
        }
        return ageRating
    }

    fun findTvShowAgeRating(ageRatings: TvShowAgeRatings): String {
        var ageRating = ""
        ageRatings.results.find { findAgeRatingResult ->
            BuildConfig.AGE_RATING_COUNTRY == findAgeRatingResult.country
        }?.let { ageRatingResult ->
            ageRating = ageRatingResult.rating
        }
        if (ageRating.isEmpty()) {
            ageRatings.results.forEach { findAgeRatingResult ->
                val certification = findAgeRatingResult.rating
                if (certification.isNotEmpty())
                    ageRating = certification
            }
        }
        return ageRating
    }

    fun findOriginalLanguage(
        originalLanguageCode: String,
        spokenLanguages: List<MovieLanguagesItem>
    ): String {
        var language = ""
        spokenLanguages.find { findLanguage ->
            originalLanguageCode == findLanguage.countryCode
        }?.let { languageResult ->
            language = languageResult.countryName
        }
        return language
    }

    fun minutesToHoursMinutes(minutes: Int): String {
        val decimalMinutes = minutes.toDouble()
        val decimalNewHours = decimalMinutes / 60
        var intNewHours = decimalNewHours.toInt()
        val doubleNewMinutes = (decimalNewHours - intNewHours) * 60
        var intNewMinutes = doubleNewMinutes.roundToInt()
        if (intNewMinutes == 60) {
            intNewHours += 1
            intNewMinutes = 0
        }

        var duration = ""
        if (intNewHours != 0) {
            duration = "${intNewHours}h"
        }
        if (intNewMinutes != 0) {
            duration = "${intNewMinutes}m"
        }
        if (intNewHours != 0 && intNewMinutes != 0) {
            duration = "${intNewHours}h ${intNewMinutes}m"
        }
        return duration
    }

    fun currencyFormat(number: Int): String {
        var currency = "-"
        if (number != 0) {
            val formatter = DecimalFormat("$###,###,###.00")
            currency = formatter.format(number.toDouble())
        }
        return currency
    }

    fun genreMovieListToStringList(genreItemList: List<MovieGenresItem>): ArrayList<String> {
        val genreStringList = arrayListOf<String>()
        genreItemList.forEach { genre -> genreStringList.add(genre.name) }
        return genreStringList
    }

    fun genreTvShowListToStringList(genreItemList: List<TvShowGenresItem>): ArrayList<String> {
        val genreStringList = arrayListOf<String>()
        genreItemList.forEach { genre -> genreStringList.add(genre.name) }
        return genreStringList
    }

    fun networkTvShowListToString(networkItemList: List<TvShowNetworksItem>): String {
        val networkStringList = arrayListOf<String>()
        networkItemList.forEach { network -> networkStringList.add(network.name) }

        var networks = ""
        networkStringList.forEachIndexed { index, network ->
            networks += network
            if (index != networkStringList.lastIndex) {
                networks = "$networks, "
            }
        }
        return networks
    }

    fun userScoreDoubleToPercent(userScore: Double): String = "${(userScore * 10).toInt()}%"
}