package com.example.moviecatalog.ui.detail.tvshow

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.TvShowEntity
import com.example.moviecatalog.ui.home.HomeRepository

class DetailTvShowViewModel : ViewModel() {

    private var homeRepository = HomeRepository()
    private var tvShows = homeRepository.getTvShowData()

    fun loadTvShowData(context: Context) {
        tvShows.clear()
        homeRepository.loadTvShowData(context)
    }

    fun getSelectedTvShowData(id: Int): TvShowEntity = tvShows[id]
}