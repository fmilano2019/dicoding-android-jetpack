package com.example.moviecatalog.ui.detail.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.DetailTagAdapter
import com.example.moviecatalog.ui.tvshow.TvShowsFragment
import com.example.moviecatalog.utils.MappingHelper
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        init()
        tbDetail.setNavigationOnClickListener { onBackPressed() }
        val id = intent.getIntExtra(TvShowsFragment.EXTRA_TVSHOW, -1)
        detailTvShowViewModel.getSelectedTvShowData(id).let {
            tvTitle.text = it.title
            tvYear.text = it.year
            tvUserscore.text = it.userScore
            tvAgeRating.text = it.ageRating
            tvDuration.text = it.duration
            tvOverview.text = it.overview
            tvStatus.text = it.status
            tvNetwork.text = it.network
            tvType.text = it.type
            tvOriginalLanguage.text = it.originalLanguage
            Glide.with(this)
                .load(it.image)
                .into(ivImage)
            rvTag.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailTvShowActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = DetailTagAdapter(MappingHelper.stringToList(it.tag))
            }
        }
    }

    private fun init() {
        detailTvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailTvShowViewModel::class.java
        ).apply {
            loadTvShowData(this@DetailTvShowActivity)
        }
    }
}