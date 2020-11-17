package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.MovieEntity
import com.example.moviecatalog.data.source.remote.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    private var popularMovies = remoteDataSource.getPopularMovies()
    private var detailMovie = remoteDataSource.getDetailMovie()
    private var errorMessage = remoteDataSource.getErrorMessage()

    override fun getErrorMessage(): LiveData<String> = errorMessage

    override fun loadPopularMovies() = remoteDataSource.loadPopularMovies()

    override fun getPopularMovies(): LiveData<ArrayList<MovieEntity>> = popularMovies

    override fun loadDetailMovie(id: Int) = remoteDataSource.loadDetailMovie(id)

    override fun getDetailMovie(): LiveData<MovieEntity> = detailMovie
}