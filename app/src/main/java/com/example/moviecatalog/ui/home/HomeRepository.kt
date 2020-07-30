package com.example.moviecatalog.ui.home

import android.content.Context
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.data.TvShowEntity
import com.example.moviecatalog.utils.DataDummy

class HomeRepository {

    private var movies = arrayListOf<MovieEntity>()
    private var tvShows = arrayListOf<TvShowEntity>()

    fun loadMovieData(context: Context) {
        movies.addAll(DataDummy(context).loadMovieData())
    }

    fun getMovieData(): ArrayList<MovieEntity> = movies

    fun loadTvShowData(context: Context) {
        tvShows.addAll(DataDummy(context).loadTvShowData())
    }

    fun getTvShowData(): ArrayList<TvShowEntity> = tvShows
}