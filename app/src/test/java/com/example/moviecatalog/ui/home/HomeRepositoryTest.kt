package com.example.moviecatalog.ui.home

import com.example.moviecatalog.data.MovieEntity
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class HomeRepositoryTest {
    private lateinit var dummyMovieData: ArrayList<MovieEntity>

    @Mock
    private lateinit var mockHomeRepository: HomeRepository

    @Mock
    private lateinit var mockMovieEntity: MovieEntity

    @Before
    fun init() {
        mockHomeRepository = mock(HomeRepository::class.java)
        mockMovieEntity = mock(MovieEntity::class.java)
        dummyMovieData = arrayListOf(mockMovieEntity, mockMovieEntity)
    }

    @Test
    fun getMovieData() {
        `when`(mockHomeRepository.getMovieData()).thenReturn(dummyMovieData)
        val result = mockHomeRepository.getMovieData()
        assertNotNull(result.size)
        assertEquals(2, result.size)
    }

}