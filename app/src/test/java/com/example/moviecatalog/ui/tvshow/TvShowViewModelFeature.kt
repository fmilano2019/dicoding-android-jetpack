package com.example.moviecatalog.ui.tvshow

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

object TvShowViewModelFeature : Spek({

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

    Feature("TvShowViewModel") {
        val viewModel by memoized { mock(TvShowViewModel::class.java) }
        val tvShowsObserver: Observer<ArrayList<TvShowEntity>> by memoized { mock() }
        val errorMessageObserver: Observer<String> by memoized { mock() }

        Scenario("getting tvShows") {
            val tvShow by memoized { mock(TvShowEntity::class.java) }
            val dummyTvShows by memoized { arrayListOf(tvShow) }
            val returnTvShows by memoized { MutableLiveData<ArrayList<TvShowEntity>>() }

            Given("an non-empty tvShows") {
                returnTvShows.value = dummyTvShows
            }

            lateinit var tvShows: LiveData<ArrayList<TvShowEntity>>

            When("getting tvShows from ViewModel") {
                `when`(viewModel.getPopularTvShows()).thenReturn(returnTvShows)
                tvShows = viewModel.getPopularTvShows()
            }

            Then("it should have a size of 1") {
                assertEquals(dummyTvShows.size, tvShows.value?.size)
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularTvShows().observeForever(tvShowsObserver)
                verify(tvShowsObserver).onChanged(dummyTvShows)
            }
        }

        Scenario("getting empty tvShows") {
            val dummyTvShows by memoized { arrayListOf<TvShowEntity>() }
            val returnTvShows by memoized { MutableLiveData<ArrayList<TvShowEntity>>() }

            Given("an empty tvShows") {
                returnTvShows.value = dummyTvShows
            }

            lateinit var tvShows: LiveData<ArrayList<TvShowEntity>>

            When("getting empty tvShows from ViewModel") {
                `when`(viewModel.getPopularTvShows()).thenReturn(returnTvShows)
                tvShows = viewModel.getPopularTvShows()
            }

            Then("it should have a size of 0") {
                assertEquals(dummyTvShows.size, tvShows.value?.size)
            }

            Then("it should throw when first tvShow is invoked") {
                assertFailsWith(NoSuchElementException::class) {
                    tvShows.value?.first()
                }
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularTvShows().observeForever(tvShowsObserver)
                verify(tvShowsObserver).onChanged(dummyTvShows)
            }
        }

        Scenario("failed getting tvShows") {
            val tvShow by memoized { mock(TvShowEntity::class.java) }
            val dummyTvShows by memoized { arrayListOf(tvShow) }
            val returnTvShows by memoized { MutableLiveData<ArrayList<TvShowEntity>>() }

            Given("an non-empty tvShows") {
                returnTvShows.value = dummyTvShows
            }

            lateinit var tvShows: LiveData<ArrayList<TvShowEntity>>

            When("getting tvShows from ViewModel") {
                `when`(viewModel.getPopularTvShows()).thenReturn(returnTvShows)
                tvShows = viewModel.getPopularTvShows()
            }

            Then("it should throw when second tvShow is invoked") {
                assertFailsWith(IndexOutOfBoundsException::class) {
                    tvShows.value?.get(1)
                }
            }

            Then("it should observe to verify changes") {
                viewModel.getPopularTvShows().observeForever(tvShowsObserver)
                verify(tvShowsObserver).onChanged(dummyTvShows)
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