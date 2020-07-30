package com.example.moviecatalog.ui.tvshow

import com.example.moviecatalog.data.TvShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TvShowViewModelTest {
    private lateinit var dummyTvShowViewModel: TvShowViewModel
    private lateinit var dummyTvShowEntity: TvShowEntity
    private lateinit var dummyTvShows: ArrayList<TvShowEntity>

    @Before
    fun init() {
        dummyTvShowViewModel = mock(TvShowViewModel::class.java)
        dummyTvShowEntity = mock(TvShowEntity::class.java)
        dummyTvShows = arrayListOf(dummyTvShowEntity, dummyTvShowEntity)
    }

    @Test
    fun getTvShows() {
        `when`(dummyTvShowViewModel.getTvShowData()).thenReturn(dummyTvShows)
        val tvShows = dummyTvShowViewModel.getTvShowData()
        assertNotNull(tvShows.size)
        assertEquals(dummyTvShows.size, tvShows.size)
    }
}