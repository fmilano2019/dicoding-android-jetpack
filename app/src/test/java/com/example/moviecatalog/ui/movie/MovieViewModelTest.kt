package com.example.moviecatalog.ui.movie

import com.example.moviecatalog.data.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieViewModelTest {
    private lateinit var dummyMovieViewModel: MovieViewModel
    private lateinit var dummyMovieEntity: MovieEntity
    private lateinit var dummyMovies: ArrayList<MovieEntity>

    @Before
    fun init() {
        dummyMovieViewModel = mock(MovieViewModel::class.java)
        dummyMovieEntity = mock(MovieEntity::class.java)
        dummyMovies = arrayListOf(dummyMovieEntity, dummyMovieEntity, dummyMovieEntity)
    }

    @Test
    fun getMovies() {
        `when`(dummyMovieViewModel.getMovieData()).thenReturn(dummyMovies)
        val movies = dummyMovieViewModel.getMovieData()
        assertNotNull(movies.size)
        assertEquals(dummyMovies.size, movies.size)
    }

}