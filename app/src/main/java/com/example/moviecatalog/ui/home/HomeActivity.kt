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
        // Setup viewpager adapter
        vp_home.adapter = HomeViewPagerAdapter(
            arrayListOf("Movies", "Tv Shows"),
            arrayListOf(MoviesFragment(), TvShowsFragment()),
            supportFragmentManager,
            0
        )
        // Setup tab viewpager
        tl_home.setupWithViewPager(vp_home)
    }
}