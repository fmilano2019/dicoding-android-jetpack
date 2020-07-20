package com.example.moviecatalog.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity

class DataDummy(private var context: Context) {

    fun loadMovieData(): ArrayList<MovieEntity> {
        context.resources.let {
            // Load data from string resource
            val images: TypedArray = it.obtainTypedArray(R.array.movie_image)
            val titles = it.getStringArray(R.array.movie_title)
            val ageRatings = it.getStringArray(R.array.movie_age_rating)
            val releaseDates = it.getStringArray(R.array.movie_release_date)
            val tags = it.getStringArray(R.array.movie_tag)
            val durations = it.getStringArray(R.array.movie_duration)
            val userScores = it.getStringArray(R.array.movie_user_score)
            val overview = it.getStringArray(R.array.movie_overview)
            val status = it.getStringArray(R.array.movie_status)
            val originalLanguages = it.getStringArray(R.array.movie_original_language)
            val budgets = it.getStringArray(R.array.movie_budget)
            val revenue = it.getStringArray(R.array.movie_revenue)

            // Add each movie object to movie list
            val movies = arrayListOf<MovieEntity>()
            titles.forEachIndexed { index, title ->
                val movie = MovieEntity(
                    index,
                    images.getResourceId(index, 0),
                    title,
                    ageRatings[index],
                    releaseDates[index],
                    tags[index],
                    durations[index],
                    userScores[index],
                    overview[index],
                    status[index],
                    originalLanguages[index],
                    budgets[index],
                    revenue[index]
                )
                movies.add(movie)
            }
            images.recycle()
            return movies
        }
    }

}