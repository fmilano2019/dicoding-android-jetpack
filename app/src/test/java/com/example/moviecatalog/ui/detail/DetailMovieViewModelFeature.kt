package com.example.moviecatalog.ui.detail

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.local.entity.MovieEntity
import com.example.moviecatalog.ui.detail.movie.DetailMovieViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertNull

object DetailMovieViewModelFeature : Spek({

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

    Feature("DetailMovieViewModel") {
        val viewModel by memoized { mock(DetailMovieViewModel::class.java) }
        val movieObserver: Observer<MovieEntity> by memoized { mock() }
        val errorMessageObserver: Observer<String> by memoized { mock() }

        Scenario("getting movie detail") {
            val dummyMovie by memoized {
                MovieEntity(
                    1,
                    "myImage",
                    "myTitle",
                    "myYear",
                    "myAgeRating",
                    "myReleaseDate",
                    arrayListOf("myTag"),
                    "myDuration",
                    "myUserScore",
                    "myOverview",
                    "myStatus",
                    "myOriginalLanguage",
                    "myBudget",
                    "myRevenue",
                    "myLink"
                )
            }
            val returnMovie by memoized { MutableLiveData<MovieEntity>() }

            Given("an non-empty movie") {
                returnMovie.value = dummyMovie
            }

            lateinit var movie: LiveData<MovieEntity>

            When("getting movie from ViewModel") {
                `when`(viewModel.getDetailMovie()).thenReturn(returnMovie)
                movie = viewModel.getDetailMovie()
            }

            Then("it should have the correct movie id") {
                assertEquals(dummyMovie.id, movie.value?.id)
            }

            Then("it should have the correct movie image") {
                assertEquals(dummyMovie.image, movie.value?.image)
            }

            Then("it should have the correct movie title") {
                assertEquals(dummyMovie.title, movie.value?.title)
            }

            Then("it should have the correct movie year") {
                assertEquals(dummyMovie.year, movie.value?.year)
            }

            Then("it should have the correct movie age rating") {
                assertEquals(dummyMovie.ageRating, movie.value?.ageRating)
            }

            Then("it should have the correct movie release date") {
                assertEquals(dummyMovie.releaseDate, movie.value?.releaseDate)
            }

            Then("it should have the correct movie tag size") {
                assertEquals(dummyMovie.tag.size, movie.value?.tag?.size)
            }

            Then("it should have the correct movie duration") {
                assertEquals(dummyMovie.duration, movie.value?.duration)
            }

            Then("it should have the correct movie user score") {
                assertEquals(dummyMovie.userScore, movie.value?.userScore)
            }

            Then("it should have the correct movie overview") {
                assertEquals(dummyMovie.overview, movie.value?.overview)
            }

            Then("it should have the correct movie status") {
                assertEquals(dummyMovie.status, movie.value?.status)
            }

            Then("it should have the correct movie original language") {
                assertEquals(dummyMovie.originalLanguage, movie.value?.originalLanguage)
            }

            Then("it should have the correct movie budget") {
                assertEquals(dummyMovie.budget, movie.value?.budget)
            }

            Then("it should have the correct movie revenue") {
                assertEquals(dummyMovie.revenue, movie.value?.revenue)
            }

            Then("it should have the correct movie link") {
                assertEquals(dummyMovie.link, movie.value?.link)
            }

            Then("it should observe to verify changes") {
                viewModel.getDetailMovie().observeForever(movieObserver)
                verify(movieObserver).onChanged(dummyMovie)
            }
        }

        Scenario("getting empty movie detail") {
            val dummyMovie by memoized { mock(MovieEntity::class.java) }
            val returnMovie by memoized { MutableLiveData<MovieEntity>() }

            Given("an empty movie") {
                returnMovie.value = dummyMovie
            }

            lateinit var movie: LiveData<MovieEntity>

            When("getting empty movie from ViewModel") {
                `when`(viewModel.getDetailMovie()).thenReturn(returnMovie)
                movie = viewModel.getDetailMovie()
            }

            Then("it should have 0 as movie id") {
                assertEquals(dummyMovie.id, movie.value?.id)
            }

            Then("it should have null as movie image") {
                assertNull(movie.value?.image)
            }

            Then("it should have null as movie title") {
                assertNull(movie.value?.title)
            }

            Then("it should have null as movie year") {
                assertNull(movie.value?.year)
            }

            Then("it should have null as movie age rating") {
                assertNull(movie.value?.ageRating)
            }

            Then("it should have null as movie release date") {
                assertNull(movie.value?.releaseDate)
            }

            Then("it should have 0 as movie tag size") {
                assertEquals(dummyMovie.tag.size, movie.value?.tag?.size)
            }

            Then("it should have null as movie duration") {
                assertNull(movie.value?.duration)
            }

            Then("it should have null as movie user score") {
                assertNull(movie.value?.userScore)
            }

            Then("it should have null as movie overview") {
                assertNull(movie.value?.overview)
            }

            Then("it should have null as movie status") {
                assertNull(movie.value?.status)
            }

            Then("it should have null as movie original language") {
                assertNull(movie.value?.originalLanguage)
            }

            Then("it should have null as movie budget") {
                assertNull(movie.value?.budget)
            }

            Then("it should have null as movie revenue") {
                assertNull(movie.value?.revenue)
            }

            Then("it should have null as movie link") {
                assertNull(movie.value?.link)
            }

            Then("it should observe to verify changes") {
                viewModel.getDetailMovie().observeForever(movieObserver)
                verify(movieObserver).onChanged(dummyMovie)
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