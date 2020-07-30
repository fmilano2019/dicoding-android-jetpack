package com.example.moviecatalog.ui.detail.movie

import com.example.moviecatalog.data.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DetailMovieViewModelTest {
    private lateinit var dummyDetailMovieViewModel: DetailMovieViewModel
    private lateinit var dummyMovieEntity: MovieEntity

    @Before
    fun init() {
        dummyDetailMovieViewModel = mock(DetailMovieViewModel::class.java)
        dummyMovieEntity = MovieEntity(
            1,
            200,
            "myTitle",
            "myYear",
            "myAgeRating",
            "myReleaseDate",
            "myTag",
            "myDuration",
            "myUserScore",
            "myOverview",
            "myStatus",
            "myOriginalLanguage",
            "myBudget",
            "myRevenue"
        )
    }

    @Test
    fun getMovie() {
        `when`(dummyDetailMovieViewModel.getSelectedMovieData(dummyMovieEntity.id)).thenReturn(
            dummyMovieEntity
        )
        val movieEntity = dummyDetailMovieViewModel.getSelectedMovieData(dummyMovieEntity.id)
        assertNotNull(movieEntity)
        assertEquals(dummyMovieEntity.id, movieEntity.id)
        assertEquals(dummyMovieEntity.image, movieEntity.image)
        assertEquals(dummyMovieEntity.title, movieEntity.title)
        assertEquals(dummyMovieEntity.year, movieEntity.year)
        assertEquals(dummyMovieEntity.ageRating, movieEntity.ageRating)
        assertEquals(dummyMovieEntity.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovieEntity.tag, movieEntity.tag)
        assertEquals(dummyMovieEntity.duration, movieEntity.duration)
        assertEquals(dummyMovieEntity.userScore, movieEntity.userScore)
        assertEquals(dummyMovieEntity.overview, movieEntity.overview)
        assertEquals(dummyMovieEntity.status, movieEntity.status)
        assertEquals(dummyMovieEntity.originalLanguage, movieEntity.originalLanguage)
        assertEquals(dummyMovieEntity.budget, movieEntity.budget)
        assertEquals(dummyMovieEntity.revenue, movieEntity.revenue)
    }
}