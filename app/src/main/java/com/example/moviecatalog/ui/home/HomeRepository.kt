package com.example.moviecatalog.ui.home

import android.content.Context
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.utils.DataDummy

class HomeRepository {
    private var movies = arrayListOf<MovieEntity>()

    // Add movie list data from DataDummy
    fun loadMovieData(context: Context) {
        movies.addAll(DataDummy(context).loadMovieData())
    }

    // Get movie list
    fun getMovieData(): ArrayList<MovieEntity> {
        return movies
    }

}