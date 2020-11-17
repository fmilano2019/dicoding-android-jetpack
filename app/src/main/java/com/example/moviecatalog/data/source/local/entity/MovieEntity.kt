package com.example.moviecatalog.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var id: Int,
    var image: String,
    var title: String,
    var year: String,
    var releaseDate: String = "",
    var ageRating: String = "",
    var tag: ArrayList<String> = arrayListOf(),
    var duration: String = "",
    var userScore: String = "",
    var overview: String = "",
    var status: String = "",
    var originalLanguage: String = "",
    var budget: String = "",
    var revenue: String = "",
    var link: String = ""
) : Parcelable