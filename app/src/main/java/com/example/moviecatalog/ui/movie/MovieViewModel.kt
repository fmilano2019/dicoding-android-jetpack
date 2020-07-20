package com.example.moviecatalog.ui.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.ui.home.HomeRepository

class MovieViewModel : ViewModel() {
    private var homeRepository = HomeRepository()
    private var movies = homeRepository.getMovieData()

    // Load movie list from HomeRepository
    fun loadMovieData(context: Context) {
        movies.clear()
        homeRepository.loadMovieData(context)
    }

    // Get movie list
    fun getMovieData(): ArrayList<MovieEntity> {
        return movies
    }

}