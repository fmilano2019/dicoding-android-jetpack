package com.example.moviecatalog.ui.detail.tvshow

import com.example.moviecatalog.data.TvShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DetailTvShowViewModelTest {
    private lateinit var dummyDetailTvShowViewModel: DetailTvShowViewModel
    private lateinit var dummyTvShowEntity: TvShowEntity

    @Before
    fun init() {
        dummyDetailTvShowViewModel = mock(DetailTvShowViewModel::class.java)
        dummyTvShowEntity = TvShowEntity(
            1,
            200,
            "myTitle",
            "myYear",
            "myAgeRating",
            "myTag",
            "myDuration",
            "myUserScore",
            "myOverview",
            "myStatus",
            "myNetwork",
            "myType",
            "myOriginalLanguage"
        )
    }

    @Test
    fun getTvShow() {
        `when`(dummyDetailTvShowViewModel.getSelectedTvShowData(dummyTvShowEntity.id)).thenReturn(
            dummyTvShowEntity
        )
        val tvShowEntity = dummyDetailTvShowViewModel.getSelectedTvShowData(dummyTvShowEntity.id)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShowEntity.id, tvShowEntity.id)
        assertEquals(dummyTvShowEntity.image, tvShowEntity.image)
        assertEquals(dummyTvShowEntity.title, tvShowEntity.title)
        assertEquals(dummyTvShowEntity.year, tvShowEntity.year)
        assertEquals(dummyTvShowEntity.ageRating, tvShowEntity.ageRating)
        assertEquals(dummyTvShowEntity.tag, tvShowEntity.tag)
        assertEquals(dummyTvShowEntity.duration, tvShowEntity.duration)
        assertEquals(dummyTvShowEntity.userScore, tvShowEntity.userScore)
        assertEquals(dummyTvShowEntity.overview, tvShowEntity.overview)
        assertEquals(dummyTvShowEntity.status, tvShowEntity.status)
        assertEquals(dummyTvShowEntity.network, tvShowEntity.network)
        assertEquals(dummyTvShowEntity.type, tvShowEntity.type)
        assertEquals(dummyTvShowEntity.originalLanguage, dummyTvShowEntity.originalLanguage)
    }
}