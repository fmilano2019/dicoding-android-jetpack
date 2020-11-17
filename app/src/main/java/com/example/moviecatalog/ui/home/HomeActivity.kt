package com.example.moviecatalog.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.movie.MovieViewModel
import com.example.moviecatalog.ui.movie.MoviesFragment
import com.example.moviecatalog.ui.tvshow.TvShowsFragment
import com.example.moviecatalog.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        subscribeUI()
        vpHome.adapter = HomeViewPagerAdapter(
            arrayListOf(getString(R.string.movies), getString(R.string.tvshows)),
            arrayListOf(MoviesFragment(), TvShowsFragment()),
            supportFragmentManager
        )
        tabHome.apply {
            setupWithViewPager(vpHome)
            getTabAt(0)?.setIcon(R.drawable.selector_movie)
            getTabAt(1)?.setIcon(R.drawable.selector_tvshow)
        }
    }

    private fun subscribeUI() {
        movieViewModel.getErrorMessage().observe(this, {
            if (it != null) {
                ViewUtils.errorSnackbar(coordinatorHome, getString(R.string.no_connection))
            }
        })
    }
}