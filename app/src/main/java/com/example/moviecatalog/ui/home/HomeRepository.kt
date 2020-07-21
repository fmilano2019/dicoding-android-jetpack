package com.example.moviecatalog.ui.home

import android.content.Context
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.data.TvShowEntity
import com.example.moviecatalog.utils.DataDummy

class HomeRepository {
    private var movies = arrayListOf<MovieEntity>()
    private var tvShows = arrayListOf<TvShowEntity>()

    // Load movie data
    fun loadMovieData(context: Context) {
        movies.addAll(DataDummy(context).loadMovieData())
    }

    // Get movie data
    fun getMovieData(): ArrayList<MovieEntity> {
        return movies
    }

    // Load tvshow data
    fun loadTvShowData(context: Context) {
        tvShows.addAll(DataDummy(context).loadTvShowData())
    }

    // Get tvshow data
    fun getTvShowData(): ArrayList<TvShowEntity> {
        return tvShows
    }

}