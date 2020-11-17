package com.example.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.TvShowRepository
import com.example.moviecatalog.data.source.local.entity.TvShowEntity

class TvShowViewModel(private var repository: TvShowRepository) : ViewModel() {

    private var popularTvShows = repository.getPopularTvShows()
    private var errorMessage = repository.getErrorMessage()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun loadPopularTvShows() = repository.loadPopularTvShows()

    fun getPopularTvShows(): LiveData<ArrayList<TvShowEntity>> = popularTvShows
}