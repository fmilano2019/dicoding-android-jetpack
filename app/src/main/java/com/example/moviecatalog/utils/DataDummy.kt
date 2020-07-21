package com.example.moviecatalog.utils

import android.content.Context
import android.content.res.TypedArray
import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.data.TvShowEntity

class DataDummy(private var context: Context) {

    fun loadMovieData(): ArrayList<MovieEntity> {
        context.resources.let {
            // Load data from string resource
            val images: TypedArray = it.obtainTypedArray(R.array.movie_image)
            val titles = it.getStringArray(R.array.movie_title)
            val years = it.getStringArray(R.array.movie_year)
            val ageRatings = it.getStringArray(R.array.movie_age_rating)
            val releaseDates = it.getStringArray(R.array.movie_release_date)
            val tags = it.getStringArray(R.array.movie_tag)
            val durations = it.getStringArray(R.array.movie_duration)
            val userScores = it.getStringArray(R.array.movie_user_score)
            val overviews = it.getStringArray(R.array.movie_overview)
            val status = it.getStringArray(R.array.movie_status)
            val originalLanguages = it.getStringArray(R.array.movie_original_language)
            val budgets = it.getStringArray(R.array.movie_budget)
            val revenues = it.getStringArray(R.array.movie_revenue)

            // Add each object to movie list
            val movies = arrayListOf<MovieEntity>()
            titles.forEachIndexed { index, title ->
                val movie = MovieEntity(
                    index,
                    images.getResourceId(index, 0),
                    title,
                    years[index],
                    ageRatings[index],
                    releaseDates[index],
                    tags[index],
                    durations[index],
                    userScores[index],
                    overviews[index],
                    status[index],
                    originalLanguages[index],
                    budgets[index],
                    revenues[index]
                )
                movies.add(movie)
            }
            images.recycle()
            return movies
        }
    }

    fun loadTvShowData(): ArrayList<TvShowEntity> {
        context.resources.let {
            // Load data from string resource
            val images = it.obtainTypedArray(R.array.tvshow_image)
            val titles = it.getStringArray(R.array.tvshow_title)
            val years = it.getStringArray(R.array.tvshow_year)
            val ageRatings = it.getStringArray(R.array.tvshow_age_rating)
            val tags = it.getStringArray(R.array.tvshow_tag)
            val durations = it.getStringArray(R.array.tvshow_duration)
            val userScores = it.getStringArray(R.array.tvshow_user_score)
            val overviews = it.getStringArray(R.array.tvshow_overview)
            val status = it.getStringArray(R.array.tvshow_status)
            val networks = it.getStringArray(R.array.tvshow_network)
            val types = it.getStringArray(R.array.tvshow_type)
            val originalLanguages = it.getStringArray(R.array.tvshow_original_language)

            //Add each object to tvshow list
            val tvShows = arrayListOf<TvShowEntity>()
            titles.forEachIndexed { index, title ->
                val tvShow = TvShowEntity(
                    index,
                    images.getResourceId(index, 0),
                    title,
                    years[index],
                    ageRatings[index],
                    tags[index],
                    durations[index],
                    userScores[index],
                    overviews[index],
                    status[index],
                    networks[index],
                    types[index],
                    originalLanguages[index]
                )
                tvShows.add(tvShow)
            }
            images.recycle()
            return tvShows
        }
    }

}