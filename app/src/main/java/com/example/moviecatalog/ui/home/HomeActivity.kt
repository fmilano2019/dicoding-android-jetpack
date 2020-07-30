package com.example.moviecatalog.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.movie.MoviesFragment
import com.example.moviecatalog.ui.tvshow.TvShowsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        vpHome.adapter = HomeViewPagerAdapter(
            arrayListOf("Movies", "Tv Shows"),
            arrayListOf(MoviesFragment(), TvShowsFragment()),
            supportFragmentManager,
            0
        )
        tabHome.apply {
            setupWithViewPager(vpHome)
            getTabAt(0)?.setIcon(R.drawable.selector_movie)
            getTabAt(1)?.setIcon(R.drawable.selector_tvshow)
        }
    }
}