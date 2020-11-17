package com.example.moviecatalog.ui.detail.tvshow

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
import com.example.moviecatalog.ui.tvshow.TvShowsFragment
import com.example.moviecatalog.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class DetailTvShowActivity : AppCompatActivity() {

    private val detailTvShowViewModel: DetailTvShowViewModel by viewModel()
    private var id: Int by Delegates.notNull()
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        setSupportActionBar(tbDetail)
        supportActionBar?.title = null
        swipeRefreshDetailTvShow.setOnRefreshListener { detailTvShowViewModel.loadDetailTvShow(id) }
        tbDetail.setNavigationOnClickListener { onBackPressed() }
        link = resources.getString(R.string.link_not_available)
        id = intent.getIntExtra(TvShowsFragment.EXTRA_TVSHOW_ID, -1)
        subscribeUI(id)
    }

    private fun subscribeUI(id: Int) {
        detailTvShowViewModel.run {
            loadDetailTvShow(id)
            getDetailTvShow().observe(this@DetailTvShowActivity, {
                if (it != null) {
                    if (id == it.id) {
                        tvTitle.text = it.title
                        tvYear.text = it.year
                        tvUserscore.text = it.userScore
                        tvAgeRating.text = it.ageRating
                        tvDuration.text = it.duration
                        tvOverview.text = it.overview
                        tvStatus.text = it.status
                        tvOriginalLanguage.text = it.originalLanguage
                        tvType.text = it.type
                        tvNetwork.text = it.networks
                        link = it.link
                        Glide.with(this@DetailTvShowActivity)
                            .load(it.image)
                            .into(ivImage)
                        rvTag.apply {
                            setHasFixedSize(true)
                            adapter = DetailTagAdapter(it.genres)
                            layoutManager = LinearLayoutManager(
                                this@DetailTvShowActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        }
                        swipeRefreshDetailTvShow.isRefreshing = false
                        pbDetailTvShow.visibility = View.GONE
                        showViews()
                    }
                }
            })
            getErrorMessage().observe(this@DetailTvShowActivity, {
                if (it != null) {
                    ViewUtils.errorSnackbar(
                        coordinatorDetailTvShow,
                        getString(R.string.no_connection)
                    )
                    swipeRefreshDetailTvShow.isRefreshing = false
                    pbDetailTvShow.visibility = View.GONE
                }
            })
        }
    }

    private fun showViews() {
        ivImage.visibility = View.VISIBLE
        tvTitle.visibility = View.VISIBLE
        tvYear.visibility = View.VISIBLE
        tvUserscore.visibility = View.VISIBLE
        tvNametagUserscoreDetailTvShow.visibility = View.VISIBLE
        tvAgeRating.visibility = View.VISIBLE
        tvNametagAgeRatingDetailTvShow.visibility = View.VISIBLE
        tvDuration.visibility = View.VISIBLE
        tvNametagDurationDetailTvShow.visibility = View.VISIBLE
        viewEndOfUserscoreDetailTvShow.visibility = View.VISIBLE
        viewStartOfDurationDetailTvShow.visibility = View.VISIBLE
        rvTag.visibility = View.VISIBLE
        tvNametagOverviewDetailTvShow.visibility = View.VISIBLE
        tvOverview.visibility = View.VISIBLE
        viewTopOfTvShowInfoDetailTvShow.visibility = View.VISIBLE
        tvNametagTvShowInfoDetailTvShow.visibility = View.VISIBLE
        tvNametagStatusDetailTvShow.visibility = View.VISIBLE
        tvStatus.visibility = View.VISIBLE
        tvNametagNetworkDetailTvShow.visibility = View.VISIBLE
        tvNetwork.visibility = View.VISIBLE
        tvNametagTypeDetailTvShow.visibility = View.VISIBLE
        tvType.visibility = View.VISIBLE
        tvNametagOriginalLanguageDetailTvShow.visibility = View.VISIBLE
        tvOriginalLanguage.visibility = View.VISIBLE
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
                val title = resources.getString(R.string.share_this_show_with)
                val chooser = Intent.createChooser(sendIntent, title)
                startActivity(chooser)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}