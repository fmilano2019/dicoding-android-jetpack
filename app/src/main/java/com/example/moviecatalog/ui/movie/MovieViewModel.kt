package com.example.moviecatalog.ui.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.ui.home.HomeRepository

class MovieViewModel : ViewModel() {

    private var homeRepository = HomeRepository()
    private var movies = homeRepository.getMovieData()

    fun loadMovieData(context: Context) {
        movies.clear()
        homeRepository.loadMovieData(context)
    }

    fun getMovieData(): ArrayList<MovieEntity> = movies
}