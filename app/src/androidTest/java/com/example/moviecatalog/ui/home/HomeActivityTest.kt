package com.example.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalog.R
import com.example.moviecatalog.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadHomeViewPager() {
        onView(withId(R.id.vp_home)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_home)).perform(swipeLeft())
        onView(withId(R.id.vp_home)).perform(swipeRight())
    }

    @Test
    fun loadMovieRecyclerView() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
    }

}