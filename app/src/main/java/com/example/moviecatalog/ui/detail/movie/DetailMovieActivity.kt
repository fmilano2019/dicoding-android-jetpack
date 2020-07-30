package com.example.moviecatalog.ui.detail.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.DetailTagAdapter
import com.example.moviecatalog.ui.movie.MoviesFragment
import com.example.moviecatalog.utils.MappingHelper
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        init()
        tbDetail.setNavigationOnClickListener { onBackPressed() }
        val id = intent.getIntExtra(MoviesFragment.EXTRA_MOVIE, -1)
        detailMovieViewModel.getSelectedMovieData(id).let {
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
            Glide.with(this)
                .load(it.image)
                .into(ivImage)
            rvTag.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailMovieActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = DetailTagAdapter(MappingHelper.stringToList(it.tag))
            }
        }
    }

    private fun init() {
        detailMovieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailMovieViewModel::class.java).apply {
            loadMovieData(this@DetailMovieActivity)
        }
    }
}