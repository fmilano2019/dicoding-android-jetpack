package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.TvShowEntity
import com.example.moviecatalog.data.source.remote.RemoteDataSource

class TvShowRepository(private val remoteDataSource: RemoteDataSource) : TvShowDataSource {

    private var popularTvShows = remoteDataSource.getPopularTvShows()
    private var detailTvShow = remoteDataSource.getDetailTvShow()
    private var errorMessage = remoteDataSource.getErrorMessage()

    override fun getErrorMessage(): LiveData<String> = errorMessage

    override fun loadPopularTvShows() = remoteDataSource.loadPopularTvShows()

    override fun getPopularTvShows(): LiveData<ArrayList<TvShowEntity>> = popularTvShows

    override fun loadDetailTvShow(id: Int) = remoteDataSource.loadDetailTvShow(id)

    override fun getDetailTvShow(): LiveData<TvShowEntity> = detailTvShow
}