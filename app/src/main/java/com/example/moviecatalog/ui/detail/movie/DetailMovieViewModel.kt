package com.example.moviecatalog.ui.detail.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.ui.home.HomeRepository

class DetailMovieViewModel : ViewModel() {

    private var homeRepository = HomeRepository()
    private var movies = homeRepository.getMovieData()

    fun loadMovieData(context: Context) {
        movies.clear()
        homeRepository.loadMovieData(context)
    }

    fun getSelectedMovieData(id: Int): MovieEntity = movies[id]
}