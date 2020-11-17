package com.example.moviecatalog.di

import com.example.moviecatalog.ui.detail.movie.DetailMovieViewModel
import com.example.moviecatalog.ui.detail.tvshow.DetailTvShowViewModel
import com.example.moviecatalog.ui.movie.MovieViewModel
import com.example.moviecatalog.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
}