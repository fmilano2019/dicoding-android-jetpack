package com.example.moviecatalog.ui.movie

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.MovieRepository
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

object MovieRepositoryFeature : Spek({

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

    Feature("MovieRepository") {
        val repository by memoized { mock(MovieRepository::class.java) }
        val moviesObserver: Observer<ArrayList<MovieEntity>> by memoized { mock() }
        val movieObserver: Observer<MovieEntity> by memoized { mock() }
        val errorMessageObserver: Observer<String> by memoized { mock() }

        Scenario("getting movies") {
            val movie by memoized { mock(MovieEntity::class.java) }
            val dummyMovies by memoized { arrayListOf(movie) }
            val returnMovies by memoized { MutableLiveData<ArrayList<MovieEntity>>() }

            Given("an non-empty movies") {
                returnMovies.value = dummyMovies
            }

            lateinit var movies: LiveData<ArrayList<MovieEntity>>

            When("getting movies from Repository") {
                `when`(repository.getPopularMovies()).thenReturn(returnMovies)
                movies = repository.getPopularMovies()
            }

            Then("it should have a size of 1") {
                assertEquals(dummyMovies.size, movies.value?.size)
            }

            Then("it should observe to verify changes") {
                repository.getPopularMovies().observeForever(moviesObserver)
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

            When("getting empty movies from Repository") {
                `when`(repository.getPopularMovies()).thenReturn(returnMovies)
                movies = repository.getPopularMovies()
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
                repository.getPopularMovies().observeForever(moviesObserver)
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

            When("getting movies from Repository") {
                `when`(repository.getPopularMovies()).thenReturn(returnMovies)
                movies = repository.getPopularMovies()
            }

            Then("it should throw when second movie is invoked") {
                assertFailsWith(IndexOutOfBoundsException::class) {
                    movies.value?.get(1)
                }
            }

            Then("it should observe to verify changes") {
                repository.getPopularMovies().observeForever(moviesObserver)
                verify(moviesObserver).onChanged(dummyMovies)
            }
        }

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

            When("getting movie from Repository") {
                `when`(repository.getDetailMovie()).thenReturn(returnMovie)
                movie = repository.getDetailMovie()
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
                repository.getDetailMovie().observeForever(movieObserver)
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

            When("getting empty movie from Repository") {
                `when`(repository.getDetailMovie()).thenReturn(returnMovie)
                movie = repository.getDetailMovie()
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
                repository.getDetailMovie().observeForever(movieObserver)
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

            When("getting error message from Repository") {
                `when`(repository.getErrorMessage()).thenReturn(returnErrorMessage)
                errorMessage = repository.getErrorMessage()
            }

            Then("it should have the same error message") {
                assertEquals(dummyErrorMessage, errorMessage.value)
            }

            Then("it should observe to verify changes") {
                repository.getErrorMessage().observeForever(errorMessageObserver)
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

            When("getting empty error message from Repository") {
                `when`(repository.getErrorMessage()).thenReturn(returnErrorMessage)
                errorMessage = repository.getErrorMessage()
            }

            Then("it should have an empty error message") {
                assertNull(errorMessage.value)
            }

            Then("it should observe to verify changes") {
                repository.getErrorMessage().observeForever(errorMessageObserver)
                verify(errorMessageObserver).onChanged(dummyErrorMessage)
            }
        }
    }
})