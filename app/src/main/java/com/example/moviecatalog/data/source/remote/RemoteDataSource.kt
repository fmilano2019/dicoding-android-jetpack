package com.example.moviecatalog.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalog.BuildConfig
import com.example.moviecatalog.data.api.ApiService
import com.example.moviecatalog.data.source.local.entity.MovieEntity
import com.example.moviecatalog.data.source.local.entity.TvShowEntity
import com.example.moviecatalog.data.source.remote.response.DetailMovieResponse
import com.example.moviecatalog.data.source.remote.response.DetailTvShowResponse
import com.example.moviecatalog.data.source.remote.response.MovieResponse
import com.example.moviecatalog.data.source.remote.response.TvShowResponse
import com.example.moviecatalog.utils.EspressoIdlingResource
import com.example.moviecatalog.utils.MappingUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class RemoteDataSource(private val apiService: ApiService) {

    private var popularMovies = MutableLiveData<ArrayList<MovieEntity>>()
    private var detailMovie = MutableLiveData<MovieEntity>()
    private var popularTvShows = MutableLiveData<ArrayList<TvShowEntity>>()
    private var detailTvShow = MutableLiveData<TvShowEntity>()
    private var errorMessage = MutableLiveData<String>()

    private fun clearErrorMessage() = errorMessage.postValue(null)

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun loadPopularMovies() {
        clearErrorMessage()
        EspressoIdlingResource.increment()
        apiService.getPopularMovies(BuildConfig.API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = arrayListOf<MovieEntity>()
                    response.body()?.results?.forEach {
                        val imageUrl = BuildConfig.IMAGE_URL + it.posterPath
                        val year = MappingUtils.dateToYear(it.releaseDate)
                        val movie = MovieEntity(
                            it.id,
                            imageUrl,
                            it.title,
                            year
                        )
                        movies.add(movie)
                    }
                    popularMovies.postValue(movies)
                } else {
                    errorMessage.postValue(response.errorBody().toString())
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getPopularMovies(): LiveData<ArrayList<MovieEntity>> = popularMovies

    fun loadDetailMovie(id: Int) {
        clearErrorMessage()
        EspressoIdlingResource.increment()
        apiService.getDetailMovie(BuildConfig.API_KEY, id, BuildConfig.APPEND_RELEASE_DATES)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    response.body()?.let {
                        val imageUrl = BuildConfig.IMAGE_URL + it.posterPath
                        val year = MappingUtils.dateToYear(it.releaseDate)
                        val ageRating = MappingUtils.findMovieAgeRating(it.ageRatings)
                        val duration = MappingUtils.minutesToHoursMinutes(it.duration)
                        val budget = MappingUtils.currencyFormat(it.budget)
                        val revenue = MappingUtils.currencyFormat(it.revenue)
                        val genres = MappingUtils.genreMovieListToStringList(it.genres)
                        val userScore = MappingUtils.userScoreDoubleToPercent(it.userScore)
                        val originalLanguage = MappingUtils.findOriginalLanguage(
                            it.originalLanguageCode,
                            it.spokenLanguages
                        )
                        val movie = MovieEntity(
                            it.id,
                            imageUrl,
                            it.title,
                            year,
                            it.releaseDate,
                            ageRating,
                            genres,
                            duration,
                            userScore,
                            it.overview,
                            it.status,
                            originalLanguage,
                            budget,
                            revenue,
                            it.homepage
                        )
                        detailMovie.postValue(movie)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getDetailMovie(): LiveData<MovieEntity> = detailMovie

    fun loadPopularTvShows() {
        clearErrorMessage()
        EspressoIdlingResource.increment()
        apiService.getPopularTvShows(BuildConfig.API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val tvShows = arrayListOf<TvShowEntity>()
                        response.body()?.results?.forEach {
                            val imageUrl = BuildConfig.IMAGE_URL + it.posterPath
                            val year = MappingUtils.dateToYear(it.firstAirDate)
                            val tvShow = TvShowEntity(
                                it.id,
                                imageUrl,
                                it.name,
                                year
                            )
                            tvShows.add(tvShow)
                        }
                        popularTvShows.postValue(tvShows)
                    } else {
                        errorMessage.postValue(response.errorBody().toString())
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getPopularTvShows(): LiveData<ArrayList<TvShowEntity>> = popularTvShows

    fun loadDetailTvShow(id: Int) {
        clearErrorMessage()
        EspressoIdlingResource.increment()
        apiService.getDetailTvShow(BuildConfig.API_KEY, id, BuildConfig.APPEND_CONTENT_RATINGS)
            .enqueue(object : Callback<DetailTvShowResponse> {
                override fun onResponse(
                    call: Call<DetailTvShowResponse>,
                    response: Response<DetailTvShowResponse>
                ) {
                    response.body()?.let {
                        val imageUrl = BuildConfig.IMAGE_URL + it.posterPath
                        val year = MappingUtils.dateToYear(it.releaseDate)
                        val ageRating = MappingUtils.findTvShowAgeRating(it.ageRatings)
                        val genres = MappingUtils.genreTvShowListToStringList(it.genres)
                        val duration = MappingUtils.minutesToHoursMinutes(it.episodeRunTime.first())
                        val userScore = MappingUtils.userScoreDoubleToPercent(it.userScore)
                        val networks = MappingUtils.networkTvShowListToString(it.networks)
                        val locale = Locale(it.originalLanguage)
                        val originalLanguage = locale.displayLanguage
                        val tvShow = TvShowEntity(
                            it.id,
                            imageUrl,
                            it.title,
                            year,
                            ageRating,
                            genres,
                            duration,
                            userScore,
                            it.overview,
                            it.status,
                            networks,
                            it.type,
                            originalLanguage,
                            it.homepage
                        )
                        detailTvShow.postValue(tvShow)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getDetailTvShow(): LiveData<TvShowEntity> = detailTvShow

}
