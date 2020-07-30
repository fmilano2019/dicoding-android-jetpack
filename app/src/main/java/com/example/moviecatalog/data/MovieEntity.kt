package com.example.moviecatalog.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var id: Int,
    var image: Int,
    var title: String,
    var year: String,
    var ageRating: String,
    var releaseDate: String,
    var tag: String,
    var duration: String,
    var userScore: String,
    var overview: String,
    var status: String,
    var originalLanguage: String,
    var budget: String,
    var revenue: String
) : Parcelable