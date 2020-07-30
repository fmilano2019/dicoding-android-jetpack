package com.example.moviecatalog.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalog.R
import com.example.moviecatalog.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private var context = ApplicationProvider.getApplicationContext<Context>()
    private var dummyMovies = DataDummy(context).loadMovieData()
    private var dummyTvShows = DataDummy(context).loadTvShowData()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ivImage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(withText(dummyMovies[0].year)))
        onView(withId(R.id.tvAgeRating)).check(matches(withText(dummyMovies[0].ageRating)))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.tvDuration)).check(matches(withText(dummyMovies[0].duration)))
        onView(withId(R.id.tvUserscore)).check(matches(withText(dummyMovies[0].userScore)))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyMovies[0].overview)))
        onView(withId(R.id.tvStatus)).check(matches(withText(dummyMovies[0].status)))
        onView(withId(R.id.tvOriginalLanguage)).check(matches(withText(dummyMovies[0].originalLanguage)))
        onView(withId(R.id.tvBudget)).check(matches(withText(dummyMovies[0].budget)))
        onView(withId(R.id.tvRevenue)).check(matches(withText(dummyMovies[0].revenue)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ivImage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(withText(dummyTvShows[0].year)))
        onView(withId(R.id.tvAgeRating)).check(matches(withText(dummyTvShows[0].ageRating)))
        onView(withId(R.id.tvDuration)).check(matches(withText(dummyTvShows[0].duration)))
        onView(withId(R.id.tvUserscore)).check(matches(withText(dummyTvShows[0].userScore)))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.tvStatus)).check(matches(withText(dummyTvShows[0].status)))
        onView(withId(R.id.tvNetwork)).check(matches(withText(dummyTvShows[0].network)))
        onView(withId(R.id.tvType)).check(matches(withText(dummyTvShows[0].type)))
        onView(withId(R.id.tvOriginalLanguage)).check(matches(withText(dummyTvShows[0].originalLanguage)))
    }

}