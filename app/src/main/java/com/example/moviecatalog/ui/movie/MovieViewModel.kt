package com.example.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.local.entity.MovieEntity
import com.example.moviecatalog.data.source.MovieRepository

class MovieViewModel(private var repository: MovieRepository) : ViewModel() {

    private var popularMovies = repository.getPopularMovies()
    private var errorMessage = repository.getErrorMessage()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun loadPopularMovies() = repository.loadPopularMovies()

    fun getPopularMovies(): LiveData<ArrayList<MovieEntity>> = popularMovies
}