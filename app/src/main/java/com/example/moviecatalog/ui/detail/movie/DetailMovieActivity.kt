package com.example.moviecatalog.ui.detail.movie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.DetailTagAdapter
import com.example.moviecatalog.ui.movie.MoviesFragment.Companion.EXTRA_MOVIE_ID
import com.example.moviecatalog.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private var id: Int by Delegates.notNull()
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(tbDetail)
        supportActionBar?.title = null
        swipeRefreshDetailMovie.setOnRefreshListener { detailMovieViewModel.loadDetailMovie(id) }
        tbDetail.setNavigationOnClickListener { onBackPressed() }
        link = resources.getString(R.string.link_not_available)
        id = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        subscribeUI(id)
    }

    private fun subscribeUI(id: Int) {
        detailMovieViewModel.run {
            loadDetailMovie(id)
            getDetailMovie().observe(this@DetailMovieActivity, {
                if (it != null) {
                    if (id == it.id) {
                        tvTitle.text = it.title
                        tvYear.text = it.year
                        tvUserscore.text = it.userScore
                        tvAgeRating.text = it.ageRating
                        tvDuration.text = it.duration
                        tvOverview.text = it.overview
                        tvReleaseDate.text = it.releaseDate
                        tvStatus.text = it.status
                        tvOriginalLanguage.text = it.originalLanguage
                        tvBudget.text = it.budget
                        tvRevenue.text = it.revenue
                        link = it.link
                        Glide.with(this@DetailMovieActivity)
                            .load(it.image)
                            .into(ivImage)
                        rvTag.apply {
                            setHasFixedSize(true)
                            adapter = DetailTagAdapter(it.tag)
                            layoutManager = LinearLayoutManager(
                                this@DetailMovieActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        }
                        swipeRefreshDetailMovie.isRefreshing = false
                        pbDetailMovie.visibility = View.GONE
                        showViews()
                    }
                }
            })
            getErrorMessage().observe(this@DetailMovieActivity, {
                if (it != null) {
                    ViewUtils.errorSnackbar(
                        coordinatorDetailMovie,
                        getString(R.string.no_connection)
                    )
                    swipeRefreshDetailMovie.isRefreshing = false
                    pbDetailMovie.visibility = View.GONE
                }
            })
        }
    }

    private fun showViews() {
        ivImage.visibility = View.VISIBLE
        tvTitle.visibility = View.VISIBLE
        tvYear.visibility = View.VISIBLE
        tvUserscore.visibility = View.VISIBLE
        tv_nametag_userscore.visibility = View.VISIBLE
        tvAgeRating.visibility = View.VISIBLE
        tv_nametag_agerating.visibility = View.VISIBLE
        tvDuration.visibility = View.VISIBLE
        tv_nametag_duration.visibility = View.VISIBLE
        rvTag.visibility = View.VISIBLE
        tv_nametag_overview.visibility = View.VISIBLE
        tvOverview.visibility = View.VISIBLE
        tv_nametag_movie_info.visibility = View.VISIBLE
        tv_nametag_releasedate.visibility = View.VISIBLE
        tvReleaseDate.visibility = View.VISIBLE
        tv_nametag_status.visibility = View.VISIBLE
        tvStatus.visibility = View.VISIBLE
        tv_nametag_originallanguage.visibility = View.VISIBLE
        tvOriginalLanguage.visibility = View.VISIBLE
        tv_nametag_budget.visibility = View.VISIBLE
        tvBudget.visibility = View.VISIBLE
        tv_nametag_revenue.visibility = View.VISIBLE
        tvRevenue.visibility = View.VISIBLE
        view_top_of_movie_info.visibility = View.VISIBLE
        view_end_of_userscore.visibility = View.VISIBLE
        view_start_of_duration.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, link)
                    type = "text/plain"
                }
                val title = resources.getString(R.string.share_this_movie_with)
                val chooser = Intent.createChooser(sendIntent, title)
                startActivity(chooser)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}