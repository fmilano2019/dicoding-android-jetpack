package com.example.moviecatalog.ui.detail

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.local.entity.TvShowEntity
import com.example.moviecatalog.ui.detail.tvshow.DetailTvShowViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertNull

object DetailTvShowViewModelFeature : Spek({

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

    Feature("DetailTvShowViewModel") {
        val viewModel by memoized { mock(DetailTvShowViewModel::class.java) }
        val tvShowObserver: Observer<TvShowEntity> by memoized { mock() }
        val errorMessageObserver: Observer<String> by memoized { mock() }

        Scenario("getting tvShow detail") {
            val dummyTvShow by memoized {
                TvShowEntity(
                    1,
                    "myImage",
                    "myTitle",
                    "myYear",
                    "myAgeRating",
                    arrayListOf("myGenres"),
                    "myDuration",
                    "myUserScore",
                    "myOverview",
                    "myStatus",
                    "myNetworks",
                    "myType",
                    "myOriginalLanguage",
                    "myLink"
                )
            }
            val returnTvShow by memoized { MutableLiveData<TvShowEntity>() }

            Given("an non-empty tvShow") {
                returnTvShow.value = dummyTvShow
            }

            lateinit var tvShow: LiveData<TvShowEntity>

            When("getting tvShow from ViewModel") {
                `when`(viewModel.getDetailTvShow()).thenReturn(returnTvShow)
                tvShow = viewModel.getDetailTvShow()
            }

            Then("it should have the correct tvShow id") {
                assertEquals(dummyTvShow.id, tvShow.value?.id)
            }

            Then("it should have the correct tvShow image") {
                assertEquals(dummyTvShow.image, tvShow.value?.image)
            }

            Then("it should have the correct tvShow title") {
                assertEquals(dummyTvShow.title, tvShow.value?.title)
            }

            Then("it should have the correct tvShow year") {
                assertEquals(dummyTvShow.year, tvShow.value?.year)
            }

            Then("it should have the correct tvShow age rating") {
                assertEquals(dummyTvShow.ageRating, tvShow.value?.ageRating)
            }

            Then("it should have the correct tvShow genres size") {
                assertEquals(dummyTvShow.genres.size, tvShow.value?.genres?.size)
            }

            Then("it should have the correct tvShow duration") {
                assertEquals(dummyTvShow.duration, tvShow.value?.duration)
            }

            Then("it should have the correct tvShow user score") {
                assertEquals(dummyTvShow.userScore, tvShow.value?.userScore)
            }

            Then("it should have the correct tvShow overview") {
                assertEquals(dummyTvShow.overview, tvShow.value?.overview)
            }

            Then("it should have the correct tvShow status") {
                assertEquals(dummyTvShow.status, tvShow.value?.status)
            }

            Then("it should have the correct tvShow networks") {
                assertEquals(dummyTvShow.networks, tvShow.value?.networks)
            }

            Then("it should have the correct tvShow type") {
                assertEquals(dummyTvShow.type, tvShow.value?.type)
            }

            Then("it should have the correct tvShow original language") {
                assertEquals(dummyTvShow.originalLanguage, tvShow.value?.originalLanguage)
            }

            Then("it should have the correct tvShow link") {
                assertEquals(dummyTvShow.link, tvShow.value?.link)
            }

            Then("it should observe to verify changes") {
                viewModel.getDetailTvShow().observeForever(tvShowObserver)
                verify(tvShowObserver).onChanged(dummyTvShow)
            }
        }

        Scenario("getting empty tvShow detail") {
            val dummyTvShow by memoized { mock(TvShowEntity::class.java) }
            val returnTvShow by memoized { MutableLiveData<TvShowEntity>() }

            Given("an empty tvShow") {
                returnTvShow.value = dummyTvShow
            }

            lateinit var tvShow: LiveData<TvShowEntity>

            When("getting empty tvShow from ViewModel") {
                `when`(viewModel.getDetailTvShow()).thenReturn(returnTvShow)
                tvShow = viewModel.getDetailTvShow()
            }

            Then("it should have 0 as tvShow id") {
                assertEquals(dummyTvShow.id, tvShow.value?.id)
            }

            Then("it should have null as tvShow image") {
                assertNull(tvShow.value?.image)
            }

            Then("it should have null as tvShow title") {
                assertNull(tvShow.value?.title)
            }

            Then("it should have null as tvShow year") {
                assertNull(tvShow.value?.year)
            }

            Then("it should have null as tvShow age rating") {
                assertNull(tvShow.value?.ageRating)
            }

            Then("it should have 0 as tvShow genres size") {
                assertEquals(dummyTvShow.genres.size, tvShow.value?.genres?.size)
            }

            Then("it should have null as tvShow duration") {
                assertNull(tvShow.value?.duration)
            }

            Then("it should have null as tvShow user score") {
                assertNull(tvShow.value?.userScore)
            }

            Then("it should have null as tvShow overview") {
                assertNull(tvShow.value?.overview)
            }

            Then("it should have null as tvShow status") {
                assertNull(tvShow.value?.status)
            }

            Then("it should have null as tvShow networks") {
                assertNull(tvShow.value?.networks)
            }

            Then("it should have null as tvShow type") {
                assertNull(tvShow.value?.type)
            }

            Then("it should have null as tvShow original language") {
                assertNull(tvShow.value?.originalLanguage)
            }

            Then("it should have null as tvShow link") {
                assertNull(tvShow.value?.link)
            }

            Then("it should observe to verify changes") {
                viewModel.getDetailTvShow().observeForever(tvShowObserver)
                verify(tvShowObserver).onChanged(dummyTvShow)
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