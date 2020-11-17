package com.example.moviecatalog.ui.movie

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import org.mockito.Mockito.mock
import kotlin.collections.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

object MovieViewModelFeature : Spek({

    beforeEachTest {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

        })
    }

    afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }

    Feature("MovieViewModel") {
        val viewModel by memoized { mock(MovieViewModel::class.java) }
        val moviesObserver: Observer<ArrayList<MovieEntity>> by memoized { mock() }
        val errorMessageObserver: Observer<String> by memoized { mock() }

        Scenario("getting movies") {
            val movie by memoized { mock(MovieEntity::class.java) }
            val dummyMovies by memoized { arrayListOf(movie) }
            val returnMovies by memoized { MutableLiveData<ArrayList<MovieEntity>>() }

            Given("an non-empty movies") {
                returnMovies.value = dummyMovies
            }

            lateinit var movies: LiveData<ArrayList<MovieEntity>>

            When("getting movies from ViewModel") {
                `when`(viewModel.getPopularMovies()).thenReturn(returnMovies)
                movies = viewModel.getPopularMovies()
            }

            Then("it should have a size of 1") {
                assertEquals(dummyMovies.size, movies.value?.size)
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularMovies().observeForever(moviesObserver)
                verify(moviesObserver).onChanged(dummyMovies)
            }
        }

        Scenario("getting empty movies") {
            val dummyMovies by memoized { arrayListOf<MovieEntity>() }
            val returnMovies by memoized { MutableLiveData<ArrayList<MovieEntity>>() }

            Given("an empty movies") {
                returnMovies.value = dummyMovies
            }

            lateinit var movies: LiveData<ArrayList<MovieEntity>>

            When("getting empty movies from ViewModel") {
                `when`(viewModel.getPopularMovies()).thenReturn(returnMovies)
                movies = viewModel.getPopularMovies()
            }

            Then("it should have a size of 0") {
                assertEquals(dummyMovies.size, movies.value?.size)
            }

            Then("it should throw when first movie is invoked") {
                assertFailsWith(NoSuchElementException::class) {
                    movies.value?.first()
                }
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularMovies().observeForever(moviesObserver)
                verify(moviesObserver).onChanged(dummyMovies)
            }
        }

        Scenario("failed getting movies") {
            val movie by memoized { mock(MovieEntity::class.java) }
            val dummyMovies by memoized { arrayListOf(movie) }
            val returnMovies by memoized { MutableLiveData<ArrayList<MovieEntity>>() }

            Given("an non-empty movies") {
                returnMovies.value = dummyMovies
            }

            lateinit var movies: LiveData<ArrayList<MovieEntity>>

            When("getting movies from ViewModel") {
                `when`(viewModel.getPopularMovies()).thenReturn(returnMovies)
                movies = viewModel.getPopularMovies()
            }

            Then("it should throw when second movie is invoked") {
                assertFailsWith(IndexOutOfBoundsException::class) {
                    movies.value?.get(1)
                }
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularMovies().observeForever(moviesObserver)
                verify(moviesObserver).onChanged(dummyMovies)
            }
        }

        Scenario("getting error message") {
            val dummyErrorMessage by memoized { "Error" }
            val returnErrorMessage by memoized { MutableLiveData<String>() }

            Given("an non-empty error message") {
                returnErrorMessage.value = dummyErrorMessage
            }

            lateinit var errorMessage: LiveData<String>

            When("getting error message from ViewModel") {
                `when`(viewModel.getErrorMessage()).thenReturn(returnErrorMessage)
                errorMessage = viewModel.getErrorMessage()
            }

            Then("it should have the same error message") {
                assertEquals(dummyErrorMessage, errorMessage.value)
            }

            Then("it should observe to verify changes") {
                viewModel.getErrorMessage().observeForever(errorMessageObserver)
                verify(errorMessageObserver).onChanged(dummyErrorMessage)
            }
        }

        Scenario("getting empty error message") {
            val dummyErrorMessage by memoized { null }
            val returnErrorMessage by memoized { MutableLiveData<String>() }

            Given("an empty error message") {
                returnErrorMessage.value = dummyErrorMessage
            }

            lateinit var errorMessage: LiveData<String>

            When("getting empty error message from ViewModel") {
                `when`(viewModel.getErrorMessage()).thenReturn(returnErrorMessage)
                errorMessage = viewModel.getErrorMessage()
            }

            Then("it should have an empty error message") {
                assertNull(errorMessage.value)
            }

            Then("it should observe to verify changes") {
                viewModel.getErrorMessage().observeForever(errorMessageObserver)
                verify(errorMessageObserver).onChanged(dummyErrorMessage)
            }
        }
    }
})