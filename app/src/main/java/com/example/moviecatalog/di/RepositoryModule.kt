package com.example.moviecatalog.di

import com.example.moviecatalog.data.source.MovieRepository
import com.example.moviecatalog.data.source.TvShowRepository
import org.koin.dsl.module

val repoModule = module {
    single { MovieRepository(get()) }
    single { TvShowRepository(get()) }
}