package com.example.moviecatalog.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.MovieRepository
import com.example.moviecatalog.data.source.local.entity.MovieEntity

class DetailMovieViewModel(private var repository: MovieRepository) : ViewModel() {

    private var detailMovie = repository.getDetailMovie()
    private var errorMessage = repository.getErrorMessage()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun loadDetailMovie(id: Int) = repository.loadDetailMovie(id)

    fun getDetailMovie(): LiveData<MovieEntity> = detailMovie
}