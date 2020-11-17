package com.example.moviecatalog.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.TvShowRepository
import com.example.moviecatalog.data.source.local.entity.TvShowEntity

class DetailTvShowViewModel(private var repository: TvShowRepository) : ViewModel() {

    private var detailTvShow = repository.getDetailTvShow()
    private var errorMessage = repository.getErrorMessage()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun loadDetailTvShow(id: Int) = repository.loadDetailTvShow(id)

    fun getDetailTvShow(): LiveData<TvShowEntity> = detailTvShow
}