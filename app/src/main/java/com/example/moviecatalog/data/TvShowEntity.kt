package com.example.moviecatalog.data

data class TvShowEntity(
    var id: Int,
    var image: Int,
    var title: String,
    var year: String,
    var ageRating: String,
    var tag: String,
    var duration: String,
    var userScore: String,
    var overview: String,
    var status: String,
    var network: String,
    var type: String,
    var originalLanguage: String
)