package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.TvShowEntity

interface TvShowDataSource {

    fun getErrorMessage(): LiveData<String>

    fun loadPopularTvShows()

    fun getPopularTvShows(): LiveData<ArrayList<TvShowEntity>>

    fun loadDetailTvShow(id: Int)

    fun getDetailTvShow(): LiveData<TvShowEntity>
}