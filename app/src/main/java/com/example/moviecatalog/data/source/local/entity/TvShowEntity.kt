package com.example.moviecatalog.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    var id: Int,
    var image: String,
    var title: String,
    var year: String,
    var ageRating: String = "",
    var genres: ArrayList<String> = arrayListOf(),
    var duration: String = "",
    var userScore: String = "",
    var overview: String = "",
    var status: String = "",
    var networks: String = "",
    var type: String = "",
    var originalLanguage: String = "",
    var link: String = ""
) : Parcelable