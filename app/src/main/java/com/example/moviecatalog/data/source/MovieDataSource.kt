package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.MovieEntity

interface MovieDataSource {

    fun getErrorMessage(): LiveData<String>

    fun loadPopularMovies()

    fun getPopularMovies(): LiveData<ArrayList<MovieEntity>>

    fun loadDetailMovie(id: Int)

    fun getDetailMovie(): LiveData<MovieEntity>
}