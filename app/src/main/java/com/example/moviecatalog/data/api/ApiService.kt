package com.example.moviecatalog.data.api

import com.example.moviecatalog.data.source.remote.response.DetailMovieResponse
import com.example.moviecatalog.data.source.remote.response.DetailTvShowResponse
import com.example.moviecatalog.data.source.remote.response.MovieResponse
import com.example.moviecatalog.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") auth: String
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Header("Authorization") auth: String,
        @Path("id") id: Int,
        @Query("append_to_response") append: String
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Header("Authorization") auth: String
    ): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Header("Authorization") auth: String,
        @Path("id") id: Int,
        @Query("append_to_response") append: String
    ): Call<DetailTvShowResponse>
}