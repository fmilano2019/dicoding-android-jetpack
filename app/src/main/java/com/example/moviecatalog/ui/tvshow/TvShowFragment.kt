package com.example.moviecatalog.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.tvshow.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    companion object {
        const val EXTRA_TVSHOW_ID = "extra_tvshow"
    }

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowAdapter = TvShowAdapter(arrayListOf()) {
            Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
                putExtra(EXTRA_TVSHOW_ID, it.id)
                startActivity(this)
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshTvShows.setOnRefreshListener { tvShowViewModel.loadPopularTvShows() }
        rvTvShows.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = tvShowAdapter
        }
        subscribeUI()
    }

    private fun subscribeUI() {
        tvShowViewModel.run {
            loadPopularTvShows()
            getPopularTvShows().observe(viewLifecycleOwner, {
                if (it != null) {
                    tvShowAdapter.updateTvShows(it)
                    swipeRefreshTvShows.isRefreshing = false
                    pbTvShows.visibility = View.GONE
                }
            })
            getErrorMessage().observe(viewLifecycleOwner, {
                if (it != null) {
                    swipeRefreshTvShows.isRefreshing = false
                    pbTvShows.visibility = View.GONE
                }
            })
        }
    }
}