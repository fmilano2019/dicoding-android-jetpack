package com.example.moviecatalog.ui.home

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalog.R
import com.example.moviecatalog.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun shouldSucceedToLoadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldSucceedToLoadMovieDetails() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivImage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldSucceedToInitiateShareLinkChooserForMovieDetails() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        Intents.init()
        val sendMatcher = allOf(
            hasAction(Intent.ACTION_SEND)
        )
        val chooserMatcher = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(equalTo(Intent.EXTRA_INTENT), sendMatcher)
        )
        intending(chooserMatcher)
        onView(withId(R.id.menu_item_share)).perform(click())
        intended(chooserMatcher)
        Intents.release()
    }

    @Test
    fun shouldSucceedToLoadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldSucceedToLoadTvShowDetails() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivImage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldSucceedToInitiateShareLinkChooserForTvShowDetails() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        Intents.init()
        val sendMatcher = allOf(
            hasAction(Intent.ACTION_SEND)
        )
        val chooserMatcher = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(equalTo(Intent.EXTRA_INTENT), sendMatcher)
        )
        intending(chooserMatcher)
        onView(withId(R.id.menu_item_share)).perform(click())
        intended(chooserMatcher)
        Intents.release()
    }
}